package com.shevtsov;

import com.shevtsov.view.MainMenu;
import com.shevtsov.view.StartMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Wellcome)");
        StartMenu startMenu = new StartMenu();
        startMenu.show();
        System.out.println("Bye)");
    }
}
