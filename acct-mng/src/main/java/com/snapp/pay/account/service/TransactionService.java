package com.snapp.pay.account.service;

import com.snapp.pay.account.model.Transaction;
import com.snapp.pay.account.payload.request.TransactionRequest;

public interface TransactionService {

    Transaction createTransaction(TransactionRequest transactionRequest);

}
