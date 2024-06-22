package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormataData {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseData(String dataStr) throws DateTimeParseException {
        return LocalDate.parse(dataStr, FORMATTER);
    }

    public static String formatData(LocalDate data) {
        return data.format(FORMATTER);
    }
}
