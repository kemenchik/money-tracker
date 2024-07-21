package com.money.tracker.service;

import static java.util.Objects.isNull;

import com.money.tracker.mapper.AccountMapper;
import com.money.tracker.model.Account;
import com.money.tracker.repo.AccountRepo;
import com.money.tracker.service.util.ExternalIdGenerator;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepo repository;
  private final AccountMapper mapper;

  public Account create(Account account) {
    String externalId = ExternalIdGenerator.generateAccountExternalId(account);
    return repository.save(account.setExternalId(externalId));
  }

  public List<Account> getAccountsByOwner(String owner) {
    return repository.getAccountsByOwner(owner);
  }

  public Account getByExternalId(String externalId) {
    var byExternalId = repository.getByExternalId(externalId);

    if (isNull(byExternalId)) {
      throw new EntityNotFoundException("Не найден account с данным externalId " + externalId);
    }

    return byExternalId;
  }

  public Account update(Account account) {
    var byExternalId = getByExternalId(account.getExternalId());

    mapper.update(account, byExternalId);

    return repository.save(byExternalId);
  }
}

