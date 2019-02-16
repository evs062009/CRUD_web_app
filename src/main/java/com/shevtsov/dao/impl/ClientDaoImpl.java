package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {
    //добавили для ємуляции БД
    private Map<Long, Client> map = new HashMap<>();
    private static long generator = 0;
    private static ClientDao clientDao = new ClientDaoImpl();

    //синглтон
    private ClientDaoImpl(){
    }

    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving... Please wait.");
        //генерируем ИД
        client.setId(generator++);
        // сохраняем в БД
        map.put(client.getId(), client);
        return true;
    }

    @Override
    public boolean modifyClient(long id, String newName, String newSurname, String newPhone) {
        System.out.println("Modifying... Please wait");
        return true;
    }

    @Override
    public boolean removeClient(long client) {
        System.out.println("Deleting... Please wait.");
        return true;
    }

    @Override
    public List<Client> gatAllClient() {
        System.out.println("Receiving data from storage...");
        System.out.println("Creating collection...");
        System.out.println("Transmitting to Service");
        //формируем из данных БД коллекцию и передаем в service
        //лучше не таскать саму "базу", а копировать в новую коллекцию - безопаснее
        return new ArrayList<>((map.values()));
    }

    @Override
    public boolean findClientByPhone(String phone) {
        System.out.println("Searching... Please wait.");
        System.out.println("Client found");
        return true;
    }

    //фабричный метод для синглтона
    public static ClientDao getInstance(){
        return clientDao;
    }
}