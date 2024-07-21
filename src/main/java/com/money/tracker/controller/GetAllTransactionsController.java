package com.money.tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetAllTransactionsController {

  @PostMapping("/api/transaction/all")
  public ResponseEntity getAllTransactions() {

    // TODO
    return ResponseEntity.ok().build();
  }
}
