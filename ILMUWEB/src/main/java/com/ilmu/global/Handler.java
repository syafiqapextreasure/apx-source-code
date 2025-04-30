/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.DateFormatter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Handler {
    public static String concatMatno(String word) {
        int length = 10;
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length - word.length()) {
            sb.append("0");
            ++i;
        }
        return String.valueOf(sb.toString()) + word;
    }

    public static String checkingChk(String nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append("N");
        } else {
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String ifIsNull(String nullValue) {
        StringBuilder result = new StringBuilder();
        System.out.println("dd23" + nullValue);
        if (nullValue == null) {
            System.out.println("dd" + nullValue);
            result.append(" ");
        } else {
            System.out.println("dd" + nullValue);
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String dateConverter(String oldDate) {
        StringBuilder result = new StringBuilder();
        if (oldDate == null) {
            result.append(" ");
        } else {
            result.append(DateFormatter.DBToDisplayFormat(oldDate));
        }
        return result.toString();
    }

    public static String getCurrentUser(String user) {
        return user;
    }

    public static String decimalConversion(String cost) {
        StringBuilder result = new StringBuilder();
        if (cost == null) {
            result.append(" ");
        } else {
            double f = Double.parseDouble(cost);
            result.append(String.format("%.2f", f));
        }
        return result.toString();
    }

    public static Double ifDoubleIsNull(Double nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append(" ");
        }
        double newValue = Double.parseDouble(result.toString());
        return newValue;
    }

    public static String rawToDisplayFormat(String raw) {
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }

    public static String rawToDisplayFormat1(String raw) {
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }

    public static String removeSubfield(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String[] rawArray = raw.split("\\|");
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append("");
                    }
                }
                ++i;
            }
        }
        String newString = result.toString();
        newString = newString.trim();
        return newString;
    }

    public static String convUpperCase(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String Str = new String(raw);
            String data = Str.toUpperCase();
            result.append(data);
        }
        return Handler.convData(result.toString());
    }

    public static String convData(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            raw = raw.replaceAll("[^A-Za-z0-9\\s+$]", "");
            result.append(raw);
        }
        return result.toString();
    }

    public static String replaceWith0(String raw) {
        StringBuilder result = new StringBuilder();
        String Str = new String(raw);
        Str = Str.replaceFirst("A", "0");
        result.append(Str);
        return result.toString();
    }

    public static String insertNULL(String data) {
        if (data.isEmpty()) {
            data = null;
        }
        return data;
    }

    public static String ifIntIsNull(String nullValue) {
        StringBuilder result = new StringBuilder();
        System.out.println("dd23" + nullValue);
        if (nullValue == null) {
            System.out.println("dd" + nullValue);
            result.append("0");
        } else {
            System.out.println("dd" + nullValue);
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String getLocalIPAdd() throws UnknownHostException {
        InetAddress myHost = InetAddress.getLocalHost();
        String ipAdd = myHost.getHostAddress();
        return ipAdd;
    }
}
