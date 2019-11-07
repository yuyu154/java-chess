package chess.dao;

import chess.domain.entity.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryTest {

    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpachess");

    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        roomRepository = new RoomRepository();
        roomRepository.deleteAll();
    }

    @Test
    void add() {
        roomRepository.add();
        roomRepository.add();
        assertThat(roomRepository.getLatestId().get()).isEqualTo(2L);
    }

    @Test
    void findById() {
        roomRepository.add();
        Room room = roomRepository.add();
        Room actual = roomRepository.findById(room.getId())
                .get();
        assertThat(actual).isEqualTo(room);
    }

    @Test
    void findAllByStatus() {
        Room room1 = roomRepository.add();
        Room room2 = roomRepository.add();
        List<Room> actual = roomRepository.findAllByStatus(0);
        assertThat(actual).contains(room1).contains(room2);
    }

    @Test
    void getLatestId() {
        roomRepository.add();
        roomRepository.add();
        assertThat(roomRepository.getLatestId().get()).isEqualTo(2L);
    }

    @Test
    void deleteAll() {
        roomRepository.add();
        roomRepository.add();
        roomRepository.deleteAll();
        assertThat(roomRepository.findAllByStatus(0)).isEqualTo(0);
    }

    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
    }
}