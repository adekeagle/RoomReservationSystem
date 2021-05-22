package pl.adcom;

import pl.adcom.exceptions.PersistanceToFileException;
import pl.adcom.ui.text.TextUI;
import pl.adcom.util.Properties;

import java.io.IOException;

public class App {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new PersistanceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }
        textUI.showSystemInfo();
        textUI.showMainMenu();

    }
}