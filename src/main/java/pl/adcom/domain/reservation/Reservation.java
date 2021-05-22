package pl.adcom.domain.reservation;

import pl.adcom.domain.guest.Guest;
import pl.adcom.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final int Id;
    private final Room room;
    private final Guest guest;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Reservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        this.Id = id;
        this.room = room;
        this.guest = guest;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return Id;
    }

    String toCSV() {
        return String.format("%s,%s,%s,%s,%s,%s",
                this.Id,
                this.room.getId(),
                this.guest.getId(),
                this.from.toString(),
                this.to.toString(),
                System.getProperty("line.separator"));
    }
}
