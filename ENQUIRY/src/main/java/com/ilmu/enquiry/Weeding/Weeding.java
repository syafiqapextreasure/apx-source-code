/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.Weeding;

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

public class Weeding {
    private String sTrxno;
    private String sAccno;
    private String sTitle;
    private String sWDate;
    private String sReason;
    private String sStatus;
    private String iCate;
    private String sCond;
    private String sLoca;
    private String ctrlno;
    private String currency;
    private String fcost;
    private String lcost;
    private String CircHit;
    private String ClaimHits;
    private String LastActDate;
    private String sBorrowerID;
    private String sDateBorrowed;
    private String sTimeBorrowed;
    private String sDateDue;
    private String sTimeDue;
    private String sAuthor;
    private String sCallNo;
    private String sPublisher;
    private String sEdition;
    private String sCollation;

    public String getsTrxno() {
        return Handler.ifIsNull(this.sTrxno);
    }

    public String getsAccno() {
        return Handler.ifIsNull(this.sAccno);
    }

    public String getsTitle() {
        return Handler.ifIsNull(this.sTitle);
    }

    public String getsWDate() {
        return Handler.ifIsNull(this.sWDate);
    }

    public String getsReason() {
        return Handler.ifIsNull(this.sReason);
    }

    public String getsStatus() {
        return Handler.ifIsNull(this.sStatus);
    }

    public String getiCate() {
        return Handler.ifIsNull(this.iCate);
    }

    public String getsCond() {
        return Handler.ifIsNull(this.sCond);
    }

    public String getsLoca() {
        return Handler.ifIsNull(this.sLoca);
    }

    public String getctrlno() {
        return Handler.ifIsNull(this.ctrlno);
    }

    public String getcurrency() {
        return Handler.ifIsNull(this.currency);
    }

    public String getfcost() {
        return Handler.ifIsNull(this.fcost);
    }

    public String getlcost() {
        return Handler.ifIsNull(this.lcost);
    }

    public String getCircHit() {
        return Handler.ifIsNull(this.CircHit);
    }

    public String getClaimHits() {
        return Handler.ifIsNull(this.ClaimHits);
    }

    public String getLastActDate() {
        return Handler.ifIsNull(this.LastActDate);
    }

    public String getsBorrowerID() {
        return Handler.ifIsNull(this.sBorrowerID);
    }

    public String getsDateBorrowed() {
        return Handler.ifIsNull(this.sDateBorrowed);
    }

    public String getsTimeBorrowed() {
        return Handler.ifIsNull(this.sTimeBorrowed);
    }

    public String getsDateDue() {
        return Handler.ifIsNull(this.sDateDue);
    }

    public String getsTimeDue() {
        return Handler.ifIsNull(this.sTimeDue);
    }

    public String getsAuthor() {
        return Handler.ifIsNull(this.sAuthor);
    }

    public String getsCallNo() {
        return Handler.ifIsNull(this.sCallNo);
    }

    public String getsPublisher() {
        return Handler.ifIsNull(this.sPublisher);
    }

    public String getsEdition() {
        return Handler.ifIsNull(this.sEdition);
    }

    public String getsCollation() {
        return Handler.ifIsNull(this.sCollation);
    }

    public Weeding(String sTrxno, String sAccno, String sTitle, String sWDate) {
        this.sTrxno = sTrxno;
        this.sAccno = sAccno;
        this.sTitle = sTitle;
        this.sWDate = sWDate;
    }

    public Weeding(String sAccno, String sTitle, String sReason, String sWDate, String sStatus, String iCate, String sCond, String sLoca, String ctrlno, String currency, String fcost, String lcost, String CircHit, String ClaimHits, String LastActDate, String sBorrowerID, String sDateBorrowed, String sTimeBorrowed, String sDateDue, String sTimeDue, String sAuthor, String sCallNo, String sPublisher, String sEdition, String sCollation) {
        this.sAccno = sAccno;
        this.sTitle = sTitle;
        this.sReason = sReason;
        this.sWDate = sWDate;
        this.sStatus = sStatus;
        this.iCate = iCate;
        this.sCond = sCond;
        this.sLoca = sLoca;
        this.ctrlno = ctrlno;
        this.currency = currency;
        this.fcost = fcost;
        this.lcost = lcost;
        this.CircHit = CircHit;
        this.ClaimHits = ClaimHits;
        this.LastActDate = LastActDate;
        this.sBorrowerID = sBorrowerID;
        this.sDateBorrowed = sDateBorrowed;
        this.sTimeBorrowed = sTimeBorrowed;
        this.sDateDue = sDateDue;
        this.sTimeDue = sTimeDue;
        this.sAuthor = sAuthor;
        this.sCallNo = sCallNo;
        this.sPublisher = sPublisher;
        this.sEdition = sEdition;
        this.sCollation = sCollation;
    }

