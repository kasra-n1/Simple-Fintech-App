package com.snapp.pay.account.model;

import com.snapp.pay.commons.model.JpaBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@NoArgsConstructor
public class Account extends JpaBaseEntity {

    @Serial
    private static final long serialVersionUID = 4963275522483233876L;

    @NotNull
    private BigDecimal balance;

    @NotNull
    @Column(unique = true, updatable = false)
    private String accountNumber;

    @NotNull
    @Column(unique = true, nullable = false)
    private AccountStatus status;

    @NotNull
    @Column(nullable = false, updatable = false)
    private Long userId;

}
