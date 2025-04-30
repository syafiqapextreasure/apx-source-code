/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.BranchCode;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BranchCriteria {
    static Connection con;
    private String branchCode = null;
    private String desc = null;
    private String display = null;

    public BranchCriteria(String branchCode, String desc, String display) {
        this.branchCode = branchCode;
        this.desc = desc;
        this.display = display;
    }

    public String getbranchCode() {
        return this.branchCode;
    }

    public String getdesc() {
        return this.desc;
    }

    public String getdisplay() {
        return this.display;
    }

    public static List<BranchCriteria> searchBranchByCode(String criteria) {
        ArrayList<BranchCriteria> list = new ArrayList<BranchCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL71BRNC, GL71DESC, GL71DISPLAY FROM GLBRNC WHERE";
        sql = String.valueOf(sql) + " GL71BRNC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    BranchCriteria newSearchBranchByCode = new BranchCriteria(rs.getString("GL71BRNC"), rs.getString("GL71DESC"), rs.getString("GL71DISPLAY"));
                    list.add(newSearchBranchByCode);
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

    public static List<BranchCriteria> searchBranchByName(String criteria) {
        ArrayList<BranchCriteria> list = new ArrayList<BranchCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL71BRNC, GL71DESC, GL71DISPLAY FROM GLBRNC WHERE";
        sql = String.valueOf(sql) + " GL71DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    BranchCriteria newSearchBranchByName = new BranchCriteria(rs.getString("GL71BRNC"), rs.getString("GL71DESC"), rs.getString("GL71DISPLAY"));
                    list.add(newSearchBranchByName);
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
