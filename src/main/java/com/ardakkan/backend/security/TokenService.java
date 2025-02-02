package com.ardakkan.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Güçlü bir gizli anahtar oluşturuyoruz

    // 1. JWT Token Üretimi (Email ve rol ile)
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)  // Kullanıcının email adresini subject olarak belirliyoruz
                .claim("role", role)  // Token'e kullanıcı rolü ekliyoruz
                .setIssuedAt(new Date())  // Token'in üretildiği tarih
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 saatlik geçerlilik süresi
                .signWith(secretKey)  // Token'i gizli anahtar ile imzalıyoruz
                .compact();  // Token'i üret ve geri döndür
    }

    // 2. JWT Token'dan Email Çıkartma
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Token imzasını doğrulamak için gizli anahtarımızı kullanıyoruz
                .build()
                .parseClaimsJws(token)  // Token'i çözüp doğruluyoruz
                .getBody()
                .getSubject();  // Kullanıcı emailini (subject) döner
    }

    // 3. JWT Token'dan Kullanıcı Rolünü Çıkartma
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // İmzayı doğrulamak için gizli anahtarı kullanıyoruz
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);  // Token'dan rolü çıkartır ve döner
    }

    // 4. Token'in Geçerli Olup Olmadığını Kontrol Etme
    public boolean isTokenValid(String token, String email) {
        String extractedEmail = extractEmail(token);  // Token'dan çıkarılan email
        return (extractedEmail.equals(email) && !isTokenExpired(token));  // Email ve token süresi kontrolü
    }

    // 5. Token'in Süresinin Dolup Dolmadığını Kontrol Etme
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());  // Token süresi şimdiki tarihten önceyse, süresi dolmuştur
    }
}
