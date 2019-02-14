package com.shevtsov;

import com.shevtsov.view.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Wellcome)");
        MainMenu startMenu = new MainMenu();
        startMenu.show();
        System.out.println("Bye)");
    }
}
