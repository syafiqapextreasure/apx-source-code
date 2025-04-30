/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckingData {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static String getFunc(String columnName) {
        boolean result = false;
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = '" + columnName + "'";
        System.out.println(sql);
        String data = "";
        try {
            try {
                connection = DBConnection.getConnection();
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery(sql);
                if (rs.next()) {
                    data = rs.getString("GL99VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
