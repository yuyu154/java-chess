package chess.utils;

import chess.domain.Position;

public class PositionConverter {
	private static final String DELIMITER = "";
	private static final int POSITION_LENGTH = 2;
	private static final int COLUMN_INDEX = 0;
	private static final int ROW_INDEX = 1;

	public static Position convert(final String input) {
		String[] position = input.split(DELIMITER);
		if (position.length != POSITION_LENGTH) {
			throw new IllegalArgumentException();
		}
		String col = position[COLUMN_INDEX];
		String row = position[ROW_INDEX];
		return Position.of(row, col);
	}
}
