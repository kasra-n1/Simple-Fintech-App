package com.snapp.pay.account.payload.request;

import com.snapp.pay.account.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String trackingCode;

    private TransactionType type;

    private BigDecimal amount;

}
