/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.fundacc.FundAcc;

import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FundDistribution {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String FA01FUND = null;
    private String FA01DESC = null;
    private String BALANCE = null;

    public FundDistribution(String FA01FUND, String FA01DESC, String BALANCE) {
        this.FA01FUND = FA01FUND;
        this.FA01DESC = FA01DESC;
        this.BALANCE = BALANCE;
    }

    public String getFA01FUND() {
        return this.FA01FUND;
    }

    public String getFA01DESC() {
        return this.FA01DESC;
    }

    public String getBALANCE() {
        return this.BALANCE;
    }

    public static List<FundDistribution> getListOfAccounting() {
        ArrayList<FundDistribution> list = new ArrayList<FundDistribution>();
        String query = "SELECT FA01FUND,  FA01DESC, FA01SEQC,  FA01MODULE,  (FA01ALLO + FA01INCR) - (FA01DECR+FA01ENCR+FA01COMM+FA01LIAB+FA01PAY) AS BALANCE from FAMAST WHERE FA01MODULE='AC' ORDER BY  FA01SEQC";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    FundDistribution newSubfList = new FundDistribution(rs.getString("FA01FUND"), rs.getString("FA01DESC"), rs.getString("BALANCE"));
                    list.add(newSubfList);
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

    public static String getBalance(String fundcode) {
        String query = "SELECT (FA01ALLO + FA01INCR) - (FA01DECR+FA01ENCR+FA01COMM+FA01LIAB+FA01PAY) AS BALANCE from FAMAST WHERE FA01MODULE='AC' and FA01FUND='" + fundcode + "' ORDER BY  FA01SEQC";
        String balance = "";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    balance = rs.getString("BALANCE");
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
        return balance;
    }

    public static String fundIntegration() {
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='FUNDACC' ";
        String exist = "";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    exist = rs.getString("GL99VALUE");
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
        return exist;
    }

    public static String InsertFund(String FA02FUND, String FA02TXCD, String FA02REF, String vendor, String FA02AMT, String FA02DESC) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        Glnumb no = Glnumb.getGL98VALUE("ACCTXNO");
        valueStr.put("FA02FUND", FA02FUND);
        valueStr.put("FA02TXCD", FA02TXCD);
        valueStr.put("FA02DATE", DateTime.getTodaySystemDate());
        valueStr.put("FA02REF", FA02REF);
        valueStr.put("FA02DESC", FA02DESC);
        valueStr.put("FA02AMT", Handler.decimalConversion(FA02AMT));
        valueInt.put("FA02TXNO", no.getGL98VALUE());
        valueStr.put("FA02WSID", Handler.getLocalIPAdd());
        String query = QueryBuilder.createInsertQuery("FATRAN", valueStr, valueInt, null);
        return query;
    }
}
