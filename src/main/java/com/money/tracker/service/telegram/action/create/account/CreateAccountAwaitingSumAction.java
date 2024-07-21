package com.money.tracker.service.telegram.action.create.account;

import static com.money.tracker.dto.session.UserState.AWAITING_SUM;
import static com.money.tracker.dto.session.UserState.AWAITING_TITLE;
import static com.money.tracker.service.util.TelegramUtils.sendMessage;

import com.money.tracker.dto.session.CreateAccountSessionState;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.service.telegram.action.SessionStateAction;
import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.sender.SilentSender;

@Component
public class CreateAccountAwaitingSumAction implements SessionStateAction {

  Pattern regex = Pattern.compile("^[\\d|\\s]*[.]?[\\d|\\s]*$");

  @Override
  public void execute(SessionState sessionState, String text, SilentSender sender, Long chatId) {
    ((CreateAccountSessionState) sessionState).setSum(BigDecimal.valueOf(Double.parseDouble(text)));
  }

  @Override
  public Predicate<String> getValidator() {
    return str -> regex.matcher(str).matches();
  }


  @Override
  public void preExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, sessionState.getCurrentState().getWelcomeMessage(), chatId, null);
  }

  @Override
  public void postExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sessionState.setCurrentState(AWAITING_TITLE);
  }

  @Override
  public UserState getType() {
    return AWAITING_SUM;
  }
}
