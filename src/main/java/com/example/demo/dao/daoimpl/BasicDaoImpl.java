package com.example.demo.dao.daoimpl;

import com.example.demo.dao.BasicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

@Repository
public class BasicDaoImpl<T> implements BasicDao<T> {


    // Вместо   session в новом хибернейте используем ентити читай мануалы к нему
    @Autowired
    EntityManagerFactory entityManagerFactory;

    private Class<T> type;


    public BasicDaoImpl(Class<T> type) {
        this.type = type;
    }

    public BasicDaoImpl() {
    }

    @Override
    public T getById(long id) {
        T object = null;
        try {
            object = entityManagerFactory.createEntityManager().find(type, id);

        } catch (OptimisticLockException e) {
            e.printStackTrace();
        } finally {
            return object;
        }

    }

    @Transactional
    @Override
    public T create(T object) {
        T created = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
            created = object;
        } catch (OptimisticLockException e) {
            e.printStackTrace();
        } finally {
            return created;
        }

    }

    @Transactional
    @Override
    public boolean delete(T object) {
        boolean flag = false;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(object);
            entityManager.getTransaction().commit();
            flag = true;
        }catch (OptimisticLockException e){
            e.printStackTrace();
        }
        finally {
            return flag;
        }
    }

    @Transactional
    @Override
    public boolean update(T object) {
        boolean flag = false;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
            flag = true;
        }catch (OptimisticLockException e){
            e.printStackTrace();
        }
        finally {
            return flag;
        }
    }

    @Transactional
    @Override
    public List<T> getAll() {
        System.out.println(4);
        List<T> list = Collections.emptyList();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);
            // criteriaQuery.where(criteriaBuilder.equal(root.get("login"),login));
            List<T> users = entityManager.createQuery(criteriaQuery).getResultList();
            System.out.println(users.size());

            list = users;
        } catch (OptimisticLockException e) {
            e.printStackTrace();

        } finally {
            return list;
        }

    }


}
