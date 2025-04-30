/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static String getVersion(String program, String module, String name) {
        System.out.println("program: " + program + "___________module: " + module + "_________name: " + name);
        String version = null;
        String query = "Select GL22VERSION FROM GLMENU WHERE GL22MODULE='" + module + "' " + "AND GL22PROG = '" + program + "' AND GL22NAME='" + name + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    version = rs.getString("GL22VERSION");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return version;
    }
}
