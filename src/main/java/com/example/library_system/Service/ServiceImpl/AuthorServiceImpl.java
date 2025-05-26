package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Authors;
import com.example.library_system.Exception.*;
import com.example.library_system.Repository.AuthorRepository;
import com.example.library_system.Service.ServiceInterface.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Authors> getAllAuthors() {
        List<Authors> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new AuthorNotFoundException("No authors found in the system");
        }
        return authors;
    }

    @Override
    public List<Authors> findByLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new LibrarySystemException("Last name cannot be empty");
        }

        List<Authors> authors = authorRepository.findByLastNameContainingIgnoreCase(lastName);
        if (authors.isEmpty()) {
            throw new AuthorNotFoundException("No authors found with last name containing: " + lastName);
        }
        return authors;
    }

    @Override
    @Transactional
    public Authors createAuthor(Authors author) {
        if (author == null) {
            throw new LibrarySystemException("Author cannot be null");
        }

        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
            throw new LibrarySystemException("Author first name cannot be empty");
        }

        if (author.getLastName() == null || author.getLastName().trim().isEmpty()) {
            throw new LibrarySystemException("Author last name cannot be empty");
        }

        // Check if author with same first and last name already exists
        if (authorRepository.existsByFirstNameAndLastName(
                author.getFirstName(),
                author.getLastName())) {
            throw new LibrarySystemException(
                    "Author '" + author.getFirstName() + " " + author.getLastName() + "' already exists");
        }

        return authorRepository.save(author);
    }
}
