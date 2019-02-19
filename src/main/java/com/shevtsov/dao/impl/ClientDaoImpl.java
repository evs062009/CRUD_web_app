package com.shevtsov.dao.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {
    private Map<Long, Client> map = new HashMap<>();        //storage emulation
    private static long generator = 0;
    private static ClientDao instance;

    private ClientDaoImpl(){
    }

    public static ClientDao getInstance(){
        if (instance == null){
            instance = new ClientDaoImpl();
        }
        return instance;
    }

    @Override
    public long save(Client client) {
        client.setId(generator++);
        long clientID =client.getId();
        map.put(clientID, client);
        return clientID;
    }

    @Override
    public void modify(long id, Client client) {
        map.put(id, client);
    }

    @Override
    public Client findByID(long id) {
        return map.get(id);
    }

    @Override
    public void remove(long id) {
        map.remove(id);
    }

    @Override
    public List<Client> gatAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public long findByPhone(String phone) {
        for (Map.Entry<Long, Client> entry: map.entrySet()
             ) {
            long key = entry.getKey();
            Client client = entry.getValue();
            if (client != null && client.getPhone() != null && client.getPhone().equals(phone)){
                return key;
            }
        }
        return -1;
    }

    @Override
    public boolean isContainsKey(long id) {
        return map.containsKey(id);
    }
}