//package com.shevtsov.controllers;
//
//import com.shevtsov.services.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ClientController {
//    private ClientService clientService;
//
//    @Autowired
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    @GetMapping("/clientss")
//    public String showClients(ModelMap modelMap){
//        modelMap.put("message", clientService.getAll());
//        return "clients";
//    }
//}
