package com.example.library_system.Exception;

public class LibrarySystemException extends RuntimeException {
    public LibrarySystemException(String message) {
        super(message);
    }

    public LibrarySystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
