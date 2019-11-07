package chess.dao;

import chess.domain.entity.Command;
import chess.domain.entity.Room;

import javax.persistence.*;
import java.util.List;

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

    public Command find(long userId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Command command = entityManager.find(Command.class, userId);
        entityTransaction.commit();
        return command;
    }

    public List<Command> findByRoomId(Room room) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Command> commands =
                entityManager.createQuery("select c from Command c join fetch c.room where c.room = :room"
                        , Command.class)
                        .setParameter("room", room)
                        .getResultList();
        entityTransaction.commit();
        return commands;
    }

    public void deleteAll() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery("delete from Command c").executeUpdate();
        entityTransaction.commit();
    }

    public long findLatestRoundByRoomId(Room room) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Long> ids =
                entityManager.createQuery("select c.round from Command c where c.room = :room" +
                        " order by c.id desc", Long.class)
                        .setParameter("room", room)
                .getResultList();
        entityTransaction.commit();
        return ids.get(0);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
