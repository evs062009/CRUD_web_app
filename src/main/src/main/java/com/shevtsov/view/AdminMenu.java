package com.shevtsov.view;

import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatus;

public class AdminMenu {
    private final MenuWorkWithClients menuWorkWithClients;
    private final MenuWorkWithProducts menuWorkWithProducts;
    private final MenuWorkWithOrders menuWorkWithOrders;

    public AdminMenu(MenuWorkWithClients menuWorkWithClients, MenuWorkWithProducts menuWorkWithProducts,
                     MenuWorkWithOrders menuWorkWithOrders) {
        this.menuWorkWithClients = menuWorkWithClients;
        this.menuWorkWithProducts = menuWorkWithProducts;
        this.menuWorkWithOrders = menuWorkWithOrders;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. Clients");
            System.out.println("2. Products");
            System.out.println("3. Orders");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    menuStatus = menuWorkWithClients.show();
                    break;
                case "2":
                    menuStatus = menuWorkWithProducts.show();
                    break;
                case "3":
                    menuStatus = menuWorkWithOrders.show();
                    break;
                case "R":
                    return MenuStatus.CONTINUE_WORK;
                case "E":
                    return MenuStatus.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatus.EXIT_PROGRAM;
    }
}