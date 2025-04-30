/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {
    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }

    public static String formatDate(String dbDate) {
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String ifIsNull(String nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append(" ");
        } else {
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String formatTime(String dbTime) {
        String second = dbTime.substring(4, 5);
        String minute = dbTime.substring(2, 3);
        String hour = dbTime.substring(0, 1);
        if (dbTime != null) {
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
        }
        return dbTime;
    }

    public static String splitDate(String currDate) {
        String year = currDate.substring(6, 10);
        String month = currDate.substring(3, 5);
        String date = currDate.substring(0, 2);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(year) + month + date;
    }

    public static String Time(String dbTime) {
        String second = dbTime.substring(4);
        String minute = dbTime.substring(2, 4);
        String hour = dbTime.substring(0, 2);
        if (dbTime != null) {
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
        }
        return dbTime;
    }

    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
