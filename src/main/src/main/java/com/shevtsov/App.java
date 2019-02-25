package com.shevtsov;


import com.shevtsov.view.MainMenu;

public class App {
    public static void main(String[] args) {
        MainMenu mainMenu = Creator.create();
        System.out.println("Welcome)");
        mainMenu.show();
        System.out.println("Bye)");
    }
}