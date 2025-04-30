/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.DateTime;
import com.ilmu.global.ISBD;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

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

    public static String format2Decimal(double cost) {
        String result = String.format("%.2f", cost);
        return result;
    }

    public static String convertValueToNull(String word) {
        System.out.println(String.valueOf(word.trim()) + "Date");
        if (word.trim() == "" || word.trim().isEmpty()) {
            System.out.println(String.valueOf(word.trim()) + "Date1");
            word = null;
        }
        System.out.println(word);
        return word;
    }

    public static String getLocalIPAdd() throws UnknownHostException {
        InetAddress myHost = InetAddress.getLocalHost();
        String ipAdd = myHost.getHostAddress();
        return ipAdd;
    }

    public static int convertNullHits(String word) {
        int value = 0;
        value = word == null ? 0 : Integer.parseInt(word);
        return value;
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

    public static String getCurrentUser(String user) {
        return user;
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

    public static String ifIsNull(String nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append(" ");
        } else {
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String buildString(String data) {
        StringBuilder result = new StringBuilder();
        if (data == null) {
            result.append(" ");
        } else {
            result.append(data);
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String convertIndi(String nullValue) {
        System.out.println("Indi" + nullValue);
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            System.out.println("dd" + nullValue);
            result.append("#");
        } else if (nullValue.equals("null")) {
            result.append("#");
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
            result.append(DateTime.DBToDisplayFormat(oldDate));
        }
        return result.toString();
    }

    public static double parseDecimal(String original) {
        double cost = 0.0;
        cost = original == null || original.isEmpty() ? 0.0 : Double.parseDouble(original);
        return cost;
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

    public static String CT03STAT(String CT03STAT) {
        StringBuilder result = new StringBuilder();
        if (CT03STAT == null || CT03STAT == "") {
            result.append(" ");
        } else if (CT03STAT.equals("F")) {
            result.append("Final Processing");
        } else if (CT03STAT.equals("A")) {
            result.append("Available");
        } else if (CT03STAT.equals("C")) {
            result.append("Circulated");
        } else if (CT03STAT.equals("D")) {
            result.append("Discarded");
        }
        return result.toString();
    }

    public static String CT03DOCNO(String CT03DOCNO) {
        StringBuilder result = new StringBuilder();
        result.append(CT03DOCNO);
        if (CT03DOCNO.length() == 0) {
            System.out.println("sss");
            result.append("0000000000");
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String CatalogueType(String type) {
        StringBuilder result = new StringBuilder();
        if (type == null) {
            result.append(" ");
        } else if (type.equals("C")) {
            result.append("Catalogue");
        } else if (type.equals("A")) {
            result.append("Accession");
        } else if (type.equals("S")) {
            System.out.println(type);
            result.append("Serial");
        }
        return result.toString();
    }

    public static String CatalogueIDXStatus(String status) {
        StringBuilder result = new StringBuilder();
        if (status == null) {
            result.append(" ");
        } else if (status.equals("A")) {
            result.append("Indexed");
        } else if (status.equals("T")) {
            result.append("Unindexed");
        } else if (status.equals("D")) {
            result.append("Deleted");
        }
        return result.toString();
    }

    public static Double ifDoubleIsNull(Double nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null || nullValue.isNaN()) {
            result.append(" ");
        }
        double newValue = Double.parseDouble(result.toString());
        return newValue;
    }

    public static String rawToDisplayFormat(String raw) {
        System.out.println("Value" + raw);
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                System.out.println("Data1" + rawArray[i] + "s");
                if (rawArray[i].isEmpty()) {
                    System.out.println("Data12");
                    result.append(" ");
                } else {
                    String splitData = rawArray[i].substring(1);
                    if (splitData != null && splitData != "") {
                        result.append(splitData);
                        if (i != rawArray.length) {
                            result.append(" ");
                        }
                    }
                }
                ++i;
            }
        }
        System.out.println("Data" + result.toString());
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
                    if (i == rawArray.length) continue;
                    result.append("");
                }
                ++i;
            }
        } else {
            result.append(raw);
        }
        String newString = result.toString();
        newString = newString.trim();
        return newString;
    }

    public static String convUpperCase(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String Str = new String(raw.trim());
            String data = Str.toUpperCase();
            result.append(data);
        }
        return result.toString();
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

    public static String replaceWithA(String raw) {
        StringBuilder result = new StringBuilder();
        String Str = new String(raw);
        Str = Str.replaceFirst("0", "A");
        result.append(Str);
        return result.toString();
    }

    public static String convertBlank(String data) {
        System.out.println(data);
        StringBuilder result = new StringBuilder();
        if (data == null) {
            result.append(" ");
        }
        return data;
    }

    public static String getYear(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String[] rawArray = raw.split("\\|c");
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0);
                System.out.println("Year" + splitData);
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

    public static String getPublication(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int index = raw.lastIndexOf("|c");
            raw = raw.substring(0, index + 1);
            String[] rawArray = raw.split("\\|");
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                System.out.println("Publ" + splitData);
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

    public static String getUnicodeValue(String line) {
        String hexCodeWithLeadingZeros = "";
        try {
            int index = 0;
            while (index < line.length()) {
                String hexCode = Integer.toHexString(line.codePointAt(index)).toUpperCase();
                String hexCodeWithAllLeadingZeros = "0000" + hexCode;
                String temp = hexCodeWithAllLeadingZeros.substring(hexCodeWithAllLeadingZeros.length() - 4);
                hexCodeWithLeadingZeros = String.valueOf(hexCodeWithLeadingZeros) + "\\u" + temp;
                ++index;
            }
            return hexCodeWithLeadingZeros;
        }
        catch (NullPointerException nlpException) {
            return hexCodeWithLeadingZeros;
        }
    }

    public static String getNormalString(String unicodeString) {
        String str = unicodeString.split(" ")[0];
        str = str.replace("\\", "");
        String[] arr = str.split("u");
        String text = "";
        int i = 1;
        while (i < arr.length) {
            int hexVal = Integer.parseInt(arr[i], 16);
            text = String.valueOf(text) + (char)hexVal;
            ++i;
        }
        System.out.println(text);
        return text;
    }

    public static String convertLowerCase(String capital) {
        String str = capital.toLowerCase();
        return str;
    }

    public static String insertNULL(String data) {
        if (data.isEmpty()) {
            data = null;
        }
        return data;
    }
}
