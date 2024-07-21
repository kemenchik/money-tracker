package com.money.tracker.dto.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserState {

  // CREATE ACCOUNT ACTIONS
  AWAITING_OWNER("Enter the owner", false),
  AWAITING_TITLE("Enter the title", false),
  AWAITING_CURRENCY("Enter the currency", false),
  AWAITING_TYPE("Enter the type", false),
  AWAITING_SUM("Enter the sum", false),
  AWAITING_COMMENT("Enter the comment", false),
  CREATE_ACCOUNT_FINAL("Your account had been created", true),

  // GET ACCOUNTS ACTIONS
  GET_ACCOUNTS_AWAITING_OWNER("Enter the owner", true),
  ;

  private final String welcomeMessage;
  private final boolean isFinal;
}