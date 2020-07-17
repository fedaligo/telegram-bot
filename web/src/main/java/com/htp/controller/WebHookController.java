package com.htp.controller;

import com.htp.config.MyTelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
public class WebHookController {

    private final MyTelegramBot myTelegramBot;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public BotApiMethod<?> onUpdateReceived(@RequestBody @Valid Update update){
        return myTelegramBot.onWebhookUpdateReceived(update);
    }
}
