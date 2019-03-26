package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
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
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
        return client.getId();
    }

    @Override
    public Optional<Client> findByID(long id) {
        return Optional.empty();
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public List<Client> getAll() {
        return entityManager.createQuery("FROM Client", Client.class).getResultList();
    }

    @Override
    public long findByPhone(String phone) {
        try{
            return entityManager.createQuery("FROM Client WHERE phone = :phone", Client.class).
                    setParameter("phone", phone).getSingleResult().getId();
        } catch (NoResultException e){
            return -1;
        }
    }

    @Override
    public boolean isContainsKey(long id) {
        return false;
    }

    @Override
    public boolean modify(Client client) {
        return false;
    }
}