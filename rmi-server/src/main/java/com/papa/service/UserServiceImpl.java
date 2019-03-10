package com.papa.service;

import com.papa.dao.DAO;
import com.papa.exception.UserAlreadyExistsException;
import com.papa.exception.UserNotExistsException;
import com.papa.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

/**
 * Implementation for UserService
 * Run on server
 * Invoke on client
 */
public class UserServiceImpl implements UserService, Serializable {
    private static final long serialVersionUID = -8600316483988232322L;
    private DAO dao;

    public UserServiceImpl() {
        dao = new DAO();
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
        System.out.println("Add user: " + user);
        User u = dao.getUserByAccount(user.account);
        if (u != null) {
            throw new UserAlreadyExistsException();
        }
        dao.addUser(user);
    }

    @Override
    public boolean validateUser(User user) {
        System.out.println("Validate user: " + user);
        User validatedUser = dao.getUserByAccount(user.account);
        if (validatedUser == null) {
            throw new UserNotExistsException();
        }
        return BCrypt.checkpw(user.password, validatedUser.password);
    }
}
