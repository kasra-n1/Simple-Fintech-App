package com.snapp.pay.account.service;

import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.Transaction;

public interface AccountService {

    Account findByAccountNumber(String accountNumber);

    void updateBalance(Transaction transaction);


}
