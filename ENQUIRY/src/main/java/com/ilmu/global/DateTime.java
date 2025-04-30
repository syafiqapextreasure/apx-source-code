/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {
    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getSysTodayDate() {
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

    public static String formatDate(String dbDate) {
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String formatDateV2(String dbDate) {
        System.out.println(dbDate);
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
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(year) + "-" + month + "-" + date;
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

    public static String splitDate(String dbDate) {
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(year) + month + date;
    }

    public static String Time(String dbTime) {
        if (dbTime == null || dbTime.isEmpty() || dbTime.equals("null")) {
            dbTime = "";
        } else {
            String second = dbTime.substring(4);
            String minute = dbTime.substring(2, 4);
            String hour = dbTime.substring(0, 2);
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
        }
        return dbTime;
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

    public static String getTodayDate2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String formatDate2() {
        String year = DateTime.getTodayDate2().substring(0, 4);
        String month = DateTime.getTodayDate2().substring(4, 6);
        String date = DateTime.getTodayDate2().substring(6, 8);
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        System.out.println("rrrr" + date + "/" + month + "/" + year);
        return String.valueOf(date) + "/" + month + "/" + year;
    }

    public static String StrToTime2(String timeStr) {
        String time = "";
        try {
            if (!timeStr.equals("")) {
                String hour = timeStr.substring(0, 2);
                String min = timeStr.substring(2, 4);
                String sec = timeStr.substring(4, 6);
                time = String.valueOf(hour) + ":" + min + ":" + sec;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
