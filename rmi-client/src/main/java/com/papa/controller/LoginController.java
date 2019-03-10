package com.papa.controller;

import com.papa.config.ErrorMessage;
import com.papa.exception.UserNotExistsException;
import com.papa.model.User;
import com.papa.service.UserService;
import com.papa.util.InputUtil;

import java.rmi.RemoteException;

/**
 * Login Process
 */
public class LoginController extends Controller {
    private MenuController mainMenu;
    private MenuController subMenu;
    private UserService userService;

    /**
     * @param mainMenu First level menu
     * @param subMenu Sub menu shown after login complete
     * @param userService it is used to validate user info
     */
    public LoginController(MenuController mainMenu, MenuController subMenu, UserService userService) {
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

    /**
     * back to the main menu
     */
    private void back() {
        System.out.println("Back to main menu");
        mainMenu.execute();
    }

    /**
     * @param result the result for validate user's password
     * If user inputs correct password, it will show the subMenu.
     * Otherwise, it will back to the main menu
     */
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
