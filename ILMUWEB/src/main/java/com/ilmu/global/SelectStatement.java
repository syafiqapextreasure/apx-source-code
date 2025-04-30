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
    private String brnc = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public String getbrnc() {
        return Handler.ifIsNull(this.brnc);
    }

    public SelectStatement(String Code, String Desc) {
        this.Code = Code;
        this.Desc = Desc;
    }

    public SelectStatement(String Code, String Desc, String brnc) {
        this.Code = Code;
        this.Desc = Desc;
        this.brnc = brnc;
    }

    public static List<SelectStatement> selectGroupID() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL02GRP, GL02NAME FROM GLGRMA";
        System.out.println("query selectGroupID = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL02GRP"), rs.getString("GL02NAME"));
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

    public static List<SelectStatement> selectBrncCode() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC";
        System.out.println("query selectBrncCode = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newBrnc = new SelectStatement(Handler.ifIsNull(rs.getString("GL71BRNC")), Handler.ifIsNull(rs.getString("GL71DESC")));
                    list.add(newBrnc);
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

    public static List<SelectStatement> selecttown() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'M' ORDER BY CODE ASC";
        System.out.println("query selecttown = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newtown = new SelectStatement(Handler.ifIsNull(rs.getString("CODE")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                    list.add(newtown);
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

    public static List<SelectStatement> selectmarc() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL16MARC, GL16DESC FROM GLMARC";
        System.out.println("query selecttown = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newtown = new SelectStatement(Handler.ifIsNull(rs.getString("GL16MARC")), Handler.ifIsNull(rs.getString("GL16DESC")));
                    list.add(newtown);
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

    public static List<SelectStatement> selecticat() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL10ICAT, GL10DESC FROM GLICAT";
        System.out.println("query selecticat = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newicat = new SelectStatement(Handler.ifIsNull(rs.getString("GL10ICAT")), Handler.ifIsNull(rs.getString("GL10DESC")));
                    list.add(newicat);
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

    public static List<SelectStatement> selectcond() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL06COND, GL06DESC FROM GLCOND";
        System.out.println("query selectcond = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newcond = new SelectStatement(Handler.ifIsNull(rs.getString("GL06COND")), Handler.ifIsNull(rs.getString("GL06DESC")));
                    list.add(newcond);
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
                    SelectStatement newsmd = new SelectStatement(Handler.ifIsNull(rs.getString("GL47SMD")), Handler.ifIsNull(rs.getString("GL47DESC")));
                    list.add(newsmd);
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

    public static List<SelectStatement> selectStat() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL36STAT, GL36DESC FROM GLDOCS";
        System.out.println("query selectStat = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newstat = new SelectStatement(Handler.ifIsNull(rs.getString("GL36STAT")), Handler.ifIsNull(rs.getString("GL36DESC")));
                    list.add(newstat);
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

    public static List<SelectStatement> infoloca() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL05LOCA, GL05DESC, GL05BRNC FROM GLLOCA ORDER BY GL05BRNC, GL05LOCA";
        System.out.println("query infoloca = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement infoloca = new SelectStatement(Handler.ifIsNull(rs.getString("GL05LOCA")), Handler.ifIsNull(rs.getString("GL05DESC")), Handler.ifIsNull(rs.getString("GL05BRNC")));
                    list.add(infoloca);
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
