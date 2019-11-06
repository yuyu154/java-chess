package chess.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROOM_ADAPT")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private int status;

    @Column
    private String winner;

    public Room() {
    }

    public Room(int status, String winner) {
        this.status = status;
        this.winner = winner;
    }

    public int getStatus() {
        return status;
    }

    public String getWinner() {
        return winner;
    }

    public Long getId() {
        return id;
    }
}
