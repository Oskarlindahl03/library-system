package com.example.library_system.Controller.Loan;

import com.example.library_system.Entity.Loans;
import com.example.library_system.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loans loanBook(@RequestBody LoanRequest request) {
        return loanService.loanBook(request.getUserId(), request.getBookId());
    }

    public static class LoanRequest {
        private Long userId;
        private Long bookId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }
    }
}