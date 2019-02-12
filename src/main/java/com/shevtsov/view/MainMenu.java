package com.shevtsov.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AdminMenu adminMenu = new AdminMenu();
    private final ClientMenu clientMenu = new ClientMenu();

    public void show() throws IOException {
        boolean isRunnig = true;

        while (isRunnig) {
            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            switch (br.readLine()) {
                case "1":
                    adminMenu.show();
                    break;
                case "2":
                    System.out.println("SHOW CLIENT MENU");
                    break;
                case "0":
                    isRunnig = false;
                    break;
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }
}
