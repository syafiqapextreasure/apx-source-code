/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.IncomeDetail;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncomeDetail {
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

    public IncomeDetail(String trxNo, String trantypeCode, String trantypeDesc, String txnDate, String amt, String memberID, String memberName, String ref, String updref, String accno, String officer, String officerName, String paymode, String branch, String branchdesc) {
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
    }

    public static List<IncomeDetail> GetSQLStmt(String txtFrom, String txtTo, String patrCate, String brancodeValue, String patrCateValue, String chkOfficer, String officer, String chkBranch, String branchVal, String chkPaymentMode, String paymodeVal, String chkCharge, String chargeVal, String chkPayment, String payVal, String chkOverride, String overrideVal, String chkOthers, String otherVal) {
        ArrayList<IncomeDetail> list = new ArrayList<IncomeDetail>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String sSQLStmt = "";
        String sSQLStmtCode = "";
        String sSQLStmtDate = "";
        String sSQLStmtCate = "";
        String sSQLStmtPaymode = "";
        String sSQLStmtCode2 = "";
        String sSQLStmtCode3 = "";
        String sSQLStmtFinal = "";
        String getCode = null;
        if (chkCharge.equals("Y")) {
            getCode = chargeVal;
        }
        if (chkPayment.equals("Y")) {
            getCode = payVal;
        }
        if (chkOverride.equals("Y")) {
            getCode = overrideVal;
        }
        if (chkOthers.equals("Y")) {
            getCode = otherVal;
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
        if (chkPaymentMode.equals("Y")) {
            sSQLStmtPaymode = "AND RE01PAYMODE IN (" + paymodeVal + ") ";
        }
        sSQLStmt = "SELECT RE01TXNO, RE01CODE, GL38DESC, RE01DATE, RE01AMT, RE01PATR, T3.GL14NAME AS gl14name, RE01REFER, RE01UPDREF, RE01DOCNO, \r\nRE01OFFID, T4.GL14NAME AS ggl14name, RE01PAYMODE, GL71BRNC, GL71DESC";
        ArrayList<String> join1 = new ArrayList<String>();
        ArrayList<String> join2 = new ArrayList<String>();
        ArrayList<String> join3 = new ArrayList<String>();
        ArrayList join4 = new ArrayList();
        String[] codeSplit = getCode.split(",");
        int i = 0;
        while (i < codeSplit.length) {
            String maCodeBranch = IncomeDetail.IsKeyExist(codeSplit[i]);
            if (maCodeBranch.equals("P")) {
                join1.add(codeSplit[i]);
            } else if (maCodeBranch.equals("O")) {
                join2.add(codeSplit[i]);
            } else {
                join3.add(codeSplit[i]);
            }
            ++i;
        }
        System.out.println("ArrayList after Update 111: " + join1);
        System.out.println("ArrayList after Update 222: " + join2);
        System.out.println("ArrayList after Update 333: " + join3);
        String sjoin1 = join1.toString();
        sjoin1 = sjoin1.replaceAll("[\\[\\]]", "");
        String sjoin2 = join2.toString();
        sjoin2 = sjoin2.replaceAll("[\\[\\]]", "");
        String sjoin3 = join3.toString();
        sjoin3 = sjoin3.replaceAll("[\\[\\]]", "");
        System.out.println("String after Update 111: " + sjoin1.length());
        System.out.println("String after Update 222: " + sjoin2.length());
        System.out.println("String after Update 333: " + sjoin3.length());
        System.out.println("brancodeValue " + brancodeValue);
        if (sjoin1.length() > 0) {
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 " + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin1 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        if (sjoin2.length() > 0) {
            System.out.println("masuk x ? ");
            sSQLStmtCode2 = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 \r\n" + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            System.out.println("sSQLStmtCode2 " + sSQLStmtCode2);
            sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + sSQLStmtDate;
            sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + "AND RE01CODE IN (" + sjoin2 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode2 = String.valueOf(sSQLStmtCode2) + sSQLStmtPaymode;
        }
        if (sjoin3.length() > 0) {
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 \r\n" + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin3 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        if (sjoin1.length() > 0 && sjoin2.length() > 0) {
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 \r\n" + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin1 + "," + sjoin2 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        if (sjoin1.length() > 0 && sjoin3.length() > 0) {
            System.out.println("are you here join 1 and 3?");
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 \r\n" + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin1 + "," + sjoin3 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        if (sjoin2.length() > 0 && sjoin3.length() > 0) {
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 \r\n" + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin2 + "," + sjoin3 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        if (sjoin1.length() > 0 && sjoin2.length() > 0 && sjoin3.length() > 0) {
            sSQLStmtCode = String.valueOf(sSQLStmt) + " FROM RETRXN T1, GLTRXC, GLPATR T3, GLBRNC, GLPATR T4 " + "WHERE GL38TXCD = RE01CODE  \r\n" + "AND RE01AMT > 0 \r\n" + "AND T4.GL14PATR = T1.RE01OFFID \r\n" + "AND T3.GL14PATR = T1.RE01PATR ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND  " + brancodeValue + " = GL71BRNC ";
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtDate;
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND RE01CODE IN (" + sjoin1 + "," + sjoin2 + "," + sjoin3 + ")\r\n";
            if (chkBranch.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND " + brancodeValue + " IN (" + branchVal + ")\r\n";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtCate;
            if (chkOfficer.equals("Y")) {
                sSQLStmtCode = String.valueOf(sSQLStmtCode) + "AND UPPER(RE01OFFID) = UPPER('" + officer + "') ";
            }
            sSQLStmtCode = String.valueOf(sSQLStmtCode) + sSQLStmtPaymode;
        }
        System.out.println("FINALeee : " + sSQLStmtCode);
        if (sSQLStmtCode != "" && sSQLStmtCode2 != "" && sSQLStmtCode3 != "") {
            System.out.println("1111111111111111111");
            sSQLStmt = String.valueOf(sSQLStmtCode) + " ORDER BY GL71BRNC, RE01CODE, RE01TXNO";
        } else if (sSQLStmtCode != "" && sSQLStmtCode2 != "") {
            System.out.println("22222222222222");
            sSQLStmt = String.valueOf(sSQLStmtCode) + " ORDER BY GL71BRNC, RE01CODE, RE01TXNO";
        } else if (sSQLStmtCode != "" && sSQLStmtCode3 != "") {
            sSQLStmt = String.valueOf(sSQLStmtCode) + "ORDER BY GL71BRNC, RE01CODE, RE01TXNO";
        } else if (sSQLStmtCode2 != "" && sSQLStmtCode3 != "") {
            sSQLStmt = String.valueOf(sSQLStmtCode) + " ORDER BY GL71BRNC, RE01CODE, RE01TXNO";
        } else {
            System.out.println("33333");
            sSQLStmt = String.valueOf(sSQLStmtCode) + sSQLStmtCode2 + sSQLStmtCode3 + " ORDER BY RE01CODE, RE01TXNO";
        }
        System.out.println("FINAL : " + sSQLStmt);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    IncomeDetail loadtabledetail = new IncomeDetail(Handler.ifIsNull(rs.getString("RE01TXNO")), Handler.ifIsNull(rs.getString("RE01CODE")), Handler.ifIsNull(rs.getString("GL38DESC")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("RE01DATE"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("RE01AMT"))), Handler.ifIsNull(rs.getString("RE01PATR")), Handler.ifIsNull(rs.getString("gl14name")), Handler.ifIsNull(rs.getString("RE01REFER")), Handler.ifIsNull(rs.getString("RE01UPDREF")), Handler.ifIsNull(rs.getString("RE01DOCNO")), Handler.ifIsNull(rs.getString("RE01OFFID")), Handler.ifIsNull(rs.getString("ggl14name")), Handler.ifIsNull(rs.getString("RE01PAYMODE")), String.valueOf(Handler.ifIsNull(rs.getString("GL71BRNC"))) + " " + Handler.ifIsNull(rs.getString("GL71DESC")), Handler.ifIsNull(rs.getString("GL71DESC")));
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
