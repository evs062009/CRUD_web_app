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
    public boolean createClient(String name, String surname, int age, String phone, String email) {

        //вставить валидацию на формат номера телефона
        //на формат мэйл
        //на уникальность номера телефона

        try {
            validationService.validateAge(age);
            Client client = new Client(name, surname, age, phone, email);
            clientDao.save(client);
            return true;
        } catch (ValidationException ve){
            ve.getStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifyClient(long id, String newName, String newSurname, int newAge, String newPhone,
                                String newEmail) {
        if (clientDao.isContainsKey(id)){
            Client client = new Client(newName, newSurname, newAge, newPhone, newEmail);
            clientDao.modify(id, client);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeClient(long id) {
        if (clientDao.isContainsKey(id)){
            clientDao.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.gatAll();
    }

    @Override
    public boolean modifyUserInformation(String newName, String newSurname, String newPhone) {
        System.out.println("Defined current client id");
        long currentClientID = 0;
        return modifyClient(currentClientID, newName, age, newSurname, newPhone, email);
    }
}