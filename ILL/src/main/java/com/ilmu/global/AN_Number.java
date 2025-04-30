/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AN_Number {
    public static int Get98VALUE(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='" + value + "'";
        int getnum = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getnum = rs.getInt("GL98VALUE");
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
        return getnum;
    }

    public static boolean updatingbidno(int newbidno, String func) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", func);
        valueInt.put("GL98VALUE", newbidno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
