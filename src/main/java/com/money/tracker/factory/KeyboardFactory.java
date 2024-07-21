package com.money.tracker.factory;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@UtilityClass
public class KeyboardFactory {
  // TODO add this

  public static ReplyKeyboard getMainKeyboard() {
    KeyboardRow row = new KeyboardRow();
    row.add("Создать новый счет");
    row.add("Создать новую транзакцию");
    return new ReplyKeyboardMarkup(List.of(row));
  }
}