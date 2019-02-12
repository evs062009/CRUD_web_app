package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.ProductService;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class AdminMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    public void show() throws IOException {
        while (true) {
            System.out.println("1. Work with clients");
            System.out.println("2. Work with products");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (br.readLine()) {
                case "1":
                    showMenuWorkWithClients();
                    break;
                case "2":
                    showMenuWorkWithProducts();
                    break;
                case "9":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }

    private void showMenuWorkWithClients() throws IOException {
        while (true) {
            System.out.println("1. Add client");
            System.out.println("2. Modify client");
            System.out.println("3. Remove client");
            System.out.println("4. List all clients");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (br.readLine()) {
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
                    listAllClients();
                    break;
                case "9":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }
    }

    private void showMenuWorkWithProducts() throws IOException {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. List all products");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (br.readLine()){
                case "1":
                    addProduct();
                    break;
                case "2":
                    modifyProduct();
                    break;
                case "3":
                    removeProduct();
                    break;
                case "4":
                    listAllProducts();
                    break;
                case "9":
                    return;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong input!!!");
                    break;
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

    private void modifyClient() throws IOException {
        System.out.println("Input client id");
        long clientID = inputLong();
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input phone:");
        String phone = br.readLine();
        clientService.modifyClient(clientID, name, surname, phone);
    }

    private void removeClient() throws IOException {
        System.out.println("Input client id");
        long clientID = inputLong();
        clientService.removeClient(clientID);
    }

    private void listAllClients() {
        clientService.listAllClients();
    }

    private void addProduct() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(inputLong());
        productService.createProduct(name, price);
    }

    private void modifyProduct() throws IOException {
        System.out.println("Input product id");
        long productID = inputLong();
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(inputLong());
        productService.modifyProduct(productID, name, price);
    }

    private void removeProduct() throws IOException {
        System.out.println("Input product id");
        long productID = inputLong();
        productService.removeProduct(productID);
    }

    private void listAllProducts() {
        productService.listAllProducts();
    }

    private long inputLong() throws IOException {
        while (true) {
            String input = br.readLine();
            try{
                return Long.parseLong(input);
            } catch (Exception ex){
                System.out.println("Invalid input!!! Please repeat");
            }
        }
    }
}