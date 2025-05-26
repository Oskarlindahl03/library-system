package com.example.library_system.Repository;

import com.example.library_system.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
    Page<Books> findByAuthorAuthorIdAndTitleContainingIgnoreCase(Long authorId, String title, Pageable pageable);
    Page<Books> findByAuthorAuthorId(Long authorId, Pageable pageable);
    Page<Books> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
