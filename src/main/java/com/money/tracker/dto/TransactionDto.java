package com.money.tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionDto {

  private String id;

  private BigDecimal sum;
  private LocalDateTime date;

  private String category;

  private String comment;

  private AccountDto account;
}
