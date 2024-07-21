package com.money.tracker.service;

import static java.util.Objects.isNull;

import com.money.tracker.mapper.TransactionMapper;
import com.money.tracker.model.Transaction;
import com.money.tracker.repo.TransactionRepo;
import com.money.tracker.service.util.ExternalIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepo repository;
  private final TransactionMapper mapper;

  public Transaction create(Transaction transaction) {
    String externalId = ExternalIdGenerator.generateTransactionExternalId(transaction);
    return repository.save(transaction.setExternalId(externalId));
  }

  public Transaction getByExternalId(String accountId, String externalId) {
    var byExternalId = repository.getByAccountExternalIdAndExternalId(accountId, externalId);

    if (isNull(byExternalId)) {
      throw new EntityNotFoundException("Не найден account с данным externalId: " + externalId);
    }

    return byExternalId;
  }

  public void delete(String accountId, String externalId) {
    var byExternalId = repository.getByAccountExternalIdAndExternalId(accountId, externalId);

    if (isNull(byExternalId)) {
      throw new EntityNotFoundException("Не найден account с данным externalId: " + externalId);
    }

    repository.delete(byExternalId);
  }

  public Transaction update(String accountId, Transaction transaction) {
    var byExternalId = getByExternalId(accountId, transaction.getExternalId());

    mapper.update(transaction, byExternalId);

    return repository.save(byExternalId);
  }
}
