/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtility {
    public static String StrToDate(String vsDateStr) {
        String vntDate = null;
        try {
            if (!vsDateStr.equals("")) {
                String sDay = vsDateStr.substring(6, 8);
                String sMonth = vsDateStr.substring(4, 6);
                String sYear = vsDateStr.substring(0, 4);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateconcat = String.valueOf(sDay) + "/" + sMonth + "/" + sYear;
                Date startdate = dateFormatter.parse(dateconcat);
                vntDate = dateFormatter.format(startdate);
            }
            if (vsDateStr.equals("null")) {
                vntDate = "";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vntDate;
    }

    public static String StrToTime(String timeStr) {
        String timeformated = null;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(timeStr);
            timeformated = date.toString();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return timeformated;
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

    public static String DatetoStr(String msDate) {
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        String dateStr = msDate.toString();
        dateStr = dateStr.replaceAll("/", "");
        return dateStr;
    }

    public static String splitDate(String currDate) {
        String year = currDate.substring(6, 10);
        String month = currDate.substring(3, 5);
        String date = currDate.substring(0, 2);
        return String.valueOf(year) + month + date;
    }

    public static String TimetoStr(String msTime) {
        String sYear = "";
        String sMonth = "";
        String sDay = "";
        String dateStr = msTime.toString();
        dateStr = dateStr.replaceAll(":", "");
        return dateStr;
    }
}
