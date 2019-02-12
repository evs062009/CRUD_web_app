package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();

    public void show() throws IOException {
        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    //закончить
                    //разбить на подменю "работа с клиентами" и "работа с продуктами"
                    break;
                case "9":
                    return;
                case "0":
                    //!!!реализовать выход из программы
                default:
                    System.out.println("Wrong input!!!");
            }
        }
    }

    private void createClient() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input phone:");
        String phone = br.readLine();
        clientService.createClient(name, surname, phone);
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("5. Add product");
        //!!!подумать какие еще функции
    }
}
