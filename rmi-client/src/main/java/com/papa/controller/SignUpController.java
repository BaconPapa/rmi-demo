package com.papa.controller;

import com.papa.config.ErrorMessage;
import com.papa.exception.UserAlreadyExistsException;
import com.papa.model.User;
import com.papa.service.UserService;
import com.papa.util.InputUtil;

import java.rmi.RemoteException;

/**
 * It is used to sign up
 */
public class SignUpController extends Controller{
    private MenuController mainMenu;
    private UserService userService;

    /**
     * @param mainMenu main menu
     * @param userService user service to add new user
     */
    public SignUpController(MenuController mainMenu, UserService userService) {
        this.mainMenu = mainMenu;
        this.userService = userService;
        this.title = "Sign Up";
    }

    @Override
    public void execute() {
        User user = InputUtil.getUserFromInput();
        try {
            userService.addUser(user);
            System.out.println("Signup successful");
        } catch (RemoteException e) {
            System.out.println(ErrorMessage.REMOT_ERROR);
        } catch (UserAlreadyExistsException e) {
            System.out.println(ErrorMessage.USER_ALREADY_EXISTS_ERROR);
        } finally {
            System.out.println("Back to main menu");
            mainMenu.execute();
        }
    }
}
