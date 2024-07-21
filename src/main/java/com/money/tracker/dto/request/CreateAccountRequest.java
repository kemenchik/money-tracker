package com.money.tracker.dto.request;

import com.money.tracker.model.enumeration.AccountCurrency;
import com.money.tracker.model.enumeration.AccountType;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateAccountRequest {

  private String owner;
  private String title;
  private AccountCurrency currency;
  private AccountType type;
  private BigDecimal sum;
  private Integer order;
  private String comment;
}
