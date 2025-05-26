package com.example.library_system.Repository;

import com.example.library_system.Entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findByEmailContainingIgnoreCase(String email);
    Optional<Users> findByUserId(Long userId);

    @Query("SELECT u FROM Users u")
    List<Users> findAllUsersCustom();


    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email WHERE u.userId = :userId")
    int updateUserById(@Param("userId") Long userId,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("email") String email);
}
