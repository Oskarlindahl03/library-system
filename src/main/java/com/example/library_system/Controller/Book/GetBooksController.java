package com.example.library_system.Controller.Book;

import com.example.library_system.Entity.Books;
import com.example.library_system.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class GetBooksController {

    @Autowired
    private BookService bookService;

    // This endpoint will be accessible at: GET /books/all
    @GetMapping("/all")
    public List<Books> getAllBooks() {
        return bookService.getAllBooks();
    }
}
