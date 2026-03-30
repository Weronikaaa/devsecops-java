package com.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // PODATNOŚĆ #1: SQL INJECTION
    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        // ZŁY KOD: konkatenacja stringów zamiast prepared statement
        String query = "SELECT * FROM users WHERE id = " + id;
        try {
            return jdbcTemplate.queryForObject(query, String.class);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // PODATNOŚĆ #2: Command Injection
    @GetMapping("/ping")
    public String ping(@RequestParam String ip) {
        try {
            // ZŁY KOD: bez walidacji wejścia
            Process process = Runtime.getRuntime().exec("ping -c 1 " + ip);
            process.waitFor();
            return "Ping executed";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // PODATNOŚĆ #3: Path Traversal
    @GetMapping("/file")
    public String readFile(@RequestParam String filename) {
        try {
            // ZŁY KOD: brak walidacji ścieżki
            java.nio.file.Path path = java.nio.file.Paths.get("/tmp/uploads/" + filename);
            return java.nio.file.Files.readString(path);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
