// src/main/java/com/example/library_system/Repository/BookRepository.java
package com.example.library_system.Repository;

import com.example.library_system.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {
    List<Books> findBooksById(Long bookId);
}