package com.snapp.pay.account.service.impl;

import com.snapp.pay.account.exception.AccountNotFoundException;
import com.snapp.pay.account.model.Account;
import com.snapp.pay.account.model.Transaction;
import com.snapp.pay.account.repository.AccountRepository;
import com.snapp.pay.account.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    @Override
    @Transactional
    public void updateBalance(Transaction transaction) {
        // first we lock the accounts in order to provide isolation
        Map<String, Account> accountMap = findAndLockAccounts(transaction.getAccountNumbers());
        if (transaction.getSourceAccount() != null) {
            accountMap.get(transaction.getSourceAccount().getAccountNumber()).setBalance(
                    accountMap.get(transaction.getSourceAccount().getAccountNumber()).getBalance().subtract(transaction.getAmount()));
        }
        if (transaction.getDestinationAccount() != null) {
            accountMap.get(transaction.getSourceAccount().getAccountNumber()).setBalance(
                    accountMap.get(transaction.getDestinationAccount().getAccountNumber()).getBalance().add(transaction.getAmount()));
        }
        accountRepository.saveAll(accountMap.values().stream().collect(Collectors.toList()));
    }

    private Map<String, Account> findAndLockAccounts(List<String> accountNumbers) {
        List<Account> accounts = accountRepository.findAllByAccountNumberIn(accountNumbers);
        log.info("find and lock accounts {} ", accountNumbers);
        return accounts.stream().collect(Collectors.toMap(Account::getAccountNumber, Function.identity()));
    }

}
