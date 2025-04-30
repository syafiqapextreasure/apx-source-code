/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.SerialFrequency;

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

    public List<Foundation> getAllSerial() throws SQLException {
        ArrayList<Foundation> seriallist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL49FREQ, GL49DESC, GL49TIME, GL49ALERT, GL49PTYPE from GLFREQ";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL49FREQ(rs.getString("GL49FREQ"));
                    fnd.setGL49DESC(rs.getString("GL49DESC"));
                    fnd.setGL49TIME(rs.getString("GL49TIME"));
                    fnd.setGL49ALERT(rs.getString("GL49ALERT"));
                    fnd.setGL49PTYPE(rs.getString("GL49PTYPE"));
                    seriallist.add(fnd);
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
        return seriallist;
    }

    public static SQLStatement AddSerial(String GL49FREQ, String GL49DESC, String GL49TIME, String GL49ALERT, String GL49PTYPE) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL49FREQ", GL49FREQ);
        valueStr.put("GL49DESC", GL49DESC);
        valueInt.put("GL49TIME", Integer.parseInt(GL49TIME));
        valueInt.put("GL49ALERT", Integer.parseInt(GL49ALERT));
        valueStr.put("GL49PTYPE", GL49PTYPE);
        String query = QueryBuilder.createInsertQuery("GLFREQ", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL49FREQ) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        Object stmt3 = null;
        Object rs3 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(SE01FREQ) AS Count FROM SESERM, GLFREQ WHERE SE01FREQ=GL49FREQ AND GL49FREQ='" + GL49FREQ + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE03FREQ) AS Count FROM SEORDD, GLFREQ WHERE SE03FREQ=GL49FREQ AND GL49FREQ='" + GL49FREQ + "'";
                    System.out.println(query2);
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(query2);
                    while (rs2.next()) {
                        Integer.parseInt(rs.getString("Count"));
                    }
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

    public static void deleteSerial(String GL49FREQ) {
        Connection con = null;
        String query = "DELETE FROM GLFREQ WHERE GL49FREQ = '" + GL49FREQ + "'";
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
