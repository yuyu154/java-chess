package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
	private final Board board;
	private Turn turn;

	private Game(final Board board, final Piece.Color color) {
		this.board = board;
		this.turn = new Turn(color, ValidTwoPlayerRule.getInstance());
	}

	public static Game of(Board board, Piece.Color color) {
		return new Game(board, color);
	}

	public boolean action(Position origin, Position target) {
		if (board.isSameColor(origin, turn.currentColor()) && board.action(origin, target)) {
			changeColor();
			return true;
		}
		return false;
	}

	private void changeColor() {
		this.turn.switchColor();
	}

	public Piece.Color currentColor() {
		return turn.currentColor();
	}

	public double scoreOfColor(final Piece.Color color) {
		ScoreCalculator scoreCalculator = ScoreCalculator.getInstance();
		return scoreCalculator.scoreOfColor(values(), color);
	}

	public List<Square> values() {
		return new ArrayList<>(board.values());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Game)) return false;
		Game game = (Game) o;
		return Objects.equals(board, game.board) &&
				Objects.equals(turn, game.turn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(board, turn);
	}
}
