package com.example.library_system.Service;

import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Repository.LoanRepository;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Service.ServiceImpl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoanServiceImpl loanService;  // ← this must be the concrete class

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        Long bookId = 1L;
        Long userId = 1L;
        Books book = new Books();
        book.setBookId(bookId);
        book.setAvailableCopies(5);

        Users user = new Users();
        user.setUserId(userId);

        Loans loan = new Loans();
        loan.setBook(book);
        loan.setUser(user);
        loan.setBorrowedDate(LocalDate.now());
        loan.setReturnedDate(LocalDate.now().plusDays(14));

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(loanRepository.save(any(Loans.class))).thenReturn(loan);

        Loans createdLoan = loanService.createLoan(bookId, userId);

        assertEquals(user, createdLoan.getUser());
        assertEquals(book, createdLoan.getBook());
        verify(bookRepository).save(book);
    }
}
