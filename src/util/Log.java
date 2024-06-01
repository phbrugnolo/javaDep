package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Log {
    private static final String LOG_DIRECTORY = "log";
    private static final String LOG_FILE = LOG_DIRECTORY + "/log.txt";

    static {
        File logDir = new File(LOG_DIRECTORY);
        if (!logDir.exists()) {
            logDir.mkdir();
        }
    }

    public static void logAction(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}