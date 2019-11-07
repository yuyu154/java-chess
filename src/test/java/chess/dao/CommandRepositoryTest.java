package chess.dao;

import chess.domain.entity.Command;
import chess.domain.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandRepositoryTest {

    @PersistenceContext
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpachess");
    private CommandRepository commandRepository;
    private EntityManager entityManager;

    private Room room;

    @BeforeEach
    public void setUp() {
        commandRepository = new CommandRepository();
        entityManager = emf.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        room = new Room(0, null);
        entityManager.persist(room);
        entityTransaction.commit();
    }

    @Test
    public void addTest() {
        Command command = new Command("1a", "2a", 1, room);
        command = commandRepository.add(command);
        Command actual = commandRepository.find(command.getId());
        assertThat(actual.getOrigin()).isEqualTo(command.getOrigin());
        assertThat(actual.getRoom().getStatus()).isEqualTo(0);
    }

    @Test
    public void findByRoomTest() {
        Command command1 = new Command("1a", "2a", 1, room);
        command1 = commandRepository.add(command1);
        Command command2 = new Command("7a", "5a", 2, room);
        command2 = commandRepository.add(command2);
        List<Command> actual = commandRepository.findByRoomId(room);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual).contains(command1).contains(command2);
    }

    @Test
    public void deleteAll() {
        Command command1 = new Command("1a", "2a", 1, room);
        commandRepository.add(command1);
        Command command2 = new Command("7a", "5a", 2, room);
        commandRepository.add(command2);
        commandRepository.deleteAll();
        assertThat(commandRepository.findByRoomId(room).size()).isEqualTo(0);
    }

    @Test
    public void findLatestRoundId() {
        Command command1 = new Command("1a", "2a", 1, room);
        commandRepository.add(command1);
        Command command2 = new Command("7a", "5a", 2, room);
        commandRepository.add(command2);
        assertThat(commandRepository.findLatestRoundByRoomId(room)).isEqualTo(2);
    }
}