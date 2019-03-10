package com.papa.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainController extends Controller{

    private HashMap<String, Controller> controllerList;
    public MainController(String title) {
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

    private void menuPrint() {
        System.out.printf("%s:\n", title);
        for (Map.Entry<String, Controller> entry:controllerList.entrySet()) {
            System.out.printf("%s. %s\n", entry.getKey(), entry.getValue().title);
        }
        System.out.print("Press number to select related options: ");
        executeInput();
    }

    private void executeInput() {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        while (!controllerList.containsKey(num)) {
            System.out.print("Please input a valid value: ");
            num = scanner.nextLine();
        }
        Controller controller = controllerList.get(num);
        controller.execute();
    }
}
