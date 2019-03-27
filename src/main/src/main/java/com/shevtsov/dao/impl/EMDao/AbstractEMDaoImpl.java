package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.exceptions.ObjectNotFoundExeption;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Abstract Dao for Hiber (AbstractEntity Manager - EM)
 */
public abstract class AbstractEMDaoImpl<T> {
    EntityManager entityManager;
    Class<T> entityClass;
    String queryFrom;

    public boolean save(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<T> findByID(long id) {
        Optional<T> optional = Optional.empty();
        try {
            T entity = entityManager.find(entityClass, id);
            if (entity != null) {
                optional = Optional.of(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optional;
    }

    public void remove(long id) {
        Optional<T> optional = findByID(id);
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

    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try {
            entities = entityManager.createQuery(queryFrom, entityClass).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean isContainsKey(long id) {
        return findByID(id).isPresent();
    }

    public boolean modify(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
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