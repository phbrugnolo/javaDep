package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final String LOG_DIRECTORY = "log";
    private static final String LOG_FILE = LOG_DIRECTORY + "/log.txt";
    private static boolean isLogDirectoryCreated = false;

    private static void createLogDirectory() {
        File logDir = new File(LOG_DIRECTORY);
        if (!logDir.exists()) {
            logDir.mkdir();
        }
        isLogDirectoryCreated = true;
    }

    private static List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de log: " + e.getMessage());
        }
        return lines;
    }

    public static void logAction(String message) {
        if (!isLogDirectoryCreated) {
            createLogDirectory();
        }

        List<String> lines = readFile();
        lines.add(message);

        try (FileWriter writer = new FileWriter(LOG_FILE)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}
