/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.Ordering;

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
    private String SE03ORDER = null;
    private String SE03CRDATE = null;
    private String TITLE = null;
    private String ISBN = null;
    private String SE03LPRICE = null;
    private String SE03COPIES = null;
    private String SE03RECEIVED = null;
    private String SE03STATUS = null;
    private String quantity = null;
    private String SE03FOREX = null;
    private String SE03FPRICE = null;
    private String total = null;
    private String localtotal = null;
    private String SE03STAT = null;
    private String SE03MATNO = null;
    private String edition = null;
    private String publication = null;
    private String bdraf = null;
    private String bdate = null;
    private String orderdate = null;
    private String invoice = null;

    public Ordering(String SE03ORDER, String SE03CRDATE, String TITLE, String ISBN, String quantity, String SE03FOREX, String SE03FPRICE, String total, String localtotal, String SE03STATUS, String SE03MATNO) {
        this.SE03ORDER = SE03ORDER;
        this.SE03CRDATE = SE03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE03FOREX = SE03FOREX;
        this.SE03FPRICE = SE03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.SE03STATUS = SE03STATUS;
        this.SE03MATNO = SE03MATNO;
    }

    public Ordering(String SE03ORDER, String SE03CRDATE, String TITLE, String ISBN, String quantity, String SE03FOREX, String SE03FPRICE, String total, String localtotal, String SE03STATUS, String SE03MATNO, String edition, String publication) {
        this.SE03ORDER = SE03ORDER;
        this.SE03CRDATE = SE03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE03FOREX = SE03FOREX;
        this.SE03FPRICE = SE03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.SE03STATUS = SE03STATUS;
        this.SE03MATNO = SE03MATNO;
        this.edition = edition;
        this.publication = publication;
    }

    public Ordering(String SE03ORDER, String SE03CRDATE, String TITLE, String ISBN, String quantity, String SE03FOREX, String SE03FPRICE, String total, String localtotal, String SE03STAT, String SE03MATNO, String edition, String publication, String invoice, String bdraf, String bdate, String orderdate) {
        this.SE03ORDER = SE03ORDER;
        this.SE03CRDATE = SE03CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE03FOREX = SE03FOREX;
        this.SE03FPRICE = SE03FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.SE03STAT = SE03STAT;
        this.SE03MATNO = SE03MATNO;
        this.edition = edition;
        this.publication = publication;
        this.invoice = invoice;
        this.bdraf = bdraf;
        this.bdate = bdate;
        this.orderdate = orderdate;
    }

    public String getSE03ORDER() {
        return this.SE03ORDER;
    }

    public String getSE03CRDATE() {
        return this.SE03CRDATE;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getSE03LPRICE() {
        return this.SE03LPRICE;
    }

    public String getSE03COPIES() {
        return this.SE03COPIES;
    }

    public String getSE03RECEIVED() {
        return this.SE03RECEIVED;
    }

    public String getSE03STATUS() {
        return this.SE03STATUS;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getSE03FOREX() {
        return this.SE03FOREX;
    }

    public String getSE03FPRICE() {
        return this.SE03FPRICE;
    }

    public String getTotal() {
        return this.total;
    }

    public String getLocalTotal() {
        return this.localtotal;
    }

    public String getSE03STAT() {
        return this.SE03STAT;
    }

    public String getSE03MATNO() {
        return this.SE03MATNO;
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
            sql = "Select SE03STAT, SE03ORDER, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, SE03LPRICE,  t5.ct05sraw as isbn, SE03MATNO From SEORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where SE03SUPPLIER = '" + Handler.convUpperCase(sVendor) + "' ";
            msCondition = "And  (SE03STAT = '10' Or   (SE03STAT = '40' And SE03INVSTAT IN ('41','42','43'))) ";
        } else if (sVendor != null) {
            sql = "Select SE03STAT, SE03ORDER, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, SE03MATNO From SEORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where SE03SUPPLIER = '" + Handler.convUpperCase(sVendor) + "' " + "And  SE03MATNO IS NOT NULL " + "And  (SE03STAT = '10' " + "Or   (SE03STAT = '40' And SE03INVSTAT IN ('41','42','43'))) ";
            msCondition = "And  (SE03STAT = '10' Or   (SE03STAT = '40' And SE03INVSTAT IN ('41','42','43'))) ";
        } else {
            sql = "Select SE03STAT, SE03ORDER, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, SE03MATNO From SEORDD T1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER inner join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '022' inner join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where (SE03STAT = '10' Or   (SE03STAT = '40' And SE03INVSTAT IN ('41','42','43'))) ";
            msCondition = "Where (SE03STAT = '10' Or   (SE03STAT = '40' And SE03INVSTAT IN ('41','42','43'))) ";
        }
        if (criteria.equals("orderdate")) {
            if (value != "" && value1 != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  SE03CRDATE Between '" + DateTime.displayToDBFormat(value) + "' And '" + DateTime.displayToDBFormat(value1) + "' ";
            } else if (value != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  SE03CRDATE >= '" + DateTime.displayToDBFormat(value) + "' ";
            } else if (value1 != "") {
                msCondition = String.valueOf(msCondition) + msCondition + " And  SE03CRDATE <= '" + DateTime.displayToDBFormat(value1) + "' ";
            }
        } else if (criteria.equals("ctrlno")) {
            msCondition = String.valueOf(msCondition) + msCondition + " And  SE03MATNO ='" + value + "' ";
        }
        sql = String.valueOf(sql) + msCondition;
        sql = String.valueOf(sql) + "Order By SE03ORDER";
        System.out.println("SQL" + sql);
        return sql;
    }

    public static List<Ordering> LoadRecordset(String criteria, String vendor, String value, String value1, String ordermode) throws SQLException {
        ArrayList<Ordering> list = new ArrayList<Ordering>();
        String sSQLStmt = Ordering.GetSQLStmt(criteria, vendor, value, value1, ordermode);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
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
                    current = rs.getString("SE03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("SE03copies")) != 0) {
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                    } else {
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                    }
                    if (previous != null) {
                        if (previous.equals(current)) {
                            isbn = String.valueOf(isbn) + previousISBN + ",";
                            System.out.println("Currentss" + previousISBN + currentISBN);
                            if (!currentISBN.equals(Handler.getSubfield(rs.getString("isbn"), isbd))) {
                                System.out.println("Current" + previousISBN + currentISBN);
                                isbn = String.valueOf(isbn) + Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                            }
                        } else {
                            isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                        }
                    } else {
                        isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    }
                    Ordering cancelorderlist = new Ordering(rs.getString("SE03order"), DateTime.DBToDisplayFormat(rs.getString("SE03CRDATE")), Handler.getSubfield(rs.getString("title"), isbd), isbn, rs.getString("SE03COPIES"), rs.getString("SE03FOREX"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE03STAT"), rs.getString("SE03MATNO"));
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

    public static boolean updateSEissue(String orderno) throws SQLException {
        String sSQLStmt = "";
        boolean updated = false;
        c = DBConnection.getConnection();
        try {
            try {
                sSQLStmt = "Update SEISSU SET SE06STAT='O', SE06ISSTAT='O' WHERE SE06ORDER IN(" + orderno + ")";
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

    public static List<Ordering> getOrderingList(String order) throws SQLException {
        ArrayList<Ordering> tplList = new ArrayList<Ordering>();
        System.out.println("OrderList" + order);
        String query = " select SE03STAT, SE03order, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, t1.SE03MATNO as ctrlno from SEORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER where SE03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE03ORDER";
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
                    current = rs.getString("SE03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("SE03copies")) != 0) {
                        quantity = String.valueOf(rs.getString("SE03copies")) + "set";
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                    } else {
                        quantity = rs.getString("SE03COPIES");
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
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
                    cancelorderlist = new Ordering(rs.getString("SE03order"), output, Handler.getSubfield(rs.getString("title"), isbd), isbn, quantity, rs.getString("SE03FOREX"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE03STAT"), rs.getString("ctrlno"));
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
        String query = " select SE03STAT, SE03order, SE03CRDATE, SE03ISSUES,SE03ISSFROM,SE03copies, SE03COPIES, SE03VOLFROM, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, t1.SE03MATNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from SEORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE03MATNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE03MATNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where SE03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE03ORDER";
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
                    current = rs.getString("SE03order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
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
                    cancelorderlist = new Ordering(rs.getString("SE03order"), output, Handler.getSubfield(rs.getString("title"), isbd), isbn, rs.getString("SE03copies"), rs.getString("SE03VOLFROM"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE03STAT"), rs.getString("ctrlno"), rs.getString("SE03ISSUES"), rs.getString("SE03ISSFROM"));
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
        String query = " select SE03ORDATE, t10.SE07INVNO AS invoice , t10.SE07BDRAF as chq, t10.SE07BDATE as chqdate,SE03STAT, SE03order, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, t1.SE03MATNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from SEORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE03MATNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE03MATNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER left join SEINVO t10 on t1.SE03ORDER = t10.SE07ORDER where SE03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE03ORDER";
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
                    if (Integer.parseInt(rs.getString("SE03copies")) != 0) {
                        quantity = String.valueOf(rs.getString("SE03copies")) + "set";
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                    } else {
                        quantity = rs.getString("SE03COPIES");
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("SE03order"), output, Handler.removeSubfield(rs.getString("title")), Handler.removeSubfield(rs.getString("isbn")), quantity, rs.getString("SE03FOREX"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE03STAT"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"), rs.getString("invoice"), rs.getString("chq"), rs.getString("chqdate"), rs.getString("SE03ORDATE"));
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
        String query = " select SE03STAT, SE03order, SE03CRDATE, SE03copies, SE03COPIES, SE03FOREX, SE03FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE03LPRICE, t1.SE03MATNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from SEORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE03MATNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE03MATNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE03MATNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where SE03order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE03ORDER";
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
                    if (Integer.parseInt(rs.getString("SE03copies")) != 0) {
                        quantity = String.valueOf(rs.getString("SE03copies")) + "set";
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03copies"));
                    } else {
                        quantity = rs.getString("SE03COPIES");
                        total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE03LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Ordering(rs.getString("SE03order"), output, Handler.getSubfield(rs.getString("title"), isbd), Handler.getSubfield(rs.getString("isbn"), isbd), quantity, rs.getString("SE03FOREX"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE03STAT"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"));
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
                sSQLStmt = "SELECT COUNT(*) as count FROM SEORDD WHERE SE03PONO='" + vsReferenceNo + "'";
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
                sSQLStmt = "Update SEORDD SET SE03ORDATE='" + DateTime.getTodaySystemDate() + "', SE03PONO='" + vsReferenceNo + "', SE03STAT='" + ordermode + "', SE03INVSTAT='" + ordermode + "', " + "SE03PRINTED='Y' WHERE SE03ORDER IN(" + orderno + ")";
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
        String query = "DELETE FROM SEORDD WHERE SE03ORDER = '" + order + "'";
        return query;
    }

    public static String resubmitACORDD(String orderno, String vendor) {
        String query = "UPDATE SEORDD SET SE03INVSTAT='10', SE03STAT='10', SE03SUPPLIER='" + Handler.convUpperCase(vendor) + "' where SE03ORDER='" + orderno + "'";
        System.out.println(query);
        return query;
    }

    public static boolean checkReq(String vsOrderNumber) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT Count(*) as count FROM SEREQC where SE02ORDER='" + vsOrderNumber + "'";
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
