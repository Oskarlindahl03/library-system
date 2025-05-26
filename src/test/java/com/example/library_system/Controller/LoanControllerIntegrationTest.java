package com.example.library_system.Controller;

import com.example.library_system.Dto.LoanRequestDTO;
import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateLoan() throws Exception {
        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        Users savedUser = userRepository.save(user);

        Books book = new Books();
        book.setTitle("Effective Java");
        book.setPublicationYear(2018);
        book.setAvailableCopies(5);
        book.setTotalCopies(5);
        Books savedBook = bookRepository.save(book);

        LoanRequestDTO request = new LoanRequestDTO();
        request.setUserId(savedUser.getUserId());
        request.setBookId(savedBook.getBookId());

        mockMvc.perform(post("/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

}
