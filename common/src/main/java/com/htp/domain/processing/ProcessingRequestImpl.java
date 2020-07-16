package com.htp.domain.processing;

import com.htp.domain.state.BotState;
import com.htp.domain.state.StateInfoImpl;
import com.htp.repository.HibernateCityInfoRepository;
import com.htp.domain.message.ReplyMessagesService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingRequestImpl implements ProcessingRequest {
    @Autowired
    private StateInfoImpl userDataCache;
    @Autowired
    private ReplyMessagesService messagesService;
    @Autowired
    private HibernateCityInfoRepository hibernateCityInfoRepository;

    @Override
    public List<SendMessage> processUsersInput(BotState currentState, Message inputMsg) {
        List<String> allCapitals = hibernateCityInfoRepository.findAllCapitals();
        List<String> allCountries = hibernateCityInfoRepository.findAllCountries();
        List<SendMessage> allMessages = new ArrayList<>();
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();


        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        if (botState.equals(BotState.ASK_CAPITAL)) {
            allMessages.add(messagesService.getReplyMessage(chatId, "reply.askCapital"));
        }

        if (botState.equals(BotState.ASK_LIST)) {
            if(allCapitals.size()>=40){
                for(int p=0;p<allCapitals.size()/40;p++){
                    StringBuilder builder1 = new StringBuilder();
                    for(int i = p*40; i< (p+1)*40; i++) {
                        builder1.append("Country:"+allCountries.get(i)+"\n"+"Capital:"+allCapitals.get(i) + "\n"+ "\n");
                    }
                    allMessages.add(new SendMessage(chatId,builder1.toString()));
                    if(allCapitals.size()%40!=0 && p==allCapitals.size()/40-1){
                        StringBuilder builder2 = new StringBuilder();
                        for(int x=allCapitals.size()-allCapitals.size()%40;x<allCapitals.size();x++){
                            builder2.append("Country:"+allCountries.get(x)+"\n"+"Capital:"+allCapitals.get(x) + "\n"+ "\n");
                        }
                        allMessages.add(new SendMessage(chatId,builder2.toString()));
                    }
                }
            }
        }

        if(botState.equals(BotState.WORD)){
            for(String cap:allCapitals){
                if(cap.toLowerCase().equals(inputMsg.getText().toLowerCase())){
                    allMessages.add(new SendMessage(chatId,hibernateCityInfoRepository.findInfoByCapital(cap)));
                    break;
                }
            }
            if(allMessages.size()==0){
                allMessages.add(new SendMessage(inputMsg.getChatId(),"Your request is not correct. " +
                        "Please enter the correct request"));
            }
        }
        return allMessages;
    }
}