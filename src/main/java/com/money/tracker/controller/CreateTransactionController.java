package com.money.tracker.controller;

import com.money.tracker.dto.AccountDto;
import com.money.tracker.dto.request.CreateTransactionRequest;
import com.money.tracker.mapper.AccountMapper;
import com.money.tracker.mapper.TransactionMapper;
import com.money.tracker.service.ManipulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateTransactionController {

  private final ManipulationService manipulationService;
  private final AccountMapper accountMapper;
  private final TransactionMapper transactionMapper;

  @PostMapping("/api/transaction/create")
  public ResponseEntity<AccountDto> createTransaction(@RequestBody CreateTransactionRequest request) {
    var newTransaction = transactionMapper.requestToDto(request);
    var accountModel = manipulationService.createTransaction(request.getAccountId(), newTransaction);
    return new ResponseEntity<>(accountMapper.modelToDto(accountModel), HttpStatus.OK);
  }
}
