package com.example.library_system.Controller.Book;

import com.example.library_system.Entity.Books;
import com.example.library_system.Service.ServiceInterface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class PostBooksController {

    @Autowired
    private BookService bookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        try {
            Books savedBook = bookService.createBook(book);

            return ResponseEntity
                    .created(URI.create("/books/" + savedBook.getBookId()))
                    .body(savedBook);

        } catch (Exception e) {
            e.printStackTrace();
            // 400 Bad Request if something went wrong (e.g. validation failed)
            return ResponseEntity.badRequest().build();
        }
    }

}