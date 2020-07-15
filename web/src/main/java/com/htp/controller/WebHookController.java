package com.htp.controller;

import com.htp.domain.HibernateCityInfo;
import com.htp.repository.HibernateCityInfoRepository;
import com.htp.config.MyTelegramBot;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
public class WebHookController {
    //перехват POST запрососв от телеграмм сервера

    private final MyTelegramBot myTelegramBot;

    private final HibernateCityInfoRepository hibernateCityInfoRepository;

    /*public WebHookController(MyTelegramBot myTelegramBot, HibernateCityInfoRepository hibernateCityInfoRepository) {
        this.myTelegramBot = myTelegramBot;
        this.hibernateCityInfoRepository = hibernateCityInfoRepository;
    }*/



    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public BotApiMethod<?> onUpdateReceived(@RequestBody @Valid Update update){
        return myTelegramBot.onWebhookUpdateReceived(update);
    }

    @GetMapping("/tt")
    @ResponseStatus(HttpStatus.OK)
    public String getHibernatesCarsRepository() {
        return "trtr";
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Cars"),
            @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Cars was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })

    @GetMapping(value = "/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCityInfo> getHByIdRepository(@ApiParam("Path Id") @PathVariable("id") Long id) {
        HibernateCityInfo t = hibernateCityInfoRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
