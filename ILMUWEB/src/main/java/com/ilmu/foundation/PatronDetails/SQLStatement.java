/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronDetails;

import com.ilmu.foundation.PatronDetails.Foundation;
import com.ilmu.foundation.global.Encrypt;
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

    public List<Foundation> getAllPatronDetails() throws SQLException {
        ArrayList<Foundation> pdlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT TOP 100 GL14PATR, GL14NAME, CASE  WHEN GL14ABBR IS NULL THEN '-'END AS GL14ABBR from GLPATR";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL14PATR(rs.getString("GL14PATR"));
                    fnd.setGL14NAME(rs.getString("GL14NAME"));
                    fnd.setGL14ABBR(rs.getString("GL14ABBR"));
                    pdlist.add(fnd);
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
        return pdlist;
    }

    public static boolean CheckIfExist(String GL14PATR) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        Statement stmt3 = null;
        ResultSet rs3 = null;
        Statement stmt4 = null;
        ResultSet rs4 = null;
        Statement stmt5 = null;
        ResultSet rs5 = null;
        Statement stmt6 = null;
        ResultSet rs6 = null;
        Statement stmt7 = null;
        ResultSet rs7 = null;
        Statement stmt8 = null;
        ResultSet rs8 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CI02PATR) AS Count FROM GLPATR, CICIRC WHERE CI02PATR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(CI03PATR) AS Count FROM GLPATR, CIRESV WHERE CI03PATR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                    System.out.println(query2);
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(query2);
                    while (rs2.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        String query3 = "SELECT COUNT(CT03PATR) AS Count FROM GLPATR, CTDOCM WHERE CT03PATR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                        System.out.println(query3);
                        stmt3 = con.createStatement();
                        rs3 = stmt3.executeQuery(query3);
                        while (rs3.next()) {
                            if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                            String query4 = "SELECT COUNT(OP02PATR) AS Count FROM GLPATR, OPSBOX WHERE OP02PATR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                            System.out.println(query4);
                            stmt4 = con.createStatement();
                            rs4 = stmt4.executeQuery(query4);
                            while (rs4.next()) {
                                if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                                String query5 = "SELECT COUNT(RE01PATR) AS Count FROM GLPATR, RETRXN WHERE RE01PATR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                                System.out.println(query5);
                                stmt5 = con.createStatement();
                                rs5 = stmt5.executeQuery(query5);
                                while (rs5.next()) {
                                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                                    String query6 = "SELECT COUNT(AC01REQUESTOR) AS Count FROM GLPATR, ACREQC WHERE AC01REQUESTOR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                                    System.out.println(query6);
                                    stmt6 = con.createStatement();
                                    rs6 = stmt6.executeQuery(query6);
                                    while (rs6.next()) {
                                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                                        String query7 = "SELECT COUNT(SE02REQUESTOR) AS Count FROM GLPATR, SEREQC WHERE SE02REQUESTOR=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                                        System.out.println(query7);
                                        stmt7 = con.createStatement();
                                        rs7 = stmt7.executeQuery(query7);
                                        while (rs7.next()) {
                                            if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                                            String query8 = "SELECT COUNT(GL34RECEIVER) AS Count FROM GLPATR, GLMAIL WHERE GL34RECEIVER=GL14PATR AND GL14PATR='" + GL14PATR + "'";
                                            System.out.println(query8);
                                            stmt8 = con.createStatement();
                                            rs8 = stmt8.executeQuery(query8);
                                            while (rs8.next()) {
                                                Integer.parseInt(rs.getString("Count"));
                                            }
                                            deletable = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                    con.commit();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
                con.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deletable;
    }

    public static void deletePatDetail(String GL14PATR) {
        Connection con = null;
        String query = "DELETE FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "'";
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
                    con.commit();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
                con.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static SQLStatement addPatronDetail(String GL14PATR, String GL14NAME, String GL14PASSWORD) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("GL14PATR", GL14PATR);
        valueStr.put("GL14NAME", GL14NAME);
        valueStr.put("GL14PASSWORD", Encrypt.crypt(GL14PASSWORD));
        String query = QueryBuilder.createInsertQuery("GLPATR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }
}
