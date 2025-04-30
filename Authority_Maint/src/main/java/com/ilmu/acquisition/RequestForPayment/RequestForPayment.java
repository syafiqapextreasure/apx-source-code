/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.RequestForPayment;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestForPayment {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String AC05DESC = null;
    private String AC05PRATE = null;
    private String AC05FOREX = null;
    private String AC05COPY = null;
    private String AC05ORDER = null;
    private String AC05SUPPLIER = null;
    private String AC05PONO = null;
    private String AC05INVNO = null;
    private String AC05AMT = null;
    private String AC05FPRICE = null;
    private String gsUpdatePayment = "ACU0010";
    private double AC05DISCOUNT = 0.0;

    public RequestForPayment(String AC05DESC, String AC05PRATE, String AC05FOREX, String AC05COPY, String AC05ORDER, String AC05SUPPLIER, String AC05PONO, String AC05INVNO, String AC05AMT, String AC05FPRICE, double AC05DISCOUNT) {
        this.AC05DESC = AC05DESC;
        this.AC05PRATE = AC05PRATE;
        this.AC05FOREX = AC05FOREX;
        this.AC05COPY = AC05COPY;
        this.AC05ORDER = AC05ORDER;
        this.AC05SUPPLIER = AC05SUPPLIER;
        this.AC05PONO = AC05PONO;
        this.AC05INVNO = AC05INVNO;
        this.AC05AMT = AC05AMT;
        this.AC05FPRICE = AC05FPRICE;
        this.AC05DISCOUNT = AC05DISCOUNT;
    }

    public RequestForPayment(String AC05DESC, String AC05PRATE, String AC05FOREX, String AC05COPY, String AC05ORDER, String AC05SUPPLIER, String AC05PONO, String AC05INVNO, String AC05AMT, String AC05FPRICE) {
        this.AC05DESC = AC05DESC;
        this.AC05PRATE = AC05PRATE;
        this.AC05FOREX = AC05FOREX;
        this.AC05COPY = AC05COPY;
        this.AC05ORDER = AC05ORDER;
        this.AC05SUPPLIER = AC05SUPPLIER;
        this.AC05PONO = AC05PONO;
        this.AC05INVNO = AC05INVNO;
        this.AC05AMT = AC05AMT;
        this.AC05FPRICE = AC05FPRICE;
    }

    public RequestForPayment(String AC05DESC, String AC05SUPPLIER, String AC05PONO, String AC05INVNO, String AC05AMT, String AC05FPRICE) {
        this.AC05DESC = AC05DESC;
        this.AC05SUPPLIER = AC05SUPPLIER;
        this.AC05PONO = AC05PONO;
        this.AC05INVNO = AC05INVNO;
        this.AC05AMT = AC05AMT;
        this.AC05FPRICE = AC05FPRICE;
    }

    public String getAC05DESC() {
        return this.AC05DESC;
    }

    public String getAC05PRATE() {
        return this.AC05PRATE;
    }

    public String getAC05FOREX() {
        return this.AC05FOREX;
    }

    public String getAC05COPY() {
        return this.AC05COPY;
    }

    public String getAC05ORDER() {
        return this.AC05ORDER;
    }

    public String getAC05SUPPLIER() {
        return this.AC05SUPPLIER;
    }

    public String getAC05PONO() {
        return this.AC05PONO;
    }

    public String getAC05INVNO() {
        return this.AC05INVNO;
    }

    public String getAC05AMT() {
        return this.AC05AMT;
    }

    public String getAC05FPRICE() {
        return this.AC05FPRICE;
    }

    public double getAC05DISCOUNT() {
        return this.AC05DISCOUNT;
    }

    public static List<RequestForPayment> LoadTransactions(String criteria, String textValue, String vendor, String startDate, String stopDate, String groupList) throws SQLException {
        ArrayList<RequestForPayment> list = new ArrayList<RequestForPayment>();
        String sSQLStmt = "";
        sSQLStmt = groupList.equals("N") ? "SELECT AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME,AC05ORDER,AC05PONO,AC05AMT,AC05DESC,AC05PRATE,AC05FPRICE,AC05COPY,AC05FOREX FROM ACINVO " : "SELECT AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME,Sum(AC05FPRICE*AC05COPY) as foreignPrice, Sum(AC05AMT) as amount FROM ACINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RequestForPayment.GetSQLCriteria(criteria, textValue, vendor, startDate, stopDate);
        sSQLStmt = groupList.equals("N") ? String.valueOf(sSQLStmt) + " ORDER BY AC05SUPPLIER, AC05INVNO, AC05ORDER" : String.valueOf(sSQLStmt) + " GROUP BY AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME";
        System.out.println(sSQLStmt);
        RequestForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    String foreignPrice = "";
                    if (groupList.equals("N")) {
                        if (rs.getString("AC05ORDER").startsWith("DIS") || rs.getString("AC05ORDER").startsWith("HC") || rs.getString("AC05ORDER").startsWith("SC")) {
                            System.out.println("Order");
                            foreignPrice = rs.getString("AC05FPRICE");
                        } else {
                            foreignPrice = rs.getString("AC05FPRICE");
                        }
                        requestforpayment = new RequestForPayment(rs.getString("AC05DESC"), rs.getString("AC05PRATE"), rs.getString("AC05FOREX"), rs.getString("AC05COPY"), rs.getString("AC05ORDER"), rs.getString("AC05SUPPLIER"), Handler.ifIsNull(rs.getString("AC05PONO")), rs.getString("AC05INVNO"), rs.getString("AC05AMT"), rs.getString("AC05FPRICE"));
                    } else {
                        requestforpayment = new RequestForPayment("N", rs.getString("AC05SUPPLIER"), Handler.ifIsNull(rs.getString("amount")), rs.getString("AC05INVNO"), rs.getString("amount"), rs.getString("foreignPrice"));
                    }
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

    public static List<RequestForPayment> ProcessRequest(String order, String groupList) throws SQLException {
        ArrayList<RequestForPayment> list = new ArrayList<RequestForPayment>();
        String sSQLStmt = "";
        sSQLStmt = groupList.equals("N") ? "SELECT AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME,AC05ORDER,AC05PONO,AC05AMT,AC05DESC,AC05PRATE,AC05FPRICE,AC05COPY,AC05FOREX, AC05DISCOUNT  FROM ACINVO WHERE AC05INVNO IN (" + order + ")" : "SELECT AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME,Sum(AC05FPRICE*AC05COPY) as foreignPrice,Sum(AC05AMT) as amount FROM ACINVO WHERE AC05INVNO IN (" + order + ")";
        sSQLStmt = groupList.equals("N") ? String.valueOf(sSQLStmt) + " ORDER BY AC05SUPPLIER, AC05INVNO, AC05ORDER, AC05DISCOUNT" : String.valueOf(sSQLStmt) + " GROUP BY AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME";
        System.out.println(sSQLStmt);
        RequestForPayment requestforpayment = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    String foreignPrice = "";
                    if (groupList.equals("N")) {
                        foreignPrice = rs.getString("AC05ORDER").startsWith("DIS") || rs.getString("AC05ORDER").startsWith("HC") || rs.getString("AC05ORDER").startsWith("SC") ? rs.getString("AC05FPRICE") : rs.getString("AC05FPRICE");
                        requestforpayment = new RequestForPayment(rs.getString("AC05DESC"), rs.getString("AC05PRATE"), rs.getString("AC05FOREX"), rs.getString("AC05COPY"), rs.getString("AC05ORDER"), rs.getString("AC05SUPPLIER"), Handler.ifIsNull(rs.getString("AC05PONO")), rs.getString("AC05INVNO"), rs.getString("AC05AMT"), rs.getString("AC05FPRICE"), Double.parseDouble(rs.getString("AC05DISCOUNT")));
                    } else {
                        requestforpayment = new RequestForPayment("N", rs.getString("AC05SUPPLIER"), Handler.ifIsNull(rs.getString("amount")), rs.getString("AC05INVNO"), rs.getString("amount"), rs.getString("foreignPrice"));
                    }
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

    private static String GetSQLCriteria(String criteria, String textValue, String vendor, String sStartDate, String sStopDate) {
        String msSQLCriteria = "";
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = "WHERE AC05INVNO IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + "(SELECT AC05INVNO FROM ACINVO WHERE AC05PONO = '" + textValue + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = "WHERE AC05INVNO = '" + textValue + "' ";
            } else if (criteria.equals("orderno")) {
                msSQLCriteria = "WHERE AC05INVNO IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + "(SELECT AC05INVNO FROM ACINVO WHERE AC05ORDER = '" + textValue + "') ";
            }
        }
        if (vendor != "") {
            sStartDate = DateTime.DBToDisplayFormatV1(sStartDate);
            sStopDate = DateTime.DBToDisplayFormatV1(sStopDate);
            msSQLCriteria = msSQLCriteria == "" ? String.valueOf(msSQLCriteria) + "WHERE AC05SUPPLIER = '" + Handler.convUpperCase(vendor) + "' " : String.valueOf(msSQLCriteria) + "AND AC05SUPPLIER = '" + Handler.convUpperCase(vendor) + "' ";
            if (sStartDate != "" && sStopDate != "" && sStartDate != null && sStopDate != null) {
                msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05INVDATE BETWEEN '" + sStartDate + "' AND '" + sStopDate + "' ";
            } else if (sStartDate != "" && sStartDate != null) {
                msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05INVDATE >= '" + sStartDate + "' ";
            } else if (sStopDate != "" && sStopDate != null) {
                msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05INVDATE <= '" + sStopDate + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND AC05STAT = 'N' ";
        return msSQLCriteria;
    }

    public static String GetVendors(String criteria, String textValue, String vendor, String startDate, String stopDate, String groupList) throws SQLException {
        String sSQLStmt = null;
        sSQLStmt = "SELECT DISTINCT AC05SUPPLIER FROM ACINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RequestForPayment.GetSQLCriteria(criteria, textValue, vendor, startDate, stopDate) + " ORDER BY AC05SUPPLIER";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    vendor = rs.getString("AC05DESC");
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
        return vendor;
    }

    public static boolean UpdateOrders(String vsOrderNumber, String vsVendor, String grpList) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT AC03ORDER, AC03INVSTAT FROM ACORDD ";
        if (!grpList.equals("Y")) {
            sSQLStmt = String.valueOf(sSQLStmt) + ",ACINVO where  AC03ORDER=AC05ORDER AND AC05INVNO IN (" + vsOrderNumber + ") ";
        }
        if (vsVendor != "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND AC03VEND = '" + vsVendor + "' ";
        }
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (rs.getString("AC03INVSTAT").equals("21")) {
                        RequestForPayment.updatingOrders("22", rs.getString("AC03ORDER"));
                        continue;
                    }
                    if (rs.getString("AC03INVSTAT").equals("41")) {
                        RequestForPayment.updatingOrders("42", rs.getString("AC03ORDER"));
                        continue;
                    }
                    if (!rs.getString("AC03INVSTAT").equals("81")) continue;
                    RequestForPayment.updatingOrders("82", rs.getString("AC03ORDER"));
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

    public static boolean UpdateReferNo(String vsOrderNumber, String vsVendor, String payment, String grpList) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT AC05ORDER, AC05REFERNO, AC05INVNO FROM ACINVO WHERE ";
        sSQLStmt = grpList.equals("Y") ? String.valueOf(sSQLStmt) + "AC05INVNO IN (" + vsOrderNumber + ") " : String.valueOf(sSQLStmt) + "AC05INVNO IN (" + vsOrderNumber + ") ";
        if (vsVendor != "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND AC05SUPPLIER = '" + Handler.convUpperCase(vsVendor) + "' ";
        }
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    RequestForPayment.updatingReferno(rs.getString("AC05INVNO"), payment);
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

    public static boolean updatingReferno(String AC05INVNO, String vsReferNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("AC05INVNO", AC05INVNO);
        valueStr.put("AC05STAT", "R");
        valueStr.put("AC05REFERNO", vsReferNo);
        valueStr.put("AC05RDATE", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("ACINVO", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updatingOrders(String AC03INVSTAT, String AC03ORDER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("AC03ORDER", AC03ORDER);
        valueStr.put("AC03INVSTAT", AC03INVSTAT);
        String query = QueryBuilder.createUpdateQuery("ACORDD", valueStr, null, null, condition);
        System.out.println(query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
