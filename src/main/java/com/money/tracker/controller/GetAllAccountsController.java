package com.money.tracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetAllAccountsController {

  @PostMapping("/api/account/all")
  public ResponseEntity getAllAccount() {

    // TODO
    return ResponseEntity.ok().build();
  }
}
