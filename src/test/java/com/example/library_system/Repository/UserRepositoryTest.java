package com.example.library_system.Repository;

import com.example.library_system.Entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@EntityScan(basePackages = "com.example.library_system.Entity")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Test
    void checkDdlAuto() {
        assertThat(ddlAuto).isEqualTo("create-drop");
    }

    @Test
    @DisplayName("Save and find user by ID")
    void testSaveAndFindById() {
        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("secret");

        Users savedUser = userRepository.save(user);

        Optional<Users> found = userRepository.findByUserId(savedUser.getUserId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("john.doe@example.com");
    }
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveUser() {
        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Users saved = entityManager.persistFlushFind(user);

        assertNotNull(saved.getUserId());
    }

    @Test
    @DisplayName("Find by email containing ignoring case")
    void testFindByEmailContainingIgnoreCase() {
        Users user = new Users();
        user.setFirstName("Alice");
        user.setLastName("Smith");
        user.setEmail("alice@example.com");
        user.setPassword("pass");
        userRepository.save(user);

        List<Users> results = userRepository.findByEmailContainingIgnoreCase("ALI");
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    @DisplayName("Update user details by ID")
    void testUpdateUserById() {
        Users user = new Users();
        user.setFirstName("Bob");
        user.setLastName("Brown");
        user.setEmail("bob@example.com");
        user.setPassword("pwd");
        Users savedUser = userRepository.save(user);

        int updatedCount = userRepository.updateUserById(savedUser.getUserId(), "Robert", "Brown", "robert.brown@example.com");
        assertThat(updatedCount).isEqualTo(1);

        Optional<Users> updatedUser = userRepository.findByUserId(savedUser.getUserId());
        assertThat(updatedUser).isPresent();
        assertThat(updatedUser.get().getFirstName()).isEqualTo("Robert");
        assertThat(updatedUser.get().getEmail()).isEqualTo("robert.brown@example.com");
    }

    @Test
    @DisplayName("Check existence by email")
    void testExistsByEmail() {
        Users user = new Users();
        user.setFirstName("Carol");
        user.setLastName("White");
        user.setEmail("carol@example.com");
        user.setPassword("pwd");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("carol@example.com");
        assertThat(exists).isTrue();

        boolean notExists = userRepository.existsByEmail("noone@example.com");
        assertThat(notExists).isFalse();
    }

    @Test
    @DisplayName("Find by exact email")
    void testFindByEmail() {
        Users user = new Users();
        user.setFirstName("Dave");
        user.setLastName("Green");
        user.setEmail("dave@example.com");
        user.setPassword("pwd");
        userRepository.save(user);

        Users foundUser = userRepository.findByEmail("dave@example.com");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getFirstName()).isEqualTo("Dave");
    }
}

