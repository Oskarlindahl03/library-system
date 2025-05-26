package com.example.library_system.Controller.Loan;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.ServiceInterface.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GetLoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/{userId}/loans")
    public ResponseEntity<List<Loans>> getLoansByUserId(@PathVariable Long userId) {
        List<Loans> loans = loanService.getLoansByUserId(userId);

        if (loans.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(loans); // 200 OK with list
    }
}
