package com.money.tracker.controller;

import com.money.tracker.dto.TransactionDto;
import com.money.tracker.dto.request.UpdateTransactionRequest;
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
public class UpdateTransactionController {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @PostMapping("/api/transaction/update")
  public ResponseEntity<TransactionDto> updateTransaction(@RequestBody UpdateTransactionRequest request) {
    var newTransaction = transactionMapper.requestToDto(request);

    return new ResponseEntity<>(transactionMapper.modelToDto(transactionService.update(request.getAccountId(), newTransaction)),
        HttpStatus.OK);
  }
}
