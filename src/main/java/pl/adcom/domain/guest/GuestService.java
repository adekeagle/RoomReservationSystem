package pl.adcom.domain.guest;

import java.util.List;

public class GuestService {

    private final static GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, boolean isMale) {
        Gender gender = Gender.FAMALE;

        if (isMale) {
            gender = Gender.MALE;
        }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }

    public List<Guest> getAllGuests() {
        return this.repository.getAll();
    }

    public void saveAll(){
        this.repository.saveAll();
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void removeGuest(int id) {

        this.repository.remove(id);
    }

    public void editGuest(int id, String firstName, String lastName, int age, boolean isMale) {
        Gender gender = Gender.FAMALE;

        if (isMale) {
            gender = Gender.MALE;
        }

        this.repository.edit(id, firstName, lastName, age, gender);

    }

    public Guest getGuestId(int id) {
        return this.repository.findById(id);
    }
}