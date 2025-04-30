/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static String displayTimeFormat(String time) {
        if (time.trim().equals("null")) {
            time = "";
        } else if (time.trim() == null || time.trim().isEmpty()) {
            time = "";
        } else {
            LocalDateTime datetime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            time = datetime.toString();
        }
        return time;
    }

    public static String displayToDBFormat(String displayDate) {
        String[] dateParts = displayDate.split("/");
        String date = String.format("%02d", Integer.parseInt(dateParts[0]));
        String month = String.format("%02d", Integer.parseInt(dateParts[1]));
        String year = String.format("%04d", Integer.parseInt(dateParts[2]));
        return String.valueOf(year) + month + date;
    }

    public static String DBToDisplayFormat(String dbDate) {
        if (dbDate == null || dbDate.isEmpty()) {
            return "-";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        return String.valueOf(date) + "/" + month + "/" + year;
    }
}
