package com.snapp.pay.account.model;

import com.snapp.pay.commons.model.JpaBaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Transaction extends JpaBaseEntity {

    @Serial
    private static final long serialVersionUID = 4603808545751994047L;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String trackingCode;

    @NotNull
    private TransactionType type;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Account sourceAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Account destinationAccount;

    @NotNull
    private Long ownerId;

    @CreatedDate
    LocalDateTime transactionDate;

    public List<String> getAccountNumbers() {
        List<String> accountNumbers = new ArrayList<>();
        if (this.sourceAccount != null)
            accountNumbers.add(this.sourceAccount.getAccountNumber());
        if (this.destinationAccount != null)
            accountNumbers.add(this.destinationAccount.getAccountNumber());
        return accountNumbers;
    }
}
