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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Handler_BO {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    static List<String> queryList = new ArrayList<String>();
    public String tags = null;

    public Handler_BO(String tags) {
        this.tags = tags;
    }

    public static boolean isExistControlNo(int counter) {
        String query = "SELECT COUNT(*) AS Count FROM CTWORK WHERE CT04COUNTER = '" + counter + "' ";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
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

    public static boolean CT04MATNOExist(String CT04MATNO) {
        String query = "SELECT COUNT(CT04MATNO) AS Count FROM CTWORK WHERE CT04MATNO = '" + CT04MATNO + "' ";
        boolean exist = false;
        System.out.println(query);
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

    public static String Delete_CTWORK(String CT04MATNO, String module) {
        String query = "DELETE FROM " + module + "WORK WHERE " + module + "04MATNO = '" + CT04MATNO + "'";
        queryList.add(query);
        return query;
    }

    public static String InsertBO(String CT04MATNO, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, int CT04COUNTER, String CT04SCONV, String CT02CREDATE, String CT02CRETIME, String CT02CREBY, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME, String CT04STAT, String type, String module, String marc) {
        CT04RAW = CT04RAW.replace("'", "''");
        CT04SCONV = CT04SCONV.replace("'", " ");
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        if (CT04SCONV.length() > 255) {
            CT04SCONV = CT04SCONV.substring(0, 255);
        }
        valueStr.put("CT04MATNO", CT04MATNO);
        valueStr.put("CT04MARC", marc);
        valueStr.put("CT04TYPE", type);
        valueStr.put("CT04TAG", CT04TAG);
        System.out.println(String.valueOf(CT04INDI1) + CT04INDI2);
        valueStr.put("CT04INDI1", CT04INDI1);
        valueStr.put("CT04INDI2", CT04INDI2);
        valueStr.put("CT04RAW", CT04RAW);
        if (CT02CRETIME.trim().isEmpty()) {
            valueStr.put("CT04CRETIME", "");
        } else {
            valueStr.put("CT04CRETIME", DateTime.timeToDBFormat(CT02CRETIME));
        }
        valueStr.put("CT04CREDATE", DateTime.displayToDBFormat(CT02CREDATE));
        valueStr.put("CT04CREBY", CT02CREBY);
        valueStr.put("CT04MODIDATE", CT02MODIDATE);
        valueStr.put("CT04MODITIME", CT02MODITIME);
        valueStr.put("CT04MODIBY", CT02MODIBY);
        valueStr.put("CT04STATUS", CT04STAT);
        valueStr.put("CT04SCONV", CT04SCONV);
        valueInt.put("CT04COUNTER", CT04COUNTER);
        String query = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
        return query;
    }

    public static boolean isExistCTCONV(String CT05SRAW, String GL17TAG) {
        String query = "SELECT COUNT(*) AS Count FROM '" + Bibliography.getMarkTagTableName(GL17TAG) + "'" + "WHERE CT05SCONV='" + Handler.convUpperCase(CT05SRAW) + "'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
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

    public static void incCount(String CT05SRAW, String GLl7TAG) {
        String query = "UPDATE '" + Bibliography.getMarkTagTableName(GLl7TAG) + "'SET CT05COUNT=CT05COUNT+1 " + "WHERE CT05SCONV ='" + Handler.convUpperCase(CT05SRAW) + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }

    public static boolean isExistCT05SRAW(String CT05SRAW, String GL17TAG) {
        String query = "SELECT COUNT(*) AS Count FROM '" + Bibliography.getMarkTagTableName(GL17TAG) + "'" + "WHERE CT05SRAW='" + CT05SRAW + "'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
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

    public static void incHits(String CT05SRAW, String GLl7TAG) {
        String query = "UPDATE '" + Bibliography.getMarkTagTableName(GLl7TAG) + "'SET CT05HITS=CT05HITS+1 " + "WHERE CT05SRAW ='" + CT05SRAW + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }
}
