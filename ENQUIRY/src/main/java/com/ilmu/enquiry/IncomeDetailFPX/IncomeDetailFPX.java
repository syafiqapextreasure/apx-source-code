/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.IncomeDetailFPX;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncomeDetailFPX {
    private String trxNo;
    private String trantypeCode;
    private String trantypeDesc;
    private String txnDate;
    private String amt;
    private String memberID;
    private String memberName;
    private String ref;
    private String updref;
    private String accno;
    private String officer;
    private String officerName;
    private String paymode;
    private String branch;
    private String branchdesc;
    private String trxnid;
    private String trxnref;
    private String docno;

    public String gettrxNo() {
        return Handler.ifIsNull(this.trxNo);
    }

    public String gettrantypeCode() {
        return Handler.ifIsNull(this.trantypeCode);
    }

    public String gettrantypeDesc() {
        return Handler.ifIsNull(this.trantypeDesc);
    }

    public String gettxnDate() {
        return Handler.ifIsNull(this.txnDate);
    }

    public String getamt() {
        return Handler.ifIsNull(this.amt);
    }

    public String getmemberID() {
        return Handler.ifIsNull(this.memberID);
    }

    public String getmemberName() {
        return Handler.ifIsNull(this.memberName);
    }

    public String getref() {
        return Handler.ifIsNull(this.ref);
    }

    public String getupdref() {
        return Handler.ifIsNull(this.updref);
    }

    public String getaccno() {
        return Handler.ifIsNull(this.accno);
    }

    public String getofficer() {
        return Handler.ifIsNull(this.officer);
    }

    public String getofficerName() {
        return Handler.ifIsNull(this.officerName);
    }

    public String getpaymode() {
        return Handler.ifIsNull(this.paymode);
    }

    public String getbranch() {
        return Handler.ifIsNull(this.branch);
    }

    public String getbranchdesc() {
        return Handler.ifIsNull(this.branchdesc);
    }

    public String gettrxnid() {
        return Handler.ifIsNull(this.trxnid);
    }

    public String gettrxnref() {
        return Handler.ifIsNull(this.trxnref);
    }

    public String getdocno() {
        return Handler.ifIsNull(this.docno);
    }

    public IncomeDetailFPX(String trxNo, String trantypeCode, String trantypeDesc, String txnDate, String amt, String memberID, String memberName, String ref, String updref, String accno, String officer, String officerName, String paymode, String branch, String branchdesc, String trxnid, String trxnref, String docno) {
        this.trxNo = trxNo;
        this.trantypeCode = trantypeCode;
        this.trantypeDesc = trantypeDesc;
        this.txnDate = txnDate;
        this.amt = amt;
        this.memberID = memberID;
        this.memberName = memberName;
        this.ref = ref;
        this.updref = updref;
        this.accno = accno;
        this.officer = officer;
        this.officerName = officerName;
        this.paymode = paymode;
        this.branch = branch;
        this.branchdesc = branchdesc;
        this.trxnid = trxnid;
        this.trxnref = trxnref;
        this.docno = docno;
    }

    public static List<IncomeDetailFPX> GetSQLStmt(String txtFrom, String txtTo, String patrCate, String brancodeValue, String patrCateValue, String chkBranch, String branchVal, String chkPayment, String payVal) {
        ArrayList<IncomeDetailFPX> list = new ArrayList<IncomeDetailFPX>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String sSQLStmt = "";
        String sSQLStmtDate = "";
        String sSQLStmtCate = "";
        String sSQLStmtBranch = "";
        String getCode = null;
        if (chkPayment.equals("Y")) {
            getCode = payVal;
        }
        System.out.println("getCode" + getCode);
        if (txtFrom != "" && txtTo != "") {
            System.out.println("inputStartDate and inputEndDate");
            sSQLStmtDate = String.valueOf(sSQLStmtDate) + "AND RE01DATE BETWEEN '" + txtFrom + "' AND '" + txtTo + "' ";
        }
        if (txtFrom != "" && txtTo == "") {
            System.out.println("inputStartDate");
            sSQLStmtDate = String.valueOf(sSQLStmtDate) + "AND RE01DATE >= '" + txtFrom + "' ";
        }
        if (txtFrom == "" && txtTo != "") {
            System.out.println("inputEndDate");
            sSQLStmtDate = String.valueOf(sSQLStmtDate) + "AND RE01DATE <= '" + txtTo + "' ";
        }
        if (patrCate.equals("Y")) {
            sSQLStmtCate = "AND T3.GL14CATE IN (" + patrCateValue + ") ";
        }
        if (chkBranch.equals("Y")) {
            sSQLStmtBranch = "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
        }
        sSQLStmt = "SELECT RE01TXNO, RE01CODE, GL38DESC, RE01DATE, RE01AMT, RE01PATR, T3.GL14NAME AS gl14name, RE01REFER, RE01UPDREF, RE01DOCNO, \r\nRE01OFFID,  RE01PAYMODE, GL71BRNC, GL71DESC, RE02TXNID, RE02TXNREFERENCE, RE02ORDERNO\r\nFROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, REBILL\r\nWHERE GL38TXCD = RE01CODE  \r\nAND RE01AMT > 0 \r\nAND T3.GL14PATR = T1.RE01PATR \r\nAND RE02BILLNO = RE01BILLNO\r\nAND  T3.GL14BRNC = GL71BRNC ";
        sSQLStmt = String.valueOf(sSQLStmt) + sSQLStmtDate;
        sSQLStmt = String.valueOf(sSQLStmt) + "AND RE01CODE LIKE '%1' ";
        sSQLStmt = String.valueOf(sSQLStmt) + sSQLStmtBranch;
        sSQLStmt = String.valueOf(sSQLStmt) + sSQLStmtCate;
        sSQLStmt = String.valueOf(sSQLStmt) + "AND RE01PAYMODE = 'FPX'  \r\n" + "ORDER BY GL71BRNC, RE01CODE, RE01TXNO";
        System.out.println("FINAL : " + sSQLStmt);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    IncomeDetailFPX loadtabledetail = new IncomeDetailFPX(Handler.ifIsNull(rs.getString("RE01TXNO")), Handler.ifIsNull(rs.getString("RE01CODE")), Handler.ifIsNull(rs.getString("GL38DESC")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("RE01DATE"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("RE01AMT"))), Handler.ifIsNull(rs.getString("RE01PATR")), Handler.ifIsNull(rs.getString("gl14name")), Handler.ifIsNull(rs.getString("RE01REFER")), Handler.ifIsNull(rs.getString("RE01UPDREF")), Handler.ifIsNull(rs.getString("RE01DOCNO")), Handler.ifIsNull(rs.getString("RE01OFFID")), Handler.ifIsNull(rs.getString("gl14name")), Handler.ifIsNull(rs.getString("RE01PAYMODE")), String.valueOf(Handler.ifIsNull(rs.getString("GL71BRNC"))) + " " + Handler.ifIsNull(rs.getString("GL71DESC")), Handler.ifIsNull(rs.getString("GL71DESC")), Handler.ifIsNull(rs.getString("RE02TXNID")), Handler.ifIsNull(rs.getString("RE02TXNREFERENCE")), Handler.ifIsNull(rs.getString("RE02ORDERNO")));
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

    public static String IsKeyExist(String code) {
        String incbrnc;
        block12: {
            String sSQLStmt = "";
            sSQLStmt = "SELECT GL38INCBRNC FROM GLTRXC WHERE GL38TXCD = " + code;
            System.out.println("SQL IsKeyExist" + sSQLStmt);
            incbrnc = null;
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    System.out.println("rs : " + rs);
                    if (!rs.next()) {
                        incbrnc = "";
                        System.out.println("incbrnc = 'empty'");
                        break block12;
                    }
                    incbrnc = Handler.ifIsNull(rs.getString("GL38INCBRNC"));
                    System.out.println("incbrnc " + incbrnc);
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
        return incbrnc;
    }
}
