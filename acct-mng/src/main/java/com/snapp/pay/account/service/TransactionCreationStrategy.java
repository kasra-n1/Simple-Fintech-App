package com.snapp.pay.account.service;

import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.TransactionRequest;

public interface TransactionCreationStrategy {

    void validate(TransactionRequest request);

    boolean supports(TransactionType type);

    Account sourceAccount(TransactionRequest request);

    Account destinationAccount(TransactionRequest request);

}
