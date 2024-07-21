package com.money.tracker.repo;

import com.money.tracker.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

  Account getByExternalId(String externalId);

  List<Account> getAccountsByOwner(String owner);
}
