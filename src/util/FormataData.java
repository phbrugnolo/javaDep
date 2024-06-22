package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormataData {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseData(String dataStr) throws DateTimeParseException, IllegalArgumentException {
        if (!validarFormato(dataStr)) {
            throw new IllegalArgumentException("Formato de data inválido. Use o formato dd/MM/yyyy.");
        }
        return LocalDate.parse(dataStr, FORMATTER);
    }

    public static String formatData(LocalDate data) {
        return data.format(FORMATTER);
    }

    public static boolean validarFormato(String dataStr) {
        try {
            LocalDate.parse(dataStr, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
