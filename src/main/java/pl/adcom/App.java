package pl.adcom;

import pl.adcom.ui.text.TextUI;

public class App {

    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {

        String hotelName = "Overlook";
        int systemVersion = 2;
        boolean isDeveloperVersion = true;

        textUI.showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        textUI.showMainMenu();

    }

//    private static pl.adcom.domain.guest.Gender chooseGenderType(Scanner in) {
//        System.out.println("Podaj płeć: ");
//        System.out.println("\t1 - Kobieta");
//        System.out.println("\t2 - Mężczyzna");
//
//        pl.adcom.domain.guest.Gender gender = pl.adcom.domain.guest.Gender.MALE;
//
//        int genderOption = in.nextInt();
//
//        if (genderOption == 1) {
//            gender = pl.adcom.domain.guest.Gender.FAMALE;
//        } else if (genderOption == 2) {
//            gender = pl.adcom.domain.guest.Gender.MALE;
//        } else {
//            throw new pl.adcom.exceptions.WrongOptionException("Wrong option gender !!!");
//        }
//
//        return gender;
//    }
}