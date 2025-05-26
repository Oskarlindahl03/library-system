package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;
import com.example.library_system.Exception.BookNotFoundException;
import com.example.library_system.Exception.LibrarySystemException;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.library_system.Service.ServiceInterface.BookService;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<Books> getAllBooks(Pageable pageable) {
        try {
            Page<Books> books = bookRepository.findAll(pageable);
            if (books.isEmpty()) {
                throw new BookNotFoundException("No books found in the system");
            }
            return books;
        } catch (Exception e) {
            if (e instanceof BookNotFoundException) {
                throw e;
            }
            throw new LibrarySystemException("Failed to retrieve books: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails(Pageable pageable) {
        try {
            Page<Books> books = bookRepository.findAll(pageable);
            if (books.isEmpty()) {
                throw new BookNotFoundException("No books found in the system");
            }
            return books.map(bookMapper::toLimitedDTO);
        } catch (Exception e) {
            if (e instanceof BookNotFoundException) {
                throw e;
            }
            throw new LibrarySystemException("Failed to retrieve book details: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title, Pageable pageable) {
        try {
            Page<Books> books;

            if (authorId != null && title != null) {
                books = bookRepository.findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title, pageable);
            } else if (authorId != null) {
                books = bookRepository.findByAuthorAuthorId(authorId, pageable);
            } else if (title != null) {
                books = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
            } else {
                books = bookRepository.findAll(pageable);
            }

            if (books.isEmpty()) {
                throw new BookNotFoundException("No books found");
            }

            return books;
        } catch (Exception e) {
            if (e instanceof BookNotFoundException) {
                throw e;
            }
            throw new LibrarySystemException("Failed to search books: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<BookWithLimitedDetailsDTO> findDetailedBooks(Long authorId, String title, Pageable pageable) {
        try {
            Page<Books> booksPage = findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title, pageable);
            return booksPage.map(bookMapper::toLimitedDTO);
        } catch (Exception e) {
            if (e instanceof BookNotFoundException) {
                throw e;
            }
            throw new LibrarySystemException("Failed to retrieve detailed book information: " + e.getMessage(), e);
        }
    }

    @Override
    public Books createBook(Books book) {
        try {
            if (book == null) {
                throw new IllegalArgumentException("Book cannot be null");
            }

            // Validate required fields
            if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
                throw new IllegalArgumentException("Book title is required");
            }

            if (book.getAuthor() == null) {
                throw new IllegalArgumentException("Book author is required");
            }

            return bookRepository.save(book);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new LibrarySystemException("Failed to create book: " + e.getMessage(), e);
        }
    }
}
