package com.snapp.pay.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public abstract class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3667552430734450852L;
    private HttpStatus status;

    public BusinessException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
