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

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showClients(ModelMap modelMap) {
        modelMap.put("message", clientService.getAll());
        return "clients";
    }

    @PostMapping
    public void addClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String phone,
            @RequestParam String email) {
        try {
            clientService.create(name, surname, Integer.parseInt(age), phone, email);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}