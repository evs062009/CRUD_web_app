package com.shevtsov.controllers;

import com.shevtsov.services.OrderService;
import com.shevtsov.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private static final String JSP_NAME = "orders";

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showAll(ModelMap modelMap) {
        return ControllerUtilities.printMsg(modelMap, orderService.getAll(), JSP_NAME);
    }

    @PostMapping
    public void create(ModelMap modelMap, @RequestParam String phone, @RequestParam String productID) {
        OrderServiceImpl orderServiceImpl = (OrderServiceImpl) orderService;
        if (orderServiceImpl.getAuthorisation().authorizeClient(phone)) {
            orderService.createDraft();
            if (!saveNewOrderSpecification(modelMap, productID)) {
                ControllerUtilities.printMsg(modelMap, "Creating has not been done.", JSP_NAME);
            }
        } else {
            ControllerUtilities.printMsg(modelMap, "No such client.", JSP_NAME);
        }
    }

    @PutMapping
    public void modify(ModelMap modelMap, @RequestParam String id, @RequestParam String productID) {
        try {
            if (orderService.copyOrderToDraft(Long.parseLong(id))) {
                if (!saveNewOrderSpecification(modelMap, productID)) {
                    ControllerUtilities.printMsg(modelMap, "Modifying has not been done.", JSP_NAME);
                }
            } else {
                ControllerUtilities.printMsg(modelMap, "No such order.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    @DeleteMapping
    public void remove(ModelMap modelMap, @RequestParam String id){
        try{
            if (!orderService.remove(Long.parseLong(id))){
                ControllerUtilities.printMsg(modelMap, "Removing has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    private boolean saveNewOrderSpecification(ModelMap modelMap, String productID) {
        try {
            if (orderService.addProductToDraft(Long.parseLong(productID))) {
                return orderService.save();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
        return false;
    }
}