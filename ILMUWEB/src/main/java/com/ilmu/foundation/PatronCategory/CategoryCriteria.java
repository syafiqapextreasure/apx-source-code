/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronCategory;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryCriteria {
    static Connection con;
    private String patcat = null;
    private String desc = null;

    public CategoryCriteria(String patcat, String desc) {
        this.patcat = patcat;
        this.desc = desc;
    }

    public String getpatcat() {
        return this.patcat;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<CategoryCriteria> searchCateByCode(String criteria) {
        ArrayList<CategoryCriteria> list = new ArrayList<CategoryCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL07CATE, GL07DESC FROM GLCATE WHERE";
        sql = String.valueOf(sql) + " GL07CATE LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CategoryCriteria newSearchItemByCode = new CategoryCriteria(rs.getString("GL07CATE"), rs.getString("GL07DESC"));
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

    public static List<CategoryCriteria> searchCateByDesc(String criteria) {
        ArrayList<CategoryCriteria> list = new ArrayList<CategoryCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL07CATE, GL07DESC FROM GLCATE WHERE";
        sql = String.valueOf(sql) + " GL07DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CategoryCriteria newSearchItemByName = new CategoryCriteria(rs.getString("GL07CATE"), rs.getString("GL07DESC"));
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
