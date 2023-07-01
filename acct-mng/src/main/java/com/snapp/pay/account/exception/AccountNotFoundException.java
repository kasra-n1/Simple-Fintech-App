package com.snapp.pay.account.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class AccountNotFoundException extends AccountException {

    @Serial
    private static final long serialVersionUID = 3817558945657179904L;

    public AccountNotFoundException(String accountNumber) {
        super(HttpStatus.NOT_FOUND, String.format("Account by number %s not found!", accountNumber), null);
    }
}
