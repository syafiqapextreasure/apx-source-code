/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.returnIll;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.global.globalmethod;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class returnILL {
    private String sReferenceNo;
    private String sControlNo;
    private String sTitle;
    private String iCopies;
    private String sRequesteddate;
    private String sExpectedDate;
    private String sReceivedDate;

    public String getsReferenceNo() {
        return Handler.ifIsNull(this.sReferenceNo);
    }

    public String getsControlNo() {
        return Handler.ifIsNull(this.sControlNo);
    }

    public String getsTitle() {
        return Handler.ifIsNull(this.sTitle);
    }

    public String getiCopies() {
        return Handler.ifIsNull(this.iCopies);
    }

    public String getsRequesteddate() {
        return Handler.ifIsNull(this.sRequesteddate);
    }

    public String getsExpectedDate() {
        return Handler.ifIsNull(this.sExpectedDate);
    }

    public String getsReceivedDate() {
        return Handler.ifIsNull(this.sReceivedDate);
    }

    public returnILL(String sReferenceNo, String sControlNo, String sTitle, String iCopies, String sRequesteddate, String sExpectedDate, String sReceivedDate) {
        this.sReferenceNo = sReferenceNo;
        this.sControlNo = sControlNo;
        this.sTitle = sTitle;
        this.iCopies = iCopies;
        this.sRequesteddate = sRequesteddate;
        this.sExpectedDate = sExpectedDate;
        this.sReceivedDate = sReceivedDate;
    }

    public static List<returnILL> LoadOutgoingRecord(String lendinglib) {
        ArrayList<returnILL> list = new ArrayList<returnILL>();
        String query = "SELECT CI04REQUEST,CI04MATNO,CI04LENDINGLIB,CI04CONTACT, CI04DTREQUESTED,CI04DTEXPECTED,CI04DTRECEIVED,CI04COPIES FROM CIOUTR WHERE UPPER(CI04LENDINGLIB) = UPPER('" + lendinglib + "') " + "AND CI04DTRETURNED IS NULL " + "AND CI04DTRECEIVED IS NOT NULL " + "ORDER BY CI04REQUEST";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String sReferenceNo = null;
                String sControlNo = null;
                int iCopiesAvailable = 0;
                int iCopies = 0;
                String sTitle = null;
                String sRequesteddate = null;
                String sExpectedDate = null;
                String sReceivedDate = null;
                while (rs.next()) {
                    sReferenceNo = Handler.ifIsNull(rs.getString("CI04REQUEST"));
                    sControlNo = Handler.ifIsNull(rs.getString("CI04MATNO"));
                    iCopiesAvailable = returnILL.CopiesAvailable(sControlNo, sReferenceNo);
                    if (iCopiesAvailable < (iCopies = Integer.parseInt(Handler.ifIsNull(rs.getString("CI04COPIES"))))) continue;
                    sTitle = globalmethod.GetTagValue(sControlNo, "245", "No");
                    sRequesteddate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTREQUESTED")));
                    sExpectedDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTEXPECTED")));
                    sReceivedDate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI04DTRECEIVED")));
                    returnILL newGetAllBy = new returnILL(sReferenceNo, sControlNo, sTitle, String.valueOf(iCopies), sRequesteddate, sExpectedDate, sReceivedDate);
                    list.add(newGetAllBy);
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

    static int CopiesAvailable(String sControlNo, String sReferenceNo) throws SQLException {
        int CopiesAvailable;
        block13: {
            CopiesAvailable = 0;
            String ConstructInStatementForAccessionNo = returnILL.ConstructInStatementForAccessionNo(sReferenceNo);
            String sSQLStmt = "SELECT COUNT(*) AS TOTAL FROM CTDOCM WHERE CT03MATNO = '" + sControlNo + "' " + "AND CT03DOCNO IN (" + ConstructInStatementForAccessionNo + ") " + "AND CT03STAT = 'A'";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        CopiesAvailable = 0;
                        break block13;
                    }
                    int total = Integer.parseInt(Handler.ifIsNull(rs.getString("TOTAL")));
                    if (total <= 0) {
                        CopiesAvailable = 0;
                        break block13;
                    }
                    CopiesAvailable = total;
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
        return CopiesAvailable;
    }

    public static final String ConstructInStatementForAccessionNo(String vsReferenceNo) throws SQLException {
        String tempConstructInStatementForAccessionNo;
        block13: {
            tempConstructInStatementForAccessionNo = null;
            String sSQLStmt = null;
            String sAccessions = null;
            sSQLStmt = "SELECT CI04RECACC FROM CIOUTR WHERE CI04REQUEST = '" + vsReferenceNo + "'";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        tempConstructInStatementForAccessionNo = "''";
                        break block13;
                    }
                    sAccessions = Handler.ifIsNull(rs.getString("CI04RECACC"));
                    if (sAccessions.compareTo("") > 0 && sAccessions.indexOf(";") + 1 > 0) {
                        sAccessions = sAccessions.replaceAll(";", "','");
                        tempConstructInStatementForAccessionNo = "'" + sAccessions + "'";
                        break block13;
                    }
                    tempConstructInStatementForAccessionNo = "'" + sAccessions + "'";
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
        return tempConstructInStatementForAccessionNo;
    }
}
