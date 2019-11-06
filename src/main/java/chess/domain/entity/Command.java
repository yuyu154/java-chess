package chess.domain.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(id, command.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
