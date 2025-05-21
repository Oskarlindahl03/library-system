package com.example.library_system.Service.ServiceIMPL;

import com.example.library_system.Entity.Books;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceIMPL implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Books> getBooksByAuthorOrTitle(Long authorId, String title) {
        if (authorId != null && title != null) {
            return bookRepository.findByAuthorIdAndTitleContainingIgnoreCase(authorId, title);
        } else if (authorId != null) {
            return bookRepository.findByAuthorId(authorId);
        } else if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else {
            return bookRepository.findAll();
        }
    }



}
