package com.shevtsov;

import com.shevtsov.view.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.show();
    }
}
