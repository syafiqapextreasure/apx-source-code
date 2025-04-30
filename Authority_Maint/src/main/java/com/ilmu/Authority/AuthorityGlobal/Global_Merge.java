/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.Authority.AuthorityGlobal;

import com.ilmu.cataloging.Bibliography.Bibliography;
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Global_Merge {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String type = null;
    public String matno = null;
    public String tag = null;
    public String indi1 = null;
    public String indi2 = null;
    public String CT06MATNO = null;

    public Global_Merge(String CT06MATNO) {
        this.CT06MATNO = CT06MATNO;
    }

    public Global_Merge(String type, String matno, String tag, String indi1, String indi2) {
        this.type = type;
        this.matno = matno;
        this.tag = tag;
        this.indi1 = indi1;
        this.indi2 = indi2;
    }

    public String getCT06MATNO() {
        return this.CT06MATNO;
    }

    public String gettype() {
        return this.type;
    }

    public String getmatno() {
        return this.matno;
    }

    public String gettag() {
        return this.tag;
    }

    public String getindi1() {
        return this.indi1;
    }

    public String getindi2() {
        return this.indi2;
    }

    public static int getHitCount(int pointer, String tag) {
        String query = " SELECT  CT05HITS FROM " + Bibliography.getMarkTagTableName(tag) + " where CT05POINTER = " + pointer;
        System.out.println(query);
        int hitcount = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    hitcount = Integer.parseInt(rs.getString("CT05HITS"));
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
        return hitcount;
    }

    public static int getTotalMatno(int pointer) {
        String query = " SELECT Count(*) as Count FROM CTPONT where CT06POINTER = " + pointer;
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

    public static String getMatnos(int pointer) {
        String query = " SELECT  CT06MATNO FROM CTPONT where CT06POINTER = " + pointer;
        System.out.println(query);
        String matno = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    matno = rs.getString("CT06MATNO");
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
        return matno;
    }

    public static List<Global_Merge> getMatno(int pointer) {
        ArrayList<Global_Merge> list = new ArrayList<Global_Merge>();
        String query = " SELECT  CT06MATNO FROM CTPONT where CT06POINTER = " + pointer;
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            try {
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    Global_Merge CT_getTagTabName = new Global_Merge(rs.getString("CT06MATNO"));
                    list.add(CT_getTagTabName);
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
        return list;
    }

    public static boolean updateHitcount(int pointer, String tag, int hitcount) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        condition.put("CT05POINTER", pointer);
        valueInt.put("CT05HITS", hitcount);
        String query = QueryBuilder.createUpdateIntQuery(Bibliography.getMarkTagTableName(tag), null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updatePointer(int frompointer, int topointer, String tag) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        condition.put("CT06POINTER", frompointer);
        valueInt.put("CT06POINTER", topointer);
        String query = QueryBuilder.createUpdateIntQuery("CTPONT", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updateIdxBy(int topointer) {
        List<Global_Merge> ordering = Global_Merge.getMatno(topointer);
        boolean isSuccess = false;
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        for (Global_Merge i : ordering) {
            condition.put("CT02MATNO", i.getCT06MATNO());
            valueStr.put("CT02IDXBY", "GLOBALMERGE");
            valueStr.put("CT02IDXDATE", DateTime.getTodaySystemDate());
            valueStr.put("CT02IDXTIME", DateTime.getTodaySystemTime());
            String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
            isSuccess = DBQuery.runQuery(query);
        }
        return isSuccess;
    }

    public static boolean deleteTerm(int pointer, String tag) {
        String query = "DELETE FROM " + Bibliography.getMarkTagTableName(tag) + "  WHERE CT05POINTER =" + pointer;
        System.out.println(query);
        boolean done = false;
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
                done = true;
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
        return done;
    }
}
