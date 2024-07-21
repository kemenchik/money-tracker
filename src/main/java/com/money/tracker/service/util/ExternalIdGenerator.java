package com.money.tracker.service.util;

import com.money.tracker.model.Account;
import com.money.tracker.model.Transaction;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ExternalIdGenerator {

  public static String generateAccountExternalId(Account account) {

    return account.getOwner().replaceAll(" ", "_")
        + "_"
        + account.getTitle();
  }

  public static String generateTransactionExternalId(Transaction transaction) {

    return transaction.getDate()
        + "_"
        + transaction.getCategory().getName()
        + "_"
        + transaction.getCategory().isIncome();
  }
}
