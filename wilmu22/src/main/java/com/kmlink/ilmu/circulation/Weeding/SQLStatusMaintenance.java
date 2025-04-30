/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Weeding;

import com.kmlink.ilmu.cataloging.Global.ISBD;
import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLStatusMaintenance {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String CT03DOCNO = null;
    private String CT03MATNO = null;
    private String GL36DESC = null;
    private String GL10DESC = null;
    private String GL47DESC = null;
    private String GL06DESC = null;
    private String CT03COPYNO = null;
    private String CT03VOLUME = null;
    private String ISBN = null;
    private String PUBLISHER = null;
    private String CALLNUMBER = null;
    private String TITLE = null;
    private String GL24DESC = null;
    private String CT03FCOST = null;
    private String CT03LCOST = null;
    private String CT03CIRHITS = null;
    private String GL05DESC = null;
    private String GL71DESC = null;
    private String CT03CRDATE = null;
    private String CT03RFCDATE = null;
    private String CT03CLMHITS = null;
    private String WE03REASON = null;
    private String CI02PATR = null;
    private String GL14NAME = null;
    private String CI02CDATE = null;
    private String CI02CTIME = null;
    private String CI02OFFI = null;
    private String CI02DDATE = null;
    private String CI02DTIME = null;
    private String CI02DIDATE = null;
    private String CI02DITIME = null;
    private String CI02DIOFFI = null;
    private String CI02RENEW = null;
    private String CI02ROFFID = null;
    private String CI02RDATE = null;
    private String CI02RTIME = null;
    private String CI02FLAG = null;
    private String GL27CATE = null;
    private String DESCCATE = null;
    private String GL27ICAT = null;
    private String DESCICATE = null;
    private String GL27SMD = null;
    private String DESCSMD = null;
    private String GL27BRNC = null;
    private String DESCBRNC = null;
    private String GL27PLOAN = null;
    private String GL27ELIG = null;
    private String GL27PTYPE = null;
    private String GL27RENEW = null;
    private String CT03STAT = null;
    private String STATDESC = null;
    private String CT03LOCA = null;
    private String LOCATION = null;
    private String GL05BRNC = null;
    private String BRANCH = null;
    private String CT03ICAT = null;
    private String ITEMCATEGORY = null;
    private String CT03SMD = null;
    private String GL36CHGTO = null;
    private String GL14PATR = null;
    private String GL14MEMDATE = null;
    private String GL14EXPDATE = null;
    private String LOST = null;
    private String LOAN = null;
    private String CT03BDATE = null;
    private String CT03DDATE = null;
    private String CT03PATR = null;
    private String GL08ACTION8 = null;
    private String totalElig = null;
    private String GL14STAT = null;
    private String AUTHOR = null;
    private String COLLATION = null;
    private String WE03BDATE = null;
    private String WE03BTIME = null;
    private String WE03DDATE = null;
    private String WE03DTIME = null;
    private String WE03PATR = null;
    private String WE03LASTACT = null;
    private String WE03EDITION = null;

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String GL36DESC, String GL10DESC, String GL47DESC, String GL06DESC, String CT03COPYNO, String CT03VOLUME, String ISBN, String COLLATION, String AUTHOR, String PUBLISHER, String CALLNUMBER, String TITLE, String GL24DESC, String CT03FCOST, String CT03LCOST, String CT03CIRHITS, String GL05DESC, String GL71DESC, String CT03CRDATE, String CT03RFCDATE, String CT03CLMHITS) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03MATNO = CT03MATNO;
        this.GL36DESC = GL36DESC;
        this.GL10DESC = GL10DESC;
        this.GL47DESC = GL47DESC;
        this.GL06DESC = GL06DESC;
        this.CT03COPYNO = CT03COPYNO;
        this.CT03VOLUME = CT03VOLUME;
        this.ISBN = ISBN;
        this.COLLATION = COLLATION;
        this.AUTHOR = AUTHOR;
        this.PUBLISHER = PUBLISHER;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
        this.GL24DESC = GL24DESC;
        this.CT03FCOST = CT03FCOST;
        this.CT03LCOST = CT03LCOST;
        this.CT03CIRHITS = CT03CIRHITS;
        this.GL05DESC = GL05DESC;
        this.GL71DESC = GL71DESC;
        this.CT03CRDATE = CT03CRDATE;
        this.CT03RFCDATE = CT03RFCDATE;
        this.CT03CLMHITS = CT03CLMHITS;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String GL36DESC, String GL10DESC, String GL06DESC, String CT03COPYNO, String CT03VOLUME, String WE03EDITION, String COLLATION, String AUTHOR, String PUBLISHER, String CALLNUMBER, String TITLE, String GL24DESC, String CT03FCOST, String CT03LCOST, String CT03CIRHITS, String GL05DESC, String GL71DESC, String CT03CRDATE, String CT03RFCDATE, String CT03CLMHITS, String WE03BDATE, String WE03BTIME, String WE03DDATE, String WE03DTIME, String WE03PATR, String WE03LASTACT, String WE03REASON) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03MATNO = CT03MATNO;
        this.GL36DESC = GL36DESC;
        this.GL10DESC = GL10DESC;
        this.GL06DESC = GL06DESC;
        this.CT03COPYNO = CT03COPYNO;
        this.CT03VOLUME = CT03VOLUME;
        this.WE03EDITION = WE03EDITION;
        this.COLLATION = COLLATION;
        this.AUTHOR = AUTHOR;
        this.PUBLISHER = PUBLISHER;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
        this.GL24DESC = GL24DESC;
        this.CT03FCOST = CT03FCOST;
        this.CT03LCOST = CT03LCOST;
        this.CT03CIRHITS = CT03CIRHITS;
        this.GL05DESC = GL05DESC;
        this.GL71DESC = GL71DESC;
        this.CT03CRDATE = CT03CRDATE;
        this.CT03RFCDATE = CT03RFCDATE;
        this.CT03CLMHITS = CT03CLMHITS;
        this.WE03BDATE = WE03BDATE;
        this.WE03BTIME = WE03BTIME;
        this.WE03DDATE = WE03DDATE;
        this.WE03DTIME = WE03DTIME;
        this.WE03PATR = WE03PATR;
        this.WE03LASTACT = WE03LASTACT;
        this.WE03REASON = WE03REASON;
    }

    public SQLStatusMaintenance(String CI02PATR, String GL14NAME, String CI02CDATE, String CI02CTIME, String CI02OFFI, String CI02DDATE, String CI02DTIME, String CI02DIDATE, String CI02DITIME, String CI02DIOFFI, String CI02RENEW, String CI02ROFFID, String CI02RDATE, String CI02RTIME, String CI02FLAG) {
        this.CI02PATR = CI02PATR;
        this.GL14NAME = GL14NAME;
        this.CI02CDATE = CI02CDATE;
        this.CI02CTIME = CI02CTIME;
        this.CI02OFFI = CI02OFFI;
        this.CI02DDATE = CI02DDATE;
        this.CI02DTIME = CI02DTIME;
        this.CI02DIDATE = CI02DIDATE;
        this.CI02DITIME = CI02DITIME;
        this.CI02DIOFFI = CI02DIOFFI;
        this.CI02RENEW = CI02RENEW;
        this.CI02ROFFID = CI02ROFFID;
        this.CI02RDATE = CI02RDATE;
        this.CI02RTIME = CI02RTIME;
        this.CI02FLAG = CI02FLAG;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03COPYNO, String CT03STAT, String STATDESC, String CT03LOCA, String LOCATION, String GL05BRNC, String BRANCH, String CT03ICAT, String ITEMCATEGORY, String CT03SMD, String SMD, String CT03VOLUME) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03COPYNO = CT03COPYNO;
        this.CT03STAT = CT03STAT;
        this.STATDESC = STATDESC;
        this.CT03LOCA = CT03LOCA;
        this.LOCATION = LOCATION;
        this.GL05BRNC = GL05BRNC;
        this.BRANCH = BRANCH;
        this.CT03ICAT = CT03ICAT;
        this.ITEMCATEGORY = ITEMCATEGORY;
        this.CT03SMD = CT03SMD;
        this.DESCSMD = SMD;
        this.CT03VOLUME = CT03VOLUME;
    }

    public SQLStatusMaintenance(String GL27CATE, String DESCCATE, String GL27ICAT, String DESCICATE, String GL27SMD, String DESCSMD, String GL27BRNC, String DESCBRNC, String GL27PLOAN, String GL27ELIG, String GL27PTYPE, String GL27RENEW) {
        this.GL27CATE = GL27CATE;
        this.DESCCATE = DESCCATE;
        this.GL27ICAT = GL27ICAT;
        this.DESCICATE = DESCICATE;
        this.GL27SMD = GL27SMD;
        this.DESCSMD = DESCSMD;
        this.GL27BRNC = GL27BRNC;
        this.DESCBRNC = DESCBRNC;
        this.GL27PLOAN = GL27PLOAN;
        this.GL27ELIG = GL27ELIG;
        this.GL27PTYPE = GL27PTYPE;
        this.GL27RENEW = GL27RENEW;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String GL36CHGTO, String CT03STAT, String GL36DESC, String CT03MATNO, String TITLE) {
        this.CT03DOCNO = CT03DOCNO;
        this.GL36CHGTO = GL36CHGTO;
        this.CT03STAT = CT03STAT;
        this.GL36DESC = GL36DESC;
        this.CT03MATNO = CT03MATNO;
        this.TITLE = TITLE;
    }

    public SQLStatusMaintenance(String GL36DESC) {
        this.GL36DESC = GL36DESC;
    }

    public SQLStatusMaintenance(String GL14PATR, String GL14NAME, String GL14MEMDATE, String GL14EXPDATE) {
        this.GL14PATR = GL14PATR;
        this.GL14NAME = GL14NAME;
        this.GL14MEMDATE = GL14MEMDATE;
        this.GL14EXPDATE = GL14EXPDATE;
    }

    public SQLStatusMaintenance(String LOST, String LOAN) {
        this.LOST = LOST;
        this.LOAN = LOAN;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String TITLE, String CT03BDATE, String CT03DDATE, String CT03STAT, String GL36DESC) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03MATNO = CT03MATNO;
        this.TITLE = TITLE;
        this.CT03BDATE = CT03BDATE;
        this.CT03DDATE = CT03DDATE;
        this.CT03STAT = CT03STAT;
        this.GL36DESC = GL36DESC;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String TITLE, String CT03PATR, String GL14NAME, String GL14STAT, String GL08ACTION8, String totalElig) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03MATNO = CT03MATNO;
        this.TITLE = TITLE;
        this.CT03PATR = CT03PATR;
        this.GL14NAME = GL14NAME;
        this.GL14STAT = GL14STAT;
        this.GL08ACTION8 = GL08ACTION8;
        this.totalElig = totalElig;
    }

    public String getCT03DOCNO() {
        return Handler.ifIsNull(this.CT03DOCNO);
    }

    public String getCT03MATNO() {
        return Handler.ifIsNull(this.CT03MATNO);
    }

    public String getWE03REASON() {
        return this.WE03REASON;
    }

    public String getGL36DESC() {
        return Handler.ifIsNull(this.GL36DESC);
    }

    public String getGL10DESC() {
        return Handler.ifIsNull(this.GL10DESC);
    }

    public String getGL47DESC() {
        return Handler.ifIsNull(this.GL47DESC);
    }

    public String getGL06DESC() {
        return Handler.ifIsNull(this.GL06DESC);
    }

    public String getCT03COPYNO() {
        return Handler.ifIsNull(this.CT03COPYNO);
    }

    public String getCT03VOLUME() {
        return Handler.ifIsNull(this.CT03VOLUME);
    }

    public String getISBN() {
        return Handler.ifIsNull(this.ISBN);
    }

    public String getAUTHOR() {
        return Handler.ifIsNull(this.AUTHOR);
    }

    public String getPUBLISHER() {
        return Handler.ifIsNull(this.PUBLISHER);
    }

    public String getCALLNUMBER() {
        return Handler.ifIsNull(this.CALLNUMBER);
    }

    public String getTITLE() {
        return Handler.ifIsNull(this.TITLE);
    }

    public String getGL24DESC() {
        return Handler.ifIsNull(this.GL24DESC);
    }

    public String getCT03FCOST() {
        return Handler.ifIsNull(this.CT03FCOST);
    }

    public String getCT03LCOST() {
        return Handler.ifIsNull(this.CT03LCOST);
    }

    public String getCT03CIRHITS() {
        return Handler.ifIsNull(this.CT03CIRHITS);
    }

    public String getGL05DESC() {
        return Handler.ifIsNull(this.GL05DESC);
    }

    public String getGL71DESC() {
        return Handler.ifIsNull(this.GL71DESC);
    }

    public String getCT03CRDATE() {
        return SQLStatusMaintenance.dateConverter(this.CT03CRDATE);
    }

    public String getCT03RFCDATE() {
        return DateTime.DBToDisplayFormat(this.CT03RFCDATE);
    }

    public String getCT03CLMHITS() {
        return Handler.ifIsNull(this.CT03CLMHITS);
    }

    public String getCI02PATR() {
        return Handler.ifIsNull(this.CI02PATR);
    }

    public String getGL14NAME() {
        return Handler.ifIsNull(this.GL14NAME);
    }

    public String getCI02CDATE() {
        return SQLStatusMaintenance.dateConverter(this.CI02CDATE);
    }

    public String getCI02CTIME() {
        return Handler.ifIsNull(this.CI02CTIME);
    }

    public String getCI02OFFI() {
        return Handler.ifIsNull(this.CI02OFFI);
    }

    public String getCI02DDATE() {
        return SQLStatusMaintenance.dateConverter(this.CI02DDATE);
    }

    public String getCI02DTIME() {
        return Handler.ifIsNull(this.CI02DTIME);
    }

    public String getCI02DIDATE() {
        return SQLStatusMaintenance.dateConverter(this.CI02DIDATE);
    }

    public String getCI02DITIME() {
        return Handler.ifIsNull(this.CI02DITIME);
    }

    public String getCI02DIOFFI() {
        return Handler.ifIsNull(this.CI02DIOFFI);
    }

    public String getCI02RENEW() {
        return Handler.ifIsNull(this.CI02RENEW);
    }

    public String getCI02ROFFID() {
        return Handler.ifIsNull(this.CI02ROFFID);
    }

    public String getCI02RDATE() {
        return SQLStatusMaintenance.dateConverter(this.CI02RDATE);
    }

    public String getCI02RTIME() {
        return Handler.ifIsNull(this.CI02RTIME);
    }

    public String getCI02FLAG() {
        return Handler.ifIsNull(this.CI02FLAG);
    }

    public String getGL27CATE() {
        return Handler.ifIsNull(this.GL27CATE);
    }

    public String getDESCCATE() {
        return Handler.ifIsNull(this.DESCCATE);
    }

    public String getGL27ICAT() {
        return Handler.ifIsNull(this.GL27ICAT);
    }

    public String getDESCICATE() {
        return Handler.ifIsNull(this.DESCICATE);
    }

    public String getGL27SMD() {
        return Handler.ifIsNull(this.GL27SMD);
    }

    public String getDESCSMD() {
        return Handler.ifIsNull(this.DESCSMD);
    }

    public String getGL27BRNC() {
        return Handler.ifIsNull(this.GL27BRNC);
    }

    public String getDESCBRNC() {
        return Handler.ifIsNull(this.DESCBRNC);
    }

    public String getGL27PLOAN() {
        return Handler.ifIsNull(this.GL27PLOAN);
    }

    public String getGL27ELIG() {
        return Handler.ifIsNull(this.GL27ELIG);
    }

    public String getGL27PTYPE() {
        return Handler.ifIsNull(this.GL27PTYPE);
    }

    public String getGL27RENEW() {
        return Handler.ifIsNull(this.GL27RENEW);
    }

    public String getCT03STAT() {
        return Handler.ifIsNull(this.CT03STAT);
    }

    public String getSTATDESC() {
        return Handler.ifIsNull(this.STATDESC);
    }

    public String getCT03LOCA() {
        return Handler.ifIsNull(this.CT03LOCA);
    }

    public String getLOCATION() {
        return Handler.ifIsNull(this.LOCATION);
    }

    public String getGL05BRNC() {
        return Handler.ifIsNull(this.GL05BRNC);
    }

    public String getBRANCH() {
        return Handler.ifIsNull(this.BRANCH);
    }

    public String getCT03ICAT() {
        return Handler.ifIsNull(this.CT03ICAT);
    }

    public String getITEMCATEGORY() {
        return Handler.ifIsNull(this.ITEMCATEGORY);
    }

    public String getCT03SMD() {
        return Handler.ifIsNull(this.CT03SMD);
    }

    public String getGL36CHGTO() {
        return Handler.ifIsNull(this.GL36CHGTO);
    }

    public String getGL14PATR() {
        return Handler.ifIsNull(this.GL14PATR);
    }

    public String getGL14MEMDATE() {
        return SQLStatusMaintenance.dateConverter(this.GL14MEMDATE);
    }

    public String getGL14EXPDATE() {
        return SQLStatusMaintenance.dateConverter(this.GL14EXPDATE);
    }

    public String getLOST() {
        return Handler.ifIsNull(this.LOST);
    }

    public String getLOAN() {
        return Handler.ifIsNull(this.LOAN);
    }

    public String getCT03BDATE() {
        return SQLStatusMaintenance.dateConverter(this.CT03BDATE);
    }

    public String getCT03DDATE() {
        return SQLStatusMaintenance.dateConverter(this.CT03DDATE);
    }

    public String getCT03PATR() {
        return Handler.ifIsNull(this.CT03PATR);
    }

    public String getGL08ACTION8() {
        return Handler.ifIsNull(this.GL08ACTION8);
    }

    public String gettotalElig() {
        return Handler.ifIsNull(this.totalElig);
    }

    public String getGL14STAT() {
        return Handler.ifIsNull(this.GL14STAT);
    }

    public String getCOLLATION() {
        return this.COLLATION;
    }

    public String getWE03BDATE() {
        if (Handler.ifIsNull(this.WE03BDATE).equals(" ")) {
            return "";
        }
        return DateTime.DBToDisplayFormat(Handler.ifIsNull(this.WE03BDATE));
    }

    public String getWE03BTIME() {
        return DateTime.displayTimeFormat(Handler.ifIsNull(this.WE03BTIME));
    }

    public String getWE03DDATE() {
        if (Handler.ifIsNull(this.WE03DDATE).equals(" ")) {
            return "";
        }
        return DateTime.DBToDisplayFormat(Handler.ifIsNull(this.WE03DDATE));
    }

    public String getWE03DTIME() {
        return DateTime.displayTimeFormat(Handler.ifIsNull(this.WE03DTIME));
    }

    public String getWE03PATR() {
        return Handler.ifIsNull(this.WE03PATR);
    }

    public String getWE03LASTACT() {
        return DateTime.DBToDisplayFormat(this.WE03LASTACT);
    }

    public String getWE03EDITION() {
        return this.WE03EDITION;
    }

    public static List<SQLStatusMaintenance> getWeedingAccession(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            List<ISBD> titleisbd = ISBD.getPunctuation("245");
            List<ISBD> isbnisbd = ISBD.getPunctuation("250");
            List<ISBD> collationisbd = ISBD.getPunctuation("300");
            List<ISBD> authorisbd = ISBD.getPunctuation("100");
            List<ISBD> pubisbd = ISBD.getPunctuation("260");
            List<ISBD> callnoisbd = ISBD.getPunctuation("090");
            String query = "SELECT weeding.WE03DOCNO,weeding.WE03STAT, weeding.WE03MATNO, docs.GL36DESC, icat.GL10DESC, cond.GL06DESC, weeding.WE03REASON, weeding.WE03COND, weeding.WE03VOLUME, weeding.WE03EDITION, weeding.WE03COLLATION, weeding.WE03AUTHOR, weeding.WE03PUBLISHER, weeding.WE03CALLNO, weeding.WE03TITLE, weeding.WE03BDATE, weeding.WE03BTIME,weeding.WE03DDATE, weeding.WE03DTIME, weeding.WE03PATR, weeding.WE03LASTACT, (SELECT forx.GL24DESC FROM GLFORX forx WHERE weeding.WE03FOREX = forx.GL24FOREX) AS FOREX, weeding.WE03FCOST, weeding.WE03LCOST,weeding.WE03CIRHITS, loca.GL05DESC, brnc.GL71DESC, weeding.WE03CRDATE, weeding.WE03WDATE, weeding.WE03CLMHITS FROM WEDOCM weeding INNER JOIN GLICAT icat ON weeding.WE03ICAT = icat.GL10ICAT INNER JOIN GLCOND cond ON weeding.WE03COND = cond.GL06COND INNER JOIN GLDOCS docs ON weeding.WE03STAT = docs.GL36STAT INNER JOIN GLLOCA loca ON weeding.WE03LOCA = loca.GL05LOCA INNER JOIN GLBRNC brnc ON loca.GL05BRNC = brnc.GL71BRNC WHERE weeding.WE03DOCNO = '" + CT03DOCNO + "'";
            System.out.println(query);
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                System.out.println("WE03BDATE" + rs.getString("WE03BDATE"));
                SQLStatusMaintenance statusMain_getDetail = new SQLStatusMaintenance(rs.getString("WE03DOCNO"), rs.getString("WE03MATNO"), rs.getString("GL36DESC"), rs.getString("GL10DESC"), rs.getString("GL06DESC"), Handler.ifIsNull(rs.getString("WE03COND")), Handler.ifIsNull(rs.getString("WE03VOLUME")), ISBD.getSubfield(rs.getString("WE03EDITION"), isbnisbd), ISBD.getSubfield(rs.getString("WE03COLLATION"), collationisbd), ISBD.getSubfield(rs.getString("WE03AUTHOR"), authorisbd), ISBD.getSubfield(rs.getString("WE03PUBLISHER"), pubisbd), ISBD.getSubfield(rs.getString("WE03CALLNO"), callnoisbd), ISBD.getSubfield(rs.getString("WE03TITLE"), titleisbd), rs.getString("FOREX"), rs.getString("WE03FCOST"), rs.getString("WE03LCOST"), Handler.ifIntIsNull(rs.getString("WE03CIRHITS")), rs.getString("GL05DESC"), rs.getString("GL71DESC"), DateTime.DBToDisplayFormat(rs.getString("WE03CRDATE")), rs.getString("WE03WDATE"), rs.getString("WE03CLMHITS"), rs.getString("WE03BDATE"), rs.getString("WE03BTIME"), rs.getString("WE03DDATE"), rs.getString("WE03DTIME"), rs.getString("WE03PATR"), rs.getString("WE03LASTACT"), rs.getString("WE03REASON"));
                list.add(statusMain_getDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            List<ISBD> titleisbd = ISBD.getPunctuation("245");
            List<ISBD> isbnisbd = ISBD.getPunctuation("250");
            List<ISBD> collationisbd = ISBD.getPunctuation("300");
            List<ISBD> authorisbd = ISBD.getPunctuation("100");
            List<ISBD> pubisbd = ISBD.getPunctuation("260");
            List<ISBD> callnoisbd = ISBD.getPunctuation("090");
            String query = "SELECT docm.CT03DOCNO,docm.CT03STAT, docm.CT03MATNO, docs.GL36DESC, icat.GL10DESC, smd.GL47DESC, cond.GL06DESC, docm.CT03COPYNO, docm.CT03VOLUME, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '250')) AS ISBN, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '300')) AS COLLATION, (SELECT CT05SRAW FROM CTAUTH WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '100')) AS AUTHOR, (SELECT CT05SRAW FROM CTPUBL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '260')) AS PUBLISHER, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS TITLE, (SELECT forx.GL24DESC FROM GLFORX forx WHERE docm.CT03FOREX = forx.GL24FOREX) AS FOREX, docm.CT03FCOST, docm.CT03LCOST, docm.CT03CIRHITS, loca.GL05DESC, brnc.GL71DESC, docm.CT03CRDATE, docm.CT03RFCDATE, docm.CT03CLMHITS FROM CTDOCM docm INNER JOIN GLICAT icat ON docm.CT03ICAT = icat.GL10ICAT INNER JOIN GLSMD smd ON docm.CT03SMD = smd.GL47SMD INNER JOIN GLCOND cond ON docm.CT03COND = cond.GL06COND INNER JOIN GLDOCS docs ON CT03STAT = docs.GL36STAT INNER JOIN GLLOCA loca ON docm.CT03LOCA = loca.GL05LOCA INNER JOIN GLBRNC brnc ON loca.GL05BRNC = brnc.GL71BRNC WHERE CT03DOCNO = '" + CT03DOCNO + "'";
            System.out.println(query);
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            System.out.println("yyyyyy");
            while (rs.next()) {
                SQLStatusMaintenance statusMain_getDetail;
                System.out.println("HEREEEEEE");
                if (rs.getString("CT03STAT").equals("W") || rs.getString("CT03STAT").equals("A")) {
                    statusMain_getDetail = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("GL36DESC"), rs.getString("GL10DESC"), rs.getString("GL47DESC"), rs.getString("GL06DESC"), Handler.ifIsNull(rs.getString("CT03COPYNO")), Handler.ifIsNull(rs.getString("CT03VOLUME")), ISBD.getSubfield(rs.getString("ISBN"), isbnisbd), ISBD.getSubfield(rs.getString("COLLATION"), collationisbd), ISBD.getSubfield(rs.getString("AUTHOR"), authorisbd), ISBD.getSubfield(rs.getString("PUBLISHER"), pubisbd), ISBD.getSubfield(rs.getString("CALLNUMBER"), callnoisbd), ISBD.getSubfield(rs.getString("TITLE"), titleisbd), rs.getString("FOREX"), rs.getString("CT03FCOST"), rs.getString("CT03LCOST"), Handler.ifIntIsNull(rs.getString("CT03CIRHITS")), rs.getString("GL05DESC"), rs.getString("GL71DESC"), DateTime.DBToDisplayFormat(rs.getString("CT03CRDATE")), DateTime.DBToDisplayFormat(rs.getString("CT03RFCDATE")), rs.getString("CT03CLMHITS"));
                    list.add(statusMain_getDetail);
                    continue;
                }
                statusMain_getDetail = new SQLStatusMaintenance("095");
                list.add(statusMain_getDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint2(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        System.out.println(String.valueOf(CT03DOCNO) + "circ222222222");
        String query = "SELECT circ.CI02PATR, patr.GL14NAME, circ.CI02CDATE, circ.CI02CTIME, circ.CI02OFFI, circ.CI02DDATE, circ.CI02DTIME, circ.CI02DIDATE, circ.CI02DITIME, circ.CI02DIOFFI, circ.CI02RENEW, circ.CI02ROFFID, circ.CI02RDATE, circ.CI02RTIME, circ.CI02FLAG FROM CICIRC circ, GLPATR patr WHERE patr.GL14PATR = circ.CI02PATR AND circ.CI02DOCNO = '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                String oldditime = rs.getString("CI02DITIME");
                System.out.println("oldtime" + oldditime);
                String ditime = oldditime != null ? Handler.ifIsNull(GeneralUtility.StrToTime2(rs.getString("CI02DITIME"))) : "";
                String oldrtime = rs.getString("CI02RTIME");
                System.out.println("oldrtime" + oldrtime);
                String rtime = oldrtime != null ? Handler.ifIsNull(GeneralUtility.StrToTime2(rs.getString("CI02RTIME"))) : "";
                SQLStatusMaintenance statusMain_getDetail2 = new SQLStatusMaintenance(rs.getString("CI02PATR"), rs.getString("GL14NAME"), DateTime.DBToDisplayFormat(rs.getString("CI02CDATE")), GeneralUtility.StrToTime2(rs.getString("CI02CTIME")), rs.getString("CI02OFFI"), DateTime.DBToDisplayFormat(rs.getString("CI02DDATE")), rs.getString("CI02DTIME"), DateTime.DBToDisplayFormat(rs.getString("CI02DIDATE")), ditime, Handler.ifIsNull(rs.getString("CI02DIOFFI")), rs.getString("CI02RENEW"), Handler.ifIsNull(rs.getString("CI02ROFFID")), DateTime.DBToDisplayFormat(rs.getString("CI02RDATE")), rtime, rs.getString("CI02FLAG"));
                list.add(statusMain_getDetail2);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint3(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03DOCNO, CT03COPYNO, CT03STAT, (SELECT GL36DESC FROM GLDOCS WHERE GL36STAT = CT03STAT) AS STATDESC, CT03LOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA = CT03LOCA) AS LOCATION, GL05BRNC, (SELECT GL71DESC FROM GLBRNC WHERE GL05BRNC= GL71BRNC) AS BRANCH, CT03ICAT, (SELECT GL10DESC FROM GLICAT WHERE CT03ICAT= GL10ICAT) AS ITEMCATEGORY, CT03SMD, (SELECT GL47DESC FROM GLSMD WHERE CT03SMD= GL47SMD) AS SMD, CT03VOLUME FROM CTDOCM INNER JOIN GLLOCA ON CT03LOCA=GL05LOCA WHERE CT03MATNO = (SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + CT03DOCNO + "') AND CT03DOCNO <> '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance statusMain_getDetail3 = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), Handler.ifIsNull(rs.getString("CT03COPYNO")), rs.getString("CT03STAT"), rs.getString("STATDESC"), rs.getString("CT03LOCA"), rs.getString("LOCATION"), rs.getString("GL05BRNC"), rs.getString("BRANCH"), rs.getString("CT03ICAT"), rs.getString("ITEMCATEGORY"), rs.getString("CT03SMD"), rs.getString("SMD"), Handler.ifIsNull(rs.getString("CT03VOLUME")));
                list.add(statusMain_getDetail3);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint4(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT elig.GL27CATE, (SELECT GL07DESC FROM GLCATE WHERE GL07CATE = GL27CATE) AS DESCCATE, elig.GL27ICAT, (SELECT GL10DESC FROM GLICAT WHERE GL10ICAT = GL27ICAT) AS DESCICATE, elig.GL27SMD, (SELECT GL47DESC FROM GLSMD WHERE GL47SMD = GL27SMD) AS DESCSMD, elig.GL27BRNC, (SELECT GL71DESC FROM GLBRNC WHERE GL71BRNC = GL27BRNC) AS DESCBRNC,elig.GL27PLOAN, elig.GL27ELIG, CASE WHEN elig.GL27PTYPE = 'D' THEN 'Day' WHEN elig.GL27PTYPE = 'H' THEN 'Hour' ELSE 'Invalid' END AS GL27PTYPE, elig.GL27RENEW FROM GLELIG elig, CTDOCM docm WHERE elig.GL27SMD = docm.CT03SMD AND elig.GL27ICAT = docm.CT03ICAT AND elig.GL27BRNC = docm.CT03LOCA AND docm.CT03DOCNO = '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            if (!rs.next()) {
                SQLStatusMaintenance statusMain_getDetail4 = new SQLStatusMaintenance("", "", "", "", "", "", "", "", "", "", "", "");
                list.add(statusMain_getDetail4);
            } else {
                SQLStatusMaintenance statusMain_getDetail4 = new SQLStatusMaintenance(rs.getString("GL27CATE"), rs.getString("DESCCATE"), rs.getString("GL27ICAT"), rs.getString("DESCICATE"), rs.getString("GL27SMD"), rs.getString("DESCSMD"), rs.getString("GL27BRNC"), rs.getString("DESCBRNC"), rs.getString("GL27PLOAN"), rs.getString("GL27ELIG"), rs.getString("GL27PTYPE"), rs.getString("GL27RENEW"));
                list.add(statusMain_getDetail4);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getMODIFY(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "  SELECT CT03DOCNO, GL36CHGTO, CT03STAT, GL36DESC, CT03MATNO, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS 'TITLE' FROM GLDOCS, CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "' AND  GL36STAT = CT03STAT";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getMODIFY = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), rs.getString("GL36CHGTO"), rs.getString("CT03STAT"), rs.getString("GL36DESC"), rs.getString("CT03MATNO"), Handler.rawToDisplayFormat(rs.getString("TITLE")));
                list.add(getMODIFY);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getCIRCDETAILSPatr(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT GL14PATR, GL14NAME, GL14MEMDATE, GL14EXPDATE FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getCIRCDETAILS1 = new SQLStatusMaintenance(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14MEMDATE"), rs.getString("GL14EXPDATE"));
                list.add(getCIRCDETAILS1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getCIRCDETAILSLL(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT COUNT(CASE WHEN CT03STAT='L' THEN 1 END) AS 'LOST',COUNT(CASE WHEN CT03STAT!='L' THEN 1 END) AS 'LOAN' FROM CTDOCM WHERE CT03PATR = '" + GL14PATR + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getCIRCDETAILS2 = new SQLStatusMaintenance(rs.getString("LOST"), rs.getString("LOAN"));
                list.add(getCIRCDETAILS2);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getCIRCDETAILTABLE(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03DOCNO, CT03MATNO, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS 'TITLE', CT03BDATE, CT03DDATE, CT03STAT, GL36DESC FROM CTDOCM, GLDOCS WHERE CT03STAT = GL36STAT AND CT03PATR = '" + GL14PATR + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getCIRCDETAIL3 = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), Handler.rawToDisplayFormat(rs.getString("TITLE")), rs.getString("CT03BDATE"), rs.getString("CT03DDATE"), rs.getString("CT03STAT"), rs.getString("GL36DESC"));
                list.add(getCIRCDETAIL3);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getStatDESC(String idStat) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT GL36DESC FROM GLDOCS WHERE GL36STAT = '" + idStat + "'";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getStatDESC = new SQLStatusMaintenance(rs.getString("GL36DESC"));
                list.add(getStatDESC);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getTabControlNo(String CT03MATNO) {
        ArrayList<SQLStatusMaintenance> listConNO;
        block6: {
            listConNO = new ArrayList<SQLStatusMaintenance>();
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            try {
                String queryGETMATNO = "SELECT CT03MATNO FROM CTDOCM WHERE CT03STAT = 'C'";
                System.out.println(queryGETMATNO);
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(queryGETMATNO);
                System.out.println(String.valueOf(queryGETMATNO) + "null");
                if (!rs.next()) {
                    System.out.println("queryGETMATNO == NULL");
                    break block6;
                }
                System.out.println("ACCESSION NOT NULL");
                String query = "SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO = '" + CT03MATNO + "'";
                System.out.println(query);
                try {
                    stmt1 = conn.createStatement();
                    rs = stmt1.executeQuery(query);
                    while (rs.next()) {
                        SQLStatusMaintenance getMATNO = new SQLStatusMaintenance(rs.getString("CT03DOCNO"));
                        listConNO.add(getMATNO);
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listConNO;
    }

    public static List<SQLStatusMaintenance> getRecallDetail(String CT03MATNO, String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> listRecallDetail = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03DOCNO, CT03MATNO, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO  AND CT06TAG = '245')) AS 'TITLE', CT03PATR, GL14NAME, GL14STAT, (SELECT GL08ACTION8 FROM GLSTAT WHERE GL08STAT = GL14STAT) AS 'GL08ACTION8', (SELECT count(elig.GL27CATE) FROM GLELIG elig, CTDOCM docm WHERE elig.GL27SMD = docm.CT03SMD AND elig.GL27ICAT = docm.CT03ICAT AND elig.GL27BRNC = docm.CT03LOCA AND docm.CT03DOCNO = '" + CT03DOCNO + "') AS 'totalElig' " + "FROM CTDOCM, GLPATR WHERE CT03PATR = GL14PATR " + "AND CT03MATNO = '" + CT03MATNO + "' AND CT03DOCNO = '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                SQLStatusMaintenance getRecallDetail = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), Handler.rawToDisplayFormat(rs.getString("TITLE")), rs.getString("CT03PATR"), rs.getString("GL14NAME"), rs.getString("GL14STAT"), rs.getString("GL08ACTION8"), rs.getString("totalElig"));
                listRecallDetail.add(getRecallDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listRecallDetail;
    }

    public static String dateConverter(String oldDate) {
        StringBuilder result = new StringBuilder();
        if (oldDate == null) {
            result.append(" ");
        } else {
            result.append(GeneralUtility.DatetoStr(oldDate));
        }
        return result.toString();
    }
}
