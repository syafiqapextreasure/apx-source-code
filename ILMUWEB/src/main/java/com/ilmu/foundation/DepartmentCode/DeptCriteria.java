/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.DepartmentCode;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeptCriteria {
    static Connection con;
    private String code = null;
    private String name = null;

    public DeptCriteria(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getcode() {
        return this.code;
    }

    public String getname() {
        return this.name;
    }

    public static List<DeptCriteria> searchByCode(String criteria) {
        ArrayList<DeptCriteria> list = new ArrayList<DeptCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL11DEPT, GL11NAME FROM GLDEPT WHERE";
        sql = String.valueOf(sql) + " GL11DEPT LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DeptCriteria newSearchSubjByStart = new DeptCriteria(rs.getString("GL11DEPT"), rs.getString("GL11NAME"));
                    list.add(newSearchSubjByStart);
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

    public static List<DeptCriteria> searchByName(String criteria) {
        ArrayList<DeptCriteria> list = new ArrayList<DeptCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL11DEPT, GL11NAME FROM GLDEPT WHERE";
        sql = String.valueOf(sql) + " GL11NAME LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DeptCriteria newSearchSubjByEnd = new DeptCriteria(rs.getString("GL11DEPT"), rs.getString("GL11NAME"));
                    list.add(newSearchSubjByEnd);
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
