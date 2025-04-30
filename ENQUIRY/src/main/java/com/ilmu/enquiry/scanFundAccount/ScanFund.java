/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.scanFundAccount;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScanFund {
    private String Allo;
    private String Incr;
    private String Decr;
    private String Encr;
    private String TotalAlloc;
    private String Comm;
    private String Liab;
    private String Pay;
    private String BalAlloc;
    private String Trx;
    private String Date;
    private String TrxCode;

    public String getAllo() {
        return Handler.ifIsNull(this.Allo);
    }

    public String getIncr() {
        return Handler.ifIsNull(this.Incr);
    }

    public String getDecr() {
        return Handler.ifIsNull(this.Decr);
    }

    public String getEncr() {
        return Handler.ifIsNull(this.Encr);
    }

    public String getTotalAlloc() {
        return Handler.ifIsNull(this.TotalAlloc);
    }

    public String getComm() {
        return Handler.ifIsNull(this.Comm);
    }

    public String getLiab() {
        return Handler.ifIsNull(this.Liab);
    }

    public String getPay() {
        return Handler.ifIsNull(this.Pay);
    }

    public String getBalAlloc() {
        return Handler.ifIsNull(this.BalAlloc);
    }

    public String getTrx() {
        return Handler.ifIsNull(this.Trx);
    }

    public String getDate() {
        return Handler.ifIsNull(this.Date);
    }

    public String getTrxCode() {
        return Handler.ifIsNull(this.TrxCode);
    }

    public ScanFund(String Allo, String Incr, String Decr, String Encr, String Comm, String Liab, String Pay, String TotalAlloc, String BalAlloc) {
        this.Allo = Allo;
        this.Incr = Incr;
        this.Decr = Decr;
        this.Encr = Encr;
        this.Comm = Comm;
        this.Liab = Liab;
        this.Pay = Pay;
        this.TotalAlloc = TotalAlloc;
        this.BalAlloc = BalAlloc;
    }

    public ScanFund(String Trx, String Date, String TrxCode, String Incr, String Decr) {
        this.Trx = Trx;
        this.Date = Date;
        this.TrxCode = TrxCode;
        this.Incr = Incr;
        this.Decr = Decr;
    }

    public static List<ScanFund> searchScanFundAccount(String code) {
        ArrayList<ScanFund> list = new ArrayList<ScanFund>();
        String query = "SELECT FA01ALLO, FA01INCR, FA01DECR, FA01ENCR, FA01COMM, FA01LIAB, FA01PAY, (FA01ALLO + FA01INCR - FA01DECR) AS TOTALALLOC, (FA01ALLO + FA01INCR - FA01DECR - FA01COMM - FA01PAY) AS BALALLOC FROM FAMAST WHERE FA01FUND = '" + code + "'";
        System.out.println("query searchScanFundAccount : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ScanFund loadtabledetail = new ScanFund(Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01ALLO"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01INCR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01DECR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01ENCR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01COMM"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01LIAB"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01PAY"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("TOTALALLOC"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BALALLOC"))));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static List<ScanFund> DisplayList(String sAccNo, String miViewCode) {
        ArrayList<ScanFund> list = new ArrayList<ScanFund>();
        String query = "SELECT FA02FUND,FA02TXNO, FA02TXCD, FA02DATE, FA02AMT, FA03PROC,SUBSTRING(FA03PROC, " + miViewCode + " ,1) AS POSITION " + "FROM FATRAN, FACODE " + "WHERE FA02FUND= '" + sAccNo + "' " + "AND FA02TXCD=FA03TXCD " + "AND SUBSTRING(FA03PROC, " + miViewCode + " ,1) <> '0'";
        System.out.println("query DisplayList : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String Incr = "";
                String Decr = "";
                while (rs.next()) {
                    String postion = Handler.ifIsNull(rs.getString("POSITION"));
                    String amt = Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA02AMT")));
                    if (postion.equals("1")) {
                        Incr = amt;
                        Decr = "";
                    } else if (postion.equals("2")) {
                        Incr = "";
                        Decr = amt;
                    }
                    ScanFund loadtabledetail = new ScanFund(Handler.ifIsNull(rs.getString("FA02TXNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("FA02DATE"))), Handler.ifIsNull(rs.getString("FA02TXCD")), Incr, Decr);
                    list.add(loadtabledetail);
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
        return list;
    }
}
