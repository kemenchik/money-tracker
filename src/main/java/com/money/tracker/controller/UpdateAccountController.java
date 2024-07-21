package com.money.tracker.controller;

import com.money.tracker.dto.AccountDto;
import com.money.tracker.dto.request.UpdateAccountRequest;
import com.money.tracker.mapper.AccountMapper;
import com.money.tracker.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UpdateAccountController {

  private final AccountService accountService;
  private final AccountMapper accountMapper;

  @PostMapping("/api/account/update")
  public ResponseEntity<AccountDto> updateAccount(@RequestBody UpdateAccountRequest request) {
    var newAccount = accountMapper.requestToModel(request);

    return new ResponseEntity<>(accountMapper.modelToDto(accountService.update(newAccount)), HttpStatus.OK);
  }
}
