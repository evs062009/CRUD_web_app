package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatus;

public class ClientMenu {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderMenu orderMenu;

    public ClientMenu(ClientService clientService, ProductService productService, OrderService orderService,
                      OrderMenu orderMenu) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderMenu = orderMenu;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. List all products");
            System.out.println("2. Create order");
            System.out.println("3. Show my orders");
            System.out.println("4. Remove order");
            System.out.println("5. Modify user information");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    ViewUtilities.showList(productService.gatAll());
//                    showProducts(productService.gatAll());
                    break;
                case "2":
                    createOrder();
                    break;
                case "3":
                    ViewUtilities.showList(orderService.getUserOrders());
//                    showUserOrders();
                    break;
                case "4":
                    removeOrder();
                    break;
                case "5":
                    modifyUserInformation();
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

    private void createOrder() {
        orderService.createOrderDraft();
        orderMenu.show();
    }

    private void removeOrder() {
        System.out.println("Input order id");
        long orderID = ViewUtilities.inputLong();
        if (orderService.remove(orderID)) {
            System.out.println("Order removed.");
        }
    }

//    private void showUserOrders() {
//        for (Order order : orderService.getUserOrders()) {
//            System.out.println(order);
//        }
//    }

//    private void showProducts(List<Product> products) {
//        for (Product product : products) {
//            System.out.println(product);
//        }
//    }

//    private void createOrderMenu() {
//        orderService.createOrderDraft();
//        while (true) {
//            System.out.println("Goods in stock:");
//            ViewUtilities.showList(productService.gatAll());
////            showProducts(productService.gatAll());
//            System.out.println("Goods in your order:");
//            ViewUtilities.showList(orderService.getOrderDraftProducts());
////            showProducts(orderService.getBasket());
//
//            System.out.println("1. Add product to the order");
//            System.out.println("2. Remove product from the order");
//            System.out.println("S. Save order and exit");
//            System.out.println("E. Exit to previous menu without saving");
//
//            switch (ViewUtilities.inputString()) {
//                case "1":
//                    addProductToOrder();
//                    break;
//                case "2":
//                    removeProductFromOrder();
//                    break;
//                case "S":
//                    if (orderService.save()) {
//                        System.out.println("Order created successfully.");
//                    }
//                    return;
//                case "E":
//                    return;
//                default:
//                    System.out.println("Invalid input!!!");
//                    break;
//            }
//        }
//    }

//    private void addProductToOrder() {
//        System.out.println("Input product ID");
//        long productID = ViewUtilities.inputLong();
//        if (!orderService.addProductToOrderDraft(productID)) {
//            System.out.println("There is no such product!!!");
//        }
//    }
//
//    private void removeProductFromOrder() {
//        System.out.println("Input product ID");
//        long productID = ViewUtilities.inputLong();
//        if (orderService.removeProductFromOrderDraft(productID)) {
//            System.out.println("There is no such product!!!");
//        }
//    }

    private void modifyUserInformation() {
        System.out.println("Input new name");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new surname");
        String newSurname = ViewUtilities.inputString();
        System.out.println("Input new age:");
        int newAge = ViewUtilities.inputInt();
        System.out.println("Input new phone number");
        String newPhone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String newEmail = ViewUtilities.inputString();
        if (clientService.modifyUserInformation(newName, newSurname, newAge, newPhone, newEmail)) {
            System.out.println("Information modified");
        }
    }
}