package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Books;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Service.ServiceInterface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title) {
        if (authorId != null && title != null) {
            return bookRepository.findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title);
        } else if (authorId != null) {
            return bookRepository.findByAuthorAuthorId(authorId);
        } else if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else {
            return bookRepository.findAll();
        }
    }
    @Override
    public Books createBook(Books book) {
        if (book.getTotalCopies() == null) {
            book.setTotalCopies(book.getAvailableCopies());
        }
        return bookRepository.save(book);
    }
}
