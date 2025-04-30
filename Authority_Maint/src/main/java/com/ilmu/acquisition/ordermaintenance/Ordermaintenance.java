/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.ordermaintenance;

import com.ilmu.global.DateTime;
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

    public Ordermaintenance(String orderNumber, String ctrlno, String title, String vendor, String descVendor, String ordermode, String source, String currency, String exchangeRate, String fprice, String lprice, String orderSet, String ordercopies, String currentStatus, String currentStatusDesc, String refno, String oderDate, String expectedDate, String claim1, String claim2, String claim3) {
        this.orderNumber = orderNumber;
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

    public Ordermaintenance(String accno, String location, String locadesc, String branch, String itemCategory, String condition, String smd) {
        this.accno = accno;
        this.location = location;
        this.locadesc = locadesc;
        this.branch = branch;
        this.itemCategory = itemCategory;
        this.condition = condition;
        this.smd = smd;
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

    public static List<Ordermaintenance> viewPartNStatus1(String orderNo) {
        ArrayList<Ordermaintenance> list = new ArrayList<Ordermaintenance>();
        String query = "SELECT AC03ORDER, AC03CTRLNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = AC03CTRLNO AND CT06TAG = '245') AS TITLE,AC03VEND, (SELECT GL39NAME FROM GLVEND WHERE GL39CODE = AC03VEND) AS VENDDESC, AC03ACQMODE, AC03SOURCE, AC03FOREX, AC03ERATE, AC03FPRICE, AC03LPRICE, AC03SETS, AC03COPIES, AC03STATUS, (SELECT GL43ACQDESC FROM GLACST WHERE GL43ACQSTAT = AC03STATUS) STATUSDESC, AC03REFNO, AC03ORDATE, AC03EXDATE, AC03CLAIM1, AC03CLAIM2, AC03CLAIM3 FROM ACORDD WHERE AC03ORDER = '" + orderNo + "' ";
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
                    Ordermaintenance partNStatus1list = new Ordermaintenance(rs.getString("AC03ORDER"), rs.getString("AC03CTRLNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), rs.getString("AC03VEND"), rs.getString("VENDDESC"), rs.getString("AC03ACQMODE"), rs.getString("AC03SOURCE"), rs.getString("AC03FOREX"), rs.getString("AC03ERATE"), rs.getString("AC03FPRICE"), rs.getString("AC03LPRICE"), rs.getString("AC03SETS"), rs.getString("AC03COPIES"), rs.getString("AC03STATUS"), rs.getString("STATUSDESC"), rs.getString("AC03REFNO"), Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC03ORDATE"))), Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC03EXDATE"))), rs.getString("AC03CLAIM1"), rs.getString("AC03CLAIM2"), rs.getString("AC03CLAIM3"));
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
        String query = "SELECT AC01REQUEST, AC01REQUESTOR, (SELECT GL14NAME FROM GLPATR WHERE GL14PATR = AC01REQUESTOR) AS REQNAME, (SELECT GL11NAME FROM GLDEPT WHERE GL11DEPT = (SELECT GL14DEPT FROM GLPATR WHERE GL14PATR = AC01REQUESTOR)) AS DEPT, AC01DATEREQ FROM ACREQC WHERE AC01ORDER = '" + orderNo + "'";
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
                    Ordermaintenance partRequestor = new Ordermaintenance(rs.getString("AC01REQUEST"), rs.getString("AC01REQUESTOR"), rs.getString("REQNAME"), Handler.ifIsNull(rs.getString("DEPT")), Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC01DATEREQ"))));
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
        String query = "SELECT AC04FDATE, (SELECT GL40DESC FROM GLFEED WHERE GL40CODE = AC04FCODE) AS FEEDBACK, AC04OFFID FROM ACFEED WHERE AC04ORDER = '" + orderNo + "'";
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
                    Ordermaintenance partFeedback = new Ordermaintenance(Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC04FDATE"))), rs.getString("FEEDBACK"), rs.getString("AC04OFFID"));
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
        String query = "SELECT AC08TRXDATE, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'F' AND CODE = AC08REASON ) AS  FEEDBACK, AC08OFFID FROM ACEISX WHERE AC08ORDER = '" + orderNo + "'";
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
                    Ordermaintenance partErrorInSupply = new Ordermaintenance(Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC08TRXDATE"))), rs.getString("FEEDBACK"), rs.getString("AC08OFFID"));
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
                    Ordermaintenance partdispalyBack = new Ordermaintenance(rs.getString("AC03ORDER"), rs.getString("AC03CTRLNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), rs.getString("VENDDESC"), rs.getString("AC03STATUS"), rs.getString("STATUSDESC"), Handler.ifIsNull(rs.getString("AC03REFNO")), Handler.ifIsNull(DateTime.DBToDisplayFormat(rs.getString("AC03ORDATE"))));
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
}
