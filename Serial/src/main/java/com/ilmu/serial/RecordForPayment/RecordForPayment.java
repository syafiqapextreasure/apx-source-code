/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.RecordForPayment;

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
    private String SE07FPRICE = null;
    private String SE07SUPPLIER = null;
    private String SE07REFERNO = null;
    private String SE07INVNO = null;
    private String SE07FOREX = null;

    public RecordForPayment(String SE07FPRICE, String SE07SUPPLIER, String SE07REFERNO, String SE07INVNO, String localAmt, String SE07FOREX) {
        this.SE07FPRICE = SE07FPRICE;
        this.SE07SUPPLIER = SE07SUPPLIER;
        this.SE07REFERNO = SE07REFERNO;
        this.SE07INVNO = SE07INVNO;
        this.localAmt = localAmt;
        this.SE07FOREX = SE07FOREX;
    }

    public RecordForPayment(String SE07FPRICE, String SE07SUPPLIER, String SE07REFERNO, String SE07INVNO, String localAmt) {
        this.SE07FPRICE = SE07FPRICE;
        this.SE07SUPPLIER = SE07SUPPLIER;
        this.SE07REFERNO = SE07REFERNO;
        this.SE07INVNO = SE07INVNO;
        this.localAmt = localAmt;
    }

    public String getSE07FPRICE() {
        return this.SE07FPRICE;
    }

    public String getSE07SUPPLIER() {
        return this.SE07SUPPLIER;
    }

    public String getSE07INVNO() {
        return this.SE07INVNO;
    }

    public String getSE07AMT() {
        return this.localAmt;
    }

    public String getSE07REFERNO() {
        return this.SE07REFERNO;
    }

    public String getSE07FOREX() {
        return this.SE07FOREX;
    }

    public static List<RecordForPayment> GetOrderNo(String criteria, String text) throws SQLException {
        String sSQLStmt = "";
        ArrayList<RecordForPayment> list = new ArrayList<RecordForPayment>();
        sSQLStmt = "SELECT SE07SUPPLIER, SE07REFERNO, SE07INVNO,SUM(SE07AMT) as localAmt, SUM(SE07FPRICE) as foreignAmt, SE07FOREX FROM SEINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RecordForPayment.GetSQLCriteria(criteria, text);
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("SE07SUPPLIER"), rs.getString("SE07REFERNO"), rs.getString("SE07INVNO"), rs.getString("localAmt"), rs.getString("SE07FOREX"));
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
        sSQLStmt = "SELECT SE07SUPPLIER, SE07REFERNO, SE07INVNO,SUM(SE07AMT) as localAmt, SUM(SE07FPRICE) as foreignAmt, SE07FOREX FROM SEINVO WHERE SE07INVNO IN (" + orderno + ") and SE07stat='P' and SE07supplier='" + Handler.convUpperCase(vendor) + "' group by SE07SUPPLIER, SE07REFERNO, SE07INVNO, SE07FOREX";
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("SE07SUPPLIER"), rs.getString("SE07REFERNO"), rs.getString("SE07INVNO"), rs.getString("localAmt"), rs.getString("SE07FOREX"));
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
        sSQLStmt = "SELECT SE07SUPPLIER, SE07REFERNO, SE07INVNO,SUM(SE07AMT) as localAmt, SUM(SE07FPRICE) as foreignAmt, SE07FOREX FROM SEINVO WHERE SE07INVNO IN (" + orderno + ") and SE07stat='R' and SE07supplier='" + Handler.convUpperCase(vendor) + "' group by SE07SUPPLIER, SE07REFERNO, SE07INVNO, SE07FOREX";
        System.out.println("SQL" + sSQLStmt);
        RecordForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    requestforpayment = new RecordForPayment(rs.getString("foreignAmt"), rs.getString("SE07SUPPLIER"), rs.getString("SE07REFERNO"), rs.getString("SE07INVNO"), rs.getString("localAmt"), rs.getString("SE07FOREX"));
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

    private static String GetSQLCriteria(String criteria, String text) {
        boolean bSQL = false;
        String msSQLCriteria = "";
        System.out.println("ssa");
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(SE07INVNO) IN " : String.valueOf(msSQLCriteria) + "WHERE UPPER(SE07INVNO) IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + " (SELECT UPPER(SE07INVNO) FROM SEINVO WHERE SE07REFERNO = '" + text + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(SE07INVNO) = '" + text + "' " : String.valueOf(msSQLCriteria) + "WHERE UPPER(SE07INVNO) = '" + text + "' ";
            } else if (criteria.equals("vendor")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(SE07SUPPLIER) = '" + text + "' " : String.valueOf(msSQLCriteria) + "WHERE UPPER(SE07SUPPLIER) = '" + text + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND SE07STAT = 'R' GROUP BY SE07SUPPLIER, SE07REFERNO, SE07INVNO, SE07FOREX";
        return msSQLCriteria;
    }

    private static String GetSQLCriteria1(String vendor, String criteria, String text) {
        boolean bSQL = false;
        String msSQLCriteria = "";
        System.out.println("SQL" + vendor);
        if (vendor != null && !vendor.trim().isEmpty()) {
            msSQLCriteria = "WHERE SE07SUPPLIER = '" + Handler.convUpperCase(vendor) + "' ";
            bSQL = true;
        }
        System.out.println("ssa");
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(SE07INVNO) IN " : String.valueOf(msSQLCriteria) + "WHERE UPPER(SE07INVNO) IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + " (SELECT UPPER(SE07INVNO) FROM SEINVO WHERE SE07REFERNO = '" + text + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = bSQL ? String.valueOf(msSQLCriteria) + "AND UPPER(SE07INVNO) = '" + text + "' " : String.valueOf(msSQLCriteria) + "WHERE UPPER(SE07INVNO) = '" + text + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND SE07STAT = 'R' GROUP BY SE07SUPPLIER, SE07REFERNO, SE07INVNO, SE07ORDER";
        return msSQLCriteria;
    }

    public static boolean UpdateOrders(String vsInvNumber, String vendor) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = " SELECT SE03ORDER, SE03INVSTAT FROM SEORDD WHERE SE03ORDER IN (SELECT SE07ORDER FROM SEINVO WHERE SE07INVNO IN (" + vsInvNumber + ")";
        sSQLStmt = !vendor.trim().isEmpty() && vendor.trim() != null ? String.valueOf(sSQLStmt) + " and SE07SUPPLIER='" + Handler.convUpperCase(vendor) + "') " : String.valueOf(sSQLStmt) + " ) ";
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (rs.getString("SE03INVSTAT").equals("22")) {
                        RecordForPayment.updatingOrders("23", rs.getString("SE03ORDER"));
                        continue;
                    }
                    if (rs.getString("SE03INVSTAT").equals("42")) {
                        RecordForPayment.updatingOrders("43", rs.getString("SE03ORDER"));
                        continue;
                    }
                    if (!rs.getString("SE03INVSTAT").equals("82")) continue;
                    RecordForPayment.updatingOrders("83", rs.getString("SE03ORDER"));
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
        sSQLStmt = "SELECT SE07SUPPLIER, SE07REFERNO, SE07INVNO, SE07ORDER FROM SEINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RecordForPayment.GetSQLCriteria1(vendor, criteria, text);
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    RecordForPayment.updatingInvoice(rs.getString("SE07INVNO"), rs.getString("SE07SUPPLIER"), rs.getString("SE07ORDER"), invno, chequeno, invdate, chequedate);
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

    public static boolean updatingOrders(String SE03INVSTAT, String SE03ORDER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE03ORDER", SE03ORDER);
        valueStr.put("SE03INVSTAT", SE03INVSTAT);
        String query = QueryBuilder.createUpdateQuery("SEORDD", valueStr, null, null, condition);
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
                sSQLStmt = "Update SEORDD SET SE03ORDATE='" + DateTime.getTodaySystemDate() + "', SE03REFNO='" + vsReferenceNo + "', SE03STATUS='" + ordermode + "', SE03INVSTAT='" + ordermode + "', " + "SE03EXDATE='" + output + "' WHERE SE03ORDER IN(" + orderno + ")";
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

    public static boolean updatingInvoice(String SE07INVNO, String SE07SUPPLIER, String SE07ORDER, String vsVoucher, String vsChequeNo, String vsVoucherDate, String vsChequeDate) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE07INVNO", SE07INVNO);
        condition.put("SE07SUPPLIER", Handler.convUpperCase(SE07SUPPLIER));
        condition.put("SE07ORDER", SE07ORDER);
        valueStr.put("SE07STAT", "P");
        valueStr.put("SE07BDRAF", vsChequeNo);
        valueStr.put("SE07BDATE", DateTime.displayToDBFormat(vsChequeDate));
        valueStr.put("SE07VOUCHER", vsVoucher);
        valueStr.put("SE07VCHDATE", DateTime.displayToDBFormat(vsVoucherDate));
        valueStr.put("SE07RDATE", DateTime.getTodaySystemDate());
        valueStr.put("SE07PDATE", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("SEINVO", valueStr, null, null, condition);
        System.out.println("Query" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
