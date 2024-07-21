package com.money.tracker.dto.request;

import lombok.Data;

@Data
public class GetTransactionByIdRequest {

  private String accountId;
  private String transactionId;
}
