package com.money.tracker.dto.request;

import lombok.Data;

@Data
public class DeleteTransactionRequest {

  private String accountId;
  private String transactionId;
}
