package com.example.library_system.Exception;

public class BookNotAvailableException extends LibrarySystemException {
    public BookNotAvailableException(Long bookId) {
        super("Book with ID " + bookId + " is not available for loan");
    }
}
