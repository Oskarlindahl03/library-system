package com.example.library_system.Repository;

import com.example.library_system.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findUserById(Long userId);
}
