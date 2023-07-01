package com.snapp.pay.account.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class TransactionFailedException extends TransactionException {

    @Serial
    private static final long serialVersionUID = 7605628122621816911L;

    public TransactionFailedException(String trackingCode, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, String.format("Transaction by tracking code %s failed because of non-existing or inActive account", trackingCode), cause);
    }

    public TransactionFailedException(Throwable cause) {
        super(HttpStatus.BAD_REQUEST, String.format("Transaction by tracking code %s failed because of non-existing or inActive account"), cause);
    }

}
