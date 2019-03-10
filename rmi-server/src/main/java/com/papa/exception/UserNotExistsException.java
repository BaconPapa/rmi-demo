package com.papa.exception;

/**
 * It throws in UserService
 */
public class UserNotExistsException extends RuntimeException {
    private static final long serialVersionUID = -4712097930173992564L;
    public UserNotExistsException() {
        super("User not exists");
    }
}
