package chess.domain;

import chess.domain.chess.Piece;
import chess.domain.chess.Turn;
import chess.domain.chess.ValidTurnRule;
import chess.domain.chess.ValidTwoPlayerRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {
    private Turn turn;
    private ValidTurnRule testValidRule;

    @BeforeEach
    public void setUp() {
        testValidRule = ValidTwoPlayerRule.getInstance();
    }

    @Test
    public void 동시성_테스트() {
        turn = new Turn(Piece.Color.WHITE, testValidRule);
        assertEquals(new Turn(Piece.Color.WHITE, testValidRule), turn);
    }

    @Test
    public void 동시성_테스트_다를때() {
        turn = new Turn(Piece.Color.WHITE, testValidRule);
        assertNotEquals(new Turn(Piece.Color.BLACK, testValidRule), turn);
    }

    @Test
    public void EMPTY_인자일때_예외_발생() {
        assertThrows(IllegalArgumentException.class, () ->
                new Turn(Piece.Color.EMPTY, testValidRule)
        );
    }

    @Test
    public void currentColor() {
        turn = new Turn(Piece.Color.WHITE, testValidRule);
        assertEquals(Piece.Color.WHITE, turn.currentColor());
    }

    @Test
    public void changeColorTest() {
        turn = new Turn(Piece.Color.WHITE, testValidRule);
        turn.switchColor();
        assertEquals(Piece.Color.BLACK, turn.currentColor());
    }
}