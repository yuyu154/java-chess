package chess.domain.entity;

import org.hibernate.annotations.Cascade;

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

    @ManyToOne(targetEntity = Room.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;

    public Command() {
    }

    public Command(String origin, String target, long round, Room room) {
        this.origin = origin;
        this.target = target;
        this.round = round;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTarget() {
        return target;
    }

    public Long getRound() {
        return round;
    }

    public Room getRoom() {
        return room;
    }
}
