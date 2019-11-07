package chess.domain.chess;

public class ValidTwoPlayerRule implements ValidTurnRule {
    private static final ValidTwoPlayerRule INSTANCE = new ValidTwoPlayerRule();

    private ValidTwoPlayerRule() {

    }

    public static ValidTwoPlayerRule getInstance() {
        return INSTANCE;
    }

    public Piece.Color switchColor(final Piece.Color color) {
        return color == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    @Override
    public boolean isValidColor(Piece.Color color) {
        return (Piece.Color.EMPTY != color);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
