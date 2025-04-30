/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.BranchCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.Handler;
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

    public List<Foundation> getAllBranchCode() throws SQLException {
        ArrayList<Foundation> branchlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT GL71BRNC, GL71DESC, GL71DISPLAY, GL71TOWN from GLBRNC";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL71BRNC(rs.getString("GL71BRNC"));
                    fnd.setGL71DESC(rs.getString("GL71DESC"));
                    fnd.setGL71DISPLAY(Handler.ifIsNull(rs.getString("GL71DISPLAY")));
                    fnd.setGL71TOWN(Handler.ifIsNull(rs.getString("GL71TOWN")));
                    branchlist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return branchlist;
    }

    public static SQLStatement AddBranch(String GL71BRNC, String GL71DESC, String GL71DISPLAY, String GL71ADD1, String GL71ADD2, String GL71ADD3, String GL71TOWN, String GL71POSCODE, String phonenoadd, String emailadd) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL71BRNC", GL71BRNC);
        valueStr.put("GL71DESC", GL71DESC);
        valueStr.put("GL71DISPLAY", GL71DISPLAY);
        valueStr.put("GL71ADD1", GL71ADD1);
        valueStr.put("GL71ADD2", GL71ADD2);
        valueStr.put("GL71ADD3", GL71ADD3);
        valueStr.put("GL71TOWN", GL71TOWN);
        valueStr.put("GL71POSCODE", GL71POSCODE);
        valueStr.put("GL71HTEL", phonenoadd);
        valueStr.put("GL71IPADD", emailadd);
        String query = QueryBuilder.createInsertQuery("GLBRNC", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL71BRNC) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean deletable = false;
        String query = "SELECT COUNT(GL14BRNC) AS Count FROM GLPATR, GLBRNC WHERE GL14BRNC=GL71BRNC AND GL71BRNC='" + GL71BRNC + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(GL05BRNC) AS Count FROM GLLOCA, GLBRNC WHERE GL05BRNC=GL71BRNC AND GL71BRNC='" + GL71BRNC + "'";
                    System.out.println(query2);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        String query3 = "SELECT COUNT(GL27BRNC) AS Count FROM GLELIG, GLBRNC WHERE GL27BRNC=GL71BRNC AND GL71BRNC='" + GL71BRNC + "'";
                        System.out.println(query3);
                        while (rs.next()) {
                            if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                            String query4 = "SELECT COUNT(GL38BRNC) AS Count FROM GLFINE, GLBRNC WHERE GL38BRNC=GL71BRNC AND GL71BRNC='" + GL71BRNC + "'";
                            System.out.println(query4);
                            while (rs.next()) {
                                Integer.parseInt(rs.getString("Count"));
                            }
                        }
                    }
                    deletable = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deletable;
    }

    public static void deleteBranch(String GL71BRNC) {
        Connection con = null;
        String query = "DELETE FROM GLBRNC WHERE GL71BRNC = '" + GL71BRNC + "'";
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
