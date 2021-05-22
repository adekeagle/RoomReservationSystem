package pl.adcom.ui.text;

import pl.adcom.domain.guest.Guest;
import pl.adcom.domain.guest.GuestService;
import pl.adcom.domain.reservation.Reservation;
import pl.adcom.domain.reservation.ReservationService;
import pl.adcom.domain.room.Room;
import pl.adcom.domain.room.RoomService;
import pl.adcom.exceptions.OnlyNumberException;
import pl.adcom.exceptions.PersistanceToFileException;
import pl.adcom.exceptions.WrongOptionException;
import pl.adcom.util.Properties;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final RoomService roomService = new RoomService();
    private final GuestService guestService = new GuestService();
    private final ReservationService reservationService = new ReservationService();

    private void readNewGuestData(Scanner input) {
        System.out.println("Wybrano opcję 1.");

        try {
            System.out.print("Podaj imię gośćia: ");
            String firstName = input.next();
            System.out.print("Podaj nazwisko gościa: ");
            String lastName = input.next();
            System.out.print("Podaj wiek gościa: ");
            int age = input.nextInt();
            System.out.println("Podaj płeć : 1. Mężczyzna, 2. Kobieta");
            int genderOption = input.nextInt();

            if (genderOption != 1 && genderOption != 2) {
                throw new WrongOptionException("Wrong option in gender selection");
            }

            boolean isMale = genderOption == 1;

            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, isMale);

            System.out.println("Dodano nowego gościa: " + newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when choosing gender");
        }
    }

    private void readNewRoomData(Scanner input) {
        System.out.println("Tworzymy nowy pokój: ");
        try {
            System.out.print("Podaj nr pokoju: ");
            int number = input.nextInt();

            int[] bedType = chooseBedType(input);

            Room newRoom = roomService.createNewRoom(number, bedType);

            System.out.println("Dodano nowy pokój : " + newRoom.getInfo());

        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room");
        }
    }

    private int[] chooseBedType(Scanner in) {
        System.out.print("Ile łóżek w pokoju ? ");

        int numberBed;
        numberBed = in.nextInt();

        int[] bedTypes = new int[numberBed];

        for (int i = 0; i < numberBed; i++) {

            System.out.println("Typy łóżek: ");
            System.out.println("\t1 Pojedyńcze: ");
            System.out.println("\t2 Podwójne: ");
            System.out.println("\t3 Królewskie: ");

            int bedTypeOption = in.nextInt();

            bedTypes[i] = bedTypeOption;

        }

        return bedTypes;
    }

    public void showSystemInfo() {

        System.out.print("Witam w systemie rezerwacji dla hotelu " + Properties.HOTEL_NAME);
        System.out.println("Aktualna wersja systemu: " + Properties.SYSTEM_VERSION);
        System.out.println("Wersja developerska: " + Properties.IS_DEVELOPER_VERSION);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {

        System.out.println("Trwa ładowanie danych ...");
        this.guestService.readAll();
        this.roomService.readAll();
        this.reservationService.readAll();

        Scanner input = new Scanner(System.in);

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException | PersistanceToFileException e) {
            System.out.println("Wystąpił nieoczekiwany błąd!!!");
            System.out.println("Kod błędu : " + e.getCode());
            System.out.println("Komunikat błędu : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Wystąpił błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu : " + e.getMessage());
        }

    }

    private void performAction(Scanner input) {
        int option = -1;

        while (option != 0) {

            option = getActionFromUser(input);

            if (option == 1) {
                readNewGuestData(input);
            } else if (option == 2) {
                readNewRoomData(input);
            } else if (option == 3) {
                showAllGuests();
            } else if (option == 4) {
                showAllRomms();
            } else if (option == 5) {
                removeGuest(input);
            } else if (option == 6) {
                editGuest(input);
            }else if(option == 7) {
                removeRoom(input);
            }else if(option == 8) {
                editRoom(input);
            }else if(option == 9){
                createReservation(input);
            } else if (option == 0) {
                System.out.println("Wychodzę z aplikacji. Zapisuję dane");
                this.guestService.saveAll();
                this.roomService.saveAll();
                this.reservationService.saveAll();
            } else {
                throw new WrongOptionException("Wrong option in main menu !!!");
            }
        }
    }

    private void createReservation(Scanner input) {
        System.out.println("Od kiedy (DD.MM.YYYY) : ");
        String fromAsString = input.next();
        LocalDate from = LocalDate.parse(fromAsString, Properties.DATE_FORMATTER);
        System.out.println("Do kiedy (DD.MM.YYYY) : ");
        String toAsString = input.next();
        LocalDate to = LocalDate.parse(toAsString, Properties.DATE_FORMATTER);
        System.out.println("ID Pokoju : ");
        int roomId = input.nextInt();
        System.out.println("ID Gościa : ");
        int guestId = input.nextInt();

        //TODO handle null reservation
        try{
            Reservation res = this.reservationService.createNewReservation(from, to, roomId, guestId);
            if (res != null) {
                System.out.println("Usało się stworzyć rezerwację");
            }
        }catch (IllegalArgumentException ex){
            System.out.println("Data zakończenia rezerwacji nie może być wcześniejsza niż data jej rozpoczęcia");
        }
    }

    private void editRoom(Scanner input) {
        System.out.println("Podaj ID pokoju do edycji");

        try{
            int id = input.nextInt();
            System.out.print("Podaj nr pokoju: ");
            int number = input.nextInt();
            int[] bedType = chooseBedType(input);
            roomService.editRoom(id, number, bedType);
        }catch (InputMismatchException e){
            throw new OnlyNumberException("Use number when editing room");
        }
    }

    private void removeRoom(Scanner input) {
        System.out.println("Podaj ID pokoju do usunięcia");

        try{
            int id = input.nextInt();

            this.roomService.removeRoom(id);
        }catch (InputMismatchException e){
            throw new OnlyNumberException("Use number when removing room");
        }
    }

    private void editGuest(Scanner input) {
        System.out.println("Podaj ID gościa do edycji");

        try {
            int id = input.nextInt();

            System.out.println("Podaj imię gośćia: ");
            String firstName = input.next();
            System.out.print("Podaj nazwisko gościa: ");
            String lastName = input.next();
            System.out.print("Podaj wiek gościa: ");
            int age = input.nextInt();
            System.out.println("Podaj płeć : 1. Mężczyzna, 2. Kobieta");
            int genderOption = input.nextInt();

            if (genderOption != 1 && genderOption != 2) {
                throw new WrongOptionException("Wrong option in gender selection");
            }

            boolean isMale = genderOption == 1;

            guestService.editGuest(id, firstName, lastName, age, isMale);

        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use number when editing guest");
        }
    }

    private void removeGuest(Scanner input) {
        System.out.println("Podaj id gościa do usunięcia");

        try {
            int id = input.nextInt();

            this.guestService.removeGuest(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when inserting ID");
        }
    }

    private void showAllRomms() {
        List<Room> rooms = this.roomService.getAllRooms();

        for (Room room : rooms) {
            System.out.println(room.getInfo());
        }
    }

    private void showAllGuests() {
        List<Guest> guests = this.guestService.getAllGuests();

        for (Guest guest : guests) {
            System.out.println(guest.getInfo());
        }
    }

    private static int getActionFromUser(Scanner in) {
        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Pokaż wszystkich gości.");
        System.out.println("4. Pokaż wszystkie pokoje.");
        System.out.println("5. Usuń gościa.");
        System.out.println("6. Edytuj dane gościa.");
        System.out.println("7. Usuń pokój.");
        System.out.println("8. Edytuj pokój.");
        System.out.println("9. Stwórz rezerwację.");
        System.out.println("0. Wyjście z programu.");
        System.out.println("Wybierz opcję: ");

        int actionNumber;

        try {
            actionNumber = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers in main menu");
        }

        return actionNumber;
    }
}
