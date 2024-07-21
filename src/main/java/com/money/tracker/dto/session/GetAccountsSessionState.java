package com.money.tracker.dto.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GetAccountsSessionState extends SessionState {

  private String owner;

  @Override
  public UserState getCurrentState() {
    return currentState;
  }
}
