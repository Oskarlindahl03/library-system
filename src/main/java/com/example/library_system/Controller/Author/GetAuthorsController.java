package com.example.library_system.Controller.Author;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class GetAuthorsController {
    @Autowired
    AuthorService authorService;
    @GetMapping("/all")
    public List<Authors> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}