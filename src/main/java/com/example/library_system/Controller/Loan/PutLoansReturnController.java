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
public class PutLoansReturnController {

    @Autowired
    private LoanService loanService;

    @PutMapping("/{loanId}/return")
    public ResponseEntity<Loans> returnLoan(@PathVariable Long loanId){
        try {
            Loans returnedLoan = loanService.returnLoan(loanId);
            return ResponseEntity.ok(returnedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
