package chess.service;

import chess.dao.RoomRepository;
import chess.domain.chess.Piece;
import chess.domain.entity.Room;
import chess.dto.RoomDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomServiceTest {
	private RoomService roomService;

	@BeforeEach
	public void setUp() throws Exception {
		roomService = new RoomService(new RoomRepository());
		roomService.deleteAll();
	}

	@Test
	public void Room이_없을때_예외발생_테스트() {
		assertThrows(IllegalArgumentException.class, () -> roomService.latestId());
	}

	@Test
	public void getLatestIdTest() {
		Room room = roomService.openRoom();
		assertEquals(room.getId(), roomService.latestId());
	}

	@Test
	void updateStatusTest() {
		long roomId = 1;
		Piece.Color winner = Piece.Color.BLACK;
		roomService.openRoom();
		roomService.updateStatus(roomId, winner);

		long expected = 0;
		assertEquals(expected, roomService.findAllByOngoing().size());
	}

	@Test
	void findAllByOngoing() {
		Piece.Color winner = Piece.Color.BLACK;
		Room finishedRoom = roomService.openRoom();
		Room notFinishedRoom = roomService.openRoom();
		roomService.updateStatus(finishedRoom.getId(), winner);

		List<RoomDto> expected = new ArrayList<>();
		RoomDto roomDto = new RoomDto(notFinishedRoom.getId(), notFinishedRoom.getStatus(), notFinishedRoom.getWinner());
		expected.add(roomDto);

		assertEquals(expected, roomService.findAllByOngoing());
	}

	@AfterEach
	public void tearDown() {
		roomService.deleteAll();
	}
}