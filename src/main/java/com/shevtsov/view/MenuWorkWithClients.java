package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.view.viewEnums.MenuStatus;
import com.shevtsov.view.viewUtilities.ViewUtilities;

public class MenuWorkWithClients {
    private final ClientService clientService;

    public MenuWorkWithClients(ClientService clientService) {
        this.clientService = clientService;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. Add client");
            System.out.println("2. Modify client");
            System.out.println("3. Remove client");
            System.out.println("4. List all clients");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    modifyClient();
                    break;
                case "3":
                    removeClient();
                    break;
                case "4":
                    ViewUtilities.showList(clientService.getAll());
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

    private void createClient() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input surname:");
        String surname = ViewUtilities.inputString();
        System.out.println("Input age:");
        int age = ViewUtilities.inputInt();
        System.out.println("Input phone number:");
        String phone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String email = ViewUtilities.inputString();
        if (clientService.create(name, surname, age, phone, email)) {
            System.out.println("Client saved");
        }
    }

    private void modifyClient() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        System.out.println("Input new name:");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new surname:");
        String newSurname = ViewUtilities.inputString();
        System.out.println("Input new age:");
        int newAge = ViewUtilities.inputInt();
        System.out.println("Input new phone number:");
        String newPhone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String newEmail = ViewUtilities.inputString();
        if (clientService.modify(clientID, newName, newSurname, newAge, newPhone, newEmail)) {
            System.out.println("Client modified");
        }
    }

    private void removeClient() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        if (clientService.remove(clientID)) {
            System.out.println("Client removed");
        }
    }
}