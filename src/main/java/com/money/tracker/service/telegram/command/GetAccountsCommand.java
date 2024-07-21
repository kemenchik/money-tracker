package com.money.tracker.service.telegram.command;

import static com.money.tracker.dto.session.UserState.GET_ACCOUNTS_AWAITING_OWNER;

import com.money.tracker.dto.session.GetAccountsSessionState;
import com.money.tracker.dto.session.SessionState;
import org.springframework.stereotype.Component;

@Component
public class GetAccountsCommand implements CommandCommand {

  @Override
  public SessionState produceNewSession() {
    var session = new GetAccountsSessionState();
    session.setCurrentState(GET_ACCOUNTS_AWAITING_OWNER);

    return session;
  }

  @Override
  public String getOperation() {
    return "/getAccounts";
  }
}
