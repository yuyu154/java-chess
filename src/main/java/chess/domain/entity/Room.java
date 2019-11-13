package chess.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ROOM_ADAPT")
public class Room {
    public static final String NO_WINNER = "EMPTY";

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @Enumerated
    private RoomStatus status;

    @Column
    private String winner;

    protected Room() {
    }

    public Room(RoomStatus status, String winner) {
        this.status = status;
        this.winner = winner;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public String getWinner() {
        return winner;
    }

    public Long getId() {
        return id;
    }

    public void checkWinner(String name) {
        if (this.winner.equals(NO_WINNER)) {
            this.status = RoomStatus.UNAVAILABLE;
            this.winner = name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
