/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.cataloging.Bibliography.Bibliography;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.QueryBuilder;
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
    static List<String> queryList = new ArrayList<String>();

    public Indexing(String CT06TAG, String CT06SRAW, int CT06COUNTER) {
        this.CT06TAG = CT06TAG;
        this.CT06SRAW = CT06SRAW;
        this.CT06COUNTER = CT06COUNTER;
    }

    public Indexing(int CT05POINTER) {
        this.CT05POINTER = CT05POINTER;
    }

    public String getCT06TAG() {
        return this.CT06TAG;
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
        String query = " SELECT COUNT(CT05SCONV) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV LIKE '" + CT05SCONV + "'";
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

    public static boolean SCONVDuplicateChk(String CT05SCONV, String GL17TAG) {
        String query = " SELECT COUNT(CT05SCONV) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SCONV= '" + CT05SCONV + "'";
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
        String query = " SELECT COUNT(CT05SRAW) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SRAW LIKE '" + CT05SRAW + "'";
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

    public static boolean CT02MATNO_Exist(String CT02MATNO) {
        String query = " SELECT COUNT(CT02MATNO) AS COUNT FROM CTMATM where CT02MATNO LIKE '" + CT02MATNO + "'";
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
        String query = "SELECT CT05POINTER FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " " + "WHERE CT05SRAW LIKE '" + CT05SRAW + "'";
        Indexing result = null;
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

    public static String updateCT05HITS(String CT05SRAW, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        if (!matcher.matches()) {
            CT05SRAW = CT05SRAW.replace("'", "''");
        }
        String query = "UPDATE " + Bibliography.getMarkTagTableName(GL17TAG) + " SET CT05HITS=CT05HITS+1 " + "WHERE CT05SRAW LIKE '" + CT05SRAW + "'";
        return query;
    }

    public static String addCTTable(int CT05POINTER, String CT05SRAW, String CT05SCONV, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        Matcher matcher1 = pattern.matcher(CT05SCONV);
        CT05SRAW = CT05SRAW.replace("'", "''");
        CT05SCONV = CT05SCONV.replace("'", "''");
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueInt.put("CT05POINTER", CT05POINTER);
        valueStr.put("CT05SRAW", CT05SRAW);
        valueStr.put("CT05SCONV", CT05SCONV);
        valueInt.put("CT05HITS", 1);
        valueInt.put("CT05COUNT", 1);
        valueStr.put("CT05AUTLINK", "T");
        String query = QueryBuilder.createInsertQuery(Bibliography.getMarkTagTableName(GL17TAG), valueStr, valueInt, null);
        return query;
    }

    public static String addCTMATM(String CT02MATNO, String CT02CREBY, String CT02CRDATE, String CT02CRETIME, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT02MATNO", CT02MATNO);
        valueStr.put("CT02IDXDATE", DateTime.displayToDBFormat(CT02MODIDATE));
        valueStr.put("CT02IDXBY", CT02MODIBY);
        valueStr.put("CT02IDXTIME", DateTime.timeToDBFormat(CT02MODITIME));
        valueStr.put("CT02CREBY", CT02CREBY);
        valueStr.put("CT02CRDATE", DateTime.displayToDBFormat(CT02CRDATE));
        valueInt.put("CT02NORESV", 0);
        valueStr.put("CT02STATUS", "A");
        valueStr.put("CT02TYPE", "C");
        valueStr.put("CT02CRETIME", DateTime.timeToDBFormat(CT02CRETIME));
        String query = QueryBuilder.createInsertQuery("CTMATM", valueStr, valueInt, null);
        return query;
    }

    public static String addCTPONT(int CT06POINTER, String CT06MATNO, String CT06TAG, String CT06INDI1, String CT06INDI2, String CT06STATUS) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT06STATUS", CT06STATUS);
        valueStr.put("CT06INDI2", CT06INDI2);
        valueStr.put("CT06INDI1", CT06INDI1);
        valueStr.put("CT06TAG", CT06TAG);
        valueStr.put("CT06MARC", "U");
        valueStr.put("CT06MATNO", CT06MATNO);
        valueInt.put("CT06POINTER", CT06POINTER);
        String query = QueryBuilder.createInsertQuery("CTPONT", valueStr, valueInt, null);
        System.out.println(query);
        return query;
    }

    public static String updateCTMATM(String CT02MATNO, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT02MATNO", CT02MATNO);
        valueStr.put("CT02IDXDATE", DateTime.displayToDBFormat(CT02MODIDATE));
        valueStr.put("CT02IDXBY", CT02MODIBY);
        valueStr.put("CT02STATUS", "A");
        valueStr.put("CT02IDXTIME", DateTime.timeToDBFormat(CT02MODITIME));
        String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
        return query;
    }

    public static String insertLeader(String CT02MATNO, String CT02LEADER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT02MATNO", CT02MATNO);
        valueStr.put("CT02LEADER", CT02LEADER.replaceAll("auto", ""));
        queryList.add(QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition));
        String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
        System.out.println(CT02LEADER.trim().length());
        return query;
    }

    public static String deleteCTWORK(String matno) {
        String query = "DELETE FROM CTWORK WHERE CT04MATNO = '" + matno + "'";
        return query;
    }
}
