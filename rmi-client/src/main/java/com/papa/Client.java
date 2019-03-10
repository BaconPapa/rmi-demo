package com.papa;

import com.papa.controller.*;
import com.papa.factory.MainMenuFactory;
import com.papa.factory.UserServiceFactory;
import com.papa.service.UserService;

public class Client {
    public static void main(String[] args){
        // Generate user service
        UserServiceFactory userServiceFactory = new UserServiceFactory();
        UserService userService = userServiceFactory.getUserService();

        // Generate main menu
        MainMenuFactory mainMenuFactory = new MainMenuFactory();
        Controller mainMenu = mainMenuFactory.getMainMenuController(userService);

        // Run main menu
        mainMenu.execute();
    }
}
