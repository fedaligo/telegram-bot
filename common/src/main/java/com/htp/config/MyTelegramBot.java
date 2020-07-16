package com.htp.config;

import com.htp.domain.FrontStep;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class MyTelegramBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private FrontStep frontStep;

    public MyTelegramBot(DefaultBotOptions options, FrontStep frontStep) {
        super(options);
        this.frontStep = frontStep;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        List<SendMessage> replyMessageToUser = frontStep.handleUpdate(update);
        return replyMessageToUser.get(0);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
