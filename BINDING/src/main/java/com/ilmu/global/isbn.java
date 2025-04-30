/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class isbn {
    private String count = null;

    public String getcount() {
        return Handler.ifIsNull(this.count);
    }

    public isbn(String count) {
        this.count = count;
    }

    public static String getValidateStatus() {
        String sql = "SELECT GL17STOP FROM GLTAGP WHERE GL17TAG = '020' AND GL17MARC = 'U'";
        System.out.println("SQL CHECK VALIDATE : " + sql);
        String validateStatus = "";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                ResultSet rs = stmt1.executeQuery(sql);
                while (rs.next()) {
                    validateStatus = rs.getString("GL17STOP");
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
        return validateStatus;
    }

    public static List<isbn> reqChecking(String isbnValue) {
        ArrayList<isbn> count = new ArrayList<isbn>();
        String query = "SELECT COUNT(CT05SCONV) AS COUNT FROM CTINDX WHERE CT05SCONV = '" + isbnValue + "'";
        System.out.println("query COUNT isbn = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    isbn viewCount = new isbn(Handler.ifIsNull(rs.getString("COUNT")));
                    count.add(viewCount);
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
        return count;
    }
}
