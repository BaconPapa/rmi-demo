package com.papa.service;

import com.papa.exception.UserAlreadyExistsException;
import com.papa.exception.UserNotExistsException;
import com.papa.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
    void addUser(User user) throws RemoteException, UserAlreadyExistsException;
    boolean validateUser(User user) throws RemoteException, UserNotExistsException;
}
