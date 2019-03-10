package com.papa.controller;

/**
 * It is used to quit the whole program
 */
public class QuitController extends Controller {
    public QuitController() {
        title = "Quit";
    }

    @Override
    public void execute() {
        System.out.println("Bye :)");
        System.exit(0);
    }
}
