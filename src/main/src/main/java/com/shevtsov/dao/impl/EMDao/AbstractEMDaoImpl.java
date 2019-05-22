package com.shevtsov.dao.impl.EMDao;

import com.shevtsov.exceptions.ObjectNotFoundExeption;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Abstract Dao for Hiber (Entity Manager - EM)
 */
public abstract class AbstractEMDaoImpl<T> {
    EntityManager entityManager;

    abstract Class<T> getEntityClass();

    abstract String queryFrom();

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
//            entityManager.getTransaction().begin();
            T entity = entityManager.find(getEntityClass(), id);
            if (entity != null) {
                optional = Optional.of(entity);
            }
//            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optional;
    }

    public boolean remove(long id) {
        Optional<T> optional = findByID(id);
        if (optional.isPresent()) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(optional.get());
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            entities = entityManager.createQuery(queryFrom(), getEntityClass()).getResultList();
            entityManager.getTransaction().commit();
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