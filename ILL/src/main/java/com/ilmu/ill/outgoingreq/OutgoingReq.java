/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.outgoingreq;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.ill.IncomingRequest.IncomingRequest;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutgoingReq {
    private String referenceNumber;
    private String materialNo;
    private String title;
    private String libName;
    private String requestorName;
    private String requestedDate;
    private String lblLibName;
    private String lblLibAdd1;
    private String lblLibAdd2;
    private String lblLibAdd3;
    private String lblLibTelNo;
    private String sExpDate;
    private String sPatronCat;
    private String sLLBorrowerCategory;
    private String accno;
    private String requestorId;
    private String libId;
    private String contact;
    private String crdate;
    private String creby;
    private String doctype;
    private String vol;
    private String iss;
    private String pageno;
    private String dtdue;
    private String candelete;

    public String getreferenceNumber() {
        return Handler.ifIsNull(this.referenceNumber);
    }

    public String getmaterialNo() {
        return Handler.ifIsNull(this.materialNo);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getlibName() {
        return Handler.ifIsNull(this.libName);
    }

    public String getrequestorName() {
        return Handler.ifIsNull(this.requestorName);
    }

    public String getrequestedDate() {
        return Handler.ifIsNull(this.requestedDate);
    }

    public String getlblLibName() {
        return Handler.ifIsNull(this.lblLibName);
    }

    public String getlblLibAdd1() {
        return Handler.ifIsNull(this.lblLibAdd1);
    }

    public String getlblLibAdd2() {
        return Handler.ifIsNull(this.lblLibAdd2);
    }

    public String getlblLibAdd3() {
        return Handler.ifIsNull(this.lblLibAdd3);
    }

    public String getlblLibTelNo() {
        return Handler.ifIsNull(this.lblLibTelNo);
    }

    public String getsExpDate() {
        return Handler.ifIsNull(this.sExpDate);
    }

    public String getsPatronCat() {
        return Handler.ifIsNull(this.sPatronCat);
    }

    public String getsLLBorrowerCategory() {
        return Handler.ifIsNull(this.sLLBorrowerCategory);
    }

    public String getaccno() {
        return Handler.ifIsNull(this.accno);
    }

    public String getrequestorId() {
        return Handler.ifIsNull(this.requestorId);
    }

    public String getlibId() {
        return Handler.ifIsNull(this.libId);
    }

    public String getcontact() {
        return Handler.ifIsNull(this.contact);
    }

    public String getcrdate() {
        return Handler.ifIsNull(this.crdate);
    }

    public String getcreby() {
        return Handler.ifIsNull(this.creby);
    }

    public String getdoctype() {
        return Handler.ifIsNull(this.doctype);
    }

    public String getvol() {
        return Handler.ifIsNull(this.vol);
    }

    public String getiss() {
        return Handler.ifIsNull(this.iss);
    }

    public String getpageno() {
        return Handler.ifIsNull(this.pageno);
    }

    public String getdtdue() {
        return Handler.ifIsNull(this.dtdue);
    }

    public String getcandelete() {
        return Handler.ifIsNull(this.candelete);
    }

    public OutgoingReq(String referenceNumber, String materialNo, String title, String libName, String requestorName, String requestedDate, String candelete) {
        this.referenceNumber = referenceNumber;
        this.materialNo = materialNo;
        this.title = title;
        this.libName = libName;
        this.requestorName = requestorName;
        this.requestedDate = requestedDate;
        this.candelete = candelete;
    }

    public OutgoingReq(String lblLibName, String lblLibAdd1, String lblLibAdd2, String lblLibAdd3, String lblLibTelNo, String sExpDate, String sPatronCat, String sLLBorrowerCategory) {
        this.lblLibName = lblLibName;
        this.lblLibAdd1 = lblLibAdd1;
        this.lblLibAdd2 = lblLibAdd2;
        this.lblLibAdd3 = lblLibAdd3;
        this.lblLibTelNo = lblLibTelNo;
        this.sExpDate = sExpDate;
        this.sPatronCat = sPatronCat;
        this.sLLBorrowerCategory = sLLBorrowerCategory;
    }

    public OutgoingReq(String referenceNumber, String materialNo, String title, String accno, String requestorId, String libId, String contact, String requestedDate, String sExpDate, String crdate, String creby, String doctype, String vol, String iss, String pageno, String dtdue) {
        this.referenceNumber = referenceNumber;
        this.materialNo = materialNo;
        this.title = title;
        this.accno = accno;
        this.requestorId = requestorId;
        this.libId = libId;
        this.contact = contact;
        this.requestedDate = requestedDate;
        this.sExpDate = sExpDate;
        this.crdate = crdate;
        this.creby = creby;
        this.doctype = doctype;
        this.vol = vol;
        this.iss = iss;
        this.pageno = pageno;
        this.dtdue = dtdue;
    }

    public static List<OutgoingReq> search(String input_criteria, String search_type) {
        ArrayList<OutgoingReq> list = new ArrayList<OutgoingReq>();
        String query = "SELECT CI04REQUEST, CI04MATNO, CI04TITLE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245') AS TITLE, lendlib.GL14NAME AS LENDLIB, req.GL14NAME AS REQNAME, CI04DTREQUESTED FROM CIOUTR LEFT JOIN GLPATR lendlib ON lendlib.GL14PATR = CI04LENDINGLIB LEFT JOIN GLPATR req ON req.GL14PATR = CI04REQUESTOR WHERE ";
        if (search_type.equals("lendlib")) {
            query = String.valueOf(query) + "UPPER(CI04LENDINGLIB) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("reqID")) {
            query = String.valueOf(query) + "UPPER(CI04REQUESTOR) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("refno")) {
            query = String.valueOf(query) + "UPPER(CI04REQUEST) = UPPER('" + input_criteria + "')";
        }
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
                    String req = Handler.ifIsNull(rs.getString("CI04REQUEST"));
                    String matno = Handler.ifIsNull(rs.getString("CI04MATNO"));
                    String title = matno.equals(" ") ? Handler.ifIsNull(rs.getString("CI04TITLE")) : Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd);
                    boolean canEdit2 = OutgoingReq.CanEdit(req);
                    boolean CanDelete2 = OutgoingReq.CanDelete(req);
                    String msg = null;
                    msg = !canEdit2 || !CanDelete2 ? "Record cannot be deleted." : "";
                    OutgoingReq loadtabledetail = new OutgoingReq(req, matno, title, Handler.ifIsNull(rs.getString("LENDLIB")), Handler.ifIsNull(rs.getString("REQNAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTREQUESTED"))), msg);
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

    public static List<OutgoingReq> GetLibraryDetails(String id) {
        ArrayList<OutgoingReq> list = new ArrayList<OutgoingReq>();
        String query = "SELECT GL14NAME, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14OTEL, GL14EXPDATE, GL14CATE FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + id + "')";
        System.out.println("query search : " + query);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found for ID: " + id);
                } else {
                    String illcate = IncomingRequest.LoadILLPatronCat();
                    while (rs.next()) {
                        OutgoingReq loadtabledetail = new OutgoingReq(Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL14OFFADD1")), Handler.ifIsNull(rs.getString("GL14OFFADD2")), Handler.ifIsNull(rs.getString("GL14OFFADD3")), Handler.ifIsNull(rs.getString("GL14OTEL")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE"))), Handler.ifIsNull(rs.getString("GL14CATE")), illcate);
                        list.add(loadtabledetail);
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean ExceedRequestAllowed(String vsRequestorID) throws SQLException {
        boolean tempExceedRequestAllowed = false;
        int iTotalRequest = 0;
        int iMaxRequest = 0;
        iTotalRequest = OutgoingReq.GetTotalRequest(vsRequestorID);
        tempExceedRequestAllowed = iTotalRequest >= (iMaxRequest = OutgoingReq.GetMaxRequest(vsRequestorID));
        return tempExceedRequestAllowed;
    }

    private static int GetTotalRequest(String vsRequestorID) throws SQLException {
        int tempGetTotalRequest;
        block12: {
            tempGetTotalRequest = 0;
            String sSQLStmt = "SELECT COUNT(*) AS TOTAL FROM CIOUTR WHERE UPPER(CI04REQUESTOR) = UPPER('" + vsRequestorID + "') " + "AND CI04RECFLAG IS NULL";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        tempGetTotalRequest = 0;
                        break block12;
                    }
                    tempGetTotalRequest = rs.getInt("TOTAL");
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
        }
        return tempGetTotalRequest;
    }

    private static int GetMaxRequest(String vsRequestorID) throws SQLException {
        int tempGetMaxRequest;
        block12: {
            tempGetMaxRequest = 0;
            String sPatronCate = OutgoingReq.GetPatronCategory(vsRequestorID);
            String sSQLStmt = "SELECT GL07CATE, GL07ILLOUT FROM GLCATE WHERE GL07CATE = '" + sPatronCate + "'";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        tempGetMaxRequest = 0;
                        break block12;
                    }
                    tempGetMaxRequest = rs.getInt("GL07ILLOUT");
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
        }
        return tempGetMaxRequest;
    }

    public static String GetPatronCategory(String vsRequestorID) throws SQLException {
        String tempGetPatronCategory = null;
        String sSQLStmt = "SELECT GL14CATE FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + vsRequestorID + "')";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    tempGetPatronCategory = Handler.ifIsNull(rs.getString("GL14CATE"));
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
        return tempGetPatronCategory;
    }

    public static boolean AddNewRecord(String cntrlno, String accno, String doctype, String vol, String iss, String pageNo, String dateReq, String expDate, String dueDate, String libId, String contactperson, String patr, String daterec, String recby, String outgoingConvert) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("CI04REQUEST", outgoingConvert);
        valueStr.put("CI04MATNO", cntrlno);
        valueStr.put("CI04ACCESSION", accno);
        valueStr.put("CI04REQUESTOR", patr);
        valueStr.put("CI04LENDINGLIB", libId);
        valueStr.put("CI04CONTACT", contactperson);
        valueStr.put("CI04DTREQUESTED", dateReq);
        valueStr.put("CI04DTEXPECTED", expDate);
        valueStr.put("CI04CRDATE", daterec);
        valueStr.put("CI04CREBY", recby);
        valueStr.put("CI04DOTYPE", doctype);
        valueStr.put("CI04VOLUME", vol);
        valueStr.put("CI04ISSUES", iss);
        valueStr.put("CI04PAGENO", pageNo);
        valueStr.put("CI04DTDUED", dueDate);
        String query = QueryBuilder.createInsertQuery("CIOUTR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static List<OutgoingReq> editView(String refno) {
        ArrayList<OutgoingReq> list = new ArrayList<OutgoingReq>();
        String query = "SELECT CI04REQUEST, CI04MATNO, CI04TITLE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245') AS TITLE, CI04ACCESSION, CI04REQUESTOR, CI04LENDINGLIB, CI04CONTACT, CI04DTREQUESTED, CI04DTEXPECTED, CI04CRDATE, CI04CREBY, CI04DOTYPE, CI04VOLUME, CI04ISSUES, CI04PAGENO, CI04DTDUED FROM CIOUTR WHERE CI04REQUEST = '" + refno + "'";
        System.out.println("query editView : " + query);
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
                    String req = Handler.ifIsNull(rs.getString("CI04REQUEST"));
                    String matno = Handler.ifIsNull(rs.getString("CI04MATNO"));
                    String title = matno.equals(" ") ? Handler.ifIsNull(rs.getString("CI04TITLE")) : Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd);
                    String expdate = rs.getString("CI04DTEXPECTED").equals(" ") ? "" : Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTEXPECTED")));
                    String duedate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTDUED")));
                    OutgoingReq loadtabledetail = new OutgoingReq(req, matno, title, Handler.ifIsNull(rs.getString("CI04ACCESSION")), Handler.ifIsNull(rs.getString("CI04REQUESTOR")), Handler.ifIsNull(rs.getString("CI04LENDINGLIB")), Handler.ifIsNull(rs.getString("CI04CONTACT")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTREQUESTED"))), expdate, Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04CRDATE"))), Handler.ifIsNull(rs.getString("CI04CREBY")), Handler.ifIsNull(rs.getString("CI04DOTYPE")), Handler.ifIsNull(rs.getString("CI04VOLUME")), Handler.ifIsNull(rs.getString("CI04ISSUES")), Handler.ifIsNull(rs.getString("CI04PAGENO")), duedate);
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

    public static boolean SaveChangesAndLog(String refno, String cntrlno, String accno, String doctype, String vol, String iss, String pageNo, String dateReq, String expDate, String dueDate, String libId, String contactperson, String patr) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI04REQUEST", refno);
        valueStr.put("CI04MATNO", cntrlno);
        valueStr.put("CI04ACCESSION", accno);
        valueStr.put("CI04REQUESTOR", patr);
        valueStr.put("CI04LENDINGLIB", libId);
        valueStr.put("CI04CONTACT", contactperson);
        valueStr.put("CI04DTREQUESTED", dateReq);
        valueStr.put("CI04DTEXPECTED", expDate);
        valueStr.put("CI04DTDUED", dueDate);
        valueStr.put("CI04VOLUME", vol);
        valueStr.put("CI04ISSUES", iss);
        valueStr.put("CI04PAGENO", pageNo);
        valueStr.put("CI04DOTYPE", doctype);
        String query = QueryBuilder.createUpdateQuery("CIOUTR", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    private static boolean CanEdit(String vsKey) {
        boolean tempCanEdit = false;
        String sSQLStmt = "SELECT CI04RECFLAG FROM CIOUTR WHERE CI04REQUEST = '" + vsKey + "'";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (!rs.next()) {
                    tempCanEdit = false;
                } else if (!Handler.ifIsNull(rs.getString("CI04RECFLAG")).equals("")) {
                    tempCanEdit = true;
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
        return tempCanEdit;
    }

    private static boolean CanDelete(String vsKey) {
        boolean tempCanDelete = false;
        String sSQLStmt = "SELECT COUNT(*) AS TOTAL FROM ILREQC WHERE IL01REQST0='" + vsKey + "' " + "AND IL01STATUS='02'";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (!rs.next()) {
                    tempCanDelete = false;
                } else if (rs.getInt("TOTAL") <= 0) {
                    tempCanDelete = true;
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
        return tempCanDelete;
    }

    public static void DeleteCurrentRecord(String refno) {
        String query = "DELETE FROM CIOUTR WHERE CI04REQUEST = '" + refno + "'";
        Connection conn = null;
        try {
            try {
                Object stmt = null;
                Object rs = null;
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                    conn.commit();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
                conn.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
