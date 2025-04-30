/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.SerialFrequency;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SerialCriteria {
    static Connection con;
    private String serial = null;
    private String desc = null;

    public SerialCriteria(String serial, String desc) {
        this.serial = serial;
        this.desc = desc;
    }

    public String getserial() {
        return this.serial;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<SerialCriteria> searchSerialByCode(String criteria) {
        ArrayList<SerialCriteria> list = new ArrayList<SerialCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL49FREQ, GL49DESC FROM GLFREQ WHERE";
        sql = String.valueOf(sql) + " GL49FREQ LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SerialCriteria newSearchItemByCode = new SerialCriteria(rs.getString("GL49FREQ"), rs.getString("GL49DESC"));
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

    public static List<SerialCriteria> searchSerialByDesc(String criteria) {
        ArrayList<SerialCriteria> list = new ArrayList<SerialCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL49FREQ, GL49DESC FROM GLFREQ WHERE";
        sql = String.valueOf(sql) + " GL49DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SerialCriteria newSearchItemByName = new SerialCriteria(rs.getString("GL49FREQ"), rs.getString("GL49DESC"));
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
