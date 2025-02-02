package com.ardakkan.backend.service;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;


@Service
public class VerificationCodeStore {
	private final Map<String, VerificationCodeEntry> verificationCodes = new ConcurrentHashMap<>();
	// Kodların geçerlilik süresi (dakika cinsinden)
    private static final long CODE_VALIDITY_MINUTES = 2;
    public void saveVerificationCode(String email, String code) {
        VerificationCodeEntry existingEntry = verificationCodes.get(email);

        // Eğer mevcut bir kod varsa ve süresi dolmamışsa, yeni kod oluşturulmasını engelle
        if (existingEntry != null && existingEntry.getCreatedAt().plusMinutes(2).isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Halen geçerli bir doğrulama kodunuz bulunuyor.");
        }

        // Yeni kodu kaydet
        verificationCodes.put(email, new VerificationCodeEntry(code, LocalDateTime.now()));
    }

 // Kodun geçerliliğini kontrol etme
    public boolean isCodeValid(String email, String code) {
        VerificationCodeEntry entry = verificationCodes.get(email);
        if (entry == null || isCodeExpired(entry)) {
            verificationCodes.remove(email); // Süresi dolmuş veya bulunamayan kodu temizle
            return false;
        }
        // Kod eşleşiyor mu?
        return entry.getCode().equals(code);
    }

    
 // Belirli bir e-posta için geçerli bir kod olup olmadığını kontrol etme
    public boolean hasValidCode(String email) {
        VerificationCodeEntry entry = verificationCodes.get(email);
        return entry != null && !isCodeExpired(entry);
    }

    // Kodun süresinin dolup dolmadığını kontrol etme
    private boolean isCodeExpired(VerificationCodeEntry entry) {
        return entry.getCreatedAt().plusMinutes(CODE_VALIDITY_MINUTES).isBefore(LocalDateTime.now());
    }

    
    

    public void removeVerificationCode(String email) {
        verificationCodes.remove(email);
    }

    // Kod ve oluşturulma zamanını saklayan iç sınıf
    private static class VerificationCodeEntry {
        private final String code;
        private final LocalDateTime createdAt;

        public VerificationCodeEntry(String code, LocalDateTime createdAt) {
            this.code = code;
            this.createdAt = createdAt;
        }

        public String getCode() {
            return code;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
