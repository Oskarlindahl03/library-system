package com.example.library_system.Exception;

public class BookNotFoundException extends LibrarySystemException {
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Long bookId) {
        super("Book with ID " + bookId + " not found");
    }
}
