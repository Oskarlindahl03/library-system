package com.example.library_system.Exception;

public class UserNotFoundException extends LibrarySystemException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("User with ID " + userId + " not found");
    }
}