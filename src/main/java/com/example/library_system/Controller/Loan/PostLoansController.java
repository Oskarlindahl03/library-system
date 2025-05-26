package com.example.library_system.Controller.Loan;

import com.example.library_system.Dto.LoanRequestDTO;
import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.ServiceInterface.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Loans> createLoan(@RequestBody LoanRequestDTO request) {
        Loans createdLoan = loanService.createLoan(request.getUserId(), request.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }


}