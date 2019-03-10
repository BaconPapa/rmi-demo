package com.papa.factory;

import com.papa.controller.*;
import com.papa.service.UserService;

/**
 * It can generate new main menu controller
 */
public class MainMenuFactory {

    /**
     * @param userService user service to process user object
     * @return main menu controller
     */
    public Controller getMainMenuController(UserService userService) {
        MenuController mainMenu = new MenuController("Main Menu");
        Controller quitController = new QuitController();
        mainMenu.addController("3", quitController);
        Controller signUpController = new SignUpController(mainMenu, userService);
        mainMenu.addController("2", signUpController);
        MenuController loginSubMenu = new MenuController("Login Menu");
        loginSubMenu.addController("1", mainMenu);
        loginSubMenu.addController("2", quitController);
        LoginController loginController = new LoginController(mainMenu, loginSubMenu, userService);
        mainMenu.addController("1", loginController);
        return mainMenu;
    }
}
