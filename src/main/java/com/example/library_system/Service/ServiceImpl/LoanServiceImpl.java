package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Repository.LoanRepository;
import com.example.library_system.Service.ServiceInterface.LoanService;
import com.example.library_system.Exception.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Loans> getLoansByUserId(Long userId) {
        return loanRepository.findByUserUserId(userId);
    }

    @Override
    @Transactional
    public Loans createLoan(Long userId, Long bookId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException(bookId);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loans loan = new Loans();
        loan.setUser(user);
        loan.setBook(book);
        loan.setReturnedDate(null);
        loan.setBorrowedDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));

        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    public Loans returnLoan(Long loanId) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        if (loan.getReturnedDate() != null) {
            throw new InvalidLoanOperationException("Book was already returned on " + loan.getReturnedDate());
        }

        loan.setReturnedDate(LocalDate.now());

        Books book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    @Override
    public Loans extendLoan(Long loanId) {
        Loans loan = loanRepository.findByLoanId(loanId)
                .orElseThrow(() -> new LoanNotFoundException(loanId));

        if (loan.getReturnedDate() != null) {
            throw new InvalidLoanOperationException("Cannot extend a returned loan");
        }

        // If already extended
        if (loan.getDueDate().isAfter(loan.getBorrowedDate().plusDays(14))) {
            throw new InvalidLoanOperationException("Loan has already been extended once");
        }

        loan.setDueDate(loan.getDueDate().plusDays(7));
        return loanRepository.save(loan);
    }
}
