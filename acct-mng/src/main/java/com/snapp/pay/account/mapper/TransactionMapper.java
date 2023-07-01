package com.snapp.pay.account.mapper;

import com.snapp.pay.account.model.Transaction;
import com.snapp.pay.account.model.TransactionType;
import com.snapp.pay.account.payload.request.DepositRequest;
import com.snapp.pay.account.payload.request.TransferRequest;
import com.snapp.pay.account.payload.request.WithdrawRequest;
import com.snapp.pay.account.payload.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = TransactionType.class)
public interface TransactionMapper {

    @Mapping(target = "sourceAccount", ignore = true)
    @Mapping(target = "destinationAccount", ignore = true)
    @Mapping(target = "type", expression = "java(TransactionType.DEPOSIT)")
    Transaction toTransaction(DepositRequest request);

    @Mapping(target = "sourceAccount", ignore = true)
    @Mapping(target = "destinationAccount", ignore = true)
    @Mapping(target = "type", expression = "java(TransactionType.TRANSFER)")
    Transaction toTransaction(TransferRequest request);

    @Mapping(target = "sourceAccount", ignore = true)
    @Mapping(target = "destinationAccount", ignore = true)
    @Mapping(target = "type", expression = "java(TransactionType.WITHDRAW)")
    Transaction toTransaction(WithdrawRequest request);

    TransactionResponse toTransactionResponse(Transaction transaction);

}
