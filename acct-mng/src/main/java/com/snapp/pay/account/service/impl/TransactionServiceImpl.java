package com.snapp.pay.account.service.impl;

import com.snapp.pay.account.exception.TransactionFailedException;
import com.snapp.pay.account.mapper.TransactionMapper;
import com.snapp.pay.account.model.Transaction;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.DepositRequest;
import com.snapp.pay.account.payload.request.TransactionRequest;
import com.snapp.pay.account.payload.request.TransferRequest;
import com.snapp.pay.account.payload.request.WithdrawRequest;
import com.snapp.pay.account.repository.TransactionRepository;
import com.snapp.pay.account.service.AccountService;
import com.snapp.pay.account.service.TransactionCreationStrategy;
import com.snapp.pay.account.service.TransactionService;
import com.snapp.pay.account.util.CodeGeneratorUtil;
import com.snapp.pay.commons.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final List<TransactionCreationStrategy> strategies;

    private final TransactionMapper dataMapper;

    private final AccountService accountService;

    @Override
    public Transaction createTransaction(TransactionRequest request) {
        TransactionCreationStrategy strategy = getTransactionStrategy(request.getType());
        log.info("found compatible strategy for {} transaction ", request.getType().name());

        return createNewTransaction(request, strategy);
    }

    private Transaction createNewTransaction(TransactionRequest request, TransactionCreationStrategy strategy) {

        Transaction transaction = switch (request.getType()) {
            case DEPOSIT -> dataMapper.toTransaction((DepositRequest) request);
            case WITHDRAW -> dataMapper.toTransaction((WithdrawRequest) request);
            case TRANSFER -> dataMapper.toTransaction((TransferRequest) request);
        };

        transaction.setTrackingCode(CodeGeneratorUtil.generateTrackingCode());

        try {
            transaction.setSourceAccount(strategy.sourceAccount(request));
            transaction.setDestinationAccount(strategy.destinationAccount(request));
        } catch (BusinessException ex) {
            log.error("failed to create new transaction " + ex.getMessage());
            throw new TransactionFailedException(transaction.getTrackingCode(), ex);
        }

        accountService.updateBalance(transaction);
        return transactionRepository.save(transaction);
    }


    private TransactionCreationStrategy getTransactionStrategy(TransactionType type) {
        return strategies.stream().filter(st -> st.supports(type)).findAny()
                .orElseThrow(() -> new IllegalStateException("No transaction creation strategy found for type " + type));
    }

}
