package com.money.tracker.controller;

import com.money.tracker.dto.AccountDto;
import com.money.tracker.dto.request.DeleteTransactionRequest;
import com.money.tracker.mapper.AccountMapper;
import com.money.tracker.service.ManipulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteTransactionController {

  private final ManipulationService manipulationService;
  private final AccountMapper accountMapper;

  @PostMapping("/api/transaction/delete")
  public ResponseEntity<AccountDto> deleteTransaction(@RequestBody DeleteTransactionRequest request) {
    var accountModel = manipulationService.deleteTransaction(request.getAccountId(), request.getTransactionId());
    return new ResponseEntity<>(accountMapper.modelToDto(accountModel), HttpStatus.OK);
  }
}
