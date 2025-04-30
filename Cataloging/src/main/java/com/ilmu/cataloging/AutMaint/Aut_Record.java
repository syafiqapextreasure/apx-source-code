/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.AutMaint;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Aut_Record {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static boolean matno_exist(String CT04MATNO) {
        String query = "SELECT COUNT(*) AS MATNO FROM AUWORK WHERE CT04MATNO='" + CT04MATNO + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("MATNO")) <= 0) continue;
                    deletable = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                    stmt.close();
                    rs.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
                stmt.close();
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deletable;
    }

    public static String chkusedterm(String tag, String marc) {
        String query = "SELECT GL17UNUSETAG FROM GLTAGP WHERE GL17MARC = '" + marc + "' AND GL17TAG='" + tag + "'";
        String unused = "";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    unused = rs.getString("GL17UNUSETAG").equals("Y") ? "U" : "T";
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                    stmt.close();
                    rs.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
                stmt.close();
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return unused;
    }
}
