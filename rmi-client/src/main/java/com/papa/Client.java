package com.papa;

import com.papa.controller.*;
import com.papa.factory.MainMenuFactory;
import com.papa.factory.UserServiceFactory;
import com.papa.service.UserService;

public class Client {
    public static void main(String[] args){
        UserServiceFactory userServiceFactory = new UserServiceFactory();
        UserService userService = userServiceFactory.getUserService();
        MainMenuFactory mainMenuFactory = new MainMenuFactory();
        Controller mainController = mainMenuFactory.getMainController(userService);
        mainController.execute();
    }
}
