package com.papa.controller;

import com.papa.config.ErrorMessage;
import com.papa.exception.UserNotExistsException;
import com.papa.model.User;
import com.papa.service.UserService;
import com.papa.util.InputUtil;

import java.rmi.RemoteException;

public class LoginController extends Controller {
    private MainController mainMenu;
    private MainController subMenu;
    private UserService userService;

    public LoginController(MainController mainMenu, MainController subMenu, UserService userService) {
        title = "Log In";
        this.userService = userService;
        this.mainMenu = mainMenu;
        this.subMenu = subMenu;
    }

    @Override
    public void execute() {
        User user = InputUtil.getUserFromInput();
        try {
            boolean result = userService.validateUser(user);
            resultProcess(result);
        } catch (RemoteException e) {
            System.out.println(ErrorMessage.REMOT_ERROR);
            back();
        } catch (UserNotExistsException e) {
            System.out.println(ErrorMessage.USER_NOT_EXISTS_ERROR);
            back();
        }
    }

    private void back() {
        System.out.println("Back to main menu");
        mainMenu.execute();
    }

    private void resultProcess(boolean result) {
        if (result) {
            System.out.println("Login success");
            subMenu.execute();
        } else {
            System.out.println(ErrorMessage.INCORRECT_PASSWORD_ERROR);
            back();
        }
    }
}
