package chess.dao;

import chess.domain.entity.Command;
import chess.domain.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandRepositoryTest {

    private CommandRepository commandRepository;

    @BeforeEach
    public void setUp() {
        commandRepository = new CommandRepository();
    }

    @Test
    public void addTest() {
        Room room = new Room(0, null);
        Command command = new Command("1a", "2a", 1, room);
        command = commandRepository.add(command);
        Command actual = commandRepository.find(command.getId());
        assertThat(actual.getOrigin()).isEqualTo(command.getOrigin());
        assertThat(actual.getRoom().getStatus()).isEqualTo(0);
    }
}