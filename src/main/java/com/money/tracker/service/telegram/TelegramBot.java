package com.money.tracker.service.telegram;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import com.money.tracker.config.TelegramProperties;
import com.money.tracker.constant.MessageConstants;
import com.money.tracker.context.ChatContext;
import com.money.tracker.service.telegram.action.SessionStateAction;
import com.money.tracker.service.telegram.command.CommandCommand;
import java.util.Set;
import java.util.function.BiConsumer;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends AbilityBot {

  private final ResponseHandler responseHandler;

  public TelegramBot(TelegramProperties properties,
      ChatContext chatContext,
      Set<CommandCommand> commandSet,
      Set<SessionStateAction> accountActionSet) {
    super(properties.getToken(), properties.getName());
    this.responseHandler = new ResponseHandler(silent, chatContext, commandSet, accountActionSet);
  }

  public Ability startBot() {
    return Ability
        .builder()
        .name("start")
        .info(MessageConstants.START_DESCRIPTION)
        .locality(USER)
        .privacy(PUBLIC)
        .action(ctx -> responseHandler.start(ctx.chatId()))
        .build();
  }

  public Reply replyToButtons() {
    BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> responseHandler.handle(getChatId(upd), upd.getMessage());
    return Reply.of(action, Flag.TEXT, (upd) -> true);
  }

  @Override
  public long creatorId() {
    return 1L;
  }
}