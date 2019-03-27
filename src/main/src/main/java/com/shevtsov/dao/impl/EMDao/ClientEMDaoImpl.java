package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/*
 * Client Dao for Hiber (Entity Manager - EM)
 */
@Repository
public class ClientEMDaoImpl extends AbstractEMDaoImpl<Client> implements ClientDao {

    @Autowired
    public ClientEMDaoImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
        entityClass = Client.class;
        queryFrom = "FROM Client";
    }

    @Override
    public long saveClient(Client client) {
        if (super.save(client)){
            return client.getId();
        } else {
            return -1;
        }
    }

    @Override
    public long findByPhone(String phone) {
        try {
            return entityManager.createQuery(queryFrom + " WHERE phone = :phone", Client.class).
                    setParameter("phone", phone).getSingleResult().getId();
        } catch (NoResultException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}