package com.money.tracker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.telegram")
public class TelegramProperties {

  private String name;
  private String token;
}
