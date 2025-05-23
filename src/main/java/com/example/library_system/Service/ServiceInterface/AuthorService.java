package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Entity.Authors;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    List<Authors> getAllAuthors();

    List<Authors> findByLastName(String lastName);
    Authors createAuthor(Authors author);
}
