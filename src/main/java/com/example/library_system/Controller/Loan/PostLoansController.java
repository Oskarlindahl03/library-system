package com.example.library_system.Controller.Loan;

import com.example.library_system.Dto.LoanRequest;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.ServiceInterface.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class PostLoansController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loans createLoan(@RequestBody LoanRequest request) {
        return loanService.createLoan(request.getUserId(), request.getBookId());
    }
}