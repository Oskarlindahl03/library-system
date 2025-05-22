package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Loans;

import java.util.List;

public interface BookService {
    List<Books> getAllBooks();
    Books createBook(Books book);
    List<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title);
}
