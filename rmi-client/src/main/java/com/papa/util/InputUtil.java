package com.papa.util;

import com.papa.model.User;

import java.util.Scanner;

public class InputUtil {
    public static String getInput(String name) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine().trim();
        while (inputString.isEmpty()) {
            System.out.printf("Invalid %s, please input again: ", name);
            inputString = scanner.nextLine().trim();
        }
        return inputString;
    }

    public static User getUserFromInput() {
        System.out.print("Please Input the account: ");
        String account = InputUtil.getInput("account");
        System.out.print("Please Input the password: ");
        String password = InputUtil.getInput("password");
        return new User(account, password);
    }
}
