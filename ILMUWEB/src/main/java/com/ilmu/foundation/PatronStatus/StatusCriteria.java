/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronStatus;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatusCriteria {
    static Connection con;
    private String status = null;
    private String desc = null;

    public StatusCriteria(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getstatus() {
        return this.status;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<StatusCriteria> searchStatusByCode(String criteria) {
        ArrayList<StatusCriteria> list = new ArrayList<StatusCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL08STAT, GL08DESC FROM GLSTAT WHERE";
        sql = String.valueOf(sql) + " GL08STAT LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    StatusCriteria newSearchItemByCode = new StatusCriteria(rs.getString("GL08STAT"), rs.getString("GL08DESC"));
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

    public static List<StatusCriteria> searchStatusByDesc(String criteria) {
        ArrayList<StatusCriteria> list = new ArrayList<StatusCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL08STAT, GL08DESC FROM GLSTAT WHERE";
        sql = String.valueOf(sql) + " GL08DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    StatusCriteria newSearchItemByName = new StatusCriteria(rs.getString("GL08STAT"), rs.getString("GL08DESC"));
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
