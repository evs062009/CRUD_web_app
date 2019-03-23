package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.view.viewEnums.MenuStatus;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
                    create();
                    break;
                case "2":
                    modify();
                    break;
                case "3":
                    remove();
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

    private void create() {
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

    private void modify() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        try{
            Client client = clientService.getClient(clientID);
            System.out.println(client);
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void remove() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        if (clientService.remove(clientID)) {
            System.out.println("Client removed");
        }
    }
}