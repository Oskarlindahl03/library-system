package com.example.library_system.Repository;

import com.example.library_system.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByEmailContainingIgnoreCase(String email);


    Optional<Users> findByUserId(Long userId);
}
