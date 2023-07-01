package com.snapp.pay.account.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class InactiveAccountException extends AccountException {
    @Serial
    private static final long serialVersionUID = 8106638827287572317L;

    public InactiveAccountException(String accountNumber) {
        super(HttpStatus.NOT_FOUND, String.format("Account by number %s in INACTIVE", accountNumber), null);
    }

}
