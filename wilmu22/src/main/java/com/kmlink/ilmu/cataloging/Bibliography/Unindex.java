/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Unindex {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    static List<String> queryList = new ArrayList<String>();
    String GL17TABNAME = null;
    String CT06POINTER = null;
    String CT06MATNO = null;
    String CT06MARC = null;
    String CT06TAG = null;
    String CT06INDI1 = null;
    String CT06INDI2 = null;
    String CT06SRAW = null;
    String CT06STATUS = null;
    String SUBFDATA = null;
    String GL17DESC = null;

    public Unindex(String CT06POINTER, String GL17TABNAME) {
        this.CT06POINTER = CT06POINTER;
        this.GL17TABNAME = GL17TABNAME;
    }

    public Unindex(String CT06POINTER, String CT06MATNO, String CT06MARC, String CT06TAG, String CT06INDI1, String CT06INDI2, String CT06SRAW, String CT06STATUS) {
        this.CT06POINTER = CT06POINTER;
        this.CT06MATNO = CT06MATNO;
        this.CT06MARC = CT06MARC;
        this.CT06TAG = CT06TAG;
        this.CT06INDI1 = CT06INDI1;
        this.CT06INDI2 = CT06INDI2;
        this.CT06SRAW = CT06SRAW;
        this.CT06STATUS = CT06STATUS;
    }

    public Unindex(String CT06MARC, String CT06TAG, String CT06INDI1, String CT06INDI2, String CT06SRAW, String CT06STATUS, String SUBFDATA, String GL17DESC, String GL17TABNAME) {
        this.CT06MARC = CT06MARC;
        this.CT06TAG = CT06TAG;
        this.CT06INDI1 = CT06INDI1;
        this.CT06INDI2 = CT06INDI2;
        this.CT06SRAW = CT06SRAW;
        this.CT06STATUS = CT06STATUS;
        this.SUBFDATA = SUBFDATA;
        this.GL17DESC = GL17DESC;
        this.GL17TABNAME = GL17TABNAME;
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getCT06POINTER() {
        return this.CT06POINTER;
    }

    public String getCT06MATNO() {
        return this.CT06MATNO;
    }

    public String getCT06MARC() {
        return this.CT06MARC;
    }

    public String getCT06TAG() {
        return this.CT06TAG;
    }

    public String getCT06INDI1() {
        return this.CT06INDI1;
    }

    public String getCT06INDI2() {
        return this.CT06INDI2;
    }

    public String getSUBFDATA() {
        return this.SUBFDATA;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getCT06SRAW() {
        return this.CT06SRAW;
    }

    public String getCT06STATUS() {
        return this.CT06STATUS;
    }

    public static Unindex addCTWORK(String CT04MATNO, String CT04MARC, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, String CT04CREDATE, String CT04CREBY, String CT04MODIDATE, String CT04MODITIME, int CT04COUNTER, String CT04STATUS, String CT04CONV, String CT04CRETIME) throws SQLException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT04MATNO", CT04MATNO);
        valueStr.put("CT04MARC", CT04MARC);
        valueStr.put("CT04TAG", CT04TAG);
        valueStr.put("CT04INDI1", CT04INDI1);
        valueStr.put("CT04INDI2", CT04INDI2);
        valueStr.put("CT04RAW", CT04RAW);
        valueStr.put("CT04CREDATE", CT04CREDATE);
        valueStr.put("CT04CRETIME", CT04CRETIME);
        valueStr.put("CT04CREBY", CT04CREBY);
        valueStr.put("CT04MODIDATE", CT04MODIDATE);
        valueStr.put("CT04MODITIME", CT04MODITIME);
        valueInt.put("CT04COUNTER", CT04COUNTER);
        valueStr.put("CT04STATUS", CT04STATUS);
        valueStr.put("CT04SCONV", CT04CONV);
        String query = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static String updateHits(String CT05POINTER, String tableName) {
        String query = "UPDATE " + tableName + " SET CT05HITS=CT05HITS-1 WHERE CT05POINTER = '" + CT05POINTER + "'";
        System.out.println(query);
        return query;
    }

    public static String updateStat(String CT02MATNO, String CT02STATUS, String tblname) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put(String.valueOf(tblname) + "02MATNO", CT02MATNO);
        valueStr.put(String.valueOf(tblname) + "02STATUS", CT02STATUS);
        String query = QueryBuilder.createUpdateQuery(String.valueOf(tblname) + "MATM", valueStr, null, null, condition);
        return query;
    }

    public static boolean BO_isDeletable(String matno, String tblname) {
        String query = "SELECT COUNT(*) AS Count FROM " + tblname + "PONT WHERE " + tblname + "06MATNO = '" + matno + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    deletable = true;
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
        return deletable;
    }

    public static boolean BO_isExist(String matno, String tag) {
        String query = "SELECT COUNT(CT04MATNO) AS Count FROM CTWORK WHERE CT04MATNO = '" + matno + "' AND CT04TAG='" + tag + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    deletable = true;
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
        return deletable;
    }

    public static boolean RcrdExist(String matno, String module) {
        String query = "SELECT COUNT(" + module + "04MATNO) AS Count FROM " + module + "WORK WHERE " + module + "04MATNO = '" + matno + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    deletable = true;
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
        return deletable;
    }

    public static String checkLeader(String matno) {
        String query = "SELECT CT02LEADER FROM CTMATM WHERE CT02MATNO = '" + matno + "'";
        String leader = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    leader = rs.getString("CT02LEADER");
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
        return leader;
    }

    public static List<Unindex> retrieveRcrd(String matno, String tag) {
        ArrayList<Unindex> list = new ArrayList<Unindex>();
        String query = "Select CT06POINTER, GL17TABNAME from CTPONT, GLTAGP where GL17TAG=CT06TAG and GL17MARC=CT06MARC and CT06MATNO = '" + matno + "' and GL17TAG='" + tag + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Unindex searchTable = new Unindex(rs.getString("CT06POINTER"), rs.getString("GL17TABNAME"));
                    list.add(searchTable);
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
        return list;
    }

    public static List<Unindex> getAllBy(String controlNoInput, String tableName) {
        ArrayList<Unindex> list = new ArrayList<Unindex>();
        String query = "SELECT CT06POINTER, CT06MATNO, CT06MARC, CT06TAG, CT06INDI1, CT06INDI2, CT06STATUS, CT05SRAW FROM CTPONT, " + tableName + " where CT06POINTER=CT05POINTER and CT06MATNO ='" + controlNoInput + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Unindex newSE01_SearchByMatno = new Unindex(rs.getString("CT06POINTER"), rs.getString("CT06MATNO"), rs.getString("CT06MARC"), rs.getString("CT06TAG"), rs.getString("CT06INDI1"), rs.getString("CT06INDI2"), rs.getString("CT05SRAW"), rs.getString("CT06STATUS"));
                    list.add(newSE01_SearchByMatno);
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
        return list;
    }

    public static String deleteCTPONT(String matno, String tablename) {
        String query = "DELETE FROM " + tablename + "PONT WHERE " + tablename + "06MATNO = '" + matno + "'";
        return query;
    }
}
