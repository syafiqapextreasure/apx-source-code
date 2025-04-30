/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CodeTable;

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

    public List<Foundation> getAllCodeStat(String value) throws SQLException {
        ArrayList<Foundation> codelist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT FCODE, FNDNAME, FCOLUMN1, FCOLUMN2 FROM FNDSYSTEM WHERE FCODE='" + value + "'";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                System.out.println(String.valueOf(query) + " querySQl");
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setFCODE(rs.getString("FCODE"));
                    fnd.setFNDNAME(rs.getString("FNDNAME"));
                    fnd.setFCOLUMN1(rs.getString("FCOLUMN1"));
                    fnd.setFCOLUMN2(rs.getString("FCOLUMN2"));
                    codelist.add(fnd);
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
        return codelist;
    }

    public List<Foundation> getAllCode(String value) throws SQLException {
        ArrayList<Foundation> codelist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT CODE, DESCRIPTION, FCODE from FNDCODE WHERE FCODE='" + value + "' ORDER BY CODE ASC";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setCODE(rs.getString("CODE"));
                    fnd.setDESCRIPTION(rs.getString("DESCRIPTION"));
                    fnd.setFCODE(rs.getString("FCODE"));
                    codelist.add(fnd);
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
        return codelist;
    }

    public Foundation getCodeById(String CODE, String titlecategory) {
        Foundation fnd = null;
        Connection con = null;
        Statement stmt = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT FCODE, FNAME, CODE, DESCRIPTION FROM FNDCODE WHERE CODE='" + CODE + "' AND FCODE='" + titlecategory + "'");
                System.out.println("SELECT FCODE, FNAME, CODE, DESCRIPTION FROM FNDCODE WHERE CODE='" + CODE + "' AND FCODE='" + titlecategory + "'" + " getData");
                while (rs.next()) {
                    fnd = new Foundation();
                    fnd.setFCODE(rs.getString(1));
                    fnd.setFNAME(rs.getString(2));
                    fnd.setCODE(rs.getString(3));
                    fnd.setDESCRIPTION(rs.getString(4));
                }
            }
            catch (SQLException sQLException) {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                }
                catch (SQLException sQLException2) {}
            }
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException sQLException) {}
        }
        return fnd;
    }

    public static SQLStatement AddCodeTable(String FCODE, String FNAME, String CODE, String DESCRIPTION) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("FCODE", FCODE);
        valueStr.put("FNAME", FNAME);
        valueStr.put("CODE", CODE);
        valueStr.put("DESCRIPTION", DESCRIPTION);
        String query = QueryBuilder.createInsertQuery("FNDCODE", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String CODE, String FCODE) {
        Connection con = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                if (FCODE.equals("B")) {
                    String query = "SELECT COUNT(AC03SOURCE) AS Count FROM ACORDD, FNDCODE WHERE AC03SOURCE=CODE AND CODE='" + CODE + "' AND FCODE ='" + FCODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("C")) {
                    String query = "SELECT COUNT(CT03COND) AS Count FROM CTDOCM, FNDCODE WHERE CT03COND=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("D")) {
                    String query = "SELECT COUNT(GL14DESC) AS Count FROM GLPATR LEFT JOIN FNDCODE ON GL14DESC = CODE WHERE CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("E")) {
                    String query = "SELECT COUNT(CT03STAT) AS Count FROM CTDOCM, FNDCODE WHERE CT03STAT=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("F")) {
                    String query = "SELECT COUNT(AC08REASON) AS Count FROM ACEISX, FNDCODE WHERE AC08REASON=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        String query2 = "SELECT COUNT(SE08REASON) AS Count FROM SEEISP, FNDCODE WHERE SE08REASON=CODE AND CODE='" + CODE + "'";
                        System.out.println(query2);
                        stmt2 = con.createStatement();
                        rs2 = stmt2.executeQuery(query2);
                        while (rs.next()) {
                            Integer.parseInt(rs.getString("Count"));
                        }
                        deletable = true;
                    }
                } else if (FCODE.equals("G")) {
                    String query = "SELECT COUNT(SE01MAIL) AS Count FROM SESERM, FNDCODE WHERE SE01MAIL=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("H")) {
                    String query = "SELECT COUNT(GL17MARC) AS Count FROM GLTAGP, FNDCODE WHERE GL17MARC=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("I")) {
                    String query = "SELECT COUNT(GL14NAMETITLE) AS Count FROM GLPATR, FNDCODE WHERE GL14NAMETITLE=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("J")) {
                    String query = "SELECT COUNT(GL14RACE) AS Count FROM GLPATR, FNDCODE WHERE GL14RACE=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("K")) {
                    String query = "SELECT COUNT(GL14RELIGION) AS Count FROM GLPATR, FNDCODE WHERE GL14RELIGION=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("L")) {
                    String query = "SELECT COUNT(RC01SEMESTER) AS Count FROM RCMASTER, FNDCODE WHERE RC01SEMESTER=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        deletable = true;
                    }
                } else if (FCODE.equals("M")) {
                    String query = "SELECT COUNT(GL14TOWN) AS Count FROM GLPATR, FNDCODE WHERE GL14TOWN=CODE AND CODE='" + CODE + "'";
                    System.out.println(query);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                        String query2 = "SELECT COUNT(GL11TOWN) AS Count FROM GLDEPT, FNDCODE WHERE GL11TOWN=CODE AND CODE='" + CODE + "'";
                        System.out.println(query2);
                        stmt2 = con.createStatement();
                        rs2 = stmt2.executeQuery(query2);
                        while (rs.next()) {
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

    public static void deleteCodeTable(String CODE, String FCODE) {
        Connection con = null;
        String query = "DELETE FROM FNDCODE WHERE CODE = '" + CODE + "' AND FCODE= '" + FCODE + "'";
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
