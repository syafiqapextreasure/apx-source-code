/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

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

    public static String getTodaySystemDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getSysTodayTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
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

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        return sdf.format(cal.getTime());
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
        String second = dbTime.substring(4);
        String minute = dbTime.substring(2, 4);
        String hour = dbTime.substring(0, 2);
        if (dbTime != null) {
            dbTime = String.valueOf(hour) + ":" + minute + ":" + second;
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

    public static String ifDateNull(String date) {
        StringBuilder result = new StringBuilder();
        if (date == null) {
            result.append(" ");
        } else {
            result.append(DateTime.formatDate(date));
        }
        return result.toString();
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

    public static String timeToDBFormat(String displayTime) {
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

    public static String dateTimePicker(String dbDate) {
        if (dbDate == null || dbDate.isEmpty()) {
            return " ";
        }
        String year = dbDate.substring(0, 4);
        String month = dbDate.substring(4, 6);
        String date = dbDate.substring(6, 8);
        return String.valueOf(year) + "-" + month + "-" + date;
    }

    public static String displayTimeFormat(String time) {
        if (time.trim().equals("null")) {
            time = "";
        } else if (time.trim() == null || time.trim().isEmpty()) {
            time = "";
        }
        return time;
    }

    public static String getToday24Time() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        Calendar cal = Calendar.getInstance();
        return timeFormat.format(cal.getTime());
    }
}
