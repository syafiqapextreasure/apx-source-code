/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.SMD;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SMDCriteria {
    static Connection con;
    private String smd = null;
    private String desc = null;

    public SMDCriteria(String smd, String desc) {
        this.smd = smd;
        this.desc = desc;
    }

    public String getsmd() {
        return this.smd;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<SMDCriteria> searchSmdByCode(String criteria) {
        ArrayList<SMDCriteria> list = new ArrayList<SMDCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL47SMD, GL47DESC FROM GLSMD WHERE";
        sql = String.valueOf(sql) + " GL47SMD LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SMDCriteria newSearchItemByCode = new SMDCriteria(rs.getString("GL47SMD"), rs.getString("GL47DESC"));
                    list.add(newSearchItemByCode);
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
        return list;
    }

    public static List<SMDCriteria> searchSmdByDesc(String criteria) {
        ArrayList<SMDCriteria> list = new ArrayList<SMDCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL47SMD, GL47DESC FROM GLSMD WHERE";
        sql = String.valueOf(sql) + " GL47DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SMDCriteria newSearchItemByName = new SMDCriteria(rs.getString("GL47SMD"), rs.getString("GL47DESC"));
                    list.add(newSearchItemByName);
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
        return list;
    }
}
