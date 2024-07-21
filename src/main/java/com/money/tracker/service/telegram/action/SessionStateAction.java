package com.money.tracker.service.telegram.action;

import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.telegram.abilitybots.api.sender.SilentSender;

public interface SessionStateAction {

  default Predicate<String> getValidator() {
    return StringUtils::isNotBlank;
  }

  void preExecute(SessionState sessionState, SilentSender sender, Long chatId);

  void execute(SessionState sessionState, String text, SilentSender sender, Long chatId);

  void postExecute(SessionState sessionState, SilentSender sender, Long chatId);

  UserState getType();
}
