package com.snapp.pay.account.exception;

import com.snapp.pay.commons.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class AccountException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 4867022046086904929L;

    public AccountException(HttpStatus status, String message, Throwable cause) {
        super(status, message, cause);
    }

}
