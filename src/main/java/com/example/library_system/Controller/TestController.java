// src/main/java/com/example/library_system/controller/TestController.java
package com.example.library_system.Controller;

import com.example.library_system.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/test")
    public String test() {
        return "Spring Boot is running!";
    }

    @GetMapping("/test/database")
    public Map<String, Object> testDatabase() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Database connection successful!");
        result.put("book_count", bookRepository.count());
        return result;
    }
}