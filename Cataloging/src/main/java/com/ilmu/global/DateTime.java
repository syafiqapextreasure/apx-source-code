/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }

    public static String formatDate(String dbDate) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date convertedCurrentDate = sdf.parse(dbDate);
            SimpleDateFormat newFormatter = new SimpleDateFormat("dd/MM/yyyy");
            date = newFormatter.format(convertedCurrentDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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

    public static String ifTimeNull(String time) {
        StringBuilder result = new StringBuilder();
        if (time == null) {
            result.append(" ");
        } else {
            result.append(DateTime.Time(time));
        }
        return result.toString();
    }

    public static String ifDateNull(String date) {
        StringBuilder result = new StringBuilder();
        System.out.println(date);
        if (date == null) {
            result.append(" ");
        } else {
            result.append(DateTime.formatDate(date));
        }
        System.out.println(date);
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
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
        try {
            Date convertedCurrentTime = sdf.parse(dbTime);
            SimpleDateFormat newFormatter = new SimpleDateFormat("hh:mm:ss aa");
            time = newFormatter.format(convertedCurrentTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String displayToDBFormat(String displayDate) {
        String date = "";
        String month = "";
        String year = "";
        String dates = "";
        if (!displayDate.isEmpty() || displayDate != "") {
            String[] dateParts = displayDate.split("/");
            date = String.format("%02d", Integer.parseInt(dateParts[0]));
            month = String.format("%02d", Integer.parseInt(dateParts[1]));
            year = String.format("%04d", Integer.parseInt(dateParts[2]));
            dates = String.valueOf(year) + month + date;
        } else {
            dates = "";
        }
        return dates;
    }

    public static String timeToDBFormat(String displayTime) {
        System.out.println(String.valueOf(displayTime) + "Date");
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss aa");
        SimpleDateFormat outputformat = new SimpleDateFormat("HHmmss");
        Date date = null;
        String output = null;
        try {
            if (displayTime != null) {
                date = df.parse(displayTime);
                output = outputformat.format(date);
            } else {
                output = "";
            }
        }
        catch (ParseException pe) {
            pe.printStackTrace();
        }
        return output;
    }

    public static String DBToDisplayFormat(String dbDate) {
        if (dbDate == null || dbDate.isEmpty()) {
            return " ";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(date) + "/" + month + "/" + year;
    }
}
