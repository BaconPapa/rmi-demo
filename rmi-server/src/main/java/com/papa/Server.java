package com.papa;

import com.papa.config.Config;
import com.papa.config.ErrorMessage;
import com.papa.service.UserService;
import com.papa.service.UserServiceImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args){
        try{
            // export user service
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            UserService userService = (UserService) UnicastRemoteObject.exportObject(userServiceImpl, 0);

            // user service registry and port bind
            Registry registry = LocateRegistry.createRegistry(Config.serverPort);
            registry.bind(Config.userServerName, userService);
            System.out.printf(
                    "RMI server is bind at port: %d\tserver name: %s\n",
                    Config.serverPort,
                    Config.userServerName
            );
        }catch (AlreadyBoundException e){
            System.out.println(ErrorMessage.RMI_ERROR);
            System.exit(-1);
        }catch (RemoteException e){
            System.out.println(ErrorMessage.RMI_ALREADY_BOUND_ERROR);
            System.exit(-1);
        }
    }
}
