package com.example.config;

import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class DatabaseConfig {

    // PODATNOŚĆ #5: Hardcoded credentials w konfiguracji
    private String dbPassword = "root";

    // PODATNOŚĆ #6: Słabe szyfrowanie (DES)
    public String encryptDES(String data) throws Exception {
        byte[] key = "secret12".getBytes(); // 8 bajtów dla DES
        SecretKeySpec secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
