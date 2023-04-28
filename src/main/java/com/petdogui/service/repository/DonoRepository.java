package com.petdogui.repository;

import com.petdogui.model.Dono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DonoRepository {

    private static final String PERSISTENCE_UNIT = "petdogui";
    private EntityManagerFactory entityManagerFactory;

    public DonoRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public Dono save(Dono dono) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(dono);
        entityManager.getTransaction().commit();
        entityManager.close();
        return dono;
    }

    public Dono update(Dono dono) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Dono updatedDono = entityManager.merge(dono);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedDono;
    }

    public void delete(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Dono dono = entityManager.find(Dono.class, id);

        if (dono != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(dono);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    public Dono findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Dono dono = entityManager.find(Dono.class, id);
        entityManager.close();
        return dono;
    }

    public List<Dono> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Dono> query = entityManager.createQuery("SELECT d FROM Dono d", Dono.class);
        List<Dono> donos = query.getResultList();
        entityManager.close();
        return donos;
    }
}