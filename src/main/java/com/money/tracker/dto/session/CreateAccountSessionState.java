package com.money.tracker.dto.session;

import com.money.tracker.model.enumeration.AccountCurrency;
import com.money.tracker.model.enumeration.AccountType;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreateAccountSessionState extends SessionState {

  private String owner;
  private String title;
  private AccountCurrency currency;
  private AccountType type;
  private BigDecimal sum;
  private String comment;

  @Override
  public UserState getCurrentState() {
    return currentState;
  }
}
