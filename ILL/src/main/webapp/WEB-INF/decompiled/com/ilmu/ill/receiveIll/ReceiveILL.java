/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.receiveIll;

import com.ilmu.global.DateFormatter;
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

public class ReceiveILL {
    private String sReferenceNo;
    private String sTitle;
    private String sCallNo;
    private String sCtrlNo;
    private String sRequestor;
    private String sLendingLib;
    private String sReqDate;
    private String sExptDate;
    private String sReceiveDate;
    private String sDueDate;
    private String iCopy;
    private String sreqId;

    public String getsReferenceNo() {
        return Handler.ifIsNull(this.sReferenceNo);
    }

    public String getsTitle() {
        return Handler.ifIsNull(this.sTitle);
    }

    public String getsCallNo() {
        return Handler.ifIsNull(this.sCallNo);
    }

    public String getsCtrlNo() {
        return Handler.ifIsNull(this.sCtrlNo);
    }

    public String getsRequestor() {
        return Handler.ifIsNull(this.sRequestor);
    }

    public String getsLendingLib() {
        return Handler.ifIsNull(this.sLendingLib);
    }

    public String getsReqDate() {
        return Handler.ifIsNull(this.sReqDate);
    }

    public String getsExptDate() {
        return Handler.ifIsNull(this.sExptDate);
    }

    public String getsReceiveDate() {
        return Handler.ifIsNull(this.sReceiveDate);
    }

    public String getsDueDate() {
        return Handler.ifIsNull(this.sDueDate);
    }

    public String getsiCopy() {
        return Handler.ifIsNull(this.iCopy);
    }

    public String getsreqId() {
        return Handler.ifIsNull(this.sreqId);
    }

    public ReceiveILL(String sReferenceNo, String sTitle) {
        this.sReferenceNo = sReferenceNo;
        this.sTitle = sTitle;
    }

    public ReceiveILL(String sCallNo, String sCtrlNo, String sTitle, String sRequestor, String sreqId, String sLendingLib, String sReqDate, String sExptDate, String sReceiveDate, String sDueDate, String iCopy) {
        this.sCallNo = sCallNo;
        this.sCtrlNo = sCtrlNo;
        this.sTitle = sTitle;
        this.sRequestor = sRequestor;
        this.sreqId = sreqId;
        this.sLendingLib = sLendingLib;
        this.sReqDate = sReqDate;
        this.sExptDate = sExptDate;
        this.sReceiveDate = sReceiveDate;
        this.sDueDate = sDueDate;
        this.iCopy = iCopy;
    }

