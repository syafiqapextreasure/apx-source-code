/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectStatement {
    private String Code = null;
    private String Desc = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public SelectStatement(String Code, String Desc) {
        this.Code = Code;
        this.Desc = Desc;
    }

    public static List<SelectStatement> selectCurrency() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL24FOREX, GL24DESC FROM GLFORX  ORDER BY GL24FOREX ";
        System.out.println("query selectCurrency = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL24FOREX"), rs.getString("GL24DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectBind() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL56BINDTYPE, GL56DESC FROM GLBIND ORDER BY GL56BINDTYPE";
        System.out.println("query selectTransactionCode = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newTransactionCode = new SelectStatement(rs.getString("GL56BINDTYPE"), rs.getString("GL56DESC"));
                    list.add(newTransactionCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectSMD() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL47SMD, GL47DESC FROM GLSMD";
        System.out.println("query selectSMD = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL47SMD"), rs.getString("GL47DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectIcat() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL10ICAT, GL10DESC FROM GLICAT";
        System.out.println("query selectIcat = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectLoca() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL05LOCA, GL05DESC FROM GLLOCA";
        System.out.println("query selectLoca = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL05LOCA"), rs.getString("GL05DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectCon() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL06COND, GL06DESC FROM GLCOND";
        System.out.println("query selectCon = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL06COND"), rs.getString("GL06DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }
}
