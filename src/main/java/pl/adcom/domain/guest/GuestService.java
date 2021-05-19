package pl.adcom;

public class GuestService {

    GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption){
        Gender gender = Gender.FAMALE;

        if(genderOption==1){
            gender = Gender.MALE;
        }else if(genderOption == 2){
            gender = Gender.FAMALE;
        }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }
}