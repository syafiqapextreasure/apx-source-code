/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CurrentUser {
    private String user = null;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public CurrentUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public static String getBrncCode(String user) {
        String query = " Select GL14BRNC from GLPATR where GL14PATR='" + user + "' ";
        System.out.println(query);
        String GL14BRNC = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    GL14BRNC = rs.getString("GL14BRNC");
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
        return GL14BRNC;
    }
}
