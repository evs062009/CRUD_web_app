package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDaoImpl;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.validators.ValidationService;

import javax.xml.bind.ValidationException;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();
    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ValidationService validationService;

    public ClientServiceImpl(ValidationService validationService){
        this.validationService = validationService;
    }

    @Override
    public boolean create(String name, String surname, int age, String phone, String email) {

        //вставить валидацию на формат номера телефона
        //на формат мэйл
        //на уникальность номера телефона

//        try {
//            validationService.validateAge(age);
            Client client = new Client(name, surname, age, phone, email);
            clientDao.save(client);
            return true;
//        } catch (ValidationException ve){
//            ve.getStackTrace();
//        }
//        return false;
    }

    @Override
    public boolean modify(long id, String newName, String newSurname, int newAge, String newPhone,
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
    public boolean remove(long id) {
        if (clientDao.isContainsKey(id)){
            clientDao.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Client> getAll() {
        return clientDao.gatAll();
    }

    @Override
    public boolean modifyUserInformation(String newName, String newSurname, int newAge, String newPhone,
                                         String newEmail) {
        return modify(authorisation.getCurrentUserID(), newName, newSurname, newAge, newPhone, newEmail);
    }
}