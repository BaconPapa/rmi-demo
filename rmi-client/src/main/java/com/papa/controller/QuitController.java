package com.papa.controller;

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
