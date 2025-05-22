package com.example.library_system.Service.ServiceImpl;

import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Repository.LoanRepository;
import com.example.library_system.Service.ServiceInterface.LoanService;
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
    public Loans createLoan(Long userId, Long bookId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available for loan");
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
    public Loans returnLoan(Long loanId) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getReturnedDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        loan.setReturnedDate(LocalDate.now());

        Books book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }
    @Override
    public Loans extendLoan(Long loanId){
        Loans loan = loanRepository.findByLoanId(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getReturnedDate() != null) {
            throw new RuntimeException("Cannot extend a returned loan");
        }

        // If already extended
        if (loan.getDueDate().isAfter(loan.getBorrowedDate().plusDays(14))) {
            throw new RuntimeException("Loan has already been extended once");
        }

        loan.setDueDate(loan.getDueDate().plusDays(7));
        return loanRepository.save(loan);
    }

}
