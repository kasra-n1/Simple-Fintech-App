package com.snapp.pay.auth.api;

import com.snapp.pay.auth.exception.UserException;
import com.snapp.pay.commons.payload.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice("com.snapp.pay.auth.api")
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("validation exception {}", ex);
        var errors = new HashMap<>();
        GeneralResponse generalResponse;
        for (var err : ex.getBindingResult().getAllErrors())
            errors.put(((FieldError) err).getField(), err.getDefaultMessage());
        if (errors.get("password") != null) {
            generalResponse = new GeneralResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Parameter", "The entered password doesn't meet the required complexity");
        } else {
            generalResponse = new GeneralResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Parameter", ex.getMessage());
        }
        return buildResponse(generalResponse);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("Bad Credentials Exception Exception", ex);
        return buildResponse(new GeneralResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), "Invalid username or password"));
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("Username Not Found Exception", ex);
        return buildResponse(new GeneralResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), "Invalid username or password"));
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<Object> handleUserNotFoundException(InternalAuthenticationServiceException ex) {
        log.error("Internal Authentication Service Exception", ex);
        return buildResponse(new GeneralResponse<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), "Invalid username or password"));
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<Object> handleUserException(UserException ex) {
        log.error("User Exception", ex);
        return buildResponse(new GeneralResponse(ex.getStatus().value(), "User Exception", ex.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> handleGeneralException(Throwable throwable) {
        log.error("Unknown Exception:", throwable);
        return buildResponse(new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown Error"));
    }

    private ResponseEntity<Object> buildResponse(GeneralResponse response) {
        return new ResponseEntity(response, HttpStatus.valueOf(response.getCode()));
    }

}
