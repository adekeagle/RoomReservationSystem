package pl.adcom.ui.text;

import pl.adcom.domain.guest.Guest;
import pl.adcom.domain.guest.GuestService;
import pl.adcom.domain.room.Room;
import pl.adcom.domain.room.RoomService;
import pl.adcom.exceptions.OnlyNumberException;
import pl.adcom.exceptions.WrongOptionException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final RoomService roomService = new RoomService();
    private final GuestService guestService = new GuestService();

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

            System.out.println(newRoom.getInfo());

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

    public void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVersion) {

        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);

        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {

        Scanner input = new Scanner(System.in);

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
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

        while(option != 0) {

            option = getActionFromUser(input);

            if (option == 1) {
                readNewGuestData(input);
            } else if (option == 2) {
                readNewRoomData(input);
            } else if (option == 3) {
                showAllGuests();
            }else if(option == 0){
                System.out.println("Wychodzę z aplikacji");
            } else {
                throw new WrongOptionException("Wrong option in main menu !!!");
            }
        }
    }

    private void showAllGuests() {
        List<Guest> guests = this.guestService.getAllGuests();

        for(Guest guest : guests){
            System.out.println(guest.getInfo());
        }
    }

    private static int getActionFromUser(Scanner in) {
        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Pokaż wszystkich gości.");
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
