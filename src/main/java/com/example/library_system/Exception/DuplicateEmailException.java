package com.example.library_system.Exception;

public class DuplicateEmailException extends LibrarySystemException {
    public DuplicateEmailException(String email) {
        super("User with email " + email + " already exists");
    }
}