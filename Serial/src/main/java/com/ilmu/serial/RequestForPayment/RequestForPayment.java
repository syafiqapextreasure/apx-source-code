/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.RequestForPayment;

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
    private String SE07DESC = null;
    private String SE07PRATE = null;
    private String SE07FOREX = null;
    private String SE07COPY = null;
    private String SE07ORDER = null;
    private String SE07SUPPLIER = null;
    private String SE07PONO = null;
    private String SE07INVNO = null;
    private String SE07AMT = null;
    private String SE07FPRICE = null;
    private String gsUpdatePayment = "ACU0010";
    private double SE07DISCOUNT = 0.0;
    private String totalorder = null;
    private String totalforign = null;

    public RequestForPayment(String SE07DESC, String SE07PRATE, String SE07FOREX, String SE07COPY, String SE07ORDER, String SE07SUPPLIER, String SE07PONO, String SE07INVNO, String SE07AMT, String SE07FPRICE, double SE07DISCOUNT) {
        this.SE07DESC = SE07DESC;
        this.SE07PRATE = SE07PRATE;
        this.SE07FOREX = SE07FOREX;
        this.SE07COPY = SE07COPY;
        this.SE07ORDER = SE07ORDER;
        this.SE07SUPPLIER = SE07SUPPLIER;
        this.SE07PONO = SE07PONO;
        this.SE07INVNO = SE07INVNO;
        this.SE07AMT = SE07AMT;
        this.SE07FPRICE = SE07FPRICE;
        this.SE07DISCOUNT = SE07DISCOUNT;
    }

    public RequestForPayment(String SE07DESC, String SE07PRATE, String SE07FOREX, String SE07COPY, String SE07ORDER, String SE07SUPPLIER, String SE07PONO, String SE07INVNO, String SE07AMT, String SE07FPRICE) {
        this.SE07DESC = SE07DESC;
        this.SE07PRATE = SE07PRATE;
        this.SE07FOREX = SE07FOREX;
        this.SE07COPY = SE07COPY;
        this.SE07ORDER = SE07ORDER;
        this.SE07SUPPLIER = SE07SUPPLIER;
        this.SE07PONO = SE07PONO;
        this.SE07INVNO = SE07INVNO;
        this.SE07AMT = SE07AMT;
        this.SE07FPRICE = SE07FPRICE;
    }

    public RequestForPayment(String SE07DESC, String SE07SUPPLIER, String SE07PONO, String SE07INVNO, String SE07AMT, String SE07FPRICE, String SE07FOREX) {
        this.SE07DESC = SE07DESC;
        this.SE07SUPPLIER = SE07SUPPLIER;
        this.SE07PONO = SE07PONO;
        this.SE07INVNO = SE07INVNO;
        this.SE07AMT = SE07AMT;
        this.SE07FPRICE = SE07FPRICE;
        this.SE07FOREX = SE07FOREX;
    }

    public RequestForPayment(String totalorder, String totalforign, String SE07FOREX) {
        this.totalorder = totalorder;
        this.totalforign = totalforign;
        this.SE07FOREX = SE07FOREX;
    }

    public String getSE07DESC() {
        return this.SE07DESC;
    }

    public String getSE07PRATE() {
        return this.SE07PRATE;
    }

    public String getSE07FOREX() {
        return this.SE07FOREX;
    }

    public String getSE07COPY() {
        return this.SE07COPY;
    }

    public String getSE07ORDER() {
        return this.SE07ORDER;
    }

    public String getSE07SUPPLIER() {
        return this.SE07SUPPLIER;
    }

    public String getSE07PONO() {
        return this.SE07PONO;
    }

    public String getSE07INVNO() {
        return this.SE07INVNO;
    }

    public String getSE07AMT() {
        return this.SE07AMT;
    }

    public String getSE07FPRICE() {
        return this.SE07FPRICE;
    }

    public double getSE07DISCOUNT() {
        return this.SE07DISCOUNT;
    }

    public String gettotalorder() {
        return this.totalorder;
    }

    public String gettotalforign() {
        return this.totalforign;
    }

    public static List<RequestForPayment> LoadTransactionsDoc(String criteria, String textValue, String groupList, String action) throws SQLException {
        ArrayList<RequestForPayment> list = new ArrayList<RequestForPayment>();
        String sSQLStmt = "";
        sSQLStmt = groupList.equals("N") ? "SELECT  SE07SUPPLIER, SE07ORDER, SE07PONO, SE07INVNO, SE07AMT, SE07DESC, SE07PRATE, SE07FPRICE, SE07COPY, SE07FOREX FROM SEINVO " : "SELECT SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME,Sum(SE07FPRICE*SE07COPY) as foreignPrice, Sum(SE07AMT) as amount, SE07FOREX FROM SEINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RequestForPayment.GetSQLCriteria1(criteria, textValue, action);
        sSQLStmt = groupList.equals("N") ? String.valueOf(sSQLStmt) + "ORDER BY SE07SUPPLIER, SE07INVNO, SE07ORDER" : String.valueOf(sSQLStmt) + "GROUP BY SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME, SE07FOREX";
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
                        if (rs.getString("SE07ORDER").startsWith("DIS") || rs.getString("SE07ORDER").startsWith("HC") || rs.getString("SE07ORDER").startsWith("SC")) {
                            System.out.println("Order");
                            foreignPrice = rs.getString("SE07FPRICE");
                        } else {
                            foreignPrice = rs.getString("SE07FPRICE");
                        }
                        requestforpayment = new RequestForPayment(Handler.getSubfield(rs.getString("SE07DESC"), isbd), rs.getString("SE07PRATE"), rs.getString("SE07FOREX"), rs.getString("SE07COPY"), rs.getString("SE07ORDER"), rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("SE07PONO")), rs.getString("SE07INVNO"), rs.getString("SE07AMT"), rs.getString("SE07FPRICE"));
                    } else {
                        requestforpayment = new RequestForPayment("N", rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("amount")), rs.getString("SE07INVNO"), rs.getString("amount"), rs.getString("foreignPrice"), rs.getString("SE07FOREX"));
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

    public static List<RequestForPayment> LoadTransactions(String criteria, String textValue, String groupList) throws SQLException {
        ArrayList<RequestForPayment> list = new ArrayList<RequestForPayment>();
        String sSQLStmt = "";
        sSQLStmt = groupList.equals("N") ? "SELECT  SE07SUPPLIER, SE07ORDER, SE07PONO, SE07INVNO, SE07AMT, SE07DESC, SE07PRATE, SE07FPRICE, SE07COPY, SE07FOREX FROM SEINVO " : "SELECT SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME,Sum(SE07FPRICE*SE07COPY) as foreignPrice, Sum(SE07AMT) as amount, SE07FOREX FROM SEINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RequestForPayment.GetSQLCriteria(criteria, textValue);
        sSQLStmt = groupList.equals("N") ? String.valueOf(sSQLStmt) + " ORDER BY SE07SUPPLIER, SE07INVNO, SE07ORDER" : String.valueOf(sSQLStmt) + " GROUP BY SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME, SE07FOREX";
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
                        if (rs.getString("SE07ORDER").startsWith("DIS") || rs.getString("SE07ORDER").startsWith("HC") || rs.getString("SE07ORDER").startsWith("SC")) {
                            System.out.println("Order");
                            foreignPrice = rs.getString("SE07FPRICE");
                        } else {
                            foreignPrice = rs.getString("SE07FPRICE");
                        }
                        requestforpayment = new RequestForPayment(Handler.getSubfield(rs.getString("SE07DESC"), isbd), rs.getString("SE07PRATE"), rs.getString("SE07FOREX"), rs.getString("SE07COPY"), rs.getString("SE07ORDER"), rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("SE07PONO")), rs.getString("SE07INVNO"), rs.getString("SE07AMT"), rs.getString("SE07FPRICE"));
                    } else {
                        requestforpayment = new RequestForPayment("N", rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("amount")), rs.getString("SE07INVNO"), rs.getString("amount"), rs.getString("foreignPrice"), rs.getString("SE07FOREX"));
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

    public static List<RequestForPayment> ProcessRequest(String order, String groupList, String vendor) throws SQLException {
        ArrayList<RequestForPayment> list = new ArrayList<RequestForPayment>();
        String sSQLStmt = "";
        sSQLStmt = groupList.equals("N") ? "SELECT SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME,SE07ORDER,SE07PONO,SE07AMT,SE07DESC,SE07PRATE,SE07FPRICE,SE07COPY,SE07FOREX, SE07DISCOUNT  FROM SEINVO WHERE SE07INVNO IN (" + order + ") AND SE07SUPPLIER='" + vendor + "'" : "SELECT SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME,Sum(SE07FPRICE*SE07COPY) as foreignPrice,Sum(SE07AMT) as amount FROM SEINVO WHERE SE07INVNO IN (" + order + ") AND SE07SUPPLIER='" + vendor + "'";
        sSQLStmt = groupList.equals("N") ? String.valueOf(sSQLStmt) + " ORDER BY SE07SUPPLIER, SE07INVNO, SE07ORDER, SE07DISCOUNT" : String.valueOf(sSQLStmt) + " GROUP BY SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME";
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
                        foreignPrice = rs.getString("SE07ORDER").startsWith("DIS") || rs.getString("SE07ORDER").startsWith("HC") || rs.getString("SE07ORDER").startsWith("SC") ? rs.getString("SE07FPRICE") : rs.getString("SE07FPRICE");
                        requestforpayment = new RequestForPayment(rs.getString("SE07DESC"), rs.getString("SE07PRATE"), rs.getString("SE07FOREX"), rs.getString("SE07COPY"), rs.getString("SE07ORDER"), rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("SE07PONO")), rs.getString("SE07INVNO"), rs.getString("SE07AMT"), rs.getString("SE07FPRICE"), Double.parseDouble(rs.getString("SE07DISCOUNT")));
                    } else {
                        requestforpayment = new RequestForPayment("N", rs.getString("SE07SUPPLIER"), Handler.ifIsNull(rs.getString("amount")), rs.getString("SE07INVNO"), rs.getString("amount"), rs.getString("foreignPrice"), rs.getString("SE07FOREX"));
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

    private static String GetSQLCriteria1(String criteria, String textValue, String action) {
        String msSQLCriteria = "";
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = "WHERE SE07INVNO IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + "(SELECT SE07INVNO FROM SEINVO WHERE SE07PONO = '" + textValue + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = "WHERE SE07INVNO = '" + textValue + "' ";
            } else if (criteria.equals("vendor")) {
                msSQLCriteria = "WHERE SE07SUPPLIER = '" + textValue + "' ";
            }
            msSQLCriteria = action.equals("print") ? String.valueOf(msSQLCriteria) + "AND SE07STAT='R' " : String.valueOf(msSQLCriteria) + "AND SE07STAT = 'N' ";
        }
        return msSQLCriteria;
    }

    private static String GetSQLCriteria(String criteria, String textValue) {
        String msSQLCriteria = "";
        if (criteria != null) {
            if (criteria.equals("referenceno")) {
                msSQLCriteria = "WHERE SE07INVNO IN ";
                msSQLCriteria = String.valueOf(msSQLCriteria) + "(SELECT SE07INVNO FROM SEINVO WHERE SE07PONO = '" + textValue + "') ";
            } else if (criteria.equals("invoiceno")) {
                msSQLCriteria = "WHERE SE07INVNO = '" + textValue + "' ";
            } else if (criteria.equals("vendor")) {
                msSQLCriteria = "WHERE SE07SUPPLIER = '" + Handler.convUpperCase(textValue) + "' ";
            }
        }
        msSQLCriteria = String.valueOf(msSQLCriteria) + "AND SE07STAT = 'N' ";
        return msSQLCriteria;
    }

    public static String GetVendors(String criteria, String textValue, String vendor, String startDate, String stopDate, String groupList) throws SQLException {
        String sSQLStmt = null;
        sSQLStmt = "SELECT DISTINCT SE07SUPPLIER FROM SEINVO ";
        sSQLStmt = String.valueOf(sSQLStmt) + RequestForPayment.GetSQLCriteria(criteria, textValue) + " ORDER BY SE07SUPPLIER";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    vendor = rs.getString("SE07DESC");
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
        sSQLStmt = "SELECT SE03ORDER, SE03INVSTAT FROM SEORDD ";
        sSQLStmt = grpList.equals("Y") ? String.valueOf(sSQLStmt) + ",SEINVO where  SE03ORDER=SE07ORDER AND SE07INVNO IN (" + vsOrderNumber + ") " : String.valueOf(sSQLStmt) + ",SEINVO where  SE03ORDER=SE07ORDER AND SE07INVNO IN (" + vsOrderNumber + ") ";
        if (vsVendor != "" && !vsVendor.trim().isEmpty()) {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND SE03SUPPLIER = '" + vsVendor + "' ";
        }
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (rs.getString("SE03INVSTAT").equals("21")) {
                        RequestForPayment.updatingOrders("22", rs.getString("SE03ORDER"));
                        continue;
                    }
                    if (rs.getString("SE03INVSTAT").equals("41")) {
                        RequestForPayment.updatingOrders("42", rs.getString("SE03ORDER"));
                        continue;
                    }
                    if (!rs.getString("SE03INVSTAT").equals("81")) continue;
                    RequestForPayment.updatingOrders("82", rs.getString("SE03ORDER"));
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
        sSQLStmt = "SELECT SE07ORDER, SE07REFERNO, SE07INVNO FROM SEINVO WHERE ";
        sSQLStmt = grpList.equals("Y") ? String.valueOf(sSQLStmt) + "SE07INVNO IN (" + vsOrderNumber + ") " : String.valueOf(sSQLStmt) + "SE07INVNO IN (" + vsOrderNumber + ") ";
        if (vsVendor != "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND SE07SUPPLIER = '" + Handler.convUpperCase(vsVendor) + "' ";
        }
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    RequestForPayment.updatingReferno(rs.getString("SE07INVNO"), payment);
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

    public static boolean updatingReferno(String SE07INVNO, String vsReferNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE07INVNO", SE07INVNO);
        valueStr.put("SE07STAT", "R");
        valueStr.put("SE07REFERNO", vsReferNo);
        valueStr.put("SE07RDATE", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("SEINVO", valueStr, null, null, condition);
        System.out.println("SQY" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updatingOrders(String AC03INVSTAT, String AC03ORDER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE03ORDER", AC03ORDER);
        valueStr.put("SE03INVSTAT", AC03INVSTAT);
        String query = QueryBuilder.createUpdateQuery("SEORDD", valueStr, null, null, condition);
        System.out.println(query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static RequestForPayment allTotalRequestForPayment2(String order) {
        RequestForPayment requestForPayment = null;
        String sql = "SELECT SE07SUPPLIER, SE07INVNO, SE07INVDATE, SE07INVTIME, Sum(SE07FPRICE*SE07COPY) as foreignPrice, Sum(SE07AMT) as amount, SE07FOREX FROM SEINVO WHERE SE07INVNO IN (" + order + ")  " + "GROUP BY SE07SUPPLIER,SE07INVNO,SE07INVDATE,SE07INVTIME,SE07FOREX";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println("allTotalRequestForPayment2" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                float tolOrder = 0.0f;
                float orderForeign = 0.0f;
                String sCurrencyCode = "";
                boolean count = false;
                while (rs.next()) {
                    sCurrencyCode = Handler.ifIsNull(rs.getString("SE07FOREX"));
                    System.out.println("tolOrdertolOrder" + (tolOrder += Float.valueOf(rs.getString("amount")).floatValue()));
                    orderForeign += Float.valueOf(rs.getString("foreignPrice")).floatValue();
                }
                requestForPayment = new RequestForPayment(String.format("%.2f", Float.valueOf(tolOrder)), String.format("%.2f", Float.valueOf(orderForeign)), sCurrencyCode);
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return requestForPayment;
    }
}
