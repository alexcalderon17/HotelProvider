package es.deusto.ingenieria.sd.rmi.comun.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean sonFechasValidas(LocalDate fechaEntrada, LocalDate fechaSalida){
        if(fechaEntrada == null || fechaSalida == null){
            return false;
        }
        if(fechaSalida.isBefore(fechaEntrada)){
            return false;
        }
        return true;
    }

    public static LocalDate parsDateConGuiones(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
