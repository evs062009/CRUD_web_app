package com.shevtsov.view;

import com.shevtsov.domain.Order;
import com.shevtsov.services.OrderService;
import com.shevtsov.view.viewEnums.MenuStatus;
import com.shevtsov.view.viewUtilities.ViewUtilities;

import java.util.Comparator;
import java.util.List;

public class MenuWorkWithOrders {
    private final OrderService orderService;
    private final EditOrderMenu editOrderMenu;

    public MenuWorkWithOrders(OrderService orderService, EditOrderMenu editOrderMenu) {
        this.orderService = orderService;
        this.editOrderMenu = editOrderMenu;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. List all orders");
            System.out.println("2. Modify order");
            System.out.println("3. Remove order");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    showAllOrders();
                    break;
                case "2":
                    modify();
                    break;
                case "3":
                    remove();
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

    private void showAllOrders() {
        List<Order> orders = orderService.getAll();
        orders.sort(Comparator.comparing(Order::getClient));
        ViewUtilities.showList(orders);
    }

    private void modify() {
        System.out.println("Input order ID");
        long orderID = ViewUtilities.inputLong();
        if (orderService.copyOrderToDraft(orderID)) {
            editOrderMenu.show();
        } else {
            System.out.println("There is no such order!!!");
        }
    }

    private void remove() {
        System.out.println("Input order id");
        long orderID = ViewUtilities.inputLong();
        if (orderService.remove(orderID)) {
            System.out.println("Order removed.");
        }
    }
}