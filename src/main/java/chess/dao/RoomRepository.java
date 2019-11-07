package chess.dao;

import chess.domain.entity.Room;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class RoomRepository {

    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpachess");

    public Room add() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Room room = new Room(0, "EMPTY");
        entityManager.persist(room);
        entityTransaction.commit();
        return room;
    }

    public Optional<Room> findById(final long id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Room room = entityManager.createQuery("select r from Room r where r.id = :room", Room.class)
                .setParameter("room", id)
                .getSingleResult();
        entityTransaction.commit();
        return Optional.ofNullable(room);
    }

    public List<Room> findAllByStatus(final Integer status) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Room> rooms = entityManager.createQuery("select r from Room r where r.status = :status", Room.class)
                .setParameter("status", status)
                .getResultList();
        entityTransaction.commit();
        return rooms;
    }

    public Optional<Long> getLatestId() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Long> ids =
                entityManager.createQuery("select r.id from Room r order by r.id desc", Long.class)
                        .getResultList();
        entityTransaction.commit();
        return Optional.ofNullable(ids.get(0));
    }

    public void deleteAll() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery("delete from Room r").executeUpdate();
        entityTransaction.commit();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
