package com.htp.config;

import com.htp.config.BotConfig;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class MyTelegramBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public MyTelegramBot(DefaultBotOptions options) {
        super(options);
    }

    //метод который будет обрабатывать полученные апдейты
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if(update.getMessage() != null && update.getMessage().hasText()){
            long chat_id = update.getMessage().getChatId();
            SendMessage sm = new SendMessage(chat_id, "Hi " + update.getMessage().getText());
            return sm;
        }
        return null;
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
