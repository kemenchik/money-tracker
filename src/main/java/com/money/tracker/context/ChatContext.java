package com.money.tracker.context;

import com.money.tracker.dto.session.SessionState;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class ChatContext {

  private final Map<Long, SessionState> chatIdToStateMap;

  public ChatContext() {
    this.chatIdToStateMap = new ConcurrentHashMap<>();
  }

  public boolean isActiveChatId(Long chatId) {
    return chatIdToStateMap.containsKey(chatId);
  }

  public void clearChatId(Long chatId) {
    chatIdToStateMap.remove(chatId);
  }

  public void addChatId(Long chatId, SessionState session) {
    chatIdToStateMap.put(chatId, session);
  }

  public SessionState getByChatId(Long chatId) {
    return chatIdToStateMap.get(chatId);
  }
}

