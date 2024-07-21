package com.money.tracker.service.telegram.action.create.account;

import static com.money.tracker.dto.session.UserState.AWAITING_CURRENCY;
import static com.money.tracker.dto.session.UserState.AWAITING_TITLE;
import static com.money.tracker.service.util.TelegramUtils.sendMessage;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.service.telegram.action.SessionStateAction;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.sender.SilentSender;

@Component
public class CreateAccountAwaitingTitleAction implements SessionStateAction {

  @Override
  public void execute(SessionState sessionState, String text, SilentSender sender, Long chatId) {
    ((CreateAccountSessionState) sessionState).setTitle(text);
  }

  @Override
  public void preExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, sessionState.getCurrentState().getWelcomeMessage(), chatId, null);
  }

  @Override
  public void postExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sessionState.setCurrentState(AWAITING_CURRENCY);
  }

  @Override
  public UserState getType() {
    return AWAITING_TITLE;
  }
}
