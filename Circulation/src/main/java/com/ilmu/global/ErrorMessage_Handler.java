/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ErrorMessage_Handler {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String GL79ERRMSG = null;

    public ErrorMessage_Handler(String GL79ERRMSG) {
        this.GL79ERRMSG = GL79ERRMSG;
    }

    public String getGL79ERRMSG() {
        return this.GL79ERRMSG;
    }

    public static List<ErrorMessage_Handler> getGL79ERRMSG(String GL79ERRCODE) {
        ArrayList<ErrorMessage_Handler> list = new ArrayList<ErrorMessage_Handler>();
        System.out.println(String.valueOf(GL79ERRCODE) + " GL79ERRCODE");
        String query = "Select GL79ERRMSG FROM GLERRMSTR WHERE GL79LANGCODE='001' AND GL79ERRCODE='" + GL79ERRCODE + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ErrorMessage_Handler errorMessage = new ErrorMessage_Handler(rs.getString("GL79ERRMSG"));
                    list.add(errorMessage);
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
}
