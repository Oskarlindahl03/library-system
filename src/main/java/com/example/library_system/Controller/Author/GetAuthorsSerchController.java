package com.example.library_system.Controller.Author;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class GetAuthorsSerchController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/name/{lastName}")
    public ResponseEntity<List<Authors>> findByLastName(@PathVariable String lastName) {
        List<Authors> authors = authorService.findByLastName(lastName);

        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();// 204 No Content
        } else {
            return ResponseEntity.ok(authors);// 200 OK
        }
    }



}
