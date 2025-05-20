package com.example.library_system.Service;

import com.example.library_system.Entity.Loans;
import java.util.List;

public interface LoanService {
    List<Loans> getLoansByUserId(Long userId);
    Loans loanBook(Long userId, Long bookId);
    Loans returnBook(Long loanId);
}
