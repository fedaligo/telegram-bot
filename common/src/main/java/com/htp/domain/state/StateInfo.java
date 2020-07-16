package com.htp.domain.state;

public interface StateInfo {
    void setUsersCurrentBotState(int userId, BotState botState);
    BotState getUsersCurrentBotState(int userId);

}

