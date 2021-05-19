package pl.adcom.domain.room;

public class RoomRepository {

    Room createNewRoom(int number, BedType[] bedTypes){

        return new Room(number, bedTypes);
    }
}
