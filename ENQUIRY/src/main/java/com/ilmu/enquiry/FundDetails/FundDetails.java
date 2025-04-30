/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.FundDetails;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FundDetails {
    private String Fund;
    private String Descrip;
    private String Allo;
    private String Incr;
    private String Decr;
    private String Comm;
    private String Pay;
    private String Balace;

    public String getFund() {
        return Handler.ifIsNull(this.Fund);
    }

    public String getDescrip() {
        return Handler.ifIsNull(this.Descrip);
    }

    public String getAllo() {
        return Handler.ifIsNull(this.Allo);
    }

    public String getIncr() {
        return Handler.ifIsNull(this.Incr);
    }

    public String getDecr() {
        return Handler.ifIsNull(this.Decr);
    }

    public String getComm() {
        return Handler.ifIsNull(this.Comm);
    }

    public String getPay() {
        return Handler.ifIsNull(this.Pay);
    }

    public String getBalace() {
        return Handler.ifIsNull(this.Balace);
    }

    public FundDetails(String Fund, String Descrip, String Allo, String Incr, String Decr, String Comm, String Pay, String Balace) {
        this.Fund = Fund;
        this.Descrip = Descrip;
        this.Allo = Allo;
        this.Incr = Incr;
        this.Decr = Decr;
        this.Comm = Comm;
        this.Pay = Pay;
        this.Balace = Balace;
    }

    public static List<FundDetails> DisplayAll() {
        ArrayList<FundDetails> list = new ArrayList<FundDetails>();
        String query = "SELECT FA01FUND, FA01DESC, FA01ALLO, FA01INCR, FA01DECR, FA01COMM, FA01PAY, (FA01ALLO + FA01INCR - FA01DECR - FA01COMM - FA01PAY) AS BALALLOC FROM FAMAST ORDER BY FA01FUND";
        System.out.println("query DisplayAll : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    FundDetails loadtabledetail = new FundDetails(Handler.ifIsNull(rs.getString("FA01FUND")), Handler.ifIsNull(rs.getString("FA01DESC")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01ALLO"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01INCR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01DECR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01COMM"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01PAY"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BALALLOC"))));
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

    public static List<FundDetails> DisplayList(String txtEntry, String opt) {
        ArrayList<FundDetails> list = new ArrayList<FundDetails>();
        String query = "SELECT FA01FUND, FA01DESC, FA01ALLO, FA01INCR, FA01DECR, FA01COMM, FA01PAY, (FA01ALLO + FA01INCR - FA01DECR - FA01COMM - FA01PAY) AS BALALLOC FROM FAMAST ORDER BY FA01FUND";
        System.out.println("query DisplayAll : " + query);
        Connection conn = null;
        double cCurrentAmount = Double.parseDouble(txtEntry);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String BalAllo = Handler.ifIsNull(Handler.decimalConversion(rs.getString("BALALLOC")));
                    double cBalAllo = Double.parseDouble(BalAllo);
                    String FA01ALLO = Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01ALLO")));
                    double cFA01ALLO = Double.parseDouble(FA01ALLO);
                    if (opt.equals("amt") && cBalAllo > cCurrentAmount || !(cFA01ALLO > 0.0) || opt.equals("percen") && cBalAllo > 0.0 && cBalAllo / cFA01ALLO * 100.0 > cCurrentAmount) continue;
                    FundDetails loadtabledetail = new FundDetails(Handler.ifIsNull(rs.getString("FA01FUND")), Handler.ifIsNull(rs.getString("FA01DESC")), FA01ALLO, Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01INCR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01DECR"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01COMM"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("FA01PAY"))), BalAllo);
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
