package com.snapp.pay.auth.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class DuplicateUsernameException extends UserException {

    @Serial
    private static final long serialVersionUID = 7529155385670989280L;

    public DuplicateUsernameException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Username is already taken!", null);
    }
}
