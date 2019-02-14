package com.shevtsov;

import com.shevtsov.view.MainMenu;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome)");
        MainMenu startMenu = new MainMenu();
        startMenu.show();
        System.out.println("Bye)");
    }
}
