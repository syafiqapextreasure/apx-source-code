/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.Receipting;

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatronService {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static String getPatronName(String patronId) throws Exception {
        DBConnection db = new DBConnection();
        Connection conn = DBConnection.getConnection();
        String name = "";
        String sql = "SELECT GL14NAME FROM GLPATR WHERE GL14PATR='" + patronId + "'";
        PreparedStatement prepSql = conn.prepareStatement(sql);
        boolean prepIndex = true;
        ResultSet rs = prepSql.executeQuery();
        while (rs.next()) {
            name = rs.getString("GL14NAME");
        }
        conn.close();
        return name;
    }

    public static String getPatronExpDate(String patronId) throws Exception {
        DBConnection db = new DBConnection();
        Connection conn = DBConnection.getConnection();
        String name = "";
        String sql = "SELECT GL14EXPDATE FROM GLPATR WHERE GL14PATR='" + patronId + "'";
        PreparedStatement prepSql = conn.prepareStatement(sql);
        boolean prepIndex = true;
        System.out.println("SQL" + sql);
        ResultSet rs = prepSql.executeQuery();
        while (rs.next()) {
            name = DateTime.DBToDisplayFormat(rs.getString("GL14EXPDATE"));
        }
        conn.close();
        return name;
    }
}
