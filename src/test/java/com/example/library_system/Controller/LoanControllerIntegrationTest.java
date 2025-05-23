package com.example.library_system.Controller;

import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private Long userId;
    private Long bookId;

    @BeforeEach
    public void setup() {
        Users user = new Users();
        user.setFirstName("Anna");
        user.setLastName("Smith");
        user.setEmail("anna@example.com");
        user.setPassword("secret");
        user.setRegistrationDate(LocalDate.now());
        userId = userRepository.save(user).getUserId();

        Books book = new Books();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        bookId = bookRepository.save(book).getBookId();
    }

    @Test
    public void testCreateLoan() throws Exception {
        mockMvc.perform(post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": " + userId + ", \"bookId\": " + bookId + "}"))
                .andExpect(status().isOk());
    }
}
