package com.example.library_system.Controller.Book;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;
import com.example.library_system.Service.ServiceInterface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<Books>> getAllBooks(Pageable pageable) {
        Page<Books> books = bookService.getAllBooks(pageable);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(books); // 200 OK
    }

    @GetMapping("/detailed")
    public ResponseEntity<Page<BookWithLimitedDetailsDTO>> getAllBooksWithLimitedDetails(Pageable pageable) {
        Page<BookWithLimitedDetailsDTO> books = bookService.getAllBooksWithLimitedDetails(pageable);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(books); // 200 OK
    }
}
