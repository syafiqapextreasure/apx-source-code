/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ISBD_V3 {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String tag = null;
    public String data = null;

    public ISBD_V3(String tag, String data) {
        this.tag = tag;
        this.data = data;
    }

    public static String getTag(int pointer) {
        String query = "SELECT DISTINCT CT06TAG FROM CTPONT WHERE CT06POINTER = '" + pointer + "'";
        String results = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("CT06TAG");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static String getPunct(String tagSubf) {
        String query = "Select GL23PUNCT FROM GLMSUBF WHERE (GL23TAG + GL23SUBF) ='" + tagSubf + "'";
        String results = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("GL23PUNCT") != null ? ISBD_V3.asciiconvert(rs.getString("GL23PUNCT")) : " ";
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static String asciiconvert(String punct) {
        StringBuilder result = new StringBuilder();
        int n = punct.length() / 3;
        String[] num = new String[n];
        int i = 0;
        int x = 0;
        int y = 3;
        while (i < n) {
            num[i] = punct.substring(x, y);
            x += 3;
            y += 3;
            Integer iOctal = Integer.parseInt(num[i], 8);
            char cOctal = (char)iOctal.intValue();
            result.append(cOctal);
            ++i;
        }
        return result.toString();
    }

    public static String getSubf(String raw, int index) {
        if (raw == null) {
            return "-";
        }
        char c = '\u0000';
        if (raw != null) {
            int i = 0;
            while (i < raw.length()) {
                if (i == index) {
                    c = raw.charAt(i + 1);
                }
                ++i;
            }
        }
        return String.valueOf(c);
    }

    public static String splitter(String getGrp, String subfields, String tag) {
        ArrayList<String> getGrpList = new ArrayList<String>();
        ArrayList<String> subfList = new ArrayList<String>();
        StringTokenizer splitGrpList = new StringTokenizer(getGrp, ",");
        StringTokenizer splitSubfList = new StringTokenizer(subfields.trim(), "|");
        StringBuilder result = new StringBuilder();
        while (splitGrpList.hasMoreTokens() && splitSubfList.hasMoreTokens()) {
            getGrpList.add(splitGrpList.nextToken());
            subfList.add(splitSubfList.nextToken());
        }
        int counter = 0;
        while (counter < getGrpList.size()) {
            String splitSubf;
            String splitGrp = (String)getGrpList.get(counter);
            if (splitGrp.equals(String.valueOf((splitSubf = (String)subfList.get(counter)).charAt(0)))) {
                String tagSubf = String.valueOf(tag) + splitSubf.charAt(0);
                String punct = ISBD_V3.getPunct(tagSubf);
                splitSubf = splitSubf.replaceFirst(String.valueOf(splitSubf.charAt(0)), punct.trim());
                result.append(splitSubf);
            }
            ++counter;
        }
        return result.toString();
    }

    public static String chkCluster(String getGrp, String getSubf) {
        String delimiter = "\\,";
        String[] temp = getGrp.split(delimiter);
        String[] temp1 = getSubf.split(delimiter);
        int i = 0;
        while (i < temp.length) {
            int j = 0;
            while (j < temp1.length) {
                temp1[j].equals(temp[i]);
                ++j;
            }
            ++i;
        }
        return delimiter;
    }

    public static String getGrp(String tag, String subf) {
        String query = "Select GL23GRP FROM GLMSUBF WHERE (GL23TAG + GL23SUBF) ='" + tag + subf + "'";
        String results = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("GL23GRP");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static String octalToAscii(String sOctal) {
        Integer iOctal = Integer.parseInt(sOctal, 8);
        System.out.println(iOctal);
        char cOctal = (char)iOctal.intValue();
        System.out.println("Output" + cOctal);
        System.out.println(iOctal);
        return Character.toString(cOctal);
    }

    public static String convert(int pointer, String raw) {
        System.out.println("Lengthss");
        String tag = ISBD_V3.getTag(pointer);
        String subfield = ISBD_V3.convertData(tag, raw);
        System.out.println("Subfield" + subfield);
        return subfield;
    }

    public static String convertData(String tag, String raw) {
        System.out.println("ss" + raw);
        String[] rawArray = raw.split("\\|");
        System.out.println("Length" + rawArray.length);
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0, 1);
                System.out.println("Subfields" + splitData);
                if (splitData != null && splitData != "") {
                    String punct = ISBD_V3.getPunctuation(tag, splitData);
                    raw = raw.replace("|" + splitData, punct);
                }
                ++i;
            }
        }
        System.out.println("Data" + raw);
        return raw;
    }

    public static String getPunctuation(String tag, String subf) {
        String query = "Select GL23PUNCT FROM GLMSUBF WHERE GL23TAG='" + tag + "' AND GL23SUBF='" + subf + "'";
        System.out.println(query);
        String results = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println(rs.getString("GL23PUNCT"));
                    if (rs.getString("GL23PUNCT") != null) {
                        System.out.println(rs.getString("GL23PUNCT"));
                        results = ISBD_V3.octalToAscii(rs.getString("GL23PUNCT"));
                        System.out.println("Res" + results);
                        continue;
                    }
                    results = " ";
                    System.out.println("Res1" + results);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static String print(String print) {
        return print;
    }
}
