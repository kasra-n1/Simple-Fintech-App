package com.snapp.pay.account.service.impl;

import com.snapp.pay.account.exception.InactiveAccountException;
import com.snapp.pay.account.exception.InsufficientBalanceException;
import com.snapp.pay.account.exception.TransactionFailedException;
import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.AccountStatus;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.TransactionRequest;
import com.snapp.pay.account.payload.request.WithdrawRequest;
import com.snapp.pay.account.service.AccountService;
import com.snapp.pay.account.service.TransactionCreationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class WithdrawCreationStrategy implements TransactionCreationStrategy {

    private final AccountService accountService;

    @Override
    public void validate(TransactionRequest request) {
        // checking source account balance and its activation status
        String accountNumber = ((WithdrawRequest) request).getAccountNumber();
        if (sourceAccount(request).getStatus().equals(AccountStatus.INACTIVE)) {
            throw new TransactionFailedException(new InactiveAccountException(accountNumber));
        }
        checkSourceAccountBalance(accountNumber, request.getAmount());
    }

    @Override
    public boolean supports(TransactionType type) {
        return type == TransactionType.WITHDRAW;
    }

    @Override
    public Account sourceAccount(TransactionRequest request) {
        return accountService.findByAccountNumber(((WithdrawRequest) request).getAccountNumber());
    }

    @Override
    public Account destinationAccount(TransactionRequest request) {
        return null;
    }

    private void checkSourceAccountBalance(String accountNumber, BigDecimal transactionAmount) {
        if (accountService.findByAccountNumber(accountNumber).getBalance().compareTo(transactionAmount) < 0) {
            throw new InsufficientBalanceException(accountNumber);
        }
    }
}
