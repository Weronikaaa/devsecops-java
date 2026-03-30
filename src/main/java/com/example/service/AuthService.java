package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // PODATNOŚĆ #4: Hardcoded credentials
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String API_KEY = "sk-1234567890abcdef";

    public boolean authenticate(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    public String getApiKey() {
        return API_KEY;
    }
}
