package com.example.library_system.Exception;

public class LoanNotFoundException extends LibrarySystemException {
  public LoanNotFoundException(String message) {
    super(message);
  }

  public LoanNotFoundException(Long loanId) {
    super("Loan with ID " + loanId + " not found");
  }
}