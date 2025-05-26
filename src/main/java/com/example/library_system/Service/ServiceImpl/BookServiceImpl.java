package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Dto.AuthorDTO;
import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Authors;
import com.example.library_system.Entity.Books;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Service.ServiceInterface.BookService;
import com.example.library_system.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

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
    @Override
    public List<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toLimitedDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookWithLimitedDetailsDTO> findDetailedBooks(Long authorId, String title) {
        List<Books> books;

        if (authorId != null && title != null) {
            books = bookRepository.findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title);
        } else if (authorId != null) {
            books = bookRepository.findByAuthorAuthorId(authorId);
        } else if (title != null) {
            books = bookRepository.findByTitleContainingIgnoreCase(title);
        } else {
            books = bookRepository.findAll();
        }

        return books.stream()
                .map(bookMapper::toLimitedDTO)
                .collect(Collectors.toList());
    }
}
