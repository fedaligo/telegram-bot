package com.htp.domain;

import com.htp.domain.processing.ProcessingRequest;
import com.htp.domain.state.BotState;
import com.htp.domain.state.StateInfoImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;

@Component
@AllArgsConstructor
public class FrontStep {
    private ProcessingRequest processingRequest;
    private StateInfoImpl userDataCache;

    public List<SendMessage> handleUpdate(Update update) {
        List<SendMessage> replyMessage = null;
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private List<SendMessage> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        List<SendMessage> replyMessage;
        switch (inputMsg.toLowerCase()) {
            case "/start":
                botState = BotState.ASK_CAPITAL;
                break;
            case "full list":
                botState = BotState.ASK_LIST;
                break;
            default:
                botState = BotState.WORD;
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = processingRequest.processUsersInput(botState, message);
        return replyMessage;
    }


}
