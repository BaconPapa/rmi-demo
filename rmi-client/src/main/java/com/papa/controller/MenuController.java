package com.papa.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles different kinds of controller and shown them while executing
 */
public class MenuController extends Controller{

    private HashMap<String, Controller> controllerList;
    public MenuController(String title) {
        controllerList = new HashMap<>();
        this.title = title;
    }

    public void addController(String inputValue, Controller controller) {
        controllerList.put(inputValue, controller);
    }

    @Override
    public void execute() {
        menuPrint();
    }

    /**
     * Printing all handled menus
     */
    private void menuPrint() {
        System.out.printf("%s:\n", title);
        for (Map.Entry<String, Controller> entry:controllerList.entrySet()) {
            System.out.printf("%s. %s\n", entry.getKey(), entry.getValue().title);
        }
        System.out.print("Press number to select related options: ");
        executeInput();
    }

    /**
     * Processing user input
     */
    private void executeInput() {
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();
        while (!controllerList.containsKey(inputValue)) {
            System.out.print("Please input a valid value: ");
            inputValue = scanner.nextLine();
        }
        Controller controller = controllerList.get(inputValue);
        controller.execute();
    }
}
