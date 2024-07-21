package com.money.tracker.model.enumeration;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountCurrency {
  RUB("Рубль"),
  USD("Доллар"),
  EUR("Евро");

  private final String value;

  public static AccountCurrency from(String value) {
    return Arrays.stream(AccountCurrency.values())
        .filter(el -> el.getValue().equals(value))
        .findAny()
        // TODO
        .orElseThrow(() -> new RuntimeException("Not found currency with value: " + value));
  }
}