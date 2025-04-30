/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.cataloging.Bibliography.Bibliography;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Indexing {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String CT06TAG = null;
    public String CT06SRAW = null;
    public int CT06COUNTER = 0;
    public int CT05POINTER = 0;
    public String CT05SRAW = null;
    static List<String> queryList = new ArrayList<String>();

    public Indexing(String CT06TAG, String CT06SRAW, int CT06COUNTER) {
        this.CT06TAG = CT06TAG;
        this.CT06SRAW = CT06SRAW;
        this.CT06COUNTER = CT06COUNTER;
    }

    public Indexing(int CT05POINTER) {
        this.CT05POINTER = CT05POINTER;
    }

    public Indexing(String CT05SRAW, int CT05POINTER) {
        this.CT05SRAW = CT05SRAW;
        this.CT05POINTER = CT05POINTER;
    }

    public String getCT06TAG() {
        return this.CT06TAG;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public String getCT06SRAW() {
        return this.CT06SRAW;
    }

    public int getCT06COUNTER() {
        return this.CT06COUNTER;
    }

    public int getCT05POINTER() {
        return this.CT05POINTER;
    }

    public static boolean CT05SCONV_Exist(String CT05SCONV, String GL17TAG) {
        System.out.println("Conv" + CT05SCONV);
        String query = " SELECT COUNT(CT05SCONV) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV = N'" + Handler.convUpperCase(Handler.removeSpecialChar(CT05SCONV)) + "'";
        boolean exist = false;
        System.out.println("Testig" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }

    public static int TotalSCONVRcrd(String CT05SCONV, String GL17TAG) {
        System.out.println("Conv" + CT05SCONV);
        String query = " SELECT COUNT(CT05SCONV) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV = N'" + Handler.convUpperCase(Handler.removeSpecialChar(CT05SCONV)) + "'";
        System.out.println(query);
        int total = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    total = Integer.parseInt(rs.getString("COUNT")) + 1;
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
        return total;
    }

    public static boolean SCONVDuplicateChk(String CT05SCONV, String GL17TAG) {
        String query = " SELECT COUNT(CT05SCONV) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV= N'" + CT05SCONV + "' AND CT05HITS >0";
        System.out.println(query);
        System.out.println(Handler.convUpperCase(Handler.removeSubfield(CT05SCONV)));
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean CT05SRAW_Exist(String CT05SRAW, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        if (!matcher.matches()) {
            CT05SRAW = CT05SRAW.replace("'", "''");
        }
        String query = " SELECT COUNT(CT05SRAW) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SRAW = '" + CT05SRAW + "'";
        boolean exist = false;
        System.out.println("BO" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }

    public static List<Indexing> checkData(String CT05SCONV, String GL17TAG) throws SQLException {
        ArrayList<Indexing> tplList = new ArrayList<Indexing>();
        String query = " SELECT CT05SRAW, CT05POINTER FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV = '" + Handler.convUpperCase(Handler.removeSpecialChar(CT05SCONV)) + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Indexing chkData = new Indexing(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")));
                    tplList.add(chkData);
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
        return tplList;
    }

    public static String getCT05SRAW(String CT05SRAW, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        if (!matcher.matches()) {
            CT05SRAW = CT05SRAW.replace("'", "''");
        }
        String query = " SELECT CT05SRAW FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SRAW = '" + CT05SRAW + "'";
        String rawData = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    rawData = rs.getString("CT05SRAW");
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
        return rawData;
    }

    public static boolean CT02MATNO_Exist(String CT02MATNO, String module) {
        String query = " SELECT COUNT(" + module + "02MATNO) AS COUNT FROM " + module + "MATM where " + module + "02MATNO LIKE '" + CT02MATNO + "'";
        boolean exist = false;
        System.out.println("Query" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }

    public static Indexing getPointer(String CT05SRAW, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        if (!matcher.matches()) {
            CT05SRAW = CT05SRAW.replace("'", "''");
        }
        String query = "SELECT CT05POINTER FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " " + "WHERE CT05SRAW = '" + CT05SRAW + "'";
        Indexing result = null;
        System.out.println("Query" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = new Indexing(Integer.parseInt(rs.getString("CT05POINTER")));
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
        return result;
    }

    public static String updateCT05COUNT(String CT05SCONV, String GL17TABNAME) {
        String query = "UPDATE " + Bibliography.getMarkTagTableName(GL17TABNAME) + " SET CT05COUNT=CT05COUNT+1 " + "WHERE CT05SCONV LIKE '" + CT05SCONV + "'";
        return query;
    }

    public static String updateCT05HITS(int CT05POINTER, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        String query = "UPDATE " + Bibliography.getMarkTagTableName(GL17TAG) + " SET CT05HITS=CT05HITS+1 " + "WHERE CT05POINTER = '" + CT05POINTER + "'";
        return query;
    }

    public static String addCTTable(int CT05POINTER, String CT05SRAW, String CT05SCONV, String GL17TAG, int SCOUNT, int hits, String autlink) {
        System.out.println("WEEE" + CT05SCONV);
        CT05SCONV = Handler.convUpperCase(Handler.removeSpecialChar(CT05SCONV));
        if (CT05SCONV.length() > 255) {
            CT05SCONV = CT05SCONV.substring(0, 255);
        }
        CT05SRAW = CT05SRAW.replace("'", "''");
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueInt.put("CT05POINTER", CT05POINTER);
        if (CT05SRAW.length() > 255) {
            System.out.println("RAW" + CT05SRAW.substring(0, 254));
            System.out.println("RAW1" + CT05SRAW.substring(255));
            valueStr.put("CT05SRAW", CT05SRAW.substring(0, 255));
            valueStr.put("CT05RAW", CT05SRAW.substring(255));
        } else {
            valueStr.put("CT05SRAW", CT05SRAW);
            valueStr.put("CT05RAW", "");
        }
        valueStr.put("CT05SCONV", CT05SCONV);
        valueInt.put("CT05HITS", hits);
        valueStr.put("CT05AUTLINK", autlink);
        valueInt.put("CT05COUNT", SCOUNT);
        String query = QueryBuilder.createInsertQuery(Bibliography.getMarkTagTableName(GL17TAG), valueStr, valueInt, null);
        System.out.println(query);
        return query;
    }

    public static int countRow(String CT05SCONV, String GL17TABNAME) {
        String query = "Select Count(*) as Count From " + Bibliography.getMarkTagTableName(GL17TABNAME) + "WHERE CT05SCONV = '" + CT05SCONV + "'";
        int count = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString("Count"));
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
        return count;
    }

    public static String addCTMATM(String CT02MATNO, String CT02CREBY, String CT02CRDATE, String CT02CRETIME, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME, String type, String tag006, String tag007, String tag008, String module, String security) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put(String.valueOf(module) + "02MATNO", CT02MATNO);
        valueStr.put(String.valueOf(module) + "02IDXDATE", CT02MODIDATE);
        valueStr.put(String.valueOf(module) + "02IDXBY", CT02MODIBY);
        valueStr.put(String.valueOf(module) + "02IDXTIME", CT02MODITIME);
        valueStr.put(String.valueOf(module) + "02CREBY", CT02CREBY);
        valueStr.put(String.valueOf(module) + "02CRDATE", DateTime.displayToDBFormat(CT02CRDATE));
        valueInt.put(String.valueOf(module) + "02NORESV", 0);
        valueStr.put(String.valueOf(module) + "02STATUS", "A");
        valueStr.put(String.valueOf(module) + "02TYPE", type);
        valueStr.put(String.valueOf(module) + "02CRETIME", DateTime.timeToDBFormat(CT02CRETIME));
        valueStr.put(String.valueOf(module) + "02T006", tag006);
        valueStr.put(String.valueOf(module) + "02T007", tag007);
        valueStr.put(String.valueOf(module) + "02T008", tag008);
        valueStr.put(String.valueOf(module) + "02PRIVATE", security);
        System.out.println("Data" + CT02MATNO + CT02MODIDATE + CT02MODITIME + CT02CREBY + CT02CRDATE + tag006 + tag007 + tag008 + module);
        String query = QueryBuilder.createInsertQuery(String.valueOf(module) + "MATM", valueStr, valueInt, null);
        return query;
    }

    public static String addAuthCTMATM(String CT02MATNO, String CT02CREBY, String CT02CRDATE, String CT02CRETIME, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME, String type, String tag006, String tag007, String tag008, String module) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put(String.valueOf(module) + "02MATNO", CT02MATNO);
        valueStr.put(String.valueOf(module) + "02IDXDATE", CT02MODIDATE);
        valueStr.put(String.valueOf(module) + "02IDXBY", CT02MODIBY);
        valueStr.put(String.valueOf(module) + "02IDXTIME", CT02MODITIME);
        valueStr.put(String.valueOf(module) + "02CREBY", CT02CREBY);
        valueStr.put(String.valueOf(module) + "02CRDATE", DateTime.displayToDBFormat(CT02CRDATE));
        valueInt.put(String.valueOf(module) + "02NORESV", 0);
        valueStr.put(String.valueOf(module) + "02STATUS", "A");
        valueStr.put(String.valueOf(module) + "02TYPE", type);
        valueStr.put(String.valueOf(module) + "02CRETIME", DateTime.timeToDBFormat(CT02CRETIME));
        valueStr.put(String.valueOf(module) + "02T006", tag006);
        valueStr.put(String.valueOf(module) + "02T007", tag007);
        valueStr.put(String.valueOf(module) + "02T008", tag008);
        String query = QueryBuilder.createInsertQuery(String.valueOf(module) + "MATM", valueStr, valueInt, null);
        return query;
    }

    public static String addCTPONT(int CT06POINTER, String CT06MATNO, String CT06TAG, String CT06INDI1, String CT06INDI2, String CT06STATUS, String marc, String module) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put(String.valueOf(module) + "06STATUS", CT06STATUS);
        valueStr.put(String.valueOf(module) + "06INDI2", CT06INDI2);
        valueStr.put(String.valueOf(module) + "06INDI1", CT06INDI1);
        valueStr.put(String.valueOf(module) + "06TAG", CT06TAG);
        valueStr.put(String.valueOf(module) + "06MARC", marc);
        valueStr.put(String.valueOf(module) + "06MATNO", CT06MATNO);
        valueInt.put(String.valueOf(module) + "06POINTER", CT06POINTER);
        String query = QueryBuilder.createInsertQuery(String.valueOf(module) + "PONT", valueStr, valueInt, null);
        return query;
    }

    public static String updateCTMATM(String CT02MATNO, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME, String type, String tag006, String tag007, String tag008, String module, String security) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put(String.valueOf(module) + "02MATNO", CT02MATNO);
        valueStr.put(String.valueOf(module) + "02IDXDATE", CT02MODIDATE);
        valueStr.put(String.valueOf(module) + "02IDXBY", CT02MODIBY);
        valueStr.put(String.valueOf(module) + "02STATUS", "A");
        valueStr.put(String.valueOf(module) + "02IDXTIME", CT02MODITIME);
        valueStr.put(String.valueOf(module) + "02TYPE", type);
        valueStr.put(String.valueOf(module) + "02T006", tag006);
        valueStr.put(String.valueOf(module) + "02T007", tag007);
        valueStr.put(String.valueOf(module) + "02T008", tag008);
        valueStr.put(String.valueOf(module) + "02PRIVATE", security);
        String query = QueryBuilder.createUpdateQuery(String.valueOf(module) + "MATM", valueStr, null, null, condition);
        return query;
    }

    public static String updateAuthCTMATM(String CT02MATNO, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME, String type, String tag006, String tag007, String tag008, String module) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put(String.valueOf(module) + "02MATNO", CT02MATNO);
        valueStr.put(String.valueOf(module) + "02IDXDATE", CT02MODIDATE);
        valueStr.put(String.valueOf(module) + "02IDXBY", CT02MODIBY);
        valueStr.put(String.valueOf(module) + "02STATUS", "A");
        valueStr.put(String.valueOf(module) + "02IDXTIME", CT02MODITIME);
        valueStr.put(String.valueOf(module) + "02TYPE", type);
        valueStr.put(String.valueOf(module) + "02T006", tag006);
        valueStr.put(String.valueOf(module) + "02T007", tag007);
        valueStr.put(String.valueOf(module) + "02T008", tag008);
        String query = QueryBuilder.createUpdateQuery(String.valueOf(module) + "MATM", valueStr, null, null, condition);
        return query;
    }

    public static String insertLeader(String CT02MATNO, String CT02LEADER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT02MATNO", CT02MATNO);
        valueStr.put("CT02LEADER", CT02LEADER.replaceAll("auto", ""));
        String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
        System.out.println(CT02LEADER.trim().length());
        return query;
    }

    public static String deleteCTWORK(String matno, String module) {
        String query = "DELETE FROM " + module + "WORK WHERE " + module + "04MATNO = '" + matno + "'";
        return query;
    }
}
