package pl.adcom.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class Properties {

    public static final String HOTEL_NAME = "Overlook";
    public static final int SYSTEM_VERSION = 2;
    public static final boolean IS_DEVELOPER_VERSION = true;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;

    public static final Path DATA_DIRECTORY = Paths.get(
            "C:\\Users\\Backend\\reservation_system",
            "reservation_system"
    );

    public static void createDataDirectory() throws IOException {

        if (!Files.isDirectory(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
    }
}
