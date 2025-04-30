/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.Ordering;

import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Ordering {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String AC03ORDER = null;
    private String AC03CRDATE = null;
    private String TITLE = null;
    private String ISBN = null;
    private String AC03LPRICE = null;
    private String AC03COPIES = null;
    private String AC03RECEIVED = null;
    private String AC03STAT = null;
    private String quantity = null;
    private String AC03FOREX = null;
    private String AC03FPRICE = null;
    private String total = null;
    private String localtotal = null;
    private String AC03STATUS = null;
    private String AC03CTRLNO = null;
    private String edition = null;
    private String publication = null;
    private String bdraf = null;
    private String bdate = null;
    private String orderdate = null;
    private String invoice = null;

    public Ordering(String AC03ORDER, String AC03CRDATE, String TITLE, String ISBN, String quantity, String AC03FOREX, String AC03FPRICE, String total, String localtotal, String AC03STATUS, String AC03CTRLNO) {
        this.AC03ORDER = AC03ORDER;
        this.AC03CRDATE = AC03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.AC03FOREX = AC03FOREX;
        this.AC03FPRICE = AC03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.AC03STATUS = AC03STATUS;
        this.AC03CTRLNO = AC03CTRLNO;
    }

    public Ordering(String AC03ORDER, String AC03CRDATE, String TITLE, String ISBN, String quantity, String AC03FOREX, String AC03FPRICE, String total, String localtotal, String AC03STATUS, String AC03CTRLNO, String edition, String publication) {
        this.AC03ORDER = AC03ORDER;
        this.AC03CRDATE = AC03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.AC03FOREX = AC03FOREX;
        this.AC03FPRICE = AC03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.AC03STATUS = AC03STATUS;
        this.AC03CTRLNO = AC03CTRLNO;
        this.edition = edition;
        this.publication = publication;
    }

    public Ordering(String AC03ORDER, String AC03CRDATE, String TITLE, String ISBN, String quantity, String AC03FOREX, String AC03FPRICE, String total, String localtotal, String AC03STATUS, String AC03CTRLNO, String edition, String publication, String invoice, String bdraf, String bdate, String orderdate) {
        this.AC03ORDER = AC03ORDER;
        this.AC03CRDATE = AC03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.AC03FOREX = AC03FOREX;
        this.AC03FPRICE = AC03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.AC03STATUS = AC03STATUS;
        this.AC03CTRLNO = AC03CTRLNO;
        this.edition = edition;
        this.publication = publication;
        this.invoice = invoice;
        this.bdraf = bdraf;
        this.bdate = bdate;
        this.orderdate = orderdate;
    }

    public String getAC03ORDER() {
        return this.AC03ORDER;
    }

    public String getAC03CRDATE() {
        return this.AC03CRDATE;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getAC03LPRICE() {
        return this.AC03LPRICE;
    }

    public String getAC03COPIES() {
        return this.AC03COPIES;
    }

    public String getAC03RECEIVED() {
        return this.AC03RECEIVED;
    }

    public String getAC03STAT() {
        return this.AC03STAT;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getAC03FOREX() {
        return this.AC03FOREX;
    }

    public String getAC03FPRICE() {
        return this.AC03FPRICE;
    }

    public String getTotal() {
        return this.total;
    }

    public String getLocalTotal() {
        return this.localtotal;
    }

    public String getAC03STATUS() {
        return this.AC03STATUS;
    }

    public String getAC03CTRLNO() {
        return this.AC03CTRLNO;
    }

    public String getEdition() {
        return this.edition;
    }

    public String getPublication() {
        return this.publication;
    }

    public String geOrderdate() {
        return this.orderdate;
    }

    public String getBdraf() {
        return this.bdraf;
    }

    public String getBDate() {
        return this.bdate;
    }

    public String getInvoice() {
        return this.invoice;
    }

    private static String GetSQLStmt(String criteria, String sVendor, String value, String value1, String ordermode) {
        System.out.println("Criteria" + criteria);
        DBConnection dbtype = new DBConnection();
        String sql = null;
        String msCondition = "";
        if (ordermode.equals("0")) {
            sql = "Select AC03STATUS, AC03ORDER, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, AC03LPRICE,  t5.ct05sraw as isbn, AC03CTRLNO From ACORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where AC03VEND = '" + Handler.convUpperCase(sVendor) + "' ";
            msCondition = "And  (AC03STATUS = '10' Or   (AC03STATUS = '40' And AC03INVSTAT IN ('41','42','43'))) ";
        } else if (sVendor != null) {
            sql = "Select AC03STATUS, AC03ORDER, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, AC03CTRLNO From ACORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where AC03VEND = '" + Handler.convUpperCase(sVendor) + "' " + "And  AC03CTRLNO IS NOT NULL " + "And  (AC03STATUS = '10' " + "Or   (AC03STATUS = '40' And AC03INVSTAT IN ('41','42','43'))) ";
            msCondition = "And  (AC03STATUS = '10' Or   (AC03STATUS = '40' And AC03INVSTAT IN ('41','42','43'))) ";
        } else {
            sql = "Select AC03STATUS, AC03ORDER, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, AC03CTRLNO From ACORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER inner join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' inner join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where (AC03STATUS = '10' Or   (AC03STATUS = '40' And AC03INVSTAT IN ('41','42','43'))) ";
            msCondition = "Where (AC03STATUS = '10' Or   (AC03STATUS = '40' And AC03INVSTAT IN ('41','42','43'))) ";
        }
        if (criteria.equals("orderdate")) {
            if (value != "" && value1 != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  AC03CRDATE Between '" + DateTime.displayToDBFormat(value) + "' And '" + DateTime.displayToDBFormat(value1) + "' ";
            } else if (value != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  AC03CRDATE >= '" + DateTime.displayToDBFormat(value) + "' ";
            } else if (value1 != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  AC03CRDATE <= '" + DateTime.displayToDBFormat(value1) + "' ";
            }
        } else if (criteria.equals("ctrlno")) {
            msCondition = String.valueOf(msCondition) + msCondition + " And  AC03CTRLNO ='" + value + "' ";
        }
        sql = String.valueOf(sql) + msCondition;
        sql = String.valueOf(sql) + "Order By AC03ORDER";
        System.out.println("SQL" + sql);
        return sql;
    }

    public static List<Ordering> LoadRecordset(String criteria, String vendor, String value, String value1, String ordermode) throws SQLException {
        ArrayList<Ordering> list = new ArrayList<Ordering>();
        String sSQLStmt = Ordering.GetSQLStmt(criteria, vendor, value, value1, ordermode);
        System.out.println(sSQLStmt);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                String current = null;
                String currentISBN = null;
                String isbn = null;
                while (rs.next()) {
                    String previous = current;
                    current = rs.getString("ac03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("AC03SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("AC03SETS")) + "set";
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                    } else {
                        quantity = rs.getString("AC03COPIES");
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                    }
                    if (previous != null) {
                        if (previous.equals(current)) {
                            isbn = String.valueOf(isbn) + previousISBN + ",";
                            System.out.println("Currentss" + previousISBN + currentISBN);
                            if (!currentISBN.equals(Handler.getSubfield(rs.getString("isbn"), isbd))) {
                                System.out.println("Current" + previousISBN + currentISBN);
                                isbn = String.valueOf(isbn) + Handler.getSubfield(rs.getString("isbn"), isbd);
                            }
                        } else {
                            isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                        }
                    } else {
                        isbn = Handler.getSubfield(rs.getString("isbn"), isbd);
                    }
                    Ordering cancelorderlist = new Ordering(rs.getString("ac03order"), DateTime.DBToDisplayFormat(rs.getString("AC03CRDATE")), Handler.getSubfield(rs.getString("title"), isbd), isbn, quantity, rs.getString("AC03FOREX"), Handler.decimalConversion(rs.getString("AC03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("AC03STATUS"), rs.getString("AC03CTRLNO"));
                    if (previous != null) {
                        if (previous.equals(current)) continue;
                        list.add(cancelorderlist);
                        continue;
                    }
                    list.add(cancelorderlist);
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

    public static List<Ordering> getOrderingList(String order) throws SQLException {
        ArrayList<Ordering> tplList = new ArrayList<Ordering>();
        System.out.println("OrderList" + order);
        String query = " select AC03STATUS, ac03order, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, t1.AC03CTRLNO as ctrlno from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER where ac03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY AC03ORDER";
        System.out.println(query);
        Ordering cancelorderlist = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                String current = null;
                String currentISBN = null;
                String isbn = null;
                while (rs.next()) {
                    String previous = current;
                    current = rs.getString("ac03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("AC03SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("AC03SETS")) + "set";
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                    } else {
                        quantity = rs.getString("AC03COPIES");
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                    }
                    if (previous != null) {
                        if (previous.equals(current)) {
                            isbn = String.valueOf(isbn) + previousISBN + ",";
                            System.out.println("Currentss" + previousISBN + currentISBN);
                            if (!currentISBN.equals(Handler.getSubfield(rs.getString("isbn"), isbd))) {
                                System.out.println("Current" + previousISBN + currentISBN);
                                isbn = String.valueOf(isbn) + Handler.getSubfield(rs.getString("isbn"), isbd);
                            }
                        } else {
                            isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                        }
                    } else {
                        isbn = Handler.getSubfield(rs.getString("isbn"), isbd);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("ac03order"), output, Handler.getSubfield(rs.getString("title"), isbd), isbn, quantity, rs.getString("AC03FOREX"), Handler.decimalConversion(rs.getString("AC03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("AC03STATUS"), rs.getString("ctrlno"));
                    if (previous != null) {
                        if (previous.equals(current)) continue;
                        tplList.add(cancelorderlist);
                        continue;
                    }
                    tplList.add(cancelorderlist);
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
        return tplList;
    }

    public static List<Ordering> getOrderingDoc(String order) throws SQLException {
        ArrayList<Ordering> tplList = new ArrayList<Ordering>();
        System.out.println("OrderList" + order);
        String query = " select AC03STATUS, ac03order, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, t1.AC03CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.AC03CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.AC03CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where ac03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY AC03ORDER";
        System.out.println(query);
        Ordering cancelorderlist = null;
        String current = null;
        String currentISBN = null;
        String isbn = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String previous = current;
                    current = rs.getString("ac03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("AC03SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("AC03SETS")) + "set";
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                    } else {
                        quantity = rs.getString("AC03COPIES");
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                    }
                    if (previous != null) {
                        if (previous.equals(current)) {
                            isbn = String.valueOf(isbn) + previousISBN + ",";
                            System.out.println("Currentss" + previousISBN + currentISBN);
                            if (!currentISBN.equals(Handler.getSubfield(rs.getString("isbn"), isbd))) {
                                System.out.println("Current" + previousISBN + currentISBN);
                                isbn = String.valueOf(isbn) + Handler.getSubfield(rs.getString("isbn"), isbd);
                            }
                        } else {
                            isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                        }
                    } else {
                        isbn = Handler.getSubfield(rs.getString("isbn"), isbd);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("ac03order"), output, Handler.getSubfield(rs.getString("title"), isbd), isbn, quantity, rs.getString("AC03FOREX"), Handler.decimalConversion(rs.getString("AC03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("AC03STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"));
                    if (previous != null) {
                        if (previous.equals(current)) continue;
                        tplList.add(cancelorderlist);
                        continue;
                    }
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static List<Ordering> getPerformaOrder(String order) throws SQLException {
        ArrayList<Ordering> tplList = new ArrayList<Ordering>();
        System.out.println("OrderList" + order);
        String query = " select AC03ORDATE, t10.AC05INVNO AS invoice , t10.AC05BDRAF as chq, t10.AC05BDATE as chqdate,AC03STATUS, ac03order, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, t1.AC03CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.AC03CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.AC03CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER left join ACINVO t10 on t1.AC03ORDER = t10.AC05ORDER where ac03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY AC03ORDER";
        System.out.println(query);
        Ordering cancelorderlist = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("AC03SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("AC03SETS")) + "set";
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                    } else {
                        quantity = rs.getString("AC03COPIES");
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("ac03order"), output, Handler.removeSubfield(rs.getString("title")), Handler.removeSubfield(rs.getString("isbn")), quantity, rs.getString("AC03FOREX"), Handler.decimalConversion(rs.getString("AC03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("AC03STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"), rs.getString("invoice"), rs.getString("chq"), rs.getString("chqdate"), rs.getString("AC03ORDATE"));
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static List<Ordering> getReqNotification(String order) throws SQLException {
        ArrayList<Ordering> tplList = new ArrayList<Ordering>();
        System.out.println("OrderListzz" + order);
        String query = " select AC03STATUS, ac03order, AC03CRDATE, AC03SETS, AC03COPIES, AC03FOREX, AC03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, AC03LPRICE, t1.AC03CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.AC03CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.AC03CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.AC03CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.AC03CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where ac03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY AC03ORDER";
        System.out.println(query);
        Ordering cancelorderlist = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("AC03SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("AC03SETS")) + "set";
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03SETS"));
                    } else {
                        quantity = rs.getString("AC03COPIES");
                        total = Float.valueOf(rs.getString("AC03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                        localtotal = Float.valueOf(rs.getString("AC03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("AC03COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("ac03order"), output, Handler.getSubfield(rs.getString("title"), isbd), Handler.getSubfield(rs.getString("isbn"), isbd), quantity, rs.getString("AC03FOREX"), Handler.decimalConversion(rs.getString("AC03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("AC03STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"));
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static boolean IsUniqueReferenceNo(String vsReferenceNo) throws SQLException {
        boolean hrs = false;
        String sSQLStmt = "";
        boolean IsUniqueReferenceNo = false;
        try {
            try {
                sSQLStmt = "SELECT COUNT(*) as count FROM ACORDD WHERE AC03REFNO='" + vsReferenceNo + "'";
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL" + sSQLStmt);
                while (rs.next()) {
                    System.out.println("SQL" + Integer.parseInt(rs.getString("Count")));
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    IsUniqueReferenceNo = true;
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
        return IsUniqueReferenceNo;
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
                sSQLStmt = "Update ACORDD SET AC03ORDATE='" + DateTime.getTodaySystemDate() + "', AC03REFNO='" + vsReferenceNo + "', AC03STATUS='" + ordermode + "', AC03INVSTAT='" + ordermode + "', " + "AC03EXDATE='" + output + "', AC03PRINTED='Y' WHERE AC03ORDER IN(" + orderno + ")";
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

    public static String deleteACORDD(String order) {
        String query = "DELETE FROM ACORDD WHERE AC03ORDER = '" + order + "'";
        return query;
    }

    public static String resubmitACORDD(String orderno, String vendor) {
        String query = "UPDATE ACORDD SET AC03INVSTAT='10', AC03STATUS='10', AC03VEND='" + Handler.convUpperCase(vendor) + "' where AC03ORDER='" + orderno + "'";
        System.out.println(query);
        return query;
    }

    public static boolean checkReq(String vsOrderNumber) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT Count(*) as count FROM ACREQC where AC01ORDER='" + vsOrderNumber + "'";
        boolean exist = false;
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }
}
