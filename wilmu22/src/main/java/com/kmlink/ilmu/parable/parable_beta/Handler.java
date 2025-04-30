/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.ISBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Handler {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String convtoPx = null;

    public Handler(String convtoPx) {
        this.convtoPx = convtoPx;
    }

    public String getconvtoPx() {
        return this.convtoPx;
    }

    public static String convTwipToPx(String convtoPx) {
        StringBuilder result = new StringBuilder();
        if (convtoPx != null) {
            double newValue = Double.parseDouble(convtoPx) / 567.0 * 38.0;
            System.out.println("NewValue1" + newValue);
            convtoPx = String.valueOf(newValue);
            System.out.println("NewValue2" + convtoPx);
            result.append(convtoPx);
        } else {
            result.append(convtoPx);
        }
        return result.toString();
    }

    public static String ifIsNullInt(int nullValue) {
        StringBuilder result = new StringBuilder();
        System.out.println("dd23" + nullValue);
        if (nullValue == 0) {
            System.out.println("dd" + nullValue);
            result.append(" ");
        } else {
            System.out.println("dd" + nullValue);
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String getSubfield(String raw, List<ISBD> isbd) {
        StringBuilder result = new StringBuilder();
        if (raw != null && raw.contains("|")) {
            String[] rawArray = raw.split("\\|");
            int i = 1;
            while (i < rawArray.length) {
                String subfield = rawArray[i].substring(0, 1);
                for (ISBD j : isbd) {
                    if (!j.getGL23SUBF().equals(subfield)) continue;
                    subfield = j.getGL23SUBF().replace(j.getGL23SUBF(), j.getGL23PUNCT());
                    String data = String.valueOf(subfield) + rawArray[i].substring(1);
                    result.append(data);
                }
                ++i;
            }
        } else {
            result.append(raw);
        }
        String newString = result.toString();
        newString = newString.trim();
        System.out.println("Subf" + newString);
        return newString;
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

    public static String rawToDisplayFormat(String raw) {
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0, 1);
                System.out.println("Subfields" + splitData);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        System.out.println("Data" + result.toString());
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
}
