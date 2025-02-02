package com.ardakkan.backend.service;


import com.ardakkan.backend.dto.RegisterRequest;
import com.ardakkan.backend.dto.UserDTO;
import com.ardakkan.backend.entity.User;
import com.ardakkan.backend.entity.UserRoles;

import com.ardakkan.backend.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService= mailService;
       
    }
    
    
    
    public String sendVerificationCode(String email, String firstName) {
        // Rastgele bir 6 haneli doğrulama kodu oluştur
        String verificationCode = String.format("%06d", new Random().nextInt(999999));

        // Kullanıcıya doğrulama kodunu gönder
        mailService.sendSimpleMail(email, "Sabancı Üniversitesi Hesap Doğrulama Kodu",
                "Merhaba " + firstName + ",\n\nDoğrulama kodunuz: " + verificationCode + "\n\nTeşekkürler.");

        return verificationCode; // Kodu döndür
    }

    public void registerUser(RegisterRequest registerRequest) {
        // Gerekli alanların dolu olup olmadığını kontrol ediyoruz
        validateRegisterRequest(registerRequest);

        // Emailin zaten kayıtlı olup olmadığını kontrol ediyoruz
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalStateException("Bu email adresi zaten kayıtlı.");
        }

        // Şifreyi encode ediyoruz
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // Yeni kullanıcı oluşturuyoruz
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(encodedPassword);
        user.setEmail(registerRequest.getEmail());
        user.setRole(UserRoles.USER);

        // Kullanıcıyı veritabanına kaydediyoruz
        userRepository.save(user);

        
    }

    public void validateRegisterRequest(RegisterRequest registerRequest) {
        if (registerRequest.getFirstName() == null || registerRequest.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("İsim boş olamaz.");
        }
        if (registerRequest.getLastName() == null || registerRequest.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Soyisim boş olamaz.");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Şifre boş olamaz.");
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email boş olamaz.");
        }
        
     // Email domain doğrulaması
        validateEmailDomain(registerRequest.getEmail());
    }
    
    private void validateEmailDomain(String email) {
        if (!email.endsWith("@sabanciuniv.edu") &&
            !email.endsWith("@alumni.sabanciuniv.edu") &&
            !email.endsWith("@emeritus.sabanciuniv.edu") &&
            !email.endsWith("@connect.sabanciuniv.edu")) {
            throw new IllegalArgumentException("Sadece Sabancı Üniversitesi uzantılı mailler kullanılabilir.");
        }
    }




    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Kullanıcı bulunamadı: " + id));

        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        
        
    }
    
    
    public User findByEmail(String email) {
        // User bulunamazsa, bir hata fırlatabiliriz veya Optional olarak dönebiliriz
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));
    }
    
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());


        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
       
        user.setRole(userDTO.getRole());

  
        return user;
    }
    
    
    
    
    
}
