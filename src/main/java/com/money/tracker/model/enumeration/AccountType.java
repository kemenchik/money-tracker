package com.money.tracker.model.enumeration;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
  PHYSICAL("Физический"),
  VIRTUAL("Виртуальный");

  private final String value;

  public static AccountType from(String value) {
    return Arrays.stream(AccountType.values())
        .filter(el -> el.getValue().equals(value))
        .findAny()
        // TODO
        .orElseThrow(() -> new RuntimeException("Not found account type with value: " + value));
  }
}
