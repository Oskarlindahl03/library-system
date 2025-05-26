package com.example.library_system.Repository;

import com.example.library_system.Entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Long> {
    List<Authors> findByLastNameContainingIgnoreCase(String lastName);

}
