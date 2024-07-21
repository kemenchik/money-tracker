package com.money.tracker.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountDto {

  private String id;

  private String title;
  private String owner;

  private Integer order;

  private String currency;
  private String type;

  private BigDecimal sum;

  private String comment;

  private List<TransactionDto> transactions;
}
