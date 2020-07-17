package com.htp.domain.processing;

import com.htp.domain.state.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface ProcessingRequest {
    List<SendMessage> processUsersInput(BotState currentState, Message inputMsg);
}