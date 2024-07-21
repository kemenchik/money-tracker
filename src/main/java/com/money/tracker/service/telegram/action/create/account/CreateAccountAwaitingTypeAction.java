package com.money.tracker.service.telegram.action.create.account;

import static com.money.tracker.dto.session.UserState.AWAITING_COMMENT;
import static com.money.tracker.dto.session.UserState.AWAITING_TYPE;
import static com.money.tracker.service.util.TelegramUtils.sendMessage;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.model.enumeration.AccountType;
import com.money.tracker.service.telegram.action.SessionStateAction;
import java.util.Arrays;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.sender.SilentSender;

@Component
public class CreateAccountAwaitingTypeAction implements SessionStateAction {

  @Override
  public void execute(SessionState sessionState, String text, SilentSender sender, Long chatId) {
    ((CreateAccountSessionState) sessionState).setType(AccountType.from(text));
  }

  @Override
  public Predicate<String> getValidator() {
    return text -> Arrays.stream(AccountType.values())
        .anyMatch(el -> el.getValue().equals(text));
  }

  @Override
  public void preExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, sessionState.getCurrentState().getWelcomeMessage(), chatId, null);
  }

  @Override
  public void postExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sessionState.setCurrentState(AWAITING_COMMENT);
  }

  @Override
  public UserState getType() {
    return AWAITING_TYPE;
  }
}
