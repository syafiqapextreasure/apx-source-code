package com.ppk.topController.lost.LostMaterial.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String getCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static String formatDate(String date, String currentFormat, String targetFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(currentFormat);
            SimpleDateFormat target = new SimpleDateFormat(targetFormat);
            return target.format(sdf.parse(date));
        } catch (Exception e) {
            return "";
        }
    }
}