    public static List<Weeding> searchWeed(String input_criteria, String search_type, String inputSentDate, String endinputSentDate) {
        ArrayList<Weeding> list = new ArrayList<Weeding>();
        String query = "SELECT WE03TRXNO, WE03DOCNO, WE03TITLE, WE03WDATE FROM WEDOCM WHERE ";
        if (search_type.equals("accNo")) {
            query = String.valueOf(query) + "UPPER(WE03DOCNO) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("title")) {
            query = String.valueOf(query) + "UPPER(WE03TITLE) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("weedDate")) {
            if (inputSentDate != "" && endinputSentDate != "") {
                System.out.println("inputStartDate and inputEndDate");
                query = String.valueOf(query) + "WE03WDATE BETWEEN '" + inputSentDate + "' AND '" + endinputSentDate + "' ";
            }
            if (inputSentDate != "" && endinputSentDate == "") {
                System.out.println("inputStartDate");
                query = String.valueOf(query) + "WE03WDATE >= '" + inputSentDate + "' ";
            }
            if (inputSentDate == "" && endinputSentDate != "") {
                System.out.println("inputEndDate");
                query = String.valueOf(query) + "WE03WDATE <= '" + endinputSentDate + "' ";
            }
        }
        query = String.valueOf(query) + "ORDER BY WE03TRXNO ";
        System.out.println("query searchWeed : " + query);
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
                    Weeding loadtabledetail = new Weeding(Handler.ifIsNull(rs.getString("WE03TRXNO")), Handler.ifIsNull(rs.getString("WE03DOCNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03TITLE")), isbd), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("WE03WDATE"))));
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

    public static List<Weeding> View(String trxno) {
        ArrayList<Weeding> list = new ArrayList<Weeding>();
        String query = "SELECT   WE03DOCNO, WE03TITLE, WE03REASON, WE03WDATE, GL36DESC, GL10DESC, GL06DESC, GL05DESC, WE03MATNO, WE03FOREX, WE03FCOST, WE03LCOST, WE03CIRHITS, WE03CLMHITS, WE03LASTACT, CI02PATR, CI02CDATE, CI02CTIME, CI02DDATE, CI02DTIME, WE03AUTHOR , WE03CALLNO, WE03PUBLISHER, WE03EDITION, WE03COLLATION FROM WEDOCM LEFT JOIN GLDOCS ON WE03STAT = GL36STAT LEFT JOIN GLICAT ON GL10ICAT = WE03ICAT LEFT JOIN GLCOND ON WE03COND = GL06COND LEFT JOIN GLLOCA ON WE03LOCA = GL05LOCA LEFT JOIN CICIRC ON WE03DOCNO = CI02DOCNO AND CI02FLAG = 'C' WHERE WE03TRXNO = " + trxno + " ";
        System.out.println("query View : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> author = ISBD.getPunctuation("100");
                List<ISBD> call = ISBD.getPunctuation("090");
                List<ISBD> publisher = ISBD.getPunctuation("260");
                List<ISBD> edition = ISBD.getPunctuation("250");
                List<ISBD> collation = ISBD.getPunctuation("300");
                while (rs.next()) {
                    String ctime = rs.getString("CI02CTIME");
                    ctime = ctime == null ? "" : Handler.ifIsNull(DateTime.StrToTime2(rs.getString("CI02CTIME")));
                    String dtime = rs.getString("CI02DTIME");
                    dtime = dtime == null ? "" : Handler.ifIsNull(DateTime.StrToTime2(rs.getString("CI02DTIME")));
                    Weeding loadtabledetail = new Weeding(Handler.ifIsNull(rs.getString("WE03DOCNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03TITLE")), isbd), Handler.ifIsNull(rs.getString("WE03REASON")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("WE03WDATE"))), Handler.ifIsNull(rs.getString("GL36DESC")), Handler.ifIsNull(rs.getString("GL10DESC")), Handler.ifIsNull(rs.getString("GL06DESC")), Handler.ifIsNull(rs.getString("GL05DESC")), Handler.ifIsNull(rs.getString("WE03MATNO")), Handler.ifIsNull(rs.getString("WE03FOREX")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("WE03FCOST"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("WE03LCOST"))), Handler.ifIsNull(rs.getString("WE03CIRHITS")), Handler.ifIsNull(rs.getString("WE03CLMHITS")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("WE03LASTACT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02CDATE"))), Handler.ifIsNull(rs.getString("CI02PATR")), ctime, Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02DDATE"))), dtime, Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03AUTHOR")), author), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03CALLNO")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03PUBLISHER")), publisher), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03EDITION")), edition), Handler.getSubfield(Handler.ifIsNull(rs.getString("WE03COLLATION")), collation));
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
}
