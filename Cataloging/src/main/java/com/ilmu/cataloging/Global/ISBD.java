/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Global;

import com.ilmu.cataloging.Bibliography.BO_Record;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ISBD {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String tag = null;
    public String data = null;
    public String GL23PUNCT = null;
    public String GL23TAG = null;
    public String GL23SUBF = null;
    public String CT17MRGLEFT = null;
    public String CT17PREPUNC = null;
    public String CT17TAG = null;
    public String CT17POSTPUNC = null;

    public ISBD(String tag, String data) {
        this.tag = tag;
        this.data = data;
    }

    public ISBD(String CT17MRGLEFT, String CT17PREPUNC, String CT17TAG, String CT17POSTPUNC) {
        this.CT17MRGLEFT = CT17MRGLEFT;
        this.CT17PREPUNC = CT17PREPUNC;
        this.CT17TAG = CT17TAG;
        this.CT17POSTPUNC = CT17POSTPUNC;
    }

    public ISBD(String GL23PUNCT, String GL23TAG, String GL23SUBF) {
        this.GL23PUNCT = GL23PUNCT;
        this.GL23TAG = GL23TAG;
        this.GL23SUBF = GL23SUBF;
    }

    public String getGL23PUNCT() {
        return this.GL23PUNCT;
    }

    public String getGL23TAG() {
        return this.GL23TAG;
    }

    public String getGL23SUBF() {
        return this.GL23SUBF;
    }

    public String getCT17MRGLEFT() {
        return this.CT17MRGLEFT;
    }

    public static String getISBDView(String CT17TPL, String controlno) throws SQLException {
        StringBuilder result;
        block15: {
            String query = " SELECT * FROM CTISBD WHERE CT17TPL='" + CT17TPL + "' ORDER BY CT17SEQNO";
            String isbd = null;
            result = new StringBuilder();
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    int number = 1;
                    int roman = 1;
                    while (rs.next()) {
                        List<BO_Record> BORecord = BO_Record.getRetrievedData(controlno, "U", "CT");
                        for (BO_Record i : BORecord) {
                            if (!rs.getString("CT17TAG").contains(i.getTAG().trim())) continue;
                            isbd = String.valueOf(Handler.convertTabNewLine(Handler.ifIsNull(rs.getString("CT17MRGLEFT")))) + Handler.ifIsNull(rs.getString("CT17PREPUNC")) + rs.getString("CT17TAG") + Handler.ifIsNull(rs.getString("CT17POSTPUNC")) + Handler.convertTabNewLine(Handler.ifIsNull(rs.getString("CT17MRGRIGHT")));
                            if (Handler.ifIsNull(rs.getString("CT17PREPUNC")).equals("%n")) {
                                isbd = isbd.replace("%n", String.valueOf(number));
                            }
                            if (Handler.ifIsNull(rs.getString("CT17PREPUNC")).equals("%r")) {
                                isbd = isbd.replace("%r", String.valueOf(Handler.toRoman(roman)));
                            }
                            isbd = isbd.replace(i.getTAG().trim(), i.getCTTPLSUBF());
                            result.append(isbd);
                            ++number;
                            ++roman;
                        }
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
                    break block15;
                }
            }
            catch (Throwable throwable) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    public static List<ISBD> getPunctuation(String GL17TAG) throws SQLException {
        ArrayList<ISBD> tplList = new ArrayList<ISBD>();
        String query = " SELECT GL23TAG, GL23SUBF, GL23PUNCT FROM GLMSUBF, GLTAGP WHERE GL23MARC='U' AND GL17MARC=GL23MARC AND GL17TAG=GL23TAG AND GL17TAG='" + GL17TAG + "'";
        String results = null;
        Connection conn = DBConnection.getConnection();
        try {
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("GL23PUNCT") != null && rs.getString("GL23PUNCT") != "" ? ISBD.asciiconvert(rs.getString("GL23PUNCT")) : " ";
                    ISBD punctuation = new ISBD(results, rs.getString("GL23TAG"), rs.getString("GL23SUBF"));
                    tplList.add(punctuation);
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
        return tplList;
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
        String query = "Select GL23PUNCT FROM GLMSUBF WHERE CONCAT(GL23TAG,GL23SUBF) ='" + tagSubf + "'";
        String results = "";
        System.out.println("Qurery" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("we" + rs.getString("GL23PUNCT"));
                    results = rs.getString("GL23PUNCT") != null ? ISBD.asciiconvert(rs.getString("GL23PUNCT")) : " ";
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
                String punct = ISBD.getPunct(tagSubf);
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
        String query = "Select GL23GRP FROM GLMSUBF WHERE CONCAT(GL23TAG, GL23SUBF) ='" + tag + subf + "'";
        String results = "";
        try {
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

    public static String convert(int pointer, String raw) {
        System.out.println("eee" + raw + pointer);
        String tag = ISBD.getTag(pointer);
        ArrayList<String> intArray = new ArrayList<String>();
        ArrayList<String> punct1 = new ArrayList<String>();
        StringTokenizer st1 = new StringTokenizer(raw, "|");
        StringBuilder result = new StringBuilder();
        StringBuilder clusterChk = new StringBuilder();
        while (st1.hasMoreTokens()) {
            intArray.add(st1.nextToken());
        }
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
            if (punct != "") {
                punct1.add(punct.trim());
                continue;
            }
            punct1.add(" ");
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
            }
            ++i;
        }
        if (punct.trim().equals("[") && clusterChk.toString() != null) {
            String s2 = ISBD.splitter(group, clusterChk.toString(), tag);
            result.append(s2);
            result.append("]");
        }
        return ISBD.print(result.toString());
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
        System.out.println("Subfield" + newString);
        return newString;
    }

    public static String print(String print) {
        return print;
    }
}
