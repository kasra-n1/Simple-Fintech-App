package com.snapp.pay.account.exception;

import com.snapp.pay.commons.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class TransactionException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 7605628122621816911L;

    public TransactionException(HttpStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }

}
