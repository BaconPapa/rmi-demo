package com.papa.controller;

/**
 * Controller is used to handle user input
 */
public abstract class Controller {
    /**
     * the name for controller
     */
    public String title;

    /**
     * execute process after user's input
     */
    abstract public void execute();
}
