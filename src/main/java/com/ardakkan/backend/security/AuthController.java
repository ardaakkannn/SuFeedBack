package com.ardakkan.backend.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ardakkan.backend.dto.RegisterRequest;
import com.ardakkan.backend.entity.User;
import com.ardakkan.backend.repo.UserRepository;
import com.ardakkan.backend.service.UserService;
import com.ardakkan.backend.service.VerificationCodeStore;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MyUserDetailsService userDetailsService;
    private final VerificationCodeStore verificationCodeStore;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, 
                          UserService userService, PasswordEncoder passwordEncoder,
                          MyUserDetailsService userDetailsService,
                          VerificationCodeStore verificationCodeStore,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.verificationCodeStore= verificationCodeStore;
        this.userRepository=userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            // Kullanıcıyı email ile yüklemeye çalışıyoruz
            var userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

            // Kullanıcı bulunduysa, şifreyi doğruluyoruz
            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                // Şifre yanlışsa hata mesajı döndür
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Şifre yanlış"));
            }

            // Şifre doğruysa, kimlik doğrulama işlemini gerçekleştiriyoruz
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            // Rolü alıp token oluşturuyoruz
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = tokenService.generateToken(authRequest.getEmail(), role);
            
            // Kullanıcı bilgilerini yanıt olarak döndürmek için User nesnesini alıyoruz
            User user = userService.findByEmail(authRequest.getEmail());

            // Yanıt olarak token, isim ve soyisim gönderiyoruz
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("userId", user.getId());

            // Token başarıyla oluşturuldu
            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            // Kullanıcı bulunamadıysa, uygun hata mesajını döndür
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Bu email adresi ile kayıtlı kullanıcı bulunamadı"));
        } catch (Exception e) {
            // Diğer hata durumları
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Giriş işlemi sırasında bir hata oluştu"));
        }
    }
    
    @PostMapping("/register/init")
    public ResponseEntity<String> initiateRegistration(@RequestBody RegisterRequest registerRequest) {
        try {
            // Kullanıcı bilgilerini doğrula
            userService.validateRegisterRequest(registerRequest);

            // Email'in kayıtlı olup olmadığını kontrol et
            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu email adresi zaten kayıtlı.");
            }
            
           // Mevcut doğrulama kodunun geçerliliğini kontrol et
            if (verificationCodeStore.hasValidCode(registerRequest.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Zaten geçerli bir doğrulama kodunuz var. Lütfen mevcut kodu kullanın.");
            }

            // Yeni doğrulama kodu oluştur ve kaydet
            String verificationCode = userService.sendVerificationCode(registerRequest.getEmail(),registerRequest.getFirstName());
            verificationCodeStore.saveVerificationCode(registerRequest.getEmail(), verificationCode);

            return ResponseEntity.ok("Doğrulama kodu email adresinize gönderildi.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hata: " + e.getMessage());
        }
    }

    
    
    @PostMapping("/register/complete")
    public ResponseEntity<Map<String, Object>> completeRegistration(@RequestBody RegisterRequest registerRequest) {
        try {
            // Kodun doğruluğunu ve geçerlilik süresini kontrol et
            boolean isValid = verificationCodeStore.isCodeValid(registerRequest.getEmail(), registerRequest.getVerificationCode());

            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Doğrulama kodu geçersiz veya süresi dolmuş."));
            }

            // Kullanıcıyı kaydet
            userService.registerUser(registerRequest);

            // Kullanıcı kayıt edildiği için kodu temizle
            verificationCodeStore.removeVerificationCode(registerRequest.getEmail());

            // Kayıttan sonra otomatik olarak giriş yap ve token oluştur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));

            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = tokenService.generateToken(registerRequest.getEmail(), role);

            // Yeni kayıt olan kullanıcının bilgilerini alıyoruz
            User user = userService.findByEmail(registerRequest.getEmail());

            // Yanıt olarak token, isim ve soyisim gönderiyoruz
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("userId", user.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Kayıt tamamlanamadı: " + e.getMessage()));
        }
    }



    // Yeni kullanıcı kaydı
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Yeni kullanıcı kaydı yapıyoruz
            userService.registerUser(registerRequest);

            // Kayıttan sonra otomatik olarak giriş yap ve token oluştur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));

            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String token = tokenService.generateToken(registerRequest.getEmail(), role);
            
            
            // Yeni kayıt olan kullanıcının bilgilerini alıyoruz
            User user = userService.findByEmail(registerRequest.getEmail());

            // Yanıt olarak token, isim ve soyisim gönderiyoruz
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("userId", user.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Kayıt başarısız: " + e.getMessage()));
        }
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> getLoginPage() {
        return ResponseEntity.ok("Login sayfasına yönlendirildiniz.");
    }

    @GetMapping("/register")
    public ResponseEntity<String> getRegisterPage() {
        return ResponseEntity.ok("Register sayfasına yönlendirildiniz.");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Kullanıcıyı frontend'de token'ı silmesi konusunda bilgilendiriyoruz
        // Eğer JWT token kullanılıyorsa, backend'de ayrıca bir işlem yapmamıza gerek yoktur.
        
        // Alternatif olarak, JWT blacklisting gibi bir mekanizma kullanıyorsanız, burada token'i geçersiz kılabilirsiniz.
        return ResponseEntity.ok("Çıkış yapıldı. Lütfen token'i istemci tarafından temizleyin.");
    }

}

// AuthRequest sınıfı, login için gereken email ve password alanlarını içerir
class AuthRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
