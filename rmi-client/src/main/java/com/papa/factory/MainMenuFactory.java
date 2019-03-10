package com.papa.factory;

import com.papa.controller.*;
import com.papa.service.UserService;

public class MainMenuFactory {
    public Controller getMainController(UserService userService) {
        MainController mainController = new MainController("Main Menu");
        Controller quitController = new QuitController();
        mainController.addController("3", quitController);
        Controller signUpController = new SignUpController(mainController, userService);
        mainController.addController("2", signUpController);
        MainController loginSubMenu = new MainController("Login Menu");
        loginSubMenu.addController("1", mainController);
        loginSubMenu.addController("2", quitController);
        LoginController loginController = new LoginController(mainController, loginSubMenu, userService);
        mainController.addController("1", loginController);
        return mainController;
    }
}
