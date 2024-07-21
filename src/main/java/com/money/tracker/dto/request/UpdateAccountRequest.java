package com.money.tracker.dto.request;

import com.money.tracker.model.enumeration.AccountCurrency;
import com.money.tracker.model.enumeration.AccountType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateAccountRequest {

  private String accountId;
  private String owner;
  private String title;
  private int order;
  private AccountCurrency currency;
  private AccountType type;
  private BigDecimal sum;
  private String comment;
}
