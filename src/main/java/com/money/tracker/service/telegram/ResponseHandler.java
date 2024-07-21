package com.money.tracker.service.telegram;

import static com.money.tracker.constant.MessageConstants.START_TEXT;
import static com.money.tracker.service.util.TelegramUtils.isCommand;
import static com.money.tracker.service.util.TelegramUtils.sendMessage;
import static java.util.Objects.nonNull;

import com.money.tracker.context.ChatContext;
import com.money.tracker.dto.session.SessionState;
import com.money.tracker.dto.session.UserState;
import com.money.tracker.service.telegram.action.SessionStateAction;
import com.money.tracker.service.telegram.command.CommandCommand;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ResponseHandler {

  private final SilentSender sender;
  private final ChatContext chatContext;
  private final Map<String, CommandCommand> commandToHandlerMap;
  private final Map<UserState, SessionStateAction> stateToActionMap;

  public ResponseHandler(
      SilentSender silentSender,
      ChatContext chatContext,
      Set<CommandCommand> commandCommands,
      Set<SessionStateAction> accountActionSet
  ) {
    this.sender = silentSender;
    this.chatContext = chatContext;
    this.stateToActionMap = accountActionSet.stream().collect(Collectors.toMap(SessionStateAction::getType, Function.identity()));
    this.commandToHandlerMap = commandCommands.stream().collect(Collectors.toMap(CommandCommand::getOperation, Function.identity()));
  }

  public void start(long chatId) {
    SendMessage message = new SendMessage();

    //TODO добавить клавиатуру
    message.setChatId(chatId);
    message.setText(START_TEXT);

    sender.execute(message);
  }

  public void handle(Long chatId, Message message) {
    var text = message.getText().trim();

    if (!chatContext.isActiveChatId(chatId) || isCommand(text)) {

      var handler = commandToHandlerMap.get(text);

      if (nonNull(handler)) {
        chatContext.clearChatId(chatId);
        applyAction(chatId, handler.produceNewSession(), text);
      } else {
        sendMessage(sender, "New operation not found", chatId, null);
      }

      return;
    }

    applyAction(chatId, chatContext.getByChatId(chatId), text);
  }

  private void applyAction(Long chatId, SessionState session, String text) {
    var state = session.getCurrentState();
    var action = stateToActionMap.get(state);

    if (chatContext.isActiveChatId(chatId)) {

      var valid = action.getValidator().test(text);
      var isFinal = state.isFinal();

      if (!valid) {
        unexpectedMessage(chatId);
        return;
      }

      action.execute(session, text, sender, chatId);
      action.postExecute(session, sender, chatId);

      if (!isFinal) {
        var nextState = session.getCurrentState();
        var nextAction = stateToActionMap.get(nextState);

        nextAction.preExecute(session, sender, chatId);
      } else {
        chatContext.clearChatId(chatId);
      }

      return;
    }

    action.preExecute(session, sender, chatId);
    chatContext.addChatId(chatId, session);
  }

  private void unexpectedMessage(long chatId) {
    sendMessage(sender, "I did not expect that", chatId, null);
  }
}