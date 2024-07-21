package com.money.tracker.dto.session;

import lombok.Setter;

@Setter
public abstract class SessionState {

  protected UserState currentState;

  public abstract UserState getCurrentState();
}
