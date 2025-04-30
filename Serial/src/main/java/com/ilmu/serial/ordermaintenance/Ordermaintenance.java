/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.ordermaintenance;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ordermaintenance {
    private String orderNumber = null;
    private String isbn = null;
    private String ctrlno = null;
    private String vendor = null;
    private String descVendor = null;
    private String ordermode = null;
    private String source = null;
    private String currency = null;
    private String exchangeRate = null;
    private String fprice = null;
    private String lprice = null;
    private String orderSet = null;
    private String ordercopies = null;
    private String currentStatus = null;
    private String currentStatusDesc = null;
    private String refno = null;
    private String oderDate = null;
    private String expectedDate = null;
    private String accno = null;
    private String branch = null;
    private String location = null;
    private String locadesc = null;
    private String itemCategory = null;
    private String condition = null;
    private String smd = null;
    private String claim1 = null;
    private String claim2 = null;
    private String claim3 = null;
    private String reqno = null;
    private String requestor = null;
    private String name = null;
    private String department = null;
    private String dateRequestor = null;
    private String date = null;
    private String feedback = null;
    private String officer = null;
    private String title = null;
    private String expdate = null;
    private String CHARGING = null;
    private String STAT = null;
    private String STATDESC = null;
    private String createdate = null;
    private String frequency = null;
    private String volume = null;
    private String volissue = null;
    private String startdate = null;
    private String stopdate = null;
    private String qty = null;
    private String issue = null;
    private String desccurrentStatus = null;
    private String invoicestatus = null;
    private String descinvStatus = null;
    private String recDate = null;
    private String issno = null;
    private String invno = null;
    private String chequeNo = null;
    private String chequeDate = null;
    private String voucherNo = null;
    private String voucherDate = null;
    private String invdate = null;
    private String subj = null;

    public String getOrderNumber() {
        return Handler.ifIsNull(this.orderNumber);
    }

    public String getIsbn() {
        return Handler.ifIsNull(this.isbn);
    }

    public String getCtrlno() {
        return Handler.ifIsNull(this.ctrlno);
    }

    public String getVendor() {
        return Handler.ifIsNull(this.vendor);
    }

    public String getDescVendor() {
        return Handler.ifIsNull(this.descVendor);
    }

    public String getOrdermode() {
        return Handler.ifIsNull(this.ordermode);
    }

    public String getSource() {
        return Handler.ifIsNull(this.source);
    }

    public String getCurrency() {
        return Handler.ifIsNull(this.currency);
    }

    public String getExchangeRate() {
        return Handler.ifIsNull(this.exchangeRate);
    }

    public String getFprice() {
        return Handler.ifIsNull(this.fprice);
    }

    public String getLprice() {
        return Handler.ifIsNull(this.lprice);
    }

    public String getOrderSet() {
        return Handler.ifIsNull(this.orderSet);
    }

    public String getOrdercopies() {
        return Handler.ifIsNull(this.ordercopies);
    }

    public String getCurrentStatus() {
        return Handler.ifIsNull(this.currentStatus);
    }

    public String getCurrentStatusDesc() {
        return Handler.ifIsNull(this.currentStatusDesc);
    }

    public String getRefno() {
        return Handler.ifIsNull(this.refno);
    }

    public String getOderDate() {
        return Handler.ifIsNull(this.oderDate);
    }

    public String getExpectedDate() {
        return Handler.ifIsNull(this.expectedDate);
    }

    public String getAccno() {
        return Handler.ifIsNull(this.accno);
    }

    public String getBranch() {
        return Handler.ifIsNull(this.branch);
    }

    public String getLocation() {
        return Handler.ifIsNull(this.location);
    }

    public String getLocadesc() {
        return Handler.ifIsNull(this.locadesc);
    }

    public String getItemCategory() {
        return Handler.ifIsNull(this.itemCategory);
    }

    public String getCondition() {
        return Handler.ifIsNull(this.condition);
    }

    public String getSmd() {
        return Handler.ifIsNull(this.smd);
    }

    public String getClaim1() {
        return Handler.ifIsNull(this.claim1);
    }

    public String getClaim2() {
        return Handler.ifIsNull(this.claim2);
    }

    public String getClaim3() {
        return Handler.ifIsNull(this.claim3);
    }

    public String getReqno() {
        return Handler.ifIsNull(this.reqno);
    }

    public String getRequestor() {
        return Handler.ifIsNull(this.requestor);
    }

    public String getName() {
        return Handler.ifIsNull(this.name);
    }

    public String getDepartment() {
        return Handler.ifIsNull(this.department);
    }

    public String getDateRequestor() {
        return Handler.ifIsNull(this.dateRequestor);
    }

    public String getDate() {
        return Handler.ifIsNull(this.date);
    }

    public String getFeedback() {
        return Handler.ifIsNull(this.feedback);
    }

    public String getOfficer() {
        return Handler.ifIsNull(this.officer);
    }

    public String getTitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getexpdate() {
        return Handler.ifIsNull(this.expdate);
    }

    public String getCHARGING() {
        return Handler.ifIsNull(this.CHARGING);
    }

    public String getSTAT() {
        return Handler.ifIsNull(this.STAT);
    }

    public String getSTATDESC() {
        return Handler.ifIsNull(this.STATDESC);
    }

    public String getcreatedate() {
        return Handler.ifIsNull(this.createdate);
    }

    public String getfrequency() {
        return Handler.ifIsNull(this.frequency);
    }

    public String getvolume() {
        return Handler.ifIsNull(this.volume);
    }

    public String getvolissue() {
        return Handler.ifIsNull(this.volissue);
    }

    public String getstartdate() {
        return Handler.ifIsNull(this.startdate);
    }

    public String getstopdate() {
        return Handler.ifIsNull(this.stopdate);
    }

    public String getqty() {
        return Handler.ifIsNull(this.qty);
    }

    public String getissue() {
        return Handler.ifIsNull(this.issue);
    }

    public String getdesccurrentStatus() {
        return Handler.ifIsNull(this.desccurrentStatus);
    }

    public String getinvoicestatus() {
        return Handler.ifIsNull(this.invoicestatus);
    }

    public String getdescinvStatus() {
        return Handler.ifIsNull(this.descinvStatus);
    }

    public String getrecDate() {
        return Handler.ifIsNull(this.recDate);
    }

    public String getissno() {
        return Handler.ifIsNull(this.issno);
    }

    public String getinvno() {
        return Handler.ifIsNull(this.invno);
    }

    public String getchequeNo() {
        return Handler.ifIsNull(this.chequeNo);
    }

    public String getchequeDate() {
        return Handler.ifIsNull(this.chequeDate);
    }

    public String getvoucherNo() {
        return Handler.ifIsNull(this.voucherNo);
    }

    public String getvoucherDate() {
        return Handler.ifIsNull(this.voucherDate);
    }

    public String getinvdate() {
        return Handler.ifIsNull(this.invdate);
    }

    public String getsubj() {
        return Handler.ifIsNull(this.subj);
    }

    public Ordermaintenance(String orderNumber, String createdate, String ctrlno, String title, String vendor, String descVendor, String ordermode, String source, String currency, String exchangeRate, String fprice, String lprice, String orderSet, String ordercopies, String currentStatus, String currentStatusDesc, String refno, String oderDate, String expectedDate, String claim1, String claim2, String claim3) {
        this.orderNumber = orderNumber;
        this.createdate = createdate;
        this.ctrlno = ctrlno;
        this.title = title;
        this.vendor = vendor;
        this.descVendor = descVendor;
        this.ordermode = ordermode;
        this.source = source;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.fprice = fprice;
        this.lprice = lprice;
        this.orderSet = orderSet;
        this.ordercopies = ordercopies;
        this.currentStatus = currentStatus;
        this.currentStatusDesc = currentStatusDesc;
        this.refno = refno;
        this.oderDate = oderDate;
        this.expectedDate = expectedDate;
        this.claim1 = claim1;
        this.claim2 = claim2;
        this.claim3 = claim3;
    }

    public Ordermaintenance(String reqno, String requestor, String name, String department, String dateRequestor) {
        this.reqno = reqno;
        this.requestor = requestor;
        this.name = name;
        this.department = department;
        this.dateRequestor = dateRequestor;
    }

    public Ordermaintenance(String date, String feedback, String officer) {
        this.date = date;
        this.feedback = feedback;
        this.officer = officer;
    }

    public Ordermaintenance(String orderNumber, String ctrlno, String title, String descVendor, String currentStatus, String currentStatusDesc, String refno, String oderDate) {
        this.orderNumber = orderNumber;
        this.ctrlno = ctrlno;
        this.title = title;
        this.descVendor = descVendor;
        this.currentStatus = currentStatus;
        this.currentStatusDesc = currentStatusDesc;
        this.refno = refno;
        this.oderDate = oderDate;
    }

    public Ordermaintenance(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Ordermaintenance(String expdate, String STAT, String STATDESC, String CHARGING) {
        this.expdate = expdate;
        this.STAT = STAT;
        this.STATDESC = STATDESC;
        this.CHARGING = CHARGING;
    }

    public Ordermaintenance(String orderNumber, String createdate, String ctrlno, String title, String vendor, String currency, String frequency, String volume, String volissue, String startdate, String stopdate, String exchangeRate, String fprice, String lprice, String qty, String issue, String currenstatus, String desccurrentStatus, String invoicestatus, String descinvStatus, String refno, String oderDate, String claim1, String claim2, String claim3, String subj) {
        this.orderNumber = orderNumber;
        this.createdate = createdate;
        this.ctrlno = ctrlno;
        this.title = title;
        this.vendor = vendor;
        this.currency = currency;
        this.frequency = frequency;
        this.volume = volume;
        this.volissue = volissue;
        this.startdate = startdate;
        this.stopdate = stopdate;
        this.exchangeRate = exchangeRate;
        this.fprice = fprice;
        this.lprice = lprice;
        this.qty = qty;
        this.issue = issue;
        this.currentStatus = currenstatus;
        this.desccurrentStatus = desccurrentStatus;
        this.invoicestatus = invoicestatus;
        this.descinvStatus = descinvStatus;
        this.refno = refno;
        this.oderDate = oderDate;
        this.claim1 = claim1;
        this.claim2 = claim2;
        this.claim3 = claim3;
        this.subj = subj;
    }

    public Ordermaintenance(String ordercopies, String volume, String volissue, String expectedDate, String recDate, String claim1, String claim2, String claim3, String issno) {
        this.ordercopies = ordercopies;
        this.volume = volume;
        this.volissue = volissue;
        this.expectedDate = expectedDate;
        this.recDate = recDate;
        this.claim1 = claim1;
        this.claim2 = claim2;
        this.claim3 = claim3;
        this.issno = issno;
    }

    public Ordermaintenance(String invno, String lprice, String chequeNo, String chequeDate, String voucherNo, String voucherDate, String invdate, String refno, String fprice, String currency, String exchangeRate) {
        this.invno = invno;
        this.lprice = lprice;
        this.chequeNo = chequeNo;
        this.chequeDate = chequeDate;
        this.voucherNo = voucherNo;
        this.voucherDate = voucherDate;
        this.invdate = invdate;
        this.refno = refno;
        this.fprice = fprice;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public Ordermaintenance(String vendor, String name, String fprice, String lprice, String exchangeRate, String currency, String frequency) {
        this.vendor = vendor;
        this.name = name;
        this.fprice = fprice;
        this.lprice = lprice;
        this.exchangeRate = exchangeRate;
        this.currency = currency;
        this.frequency = frequency;
    }

    public static List<Ordermaintenance> viewPartNStatus1(String orderNo) {
        ArrayList<Ordermaintenance> list = new ArrayList<Ordermaintenance>();
        String query = "SELECT SE03ORDER, SE03CRDATE, SE03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE03MATNO AND CT06TAG = '245') AS TITLE, SE03SUPPLIER, SE03FOREX, SE03FREQ, SE03VOLFROM, SE03ISSFROM, SE03DTSTART, SE03DTSTOP, SE03PRATE, SE03FPRICE, SE03LPRICE, SE03COPIES, SE03ISSUES, SE03STAT, stat.SE04DESC AS CurrrentStatus, SE03INVSTAT, invstat.SE04DESC AS invoiceStatus, SE03PONO, SE03ORDATE, SE03CLAIM1, SE03CLAIM2, SE03CLAIM3, SE03SUBJ FROM SEORDD LEFT JOIN SESTAT stat ON stat.SE04STAT = SE03STAT LEFT JOIN SESTAT invstat ON invstat.SE04STAT = SE03INVSTAT WHERE SE03ORDER = '" + orderNo + "'";
        System.out.println("queryviewPartNStatus1 = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Ordermaintenance partNStatus1list = new Ordermaintenance(rs.getString("SE03ORDER"), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03CRDATE"))), Handler.ifIsNull(rs.getString("SE03MATNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), Handler.ifIsNull(rs.getString("SE03SUPPLIER")), Handler.ifIsNull(rs.getString("SE03FOREX")), Handler.ifIsNull(rs.getString("SE03FREQ")), Handler.ifIsNull(rs.getString("SE03VOLFROM")), Handler.ifIsNull(rs.getString("SE03ISSFROM")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03DTSTART"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03DTSTOP"))), Handler.ifIsNull(rs.getString("SE03PRATE")), Handler.ifIsNull(rs.getString("SE03FPRICE")), Handler.ifIsNull(rs.getString("SE03LPRICE")), Handler.ifIsNull(rs.getString("SE03COPIES")), Handler.ifIsNull(rs.getString("SE03ISSUES")), Handler.ifIsNull(rs.getString("SE03STAT")), Handler.ifIsNull(rs.getString("CurrrentStatus")), Handler.ifIsNull(rs.getString("SE03INVSTAT")), Handler.ifIsNull(rs.getString("invoiceStatus")), Handler.ifIsNull(rs.getString("SE03PONO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03ORDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03CLAIM1"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03CLAIM2"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE03CLAIM3"))), Handler.ifIsNull(rs.getString("SE03SUBJ")));
                    list.add(partNStatus1list);
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

    public static List<Ordermaintenance> viewPartCardex(String orderNo) {
        ArrayList<Ordermaintenance> list2 = new ArrayList<Ordermaintenance>();
        String query = "SELECT SE06CPYNO, SE06VOLLBL, SE06ISSLBL, SE06EXPDATE, SE06RCVDATE, SE06CLAIM1, SE06CLAIM2, SE06CLAIM3, SE06ISSNO FROM SEISSU WHERE SE06ORDER = '" + orderNo + "' ";
        System.out.println("queryviewPartCardex = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partNStatus1list2 = new Ordermaintenance(Handler.ifIsNull(rs.getString("SE06CPYNO")), Handler.ifIsNull(rs.getString("SE06VOLLBL")), Handler.ifIsNull(rs.getString("SE06ISSLBL")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE06EXPDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE06RCVDATE"))), Handler.ifIsNull(rs.getString("SE06CLAIM1")), Handler.ifIsNull(rs.getString("SE06CLAIM2")), Handler.ifIsNull(rs.getString("SE06CLAIM3")), Handler.ifIsNull(rs.getString("SE06ISSNO")));
                    list2.add(partNStatus1list2);
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
        return list2;
    }

    public static List<Ordermaintenance> InvoicePaymentDetails(String orderNo) {
        ArrayList<Ordermaintenance> list2 = new ArrayList<Ordermaintenance>();
        String query = "SELECT SE07INVNO, SE07AMT, SE07BDRAF, SE07BDATE, SE07VOUCHER, SE07VCHDATE, SE07INVDATE, SE07REFERNO, SE07FPRICE, SE07FOREX, SE07PRATE FROM SEINVO WHERE SE07ORDER = '" + orderNo + "'";
        System.out.println("queryInvoicePaymentDetails = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partNStatus1list2 = new Ordermaintenance(Handler.ifIsNull(rs.getString("SE07INVNO")), Handler.ifIsNull(rs.getString("SE07AMT")), Handler.ifIsNull(rs.getString("SE07BDRAF")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE07BDATE"))), Handler.ifIsNull(rs.getString("SE07VOUCHER")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE07VCHDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE07INVDATE"))), Handler.ifIsNull(rs.getString("SE07REFERNO")), Handler.ifIsNull(rs.getString("SE07FPRICE")), Handler.ifIsNull(rs.getString("SE07FOREX")), Handler.ifIsNull(rs.getString("SE07PRATE")));
                    list2.add(partNStatus1list2);
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
        return list2;
    }

    public static List<Ordermaintenance> viewPartNStatus2(String orderNo) {
        ArrayList<Ordermaintenance> list2 = new ArrayList<Ordermaintenance>();
        String query = "SELECT CT03DOCNO, CT03LOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA = CT03LOCA) AS LOACDESC, (SELECT DISTINCT GL71DESC FROM GLBRNC, GLLOCA, CTDOCM WHERE GL71BRNC = GL05BRNC AND GL05LOCA = CT03LOCA AND CT03ORDER = '" + orderNo + "') AS BRNCH, " + "(SELECT GL10DESC FROM GLICAT WHERE GL10ICAT = CT03ICAT) AS ICAT, " + "(SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'C' AND CODE = CT03COND) AS COND, " + "(SELECT GL47DESC FROM GLSMD WHERE GL47SMD = CT03SMD) AS SMD " + "FROM CTDOCM WHERE CT03ORDER = '" + orderNo + "'";
        System.out.println("queryviewPartNStatus2 = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partNStatus1list2 = new Ordermaintenance(rs.getString("CT03DOCNO"), rs.getString("CT03LOCA"), rs.getString("LOACDESC"), rs.getString("BRNCH"), rs.getString("ICAT"), Handler.ifIsNull(rs.getString("COND")), rs.getString("SMD"));
                    list2.add(partNStatus1list2);
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
        return list2;
    }

    public static List<Ordermaintenance> viewRequestor(String orderNo) {
        ArrayList<Ordermaintenance> list3 = new ArrayList<Ordermaintenance>();
        String query = "SELECT SE02REQUEST, SE02REQUESTOR, GL14NAME, GL14DEPT, SE02DTREQUEST FROM SEREQC LEFT JOIN GLPATR ON GL14PATR = SE02REQUESTOR WHERE SE02ORDER = '" + orderNo + "'";
        System.out.println("queryviewrequestor = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partRequestor = new Ordermaintenance(rs.getString("SE02REQUEST"), rs.getString("SE02REQUESTOR"), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL14DEPT")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE02DTREQUEST"))));
                    list3.add(partRequestor);
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
        return list3;
    }

    public static List<Ordermaintenance> viewFeedback(String orderNo) {
        ArrayList<Ordermaintenance> list4 = new ArrayList<Ordermaintenance>();
        String query = " SELECT SE12FDATE, (SELECT GL40DESC FROM GLFEED WHERE GL40CODE = SE12FCODE) AS FEEDBACK, SE12OFFID FROM SEFEED WHERE SE12ORDER = '" + orderNo + "'";
        System.out.println("queryviewFeedback = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partFeedback = new Ordermaintenance(Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AC04FDATE"))), rs.getString("FEEDBACK"), rs.getString("AC04OFFID"));
                    System.out.println("feedback REQ" + rs.getString("FEEDBACK"));
                    list4.add(partFeedback);
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
        return list4;
    }

    public static List<Ordermaintenance> viewErrorInSupply(String orderNo) {
        ArrayList<Ordermaintenance> list5 = new ArrayList<Ordermaintenance>();
        String query = " SELECT SE08TRXDATE, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'F' AND CODE = SE08REASON ) AS  FEEDBACK, SE08OFFID FROM SEEISP WHERE SE08ORDER = '" + orderNo + "'";
        System.out.println("queryviewErrorInSupply = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partErrorInSupply = new Ordermaintenance(Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE08TRXDATE"))), rs.getString("FEEDBACK"), rs.getString("SE08OFFID"));
                    list5.add(partErrorInSupply);
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
        return list5;
    }

    public static List<Ordermaintenance> dispalyBack(String orderNo) {
        ArrayList<Ordermaintenance> listdisplay = new ArrayList<Ordermaintenance>();
        String query = "SELECT AC03ORDER, AC03CTRLNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = AC03CTRLNO AND CT06TAG = '245') AS TITLE, (SELECT GL39NAME FROM GLVEND WHERE GL39CODE = AC03VEND) AS VENDDESC, AC03STATUS, (SELECT GL43ACQDESC FROM GLACST WHERE GL43ACQSTAT = AC03STATUS) AS STATUSDESC, AC03REFNO, AC03ORDATE FROM ACORDD WHERE AC03ORDER = '" + orderNo + "'";
        System.out.println("querydispalyBack = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Ordermaintenance partdispalyBack = new Ordermaintenance(rs.getString("AC03ORDER"), rs.getString("AC03CTRLNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), rs.getString("VENDDESC"), rs.getString("AC03STATUS"), rs.getString("STATUSDESC"), Handler.ifIsNull(rs.getString("AC03REFNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AC03ORDATE"))));
                    listdisplay.add(partdispalyBack);
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
        return listdisplay;
    }

    public static List<Ordermaintenance> getOrderNo() {
        ArrayList<Ordermaintenance> orderno = new ArrayList<Ordermaintenance>();
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'ORDERNO'";
        System.out.println("query getOrderNo = " + query);
        Connection conn = null;
        String getOrderNo = "";
        try {
            Statement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                getOrderNo = rs.getString("GL98VALUE");
                int newOrderNo = Integer.parseInt(getOrderNo);
                System.out.println(String.valueOf(newOrderNo) + " zzz");
                String orderNo = String.format("%010d", newOrderNo);
                System.out.println("orderNo" + orderNo);
                List<Ordermaintenance> list = Ordermaintenance.dispalyBack(orderNo);
                return list;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderno;
    }

    public static List<Ordermaintenance> reqChecking(String patronID) {
        ArrayList<Ordermaintenance> patron = new ArrayList<Ordermaintenance>();
        String query = "SELECT GL14EXPDATE, GL14STAT, (SELECT  GL08DESC FROM GLSTAT WHERE GL08STAT = GL14STAT) AS STATDESC, (SELECT  GL08ACTION1 FROM GLSTAT WHERE GL08STAT = GL14STAT) AS CHARGING FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + patronID + "')";
        System.out.println("query requestor checking = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance view = new Ordermaintenance(rs.getString("GL14EXPDATE"), Handler.ifIsNull(rs.getString("GL14STAT")), Handler.ifIsNull(rs.getString("STATDESC")), rs.getString("CHARGING"));
                    patron.add(view);
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
        return patron;
    }

    public static List<Ordermaintenance> ReadMasterDetails(String ctrlno) {
        ArrayList<Ordermaintenance> list = new ArrayList<Ordermaintenance>();
        String query = "SELECT SE01SUPPLIER, GL39NAME, SE01FPRICE, SE01LPRICE, SE01PRATE, SE01FOREX, SE01FREQ FROM SESERM LEFT JOIN GLVEND ON SE01SUPPLIER = GL39CODE WHERE SE01MATNO = '" + ctrlno + "'";
        System.out.println("ReadMasterDetails = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Ordermaintenance partNStatus1list = new Ordermaintenance(Handler.ifIsNull(rs.getString("SE01SUPPLIER")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("SE01FPRICE")), Handler.ifIsNull(rs.getString("SE01LPRICE")), Handler.ifIsNull(rs.getString("SE01PRATE")), Handler.ifIsNull(rs.getString("SE01FOREX")), Handler.ifIsNull(rs.getString("SE01FREQ")));
                    list.add(partNStatus1list);
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
