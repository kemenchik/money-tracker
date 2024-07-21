package com.money.tracker.controller;

import com.money.tracker.dto.AccountDto;
import com.money.tracker.dto.request.CreateAccountRequest;
import com.money.tracker.mapper.AccountMapper;
import com.money.tracker.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CreateAccountController {

  private final AccountService accountService;
  private final AccountMapper accountMapper;

  @PostMapping("/api/account/create")
  public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request) {
    var newAccount = accountMapper.requestToModel(request);
    var accountModel = accountService.create(newAccount);
    return new ResponseEntity<>(accountMapper.modelToDto(accountModel), HttpStatus.OK);
  }
}
