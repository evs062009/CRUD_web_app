package com.shevtsov;


import com.shevtsov.view.MainMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.shevtsov");
        MainMenu menu = context.getBean(MainMenu.class);
        menu.show();
    }
}