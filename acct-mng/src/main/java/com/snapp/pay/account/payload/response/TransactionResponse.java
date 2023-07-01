package com.snapp.pay.account.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private String trackingCode;

    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

}
