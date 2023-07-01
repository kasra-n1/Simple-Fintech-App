package com.snapp.pay.account.repository;

import com.snapp.pay.account.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Account> findAllByAccountNumberIn(List<String> accountNumbers);

}
