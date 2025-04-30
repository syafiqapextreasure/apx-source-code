/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CheckIfExist {
    static Connection con;
    private int Total = 0;

    public CheckIfExist(int Total) {
        this.Total = Total;
    }

    public int getTotal() {
        return this.Total;
    }

    public static List<CheckIfExist> checking(String tableName, String columnName, String value) {
        ArrayList<CheckIfExist> list = new ArrayList<CheckIfExist>();
        Statement stmt = null;
        String query = "SELECT COUNT(" + columnName + ") AS Total FROM " + tableName + " WHERE " + columnName + "='" + value + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int total = Integer.parseInt(rs.getString("Total"));
                    CheckIfExist newSearchPatronByName = new CheckIfExist(total);
                    list.add(newSearchPatronByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
