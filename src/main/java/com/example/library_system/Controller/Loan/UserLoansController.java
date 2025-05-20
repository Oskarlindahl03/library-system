package com.example.library_system.Controller.Loan;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserLoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/{userId}/loans")
    public List<Loans> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUserId(userId);
    }
}
