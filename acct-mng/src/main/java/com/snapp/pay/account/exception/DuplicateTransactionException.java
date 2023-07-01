package com.snapp.pay.account.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateTransactionException extends TransactionException {

    @Serial
    private static final long serialVersionUID = -4079408490065079909L;

    public DuplicateTransactionException(String trackingCode) {
        super(HttpStatus.BAD_REQUEST, "Duplicate Transaction by tracking code: " + trackingCode, null);
    }

}
