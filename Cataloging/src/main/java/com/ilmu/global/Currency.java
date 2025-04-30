/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Currency {
    private String forexCode = null;
    private String forexDesc = null;
    private double pubRate = 0.0;
    private double bankRate = 0.0;
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public Currency(String forexCode, String forexDesc, double pubRate, double bankRate) {
        this.forexCode = forexCode;
        this.forexDesc = forexDesc;
        this.pubRate = pubRate;
        this.bankRate = bankRate;
    }

    public Currency(String forexCode, String forexDesc) {
        this.forexCode = forexCode;
        this.forexDesc = forexDesc;
    }

    public String getCode() {
        return this.forexCode;
    }

    public String getDesc() {
        return this.forexDesc;
    }

    public double getPubRate() {
        return this.pubRate;
    }

    public double getBankRate() {
        return this.bankRate;
    }

    public static List<String> getCodeList() {
        ArrayList<String> codeList = new ArrayList<String>();
        String query = "SELECT GL24FOREX FROM GLFORX";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    codeList.add(rs.getString("GL24FOREX"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return codeList;
    }

    public static List<Currency> getForexPairList() {
        ArrayList<Currency> list = new ArrayList<Currency>();
        String query = "SELECT GL24FOREX, GL24DESC FROM GLFORX";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Currency newCurrency = new Currency(rs.getString("GL24FOREX"), rs.getString("GL24DESC"));
                    list.add(newCurrency);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Currency> getList() {
        ArrayList<Currency> list = new ArrayList<Currency>();
        String query = "SELECT GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE FROM GLFORX";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Currency newCurrency = new Currency(rs.getString("GL24FOREX"), rs.getString("GL24DESC"), Double.parseDouble(rs.getString("GL24PRATE")), Double.parseDouble(rs.getString("GL24BRATE")));
                    list.add(newCurrency);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getDesc(String forexCode) {
        String result = null;
        String query = "SELECT GL24DESC FROM GLFORX WHERE GL24FOREX = '" + forexCode + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL24DESC");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static double getRate(String forexCode, String forexType) {
        double rate = 0.0;
        String query = null;
        if (forexType == "Publication") {
            query = "SELECT GL24PRATE FROM GLFORX WHERE GL24FOREX ='" + forexCode + "'";
        } else if (forexType == "Bank") {
            query = "SELECT GL24BRATE FROM GLFORX WHERE GL24FOREX ='" + forexCode + "'";
        }
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (forexType == "Publication") {
                        rate = Double.parseDouble(rs.getString("GL24PRATE"));
                        continue;
                    }
                    if (forexType != "Bank") continue;
                    rate = Double.parseDouble(rs.getString("GL24BRATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rate;
    }

    public static double getPubRate(String forexCode) {
        return Currency.getRate(forexCode, "Publication");
    }

    public static double getBankRate(String forexCode) {
        return Currency.getRate(forexCode, "Bank");
    }

    public static boolean isCodeExist(String forexCode) {
        int result;
        block12: {
            result = 0;
            String query = "SELECT GL24FOREX FROM GLFORX WHERE GL24FOREX = '" + forexCode + "'";
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        ++result;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result == 1;
    }

    public static double computeForeignPrice(double localPrice, double rate) {
        return localPrice * rate;
    }

    public static double computeLocalPrice(double foreignPrice, double rate) {
        return foreignPrice * rate;
    }
}
