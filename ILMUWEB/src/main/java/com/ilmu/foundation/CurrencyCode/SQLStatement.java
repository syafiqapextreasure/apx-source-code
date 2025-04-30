/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CurrencyCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.DateFormatter;
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
    public Connection connection;

    public List<Foundation> getAllCurrencyCode() throws SQLException {
        ArrayList<Foundation> currencylist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE from GLFORX";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL24FOREX(rs.getString("GL24FOREX"));
                    fnd.setGL24DESC(rs.getString("GL24DESC"));
                    fnd.setGL24PRATE(rs.getString("GL24PRATE"));
                    fnd.setGL24BRATE(rs.getString("GL24BRATE"));
                    currencylist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
        return currencylist;
    }

    public static SQLStatement AddCurrency(String GL24FOREX, String GL24DESC, String GL24PRATE, String GL24BRATE, String GL24PDATE, String GL24BDATE) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        valueStr.put("GL24FOREX", GL24FOREX);
        valueStr.put("GL24DESC", GL24DESC);
        valueDouble.put("GL24PRATE", Double.parseDouble(GL24PRATE));
        valueDouble.put("GL24BRATE", Double.parseDouble(GL24BRATE));
        if (GL24PDATE.isEmpty()) {
            valueStr.put("GL24PDATE", null);
        } else {
            valueStr.put("GL24PDATE", DateFormatter.displayToDBFormat(GL24PDATE));
        }
        if (GL24BDATE.isEmpty()) {
            valueStr.put("GL24BDATE", null);
        } else {
            valueStr.put("GL24BDATE", DateFormatter.displayToDBFormat(GL24BDATE));
        }
        String query = QueryBuilder.createInsertQuery("GLFORX", valueStr, null, valueDouble);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL24FOREX) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        Statement stmt3 = null;
        ResultSet rs3 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CT03FOREX) AS Count FROM CTDOCM, GLFORX WHERE CT03FOREX=GL24FOREX AND GL24FOREX='" + GL24FOREX + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(AC03FOREX) AS Count FROM ACORDD, GLFORX WHERE AC03FOREX=GL24FOREX AND GL24FOREX='" + GL24FOREX + "'";
                    System.out.println(query2);
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(query2);
                    while (rs2.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        String query3 = "SELECT COUNT(SE01FOREX) AS Count FROM SESERM, GLFORX WHERE SE01FOREX=GL24FOREX AND GL24FOREX='" + GL24FOREX + "'";
                        System.out.println(query3);
                        stmt3 = con.createStatement();
                        rs3 = stmt3.executeQuery(query3);
                        while (rs3.next()) {
                            Integer.parseInt(rs.getString("Count"));
                        }
                        deletable = true;
                    }
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

    public static void deleteCurrency(String GL24FOREX) {
        Connection con = null;
        String query = "DELETE FROM GLFORX WHERE GL24FOREX = '" + GL24FOREX + "'";
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
