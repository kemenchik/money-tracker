package com.money.tracker.service.telegram.command;

import com.money.tracker.dto.session.SessionState;

public interface CommandCommand {

  SessionState produceNewSession();

  String getOperation();
}
