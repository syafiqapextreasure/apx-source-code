/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CurrencyCode;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCriteria {
    static Connection con;
    private String currency = null;
    private String desc = null;
    private String pubrate = null;
    private String bank = null;

    public CurrencyCriteria(String currency, String desc, String pubrate, String bank) {
        this.currency = currency;
        this.desc = desc;
        this.pubrate = pubrate;
        this.bank = bank;
    }

    public String getcurrency() {
        return this.currency;
    }

    public String getdesc() {
        return this.desc;
    }

    public String getpubrate() {
        return this.pubrate;
    }

    public String getbank() {
        return this.bank;
    }

    public static List<CurrencyCriteria> searchCurrencyByCode(String criteria) {
        ArrayList<CurrencyCriteria> list = new ArrayList<CurrencyCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE FROM GLFORX WHERE";
        sql = String.valueOf(sql) + " GL24FOREX LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CurrencyCriteria newSearchItemByCode = new CurrencyCriteria(rs.getString("GL24FOREX"), rs.getString("GL24DESC"), rs.getString("GL24PRATE"), rs.getString("GL24BRATE"));
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

    public static List<CurrencyCriteria> searchCurrencyByDesc(String criteria) {
        ArrayList<CurrencyCriteria> list = new ArrayList<CurrencyCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE FROM GLFORX WHERE";
        sql = String.valueOf(sql) + " GL24DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CurrencyCriteria newSearchItemByName = new CurrencyCriteria(rs.getString("GL24FOREX"), rs.getString("GL24DESC"), rs.getString("GL24PRATE"), rs.getString("GL24BRATE"));
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
