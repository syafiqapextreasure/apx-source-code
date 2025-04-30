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

public class CirActSearchCriteria {
    private String Code = null;
    private String Desc = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public CirActSearchCriteria(String Code, String Desc) {
        this.Code = Code;
        this.Desc = Desc;
    }

    public CirActSearchCriteria(String Desc) {
        this.Desc = Desc;
    }

    public static List<CirActSearchCriteria> selectPatCate() {
        ArrayList<CirActSearchCriteria> list = new ArrayList<CirActSearchCriteria>();
        String query = "SELECT  GL07CATE, GL07DESC FROM GLCATE ORDER BY GL07CATE";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CirActSearchCriteria newpatCate = new CirActSearchCriteria(Handler.ifIsNull(rs.getString("GL07CATE")), Handler.ifIsNull(rs.getString("GL07DESC")));
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

    public static List<CirActSearchCriteria> selectBrnc() {
        ArrayList<CirActSearchCriteria> list = new ArrayList<CirActSearchCriteria>();
        String query = "SELECT GL71BRNC, GL71DESC FROM GLBRNC ORDER BY GL71BRNC";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CirActSearchCriteria newbrnc = new CirActSearchCriteria(Handler.ifIsNull(rs.getString("GL71BRNC")), Handler.ifIsNull(rs.getString("GL71DESC")));
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

    public static List<CirActSearchCriteria> selectSMD() {
        ArrayList<CirActSearchCriteria> list = new ArrayList<CirActSearchCriteria>();
        String query = "SELECT  GL47SMD, GL47DESC FROM GLSMD ORDER BY GL47SMD";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CirActSearchCriteria newpatCate = new CirActSearchCriteria(Handler.ifIsNull(rs.getString("GL47SMD")), Handler.ifIsNull(rs.getString("GL47DESC")));
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

    public static List<CirActSearchCriteria> selectICAT() {
        ArrayList<CirActSearchCriteria> list = new ArrayList<CirActSearchCriteria>();
        String query = "SELECT  GL10ICAT, GL10DESC FROM GLICAT ORDER BY GL10ICAT";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CirActSearchCriteria newpatCate = new CirActSearchCriteria(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
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

    public static List<CirActSearchCriteria> selectLocation() {
        ArrayList<CirActSearchCriteria> list = new ArrayList<CirActSearchCriteria>();
        String query = "SELECT GL05LOCA, GL05DESC FROM GLLOCA ORDER BY GL05LOCA";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CirActSearchCriteria newbrnc = new CirActSearchCriteria(Handler.ifIsNull(rs.getString("GL05LOCA")), Handler.ifIsNull(rs.getString("GL05DESC")));
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
}
