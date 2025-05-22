package com.example.library_system.Service.ServiceInterface;

import com.example.library_system.Entity.Loans;
import java.util.List;

public interface LoanService {
    List<Loans> getLoansByUserId(Long userId);
    Loans createLoan(Long userId, Long bookId);
    Loans returnLoan(Long loanId);
    Loans extendLoan(Long loanId);
}
