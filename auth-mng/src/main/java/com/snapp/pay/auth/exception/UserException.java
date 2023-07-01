package com.snapp.pay.auth.exception;

import com.snapp.pay.commons.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class UserException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 3506622166996266619L;

    public UserException(HttpStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }
}
