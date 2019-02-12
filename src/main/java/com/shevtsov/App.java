package com.shevtsov;

import com.shevtsov.view.MainMenu;
import sun.applet.Main;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
    }
}
