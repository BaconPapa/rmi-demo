package com.papa.exception;

/**
 * It throws in UserService
 */
public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = -6752653137139066532L;
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
