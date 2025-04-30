/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.SMD;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllSMD() throws SQLException {
        ArrayList<Foundation> smdlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL47SMD, GL47DESC, GL47DISPLAY from GLSMD";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL47SMD(rs.getString("GL47SMD"));
                    fnd.setGL47DESC(rs.getString("GL47DESC"));
                    fnd.setGL47DISPLAY(rs.getString("GL47DISPLAY"));
                    smdlist.add(fnd);
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
        return smdlist;
    }

    public static boolean CheckIfExist(String GL47SMD) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CT03SMD) AS Count FROM CTDOCM, GLSMD WHERE CT03SMD=GL47SMD AND GL47SMD='" + GL47SMD + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE01SMD) AS Count FROM SESERM, GLSMD WHERE SE01SMD=GL47SMD AND GL47SMD='" + GL47SMD + "'";
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

    public static void deleteSMD(String GL47SMD) {
        Connection con = null;
        String query = "DELETE FROM GLSMD WHERE GL47SMD = '" + GL47SMD + "'";
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
