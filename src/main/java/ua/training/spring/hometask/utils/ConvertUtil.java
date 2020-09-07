package ua.training.spring.hometask.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertUtil {

    public static LocalDateTime convertDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(dateTime, formatter);
    }
}
