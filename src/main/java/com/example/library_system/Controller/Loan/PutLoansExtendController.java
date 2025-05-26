package com.example.library_system.Controller.Loan;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.ServiceInterface.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
    public class PutLoansExtendController {

        @Autowired
        private LoanService loanService;

        @PutMapping("/{loanId}/extend")
        public ResponseEntity<?> extendLoan(@PathVariable Long loanId) {
            try {
                Loans extendedLoan = loanService.extendLoan(loanId);
                return ResponseEntity.ok(extendedLoan);
            } catch (RuntimeException e) {
                // Return 400 Bad Request with error message
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }


}
