/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronStatus;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllPatStatus() throws SQLException {
        ArrayList<Foundation> statlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL08STAT, GL08DESC from GLSTAT";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL08STAT(rs.getString("GL08STAT"));
                    fnd.setGL08DESC(rs.getString("GL08DESC"));
                    statlist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return statlist;
    }

    public static SQLStatement AddStatus(String GL08STAT, String GL08DESC, String GL08ACTION1, String GL08ACTION2, String GL08ACTION3, String GL08ACTION4, String GL08ACTION5, String GL08ACTION6, String GL08ACTION7, String GL08ACTION8, String GL08ACTION9, String GL08ACTION10, String GL08ACTION11, String GL08ACTION12, String GL08ACTION13, String GL08ACTION14, String GL08ACTION15, String GL08ACTION16, String GL08ACTION17, String GL08ACTION18, String GL08ACTION19, String GL08ACTION20) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL08STAT", GL08STAT);
        valueStr.put("GL08DESC", GL08DESC);
        valueStr.put("GL08ACTION1", GL08ACTION1);
        valueStr.put("GL08ACTION2", GL08ACTION2);
        valueStr.put("GL08ACTION3", GL08ACTION3);
        valueStr.put("GL08ACTION4", GL08ACTION4);
        valueStr.put("GL08ACTION5", GL08ACTION5);
        valueStr.put("GL08ACTION6", GL08ACTION6);
        valueStr.put("GL08ACTION7", GL08ACTION7);
        valueStr.put("GL08ACTION8", GL08ACTION8);
        valueStr.put("GL08ACTION9", GL08ACTION9);
        valueStr.put("GL08ACTION10", GL08ACTION10);
        valueStr.put("GL08ACTION11", GL08ACTION11);
        valueStr.put("GL08ACTION12", GL08ACTION12);
        valueStr.put("GL08ACTION13", GL08ACTION13);
        valueStr.put("GL08ACTION14", GL08ACTION14);
        valueStr.put("GL08ACTION15", GL08ACTION15);
        valueStr.put("GL08ACTION16", GL08ACTION16);
        valueStr.put("GL08ACTION17", GL08ACTION17);
        valueStr.put("GL08ACTION18", GL08ACTION18);
        valueStr.put("GL08ACTION19", GL08ACTION19);
        valueStr.put("GL08ACTION20", GL08ACTION20);
        String query = QueryBuilder.createInsertQuery("GLSTAT", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL08STAT) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(GL14STAT) AS Count FROM GLPATR, GLSTAT WHERE GL14STAT=GL08STAT AND GL08STAT='" + GL08STAT + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    deletable = true;
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
        return deletable;
    }

    public static void deleteStat(String GL08STAT) {
        Connection con = null;
        String query = "DELETE FROM GLSTAT WHERE GL08STAT = '" + GL08STAT + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement delete = con.prepareStatement(query);
                delete.execute();
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
    }
}
