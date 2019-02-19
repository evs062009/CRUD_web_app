package com.shevtsov.view;

import com.shevtsov.services.Authorisation;
import com.shevtsov.services.ClientService;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatus;

public class MainMenu {
    private final Authorisation authorisation;
    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;
    private final ClientService clientService;

    public MainMenu(Authorisation authorisation, AdminMenu adminMenu, ClientMenu clientMenu,
                    ClientService clientService) {
        this.authorisation = authorisation;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
        this.clientService = clientService;
    }

    public void show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;

        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. Client authorisation");
            System.out.println("2. Client registration");
            System.out.println("3. Admin authorisation");
            System.out.println("E. Exit");

            switch (ViewUtilities.inputString()) {
                case "1":
                    if (authorizeClient()) {
                        menuStatus = clientMenu.show();
                    } else {
                        System.out.println("Invalid phone number!!!");
                    }
                    break;
                case "2":
                    if (registration()){
                        menuStatus = clientMenu.show();
                    } else {
                        System.out.println("Registration unsuccessful!!!");
                    }
                    break;
                case "3":
                    if (authorizeAdmin()) {
                        System.out.println("Welcome admin.");
                        menuStatus = adminMenu.show();
                    } else {
                        System.out.println("Invalid password!!!");
                    }
                    break;
                case "E":
                    return;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
    }

    private boolean authorizeClient() {
        System.out.println("Input phone number:");
        String phone = ViewUtilities.inputString();
        return authorisation.authorizeClient(phone);
    }

    private boolean authorizeAdmin() {
        System.out.println("Input password:");
        String input = ViewUtilities.inputString();
        return authorisation.authorizeAdmin(input);
    }

    private boolean registration() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input surname:");
        String surname = ViewUtilities.inputString();
        System.out.println("Input phone:");
        String phone = ViewUtilities.inputString();
        return clientService.create(name, surname, 0, phone, null);
    }
}