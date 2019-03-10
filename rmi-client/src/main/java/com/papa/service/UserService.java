package com.papa.service;

import com.papa.exception.UserAlreadyExistsException;
import com.papa.exception.UserNotExistsException;
import com.papa.model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * UserService
 * It will be invoked on client by rmi
 */
public interface UserService extends Remote {
    /**
     * @param user new user
     * @throws RemoteException throws by network error
     * @throws UserAlreadyExistsException throws if user account already exists
     */
    void addUser(User user) throws RemoteException, UserAlreadyExistsException;

    /**
     * @param user validated user
     * @return whether the user is validated in database
     * @throws RemoteException throws by network error
     * @throws UserNotExistsException throws if user account not exists
     */
    boolean validateUser(User user) throws RemoteException, UserNotExistsException;
}
