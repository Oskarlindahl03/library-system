package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Books> getAllBooks(Pageable pageable);
    Page<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails(Pageable pageable);
    Page<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title, Pageable pageable);
    Page<BookWithLimitedDetailsDTO> findDetailedBooks(Long authorId, String title, Pageable pageable);
    Books createBook(Books book);
}

