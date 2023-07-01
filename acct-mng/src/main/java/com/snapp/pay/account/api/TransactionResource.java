package com.snapp.pay.account.api;

import com.snapp.pay.account.mapper.TransactionMapper;
import com.snapp.pay.account.payload.request.DepositRequest;
import com.snapp.pay.account.payload.request.TransferRequest;
import com.snapp.pay.account.payload.request.WithdrawRequest;
import com.snapp.pay.account.payload.response.TransactionResponse;
import com.snapp.pay.account.service.TransactionService;
import com.snapp.pay.commons.payload.response.GeneralResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {

    private final TransactionService transactionService;

    private final TransactionMapper dataMapper;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping(path = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<TransactionResponse> deposit(@Valid @RequestBody DepositRequest request) {
        log.info("Deposit transaction api invocation => {}", request);
        return new GeneralResponse(dataMapper.toTransactionResponse(transactionService.createTransaction(request)));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<TransactionResponse> transfer(@Valid @RequestBody TransferRequest request) {
        log.info("Transfer transaction api invocation => {}", request);
        return new GeneralResponse(dataMapper.toTransactionResponse(transactionService.createTransaction(request)));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping(path = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<TransactionResponse> withdraw(@Valid @RequestBody WithdrawRequest request) {
        log.info("Withdraw transaction api invocation => {}", request);
        return new GeneralResponse(dataMapper.toTransactionResponse(transactionService.createTransaction(request)));
    }

}
