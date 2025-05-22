package com.example.library_system.Repository;

import com.example.library_system.Entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans, Long> {
    List<Loans> findByUserUserId(Long userId);
    Optional<Loans> findByLoanId(Long loanId);
    List<Loans> findByUserUserIdAndBookBookId(Long userId, Long bookId);

}

