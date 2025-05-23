package com.example.library_system.Service;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Entity.Users;
import com.example.library_system.Entity.Books;
import com.example.library_system.Repository.LoanRepository;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Repository.BookRepository;
import com.example.library_system.Service.ServiceImpl.LoanServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    public LoanServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateLoan() {
        Users user = new Users();
        user.setUserId(1L);

        Books book = new Books();
        book.setBookId(1L);

        Loans loan = new Loans();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.save(any(Loans.class))).thenReturn(loan);

        Loans result = loanService.createLoan(1L, 1L);

        assertEquals(loan.getBook(), result.getBook());
        assertEquals(loan.getUser(), result.getUser());

        verify(loanRepository, times(1)).save(any(Loans.class));
    }
}
