package chess.domain.chess;

public interface ValidTurnRule {
    boolean isValidColor(final Piece.Color color);
    Piece.Color switchColor(final Piece.Color color);
}
