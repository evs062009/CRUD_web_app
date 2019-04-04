package com.shevtsov.controllers;

import com.shevtsov.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;
    private static final String JSP_NAME = "clients";

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showAll(ModelMap modelMap) {
        return ControllerUtilities.printMsg(modelMap, clientService.getAll(), JSP_NAME);
    }

    @PostMapping
    public void add(ModelMap modelMap,
                    @RequestParam String name,
                    @RequestParam String surname,
                    @RequestParam String age,
                    @RequestParam String phone,
                    @RequestParam String email) {
        try {
            if (!clientService.create(name, surname, Integer.parseInt(age), phone, email)) {
                ControllerUtilities.printMsg(modelMap, "Creating has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    @PutMapping
    public void modify(ModelMap modelMap,
                       @RequestParam String id,
                       @RequestParam String name,
                       @RequestParam String surname,
                       @RequestParam String age,
                       @RequestParam String phone,
                       @RequestParam String email) {
        try {
            if (!clientService.modify(Long.parseLong(id), name, surname, Integer.parseInt(age), phone, email)) {
                ControllerUtilities.printMsg(modelMap, "Modifying has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    @DeleteMapping
    public void remove(ModelMap modelMap, @RequestParam String id) {
        try {
            if (!clientService.remove(Long.parseLong(id))) {
                ControllerUtilities.printMsg(modelMap, "Removing has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }
}