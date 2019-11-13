package jpa;

import chess.domain.entity.Command;
import chess.domain.entity.Room;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 학습 테스트
public class TestJpa {

    @Test
    public void test() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpachess");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();
        em.persist(new Room(0, "nada"));
        Room room = em.find(Room.class, 1L);
        em.persist(new Command("a2", "a4", 1L, room));
        Command command = em.find(Command.class, 1L);
        entityTransaction.commit();
    }
}
