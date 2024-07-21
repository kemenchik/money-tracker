package com.money.tracker.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionCategory {

  TRANSPORT("Транспорт", false),
  HOME("Дом", false),
  PRODUCT("Продукты", false),
  RELAX("Отдых", false),
  CLOTHES("Одежда и аксессуары", false),
  HEALTH("Здоровье", false),
  GIFT("Подарки", false),
  TIME_SPENDING("Досуг", false),
  DUTY("Налоги", false),
  RESTAURANT("Рестораны", false),
  OTHER("Другое", false),

  INCOME_GIFT("Подарки", true),
  SALARY("Зарплата", true),
  DEPOSIT("Проценты по вкладу", true),
  INCOME_OTHER("Другое", true);


  private final String name;
  private final boolean income;
}
