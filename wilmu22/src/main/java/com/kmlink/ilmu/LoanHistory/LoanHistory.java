/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.LoanHistory;

import com.kmlink.ilmu.shared.global.DateFormatter;
import com.kmlink.ilmu.shared.global.GetTitle;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoanHistory {
    private String sAccno;
    private String sPatron;
    private String sPatronName;
    private String sBorrow;
    private String sDue;
    private String sReturn;
    private String sChargeOfficer;
    private String sDischargeOfficer;
    private String sTitle;
    private String sControlNumber;

    public String getsAccno() {
        return Handler.ifIsNull(this.sAccno);
    }

    public String getsPatron() {
        return Handler.ifIsNull(this.sPatron);
    }

    public String getsPatronName() {
        return Handler.ifIsNull(this.sPatronName);
    }

    public String getsBorrow() {
        return Handler.ifIsNull(this.sBorrow);
    }

    public String getsDue() {
        return Handler.ifIsNull(this.sDue);
    }

    public String getsReturn() {
        return Handler.ifIsNull(this.sReturn);
    }

    public String getsChargeOfficer() {
        return Handler.ifIsNull(this.sChargeOfficer);
    }

    public String getsDischargeOfficer() {
        return Handler.ifIsNull(this.sDischargeOfficer);
    }

    public String getsTitle() {
        return Handler.ifIsNull(this.sTitle);
    }

    public String getsControlNumber() {
        return Handler.ifIsNull(this.sControlNumber);
    }

    public LoanHistory(String sTitle) {
        this.sTitle = sTitle;
    }

    public LoanHistory(String sTitle, String sControlNumber) {
        this.sTitle = sTitle;
        this.sControlNumber = sControlNumber;
    }

    public LoanHistory(String sAccno, String sPatronName, String sPatron, String sBorrow, String sDue, String sReturn, String sChargeOfficer, String sDischargeOfficer) {
        this.sAccno = sAccno;
        this.sPatronName = sPatronName;
        this.sPatron = sPatron;
        this.sBorrow = sBorrow;
        this.sDue = sDue;
        this.sReturn = sReturn;
        this.sChargeOfficer = sChargeOfficer;
        this.sDischargeOfficer = sDischargeOfficer;
    }

    public static List<LoanHistory> GetAccessionTitle(String searchInput, String type) {
        ArrayList<LoanHistory> list = new ArrayList<LoanHistory>();
        String getTitle = null;
        String sControlNo = null;
        if (type.equals("controlnumber")) {
            getTitle = GetTitle.GetTagValue(searchInput, "245");
            LoanHistory resultTitle = new LoanHistory(getTitle);
            list.add(resultTitle);
        } else if (type.equals("accessionnumber")) {
            sControlNo = LoanHistory.GetControlNumber(searchInput);
            System.out.println("sControlNo" + sControlNo);
            getTitle = GetTitle.GetTagValue(sControlNo, "245");
            LoanHistory resultTitle = new LoanHistory(getTitle, sControlNo);
            list.add(resultTitle);
        }
        return list;
    }

    public static List<LoanHistory> GetSQLStmt(String searchInput, String type) {
        ArrayList<LoanHistory> list = new ArrayList<LoanHistory>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        sSQLStmt = "SELECT CI02DOCNO,GL14NAME,CI02PATR,CI02CDATE,CI02CTIME,CI02DDATE, CI02DTIME,CI02DIDATE,CI02DITIME,CI02OFFI,CI02DIOFFI FROM CICIRC RIGHT JOIN GLPATR ON GL14PATR = CI02PATR ";
        if (type.equals("controlnumber")) {
            sSQLStmt = String.valueOf(sSQLStmt) + "WHERE CI02MATNO = '" + searchInput + "' " + "ORDER BY CI02DOCNO, CI02CDATE";
        } else if (type.equals("accessionnumber")) {
            sSQLStmt = String.valueOf(sSQLStmt) + "WHERE CI02DOCNO = '" + searchInput + "' " + "ORDER BY CI02CDATE DESC";
        }
        System.out.println(sSQLStmt);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    LoanHistory loadtabledetail = new LoanHistory(Handler.ifIsNull(rs.getString("CI02DOCNO")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("CI02PATR")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02CDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DIDATE"))), Handler.ifIsNull(rs.getString("CI02OFFI")), Handler.ifIsNull(rs.getString("CI02DIOFFI")));
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

    public static String GetControlNumber(String searchInput) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO = '" + searchInput + "'";
        System.out.println(sSQLStmt);
        String controlNUmber = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    controlNUmber = Handler.ifIsNull(rs.getString("CT03MATNO"));
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
        return controlNUmber;
    }
}
