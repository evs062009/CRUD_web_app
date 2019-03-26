package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import com.shevtsov.exceptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Client Dao for Hiber (Entity Manager - EM)
 */
@Repository
public class ClientEMDaoImpl implements ClientDao {
    private EntityManager entityManager;

    @Autowired
    public ClientEMDaoImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public long save(Client client) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
            return client.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Optional<Client> findByID(long id) {
        Optional<Client> optional = Optional.empty();
        try {
            Client client = entityManager.find(Client.class, id);
            if (client != null) {
                optional = Optional.of(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optional;
    }

    @Override
    public void remove(long id) {
        Optional<Client> optional = findByID(id);
        if (optional.isPresent()) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(optional.get());
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try {
            clients = entityManager.createQuery("FROM Client", Client.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public long findByPhone(String phone) {
        try {
            return entityManager.createQuery("FROM Client WHERE phone = :phone", Client.class).
                    setParameter("phone", phone).getSingleResult().getId();
        } catch (NoResultException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean isContainsKey(long id) {
        return findByID(id).isPresent();
    }

    @Override
    public boolean modify(Client client) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();
            return true;
        } catch (ObjectNotFoundExeption e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}