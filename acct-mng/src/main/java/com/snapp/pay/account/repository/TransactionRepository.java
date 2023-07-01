package com.snapp.pay.account.repository;

import com.snapp.pay.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByTrackingCode(String trackingCode);

}
