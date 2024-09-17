package com.mai.gameinit;

import com.mai.data.AI;
import com.mai.data.User;
import com.mai.service.AIService;
import com.mai.service.UserService;

import java.util.List;

public class GameApplication {


    public GameApplication(User user, List<AI> aiUsers) {
        UserService.user = user;
        AIService.aiList = aiUsers;
    }

}
