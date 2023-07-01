package com.snapp.pay.account.exception;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends TransactionException {
    public InsufficientBalanceException(String accountNumber) {
        super(HttpStatus.BAD_REQUEST, String.format("Insufficient balance for transaction for account by number %s ", accountNumber), null);
    }

}
