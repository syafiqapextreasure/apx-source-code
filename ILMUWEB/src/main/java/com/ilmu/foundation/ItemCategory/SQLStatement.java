/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.ItemCategory;

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

    public List<Foundation> getAllItemCat() throws SQLException {
        ArrayList<Foundation> icatlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL10ICAT, GL10DESC from GLICAT";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL10ICAT(rs.getString("GL10ICAT"));
                    fnd.setGL10DESC(rs.getString("GL10DESC"));
                    icatlist.add(fnd);
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
        return icatlist;
    }

    public static SQLStatement AddItemCat(String GL10ICAT, String GL10DESC, String GL10DISPLAY, String GL10ELIG, String GL10UNIT, String GL10RESERVEC) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL10ICAT", GL10ICAT);
        valueStr.put("GL10DESC", GL10DESC);
        valueStr.put("GL10DISPLAY", GL10DISPLAY);
        valueInt.put("GL10ELIG", Integer.parseInt(GL10ELIG));
        valueStr.put("GL10UNIT", GL10UNIT);
        valueStr.put("GL10RESERVEC", GL10RESERVEC);
        String query = QueryBuilder.createInsertQuery("GLICAT", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL10ICAT) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CT03ICAT) AS Count FROM CTDOCM, GLICAT WHERE CT03ICAT=GL10ICAT AND GL10ICAT='" + GL10ICAT + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE06ICAT) AS Count FROM SEISSU, GLICAT WHERE SE06ICAT=GL10ICAT AND GL10ICAT='" + GL10ICAT + "'";
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

    public static void deleteItemCat(String GL10ICAT) {
        Connection con = null;
        String query = "DELETE FROM GLICAT WHERE GL10ICAT = '" + GL10ICAT + "'";
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
