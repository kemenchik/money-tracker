package com.money.tracker.controller;

import com.money.tracker.dto.TransactionDto;
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
public class GetTransactionByIdController {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  @PostMapping("/api/transaction/get")
  public ResponseEntity<TransactionDto> getTransactionByExternalId(@RequestBody GetTransactionByIdRequest request) {

    var transactionModel = transactionService.getByExternalId(request.getAccountId(), request.getTransactionId());
    var transactionDto = transactionMapper.modelToDto(transactionModel);

    return new ResponseEntity<>(transactionDto, HttpStatus.OK);
  }
}
