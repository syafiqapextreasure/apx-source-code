/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.PatronEnquiry;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatronEnquiry {
    private String PatronID;
    private String Name;
    private String Address;
    private String DateEnrolled;
    private String ExpiryDate;
    private String ContactNo;
    private String IPAddress;
    private int Onloan;
    private int Reserved;
    private int Overdue;
    private String CallNo;
    private String Title;
    private String Status;
    private String DateReserved;
    private String ControlNo;
    private String Col1;
    private String Col2;
    private String Col3;
    private String Col4;
    private int ItemsChargedTD;
    private int ItemsChargedYTD;
    private int LateReturnsTD;
    private int LateReturnsYTD;
    private String LastCircTrxDate;
    private String LastReturned;
    private int LostItems;
    private String Suspension;
    private String GroupID;
    private String Department;
    private String Category;
    private String DOB;
    private String Gender;
    private String FinesPaid;
    private BigDecimal outstanding;
    private String AccessionNo;
    private String DateDue;
    private String TimeDue;
    private String RelatedID;
    private String RelatedName;
    private String Description;
    private String Value;

    public String getPatronID() {
        return Handler.ifIsNull(this.PatronID);
    }

    public String getName() {
        return Handler.ifIsNull(this.Name);
    }

    public String getAddress() {
        return Handler.ifIsNull(this.Address);
    }

    public String getDateEnrolled() {
        return Handler.ifIsNull(this.DateEnrolled);
    }

    public String getExpiryDate() {
        return Handler.ifIsNull(this.ExpiryDate);
    }

    public String getContactNo() {
        return Handler.ifIsNull(this.ContactNo);
    }

    public String getIPAddress() {
        return Handler.ifIsNull(this.IPAddress);
    }

    public int getOnloan() {
        return this.Onloan;
    }

    public int getReserved() {
        return this.Reserved;
    }

    public int getOverdue() {
        return this.Overdue;
    }

    public String getCallNo() {
        return Handler.ifIsNull(this.CallNo);
    }

    public String getTitle() {
        return Handler.ifIsNull(this.Title);
    }

    public String getStatus() {
        return Handler.ifIsNull(this.Status);
    }

    public String getDateReserved() {
        return Handler.ifIsNull(this.DateReserved);
    }

    public String getControlNo() {
        return Handler.ifIsNull(this.ControlNo);
    }

    public String getCol1() {
        return Handler.ifIsNull(this.Col1);
    }

    public String getCol2() {
        return Handler.ifIsNull(this.Col2);
    }

    public String getCol3() {
        return Handler.ifIsNull(this.Col3);
    }

    public String getCol4() {
        return Handler.ifIsNull(this.Col4);
    }

    public int getItemsChargedTD() {
        return this.ItemsChargedTD;
    }

    public int getItemsChargedYTD() {
        return this.ItemsChargedYTD;
    }

    public int getLateReturnsTD() {
        return this.LateReturnsTD;
    }

    public int getLateReturnsYTD() {
        return this.LateReturnsYTD;
    }

    public String getLastCircTrxDate() {
        return Handler.ifIsNull(this.LastCircTrxDate);
    }

    public String getLastReturned() {
        return Handler.ifIsNull(this.LastReturned);
    }

    public int getLostItems() {
        return this.LostItems;
    }

    public String getSuspension() {
        return Handler.ifIsNull(this.Suspension);
    }

    public String getGroupID() {
        return Handler.ifIsNull(this.GroupID);
    }

    public String getDepartment() {
        return Handler.ifIsNull(this.Department);
    }

    public String getCategory() {
        return Handler.ifIsNull(this.Category);
    }

    public String getDOB() {
        return Handler.ifIsNull(this.DOB);
    }

    public String getGender() {
        return Handler.ifIsNull(this.Gender);
    }

    public String getFinesPaid() {
        return Handler.ifIsNull(this.FinesPaid);
    }

    public String getAccessionNo() {
        return Handler.ifIsNull(this.AccessionNo);
    }

    public String getDateDue() {
        return Handler.ifIsNull(this.DateDue);
    }

    public String getTimeDue() {
        return Handler.ifIsNull(this.TimeDue);
    }

    public String getRelatedID() {
        return Handler.ifIsNull(this.RelatedID);
    }

    public String getRelatedName() {
        return Handler.ifIsNull(this.RelatedName);
    }

    public String getDescription() {
        return Handler.ifIsNull(this.Description);
    }

    public String getValue() {
        return Handler.ifIsNull(this.Value);
    }

    public PatronEnquiry(String PatronID, String Name, String Address, String DateEnrolled, String ExpiryDate, String ContactNo, String IPAddress, int Onloan, int Reserved, int Overdue, int ItemsChargedTD, int ItemsChargedYTD, int LateReturnsTD, int LateReturnsYTD, String LastCircTrxDate, String LastReturned, int LostItems, BigDecimal outstanding, String Suspension, String GroupID, String Department, String Status, String Category, String DOB, String Gender, String FinesPaid, String RelatedID, String RelatedName) {
        this.PatronID = PatronID;
        this.Name = Name;
        this.Address = Address;
        this.DateEnrolled = DateEnrolled;
        this.ExpiryDate = ExpiryDate;
        this.ContactNo = ContactNo;
        this.IPAddress = IPAddress;
        this.Onloan = Onloan;
        this.Reserved = Reserved;
        this.Overdue = Overdue;
        this.ItemsChargedTD = ItemsChargedTD;
        this.ItemsChargedYTD = ItemsChargedYTD;
        this.LateReturnsTD = LateReturnsTD;
        this.LateReturnsYTD = LateReturnsYTD;
        this.LastCircTrxDate = LastCircTrxDate;
        this.LastReturned = LastReturned;
        this.LostItems = LostItems;
        this.outstanding = outstanding;
        this.Suspension = Suspension;
        this.GroupID = GroupID;
        this.Department = Department;
        this.Status = Status;
        this.Category = Category;
        this.DOB = DOB;
        this.Gender = Gender;
        this.FinesPaid = FinesPaid;
        this.RelatedID = RelatedID;
        this.RelatedName = RelatedName;
    }

    public PatronEnquiry(String CallNo, String Title, String Status, String DateReserved, String ControlNo) {
        this.CallNo = CallNo;
        this.Title = Title;
        this.Status = Status;
        this.DateReserved = DateReserved;
        this.ControlNo = ControlNo;
    }

    public PatronEnquiry(String Col1, String Col2, String Col3, String Col4) {
        this.Col1 = Col1;
        this.Col2 = Col2;
        this.Col3 = Col3;
        this.Col4 = Col4;
    }

    public PatronEnquiry(String AccessionNo, String CallNo, String Title, String DateDue, String TimeDue, String ControlNo) {
        this.AccessionNo = AccessionNo;
        this.CallNo = CallNo;
        this.Title = Title;
        this.DateDue = DateDue;
        this.TimeDue = TimeDue;
        this.ControlNo = ControlNo;
    }

    public PatronEnquiry(String Description, String Value) {
        this.Description = Description;
        this.Value = Value;
    }

    public static List<PatronEnquiry> GetPatronDetails(String patrID) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        query = "SELECT GL14PATR, GL14NAME, GL14IMG, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL15DESC, GL14MEMDATE, GL14EXPDATE, GL14OTEL, GL14IPADD, GL14SUSPEND, GL02NAME, GL14DEPT, GL08DESC, GL07DESC, GL14DOB, GL14SEX, GL14RELID, GL14PARENTID  FROM GLPATR LEFT JOIN GLTOWN ON GL15TOWN = GL14TOWN LEFT JOIN GLGRMA ON GL02GRP = GL14GRID LEFT JOIN GLSTAT ON GL08STAT = GL14STAT LEFT JOIN GLCATE ON GL07CATE = GL14CATE WHERE UPPER(GL14PATR) = UPPER('" + patrID + "')";
        System.out.println("query GetPatronDetails : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                int countOnLoan = 0;
                int reserve = 0;
                int overdue = 0;
                int borrowed = 0;
                int borrowedToYear = 0;
                int lateReturn = 0;
                int lateReturnsYear = 0;
                int lostItem = 0;
                String circTrxDate = "";
                String returnDate = "";
                BigDecimal outstanding = null;
                String finesPaid = "";
                String newsRelatedID = "";
                String relatedName = "";
                while (rs.next()) {
                    String gender;
                    String Suspension;
                    String patr = Handler.ifIsNull(rs.getString("GL14PATR"));
                    String sRelatedID = Handler.ifIsNull(rs.getString("GL14RELID"));
                    String sParentID = Handler.ifIsNull(rs.getString("GL14PARENTID"));
                    String add1 = Handler.ifIsNull(rs.getString("GL14ADD1"));
                    String add2 = Handler.ifIsNull(rs.getString("GL14ADD2"));
                    String add3 = Handler.ifIsNull(rs.getString("GL14ADD3"));
                    String code = Handler.ifIsNull(rs.getString("GL14CODE"));
                    String town = Handler.ifIsNull(rs.getString("GL15DESC"));
                    String address = String.valueOf(add1) + "\n" + add2 + "\n" + add3 + "\n" + code + " " + town;
                    String telno = Handler.ifIsNull(rs.getString("GL14OTEL"));
                    if (!"".equals(telno) && !" ".equals(telno)) {
                        telno = String.valueOf(telno) + " (O)";
                    }
                    if (!"".equals(Suspension = Handler.ifIsNull(rs.getString("GL14SUSPEND")))) {
                        Suspension = "0";
                    }
                    if ((gender = Handler.ifIsNull(rs.getString("GL14SEX"))).equalsIgnoreCase("F")) {
                        gender = "Female";
                    } else if (gender.equalsIgnoreCase("M")) {
                        gender = "Male";
                    }
                    if (!sRelatedID.equals("") && !sRelatedID.equals(" ")) {
                        System.out.println("11111111");
                        newsRelatedID = sRelatedID;
                        relatedName = PatronEnquiry.GetCorporateName(sRelatedID);
                    } else if (sRelatedID.equals("") || sRelatedID.equals(" ")) {
                        System.out.println("222222222");
                        if (!sParentID.equals("") && !sParentID.equals(" ")) {
                            newsRelatedID = sParentID;
                            relatedName = PatronEnquiry.GetCorporateName(sParentID);
                        } else if (sParentID.equals("") || sParentID.equals(" ")) {
                            System.out.println("444444444");
                            newsRelatedID = patr;
                            relatedName = PatronEnquiry.GetCorporateName(patr);
                        }
                    }
                    System.out.println("newsRelatedID" + newsRelatedID);
                    System.out.println("relatedName" + relatedName);
                    if (!"".equals(patr)) {
                        countOnLoan = PatronEnquiry.LoadOnLoanHits(patr);
                        reserve = PatronEnquiry.GetReservationHits(patr);
                        overdue = PatronEnquiry.GetOverdueHits(patr);
                        borrowed = PatronEnquiry.GetBorrowedToDate(patr);
                        borrowedToYear = PatronEnquiry.GetBorrowedToYear(patr);
                        lateReturn = PatronEnquiry.GetLateReturn(patr);
                        lateReturnsYear = PatronEnquiry.GetLateReturnToYear(patr);
                        circTrxDate = PatronEnquiry.GetLastBorrowDate(patr);
                        finesPaid = PatronEnquiry.GetFinesCollected(patr);
                        returnDate = PatronEnquiry.GetLastReturnDate(patr);
                        lostItem = PatronEnquiry.GetLostItemCount(patr);
                        outstanding = PatronEnquiry.calculateOutstandingAmount(patr);
                    }
                    PatronEnquiry loadtabledetail = new PatronEnquiry(patr, Handler.ifIsNull(rs.getString("GL14NAME")), address, Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14MEMDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE"))), telno, Handler.ifIsNull(rs.getString("GL14IPADD")), countOnLoan, reserve, overdue, borrowed, borrowedToYear, lateReturn, lateReturnsYear, circTrxDate, returnDate, lostItem, outstanding, Suspension, Handler.ifIsNull(rs.getString("GL02NAME")), Handler.ifIsNull(rs.getString("GL14DEPT")), Handler.ifIsNull(rs.getString("GL08DESC")), Handler.ifIsNull(rs.getString("GL07DESC")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14DOB"))), gender, finesPaid, newsRelatedID, relatedName);
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

    public static int LoadOnLoanHits(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(*) AS ONLOAN FROM CTDOCM WHERE UPPER(CT03PATR) = UPPER('" + id + "') " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
        System.out.println("SQL LoadOnLoanHits" + sSQLStmt);
        int loan = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    loan = rs.getInt("ONLOAN");
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
        return loan;
    }

    public static int GetReservationHits(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT Count(*) AS RESERVED FROM CIRESV WHERE UPPER(CI03PATR) = UPPER('" + id + "')";
        System.out.println("SQL GetReservationHits" + sSQLStmt);
        int reserva = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    reserva = rs.getInt("RESERVED");
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
        return reserva;
    }

    public static int GetOverdueHits(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(*) AS OVERDUE FROM CTDOCM WHERE UPPER(CT03PATR) = UPPER('" + id + "') " + "AND (CT03STAT = 'C' Or CT03STAT = 'E') " + "AND CT03DDATE <=  '" + DateTime.getTodaySystemDate() + "'";
        System.out.println("SQL GetOverdueHits" + sSQLStmt);
        int overdue = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    overdue = rs.getInt("OVERDUE");
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
        return overdue;
    }

    public static List<PatronEnquiry> DisplayReserved(String id) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '245') AS TITLE, CI03NSTAT, CI03RDATE, CI03MATNO FROM CIRESV WHERE UPPER(CI03PATR) = UPPER('" + id + "')";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, CI03NSTAT, CI03RDATE, CI03MATNO FROM CIRESV WHERE UPPER(CI03PATR) = UPPER('" + id + "')";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI03MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, CI03NSTAT, CI03RDATE, CI03MATNO FROM CIRESV WHERE UPPER(CI03PATR) = UPPER('" + id + "')";
        }
        System.out.println("query DisplayReserved : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> call = ISBD.getPunctuation("090");
                while (rs.next()) {
                    String status = Handler.ifIsNull(rs.getString("CI03NSTAT"));
                    if (status.equals("X")) {
                        status = "Waiting List";
                    } else if (status.equals("A")) {
                        status = "Awaiting Collection";
                    }
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), status, Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI03RDATE"))), Handler.ifIsNull(rs.getString("CI03MATNO")));
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

    public static List<PatronEnquiry> DisplayOnLoanOverdue(String id, String m_sMode) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (m_sMode.equalsIgnoreCase("ViewOnloan")) {
            if (dbtype.getDBType().equals("mssql")) {
                System.out.println("sql here");
                query = "SELECT CT03DOCNO, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245') AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' OR CT03STAT = 'E') " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME";
            } else if (dbtype.getDBType().equals("oracle")) {
                System.out.println("oracle here");
                query = "SELECT CT03DOCNO, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' OR CT03STAT = 'E') " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME";
            } else if (dbtype.getDBType().equals("mysql")) {
                System.out.println("MYSQL here");
                query = "SELECT CT03DOCNO, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' OR CT03STAT = 'E') " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME";
            }
        } else if (m_sMode.equalsIgnoreCase("ViewOverdue")) {
            if (dbtype.getDBType().equals("mssql")) {
                System.out.println("sql here");
                query = "SELECT CT03DOCNO, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245') AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E') " + "AND CT03DDATE <= '" + DateTime.getTodaySystemDate() + "' " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME ";
            } else if (dbtype.getDBType().equals("oracle")) {
                System.out.println("oracle here");
                query = "SELECT CT03DOCNO, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E') " + "AND CT03DDATE <= '" + DateTime.getTodaySystemDate() + "' " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME ";
            } else if (dbtype.getDBType().equals("mysql")) {
                System.out.println("MYSQL here");
                query = "SELECT CT03DOCNO, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT03MATNO = CT06MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, CT03DDATE, CT03DTIME, CT03MATNO FROM CTDOCM WHERE CT03PATR = '" + id + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E') " + "AND CT03DDATE <= '" + DateTime.getTodaySystemDate() + "' " + "ORDER BY CT03DDATE, CT03DTIME, CT03BDATE, CT03BTIME ";
            }
        }
        System.out.println("query DisplayOnLoanOverdue : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> call = ISBD.getPunctuation("090");
                while (rs.next()) {
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Handler.ifIsNull(rs.getString("CT03DOCNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CT03DDATE"))), Handler.ifIsNull(rs.getString("CT03DTIME")), Handler.ifIsNull(rs.getString("CT03MATNO")));
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

    public static List<PatronEnquiry> DisplayRequestStatus(String id, String reqStat) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        if (reqStat.equals("acqreq")) {
            query = "SELECT AC01DATEREQ, AC01REQUEST, AC01TITLE, GL43ACQDESC FROM ACREQC LEFT JOIN GLACST ON AC01STATUS = GL43ACQSTAT WHERE UPPER(AC01REQUESTOR) = UPPER('" + id + "') " + "ORDER BY AC01DATEREQ, AC01REQUEST";
        } else if (reqStat.equals("serreq")) {
            query = "SELECT SE02DTREQUEST, SE02REQUEST, SE02TITLE, SE04DESC FROM SEREQC LEFT JOIN SESTAT ON SE04STAT = SE02STATUS WHERE UPPER(SE02REQUESTOR) = UPPER('" + id + "') " + "ORDER BY SE02DTREQUEST";
        }
        System.out.println("query DisplayRequestStatus : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String Column1 = "";
                String Column2 = "";
                String Column3 = "";
                String Column4 = "";
                while (rs.next()) {
                    if (reqStat.equals("acqreq")) {
                        Column1 = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AC01DATEREQ")));
                        Column2 = Handler.ifIsNull(rs.getString("AC01REQUEST"));
                        Column3 = Handler.ifIsNull(rs.getString("AC01TITLE"));
                        Column4 = Handler.ifIsNull(rs.getString("GL43ACQDESC"));
                    } else if (reqStat.equals("serreq")) {
                        Column1 = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE02DTREQUEST")));
                        Column2 = Handler.ifIsNull(rs.getString("SE02REQUEST"));
                        Column3 = Handler.ifIsNull(rs.getString("SE02TITLE"));
                        Column4 = Handler.ifIsNull(rs.getString("SE04DESC"));
                    }
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Column1, Column2, Column3, Column4);
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

    public static int GetBorrowedToDate(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT  COUNT(CI02PATR) AS BorrowedToDate FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "')";
        System.out.println("SQL GetBorrowedToDate" + sSQLStmt);
        int Borrowed = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    Borrowed = rs.getInt("BorrowedToDate");
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
        return Borrowed;
    }

    public static int GetBorrowedToYear(String id) {
        String sSQLStmt = "";
        String sCurDate = null;
        String sStartDate = null;
        sCurDate = DateTime.getTodaySystemDate();
        sStartDate = String.valueOf(sCurDate.substring(0, 4)) + "0101";
        sSQLStmt = "SELECT  COUNT(CI02PATR) AS BorrowedToYear FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') " + "AND CI02CDATE BETWEEN '" + sStartDate + "' AND '" + sCurDate + "'";
        System.out.println("SQL GetBorrowedToYear" + sSQLStmt);
        int BorrowedToYear = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    BorrowedToYear = rs.getInt("BorrowedToYear");
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
        return BorrowedToYear;
    }

    public static int GetLateReturn(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(*) AS LateReturn FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') " + "AND CI02FLAG = 'D' " + "AND CI02DDATE <  CI02DIDATE";
        System.out.println("SQL GetLateReturn" + sSQLStmt);
        int LateReturn = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    LateReturn = rs.getInt("LateReturn");
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
        return LateReturn;
    }

    public static int GetLateReturnToYear(String id) {
        String sSQLStmt = "";
        String sCurDate = null;
        String sStartDate = null;
        sCurDate = DateTime.getTodaySystemDate();
        sStartDate = String.valueOf(sCurDate.substring(0, 4)) + "0101";
        sSQLStmt = "SELECT  COUNT(CI02PATR) AS LateReturnToYear FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') " + "AND CI02DIDATE > CI02DDATE " + "AND (CI02DIDATE BETWEEN '" + sStartDate + "' AND '" + sCurDate + "')";
        System.out.println("SQL GetLateReturnToYear" + sSQLStmt);
        int LateReturnToYear = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    LateReturnToYear = rs.getInt("LateReturnToYear");
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
        return LateReturnToYear;
    }

    public static String GetLastBorrowDate(String id) {
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT TOP 1 CI02CDATE FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') " + "ORDER BY CI02CDATE DESC";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT CI02CDATE FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') AND rownum = 1 " + "ORDER BY CI02CDATE DESC";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT CI02CDATE FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') LIMIT 1 " + "ORDER BY CI02CDATE DESC";
        }
        System.out.println("SQL GetLastBorrowDate" + query);
        String TrxDate = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    TrxDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02CDATE")));
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
        return TrxDate;
    }

    public static String GetLastReturnDate(String id) {
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT TOP 1 CI02CDATE, CI02DIDATE  FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') " + "ORDER BY CI02DIDATE DESC";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT CI02CDATE, CI02DIDATE  FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') AND rownum = 1 " + "ORDER BY CI02DIDATE DESC";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT CI02CDATE, CI02DIDATE  FROM CICIRC WHERE UPPER(CI02PATR) = UPPER('" + id + "') AND LIMIT 1" + "ORDER BY CI02DIDATE DESC";
        }
        System.out.println("SQL GetLastReturnDate" + query);
        String ReturnDate = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReturnDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DIDATE")));
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
        return ReturnDate;
    }

    public static int GetLostItemCount(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(CT03DOCNO) AS LOST FROM CTDOCM WHERE UPPER(CT03PATR) = UPPER('" + id + "') " + "AND CT03STAT ='L'";
        System.out.println("SQL GetLostItemCount" + sSQLStmt);
        int LostItem = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    LostItem = rs.getInt("LOST");
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
        return LostItem;
    }

    private static BigDecimal calculateOutstandingAmount(String patronId) {
        String query = "SELECT RE01AMT FROM RETRXN, GLTRXC WHERE RE01PATR =? AND RE01CODE=GL38TXCD\r\nAND  GL38MODE = 'D' ORDER BY RE01DATE\r\n";
        String query1 = "SELECT RE01AMT FROM RETRXN, GLTRXC WHERE RE01PATR =?\r\nAND RE01CODE=GL38TXCD AND GL38MODE='C' AND RE01STAT = '1'\r\n";
        BigDecimal outstandingAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal paidAmount = BigDecimal.ZERO;
        Connection conn = null;
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                stmt = conn.prepareStatement(query);
                stmt.setString(1, patronId);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    i = 1;
                    while (i <= columnCount) {
                        System.out.println("Total Amount: " + rs.getBigDecimal(i));
                        totalAmount = totalAmount.add(rs.getBigDecimal(i));
                        ++i;
                    }
                    System.out.println(totalAmount);
                }
                PreparedStatement stmt1 = null;
                ResultSet rs1 = null;
                stmt1 = conn.prepareStatement(query1);
                stmt1.setString(1, patronId);
                rs1 = stmt1.executeQuery();
                ResultSetMetaData metadata1 = rs.getMetaData();
                int columnCount1 = metadata1.getColumnCount();
                int i2 = 1;
                while (i2 <= columnCount1) {
                    System.out.println("[" + metadata1.getColumnTypeName(i2) + "|" + metadata1.getColumnName(i2) + "]" + ", ");
                    ++i2;
                }
                while (rs1.next()) {
                    i2 = 1;
                    while (i2 <= columnCount1) {
                        System.out.println("Paid Amount: " + rs1.getBigDecimal(i2));
                        paidAmount = paidAmount.add(rs1.getBigDecimal(i2));
                        ++i2;
                    }
                    System.out.println(paidAmount);
                }
                outstandingAmount = totalAmount.subtract(paidAmount);
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
        return outstandingAmount;
    }

    public static String GetFinesCollected(String id) {
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT SUM(RE01AMT) AS FINECOL FROM RETRXN, GLTRXC WHERE UPPER(RE01PATR) = UPPER('" + id + "') " + "AND RE01CODE = GL38TXCD AND GL38TYPE = 'R'";
        System.out.println("SQL GetFinesCollected" + query);
        String finesPaid = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    finesPaid = Handler.ifIsNull(Handler.decimalConversion(rs.getString("FINECOL")));
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
        return finesPaid;
    }

    public static String GetCorporateName(String id) {
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT GL14NAME FROM GLPATR WHERE GL14PATR = '" + id + "'";
        DBConnection dbtype = new DBConnection();
        System.out.println("SQL GetCorporateName" + query);
        String getName = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getName = Handler.ifIsNull(rs.getString("GL14NAME"));
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
        return getName;
    }

    /*
     * Exception decompiling
     */
    public static List<PatronEnquiry> GetPatronDetails2(String patrID) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [20[DOLOOP]], but top level block is 7[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static List<PatronEnquiry> GetChildInfo(String id, String stat) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT GL14PATR, GL14NAME, GL08DESC FROM GLPATR LEFT JOIN GLSTAT ON GL08STAT = GL14STAT WHERE GL14PARENTID = '" + id + "'";
        System.out.println("query GetChildInfo : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL08DESC")), stat);
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

    public static List<PatronEnquiry> GetParentInfo(String related, String id, String stat) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT GL14PATR, GL14NAME, GL08DESC FROM GLPATR LEFT JOIN GLSTAT ON GL08STAT = GL14STAT WHERE GL14PARENTID = '" + related + "' AND GL14PATR = '" + id + "'";
        System.out.println("query GetParentInfo : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL08DESC")), stat);
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

    public static List<PatronEnquiry> GetMiscellaneous(String patrID) {
        ArrayList<PatronEnquiry> list = new ArrayList<PatronEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        query = "SELECT GL65PATR, GL65VALUE, GL66DESC FROM GLATTRI, GLPATREX WHERE GL65ATTRI = GL66ATTRI AND UPPER(GL65PATR) = UPPER('" + patrID + "') " + "ORDER BY GL66DESC";
        System.out.println("query Miscellaneous : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronEnquiry loadtabledetail = new PatronEnquiry(Handler.ifIsNull(rs.getString("GL66DESC")), Handler.ifIsNull(rs.getString("GL65VALUE")));
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
