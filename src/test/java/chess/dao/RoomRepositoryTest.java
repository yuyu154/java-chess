package chess.dao;

import chess.domain.entity.Room;
import chess.domain.entity.RoomStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoomRepositoryTest {
    private RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        roomRepository = new RoomRepository();
    }

    @Test
    void findById() {
        Room room = roomRepository.add();
        Room actual = roomRepository.findById(room.getId())
                .get();
        assertThat(actual).isEqualTo(room);
    }

    @Test
    void findAllByStatus() {
        Room room1 = roomRepository.add();
        Room room2 = roomRepository.add();
        List<Room> actual = roomRepository.findAllByStatus(RoomStatus.AVAILABLE);
        assertThat(actual).contains(room1).contains(room2);
    }

    @Test
    void getLatestId() {
        Room room = roomRepository.add();
        assertThat(roomRepository.getLatestId()).isEqualTo(room.getId());
    }
}