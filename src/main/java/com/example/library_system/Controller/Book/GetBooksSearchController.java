package com.example.library_system.Controller.Book;

import com.example.library_system.Entity.Books;
import com.example.library_system.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class GetBooksSearchController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public List<Books> getBooksByAuthorOrTitle(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String title) {
        return bookService.getBooksByAuthorOrTitle(authorId, title);
    }
}


