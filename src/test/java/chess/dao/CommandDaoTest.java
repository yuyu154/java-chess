package chess.dao;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.domain.chess.Position;
import chess.dto.CommandDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandDaoTest {
	private CommandDao commandDao;
	private RoomDao roomDao;
	private long roomId = 1L;

	@BeforeEach
	public void setUp() throws Exception {
		DbConnector dbConnector = new DbConnector(DataSource.getInstance());
		commandDao = CommandDao.from(dbConnector);
		roomDao = RoomDao.from(dbConnector);
		new TableCreator(dbConnector).create();
		commandDao.deleteAll();
		roomDao.deleteAll();
	}

	@Test
	public void addTest() {
		Position origin = Position.of("1", "a");
		Position target = Position.of("2", "a");
		long round = 1;
		long room_id = 1;
		CommandDto commandDto = new CommandDto();
		commandDto.setTarget(origin.toString());
		commandDto.setOrigin(target.toString());
		commandDto.setRoomId(room_id);
		commandDto.setRound(round);

		assertDoesNotThrow(() -> commandDao.add(commandDto));
	}

	@Test
	public void findByRoomIdTest() {
		final long roundId = 100;
		roomDao.add();
		CommandDto commandDto = new CommandDto();
		commandDto.setTarget("a1");
		commandDto.setOrigin("a2");
		commandDto.setRoomId(roomId);
		commandDto.setRound(roundId);
		commandDao.add(commandDto);

		List<CommandDto> commandDtos = commandDao.findByRoomId(roomId);
		assertThat(commandDtos.size()).isEqualTo(1);
	}

	@Test
	public void findLatestRoundByRoomIdTest() {
		roomDao.add();
		
		final int firstRoundId = 1;
		CommandDto commandDto = new CommandDto();
		commandDto.setTarget("a1");
		commandDto.setOrigin("a2");
		commandDto.setRoomId(roomId);
		commandDto.setRound(firstRoundId);
		commandDao.add(commandDto);

		final int expected = 5;
		commandDto = new CommandDto();
		commandDto.setTarget("a1");
		commandDto.setOrigin("a2");
		commandDto.setRoomId(roomId);
		commandDto.setRound(expected);
		commandDao.add(commandDto);

		assertEquals(expected, commandDao.findLatestRoundByRoomId(roomId));
	}

	@Test
	public void DB에_아무것도_없을때_findLatestRoundByRoomIdTest() {
		final int expected = 1;
		assertEquals(expected, commandDao.findLatestRoundByRoomId(roomId));
	}

	@AfterEach
	public void tearDown() {
		commandDao.deleteAll();
		roomDao.deleteAll();
	}
}