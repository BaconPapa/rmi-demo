package com.papa.factory;

import com.papa.config.Config;
import com.papa.config.ErrorMessage;
import com.papa.service.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * It is used to generate user service
 */
public class UserServiceFactory {
    private UserService userService;

    /**
     * Link to server and get user service object on server
     */
    public UserServiceFactory() {
        try {
            Registry registry = LocateRegistry.getRegistry(Config.host, Config.port);
            userService = (UserService) registry.lookup(Config.userServer);
            System.out.printf("Link to server: %s:%d\tserver name: %s\n", Config.host,Config.port, Config.userServer);
        } catch (Exception e) {
            System.out.println(ErrorMessage.MAIN_ERROR);
            System.exit(-1);
        }
    }


    /**
     * Get the userService which is exactly running on server
     */
    public UserService getUserService() {
        return userService;
    }
}
