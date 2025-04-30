/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.RequestAuthorisation;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
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

public class RequestAuthorisation {
    private String reqno;
    private String titleAuthor;
    private String isbnissn;
    private String requestorID;
    private String requestorName;
    private String reqDate;

    public String getreqno() {
        return Handler.ifIsNull(this.reqno);
    }

    public String gettitleAuthor() {
        return Handler.ifIsNull(this.titleAuthor);
    }

    public String getisbnissn() {
        return Handler.ifIsNull(this.isbnissn);
    }

    public String getrequestorID() {
        return Handler.ifIsNull(this.requestorID);
    }

    public String getrequestorName() {
        return Handler.ifIsNull(this.requestorName);
    }

    public String getreqDate() {
        return Handler.ifIsNull(this.reqDate);
    }

    public RequestAuthorisation(String reqno, String titleAuthor, String isbnissn, String requestorID, String requestorName, String reqDate) {
        this.reqno = reqno;
        this.titleAuthor = titleAuthor;
        this.isbnissn = isbnissn;
        this.requestorID = requestorID;
        this.requestorName = requestorName;
        this.reqDate = reqDate;
    }

    public static List<RequestAuthorisation> LoadRecordset(String requestor, String libID, String inputStartDate, String inputEndDate, String isreqidCheck, String islibidCheck, String isdateCheck) {
        ArrayList<RequestAuthorisation> list = new ArrayList<RequestAuthorisation>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT IL01REQCID, IL01TITLE0, IL01AUTHOR, IL01ISBNSN, IL01REQTER, GL14NAME, IL01CRTNDT FROM ILREQC LEFT JOIN GLPATR ON GL14PATR = IL01REQTER WHERE IL01REQCID IS NOT NULL AND IL01STATUS = '00' ";
        if (isreqidCheck.equals("Y")) {
            query = String.valueOf(query) + "AND UPPER(IL01REQTER) = UPPER('" + requestor + "') ";
        }
        if (islibidCheck.equals("Y")) {
            query = String.valueOf(query) + "AND UPPER(IL01LNDLIB) = UPPER('" + requestor + "') ";
        }
        if (isdateCheck.equals("Y")) {
            if (inputStartDate != "" && inputEndDate != "") {
                query = String.valueOf(query) + "AND IL01CRTNDT BETWEEN '" + inputStartDate + "' AND '" + inputEndDate + "' ";
            }
            if (inputStartDate != "" && inputEndDate == "") {
                query = String.valueOf(query) + "AND IL01CRTNDT >= '" + inputStartDate + "' ";
            }
            if (inputStartDate == "" && inputEndDate != "") {
                query = String.valueOf(query) + "AND IL01CRTNDT <= '" + inputEndDate + "' ";
            }
        }
        query = String.valueOf(query) + "ORDER BY IL01REQCID ";
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sTitle = Handler.ifIsNull(rs.getString("IL01TITLE0"));
                    String sAuthor = Handler.ifIsNull(rs.getString("IL01AUTHOR"));
                    if (sTitle.compareTo("") > 1 && sAuthor.compareTo("") > 1) {
                        sTitle = String.valueOf(sTitle) + " / " + sAuthor;
                    }
                    sTitle = sTitle.compareTo("") > 1 ? String.valueOf(sTitle) : "";
                    RequestAuthorisation loadtabledetail = new RequestAuthorisation(Handler.ifIsNull(rs.getString("IL01REQCID")), sTitle, Handler.ifIsNull(rs.getString("IL01ISBNSN")), Handler.ifIsNull(rs.getString("IL01REQTER")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("IL01CRTNDT"))));
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

    public static int getVRSION(String reqNo) {
        String sSQLStmt = "SELECT IL01VRSION FROM ILREQC WHERE IL01REQCID = '" + reqNo + "'";
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
                    getCount = rs.getInt("IL01VRSION");
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

    public static boolean RejectRequest(String gsUserId, String refno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        int version = RequestAuthorisation.getVRSION(refno);
        condition.put("IL01REQCID", refno);
        valueStr.put("IL01OFFIID", gsUserId);
        valueStr.put("IL01DTPROC", DateTime.getTodaySystemDate());
        valueStr.put("IL01STATUS", "01");
        valueInt.put("IL01VRSION", version + 1);
        String query = QueryBuilder.createUpdateQuery("ILREQC", valueStr, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean AddRequest(String vsRequestorID, String vsLibraryID, String vsRequestedDate, String vsExpectedDate, String vsTitle, String vsDocumentType, String vsVolume, String vsIssue, String vsPageNumber, String outreqnoConvert, String gsUserId) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("CI04REQUEST", outreqnoConvert);
        valueStr.put("CI04REQUESTOR", vsRequestorID);
        valueStr.put("CI04LENDINGLIB", vsLibraryID);
        valueStr.put("CI04DTREQUESTED", vsRequestedDate);
        valueStr.put("CI04DTEXPECTED", vsExpectedDate);
        valueStr.put("CI04CRDATE", DateTime.getTodaySystemDate());
        valueStr.put("CI04CREBY", gsUserId);
        valueStr.put("CI04TITLE", vsTitle);
        valueStr.put("CI04DOTYPE", vsDocumentType);
        valueStr.put("CI04VOLUME", vsVolume);
        valueStr.put("CI04ISSUES", vsIssue);
        valueStr.put("CI04PAGENO", vsPageNumber);
        String query = QueryBuilder.createInsertQuery("CIOUTR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean ApproveRequest(String gsUserId, String refno, String outreqnoConvert) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        int version = RequestAuthorisation.getVRSION(refno);
        condition.put("IL01REQCID", refno);
        valueStr.put("IL01REQST0", outreqnoConvert);
        valueStr.put("IL01OFFIID", gsUserId);
        valueStr.put("IL01DTPROC", DateTime.getTodaySystemDate());
        valueStr.put("IL01STATUS", "02");
        valueInt.put("IL01VRSION", version + 1);
        String query = QueryBuilder.createUpdateQuery("ILREQC", valueStr, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
