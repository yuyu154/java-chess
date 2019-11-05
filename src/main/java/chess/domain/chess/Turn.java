package chess.domain.chess;

import java.util.Objects;

public class Turn {
    private final ValidTurnRule validTurnRule;
    private Piece.Color color;

    public Turn(final Piece.Color color, final ValidTurnRule validTurnRule) {
        if (!validTurnRule.isValidColor(color)) {
            throw new IllegalArgumentException();
        }
        this.color = color;
        this.validTurnRule = validTurnRule;
    }

    public Piece.Color currentColor() {
        return this.color;
    }

    public void switchColor() {
        this.color = validTurnRule.switchColor(this.color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turn)) return false;
        Turn turn = (Turn) o;
        return color == turn.color &&
                Objects.equals(validTurnRule, turn.validTurnRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, validTurnRule);
    }
}
