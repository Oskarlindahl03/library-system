package com.example.library_system.Controller.Author;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class GetAuthorsSerchController {
    @Autowired
    AuthorService authorService;
    @GetMapping("/name/{lastName}")
    public List<Authors> findByLastName(@PathVariable String lastName){
        return authorService.findByLastName(lastName);
    }



}
