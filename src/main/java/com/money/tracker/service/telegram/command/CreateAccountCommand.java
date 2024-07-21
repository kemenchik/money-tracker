package com.money.tracker.service.telegram.command;

import static com.money.tracker.dto.session.UserState.AWAITING_OWNER;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.dto.session.SessionState;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountCommand implements CommandCommand {

  @Override
  public SessionState produceNewSession() {
    var session = new CreateAccountSessionState();
    session.setCurrentState(AWAITING_OWNER);

    return session;
  }

  @Override
  public String getOperation() {
    return "/createAccount";
  }
}
