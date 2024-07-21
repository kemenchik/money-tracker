package com.money.tracker.controller;

import com.money.tracker.dto.request.GetTransactionByIdRequest;
import com.money.tracker.mapper.TransactionMapper;
import com.money.tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetTransactionsByFilterController {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @PostMapping("/api/transaction/filter")
  public ResponseEntity<Void> gerTransactionByFilter(@RequestBody GetTransactionByIdRequest request) {

    // TODO

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
