package com.htp.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String webHookPath; //из application.properties
    private String botUserName;//из application.properties
    private String botToken;//из application.properties
    private DefaultBotOptions.ProxyType proxyType;//из application.properties
    private String proxyHost;//из application.properties
    private int proxyPort;//из application.properties

    @Bean
    public MyTelegramBot MyTelegramBot (){
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);

        /*options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);*/

        MyTelegramBot myTelegramBot = new MyTelegramBot(options);
        myTelegramBot.setBotUserName(botUserName);
        myTelegramBot.setBotToken(botToken);
        myTelegramBot.setWebHookPath(webHookPath);

        return myTelegramBot;
    }
}
