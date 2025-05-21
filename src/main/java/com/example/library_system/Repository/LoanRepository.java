package com.example.library_system.Repository;

import com.example.library_system.Entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByUserId(Long userId);
    List<Loans> findByUserIdAndBookId(Long userId, Long bookId);
    Optional<Loans> findByLoanId(Long loanId);

}

