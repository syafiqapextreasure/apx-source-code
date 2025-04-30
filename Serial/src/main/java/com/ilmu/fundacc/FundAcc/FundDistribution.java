/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.fundacc.FundAcc;

import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        String query = "SELECT FA01FUND,  FA01DESC, FA01SEQC,  FA01MODULE,  (FA01ALLO + FA01INCR) - (FA01DECR+FA01ENCR+FA01COMM+FA01LIAB+FA01PAY) AS BALANCE from FAMAST WHERE (FA01MODULE = 'SE' OR FA01MODULE = 'BT') ORDER BY  FA01SEQC";
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
        String balance;
        block12: {
            String query = "SELECT (FA01ALLO + FA01INCR) - (FA01DECR+FA01ENCR+FA01COMM+FA01LIAB+FA01PAY) AS BALANCE from FAMAST WHERE FA01MODULE='SE' and FA01FUND='" + fundcode + "' ORDER BY  FA01SEQC";
            balance = "";
            System.out.println(query);
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    if (!rs.next()) {
                        balance = "0.00";
                        break block12;
                    }
                    balance = rs.getString("BALANCE");
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

    public static String InsertFund(String FA02FUND, String FA02TXCD, String FA02REF, String vendor, String FA02AMT, String FA02DESC, int FA02TXNO) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        Glnumb no = Glnumb.getGL98VALUE("ACCTXNO");
        valueStr.put("FA02FUND", FA02FUND);
        valueStr.put("FA02TXCD", FA02TXCD);
        valueStr.put("FA02DATE", DateTime.getTodaySystemDate());
        valueStr.put("FA02REF", FA02REF);
        valueStr.put("FA02DESC", FA02DESC);
        valueStr.put("FA02AMT", Handler.decimalConversion(FA02AMT));
        valueInt.put("FA02TXNO", FA02TXNO);
        valueStr.put("FA02WSID", Handler.getLocalIPAdd());
        String query = QueryBuilder.createInsertQuery("FATRAN", valueStr, valueInt, null);
        return query;
    }

    public static boolean UpdateFAPost(String procVal, String FundAccount, double Amount) {
        boolean isSuccess = true;
        String query = "SELECT FA01FUND, FA01ALLO, FA01INCR, FA01DECR, FA01COMM, FA01ENCR, FA01LIAB, FA01PAY FROM FAMAST WHERE FA01FUND='" + FundAccount + "'";
        System.out.println("query GET FAMAST : " + query);
        Connection conn = null;
        double newAmount = 0.0;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ResultSetMetaData meta = rs.getMetaData();
                    String proc = procVal;
                    int i = 0;
                    while (i < 20) {
                        char sProcType = proc.charAt(i);
                        if (sProcType == '1') {
                            newAmount = rs.getDouble(i + 2) + Amount;
                            FundDistribution.updatingNewAmount(meta.getColumnName(i + 2), newAmount, FundAccount);
                        } else if (sProcType == '2') {
                            newAmount = rs.getDouble(i + 2) - Amount;
                            FundDistribution.updatingNewAmount(meta.getColumnName(i + 2), newAmount, FundAccount);
                        }
                        ++i;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public static boolean updatingNewAmount(String column, double newAmount, String FundAccount) {
        HashMap<String, Double> valuedouble = new HashMap<String, Double>();
        HashMap<String, String> condition = new HashMap<String, String>();
        valuedouble.put(column, newAmount);
        condition.put("FA01FUND", FundAccount);
        String query = QueryBuilder.createUpdateQuery("FAMAST", null, null, valuedouble, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(query);
        return isSuccess;
    }

    public static String GetprocVal(String TransactionCode) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT FA03PROC FROM FACODE WHERE FA03TXCD = '" + TransactionCode + "'";
        System.out.println("SQL GetprocVal" + sSQLStmt);
        String getprocVal = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getprocVal = Handler.ifIsNull(rs.getString("FA03PROC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getprocVal;
    }

    public static boolean FAPostTrx(String FundAccount, String TransactionCode, double Amount) {
        String procVal = FundDistribution.GetprocVal(TransactionCode);
        System.out.println("procVal " + procVal);
        FundDistribution.UpdateFAPost(procVal, FundAccount, Amount);
        boolean isSuccess = true;
        return isSuccess;
    }

    public static double GetAmt(String TransactionCode, double amount) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT FA01COMM FROM FAMAST WHERE fa01fund = '" + TransactionCode + "'";
        System.out.println("SQL GetprocVal" + sSQLStmt);
        double newAmt = 0.0;
        String getprocVal = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    newAmt = Double.parseDouble(rs.getString("FA01COMM")) + amount;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newAmt;
    }

    public static boolean insertComm(double amount, String fundcode) {
        HashMap valueStr = new HashMap();
        HashMap<String, Double> valuedouble = new HashMap<String, Double>();
        HashMap<String, String> condition = new HashMap<String, String>();
        double newAmt = 0.0;
        newAmt = FundDistribution.GetAmt(fundcode, amount);
        valuedouble.put("FA01COMM", newAmt);
        condition.put("FA01FUND", fundcode);
        String query = QueryBuilder.createUpdateQuery("FAMAST", null, null, valuedouble, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static String InsertSEDIST(String ref, String txcd, String code, String amount, String orderno, int txno) throws UnknownHostException {
        System.out.println("ref" + ref + "code" + txcd + "code" + code + "amt" + amount + "order" + orderno + "txno" + txno);
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("SE17ORDER", orderno);
        valueInt.put("SE17TXNO", txno);
        if (txcd.equals("200")) {
            valueStr.put("SE17COMM", Handler.decimalConversion(amount));
            valueStr.put("SE17LIAB", Handler.decimalConversion("0.00"));
            valueStr.put("SE17PAY", Handler.decimalConversion("0.00"));
        }
        valueStr.put("SE17FUND", code);
        String query = QueryBuilder.createInsertQuery("SEDIST", valueStr, valueInt, null);
        System.out.println(query);
        return query;
    }
}
