/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.CurrentUser;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Handler_BO {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static boolean isExistControlNo(int counter) {
        String query = "SELECT COUNT(*) AS Count FROM CTWORK WHERE CT04COUNTER = '" + counter + "' ";
        boolean exist = false;
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
        }
        return exist;
    }

    public static Handler_BO InsertBO(String CT04MATNO, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, int CT04COUNTER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT04MATNO", CT04MATNO);
        valueStr.put("CT04MARC", "U");
        valueStr.put("CT04TAG", CT04TAG);
        valueStr.put("CT04INDI1", CT04INDI1);
        valueStr.put("CT04INDI2", CT04INDI2);
        if (CT04RAW.length() <= 2000) {
            valueStr.put("CT04RAW", CT04RAW);
        } else {
            valueStr.put("CT04RAW1", CT04RAW);
        }
        valueStr.put("CT04CREDATE", DateTime.getTodayDate());
        valueStr.put("CT04CRETIME", DateTime.getTodayTime());
        valueStr.put("CT04CREBY", CurrentUser.getCurrUser());
        valueStr.put("CT04MODIDATE", DateTime.getTodayDate());
        valueStr.put("CT04MODITIME", DateTime.getTodayTime());
        valueStr.put("CT04MODIBY", CurrentUser.getCurrUser());
        valueStr.put("CT04STATUS", "A");
        valueStr.put("CT04SCONV", Handler.convUpperCase(Handler.rawToDisplayFormat(CT04RAW)));
        valueInt.put("CT04COUNTER", CT04COUNTER);
        String query = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean isExistCTCONV(String CT05SRAW, String GL17TABNAME) {
        String query = "SELECT CT05SCONV FROM '" + GL17TABNAME + "'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println(rs.getString("CT05SCONV"));
                    System.out.println(Handler.rawToDisplayFormat(CT05SRAW));
                    if (!rs.getString("CT05SCONV").equals(Handler.rawToDisplayFormat(CT05SRAW))) continue;
                    System.out.println("Testing");
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
}
