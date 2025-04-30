/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.ReqCtrl;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
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

public class ReqCtrl {
    private String requestNo;
    private String requestor;
    private String isbn;
    private String title;
    private String dateReq;
    private String status;
    private String sRequestNo;
    private String sLendingLibraryId;
    private String sDocumentType;
    private String sDeliveryType;
    private String sISBN;
    private String sAuthor;
    private String sPlaceOfPublication;
    private String sPublisher;
    private String sYearOfPublication;
    private String sEdition;
    private String sRequestorRemark;
    private String sVolume;
    private String sIssue;
    private String sPageNumber;
    private String sDateExpected;

    public String getrequestNo() {
        return Handler.ifIsNull(this.requestNo);
    }

    public String getrequestor() {
        return Handler.ifIsNull(this.requestor);
    }

    public String getisbn() {
        return Handler.ifIsNull(this.isbn);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getdateReq() {
        return Handler.ifIsNull(this.dateReq);
    }

    public String getstatus() {
        return Handler.ifIsNull(this.status);
    }

    public String getsRequestNo() {
        return Handler.ifIsNull(this.sRequestNo);
    }

    public String getsLendingLibraryId() {
        return Handler.ifIsNull(this.sLendingLibraryId);
    }

    public String getsDocumentType() {
        return Handler.ifIsNull(this.sDocumentType);
    }

    public String getsDeliveryType() {
        return Handler.ifIsNull(this.sDeliveryType);
    }

    public String getsISBN() {
        return Handler.ifIsNull(this.sISBN);
    }

    public String getsAuthor() {
        return Handler.ifIsNull(this.sAuthor);
    }

    public String getsPlaceOfPublication() {
        return Handler.ifIsNull(this.sPlaceOfPublication);
    }

    public String getsPublisher() {
        return Handler.ifIsNull(this.sPublisher);
    }

    public String getsYearOfPublication() {
        return Handler.ifIsNull(this.sYearOfPublication);
    }

    public String getsEdition() {
        return Handler.ifIsNull(this.sEdition);
    }

    public String getsRequestorRemark() {
        return Handler.ifIsNull(this.sRequestorRemark);
    }

    public String getsVolume() {
        return Handler.ifIsNull(this.sVolume);
    }

    public String getsIssue() {
        return Handler.ifIsNull(this.sIssue);
    }

    public String getsPageNumber() {
        return Handler.ifIsNull(this.sPageNumber);
    }

    public String getssDateExpected() {
        return Handler.ifIsNull(this.sDateExpected);
    }

    public ReqCtrl(String requestNo, String requestor, String isbn, String title, String dateReq, String status) {
        this.requestNo = requestNo;
        this.requestor = requestor;
        this.isbn = isbn;
        this.title = title;
        this.dateReq = dateReq;
        this.status = status;
    }

    public ReqCtrl(String sRequestNo, String status, String dateReq, String requestor, String sLendingLibraryId, String sDocumentType, String sDeliveryType, String sDateExpected, String title, String sISBN, String sAuthor, String sPlaceOfPublication, String sPublisher, String sYearOfPublication, String sEdition, String sRequestorRemark, String sVolume, String sIssue, String sPageNumber) {
        this.sRequestNo = sRequestNo;
        this.status = status;
        this.dateReq = dateReq;
        this.requestor = requestor;
        this.sLendingLibraryId = sLendingLibraryId;
        this.sDocumentType = sDocumentType;
        this.sDeliveryType = sDeliveryType;
        this.sDateExpected = sDateExpected;
        this.title = title;
        this.sISBN = sISBN;
        this.sAuthor = sAuthor;
        this.sPlaceOfPublication = sPlaceOfPublication;
        this.sPublisher = sPublisher;
        this.sYearOfPublication = sYearOfPublication;
        this.sEdition = sEdition;
        this.sRequestorRemark = sRequestorRemark;
        this.sVolume = sVolume;
        this.sIssue = sIssue;
        this.sPageNumber = sPageNumber;
    }

    public static List<ReqCtrl> search(String input_criteria, String search_type, String inputDate, String endinputDate) {
        ArrayList<ReqCtrl> list = new ArrayList<ReqCtrl>();
        String query = "SELECT IL01REQCID, GL14NAME, IL01ISBNSN, IL01TITLE0, IL01CRTNDT, IL01STATUS FROM ILREQC LEFT JOIN GLPATR ON GL14PATR = IL01REQTER WHERE ";
        if (search_type.equals("reqno")) {
            query = String.valueOf(query) + "UPPER(IL01REQCID) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("title")) {
            query = String.valueOf(query) + "UPPER(IL01TITLE0) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("reqID")) {
            query = String.valueOf(query) + "UPPER(IL01REQTER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("dateReq")) {
            if (inputDate != "" && endinputDate != "") {
                query = String.valueOf(query) + "IL01CRTNDT BETWEEN '" + inputDate + "' AND '" + endinputDate + "' ";
            }
            if (inputDate != "" && endinputDate == "") {
                query = String.valueOf(query) + "AND IL01CRTNDT >= '" + endinputDate + "' ";
            }
            if (inputDate == "" && endinputDate != "") {
                query = String.valueOf(query) + "AND IL01CRTNDT <= '" + endinputDate + "' ";
            }
        } else if (search_type.equals("llID")) {
            query = String.valueOf(query) + "UPPER(IL01LNDLIB) = UPPER('" + input_criteria + "')";
        }
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReqCtrl loadtabledetail = new ReqCtrl(Handler.ifIsNull(rs.getString("IL01REQCID")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("IL01ISBNSN")), Handler.ifIsNull(rs.getString("IL01TITLE0")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("IL01CRTNDT"))), Handler.ifIsNull(rs.getString("IL01STATUS")));
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

    public static boolean AddNewRecord(String reqDate, String reqID, String lendLib, String doctype, String dexpect, String predevtype, String title, String issn, String author, String placePub, String pub, String yearPub, String edition, String remarks, String vol, String iss, String pageno, String gsUserId, String outgoingConvert) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("IL01REQCID", outgoingConvert);
        valueStr.put("IL01CRTNDT", reqDate);
        valueStr.put("IL01CRTNBY", gsUserId);
        valueStr.put("IL01LMODDT", DateTime.getTodaySystemDate());
        valueStr.put("IL01LMODBY", gsUserId);
        valueStr.put("IL01REQTER", reqID);
        valueStr.put("IL01LNDLIB", lendLib);
        valueStr.put("IL01DOTYPE", doctype);
        valueStr.put("IL01DBFORE", dexpect);
        valueStr.put("IL01DELIVY", predevtype);
        valueStr.put("IL01TITLE0", title.replace("'", "''"));
        valueStr.put("IL01ISBNSN", issn);
        valueStr.put("IL01AUTHOR", author);
        valueStr.put("IL01PUBPLC", placePub);
        valueStr.put("IL01PUBLER", pub);
        valueStr.put("IL01PUBLYR", yearPub);
        valueStr.put("IL01EDTION", edition);
        valueStr.put("IL01REMARK", remarks);
        valueStr.put("IL01VOLUME", vol);
        valueStr.put("IL01ISSUES", iss);
        valueStr.put("IL01PAGENO", pageno);
        valueStr.put("IL01STATUS", "00");
        valueInt.put("IL01VRSION", 0);
        String query = QueryBuilder.createInsertQuery("ILREQC", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static List<ReqCtrl> editView(String refno) {
        ArrayList<ReqCtrl> list = new ArrayList<ReqCtrl>();
        String query = "SELECT IL01REQCID, IL01STATUS, IL01CRTNDT, IL01REQTER, IL01LNDLIB, IL01DOTYPE, IL01DELIVY, IL01DBFORE, IL01TITLE0, IL01ISBNSN, IL01AUTHOR, IL01PUBPLC, IL01PUBLER, IL01PUBLYR, IL01EDTION, IL01REMARK, IL01VOLUME, IL01ISSUES, IL01PAGENO FROM ILREQC WHERE IL01REQCID = '" + refno + "'";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReqCtrl loadtabledetail = new ReqCtrl(Handler.ifIsNull(rs.getString("IL01REQCID")), Handler.ifIsNull(rs.getString("IL01STATUS")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("IL01CRTNDT"))), Handler.ifIsNull(rs.getString("IL01REQTER")), Handler.ifIsNull(rs.getString("IL01LNDLIB")), Handler.ifIsNull(rs.getString("IL01DOTYPE")), Handler.ifIsNull(rs.getString("IL01DELIVY")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("IL01DBFORE"))), Handler.ifIsNull(rs.getString("IL01TITLE0")), Handler.ifIsNull(rs.getString("IL01ISBNSN")), Handler.ifIsNull(rs.getString("IL01AUTHOR")), Handler.ifIsNull(rs.getString("IL01PUBPLC")), Handler.ifIsNull(rs.getString("IL01PUBLER")), Handler.ifIsNull(rs.getString("IL01PUBLYR")), Handler.ifIsNull(rs.getString("IL01EDTION")), Handler.ifIsNull(rs.getString("IL01REMARK")), Handler.ifIsNull(rs.getString("IL01VOLUME")), Handler.ifIsNull(rs.getString("IL01ISSUES")), Handler.ifIsNull(rs.getString("IL01PAGENO")));
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

    public static boolean EditCurrentRecord(String sRequestNo, String gsUserId, String reqID, String lendLib, String doctype, String dexpect, String predevtype, String txtTitle, String issn, String author, String placePub, String pub, String yearPub, String edition, String remarks, String vol, String iss, String pageno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("IL01REQCID", sRequestNo);
        valueStr.put("IL01LMODDT", DateTime.getTodaySystemDate());
        valueStr.put("IL01LMODBY", gsUserId);
        valueStr.put("IL01REQTER", reqID);
        valueStr.put("IL01LNDLIB", lendLib);
        valueStr.put("IL01DOTYPE", doctype);
        valueStr.put("IL01DBFORE", dexpect);
        valueStr.put("IL01DELIVY", predevtype);
        valueStr.put("IL01TITLE0", txtTitle);
        valueStr.put("IL01ISBNSN", issn);
        valueStr.put("IL01AUTHOR", author);
        valueStr.put("IL01PUBPLC", placePub);
        valueStr.put("IL01PUBLER", pub);
        valueStr.put("IL01PUBLYR", yearPub);
        valueStr.put("IL01EDTION", edition);
        valueStr.put("IL01REMARK", remarks);
        valueStr.put("IL01VOLUME", vol);
        valueStr.put("IL01ISSUES", iss);
        valueStr.put("IL01PAGENO", pageno);
        String query = QueryBuilder.createUpdateQuery("ILREQC", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static void DeleteCurrentRecord(String id) {
        String query = "DELETE FROM ILREQC WHERE IL01REQCID = '" + id + "'";
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
