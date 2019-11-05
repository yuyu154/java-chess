package chess.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMMAND_ADAPT")
public class Command {

    @Id
    @Column(name = "column_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "target")
    private String target;

    @Column(name = "round")
    private Long round;

    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    public Command(String origin, String target, long round, Room room) {
        this.origin = origin;
        this.target = target;
        this.round = round;
        this.room = room;
    }
}
