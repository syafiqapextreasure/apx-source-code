/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.serial.Issues;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
    public static String nextDate(String inputDate, String freq, int count) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        Calendar c = Calendar.getInstance();
        date = formatter.parse(inputDate);
        c.setTime(date);
        switch (Issues.SE04_getTypeByFreq(freq)) {
            case "M": {
                c.add(2, Issues.SE04_getTimeByFreq(freq) * count);
                break;
            }
            case "WW": {
                if (Issues.SE04_getTimeByFreq(freq) == 200) {
                    int dayOfMonth = c.get(5);
                    int yourChosenPaymentDate = (Math.min(dayOfMonth, 28) + 15) % 28;
                    int first = Math.min(dayOfMonth, yourChosenPaymentDate);
                    int second = Math.max(dayOfMonth, yourChosenPaymentDate);
                    int addMonth = ++count - (int)(Math.floor(count / 2) + 1.0);
                    if (count % 2 == 0) {
                        c.set(5, second);
                        c.add(2, addMonth);
                        break;
                    }
                    c.set(5, first);
                    c.add(2, addMonth);
                    break;
                }
                c.add(5, Issues.SE04_getTimeByFreq(freq) * 7 * count);
                break;
            }
            case "YYYY": {
                c.add(1, Issues.SE04_getTimeByFreq(freq) * count);
                break;
            }
            case "D": {
                c.add(5, Issues.SE04_getTimeByFreq(freq) * count);
            }
        }
        return formatter.format(c.getTime());
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
        month = String.valueOf(Integer.parseInt(month));
        date = String.valueOf(Integer.parseInt(date));
        return String.valueOf(date) + "/" + month + "/" + year;
    }
}
