package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Books;
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
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<BookWithLimitedDetailsDTO> getAllBooksWithLimitedDetails(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toLimitedDTO);
    }

    @Override
    public Page<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title, Pageable pageable) {
        if (authorId != null && title != null) {
            return bookRepository.findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title, pageable);
        } else if (authorId != null) {
            return bookRepository.findByAuthorAuthorId(authorId, pageable);
        } else if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }

    @Override
    public Page<BookWithLimitedDetailsDTO> findDetailedBooks(Long authorId, String title, Pageable pageable) {
        Page<Books> booksPage = findByAuthorAuthorIdAndTitleContainingIgnoreCase(authorId, title, pageable);
        return booksPage.map(bookMapper::toLimitedDTO);
    }
    @Override
    public Books createBook(Books book) {
        return bookRepository.save(book);
    }
}
