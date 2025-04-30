/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Item_Status_Maintenance;

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

public class SQLStatusMaintenance {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String DOCNO = null;
    private String MATNO = null;
    private String DESC36 = null;
    private String DESC10 = null;
    private String DESC47 = null;
    private String DESC06 = null;
    private String COPYNO = null;
    private String VOLUME = null;
    private String ISBN = null;
    private String PUBLISHER = null;
    private String CALLNUMBER = null;
    private String TITLE = null;
    private String DESC24 = null;
    private String FCOST = null;
    private String LCOST = null;
    private String CIRHITS = null;
    private String DESC05 = null;
    private String DESC71 = null;
    private String CRDATE = null;
    private String RFCDATE = null;
    private String CLMHITS = null;
    private String PATR02 = null;
    private String NAME = null;
    private String CDATE = null;
    private String CTIME = null;
    private String OFFI = null;
    private String OFFINAME = null;
    private String DDATE02 = null;
    private String DTIME = null;
    private String DIDATE = null;
    private String DITIME = null;
    private String DIOFFI = null;
    private String DIOFFINAME = null;
    private String RENEW02 = null;
    private String ROFFID = null;
    private String ROFFIDNAME = null;
    private String RDATE = null;
    private String RTIME = null;
    private String FLAG = null;
    private String CATE = null;
    private String DESCCATE = null;
    private String ICAT27 = null;
    private String DESCICATE = null;
    private String SMD27 = null;
    private String DESCSMD = null;
    private String BRNC = null;
    private String DESCBRNC = null;
    private String PLOAN = null;
    private String ELIG = null;
    private String PTYPE = null;
    private String RENEW27 = null;
    private String STAT03 = null;
    private String STATDESC = null;
    private String LOCA03 = null;
    private String LOCATION = null;
    private String GL05BRNC = null;
    private String BRANCH = null;
    private String ICAT03 = null;
    private String ITEMCATEGORY = null;
    private String SMD03 = null;
    private String CHGTO = null;
    private String PATR14 = null;
    private String MEMDATE = null;
    private String EXPDATE = null;
    private String LOST = null;
    private String LOAN = null;
    private String BDATE = null;
    private String DDATE03 = null;
    private String PATR03 = null;
    private String ACTION8 = null;
    private String totalElig = null;
    private String STAT14 = null;
    private String PATR = null;
    private String PATRNAME = null;
    private String POINTER05 = null;
    private int POINTER = 0;
    private String ALLOWRSV = null;
    private String POINTERTITLE = null;
    private String ITEMBRNC = null;
    private String RESSTAT = null;

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String GL36DESC, String GL10DESC, String GL47DESC, String GL06DESC, String CT03COPYNO, String CT03VOLUME, String ISBN, String PUBLISHER, String CALLNUMBER, String TITLE, String GL24DESC, String CT03FCOST, String CT03LCOST, String CT03CIRHITS, String GL05DESC, String GL71DESC, String CT03CRDATE, String CT03RFCDATE, String CT03CLMHITS, String CT03DDATE, String PATRNAME) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.DESC36 = GL36DESC;
        this.DESC10 = GL10DESC;
        this.DESC47 = GL47DESC;
        this.DESC06 = GL06DESC;
        this.COPYNO = CT03COPYNO;
        this.VOLUME = CT03VOLUME;
        this.ISBN = ISBN;
        this.PUBLISHER = PUBLISHER;
        this.CALLNUMBER = CALLNUMBER;
        this.TITLE = TITLE;
        this.DESC24 = GL24DESC;
        this.FCOST = CT03FCOST;
        this.LCOST = CT03LCOST;
        this.CIRHITS = CT03CIRHITS;
        this.DESC05 = GL05DESC;
        this.DESC71 = GL71DESC;
        this.CRDATE = CT03CRDATE;
        this.RFCDATE = CT03RFCDATE;
        this.CLMHITS = CT03CLMHITS;
        this.DDATE03 = CT03DDATE;
        this.PATRNAME = PATRNAME;
    }

    public SQLStatusMaintenance(String CI02PATR, String GL14NAME, String CI02CDATE, String CI02CTIME, String CI02OFFI, String OFFINAME, String CI02DDATE, String CI02DTIME, String CI02DIDATE, String CI02DITIME, String CI02DIOFFI, String DIOFFINAME, String CI02RENEW, String CI02ROFFID, String ROFFIDNAME, String CI02RDATE, String CI02RTIME, String CI02FLAG) {
        this.PATR02 = CI02PATR;
        this.NAME = GL14NAME;
        this.CDATE = CI02CDATE;
        this.CTIME = CI02CTIME;
        this.OFFI = CI02OFFI;
        this.OFFINAME = OFFINAME;
        this.DDATE02 = CI02DDATE;
        this.DTIME = CI02DTIME;
        this.DIDATE = CI02DIDATE;
        this.DITIME = CI02DITIME;
        this.DIOFFI = CI02DIOFFI;
        this.DIOFFINAME = DIOFFINAME;
        this.RENEW02 = CI02RENEW;
        this.ROFFID = CI02ROFFID;
        this.ROFFIDNAME = ROFFIDNAME;
        this.RDATE = CI02RDATE;
        this.RTIME = CI02RTIME;
        this.FLAG = CI02FLAG;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03COPYNO, String CT03STAT, String STATDESC, String CT03LOCA, String LOCATION, String GL05BRNC, String BRANCH, String CT03ICAT, String ITEMCATEGORY, String CT03SMD, String SMD, String CT03VOLUME) {
        this.DOCNO = CT03DOCNO;
        this.COPYNO = CT03COPYNO;
        this.STAT03 = CT03STAT;
        this.STATDESC = STATDESC;
        this.LOCA03 = CT03LOCA;
        this.LOCATION = LOCATION;
        this.GL05BRNC = GL05BRNC;
        this.BRANCH = BRANCH;
        this.ICAT03 = CT03ICAT;
        this.ITEMCATEGORY = ITEMCATEGORY;
        this.SMD03 = CT03SMD;
        this.DESCSMD = SMD;
        this.VOLUME = CT03VOLUME;
    }

    public SQLStatusMaintenance(String GL27CATE, String DESCCATE, String GL27ICAT, String DESCICATE, String GL27SMD, String DESCSMD, String GL27BRNC, String DESCBRNC, String GL27PLOAN, String GL27ELIG, String GL27PTYPE, String GL27RENEW) {
        this.CATE = GL27CATE;
        this.DESCCATE = DESCCATE;
        this.ICAT27 = GL27ICAT;
        this.DESCICATE = DESCICATE;
        this.SMD27 = GL27SMD;
        this.DESCSMD = DESCSMD;
        this.BRNC = GL27BRNC;
        this.DESCBRNC = DESCBRNC;
        this.PLOAN = GL27PLOAN;
        this.ELIG = GL27ELIG;
        this.PTYPE = GL27PTYPE;
        this.RENEW27 = GL27RENEW;
    }

    public SQLStatusMaintenance(String GL36CHGTO, String CT03STAT, String GL36DESC, String CT03MATNO, String TITLE, String PATR) {
        this.CHGTO = GL36CHGTO;
        this.STAT03 = CT03STAT;
        this.DESC36 = GL36DESC;
        this.MATNO = CT03MATNO;
        this.TITLE = TITLE;
        this.PATR = PATR;
    }

    public SQLStatusMaintenance(String GL36DESC) {
        this.DESC36 = GL36DESC;
    }

    public SQLStatusMaintenance(String GL14PATR, String GL14NAME, String GL14MEMDATE, String GL14EXPDATE) {
        this.PATR14 = GL14PATR;
        this.NAME = GL14NAME;
        this.MEMDATE = GL14MEMDATE;
        this.EXPDATE = GL14EXPDATE;
    }

    public SQLStatusMaintenance(String LOST, String LOAN) {
        this.LOST = LOST;
        this.LOAN = LOAN;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String TITLE, String CT03BDATE, String CT03DDATE, String CT03STAT, String GL36DESC) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.TITLE = TITLE;
        this.BDATE = CT03BDATE;
        this.DDATE03 = CT03DDATE;
        this.STAT03 = CT03STAT;
        this.DESC36 = GL36DESC;
    }

    public SQLStatusMaintenance(String CT03DOCNO, String CT03MATNO, String TITLE, String CT03PATR, String GL14NAME, String GL14STAT, String GL08ACTION8, String totalElig) {
        this.DOCNO = CT03DOCNO;
        this.MATNO = CT03MATNO;
        this.TITLE = TITLE;
        this.PATR03 = CT03PATR;
        this.NAME = GL14NAME;
        this.STAT14 = GL14STAT;
        this.ACTION8 = GL08ACTION8;
        this.totalElig = totalElig;
    }

    public SQLStatusMaintenance(String TITLE, String CT03PATR, String NAMEPATR, String POINTERTITLE, String ALLOWRSV, String CT03LOCA, String GL05BRNC, String GL14BRNC, String RESSTAT) {
        this.TITLE = TITLE;
        this.PATR03 = CT03PATR;
        this.PATRNAME = NAMEPATR;
        this.POINTERTITLE = POINTERTITLE;
        this.ALLOWRSV = ALLOWRSV;
        this.LOCA03 = CT03LOCA;
        this.BRANCH = GL05BRNC;
        this.ITEMBRNC = GL14BRNC;
        this.RESSTAT = RESSTAT;
    }

    public String getDOCNO() {
        return Handler.ifIsNull(this.DOCNO);
    }

    public String getMATNO() {
        return Handler.ifIsNull(this.MATNO);
    }

    public String getDESC36() {
        return Handler.ifIsNull(this.DESC36);
    }

    public String getDESC10() {
        return Handler.ifIsNull(this.DESC10);
    }

    public String getDESC47() {
        return Handler.ifIsNull(this.DESC47);
    }

    public String getDESC06() {
        return Handler.ifIsNull(this.DESC06);
    }

    public String getCOPYNO() {
        return Handler.ifIsNull(this.COPYNO);
    }

    public String getVOLUME() {
        return Handler.ifIsNull(this.VOLUME);
    }

    public String getISBN() {
        return Handler.ifIsNull(this.ISBN);
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

    public String getDESC24() {
        return Handler.ifIsNull(this.DESC24);
    }

    public String getFCOST() {
        return Handler.ifIsNull(this.FCOST);
    }

    public String getLCOST() {
        return Handler.ifIsNull(this.LCOST);
    }

    public String getCIRHITS() {
        return Handler.ifIsNull(this.CIRHITS);
    }

    public String getDESC05() {
        return Handler.ifIsNull(this.DESC05);
    }

    public String getDESC71() {
        return Handler.ifIsNull(this.DESC71);
    }

    public String getCRDATE() {
        return SQLStatusMaintenance.dateConverter(this.CRDATE);
    }

    public String getRFCDATE() {
        return SQLStatusMaintenance.dateConverter(this.RFCDATE);
    }

    public String getCLMHITS() {
        return Handler.ifIsNull(this.CLMHITS);
    }

    public String getPATR02() {
        return Handler.ifIsNull(this.PATR02);
    }

    public String getNAME() {
        return Handler.ifIsNull(this.NAME);
    }

    public String getCDATE() {
        return SQLStatusMaintenance.dateConverter(this.CDATE);
    }

    public String getCTIME() {
        return Handler.ifIsNull(this.CTIME);
    }

    public String getOFFI() {
        return Handler.ifIsNull(this.OFFI);
    }

    public String getOFFINAME() {
        return Handler.ifIsNull(this.OFFINAME);
    }

    public String getDDATE02() {
        return SQLStatusMaintenance.dateConverter(this.DDATE02);
    }

    public String getDTIME() {
        return Handler.ifIsNull(this.DTIME);
    }

    public String getDIDATE() {
        return SQLStatusMaintenance.dateConverter(this.DIDATE);
    }

    public String getDITIME() {
        return Handler.ifIsNull(this.DITIME);
    }

    public String getDIOFFI() {
        return Handler.ifIsNull(this.DIOFFI);
    }

    public String getDIOFFINAME() {
        return Handler.ifIsNull(this.DIOFFINAME);
    }

    public String getRENEW02() {
        return Handler.ifIsNull(this.RENEW02);
    }

    public String getROFFID() {
        return Handler.ifIsNull(this.ROFFID);
    }

    public String getROFFIDNAME() {
        return Handler.ifIsNull(this.ROFFIDNAME);
    }

    public String getRDATE() {
        return SQLStatusMaintenance.dateConverter(this.RDATE);
    }

    public String getRTIME() {
        return Handler.ifIsNull(this.RTIME);
    }

    public String getFLAG() {
        return Handler.ifIsNull(this.FLAG);
    }

    public String getCATE() {
        return Handler.ifIsNull(this.CATE);
    }

    public String getDESCCATE() {
        return Handler.ifIsNull(this.DESCCATE);
    }

    public String getICAT27() {
        return Handler.ifIsNull(this.ICAT27);
    }

    public String getDESCICATE() {
        return Handler.ifIsNull(this.DESCICATE);
    }

    public String getSMD27() {
        return Handler.ifIsNull(this.SMD27);
    }

    public String getDESCSMD() {
        return Handler.ifIsNull(this.DESCSMD);
    }

    public String getBRNC() {
        return Handler.ifIsNull(this.BRNC);
    }

    public String getDESCBRNC() {
        return Handler.ifIsNull(this.DESCBRNC);
    }

    public String getPLOAN() {
        return Handler.ifIsNull(this.PLOAN);
    }

    public String getELIG() {
        return Handler.ifIsNull(this.ELIG);
    }

    public String getPTYPE() {
        return Handler.ifIsNull(this.PTYPE);
    }

    public String getRENEW27() {
        return Handler.ifIsNull(this.RENEW27);
    }

    public String getSTA03T() {
        return Handler.ifIsNull(this.STAT03);
    }

    public String getSTATDESC() {
        return Handler.ifIsNull(this.STATDESC);
    }

    public String getLOCA03() {
        return Handler.ifIsNull(this.LOCA03);
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

    public String getICAT03() {
        return Handler.ifIsNull(this.ICAT03);
    }

    public String getITEMCATEGORY() {
        return Handler.ifIsNull(this.ITEMCATEGORY);
    }

    public String getSMD() {
        return Handler.ifIsNull(this.SMD03);
    }

    public String getCHGTO() {
        return Handler.ifIsNull(this.CHGTO);
    }

    public String getPATR14() {
        return Handler.ifIsNull(this.PATR14);
    }

    public String getMEMDATE() {
        return SQLStatusMaintenance.dateConverter(this.MEMDATE);
    }

    public String getEXPDATE() {
        return SQLStatusMaintenance.dateConverter(this.EXPDATE);
    }

    public String getLOST() {
        return Handler.ifIsNull(this.LOST);
    }

    public String getLOAN() {
        return Handler.ifIsNull(this.LOAN);
    }

    public String getBDATE() {
        return SQLStatusMaintenance.dateConverter(this.BDATE);
    }

    public String getDDATE03() {
        return SQLStatusMaintenance.dateConverter(this.DDATE03);
    }

    public String getPATR03() {
        return Handler.ifIsNull(this.PATR03);
    }

    public String getACTION8() {
        return Handler.ifIsNull(this.ACTION8);
    }

    public String gettotalElig() {
        return Handler.ifIsNull(this.totalElig);
    }

    public String getSTAT14() {
        return Handler.ifIsNull(this.STAT14);
    }

    public String getPATR() {
        return Handler.ifIsNull(this.PATR);
    }

    public String getPATRNAME() {
        return Handler.ifIsNull(this.PATRNAME);
    }

    public String getPOINTER05() {
        return Handler.ifIsNull(this.POINTER05);
    }

    public String getPOINTERTITLE() {
        return Handler.ifIsNull(this.POINTERTITLE);
    }

    public String getALLOWRSV() {
        return Handler.ifIsNull(this.ALLOWRSV);
    }

    public String getITEMBRNC() {
        return Handler.ifIsNull(this.ITEMBRNC);
    }

    public String getRESSTAT() {
        return Handler.ifIsNull(this.RESSTAT);
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        System.out.println("DBTYPE IS :" + dbtype.getDBType());
        try {
            try {
                if (dbtype.getDBType().equals("mssql")) {
                    System.out.println("sql here");
                    query = "SELECT docm.CT03DOCNO, docm.CT03MATNO, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'E' AND CODE = docm.CT03STAT) AS  GL36DESC, icat.GL10DESC, smd.GL47DESC, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'C' AND CODE = docm.CT03COND) AS  GL06DESC, docm.CT03COPYNO, docm.CT03VOLUME, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '020') AS ISBNISSN, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTPUBL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '260') AS PUBLISHER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '245') AS TITLE, (SELECT forx.GL24DESC FROM GLFORX forx WHERE docm.CT03FOREX = forx.GL24FOREX) AS FOREX, docm.CT03FCOST, docm.CT03LCOST, docm.CT03CIRHITS, loca.GL05DESC, brnc.GL71DESC, docm.CT03CRDATE, docm.CT03RFCDATE, docm.CT03CLMHITS, docm.CT03DDATE, (SELECT TOP 1 GL14NAME FROM CICIRC, GLPATR WHERE CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG = 'C' AND CI02PATR = GL14PATR) AS PATRNAME " + "FROM CTDOCM docm, GLICAT icat, GLSMD smd, GLLOCA loca,GLBRNC brnc " + "WHERE docm.CT03ICAT = icat.GL10ICAT AND docm.CT03SMD = smd.GL47SMD " + "AND docm.CT03LOCA = loca.GL05LOCA AND loca.GL05BRNC = brnc.GL71BRNC " + "AND CT03DOCNO = '" + CT03DOCNO + "'";
                } else if (dbtype.getDBType().equals("oracle")) {
                    System.out.println("oracle here");
                    query = "SELECT docm.CT03DOCNO, docm.CT03MATNO, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'E' AND CODE = docm.CT03STAT) AS  GL36DESC, icat.GL10DESC, smd.GL47DESC, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'C' AND CODE = docm.CT03COND) AS  GL06DESC, docm.CT03COPYNO, docm.CT03VOLUME, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '020' AND rownum = 1) AS ISBNISSN, (SELECT CT05SRAW FROM CTPONT, CTPUBL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '260' AND rownum = 1) AS PUBLISHER, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, (SELECT forx.GL24DESC FROM GLFORX forx WHERE docm.CT03FOREX = forx.GL24FOREX) AS FOREX, docm.CT03FCOST, docm.CT03LCOST, docm.CT03CIRHITS, loca.GL05DESC, brnc.GL71DESC, docm.CT03CRDATE, docm.CT03RFCDATE, docm.CT03CLMHITS, docm.CT03DDATE, (SELECT GL14NAME FROM CICIRC, GLPATR WHERE CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG = 'C' AND CI02PATR = GL14PATR AND rownum = 1) AS PATRNAME " + "FROM CTDOCM docm, GLICAT icat, GLSMD smd, GLLOCA loca,GLBRNC brnc " + "WHERE docm.CT03ICAT = icat.GL10ICAT AND docm.CT03SMD = smd.GL47SMD " + "AND docm.CT03LOCA = loca.GL05LOCA AND loca.GL05BRNC = brnc.GL71BRNC " + "AND CT03DOCNO = '" + CT03DOCNO + "'";
                } else if (dbtype.getDBType().equals("mysql")) {
                    System.out.println("MYSQL here");
                    query = "SELECT docm.CT03DOCNO, docm.CT03MATNO, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'E' AND CODE = docm.CT03STAT) AS  GL36DESC, icat.GL10DESC, smd.GL47DESC, (SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'C' AND CODE = docm.CT03COND) AS  GL06DESC, docm.CT03COPYNO, docm.CT03VOLUME, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '020' LIMIT 1) AS ISBNISSN, (SELECT CT05SRAW FROM CTPONT, CTPUBL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '260' LIMIT 1) AS PUBLISHER, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, (SELECT forx.GL24DESC FROM GLFORX forx WHERE docm.CT03FOREX = forx.GL24FOREX) AS FOREX, docm.CT03FCOST, docm.CT03LCOST, docm.CT03CIRHITS, loca.GL05DESC, brnc.GL71DESC, docm.CT03CRDATE, docm.CT03RFCDATE, docm.CT03CLMHITS, docm.CT03DDATE, (SELECT GL14NAME FROM CICIRC, GLPATR WHERE CI02DOCNO = '" + CT03DOCNO + "' " + "AND CI02FLAG = 'C' AND CI02PATR = GL14PATR LIMIT 1) AS PATRNAME " + "FROM CTDOCM docm, GLICAT icat, GLSMD smd, GLLOCA loca,GLBRNC brnc " + "WHERE docm.CT03ICAT = icat.GL10ICAT AND docm.CT03SMD = smd.GL47SMD " + "AND docm.CT03LOCA = loca.GL05LOCA AND loca.GL05BRNC = brnc.GL71BRNC " + "AND CT03DOCNO = '" + CT03DOCNO + "'";
                }
                System.out.println("querySTAUTS1" + query);
                Statement stmt1 = null;
                ResultSet rs = null;
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                List<ISBD> isbn = ISBD.getPunctuation("020");
                List<ISBD> pub = ISBD.getPunctuation("260");
                List<ISBD> call = ISBD.getPunctuation("090");
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    SQLStatusMaintenance statusMain_getDetail = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), Handler.ifIsNull(rs.getString("CT03MATNO")), Handler.ifIsNull(rs.getString("GL36DESC")), Handler.ifIsNull(rs.getString("GL10DESC")), Handler.ifIsNull(rs.getString("GL47DESC")), Handler.ifIsNull(rs.getString("GL06DESC")), Handler.ifIntIsNull(rs.getString("CT03COPYNO")), Handler.ifIntIsNull(rs.getString("CT03VOLUME")), Handler.getSubfield(Handler.ifIsNull(rs.getString("ISBNISSN")), isbn), Handler.getSubfield(Handler.ifIsNull(rs.getString("PUBLISHER")), pub), Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), rs.getString("FOREX"), rs.getString("CT03FCOST"), rs.getString("CT03LCOST"), rs.getString("CT03CIRHITS"), rs.getString("GL05DESC"), rs.getString("GL71DESC"), rs.getString("CT03CRDATE"), Handler.ifIsNull(rs.getString("CT03RFCDATE")), rs.getString("CT03CLMHITS"), Handler.ifIsNull(rs.getString("CT03DDATE")), Handler.ifIsNull(rs.getString("PATRNAME")));
                    System.out.println(String.valueOf(rs.getString("GL36DESC")) + " QQQ");
                    list.add(statusMain_getDetail);
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

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint2(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        System.out.println(String.valueOf(CT03DOCNO) + "circ222222222");
        String query = "SELECT circ.CI02PATR, patr.GL14NAME, circ.CI02CDATE, circ.CI02CTIME, circ.CI02OFFI, chargepatr.GL14NAME AS CHARGENAME, circ.CI02DDATE, circ.CI02DTIME, circ.CI02DIDATE, circ.CI02DITIME, circ.CI02DIOFFI, returnpatr.GL14NAME AS RETURNNAME, circ.CI02RENEW, circ.CI02ROFFID, renewpatr.GL14NAME AS RENEWNAME,  circ.CI02RDATE, circ.CI02RTIME, CASE WHEN circ.CI02FLAG  = 'D' THEN 'Discharged' WHEN circ.CI02FLAG  = 'C' THEN 'Charged' WHEN circ.CI02FLAG  = 'L' THEN 'Lost' ELSE 'Invalid' END AS FLAGDESC FROM CICIRC circ LEFT JOIN GLPATR patr  ON patr.GL14PATR = circ.CI02PATR LEFT JOIN GLPATR chargepatr  ON chargepatr.GL14PATR = circ.CI02OFFI LEFT JOIN GLPATR returnpatr  ON returnpatr.GL14PATR = circ.CI02DIOFFI LEFT JOIN GLPATR renewpatr  ON renewpatr.GL14PATR = circ.CI02ROFFID WHERE circ.CI02DOCNO = '" + CT03DOCNO + "' " + "ORDER BY circ.CI02CDATE DESC";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance statusMain_getDetail2 = new SQLStatusMaintenance(Handler.ifIsNull(rs.getString("CI02PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("CI02CDATE")), Handler.ifIsNull(rs.getString("CI02CTIME")), Handler.ifIsNull(rs.getString("CI02OFFI")), Handler.ifIsNull(rs.getString("CHARGENAME")), Handler.ifIsNull(rs.getString("CI02DDATE")), Handler.ifIsNull(rs.getString("CI02DTIME")), Handler.ifIsNull(rs.getString("CI02DIDATE")), Handler.ifIsNull(rs.getString("CI02DITIME")), Handler.ifIsNull(rs.getString("CI02DIOFFI")), Handler.ifIsNull(rs.getString("RETURNNAME")), Handler.ifIsNull(rs.getString("CI02RENEW")), Handler.ifIsNull(rs.getString("CI02ROFFID")), Handler.ifIsNull(rs.getString("RENEWNAME")), Handler.ifIsNull(rs.getString("CI02RDATE")), Handler.ifIsNull(rs.getString("CI02RTIME")), Handler.ifIsNull(rs.getString("FLAGDESC")));
                    list.add(statusMain_getDetail2);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint3(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03DOCNO, CT03COPYNO, CT03STAT, (SELECT GL36DESC FROM GLDOCS WHERE GL36STAT = CT03STAT) AS STATDESC, CT03LOCA, (SELECT GL05DESC FROM GLLOCA WHERE GL05LOCA = CT03LOCA) AS LOCATION, GL05BRNC, (SELECT GL71DESC FROM GLBRNC WHERE GL05BRNC= GL71BRNC) AS BRANCH, CT03ICAT, (SELECT GL10DESC FROM GLICAT WHERE CT03ICAT= GL10ICAT) AS ITEMCATEGORY, CT03SMD, (SELECT GL47DESC FROM GLSMD WHERE CT03SMD= GL47SMD) AS SMD, CT03VOLUME FROM CTDOCM,  GLLOCA WHERE CT03LOCA=GL05LOCA  AND CT03MATNO = (SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "') " + "AND CT03DOCNO <> '" + CT03DOCNO + "'";
        System.out.println(query);
        System.out.println(query);
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            try {
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

    public static List<SQLStatusMaintenance> getAccessionNoStatusMaint4(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT elig.GL27CATE, (SELECT GL07DESC FROM GLCATE WHERE GL07CATE = GL27CATE) AS DESCCATE, elig.GL27ICAT, (SELECT GL10DESC FROM GLICAT WHERE GL10ICAT = GL27ICAT) AS DESCICATE, elig.GL27SMD, (SELECT GL47DESC FROM GLSMD WHERE GL47SMD = GL27SMD) AS DESCSMD, elig.GL27BRNC, (SELECT GL71DESC FROM GLBRNC WHERE GL71BRNC = GL27BRNC) AS DESCBRNC, elig.GL27PLOAN, elig.GL27ELIG, CASE WHEN elig.GL27PTYPE = 'D' THEN 'Days' WHEN elig.GL27PTYPE = 'H' THEN 'Hours' ELSE 'Invalid' END AS GL27PTYPE, elig.GL27RENEW FROM GLELIG elig, CTDOCM docm, GLLOCA loca WHERE elig.GL27SMD = docm.CT03SMD AND elig.GL27ICAT = docm.CT03ICAT AND docm.CT03DOCNO = '" + CT03DOCNO + "' " + "AND docm.CT03LOCA = loca.GL05LOCA AND GL27BRNC = GL05BRNC";
        System.out.println(query);
        System.out.println(query);
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance statusMain_getDetail4 = new SQLStatusMaintenance(rs.getString("GL27CATE"), rs.getString("DESCCATE"), rs.getString("GL27ICAT"), rs.getString("DESCICATE"), rs.getString("GL27SMD"), rs.getString("DESCSMD"), rs.getString("GL27BRNC"), rs.getString("DESCBRNC"), rs.getString("GL27PLOAN"), rs.getString("GL27ELIG"), rs.getString("GL27PTYPE"), rs.getString("GL27RENEW"));
                    list.add(statusMain_getDetail4);
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

    public static List<SQLStatusMaintenance> getMODIFY(String CT03DOCNO) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        DBConnection dbtype = new DBConnection();
        String query = "";
        try {
            try {
                if (dbtype.getDBType().equals("mssql")) {
                    System.out.println("sql here");
                    query = "SELECT GL36CHGTO, CT03STAT, GL36DESC, CT03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245') AS TITLE, (SELECT TOP 1 CI02PATR FROM CICIRC WHERE CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG = 'C') AS PATR " + "FROM GLDOCS, CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "' AND  GL36STAT = CT03STAT";
                } else if (dbtype.getDBType().equals("oracle")) {
                    System.out.println("oracle here");
                    query = "SELECT GL36CHGTO, CT03STAT, GL36DESC, CT03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245') AS TITLE, (SELECT CI02PATR FROM CICIRC WHERE CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG = 'C' AND rownum = 1) AS PATR " + "FROM GLDOCS, CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "' AND  GL36STAT = CT03STAT";
                } else if (dbtype.getDBType().equals("mysql")) {
                    System.out.println("mysql here");
                    query = "SELECT GL36CHGTO, CT03STAT, GL36DESC, CT03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245') AS TITLE, (SELECT CI02PATR FROM CICIRC WHERE CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG = 'C' LIMIT 1) AS PATR " + "FROM GLDOCS, CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "' AND  GL36STAT = CT03STAT";
                }
                System.out.println(query);
                Statement stmt1 = null;
                ResultSet rs = null;
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    SQLStatusMaintenance getMODIFY = new SQLStatusMaintenance(rs.getString("GL36CHGTO"), rs.getString("CT03STAT"), rs.getString("GL36DESC"), rs.getString("CT03MATNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), rs.getString("PATR"));
                    list.add(getMODIFY);
                    System.out.println("qwerty" + Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title));
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

    public static List<SQLStatusMaintenance> getCIRCDETAILSPatr(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT GL14PATR, GL14NAME, GL14MEMDATE, GL14EXPDATE FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + GL14PATR + "')";
        System.out.println(String.valueOf(query) + " getCIRCDETAILSPatr");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance getCIRCDETAILS1 = new SQLStatusMaintenance(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14MEMDATE"), rs.getString("GL14EXPDATE"));
                    list.add(getCIRCDETAILS1);
                    System.out.println(String.valueOf(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE"))) + " GL14PATRgetCIRCDETAILSPatr");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getCIRCDETAILSLL(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT COUNT(CASE WHEN CT03STAT='L' THEN 1 END) AS LOST,COUNT(CASE WHEN CT03STAT!='L' THEN 1 END) AS LOAN FROM CTDOCM WHERE CT03PATR = '" + GL14PATR + "'";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance getCIRCDETAILS2 = new SQLStatusMaintenance(rs.getString("LOST"), rs.getString("LOAN"));
                    list.add(getCIRCDETAILS2);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getCIRCDETAILTABLE(String GL14PATR) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03DOCNO, CT03MATNO, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS TITLE, CT03BDATE, CT03DDATE, CT03STAT, GL36DESC, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE FROM CTDOCM, GLDOCS WHERE CT03STAT = GL36STAT AND UPPER(CT03PATR) = UPPER('" + GL14PATR + "') AND  CT03STAT IN ('L', 'C', 'E')";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    SQLStatusMaintenance getCIRCDETAIL3 = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), rs.getString("CT03BDATE"), rs.getString("CT03DDATE"), rs.getString("CT03STAT"), rs.getString("GL36DESC"));
                    list.add(getCIRCDETAIL3);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLStatusMaintenance> getStatDESC(String idStat) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT DESCRIPTION FROM FNDCODE WHERE FCODE = 'E' AND CODE = '" + idStat + "'";
        System.out.println(query);
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance getStatDESC2 = new SQLStatusMaintenance(rs.getString("DESCRIPTION"));
                    list.add(getStatDESC2);
                    System.out.println(String.valueOf(rs.getString("DESCRIPTION")) + " DESCRIPTION222");
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

    public static List<SQLStatusMaintenance> getTabControlNo(String CT03MATNO) {
        ArrayList<SQLStatusMaintenance> listConNO;
        block15: {
            listConNO = new ArrayList<SQLStatusMaintenance>();
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            try {
                try {
                    String queryGETMATNO = "SELECT CT03MATNO FROM CTDOCM WHERE CT03STAT = 'C'";
                    System.out.println(queryGETMATNO);
                    stmt1 = conn.createStatement();
                    rs = stmt1.executeQuery(queryGETMATNO);
                    System.out.println(String.valueOf(queryGETMATNO) + "null");
                    if (!rs.next()) {
                        System.out.println("queryGETMATNO == NULL");
                        break block15;
                    }
                    System.out.println("ACCESSION NOT NULL");
                    String query = "SELECT CT03DOCNO, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS TITLE, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE FROM CTDOCM WHERE CT03MATNO = '" + CT03MATNO + "' AND CT03STAT = 'C'";
                    System.out.println(query);
                    try {
                        stmt1 = conn.createStatement();
                        rs = stmt1.executeQuery(query);
                        List<ISBD> title = ISBD.getPunctuation("245");
                        while (rs.next()) {
                            SQLStatusMaintenance getMATNO = new SQLStatusMaintenance(rs.getString("CT03DOCNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title));
                            listConNO.add(getMATNO);
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
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
        }
        return listConNO;
    }

    public static List<SQLStatusMaintenance> getRecallDetail(String CT03DOCNO, String patr) {
        ArrayList<SQLStatusMaintenance> listRecallDetail = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO  AND CT06TAG = '245')) AS TITLE, CT03PATR, (SELECT GL14NAME FROM  GLPATR WHERE GL14PATR = CT03PATR) AS NAMEPATR, (SELECT CT05POINTER FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = CT03MATNO AND CT06TAG = '245')) AS  POINTERTITLE, GL27ALLOWRSV AS ALLOWRSV, CT03LOCA, GL05BRNC, GL14BRNC, (SELECT CI03NSTAT FROM CIRESV WHERE CI03PATR = '" + patr + "' AND CI03DOCNO = '" + CT03DOCNO + "') AS RESSTAT " + "FROM GLELIG elig " + "LEFT JOIN GLPATR patr ON patr.GL14CATE = elig.GL27CATE AND patr.GL14BRNC = elig.GL27BRNC " + "LEFT JOIN CTDOCM docm ON elig.GL27SMD = docm.CT03SMD AND elig.GL27ICAT = docm.CT03ICAT " + "LEFT JOIN GLLOCA loca ON loca.GL05LOCA = docm.CT03LOCA " + "LEFT JOIN GLBRNC brnc ON brnc.GL71BRNC = loca.GL05BRNC " + "WHERE docm.CT03DOCNO = '" + CT03DOCNO + "' AND UPPER(patr.GL14PATR) = UPPER('" + patr + "')";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    SQLStatusMaintenance getRecallDetail = new SQLStatusMaintenance(Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), rs.getString("CT03PATR"), rs.getString("NAMEPATR"), rs.getString("POINTERTITLE"), Handler.ifIsNull(rs.getString("ALLOWRSV")), rs.getString("CT03LOCA"), rs.getString("GL05BRNC"), rs.getString("GL14BRNC"), Handler.ifIsNull(rs.getString("RESSTAT")));
                    listRecallDetail.add(getRecallDetail);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listRecallDetail;
    }

    public static List<SQLStatusMaintenance> getStatDESCAfterModify(String docno) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT (SELECT GL36DESC FROM GLDOCS WHERE GL36STAT = CT03STAT) AS STATDESC FROM CTDOCM WHERE CT03DOCNO = '" + docno + "'";
        System.out.println(query);
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance getStatDESC2 = new SQLStatusMaintenance(rs.getString("STATDESC"));
                    list.add(getStatDESC2);
                    System.out.println(String.valueOf(rs.getString("STATDESC")) + " DESCRIPu");
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

    public static List<SQLStatusMaintenance> getpriceLost(String docno) {
        ArrayList<SQLStatusMaintenance> list = new ArrayList<SQLStatusMaintenance>();
        String query = "SELECT CT03LCOST FROM CTDOCM WHERE CT03DOCNO = '" + docno + "'";
        System.out.println(query);
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    SQLStatusMaintenance getStatDESC2 = new SQLStatusMaintenance(rs.getString("CT03LCOST"));
                    list.add(getStatDESC2);
                    System.out.println("LCOST" + rs.getString("CT03LCOST"));
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

    public static String dateConverter(String oldDate) {
        StringBuilder result = new StringBuilder();
        if (oldDate == null) {
            result.append(" ");
        } else {
            result.append(DateTime.DBToDisplayFormat(oldDate));
        }
        return result.toString();
    }
}