    public static List<ReceiveILL> LoadRecordset() {
        ArrayList<ReceiveILL> list = new ArrayList<ReceiveILL>();
        String query = "SELECT CI04REQUEST,(SELECT CT05SRAW FROM CTPONT, CTTITL  WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245') AS TITLE FROM CIOUTR WHERE CI04DTRECEIVED IS NULL AND CI04MATNO IS NOT NULL";
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
                    ReceiveILL loadtabledetail = new ReceiveILL(Handler.ifIsNull(rs.getString("CI04REQUEST")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title));
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

    public static List<ReceiveILL> CheckOutgoingRequest(String vsRequestNo) {
        ArrayList<ReceiveILL> list = new ArrayList<ReceiveILL>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            query = "SELECT CI04REQUEST,CI04MATNO,(SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245') AS TITLE, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '090') AS CALLNUMBER, Reqname.GL14NAME AS REQNAME, CI04REQUESTOR, lendlib.GL14NAME AS LIBLEND, CI04DTREQUESTED,CI04DTEXPECTED,CI04DTRECEIVED,CI04COPIES, CI04DTDUED FROM CIOUTR LEFT JOIN GLPATR Reqname ON Reqname.GL14PATR = CI04REQUESTOR LEFT JOIN GLPATR lendlib ON lendlib.GL14PATR = CI04LENDINGLIB WHERE CI04REQUEST = '" + vsRequestNo + "'";
        } else if (dbtype.getDBType().equals("oracle")) {
            query = "SELECT CI04REQUEST,CI04MATNO,(SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, Reqname.GL14NAME  AS REQNAME, CI04REQUESTOR, lendlib.GL14NAME AS LIBLEND, CI04DTREQUESTED,CI04DTEXPECTED,CI04DTRECEIVED,CI04COPIES, CI04DTDUED FROM CIOUTR LEFT JOIN GLPATR Reqname ON Reqname.GL14PATR = CI04REQUESTOR LEFT JOIN GLPATR lendlib ON lendlib.GL14PATR = CI04LENDINGLIB WHERE CI04REQUEST = '" + vsRequestNo + "'";
        } else if (dbtype.getDBType().equals("mysql")) {
            query = "SELECT CI04REQUEST,CI04MATNO,(SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI04MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, Reqname.GL14NAME  AS REQNAME, CI04REQUESTOR, lendlib.GL14NAME AS LIBLEND, CI04DTREQUESTED,CI04DTEXPECTED,CI04DTRECEIVED,CI04COPIES, CI04DTDUED FROM CIOUTR LEFT JOIN GLPATR Reqname ON Reqname.GL14PATR = CI04REQUESTOR LEFT JOIN GLPATR lendlib ON lendlib.GL14PATR = CI04LENDINGLIB WHERE CI04REQUEST = '" + vsRequestNo + "'";
        }
        System.out.println("query CheckOutgoingRequest : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> titleisbd = ISBD.getPunctuation("245");
                List<ISBD> callnoisbd = ISBD.getPunctuation("090");
                while (rs.next()) {
                    ReceiveILL loadtabledetail = new ReceiveILL(Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), callnoisbd), Handler.ifIsNull(rs.getString("CI04MATNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), titleisbd), Handler.ifIsNull(rs.getString("REQNAME")), Handler.ifIsNull(rs.getString("CI04REQUESTOR")), Handler.ifIsNull(rs.getString("LIBLEND")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTREQUESTED"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTEXPECTED"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTRECEIVED"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTDUED"))), Handler.ifIsNull(rs.getString("CI04COPIES")));
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

    public static int illlength() {
        String sSQLStmt = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC  = 'ACCNOLEN'";
        int getCount = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getCount = rs.getInt("GL99VALUE");
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
        return getCount;
    }

    public static String illaccval() {
        String sSQLStmt = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC  = 'ILLACCNO'";
        String value = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = rs.getString("GL99VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static boolean SaveRecords(String sAccessionNo, String ctrlno, String sLocation, String sIcat, String sCondition, String sSMD, String msRequestor, String gsUserID) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT03DOCNO", sAccessionNo);
        valueStr.put("CT03MATNO", ctrlno);
        valueStr.put("CT03LOCA", sLocation);
        valueStr.put("CT03ICAT", sIcat);
        valueStr.put("CT03COND", sCondition);
        valueStr.put("CT03SMD", sSMD);
        valueStr.put("CT03STAT", "A");
        valueStr.put("CT03PATR", msRequestor);
        valueInt.put("CT03CLMHITS", 0);
        valueInt.put("CT03CIRHITS", 0);
        valueStr.put("CT03LASTACT", DateTime.getTodaySystemDate());
        valueStr.put("CT03CRDATE", DateTime.getTodaySystemDate());
        valueStr.put("CT03CREBY", gsUserID);
        String query = QueryBuilder.createInsertQuery("CTDOCM", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean UpdateILREQC(String vsReferenceNumber) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("IL01REQST0", vsReferenceNumber);
        valueStr.put("IL01STATUS", "03");
        String query = QueryBuilder.createUpdateQuery("ILREQC", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean UpdateCIOUTR(int total, String recDate, String dueDate, String newAccno, String refno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI04REQUEST", refno);
        valueInt.put("CI04COPIES", total);
        valueStr.put("CI04RECFLAG", "P");
        valueStr.put("CI04DTRECEIVED", recDate);
        valueStr.put("CI04DTDUED", dueDate);
        valueStr.put("CI04RECACC", newAccno);
        String query = QueryBuilder.createUpdateQuery("CIOUTR", valueStr, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
