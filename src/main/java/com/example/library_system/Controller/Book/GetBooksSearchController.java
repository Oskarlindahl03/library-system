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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class GetBooksSearchController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<Page<Books>> findByAuthorAuthorIdAndTitleContainingIgnoreCase(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String title,
            Pageable pageable) {

        Page<Books> booksPage = bookService.findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title, pageable);

        if (booksPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(booksPage); // 200 OK
    }

    @GetMapping("/detailed/search")
    public ResponseEntity<Page<BookWithLimitedDetailsDTO>> findDetailedBooks(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String title,
            Pageable pageable) {

        Page<BookWithLimitedDetailsDTO> booksPage = bookService.findDetailedBooks(authorId, title, pageable);

        if (booksPage.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(booksPage); // 200 OK
    }
}


