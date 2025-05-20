package com.example.library_system.Service.ServiceIMPL;

import com.example.library_system.Entity.Books;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Repository.LoanRepository;
import com.example.library_system.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library_system.Repository.UserRepository;
import com.example.library_system.Entity.Users;
import com.example.library_system.Repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceIMPL implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanServiceIMPL(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Loans> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    @Override
    public Loans loanBook(Long userId, Long bookId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available for loan");
        }

        // Decrease available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book); // Save the updated book

        Loans loan = new Loans();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));

        return loanRepository.save(loan);
    }
    @Override
    public Loans returnBook(Long loanId) {
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getReturnedDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        loan.setReturnedDate(LocalDate.now());

        // Increase availableCopies
        Books book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

}
