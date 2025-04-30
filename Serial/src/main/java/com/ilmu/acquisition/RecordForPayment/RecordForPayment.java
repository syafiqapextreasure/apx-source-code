/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.RecordForPayment;

import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RecordForPayment {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String localAmt = null;
    private String AC05FPRICE = null;
    private String AC05SUPPLIER = null;
    private String AC05REFERNO = null;
    private String AC05INVNO = null;
    private String AC05FOREX = null;

    public RecordForPayment(String AC05FPRICE, String AC05SUPPLIER, String AC05REFERNO, String AC05INVNO, String localAmt, String AC05FOREX) {
        this.AC05FPRICE = AC05FPRICE;
        this.AC05SUPPLIER = AC05SUPPLIER;
        this.AC05REFERNO = AC05REFERNO;
        this.AC05INVNO = AC05INVNO;
        this.localAmt = localAmt;
        this.AC05FOREX = AC05FOREX;
    }

    public RecordForPayment(String AC05FPRICE, String AC05SUPPLIER, String AC05REFERNO, String AC05INVNO, String localAmt) {
        this.AC05FPRICE = AC05FPRICE;
        this.AC05SUPPLIER = AC05SUPPLIER;
        this.AC05REFERNO = AC05REFERNO;
        this.AC05INVNO = AC05INVNO;
        this.localAmt = localAmt;
    }

    public String getAC05FPRICE() {
        return this.AC05FPRICE;
    }

    public String getAC05SUPPLIER() {
        return this.AC05SUPPLIER;
    }

    public String getAC05INVNO() {
        return this.AC05INVNO;
    }

    public String getAC05AMT() {
        return this.localAmt;
    }

    public String getAC05REFERNO() {
        return this.AC05REFERNO;
    }

    public String getAC05FOREX() {
        return this.AC05FOREX;
    }

    public static List<RecordForPayment> GetOrderNo(String criteria, String text, String vendor) throws SQLException {
        String sSQLStmt = "";
        ArrayList<RecordForPayment> list = new ArrayList<RecordForPayment>();
        sSQLStmt = "SELECT AC05SUPPLIER, AC05REFERNO, AC05INVNO,SUM(AC05AMT) as localAmt, SUM(AC05FPRICE) as foreignAmt FROM ACINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RecordForPayment.GetSQLCriteria(vendor, criteria, text);
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("AC05SUPPLIER"), rs.getString("AC05REFERNO"), rs.getString("AC05INVNO"), rs.getString("localAmt"));
                    list.add(requestforpayment);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<RecordForPayment> ProcessOrderNo(String orderno, String vendor) throws SQLException {
        String sSQLStmt = "";
        ArrayList<RecordForPayment> list = new ArrayList<RecordForPayment>();
        sSQLStmt = "SELECT AC05SUPPLIER, AC05REFERNO, AC05INVNO,SUM(AC05AMT) as localAmt, SUM(AC05FPRICE) as foreignAmt FROM ACINVO WHERE AC05INVNO IN (" + orderno + ") and ac05stat='P' and ac05supplier='" + Handler.convUpperCase(vendor) + "' group by AC05SUPPLIER, AC05REFERNO, AC05INVNO";
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("AC05SUPPLIER"), rs.getString("AC05REFERNO"), rs.getString("AC05INVNO"), rs.getString("localAmt"));
                    list.add(requestforpayment);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<RecordForPayment> PreviewOrderNo(String orderno, String vendor) throws SQLException {
        String sSQLStmt = "";
        ArrayList<RecordForPayment> list = new ArrayList<RecordForPayment>();
        sSQLStmt = "SELECT AC05SUPPLIER, AC05REFERNO, AC05INVNO,SUM(AC05AMT) as localAmt, SUM(AC05FPRICE) as foreignAmt FROM ACINVO WHERE AC05INVNO IN (" + orderno + ") and ac05stat='R' and ac05supplier='" + Handler.convUpperCase(vendor) + "' group by AC05SUPPLIER, AC05REFERNO, AC05INVNO";
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("AC05SUPPLIER"), rs.getString("AC05REFERNO"), rs.getString("AC05INVNO"), rs.getString("localAmt"));
                    list.add(requestforpayment);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    private static String GetSQLCriteria(String vendor, String criteria, String text) {
        boolean bSQL = false;
        String msSQLCriteria = "";
        if (!vendor.trim().isEmpty()) {
            msSQLCriteria = "WHERE AC05SUPPLIER = '" + Handler.convUpperCase(vendor) + "' ";
            bSQL = true;
        }
        System.out.println("ssa");
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(AC05INVNO) IN " : String.valueOf(msSQLCriteria) + "WHERE UPPER(AC05INVNO) IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + " (SELECT UPPER(AC05INVNO) FROM ACINVO WHERE AC05REFERNO = '" + text + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(AC05INVNO) = '" + text + "' " : String.valueOf(msSQLCriteria) + "WHERE UPPER(AC05INVNO) = '" + text + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05STAT = 'R' GROUP BY AC05SUPPLIER, AC05REFERNO, AC05INVNO";
        return msSQLCriteria;
    }

    private static String GetSQLCriteria1(String vendor, String criteria, String text) {
        boolean bSQL = false;
        String msSQLCriteria = "";
        if (vendor != null) {
            msSQLCriteria = "WHERE AC05SUPPLIER = '" + Handler.convUpperCase(vendor) + "' ";
            bSQL = true;
        }
        System.out.println("ssa");
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(AC05INVNO) IN " : String.valueOf(msSQLCriteria) + "WHERE UPPER(AC05INVNO) IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + " (SELECT UPPER(AC05INVNO) FROM ACINVO WHERE AC05REFERNO = '" + text + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(AC05INVNO) = '" + text + "' " : String.valueOf(msSQLCriteria) + "WHERE UPPER(AC05INVNO) = '" + text + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05STAT = 'R' GROUP BY AC05SUPPLIER, AC05REFERNO, AC05INVNO, AC05ORDER";
        return msSQLCriteria;
    }

    public static boolean UpdateOrders(String vsInvNumber, String vendor) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = " SELECT AC03ORDER, AC03INVSTAT FROM ACORDD WHERE AC03ORDER IN (SELECT AC05ORDER FROM ACINVO WHERE AC05INVNO IN (" + vsInvNumber + ") and AC05SUPPLIER='" + Handler.convUpperCase(vendor) + "') ";
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (rs.getString("AC03INVSTAT").equals("22")) {
                        RecordForPayment.updatingOrders("23", rs.getString("AC03ORDER"));
                        continue;
                    }
                    if (rs.getString("AC03INVSTAT").equals("42")) {
                        RecordForPayment.updatingOrders("43", rs.getString("AC03ORDER"));
                        continue;
                    }
                    if (!rs.getString("AC03INVSTAT").equals("82")) continue;
                    RecordForPayment.updatingOrders("83", rs.getString("AC03ORDER"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return true;
    }

    public static boolean UpdateInvoice(String criteria, String text, String vendor, String invno, String chequeno, String invdate, String chequedate) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT AC05SUPPLIER, AC05REFERNO, AC05INVNO, AC05ORDER FROM ACINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RecordForPayment.GetSQLCriteria1(vendor, criteria, text);
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    RecordForPayment.updatingInvoice(rs.getString("AC05INVNO"), rs.getString("AC05SUPPLIER"), rs.getString("AC05ORDER"), invno, chequeno, invdate, chequedate);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return true;
    }

    public static boolean updatingOrders(String AC03INVSTAT, String AC03ORDER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("AC03ORDER", AC03ORDER);
        valueStr.put("AC03INVSTAT", AC03INVSTAT);
        String query = QueryBuilder.createUpdateQuery("ACORDD", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean UpdateReference(String vsReferenceNo, String orderno, String ordermode) throws SQLException {
        boolean hrs = false;
        String sSQLStmt = "";
        boolean updated = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(5, Library.getOrderSpan());
        String output = sdf.format(cal.getTime());
        c = DBConnection.getConnection();
        try {
            try {
                sSQLStmt = "Update ACORDD SET AC03ORDATE='" + DateTime.getTodaySystemDate() + "', AC03REFNO='" + vsReferenceNo + "', AC03STATUS='" + ordermode + "', AC03INVSTAT='" + ordermode + "', " + "AC03EXDATE='" + output + "' WHERE AC03ORDER IN(" + orderno + ")";
                System.out.println(sSQLStmt);
                PreparedStatement preparedStmt = c.prepareStatement(sSQLStmt);
                preparedStmt.executeUpdate();
                updated = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return updated;
    }

    public static boolean updatingInvoice(String AC05INVNO, String AC05SUPPLIER, String AC05ORDER, String vsVoucher, String vsChequeNo, String vsVoucherDate, String vsChequeDate) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("AC05INVNO", AC05INVNO);
        condition.put("AC05SUPPLIER", Handler.convUpperCase(AC05SUPPLIER));
        condition.put("AC05ORDER", AC05ORDER);
        valueStr.put("AC05STAT", "P");
        valueStr.put("AC05BDRAF", vsChequeNo);
        valueStr.put("AC05BDATE", DateTime.displayToDBFormat(vsChequeDate));
        valueStr.put("AC05VOUCHER", vsVoucher);
        valueStr.put("AC05VCHDATE", DateTime.displayToDBFormat(vsVoucherDate));
        valueStr.put("AC05RDATE", DateTime.getTodaySystemDate());
        valueStr.put("AC05PDATE", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("ACINVO", valueStr, null, null, condition);
        System.out.println("Query" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
