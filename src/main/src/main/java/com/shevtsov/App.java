package com.shevtsov;


import com.shevtsov.view.MainMenu;

public class App {
    public static void main(String[] args) {
        Creator creator = new Creator();
        MainMenu mainMenu = creator.create();
        System.out.println("Welcome)");
        mainMenu.show();
        System.out.println("Bye)");
    }
}