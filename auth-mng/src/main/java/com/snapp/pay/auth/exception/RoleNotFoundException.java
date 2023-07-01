package com.snapp.pay.auth.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class RoleNotFoundException extends UserException {

    @Serial
    private static final long serialVersionUID = -7315903899328330964L;

    public RoleNotFoundException(String roleName) {
        super(HttpStatus.NOT_FOUND, String.format("Role with name '%s' not found", roleName), null);
    }

}
