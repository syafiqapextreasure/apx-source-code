/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.Authority.AuthorityGlobal;

import com.ilmu.cataloging.Bibliography.Bibliography;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Global_Change {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String type = null;
    public String matno = null;
    public String tag = null;
    public String indi1 = null;
    public String indi2 = null;

    public Global_Change(String type, String matno, String tag, String indi1, String indi2) {
        this.type = type;
        this.matno = matno;
        this.tag = tag;
        this.indi1 = indi1;
        this.indi2 = indi2;
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

    public static List<Global_Change> getTagDetails(int pointer) {
        ArrayList<Global_Change> list = new ArrayList<Global_Change>();
        String query = "Select CT02TYPE, CT02MATNO, CT06TAG, CT06INDI1, CT06INDI2 from CTPONT, CTMATM where CT06MATNO = CT02MATNO and CT06POINTER = " + pointer;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Global_Change newGetAllBy = new Global_Change(Handler.CatalogueType(rs.getString("CT02TYPE")), rs.getString("CT02MATNO"), rs.getString("CT06TAG"), rs.getString("CT06INDI1"), rs.getString("CT06INDI2"));
                    list.add(newGetAllBy);
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

    public static boolean CT05SRAW_Exist(String CT05SRAW, String GL17TAG) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(CT05SRAW);
        if (!matcher.matches()) {
            CT05SRAW = CT05SRAW.replace("'", "''");
        }
        String query = " SELECT COUNT(CT05SRAW) AS COUNT FROM " + Bibliography.getMarkTagTableName(GL17TAG) + " where CT05SRAW = '" + CT05SRAW + "'";
        System.out.println(query);
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

    public static String getMatno(int pointer) {
        String query = " SELECT CT06MATNO FROM CTPONT where CT06POINTER = '" + pointer + "'";
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

    public static boolean updateTerm(String term, int pointer, String tag) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        condition.put("CT05POINTER", pointer);
        valueStr.put("CT05SRAW", term);
        String query = QueryBuilder.createUpdateIntQuery(Bibliography.getMarkTagTableName(tag), valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updateIdxBy(String matno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT02MATNO", matno);
        valueStr.put("CT02IDXBY", "GLOBALCHANGE");
        valueStr.put("CT02IDXDATE", DateTime.getTodaySystemDate());
        valueStr.put("CT02IDXTIME", DateTime.getTodaySystemTime());
        String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
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
