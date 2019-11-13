package chess.service;

import chess.dao.CommandDao;
import chess.dao.CommandRepository;
import chess.dao.RoomRepository;
import chess.domain.chess.*;
import chess.domain.entity.Command;
import chess.domain.entity.Room;
import chess.dto.CommandDto;
import chess.utils.PositionConverter;

import java.util.List;

public class ChessService {
	private CommandDao commandDao;
	private CommandRepository commandRepository;
	private RoomRepository roomRepository;

	public ChessService(final CommandDao commandDao) {
		this.commandDao = commandDao;
	}

	public ChessService(CommandRepository commandRepository, RoomRepository roomRepository) {
		this.commandRepository = commandRepository;
		this.roomRepository = roomRepository;
	}

	public Game initGame() {
		Board board = new Board(BoardGenerator.generate());
		return Game.of(board, Piece.Color.WHITE);
	}

	public boolean action(final Game game, final String originInput, final String targetInput, final long roomId) {
		final Position origin = convertToPosition(originInput);
		final Position target = convertToPosition(targetInput);
		if (game.action(origin, target)) {
//			CommandDto commandDto = new CommandDto();
//			commandDto.setOrigin(origin.toString());
//			commandDto.setTarget(target.toString());
//			commandDto.setRoomId(roomId);
//			commandDto.setRound(commandDao.findLatestRoundByRoomId(roomId));
//			commandDao.add(commandDto);
			Room room = roomRepository.findById(roomId)
					.orElseThrow(IllegalArgumentException::new);
			commandRepository
					.add(new Command(originInput, targetInput, commandRepository.findLatestRoundByRoomId(room), room));
			return true;
		}
		return false;
	}

	public Game load(final long roomId) {
		Board board = new Board(BoardGenerator.generate());
		Game game = Game.of(board, Piece.Color.WHITE);
		List<CommandDto> commandDtos = findByRoomId(roomId);
		for (final CommandDto commandDto : commandDtos) {
			Position origin = convertToPosition(commandDto.getOrigin());
			Position target = convertToPosition(commandDto.getTarget());
			game.action(origin, target);
		}
		return game;
	}

	private List<CommandDto> findByRoomId(final long roomId) {
		return commandDao.findByRoomId(roomId);
	}

	private Position convertToPosition(final String input) {
		return PositionConverter.convert(input);
	}

	public void deleteAll() {
		commandDao.deleteAll();
	}

	public List<Square> getSquares(final Game game) {
		return game.values();
	}
}
