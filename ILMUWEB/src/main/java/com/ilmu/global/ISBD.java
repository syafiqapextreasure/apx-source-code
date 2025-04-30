/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ISBD {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String tag = null;
    public String data = null;

    public ISBD(String tag, String data) {
        this.tag = tag;
        this.data = data;
    }

    public static String getTag(int pointer) {
        String query = "SELECT DISTINCT CT06TAG FROM CTPONT WHERE CT06POINTER = '" + pointer + "'";
        String results = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("CT06TAG");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
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
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                results = ISBD.asciiconvert(rs.getString("GL23PUNCT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println(num[i]);
            Integer iOctal = Integer.parseInt(num[i], 8);
            char cOctal = (char)iOctal.intValue();
            System.out.println(cOctal);
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
                    System.out.println(i);
                    c = raw.charAt(i + 1);
                }
                ++i;
            }
        }
        System.out.println(c);
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
            String splitGrp = (String)getGrpList.get(counter);
            String splitSubf = (String)subfList.get(counter);
            System.out.println(String.valueOf(splitGrp) + String.valueOf(splitSubf.charAt(0)));
            if (splitGrp.equals(String.valueOf(splitSubf.charAt(0)))) {
                String tagSubf = String.valueOf(tag) + splitSubf.charAt(0);
                String punct = ISBD.getPunct(tagSubf);
                splitSubf = splitSubf.replaceFirst(String.valueOf(splitSubf.charAt(0)), punct.trim());
                result.append(splitSubf);
                System.out.println("t" + result.toString());
            }
            ++counter;
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static String chkCluster(String getGrp, String getSubf) {
        System.out.println(getGrp);
        String delimiter = "\\,";
        String[] temp = getGrp.split(delimiter);
        String[] temp1 = getSubf.split(delimiter);
        int i = 0;
        while (i < temp.length) {
            int j = 0;
            while (j < temp1.length) {
                if (temp1[j].equals(temp[i])) {
                    System.out.println(temp[i]);
                }
                ++j;
            }
            ++i;
        }
        return delimiter;
    }

    public static String getGrp(String tag, String subf) {
        String query = "Select GL23GRP FROM GLMSUBF WHERE (GL23TAG + GL23SUBF) ='" + tag + subf + "'";
        System.out.println(query);
        String results = "";
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                results = rs.getString("GL23GRP");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static String convert(int pointer, String raw) {
        String tag = ISBD.getTag(pointer);
        ArrayList<String> intArray = new ArrayList<String>();
        ArrayList<String> punct1 = new ArrayList<String>();
        StringTokenizer st1 = new StringTokenizer(raw, "|");
        StringBuilder result = new StringBuilder();
        StringBuilder clusterChk = new StringBuilder();
        while (st1.hasMoreTokens()) {
            intArray.add(st1.nextToken());
        }
        System.out.println("t" + intArray);
        String getGrp = null;
        String group = null;
        String punct = null;
        for (String s : intArray) {
            String tagSubf = String.valueOf(tag) + s.charAt(0);
            punct = ISBD.getPunct(tagSubf);
            getGrp = ISBD.getGrp(tag, String.valueOf(s.charAt(0)));
            if (getGrp != null) {
                group = getGrp;
            }
            punct1.add(punct.trim());
        }
        int idx1 = intArray.indexOf("[");
        int i = 0;
        while (i < punct1.size()) {
            String s1 = (String)punct1.get(i);
            String s2 = (String)intArray.get(i);
            int idx = result.toString().indexOf("[");
            if (idx <= 0) {
                s2 = s2.replaceFirst(String.valueOf(s2.charAt(0)), s1);
                result.append(s2);
            } else {
                clusterChk.append("|" + s2);
            }
            System.out.println(result.toString());
            ++i;
        }
        if (punct.trim().equals("[") && clusterChk.toString() != null) {
            System.out.println(result.toString());
            String s2 = ISBD.splitter(group, clusterChk.toString(), tag);
            result.append(s2);
            result.append("]");
            System.out.println(result.toString());
        }
        return ISBD.print(result.toString());
    }

    public static String print(String print) {
        System.out.println(print);
        return print;
    }
}
