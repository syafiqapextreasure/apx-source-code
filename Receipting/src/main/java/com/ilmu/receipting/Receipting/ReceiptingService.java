/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.Receipting;

import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReceiptingService {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String RE01TXNO = null;
    private String RE01DATE = null;
    private String RE01CODE = null;
    private String RE01DOCNO = null;
    private String RE01OFFID = null;
    private double ReceiptAmount = 0.0;
    private String RE01REFER = null;
    private String GL05BRNC = null;
    private String CT05SRAW = null;
    private String CT04INDI1 = null;
    private String CT04INDI2 = null;
    private String CT04RAW = null;
    private String GL17TABNAME = null;
    private String GL17DESC = null;
    private String GL23DESC = null;
    private String CT06TAG = null;
    private String CT02MATNO = null;
    private String CT02CREDATE = null;
    private String CT02STATUS = null;
    private String CT02CRETIME = null;
    private String CT02CREBY = null;
    private String CT05SCONV = null;
    private int TPLID = 0;
    private String TPLNAME = null;
    private String TAG = null;
    private String TAGDESC = null;
    private String INDI1 = null;
    private String INDI2 = null;
    private String SUBFIELDS = null;
    private String SUBFDESC = null;
    private String CTTPLSUBF = null;
    private int CT16MSTR = 0;
    private String CT02TYPE = null;
    private String CT02IDXDATE = null;
    private String CT02IDXBY = null;
    private String CT02LEADER = null;
    private int CT06POINTER = 0;
    private String GL17AUTFLAG = null;
    private String GL17AUTHFLAG = null;
    private String GL17CPFLAG = null;
    private String GL17STOP = null;
    private String GL17MANDA = null;
    private String CT02IDXTIME = null;
    private String GL17REPEAT = null;
    private String override = null;
    private boolean overrideExist = false;
    private String patrId;
    private String patrName;
    private String patrCourse;
    private String patrAdd1;
    private String patrAdd2;
    private String patrAdd3;
    private String patrCode;
    private String patrTown;
    private String patrHtel;
    private String paymentMode;

    public ReceiptingService(String RE01TXNO, String RE01DATE, String RE01CODE, String RE01OFFID, String RE01DOCNO, double ReceiptAmount, String RE01REFER, String GL05BRNC, String CT05SRAW, boolean overrideExist) {
        this.RE01TXNO = RE01TXNO;
        this.RE01DATE = RE01DATE;
        this.RE01CODE = RE01CODE;
        this.RE01OFFID = RE01OFFID;
        this.RE01DOCNO = RE01DOCNO;
        this.ReceiptAmount = ReceiptAmount;
        this.RE01REFER = RE01REFER;
        this.GL05BRNC = GL05BRNC;
        this.CT05SRAW = CT05SRAW;
        this.overrideExist = overrideExist;
    }

    public ReceiptingService(String RE01TXNO, String RE01DATE, String RE01CODE, String RE01OFFID, String RE01DOCNO, double ReceiptAmount, String RE01REFER, String GL05BRNC, String CT05SRAW) {
        this.RE01TXNO = RE01TXNO;
        this.RE01DATE = RE01DATE;
        this.RE01CODE = RE01CODE;
        this.RE01OFFID = RE01OFFID;
        this.RE01DOCNO = RE01DOCNO;
        this.ReceiptAmount = ReceiptAmount;
        this.RE01REFER = RE01REFER;
        this.GL05BRNC = GL05BRNC;
        this.CT05SRAW = CT05SRAW;
    }

    public ReceiptingService(String RE01TXNO, String RE01DATE, String RE01CODE, String RE01OFFID, String RE01DOCNO, double ReceiptAmount, String RE01REFER, String GL05BRNC, String CT05SRAW, String paymentMode) {
        this.RE01TXNO = RE01TXNO;
        this.RE01DATE = RE01DATE;
        this.RE01CODE = RE01CODE;
        this.RE01OFFID = RE01OFFID;
        this.RE01DOCNO = RE01DOCNO;
        this.ReceiptAmount = ReceiptAmount;
        this.RE01REFER = RE01REFER;
        this.GL05BRNC = GL05BRNC;
        this.CT05SRAW = CT05SRAW;
        this.paymentMode = paymentMode;
    }

    public ReceiptingService(String RE01CODE, String RE01DOCNO, String CT05SRAW, double ReceiptAmount, String RE01REFER) {
        this.RE01CODE = RE01CODE;
        this.RE01DOCNO = RE01DOCNO;
        this.ReceiptAmount = ReceiptAmount;
        this.CT05SRAW = CT05SRAW;
        this.RE01REFER = RE01REFER;
    }

    private ReceiptingService(String patrId, String patrName, String patrCourse, String patrAdd1, String patrAdd2, String patrAdd3, String patrCode, String patrTown, String partHtel) {
        this.patrId = patrId;
        this.patrName = patrName;
        this.patrCourse = patrCourse;
        this.patrAdd1 = patrAdd1;
        this.patrAdd2 = patrAdd2;
        this.patrAdd3 = patrAdd3;
        this.patrCode = patrCode;
        this.patrTown = patrTown;
        this.patrHtel = partHtel;
    }

    public String getRE01TXNO() {
        return this.RE01TXNO;
    }

    public String getRE01DATE() {
        return this.RE01DATE;
    }

    public String getRE01CODE() {
        return this.RE01CODE;
    }

    public String getRE01OFFID() {
        return this.RE01OFFID;
    }

    public String getRE01DOCNO() {
        return this.RE01DOCNO;
    }

    public double getReceiptAmount() {
        return this.ReceiptAmount;
    }

    public String getRE01REFER() {
        return this.RE01REFER;
    }

    public String getGL05BRNC() {
        return this.GL05BRNC;
    }

    public String getoverride() {
        return this.override;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public boolean getoverrideExist() {
        return this.overrideExist;
    }

    public String getPatrName() {
        return this.patrName;
    }

    public String getpatrId() {
        return this.patrId;
    }

    public String getPatrCourse() {
        return this.patrCourse;
    }

    public String getPatrAdd1() {
        return this.patrAdd1;
    }

    public String getPatrAdd2() {
        return this.patrAdd2;
    }

    public String getPatrAdd3() {
        return this.patrAdd3;
    }

    public String getPatrCode() {
        return this.patrCode;
    }

    public String getPatrTown() {
        return this.patrTown;
    }

    public String getPatrHtel() {
        return this.patrHtel;
    }

    public String getPaymentMode() {
        return this.paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public static List<ReceiptingService> ListPendingPayment(String patrid) {
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        if (dbtype.equals("mssql")) {
            payment = "ISNULL(T2.RE01AMT,0)";
            override = "ISNULL(T3.RE01AMT,0)";
            cast = "(CAST(T1.RE01TXNO as varchar)";
            cast1 = "(CAST(T1.RE01TXNO as varchar)";
        } else if (dbtype.equals("mysql")) {
            payment = "IFNULL(T2.RE01AMT,0)";
            override = "IFNULL(T3.RE01AMT,0)";
            cast = "(CAST(T1.RE01TXNO as nchar)";
            cast1 = "(CAST(T1.RE01TXNO as nchar)";
        } else if (dbtype.equals("oracle")) {
            payment = "NVL(T2.RE01AMT,0)";
            override = "NVL(T3.RE01AMT,0)";
            cast = "(CAST(T1.RE01TXNO as varchar2(30))";
            cast1 = "(CAST(T1.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T1.RE01TXNO as RE01TXNO, T1.RE01DATE as RE01DATE, T1.RE01CODE as RE01CODE, T1.RE01OFFID AS RE01OFFID, T1.RE01DOCNO AS RE01DOCNO, T1.RE01PDAMT AS RE01PDAMT, T1.RE01REFER AS RE01REFER, (T1.RE01AMT - SUM(" + payment + ")) AS RE01AMT, " + "T5.GL14BRNC  AS GL05BRNC, T8.CT05SRAW  AS CT05SRAW FROM RETRXN T1 " + "LEFT JOIN  RETRXN T2 ON " + cast + "= T2.RE01UPDREF AND (T2.RE01CODE LIKE '%3' or T2.RE01CODE LIKE '%1')) " + "INNER JOIN  GLTRXC T4 ON (T1.RE01CODE = T4.GL38TXCD AND T4.GL38MODE = 'D' ) " + "LEFT JOIN GLPATR T5 ON T1.RE01PATR = T5.GL14PATR " + "LEFT JOIN CTDOCM T6 ON T1.RE01DOCNO = T6.CT03DOCNO " + "LEFT JOIN CTPONT T7 ON T6.CT03MATNO = T7.CT06MATNO AND T7.CT06TAG = '245' " + "LEFT JOIN CTTITL T8 ON T7.CT06POINTER = T8.CT05POINTER " + "WHERE T1.RE01PATR = '" + patrid + "' AND (T1.RE01AMT -" + payment + ") > 0 " + "GROUP BY T1.RE01TXNO, T1.RE01DATE, T1.RE01CODE, T1.RE01OFFID, " + "T1.RE01DOCNO, T1.RE01AMT, T1.RE01PDAMT, T1.RE01REFER, T5.GL14BRNC, T8.CT05SRAW ";
        System.out.println("sql: " + sql);
        double amt = 0.0;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                int count = rs1.getRow();
                while (rs1.next()) {
                    amt = rs1.getString("RE01AMT") == null ? 0.0 : Double.parseDouble(rs1.getString("RE01AMT"));
                    ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs1.getString("RE01TXNO"), DateTime.formatDate(rs1.getString("RE01DATE")), rs1.getString("RE01CODE"), rs1.getString("RE01OFFID"), Handler.ifIsNull(rs1.getString("RE01DOCNO")), amt, Handler.ifIsNull(rs1.getString("RE01REFER")), Handler.ifIsNull(rs1.getString("GL05BRNC")), Handler.ifIsNull(rs1.getString("CT05SRAW")), ReceiptingService.CheckOverride(rs1.getString("RE01CODE")));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn1 != null) {
                        conn1.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getTrxcDesc(String code) {
        String sql = "SELECT GL38DESC FROM GLTRXC WHERE GL38TXCD='" + code + "'";
        String desc = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    desc = rs.getString("GL38DESC");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c != null) {
                        c.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return desc;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<ReceiptingService> ListReceiptData(String receiptno) {
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        if (dbtype.equals("mssql")) {
            payment = "CAST(T3.RE01TXNO as varchar)";
        } else if (dbtype.equals("mysql")) {
            payment = "CAST(T3.RE01TXNO as char)";
        } else if (dbtype.equals("oracle")) {
            payment = "CAST(T3.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T3.RE01CODE AS RE01CODE, T3.RE01REFER AS RE01REFER, T1.RE01DOCNO AS RE01DOCNO, T1.RE01AMT AS RE01AMT, T6.CT05SRAW AS CT05SRAW FROM RETRXN T1 INNER JOIN GLTRXC T2 ON (T2.GL38TXCD = T1.RE01CODE AND T2.GL38MODE = 'C' AND T2.GL38TYPE='R') INNER JOIN RETRXN T3 ON (" + payment + " = T1.RE01UPDREF) " + "LEFT JOIN CTDOCM T4 ON (T1.RE01DOCNO = T4.CT03DOCNO) " + "LEFT JOIN CTPONT T5 ON (T5.CT06MATNO = T4.CT03MATNO AND T5.CT06TAG = '245') " + "LEFT JOIN CTTITL T6 ON (T5.CT06POINTER = T6.CT05POINTER) " + "WHERE T1.RE01REFER = '" + receiptno + "'";
        System.out.println("sql: " + sql);
        double amt = 0.0;
        String docno = "";
        try {
            try {
                Throwable throwable = null;
                Object var12_13 = null;
                try {
                    Connection conn = DBConnection.getConnection();
                    try {
                        block36: {
                            Statement stmt = conn.createStatement();
                            try {
                                try (ResultSet rs = stmt.executeQuery(sql);){
                                    while (rs.next()) {
                                        docno = rs.getString("RE01DOCNO") == null ? ReceiptingService.getTrxcDesc(rs.getString("RE01CODE")) : Handler.ifIsNull(rs.getString("RE01DOCNO"));
                                        ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs.getString("RE01CODE"), docno, Handler.ifIsNull(rs.getString("CT05SRAW")), rs.getDouble("RE01AMT"), Handler.ifIsNull(rs.getString("RE01REFER")));
                                        list.add(newSE01_SearchByMatno);
                                    }
                                }
                                if (stmt == null) break block36;
                            }
                            catch (Throwable throwable2) {
                                if (throwable == null) {
                                    throwable = throwable2;
                                } else if (throwable != throwable2) {
                                    throwable.addSuppressed(throwable2);
                                }
                                if (stmt == null) throw throwable;
                                stmt.close();
                                throw throwable;
                            }
                            stmt.close();
                        }
                        if (conn == null) return list;
                    }
                    catch (Throwable throwable3) {
                        if (throwable == null) {
                            throwable = throwable3;
                        } else if (throwable != throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        if (conn == null) throw throwable;
                        conn.close();
                        throw throwable;
                    }
                    conn.close();
                    return list;
                }
                catch (Throwable throwable4) {
                    if (throwable == null) {
                        throwable = throwable4;
                        throw throwable;
                    }
                    if (throwable == throwable4) throw throwable;
                    throwable.addSuppressed(throwable4);
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c == null) return list;
                    c.close();
                    return list;
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                    return list;
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<ReceiptingService> ListPaidPayment(String patrid) {
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        if (dbtype.equals("mssql")) {
            payment = "T3.RE01TXNO";
        } else if (dbtype.equals("mysql")) {
            payment = "CAST(T3.RE01TXNO as char)";
        } else if (dbtype.equals("oracle")) {
            payment = "CAST(T3.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T3.RE01TXNO as RE01TXNO, T3.RE01CODE as RE01CODE, T1.RE01DATE as RE01DATE, T2.GL38TYPE AS GL38TYPE, T1.RE01OFFID as RE01OFFID, T3.RE01REFER as RE01REFER, T1.RE01DOCNO as RE01DOCNO, T1.RE01UPDREF AS RE01UPDREF, SUM(T1.RE01AMT) as RE01AMT FROM RETRXN T1 INNER JOIN GLTRXC T2 ON (T2.GL38TXCD = T1.RE01CODE) INNER JOIN RETRXN T3 ON (" + payment + " = T1.RE01UPDREF) " + "INNER JOIN GLTRXC T4 ON T4.GL38TXCD = T3.RE01CODE AND (T4.GL38MODE='D' AND T4.GL38TYPE='D') " + "WHERE T1.RE01PATR = '" + patrid + "' AND T2.GL38MODE = 'C' " + "GROUP BY T3.RE01TXNO, T3.RE01CODE , T3.RE01CODE, T1.RE01UPDREF, T1.RE01DATE, T1.RE01OFFID,T3.RE01REFER,  " + "T1.RE01DOCNO, T2.GL38TYPE " + "ORDER BY T1.RE01DATE DESC ";
        System.out.println("sql ListPaidPayment: " + sql);
        double amt = 0.0;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                while (rs1.next()) {
                    amt = Double.parseDouble(rs1.getString("RE01AMT"));
                    ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs1.getString("RE01TXNO"), DateTime.formatDate(rs1.getString("RE01DATE")), rs1.getString("RE01CODE"), rs1.getString("RE01OFFID"), Handler.ifIsNull(rs1.getString("RE01DOCNO")), amt, rs1.getString("RE01REFER"), rs1.getString("GL38TYPE"), rs1.getString("RE01TXNO"));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn1 != null) {
                        conn1.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<ReceiptingService> ListOverridePayment(String patrid) {
        System.out.println("List" + patrid);
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        if (dbtype.equals("mssql")) {
            payment = "ISNULL(T3.RE01AMT,0)";
            override = "ISNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar)";
            cast1 = "(CAST(T1.RE01TXNO as varchar)";
        } else if (dbtype.equals("mysql")) {
            payment = "IFNULL(T3.RE01AMT,0)";
            override = "IFNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as nchar)";
            cast1 = "(CAST(T1.RE01TXNO as nchar)";
        } else if (dbtype.equals("oracle")) {
            payment = "NVL(T3.RE01AMT,0)";
            override = "NVL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar2(30))";
            cast1 = "(CAST(T1.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T1.RE01TXNO as RE01TXNO, T1.RE01CODE as RE01CODE, T3.RE01DATE as RE01DATE, T3.RE01OFFID as RE01OFFID, T1.RE01REFER as RE01REFER, T1.RE01DOCNO as RE01DOCNO, SUM(" + payment + ") as RE01AMT FROM RETRXN T1 " + "INNER JOIN GLTRXC T2 ON (T2.GL38TXCD = T1.RE01CODE) " + "INNER JOIN RETRXN T3 ON (" + cast1 + " = T3.RE01UPDREF)) " + "INNER JOIN GLTRXC T4 ON T4.GL38TXCD = T3.RE01CODE AND (T4.GL38MODE='C' AND T4.GL38TYPE='J') " + "WHERE T1.RE01PATR = 'SL0733' AND T2.GL38MODE = 'D' AND T2.GL38TYPE = 'D' " + "GROUP BY T1.RE01TXNO, T3.RE01DATE, T1.RE01CODE , T3.RE01OFFID, " + "T1.RE01DOCNO, T1.RE01REFER " + "ORDER BY T3.RE01DATE DESC ";
        System.out.println("SQL: " + sql);
        double amt = 0.0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    amt = rs.getString("RE01AMT") == null ? 0.0 : Double.parseDouble(rs.getString("RE01AMT"));
                    ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs.getString("RE01TXNO"), DateTime.formatDate(rs.getString("RE01DATE")), rs.getString("RE01CODE"), rs.getString("RE01OFFID"), Handler.ifIsNull(rs.getString("RE01DOCNO")), amt, rs.getString("RE01REFER"), rs.getString("RE01TXNO"), rs.getString("RE01TXNO"));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c != null) {
                        c.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean CheckOverride(String RE01TXNO) {
        boolean exist = false;
        RE01TXNO = RE01TXNO.substring(0, 2);
        String sql = "SELECT COUNT(*) AS COUNT FROM GLTRXC WHERE GL38TXCD = '" + RE01TXNO + "3'";
        System.out.println("SQL: " + sql);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("COUNT")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c != null) {
                        c.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public static List<ReceiptingService> ListReceiptNumbersByPatronId_SuithaBackup(String patrid) {
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        double amt = 0.0;
        System.out.println("dbtype: " + dbtype);
        if (dbtype.equals("mssql")) {
            payment = "ISNULL(T1.RE01AMT,0)";
            override = "ISNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar)";
            cast1 = "(CAST(T1.RE01TXNO as varchar)";
        } else if (dbtype.equals("mysql")) {
            payment = "IFNULL(T1.RE01AMT,0)";
            override = "IFNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as nchar)";
            cast1 = "(CAST(T1.RE01TXNO as nchar)";
        } else if (dbtype.equals("oracle")) {
            payment = "NVL(T1.RE01AMT,0)";
            override = "NVL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar2(30))";
            cast1 = "(CAST(T1.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T1.RE01REFER as RE01REFER, T1.RE01DATE as RE01DATE, T1.RE01OFFID as RE01OFFID, SUM(T1.RE01AMT) as ReceiptAmount FROM RETRXN T1 INNER JOIN GLTRXC T2 ON (T2.GL38TXCD = T1.RE01CODE) WHERE T1.RE01PATR = '" + patrid + "' AND T2.GL38MODE = 'C' AND T2.GL38TYPE='R' GROUP BY T1.RE01REFER, " + "T1.RE01DATE, T1.RE01OFFID ORDER BY T1.RE01DATE DESC ";
        System.out.println("SQL: " + sql);
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                while (rs1.next()) {
                    amt = rs.getString("ReceiptAmount") == null ? 0.0 : Double.parseDouble(rs.getString("ReceiptAmount"));
                    ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs.getString("RE01REFER"), DateTime.formatDate(rs.getString("RE01DATE")), rs.getString("RE01REFER"), rs.getString("RE01OFFID"), Handler.ifIsNull(rs.getString("RE01REFER")), amt, rs.getString("RE01REFER"), rs.getString("RE01REFER"), rs.getString("RE01REFER"));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn1 != null) {
                        conn1.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<ReceiptingService> ListReceiptNumbersByPatronId(String patrid) {
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String payment = "";
        String override = "";
        String cast = "";
        String cast1 = "";
        String dbtype = DBConnection.getDBtype();
        double amt = 0.0;
        if (dbtype.equals("mssql")) {
            payment = "ISNULL(T1.RE01AMT,0)";
            override = "ISNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar)";
            cast1 = "(CAST(T1.RE01TXNO as varchar)";
        } else if (dbtype.equals("mysql")) {
            payment = "IFNULL(T1.RE01AMT,0)";
            override = "IFNULL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as nchar)";
            cast1 = "(CAST(T1.RE01TXNO as nchar)";
        } else if (dbtype.equals("oracle")) {
            payment = "NVL(T1.RE01AMT,0)";
            override = "NVL(T3.RE01AMT,0)";
            cast = "(CAST(T2.RE01TXNO as varchar2(30))";
            cast1 = "(CAST(T1.RE01TXNO as varchar2(30))";
        }
        String sql = "SELECT T1.RE01REFER as RE01REFER, T1.RE01DATE as RE01DATE, T1.RE01OFFID as RE01OFFID, SUM(T1.RE01AMT) as ReceiptAmount, T1.RE01PAYMODE, T3.DESCRIPTION FROM RETRXN T1 INNER JOIN GLTRXC T2 ON (T2.GL38TXCD = T1.RE01CODE) AND T2.GL38MODE = 'C' AND T2.GL38TYPE='R' LEFT JOIN FNDCODE T3 ON (T3.CODE=T1.RE01PAYMODE) AND T3.FCODE='P' WHERE T1.RE01PATR = '" + patrid + "' " + "GROUP BY T1.RE01REFER, T1.RE01DATE, T1.RE01OFFID, T1.RE01PAYMODE, T3.DESCRIPTION  " + "ORDER BY T1.RE01DATE DESC ";
        System.out.println("SQL: " + sql);
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                ResultSetMetaData metadata = rs1.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs1.next()) {
                    amt = rs1.getString("ReceiptAmount") == null ? 0.0 : Double.parseDouble(rs1.getString("ReceiptAmount"));
                    ReceiptingService newSE01_SearchByMatno = new ReceiptingService(rs1.getString("RE01REFER"), DateTime.formatDate(rs1.getString("RE01DATE")), Handler.ifIsNull(rs1.getString("RE01REFER")), Handler.ifIsNull(rs1.getString("RE01OFFID")), Handler.ifIsNull(rs1.getString("RE01REFER")), Double.parseDouble(rs1.getString("ReceiptAmount")), Handler.ifIsNull(rs1.getString("RE01REFER")), Handler.ifIsNull(rs1.getString("RE01REFER")), Handler.ifIsNull(rs1.getString("RE01REFER")), Handler.ifIsNull(rs1.getString("DESCRIPTION")));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn1 != null) {
                        conn1.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String addPayment(String action, String trxn, String code, String date, String amt, String reference, String patrid, String offid, String docno, String mode, String num, String pdamt) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        Glnumb CTRLNO = Glnumb.getGL98VALUE("TRXNO");
        if (date == null) {
            date = DateTime.getTodayDBDate();
            System.out.println("Date" + date);
        } else {
            date = DateTime.displayToDBFormat(date);
        }
        valueStr.put("RE01TXNO", String.valueOf(CTRLNO.getGL98VALUE()));
        valueStr.put("RE01DATE", date);
        System.out.println("aa1" + date);
        valueStr.put("RE01CODE", code);
        valueStr.put("RE01AMT", amt);
        valueStr.put("RE01OFFID", offid.toUpperCase());
        valueStr.put("RE01UPDREF", Handler.ifIsNull(trxn));
        valueStr.put("RE01REFER", Handler.ifIsNull(reference));
        valueStr.put("RE01PATR", patrid.toUpperCase());
        valueStr.put("RE01DOCNO", Handler.ifIsNull(docno.trim()));
        valueStr.put("RE01STAT", num);
        valueStr.put("RE01PDAMT", pdamt);
        valueStr.put("RE01PAYMODE", mode);
        String query = QueryBuilder.createInsertQuery("RETRXN", valueStr, null, null);
        System.out.println("query: " + query);
        return query;
    }

    public static boolean addTransaction(String action, String trxn, String code, String date, String amt, String reference, String patrid, String offid, String docno, String mode, String num) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        Glnumb CTRLNO = Glnumb.getGL98VALUE("TRXNO");
        date = date == null ? DateTime.getTodayDBDate() : DateTime.displayToDBFormat(date);
        valueStr.put("RE01TXNO", String.valueOf(CTRLNO.getGL98VALUE()));
        valueStr.put("RE01DATE", date);
        valueStr.put("RE01CODE", code);
        valueStr.put("RE01AMT", amt);
        valueStr.put("RE01PDAMT", "0.00");
        valueStr.put("RE01OFFID", offid);
        valueStr.put("RE01UPDREF", Handler.ifIsNull(trxn));
        valueStr.put("RE01REFER", Handler.ifIsNull(reference.trim()));
        valueStr.put("RE01PATR", patrid);
        valueStr.put("RE01DOCNO", Handler.ifIsNull(docno));
        valueStr.put("RE01STAT", num);
        String query = QueryBuilder.createInsertQuery("RETRXN", valueStr, null, null);
        System.out.println("Query: " + query);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean insertAuditAddTransaction(String reference, String patrid, String offid, String docno, String amt, String code) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        DBConnection db = new DBConnection();
        DateTimeFormatter currDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        boolean isSuccess = false;
        if (DBConnection.getDBtype().equals("oracle")) {
            valueStr.put("CI68AUDITID", "CI68AUDITID.NEXTVAL");
            valueStr.put("CI68ACTCODE", "REI0002");
            valueStr.put("CI68PATRONID", offid);
            valueStr.put("CI68DATE", currDate.format(now));
            valueStr.put("CI68TIME", time.format(now));
            valueStr.put("CI68REFER", String.valueOf(patrid) + " : " + amt + " : " + reference + " : " + code);
            valueStr.put("CI68TEMNID", Handler.getLocalIPAdd());
            String add = QueryBuilder.createInsertQuery("CIAUDIT", valueStr, null, null);
            String replacedAdd = add.replaceAll("'CI68AUDITID.NEXTVAL'", "CI68AUDITID.NEXTVAL");
            isSuccess = DBQuery.runQuery(replacedAdd);
        } else if (DBConnection.getDBtype().equals("mssql") || DBConnection.getDBtype().equals("mysql")) {
            valueStr.put("CI68ACTCODE", "REI0002");
            valueStr.put("CI68PATRONID", offid);
            valueStr.put("CI68DATE", currDate.format(now));
            valueStr.put("CI68TIME", time.format(now));
            valueStr.put("CI68REFER", String.valueOf(patrid) + " : " + amt + " : " + reference + " : " + code);
            valueStr.put("CI68TEMNID", Handler.getLocalIPAdd());
            String add = QueryBuilder.createInsertQuery("CIAUDIT", valueStr, null, null);
            isSuccess = DBQuery.runQuery(add);
        }
        return isSuccess;
    }

    public static boolean insertAuditOverride(String reference, String patrid, String offid, String docno, String amt, String code) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        DateTimeFormatter currDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        valueStr.put("CI68ACTCODE", "REI0002");
        valueStr.put("CI68PATRONID", offid);
        valueStr.put("CI68DATE", currDate.format(now));
        valueStr.put("CI68TIME", time.format(now));
        valueStr.put("CI68REFER", String.valueOf(patrid) + " : " + amt + " : " + code);
        valueStr.put("CI68TEMNID", Handler.getLocalIPAdd());
        String add = QueryBuilder.createInsertQuery("CIAUDIT", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(add);
        return isSuccess;
    }

    public static boolean insertAuditAddPayAmount(String reference, String patrid, String offid, float amt, String receiptNo) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        DBConnection db = new DBConnection();
        boolean isSuccess = false;
        DateTimeFormatter currDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        if (DBConnection.getDBtype().equals("oracle")) {
            valueStr.put("CI68AUDITID", "CI68AUDITID.NEXTVAL");
            valueStr.put("CI68ACTCODE", "REI0001");
            valueStr.put("CI68PATRONID", offid);
            valueStr.put("CI68DATE", currDate.format(now));
            valueStr.put("CI68TIME", time.format(now));
            valueStr.put("CI68REFER", String.valueOf(patrid) + " : " + String.valueOf(amt) + " : " + receiptNo);
            valueStr.put("CI68TEMNID", Handler.getLocalIPAdd());
            String add = QueryBuilder.createInsertQuery("CIAUDIT", valueStr, null, null);
            String replacedAdd = add.replaceAll("'CI68AUDITID.NEXTVAL'", "CI68AUDITID.NEXTVAL");
            isSuccess = DBQuery.runQuery(replacedAdd);
        } else if (DBConnection.getDBtype().equals("mssql") || DBConnection.getDBtype().equals("mysql")) {
            valueStr.put("CI68ACTCODE", "REI0001");
            valueStr.put("CI68PATRONID", offid);
            valueStr.put("CI68DATE", currDate.format(now));
            valueStr.put("CI68TIME", time.format(now));
            valueStr.put("CI68REFER", String.valueOf(patrid) + " : " + String.valueOf(amt) + " : " + receiptNo);
            valueStr.put("CI68TEMNID", Handler.getLocalIPAdd());
            String add = QueryBuilder.createInsertQuery("CIAUDIT", valueStr, null, null);
            isSuccess = DBQuery.runQuery(add);
        }
        return isSuccess;
    }

    public static String acceptTransaction(String action, String trxn, String code, String date, String amt, String reference, String patrid, String offid, String docno, String mode, String num) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        Glnumb CTRLNO = Glnumb.getGL98VALUE("TRXNO");
        if (date == null) {
            date = DateTime.getTodayDBDate();
            System.out.println("Date" + date);
        } else {
            date = DateTime.displayToDBFormat(date);
        }
        valueStr.put("RE01TXNO", String.valueOf(CTRLNO.getGL98VALUE()));
        System.out.println("aa" + String.valueOf(CTRLNO.getGL98VALUE()));
        valueStr.put("RE01DATE", date);
        System.out.println("aa1" + date);
        valueStr.put("RE01CODE", code);
        System.out.println("aa2" + code);
        valueStr.put("RE01AMT", amt);
        System.out.println("aa3" + amt);
        valueStr.put("RE01OFFID", offid);
        System.out.println("aa4" + offid);
        valueStr.put("RE01UPDREF", Handler.ifIsNull(trxn));
        valueStr.put("RE01REFER", Handler.ifIsNull(reference));
        System.out.println("aa5" + trxn);
        valueStr.put("RE01PATR", patrid);
        System.out.println("aa6" + patrid);
        String query = QueryBuilder.createInsertQuery("RETRXN", valueStr, null, null);
        return query;
    }

    public static List<ReceiptingService> getContactsOnly() {
        String sql = "";
        ArrayList<ReceiptingService> list = new ArrayList<ReceiptingService>();
        String dbtype = DBConnection.getDBtype();
        if (dbtype.equals("mssql")) {
            sql = "SELECT TOP 1 * FROM GLLIBR ";
        }
        if (dbtype.equals("oracle")) {
            sql = "SELECT * FROM GLLIBR WHERE ROWNUM=1";
        }
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    ReceiptingService library = new ReceiptingService(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("GL28TOWN"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"));
                    list.add(library);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getTitle(String accession) {
        String sql = "select ct05sraw from ctdocm, ctpont, cttitl where ct03docno = '" + accession + "' " + "and ct06matno = ct03matno " + "and ct06pointer = ct05pointer " + "and ct06tag = '245'";
        String title = "";
        System.out.println(sql);
        try {
            try {
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    title = Handler.removeSubfield(rs.getString("ct05sraw"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return title;
    }

    public static void InsertAuditAddTransaction(String sModule, String vsCode, String vsRefNo, String user) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        Connection conn1 = null;
        Statement stmt1 = null;
        DBConnection db = new DBConnection();
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
                LocalDateTime now = LocalDateTime.now();
                if (DBConnection.getDBtype().equals("oracle")) {
                    valueStr.put(String.valueOf(sModule) + "68AUDITID", "CI68AUDITID.NEXTVAL");
                    valueStr.put(String.valueOf(sModule) + "68ACTCODE", vsCode);
                    valueStr.put(String.valueOf(sModule) + "68PATRONID", user);
                    valueStr.put(String.valueOf(sModule) + "68DATE", date.format(now));
                    valueStr.put(String.valueOf(sModule) + "68TIME", time.format(now));
                    valueStr.put(String.valueOf(sModule) + "68REFER", vsRefNo);
                    String add = QueryBuilder.createInsertQuery(String.valueOf(sModule) + "AUDIT", valueStr, null, null);
                    String replacedAdd = add.replaceAll("'CI68AUDITID.NEXTVAL'", "CI68AUDITID.NEXTVAL");
                    boolean bl = DBQuery.runQuery(replacedAdd);
                } else if (DBConnection.getDBtype().equals("mssql") || DBConnection.getDBtype().equals("mysql")) {
                    valueStr.put(String.valueOf(sModule) + "68ACTCODE", vsCode);
                    valueStr.put(String.valueOf(sModule) + "68PATRONID", user);
                    valueStr.put(String.valueOf(sModule) + "68DATE", date.format(now));
                    valueStr.put(String.valueOf(sModule) + "68TIME", time.format(now));
                    valueStr.put(String.valueOf(sModule) + "68REFER", vsRefNo);
                    String add = QueryBuilder.createInsertQuery(String.valueOf(sModule) + "AUDIT", valueStr, null, null);
                    boolean bl = DBQuery.runQuery(add);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
