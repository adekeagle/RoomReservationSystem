package pl.adcom.domain.room;

import pl.adcom.exceptions.WrongOptionException;

import java.util.List;

public class RoomService {

    private final static RoomRepository repository = new RoomRepository();

    public Room createNewRoom(int number, int[] bedTypesOptions) {

        BedType[] bedTypes = new BedType[bedTypesOptions.length];

        for (int i = 0; i < bedTypesOptions.length; i++) {

            BedType bedType;

            if (bedTypesOptions[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesOptions[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesOptions[i] == 3) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option bedType!!!");
            }


            bedTypes[i] = bedType;

        }

        return repository.createNewRoom(number, bedTypes);
    }

    public List<Room> getAllRooms() {
        return this.repository.getAllRooms();
    }

    public void saveAll(){
        this.repository.saveAll();
    }

    public void readAll(){
        this.repository.readAll();
    }

    public void editRoom(int id, int number, int[] bedTypesOptions) {

        BedType[] bedTypes = new BedType[bedTypesOptions.length];

        for (int i = 0; i < bedTypesOptions.length; i++) {

            BedType bedType;

            if (bedTypesOptions[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesOptions[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesOptions[i] == 3) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option bedType!!!");
            }


            bedTypes[i] = bedType;
        }
        this.repository.edit(id, number, bedTypes);
    }

    public void removeRoom(int id) {
        this.repository.remove(id);
    }

    public Room getRoomId(int roomId) {
        return this.repository.getById(roomId);
    }
}
