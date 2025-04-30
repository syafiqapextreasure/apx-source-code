/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.sql;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CirculationSQL {
    public static String getMsLibOpen(String brnc, String day) {
        String sql = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + brnc + "' AND GL37DAY='" + day + "'";
        System.out.println(sql);
        return sql;
    }

    public static String getMsLibOpen_V1(String brnc, String day) {
        String sql = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + brnc + "' AND GL37DAY='" + day + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String libOpen = "";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                libOpen = rs.getString("GL37START");
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return libOpen;
    }

    public static String getMsLibOpen_V1(String brnc, int day) {
        String sql = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + brnc + "' AND GL37DAY='" + Integer.toString(day) + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String libOpen = "";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                libOpen = rs.getString("GL37START");
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return libOpen;
    }

    public static String getMsLibClose(String brnc, String day) {
        String sql = "SELECT GL37STOP FROM GLTIME WHERE GL37BRNC='" + brnc + "' AND GL37DAY='" + day + "'";
        System.out.println(sql);
        return sql;
    }
}
