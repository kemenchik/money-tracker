package com.money.tracker.service.util;

import lombok.experimental.UtilityClass;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@UtilityClass
public class TelegramUtils {

  public static void sendMessage(SilentSender sender, String message, Long chatId, ReplyKeyboard keyboard) {
    SendMessage sendMessage = new SendMessage();

    sendMessage.setChatId(chatId);
    sendMessage.setText(message);
    sendMessage.setReplyMarkup(keyboard);

    sender.execute(sendMessage);
  }

  public static boolean isCommand(String text) {
    return text.matches("\\s?/[a-zA-Z0-9]*");
  }
}
