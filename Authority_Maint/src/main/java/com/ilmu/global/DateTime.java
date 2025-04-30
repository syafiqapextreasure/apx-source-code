/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static String getSysTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getSysTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }

    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodaySystemTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
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
        System.out.println("DB" + dbTime.trim() + "ss");
        if (dbTime.trim() != null && !dbTime.trim().isEmpty()) {
            String second = dbTime.substring(4, 5);
            String minute = dbTime.substring(2, 3);
            String hour = dbTime.substring(0, 1);
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
        } else {
            dbTime = "";
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
            SimpleDateFormat newFormatter = new SimpleDateFormat("hh:mm:ss a");
            time = newFormatter.format(convertedCurrentTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String displayToDBFormat(String displayDate) {
        System.out.println("Test" + displayDate);
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
        System.out.println("Order" + dbDate);
        if (dbDate == null || dbDate.isEmpty()) {
            return " ";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        System.out.println("Order" + month + date);
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String DBToDisFormat(String dbDate) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = null;
        try {
            date = formatter.parse(dbDate);
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            dateString = formatter1.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String DBToDisplayFormatV1(String dbDate) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = null;
        try {
            date = formatter.parse(dbDate);
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
            dateString = formatter1.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
