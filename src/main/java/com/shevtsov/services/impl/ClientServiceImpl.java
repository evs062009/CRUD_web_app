package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;

import javax.xml.bind.ValidationException;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    //депенденси инжекшин для Инверсия Контроль
    private ClientDao clientDao;
    private ValidationService validationService;

    //конструктор для Инверсия Контроль
    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService){
        this.validationService = validationService;
        this.clientDao = clientDao;
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        this.createClient(name, surname, 0, phone, null);
    }

    @Override
    public void createClient(String name, String surname, int age, String phone, String email) {
        try {
            validationService.validateAge(age);
            Client client = new Client(name, surname, phone);
            boolean result = clientDao.saveClient(client);
        } catch (ValidationException ve){
            ve.getStackTrace();
        }

    }

    @Override
    public boolean modifyClient(long id, String newName, String newSurname, String newPhone) {
        System.out.println("Processing...");
        return clientDao.modifyClient(id, newName, newSurname, newPhone);
    }

    @Override
    public boolean removeClient(long id) {
        System.out.println("Processing...");
        return clientDao.removeClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        System.out.println("Received collection from DAO");
        System.out.println("Processed");
        System.out.println("Transmitted to UI");
        //можно добавить логику, например сортировку
        return clientDao.gatAllClient();
    }

    @Override
    public boolean modifyUserInformation(String newName, String newSurname, String newPhone) {
        System.out.println("Defined current client id");
        long currentClientID = 0;
        return modifyClient(currentClientID, newName, newSurname, newPhone);
    }
}