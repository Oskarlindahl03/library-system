package com.example.library_system.Controller.Loan;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.ServiceInterface.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Loans findByLoanLoanId(@PathVariable Long loanId){
        return loanService.returnLoan(loanId);
    }
}
