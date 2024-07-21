package com.money.tracker.service;

import com.money.tracker.model.Account;
import com.money.tracker.model.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManipulationService {

  private final AccountService accountService;
  private final TransactionService transactionService;

  @Transactional
  public Account createTransaction(String accountId, Transaction transaction) {

    var account = accountService.getByExternalId(accountId);

    var income = transaction.getCategory().isIncome();
    var transactionSum = transaction.getSum();

    var currentAccountSum = account.getSum();
    var newAccountSum = income ? currentAccountSum.add(transactionSum) : currentAccountSum.subtract(transactionSum);

    transaction.setAccount(account);
    account.setSum(newAccountSum);

    transactionService.create(transaction);

    return accountService.update(account);
  }

  @Transactional
  public Account deleteTransaction(String accountId, String transactionId) {

    var account = accountService.getByExternalId(accountId);
    var transaction = transactionService.getByExternalId(accountId, transactionId);

    var income = transaction.getCategory().isIncome();
    var transactionSum = transaction.getSum();

    var currentAccountSum = account.getSum();
    var newAccountSum = income ? currentAccountSum.subtract(transactionSum) : currentAccountSum.add(transactionSum);

    transaction.setAccount(account);
    account.setSum(newAccountSum);

    transactionService.delete(accountId, transactionId);

    return accountService.update(account);
  }
}
