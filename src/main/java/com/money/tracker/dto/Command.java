package com.money.tracker.dto;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Command {
  CREATE_ACCOUNT("/create-account", true, "title, currency, type, sum, comment"),
  CREATE_TRANSACTION("/create-transaction", true, ""),
  DELETE_TRANSACTION("/delete-transaction", true, ""),
  GET_ACCOUNT_ID("/get-account-id", true, ""),
  GET_TRANSACTION_ID("/get-transaction-id", true, ""),
  GET_TRANSACTION_FILTER("/get-transaction-filter", true, ""),
  UPDATE_ACCOUNT("/update-account", true, ""),
  GET_ALL_ACCOUNTS("/all-accounts", false, ""),
  GET_ALL_TRANSACTIONS("/all-transaction", false, ""),
  UPDATE_TRANSACTION("/update-transaction", true, "");

  private final String command;
  private final boolean needArgs;
  private final String parameterMessage;

  public static Command from(String value) {
    return Arrays.stream(Command.values())
        .filter(el -> el.getCommand().equals(value))
        .findAny()
        // TODO
        .orElseThrow(() -> new RuntimeException("Not found command with value: " + value));
  }
}
