package pl.adcom.domain.reservation;

import pl.adcom.domain.guest.Guest;
import pl.adcom.domain.guest.GuestService;
import pl.adcom.domain.room.Room;
import pl.adcom.domain.room.RoomService;
import pl.adcom.util.Properties;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationService {

    private final RoomService roomService = new RoomService();
    private final GuestService guestService = new GuestService();
    private final ReservationRepository repository = new ReservationRepository();

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException {

        //TODO handle null room
        Room room = this.roomService.getRoomId(roomId);
        //TODO handle null guest
        Guest guest = this.guestService.getGuestId(guestId);

        LocalDateTime fromWithTime = from.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);

        if(toWithTime.isBefore(fromWithTime)){
            throw new IllegalArgumentException();
        }

        return this.repository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }



    public void readAll() {
        this.repository.readAll();
    }

    public void saveAll() {
        this.repository.saveAll();
    }
}
