package pl.adcom.domain.reservation;

import pl.adcom.domain.guest.Guest;
import pl.adcom.domain.guest.GuestService;
import pl.adcom.domain.room.Room;
import pl.adcom.domain.room.RoomService;
import pl.adcom.exceptions.PersistanceToFileException;
import pl.adcom.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    List<Reservation> reservations = new ArrayList<>();
    RoomService roomService = new RoomService();
    GuestService guestService = new GuestService();

    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(this.findNewId(), room, guest, from, to);
        this.reservations.add(res);
        return res;
    }

    private int findNewId() {

        int max = 0;

        for (Reservation reservation : this.reservations) {
            if (reservation.getId() > max){
                max = reservation.getId();
            }
        }
        return max + 1;
    }

    public void readAll() {
        String name = "reservation.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        if (!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] reservationsAsString = data.split(System.getProperty("line.separator"));

            for (String reservationAsString : reservationsAsString) {
                String[] reservationData = reservationAsString.split(",");
                if(reservationData[0]==null | reservationData[0].trim().isEmpty()){
                    continue;
                }
                int id = Integer.parseInt(reservationData[0]);
                int roomId = Integer.parseInt(reservationData[1]);
                int guestId = Integer.parseInt(reservationData[2]);
                String fromAsString = reservationData[3];
                String toAsString = reservationData[4];
                //TODO handle null guest/room
                addExistingReservation(id, this.roomService.getRoomId(roomId), this.guestService.getGuestId(guestId), LocalDateTime.parse(fromAsString), LocalDateTime.parse(toAsString));
            }

        } catch (IOException e) {
            throw new PersistanceToFileException(file.toString(), "read", "reservation data");
        }
    }

    private void addExistingReservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(id, room, guest, from, to);
        this.reservations.add(res);
    }

    public void saveAll() {
        String name = "reservation.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder();

        for (Reservation reservation : reservations) {
            sb.append(reservation.toCSV());
        }

        try {
//            final Path reservation_system_dir = Properties.DATA_DIRECTORY;
//            if (!Files.isDirectory(reservation_system_dir)) {
//                Files.createDirectory(reservation_system_dir);
//            }

            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistanceToFileException(file.toString(), "write", "reservations data");
        }
    }
}
