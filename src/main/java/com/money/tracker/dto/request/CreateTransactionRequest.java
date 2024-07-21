package com.money.tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.money.tracker.model.enumeration.TransactionCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreateTransactionRequest {

  private String accountId;
  private String owner;
  private BigDecimal sum;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private TransactionCategory category;
  private String comment;
}
