package com.snapp.pay.account.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class DepositRequest extends TransactionRequest {

    private String accountNumber;

}
