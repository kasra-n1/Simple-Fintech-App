package com.snapp.pay.account.service.impl;

import com.snapp.pay.account.exception.InactiveAccountException;
import com.snapp.pay.account.exception.TransactionFailedException;
import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.AccountStatus;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.DepositRequest;
import com.snapp.pay.account.payload.request.TransactionRequest;
import com.snapp.pay.account.service.AccountService;
import com.snapp.pay.account.service.TransactionCreationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepositCreationStrategy implements TransactionCreationStrategy {

    private final AccountService accountService;

    @Override
    public void validate(TransactionRequest request) {
        // only checking destination account activation status
        String accountNumber = ((DepositRequest) request).getAccountNumber();
        if (destinationAccount(request).getStatus().equals(AccountStatus.INACTIVE)) {
            throw new TransactionFailedException(new InactiveAccountException(accountNumber));
        }
    }

    @Override
    public boolean supports(TransactionType type) {
        return type == TransactionType.DEPOSIT;
    }

    @Override
    public Account sourceAccount(TransactionRequest request) {
        return null;
    }

    @Override
    public Account destinationAccount(TransactionRequest request) {
        return accountService.findByAccountNumber(((DepositRequest) request).getAccountNumber());
    }
}
