package com.money.tracker.repo;

import com.money.tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

  Transaction getByAccountExternalIdAndExternalId(String accountId, String externalId);
}
