package com.money.tracker.service.telegram.action.create.account;

import static com.money.tracker.service.util.TelegramUtils.sendMessage;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.mapper.RequestStateMapper;
import com.money.tracker.service.AccountService;
import com.money.tracker.service.telegram.action.SessionStateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.sender.SilentSender;

@Component
@RequiredArgsConstructor
public class CreateAccountFinalAction implements SessionStateAction {

  private final AccountService accountService;
  private final RequestStateMapper mapper;

  @Override
  public void execute(SessionState sessionState, String text, SilentSender sender, Long chatId) {
    accountService.create(mapper.stateToDto(((CreateAccountSessionState) sessionState)));
  }

  @Override
  public void postExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, "Account had been created", chatId, null);
    sessionState.setCurrentState(null);
  }


  @Override
  public void preExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, sessionState.getCurrentState().getWelcomeMessage(), chatId, null);
  }

  @Override
  public UserState getType() {
    return UserState.CREATE_ACCOUNT_FINAL;
  }
}

