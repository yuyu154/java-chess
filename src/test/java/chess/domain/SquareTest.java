package chess.domain;

import chess.domain.PieceImpl.Empty;
import chess.domain.PieceImpl.Rook;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {
    @Test
    public void 동치성_테스트() {
        Position position = Position.of("1", "a");
        Piece rook = Empty.getInstance();
        Square square1 = Square.of(position, rook);
        Square square2 = Square.of(position, rook);

        assertThat(square1.equals(square2)).isTrue();
    }

    @Test
    public void 룩에서_빈칸_이동_테스트() {
        Position position1 = Position.of("1", "a");
        Piece rook = Rook.getInstance();
        Square origin = Square.of(position1, rook);

        Position position2 = Position.of("1", "h");
        Piece empty = Empty.getInstance();
        Square target = Square.of(position2, empty);

        Square expectedOrigin = Square.of(position1, empty);
        Square expectedTarget = Square.of(position2, rook);


        assertThat(origin.movePiece(target)).isTrue();
        assertThat(origin.equals(expectedOrigin)).isTrue();
        assertThat(target.equals(expectedTarget)).isTrue();
    }

    @Test
    public void 빈칸에서_빈칸으로_이동할때_false반환_확인() {
        Position position1 = Position.of("1", "a");
        Piece empty = Empty.getInstance();
        Square origin = Square.of(position1, empty);

        Position position2 = Position.of("1", "h");
        Square target = Square.of(position2, empty);

        Square expected = Square.of(position1, empty);

        assertThat(origin.movePiece(target)).isFalse();
        assertThat(origin.equals(expected)).isTrue();
    }

    @Test
    public void 말의_이동_규칙을_위배했을떄() {
        Position position1 = Position.of("1", "a");
        Piece rook = Rook.getInstance();
        Square origin = Square.of(position1, rook);

        Position position2 = Position.of("2", "h");
        Piece empty = Empty.getInstance();
        Square target = Square.of(position2, empty);

        Square expectedOrigin = Square.of(position1, rook);
        Square expectedTarget = Square.of(position2, empty);


        assertThat(origin.movePiece(target)).isFalse();
        assertThat(origin.equals(expectedOrigin)).isTrue();
        assertThat(target.equals(expectedTarget)).isTrue();
    }
}
