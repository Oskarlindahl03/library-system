package com.example.library_system.Controller.Book;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;
import com.example.library_system.Service.ServiceInterface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class GetBooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Books> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }
    @GetMapping("/limited-details")
    public Page<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails(Pageable pageable) {
        return bookService.getAllBooksWithLimitedDetails(pageable);
    }
}
