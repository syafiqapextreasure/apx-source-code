/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.ItemCategory;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemCriteria {
    static Connection con;
    private String itemcode = null;
    private String desc = null;

    public ItemCriteria(String itemcode, String desc) {
        this.itemcode = itemcode;
        this.desc = desc;
    }

    public String getitemcode() {
        return this.itemcode;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<ItemCriteria> searchItemByCode(String criteria) {
        ArrayList<ItemCriteria> list = new ArrayList<ItemCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL10ICAT, GL10DESC FROM GLICAT WHERE";
        sql = String.valueOf(sql) + " GL10ICAT LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    ItemCriteria newSearchItemByCode = new ItemCriteria(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
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

    public static List<ItemCriteria> searchItemByDesc(String criteria) {
        ArrayList<ItemCriteria> list = new ArrayList<ItemCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL10ICAT, GL10DESC FROM GLICAT WHERE";
        sql = String.valueOf(sql) + " GL10DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    ItemCriteria newSearchItemByName = new ItemCriteria(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
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
