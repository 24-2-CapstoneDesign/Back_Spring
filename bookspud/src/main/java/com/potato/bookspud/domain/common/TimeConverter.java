package com.potato.bookspud.domain.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverter {
    public static String transferLocalDateTime(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
