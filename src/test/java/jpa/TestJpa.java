package jpa;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestJpa {

    @Test
    public void test() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpachess");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        entityTransaction.commit();
    }
}
