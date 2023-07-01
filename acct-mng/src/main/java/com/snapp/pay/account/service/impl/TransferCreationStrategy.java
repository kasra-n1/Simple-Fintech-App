package com.snapp.pay.account.service.impl;

import com.snapp.pay.account.exception.InactiveAccountException;
import com.snapp.pay.account.exception.InsufficientBalanceException;
import com.snapp.pay.account.exception.TransactionFailedException;
import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.AccountStatus;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.TransactionRequest;
import com.snapp.pay.account.payload.request.TransferRequest;
import com.snapp.pay.account.service.AccountService;
import com.snapp.pay.account.service.TransactionCreationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TransferCreationStrategy implements TransactionCreationStrategy {

    private final AccountService accountService;

    @Override
    public void validate(TransactionRequest request) {
        // checking source account balance. also source and destination account activation status
        String accountNumber = ((TransferRequest) request).getSourceAccountNumber();
        if (sourceAccount(request).getStatus().equals(AccountStatus.INACTIVE) ||
                destinationAccount(request).getStatus().equals(AccountStatus.INACTIVE)) {
            throw new TransactionFailedException(new InactiveAccountException(accountNumber));
        }
        checkSourceAccountBalance(accountNumber, request.getAmount());
    }

    @Override
    public boolean supports(TransactionType type) {
        return type == TransactionType.TRANSFER;
    }

    @Override
    public Account sourceAccount(TransactionRequest request) {
        return accountService.findByAccountNumber(((TransferRequest) request).getSourceAccountNumber());
    }

    @Override
    public Account destinationAccount(TransactionRequest request) {
        return accountService.findByAccountNumber(((TransferRequest) request).getDestinationAccountNumber());
    }

    private void checkSourceAccountBalance(String accountNumber, BigDecimal transactionAmount) {
        if (accountService.findByAccountNumber(accountNumber).getBalance().compareTo(transactionAmount) < 0) {
            throw new InsufficientBalanceException(accountNumber);
        }
    }
}
