package com.example.library_system.Controller.Author;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class PostAuthorsController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Authors> createAuthor(@RequestBody Authors author) {
        try {
            Authors savedAuthor = authorService.createAuthor(author);
            return ResponseEntity.status(201).body(savedAuthor);// HTTP 201 Created
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();// HTTP 400 Bad Request
        }
    }
}
