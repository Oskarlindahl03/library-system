package com.example.library_system.Controller.Author;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Authors createAuthor(@RequestBody Authors author){
        return authorService.createAuthor(author);
    }
}
