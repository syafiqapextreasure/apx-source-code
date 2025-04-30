/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static String getVersion(String program, String module, String name) {
        String version = null;
        String query = "Select GL22VERSION FROM GLMENU WHERE GL22MODULE='" + module + "' " + "AND GL22PROG = '" + program + "' AND GL22NAME='" + name + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    version = rs.getString("GL22VERSION");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return version;
    }
}
