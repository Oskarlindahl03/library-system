package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;

import java.util.List;

public interface BookService {
    List<Books> getAllBooks();
    Books createBook(Books book);
    List<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title);
    List<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails();
    List<BookWithLimitedDetailsDTO> findDetailedBooks(Long authorId, String title);

}
