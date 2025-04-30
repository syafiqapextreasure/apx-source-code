/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.equiry.PatronHistory;

import com.ilmu.global.DateFormatter;
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

public class PatronHistory {
    private String sAccNo;
    private String sBorrowDate;
    private String sBorrowTime;
    private String sDueDate;
    private String sDueTime;
    private String sDateReturned;
    private String sTimeReturned;
    private String sStatus;
    private String sItemStatus;
    private String sRenewal;
    private String sItemCat;
    private String sChargingOfficer;
    private String sDischargingOfficer;
    private String sTitle;
    private String sRenewDate;
    private String sRenewTime;
    private String sRenewOfficer;
    private String sChargedDate;
    private String sChargedTime;

    public String getsAccNo() {
        return this.sAccNo;
    }

    public String getsBorrowDate() {
        return this.sBorrowDate;
    }

    public String getsBorrowTime() {
        return this.sBorrowTime;
    }

    public String getsDueDate() {
        return this.sDueDate;
    }

    public String getsDueTime() {
        return this.sDueTime;
    }

    public String getsDateReturned() {
        return this.sDateReturned;
    }

    public String getsTimeReturned() {
        return this.sTimeReturned;
    }

    public String getsStatus() {
        return this.sStatus;
    }

    public String getsItemStatus() {
        return this.sItemStatus;
    }

    public String getsRenewal() {
        return this.sRenewal;
    }

    public String getsItemCat() {
        return this.sItemCat;
    }

    public String getsChargingOfficer() {
        return this.sChargingOfficer;
    }

    public String getsDischargingOfficer() {
        return this.sDischargingOfficer;
    }

    public String getsTitle() {
        return this.sTitle;
    }

    public String getsRenewDate() {
        return this.sRenewDate;
    }

    public String getsRenewTime() {
        return this.sRenewTime;
    }

    public String getsRenewOfficer() {
        return this.sRenewOfficer;
    }

    public String getsChargedDate() {
        return this.sChargedDate;
    }

    public String getsChargedTime() {
        return this.sChargedTime;
    }

    public PatronHistory(String sAccNo, String sBorrowDate, String sBorrowTime, String sDueDate, String sDueTime, String sDateReturned, String sTimeReturned, String sStatus, String sItemStatus, String sRenewal, String sItemCat, String sChargingOfficer, String sDischargingOfficer, String sTitle, String sRenewDate, String sRenewTime, String sRenewOfficer, String sChargedDate, String sChargedTime) {
        this.sAccNo = sAccNo;
        this.sBorrowDate = sBorrowDate;
        this.sBorrowTime = sBorrowTime;
        this.sDueDate = sDueDate;
        this.sDueTime = sDueTime;
        this.sDateReturned = sDateReturned;
        this.sTimeReturned = sTimeReturned;
        this.sStatus = sStatus;
        this.sItemStatus = sItemStatus;
        this.sRenewal = sRenewal;
        this.sItemCat = sItemCat;
        this.sChargingOfficer = sChargingOfficer;
        this.sDischargingOfficer = sDischargingOfficer;
        this.sTitle = sTitle;
        this.sRenewDate = sRenewDate;
        this.sRenewTime = sRenewTime;
        this.sRenewOfficer = sRenewOfficer;
        this.sChargedDate = sChargedDate;
        this.sChargedTime = sChargedTime;
    }

    public static List<PatronHistory> loadPatr(String id) {
        ArrayList<PatronHistory> list = new ArrayList<PatronHistory>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        DBConnection dbtype = new DBConnection();
        String query = "SELECT CI02DOCNO,CI02PATR,CI02CDATE,CI02CTIME,CI02DDATE, CI02DTIME,CI02DIDATE,CI02DITIME, CASE WHEN CI02FLAG  = 'D' THEN 'Discharged' WHEN CI02FLAG  = 'C' THEN 'Charged' WHEN CI02FLAG  = 'L' THEN 'Lost' ELSE 'Invalid' END AS FLAGDESC, GL36DESC, CI02RENEW, CI02OFFI, CI02DIOFFI, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CI02MATNO AND CT06TAG = '245') AS TITLE, CI02RDATE, CI02RTIME, CI02ROFFID, CT03BDATE,CT03BTIME FROM CICIRC LEFT JOIN CTDOCM ON CT03DOCNO = CI02DOCNO LEFT JOIN GLDOCS ON GL36STAT = CT03STAT WHERE CI02DOCNO = CT03DOCNO AND UPPER(CI02PATR) = UPPER('" + id + "') " + "ORDER BY CI02CDATE";
        System.out.println("query loadPatr : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> title = ISBD.getPunctuation("245");
                String icatedesc = null;
                while (rs.next()) {
                    icatedesc = PatronHistory.GetItemCategoryDesc(Handler.ifIsNull(rs.getString("CI02DOCNO")));
                    if (icatedesc.equals("") || icatedesc.equals(" ")) {
                        icatedesc = " - ";
                    }
                    System.out.println("rrrr" + rs.getString("CI02RTIME"));
                    PatronHistory loadtabledetail = new PatronHistory(Handler.ifIsNull(rs.getString("CI02DOCNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02CDATE"))), Handler.ifIsNull(DateTime.Time(rs.getString("CI02CTIME"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DDATE"))), Handler.ifIsNull(DateTime.Time(rs.getString("CI02DTIME"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DIDATE"))), Handler.ifIsNull(DateTime.Time(rs.getString("CI02DITIME"))), Handler.ifIsNull(rs.getString("FLAGDESC")), Handler.ifIsNull(rs.getString("GL36DESC")), Handler.ifIsNull(rs.getString("CI02RENEW")), icatedesc, Handler.ifIsNull(rs.getString("CI02OFFI")), Handler.ifIsNull(rs.getString("CI02DIOFFI")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02RDATE"))), Handler.ifIsNull(DateTime.Time(rs.getString("CI02RTIME"))), Handler.ifIsNull(rs.getString("CI02ROFFID")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CT03BDATE"))), Handler.ifIsNull(DateTime.Time(rs.getString("CT03BTIME"))));
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

    public static String GetItemCategoryDesc(String docno) throws SQLException {
        String sSQLStmt = "SELECT GL10DESC FROM CTDOCM LEFT JOIN GLICAT ON GL10ICAT = CT03ICAT WHERE CT03DOCNO = '" + docno + "'";
        System.out.println("SQL GetItemCategoryDesc : " + sSQLStmt);
        String title = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    title = Handler.ifIsNull(rs.getString("GL10DESC"));
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
        return title;
    }
}
