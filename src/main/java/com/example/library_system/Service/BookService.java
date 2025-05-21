package com.example.library_system.Service;

import com.example.library_system.Entity.Books;

import java.util.List;

public interface BookService {
    List<Books> getAllBooks();

    List<Books> getBooksByAuthorOrTitle(Long authorId, String title);

}
