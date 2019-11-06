package chess.dao;

import chess.domain.entity.Command;

import javax.persistence.*;

public class CommandRepository {

    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpachess");

    public Command add(final Command command) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(command);
        entityTransaction.commit();
        return command;
    }

    public Command find(long id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Command.class, id);
    }
}
