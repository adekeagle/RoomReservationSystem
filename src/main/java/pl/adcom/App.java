public class App {

    public static TextUI textUI = new TextUI();

    public static void main(String[] args) {

        String hotelName = "Overlook";
        int systemVersion = 2;
        boolean isDeveloperVersion = true;

        textUI.showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        textUI.showMainMenu();

    }

//    private static Gender chooseGenderType(Scanner in) {
//        System.out.println("Podaj płeć: ");
//        System.out.println("\t1 - Kobieta");
//        System.out.println("\t2 - Mężczyzna");
//
//        Gender gender = Gender.MALE;
//
//        int genderOption = in.nextInt();
//
//        if (genderOption == 1) {
//            gender = Gender.FAMALE;
//        } else if (genderOption == 2) {
//            gender = Gender.MALE;
//        } else {
//            throw new WrongOptionException("Wrong option gender !!!");
//        }
//
//        return gender;
//    }
}