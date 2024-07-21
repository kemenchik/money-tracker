package com.money.tracker.service.telegram.action.get.account;

import static com.money.tracker.dto.session.UserState.GET_ACCOUNTS_AWAITING_OWNER;
import static com.money.tracker.service.util.TelegramUtils.sendMessage;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.money.tracker.dto.session.GetAccountsSessionState;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.service.AccountService;
import com.money.tracker.service.telegram.action.SessionStateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.sender.SilentSender;

@Component
@RequiredArgsConstructor
public class GetAccountsAwaitingOwnerAction implements SessionStateAction {

  private final AccountService accountService;

  @Override
  public void execute(SessionState sessionState, String text, SilentSender sender, Long chatId) {
    ((GetAccountsSessionState) sessionState).setOwner(text);
  }

  @Override
  public void preExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    sendMessage(sender, sessionState.getCurrentState().getWelcomeMessage(), chatId, null);
  }

  @Override
  public void postExecute(SessionState sessionState, SilentSender sender, Long chatId) {
    var accounts = accountService
        .getAccountsByOwner(((GetAccountsSessionState) sessionState).getOwner());

    if (isEmpty(accounts)) {
      sendMessage(sender, "Not found active accounts", chatId, null);
    } else {

      var sb = new StringBuilder();

      for (int i = 0; i < accounts.size(); i++) {
        sb.append(i + 1);
        sb.append(". ");
        sb.append(accounts.get(i).getTitle());
        sb.append("\n");
      }

      var accountsStr = sb.toString();

      sendMessage(sender, accountsStr, chatId, null);

      sessionState.setCurrentState(null);
    }
  }

  @Override
  public UserState getType() {
    return GET_ACCOUNTS_AWAITING_OWNER;
  }
}
