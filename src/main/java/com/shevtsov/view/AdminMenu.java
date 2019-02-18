package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatus;

import java.math.BigDecimal;

public class AdminMenu {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderMenu orderMenu;

    public AdminMenu(ClientService clientService, ProductService productService, OrderService orderService,
                     OrderMenu orderMenu) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderMenu = orderMenu;
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
                    menuStatus = showMenuWorkWithClients();
                    break;
                case "2":
                    menuStatus = showMenuWorkWithProducts();
                    break;
                case "3":
                    menuStatus = showMenuWorkWithOrders();
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

    private MenuStatus showMenuWorkWithClients() {
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
                    showAllClients();
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

    private MenuStatus showMenuWorkWithProducts() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. List all products");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
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
                    showAllProducts();
                    break;
                case "9":
                    return MenuStatus.CONTINUE_WORK;
                case "0":
                    return MenuStatus.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatus.EXIT_PROGRAM;
    }

    private MenuStatus showMenuWorkWithOrders() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. List all orders");
            System.out.println("2. Modify order");
            System.out.println("3. Remove order");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    listAllOrder();
                    break;
                case "2":
                    modifyOrder();
                    break;
                case "3":
                    removeOrder();
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

    private void modifyOrder() {
        System.out.println("Input order ID");
        long orderID = ViewUtilities.inputLong();
        if (orderService.copyOrderToDraft(orderID)) {
            orderMenu.show();
        } else {
            System.out.println("There is no such order!!!");
        }
    }

    private void removeOrder() {
        System.out.println("Input order id");
        long orderID = ViewUtilities.inputLong();
        if (orderService.remove(orderID)) {
            System.out.println("Order removed.");
        }
    }

//    private void modifyOrderMenu() {
//        while (true) {
//            System.out.println("Goods in stock:");
//            ViewUtilities.showList(productService.gatAll());
//            System.out.println("Goods in your order:");
//            ViewUtilities.showList(orderService.getOrderDraftProducts());
//
//            System.out.println("1. Add product to the order");
//            System.out.println("2. Remove product from the order");
//            System.out.println("S. Save order and exit");
//            System.out.println("E. Exit to previous menu without saving");
//
//            switch (ViewUtilities.inputString()) {
//                case "1":
//                    111
//                    break;
//                case "2":
//                    111
//                    break;
//                case "S":
//                    111
//                    return;
//                case "E":
//                    return;
//                default:
//                    System.out.println("Invalid input!!!");
//                    break;
//            }
//            System.out.println();
//        }
//    } else
//
//        System.out.println("There is no such order!!!");
//    }
//
//}

    private void showAllClients() {
        for (Client client : clientService.getAll()) {
            System.out.println(client);
        }
    }

    private void showAllProducts() {
        for (Product product : productService.gatAll()) {
            System.out.println(product);
        }
    }

    private void listAllOrder() {
        for (Order order : orderService.getAll()) {
            System.out.println(order);
        }
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

    private void addProduct() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(ViewUtilities.inputLong());
        if (productService.create(name, price)) {
            System.out.println("Product saved");
        }
    }

    private void modifyProduct() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        System.out.println("Input new name:");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new price:");
        BigDecimal newPrice = BigDecimal.valueOf(ViewUtilities.inputLong());
        if (productService.modify(productID, newName, newPrice)) {
            System.out.println("Product modified");
        }
    }

    private void removeProduct() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        if (productService.remove(productID)) {
            System.out.println("Product removed");
        }
    }
}