package com.snapp.pay.auth.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class UserNotFoundException extends UserException {

    @Serial
    private static final long serialVersionUID = -2194777407633013149L;

    public UserNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, String.format("User with username '%s' not found", username), null);
    }
}
