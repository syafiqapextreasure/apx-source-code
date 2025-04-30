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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getSysTodayDate2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        return sdf.format(cal.getTime());
    }

    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getAddDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(5, 14);
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }

    public static String getToday24Time() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }

    public static String getSysTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
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
        System.out.println("www" + displayTime + "e");
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss aa");
        SimpleDateFormat outputformat = new SimpleDateFormat("HHmmss");
        Date date = null;
        String output = null;
        try {
            if (displayTime != null && displayTime != "") {
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

    public static String formatDate(String dbDate) {
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String formatDateV2(String dbDate) {
        if (dbDate == null || dbDate.equals("NULL")) {
            dbDate = "";
        } else {
            String year = dbDate.substring(0, 4);
            String month = dbDate.substring(4, 6);
            String date = dbDate.substring(6, 8);
            month = String.valueOf(Integer.parseInt(month));
            date = String.valueOf(Integer.parseInt(date));
            dbDate = String.valueOf(date) + "/" + month + "/" + year;
        }
        return dbDate;
    }

    public static String formatDatelocal(String dbDate) {
        String dates = "";
        if (dbDate != null && !dbDate.trim().isEmpty()) {
            String year = dbDate.substring(0, 4);
            String month = dbDate.substring(4, 6);
            String date = dbDate.substring(6, 8);
            month = String.valueOf(Integer.parseInt(month));
            date = String.valueOf(Integer.parseInt(date));
            dates = String.valueOf(year) + "-" + month + "-" + date;
        } else {
            dates = "";
        }
        return dates;
    }

    public static String formatDatelocals(String dbDate) {
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(DateTime.formatDateV2(dbDate));
            System.out.println(date1 + "servicesaas");
            SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
            dbDate = date2.format(date1);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dbDate;
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

    public static String formatTimetoDB(String dbTime) {
        dbTime = dbTime.replace(":", "");
        return dbTime;
    }

    public static String splitDate(String dbDate) {
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(year) + month + date;
    }

    public static String Time(String dbTime) {
        if (dbTime != null && dbTime != "" && !dbTime.trim().isEmpty()) {
            String second = dbTime.substring(4);
            String minute = dbTime.substring(2, 4);
            String hour = dbTime.substring(0, 2);
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
        } else {
            dbTime = "";
        }
        return dbTime;
    }

    public static String convertLibraryTiming(String dbTime) {
        if (dbTime != null && dbTime != "" && !dbTime.trim().isEmpty()) {
            String minute = dbTime.substring(2, 4);
            String hour = dbTime.substring(0, 2);
            dbTime = String.valueOf(hour) + ":" + minute + ":00";
        } else {
            dbTime = "";
        }
        return dbTime;
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

    public static String DBToDisplayFormat(String dbDate) {
        if (dbDate == null || dbDate.isEmpty()) {
            return " ";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String dateTimePicker(String dbDate) {
        if (dbDate == null || dbDate.isEmpty()) {
            return " ";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        return String.valueOf(year) + "-" + month + "-" + date;
    }

    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
