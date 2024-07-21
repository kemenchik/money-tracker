package com.money.tracker;

import com.money.tracker.config.TelegramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties({TelegramProperties.class})
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(ctx.getBean("telegramBot", AbilityBot.class));
    } catch (TelegramApiException e) {
      throw new RuntimeException(e);
    }
  }
}
