package com.snapp.pay.account.api;

import com.snapp.pay.account.exception.AccountNotFoundException;
import com.snapp.pay.account.exception.TransactionFailedException;
import com.snapp.pay.commons.payload.response.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
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
public class AccountMngExceptionHandler extends ResponseEntityExceptionHandler {

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("validation exception {}", ex);
        var errors = new HashMap<>();
        GeneralResponse generalResponse;
        for (var err : ex.getBindingResult().getAllErrors()) {
            errors.put(((FieldError) err).getField(), err.getDefaultMessage());
        }
        generalResponse = new GeneralResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Parameter", ex.getMessage());
        return buildResponse(generalResponse);
    }

    @ExceptionHandler({TransactionFailedException.class})
    public ResponseEntity<Object> handleTransactionFailedException(TransactionFailedException ex) {
        log.error("Transaction Failed Exception", ex);
        return buildResponse(new GeneralResponse<>(HttpStatus.UNAUTHORIZED.value(), "Transaction failed", ex.getMessage()));
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<Object> handleTransactionFailedException(AccountNotFoundException ex) {
        log.error("Account Not FoundException", ex);
        return buildResponse(new GeneralResponse<>(HttpStatus.UNAUTHORIZED.value(), "Account not found", ex.getMessage()));
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
