/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
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

    public SelectStatement(String Desc) {
        this.Desc = Desc;
    }

    public static List<SelectStatement> selectPatCate() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT  GL07CATE, GL07DESC FROM GLCATE ORDER BY GL07CATE";
        System.out.println("query selectPatCate = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newpatCate = new SelectStatement(rs.getString("GL07CATE"), rs.getString("GL07DESC"));
                    list.add(newpatCate);
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

    public static List<SelectStatement> selectBrnc() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC ORDER BY GL71BRNC";
        System.out.println("query selectBrnc = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newbrnc = new SelectStatement(rs.getString("GL71BRNC"), rs.getString("GL71DESC"));
                    list.add(newbrnc);
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

    public static List<SelectStatement> selectFund() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT FA01FUND,FA01DESC FROM FAMAST ORDER BY FA01FUND";
        System.out.println("query selectFund = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newfund = new SelectStatement(rs.getString("FA01FUND"), rs.getString("FA01DESC"));
                    list.add(newfund);
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

    public static List<SelectStatement> selectchargeData() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL38TXCD, GL38DESC FROM GLTRXC WHERE GL38TXCD LIKE '%0'";
        System.out.println("query selectchargeData = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement libname = new SelectStatement(Handler.ifIsNull(rs.getString("GL38TXCD")), Handler.ifIsNull(rs.getString("GL38DESC")));
                    list.add(libname);
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

    public static List<SelectStatement> selectpaymentData() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL38TXCD, GL38DESC FROM GLTRXC WHERE GL38TXCD LIKE '%1'";
        System.out.println("query selectpaymentData = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement libname = new SelectStatement(Handler.ifIsNull(rs.getString("GL38TXCD")), Handler.ifIsNull(rs.getString("GL38DESC")));
                    list.add(libname);
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

    public static List<SelectStatement> selectOverrideData() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL38TXCD, GL38DESC FROM GLTRXC WHERE GL38TXCD LIKE '%3'";
        System.out.println("query selectpaymentData = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement libname = new SelectStatement(Handler.ifIsNull(rs.getString("GL38TXCD")), Handler.ifIsNull(rs.getString("GL38DESC")));
                    list.add(libname);
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

    public static List<SelectStatement> selectOtherData() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT * FROM GLTRXC WHERE GL38TXCD NOT LIKE '%0' AND GL38TXCD NOT LIKE '%1' AND GL38TXCD NOT LIKE '%3'";
        System.out.println("query selectOtherData = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement libname = new SelectStatement(Handler.ifIsNull(rs.getString("GL38TXCD")), Handler.ifIsNull(rs.getString("GL38DESC")));
                    list.add(libname);
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

    public static List<SelectStatement> selectpaymodeData() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT CODE,DESCRIPTION FROM FNDCODE WHERE FCODE = 'P'";
        System.out.println("query selectpaymodeData = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement libname = new SelectStatement(Handler.ifIsNull(rs.getString("CODE")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                    list.add(libname);
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
        String query = "SELECT  GL47SMD, GL47DESC FROM GLSMD ORDER BY GL47SMD";
        System.out.println("query selectPatCate = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newpatCate = new SelectStatement(rs.getString("GL47SMD"), rs.getString("GL47DESC"));
                    list.add(newpatCate);
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

    public static List<SelectStatement> selectICAT() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT  GL10ICAT, GL10DESC FROM GLICAT ORDER BY GL10ICAT";
        System.out.println("query selectPatCate = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newpatCate = new SelectStatement(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
                    list.add(newpatCate);
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
