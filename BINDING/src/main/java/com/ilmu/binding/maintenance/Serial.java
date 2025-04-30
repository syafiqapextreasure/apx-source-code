/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.maintenance;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Serial {
    private String refno;
    private String title;
    private String volfrom;
    private String volto;
    private String issfrom;
    private String labelissfrom;
    private String issto;
    private String lableissto;
    private String datesent;
    private String dateexpected;
    private String status;
    private String bindno;
    private String issn;
    private String ctrlno;
    private String officer;
    private String name;
    private String binder;
    private String bindName;
    private String currency;
    private String rate;
    private String year;
    private String fcost;
    private String lcost;
    private String page;
    private String remarks;
    private String type;
    private String spine;
    private String callno;
    private String cover;
    private String lettering;
    private String original;
    private String adver;
    private String titlePage;
    private String supplement;
    private String index;
    private String contents;
    private String offid;
    private String crdate;
    private String orderno;
    private String copy;
    private String info;
    private String vol;
    private String isslabel;
    private String issno;
    private String voldesc;
    private String issdesc;
    private String dateReceived;

    public String getrefno() {
        return Handler.ifIsNull(this.refno);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getvolfrom() {
        return Handler.ifIsNull(this.volfrom);
    }

    public String getvolto() {
        return Handler.ifIsNull(this.volto);
    }

    public String getissfrom() {
        return Handler.ifIsNull(this.issfrom);
    }

    public String getlabelissfrom() {
        return Handler.ifIsNull(this.labelissfrom);
    }

    public String getissto() {
        return Handler.ifIsNull(this.issto);
    }

    public String getlableissto() {
        return Handler.ifIsNull(this.lableissto);
    }

    public String getdatesent() {
        return Handler.ifIsNull(this.datesent);
    }

    public String getdateexpected() {
        return Handler.ifIsNull(this.dateexpected);
    }

    public String getstatus() {
        return Handler.ifIsNull(this.status);
    }

    public String getbindno() {
        return Handler.ifIsNull(this.bindno);
    }

    public String getissn() {
        return Handler.ifIsNull(this.issn);
    }

    public String getctrlno() {
        return Handler.ifIsNull(this.ctrlno);
    }

    public String getofficer() {
        return Handler.ifIsNull(this.officer);
    }

    public String getname() {
        return Handler.ifIsNull(this.name);
    }

    public String getbinder() {
        return Handler.ifIsNull(this.binder);
    }

    public String getbindName() {
        return Handler.ifIsNull(this.bindName);
    }

    public String getcurrency() {
        return Handler.ifIsNull(this.currency);
    }

    public String getrate() {
        return Handler.ifIsNull(this.rate);
    }

    public String getyear() {
        return Handler.ifIsNull(this.year);
    }

    public String getfcost() {
        return Handler.ifIsNull(this.fcost);
    }

    public String getlcost() {
        return Handler.ifIsNull(this.lcost);
    }

    public String getpage() {
        return Handler.ifIsNull(this.page);
    }

    public String getremarks() {
        return Handler.ifIsNull(this.remarks);
    }

    public String gettype() {
        return Handler.ifIsNull(this.type);
    }

    public String getspine() {
        return Handler.ifIsNull(this.spine);
    }

    public String getcallno() {
        return Handler.ifIsNull(this.callno);
    }

    public String getcover() {
        return Handler.ifIsNull(this.cover);
    }

    public String getlettering() {
        return Handler.ifIsNull(this.lettering);
    }

    public String getoriginal() {
        return Handler.ifIsNull(this.original);
    }

    public String getadver() {
        return Handler.ifIsNull(this.adver);
    }

    public String gettitlePage() {
        return Handler.ifIsNull(this.titlePage);
    }

    public String getsupplement() {
        return Handler.ifIsNull(this.supplement);
    }

    public String getindex() {
        return Handler.ifIsNull(this.index);
    }

    public String getcontents() {
        return Handler.ifIsNull(this.contents);
    }

    public String getoffid() {
        return Handler.ifIsNull(this.offid);
    }

    public String getcrdate() {
        return Handler.ifIsNull(this.crdate);
    }

    public String getorderno() {
        return Handler.ifIsNull(this.orderno);
    }

    public String getcopy() {
        return Handler.ifIsNull(this.copy);
    }

    public String getinfo() {
        return Handler.ifIsNull(this.info);
    }

    public String getvol() {
        return Handler.ifIsNull(this.vol);
    }

    public String getisslabel() {
        return Handler.ifIsNull(this.isslabel);
    }

    public String getissno() {
        return Handler.ifIsNull(this.issno);
    }

    public String getvoldesc() {
        return Handler.ifIsNull(this.voldesc);
    }

    public String getissdesc() {
        return Handler.ifIsNull(this.issdesc);
    }

    public String getvdateReceived() {
        return Handler.ifIsNull(this.dateReceived);
    }

    public Serial(String orderno, String copy, String volfrom, String volto, String info) {
        this.orderno = orderno;
        this.copy = copy;
        this.volfrom = volfrom;
        this.volto = volto;
        this.info = info;
    }

    public Serial(String copy, String vol, String isslabel, String issno, String voldesc, String issdesc, String dateReceived) {
        this.copy = copy;
        this.vol = vol;
        this.isslabel = isslabel;
        this.issno = issno;
        this.voldesc = voldesc;
        this.issdesc = issdesc;
        this.dateReceived = dateReceived;
    }

    public Serial(String refno, String title, String volfrom, String volto, String issfrom, String labelissfrom, String issto, String lableissto, String datesent, String dateexpected, String status) {
        this.refno = refno;
        this.title = title;
        this.volfrom = volfrom;
        this.volto = volto;
        this.issfrom = issfrom;
        this.labelissfrom = labelissfrom;
        this.issto = issto;
        this.lableissto = lableissto;
        this.datesent = datesent;
        this.dateexpected = dateexpected;
        this.status = status;
    }

    public Serial(String issn, String type, String spine, String callno, String cover, String lettering, String original, String adver, String titlePage, String supplement, String index, String contents) {
        this.issn = issn;
        this.type = type;
        this.spine = spine;
        this.callno = callno;
        this.cover = cover;
        this.lettering = lettering;
        this.original = original;
        this.adver = adver;
        this.titlePage = titlePage;
        this.supplement = supplement;
        this.index = index;
        this.contents = contents;
    }

    public Serial(String bindno, String refno, String status, String issn, String ctrlno, String title, String officer, String name, String binder, String bindName, String currency, String rate, String year, String fcost, String lcost, String datesent, String dateexpected, String page, String remarks, String type, String spine, String callno, String cover, String lettering, String original, String adver, String titlePage, String supplement, String index, String contents, String offid, String crdate, String orderno, String volfrom, String volto, String issfrom, String labelissfrom, String issto, String lableissto, String copy) {
        this.bindno = bindno;
        this.refno = refno;
        this.status = status;
        this.issn = issn;
        this.ctrlno = ctrlno;
        this.title = title;
        this.officer = officer;
        this.name = name;
        this.binder = binder;
        this.bindName = bindName;
        this.currency = currency;
        this.rate = rate;
        this.year = year;
        this.fcost = fcost;
        this.lcost = lcost;
        this.datesent = datesent;
        this.dateexpected = dateexpected;
        this.page = page;
        this.remarks = remarks;
        this.type = type;
        this.spine = spine;
        this.callno = callno;
        this.cover = cover;
        this.lettering = lettering;
        this.original = original;
        this.adver = adver;
        this.titlePage = titlePage;
        this.supplement = supplement;
        this.index = index;
        this.contents = contents;
        this.offid = offid;
        this.crdate = crdate;
        this.orderno = orderno;
        this.volfrom = volfrom;
        this.volto = volto;
        this.issfrom = issfrom;
        this.labelissfrom = labelissfrom;
        this.issto = issto;
        this.lableissto = lableissto;
        this.copy = copy;
    }

    public static List<Serial> editView(String refno) {
        ArrayList<Serial> list = new ArrayList<Serial>();
        String query = "SELECT BI01BINDNO, BI01REFERENCE, BI01STATUS, BI02ISSN, BI01CTRLNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, BI01OFFICER, GL14NAME, BI01BINDER, GL39NAME, BI01FOREX, BI01RATE, BI01YEAR, BI01FCOST, BI01LCOST, BI01DTSENT, BI01DTEXPECTED, BI01PAGES, BI01REMARKS, BI02TYPE, BI02SPINE, BI02CALLNO, BI02COVER, BI02LETTERING, BI02ORIGINAL, BI02ADVER, BI02TITLEPAGE, BI02SUPPLEMENT, BI02INDEX, BI02CONTENTS, BI01OFFID,  BI01CRDATE, BI01ORDERNO, BI01VOLFROM, BI01VOLTO, BI01ISSFROM, SE1.SE06ISSLBL AS LABELISSFROM, BI01ISSTO, SE2.SE06ISSLBL AS LABELISSTO, BI01COPY FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO LEFT JOIN GLPATR ON BI01OFFICER = GL14PATR LEFT JOIN GLVEND ON BI01BINDER = GL39CODE LEFT JOIN SEISSU SE1 ON BI01ORDERNO = SE1.SE06ORDER AND  BI01ISSFROM = SE1.SE06ISSNO LEFT JOIN SEISSU SE2 ON BI01ORDERNO = SE2.SE06ORDER AND  BI01ISSTO = SE2.SE06ISSNO WHERE BI01REFERENCE = '" + refno + "'";
        System.out.println("query editView : " + query);
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
                    Serial loadtabledetail = new Serial(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.ifIsNull(rs.getString("BI01REFERENCE")), Handler.ifIsNull(rs.getString("BI01STATUS")), Handler.ifIsNull(rs.getString("BI02ISSN")), Handler.ifIsNull(rs.getString("BI01CTRLNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("BI01OFFICER")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("BI01BINDER")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("BI01FOREX")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01RATE"))), Handler.ifIsNull(rs.getString("BI01YEAR")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01FCOST"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01LCOST"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))), Handler.ifIsNull(rs.getString("BI01PAGES")), Handler.ifIsNull(rs.getString("BI01REMARKS")), Handler.ifIsNull(rs.getString("BI02TYPE")), Handler.ifIsNull(rs.getString("BI02SPINE")), Handler.ifIsNull(rs.getString("BI02CALLNO")), Handler.ifIsNull(rs.getString("BI02COVER")), Handler.ifIsNull(rs.getString("BI02LETTERING")), Handler.ifIsNull(rs.getString("BI02ORIGINAL")), Handler.ifIsNull(rs.getString("BI02ADVER")), Handler.ifIsNull(rs.getString("BI02TITLEPAGE")), Handler.ifIsNull(rs.getString("BI02SUPPLEMENT")), Handler.ifIsNull(rs.getString("BI02INDEX")), Handler.ifIsNull(rs.getString("BI02CONTENTS")), Handler.ifIsNull(rs.getString("BI01OFFID")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01CRDATE"))), Handler.ifIsNull(rs.getString("BI01ORDERNO")), Handler.ifIsNull(rs.getString("BI01VOLFROM")), Handler.ifIsNull(rs.getString("BI01VOLTO")), Handler.ifIsNull(rs.getString("BI01ISSFROM")), Handler.ifIsNull(rs.getString("LABELISSFROM")), Handler.ifIsNull(rs.getString("BI01ISSTO")), Handler.ifIsNull(rs.getString("LABELISSTO")), Handler.ifIsNull(rs.getString("BI01COPY")));
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

    public static List<Serial> getTableOrder(String ctrlno) {
        ArrayList<Serial> list = new ArrayList<Serial>();
        String query = "SELECT SE06ORDER, SE06CPYNO, MIN(SE06VOLLBL) AS VOLFROM, MAX(SE06VOLLBL) AS VOLTO, COUNT(SE06ORDER) AS INFORMATION FROM SEISSU LEFT JOIN SEORDD ON SE03ORDER = SE06ORDER WHERE SE06MATNO = '" + ctrlno + "' AND SE06ISSTAT = 'R' " + "GROUP BY SE06ORDER, SE06CPYNO";
        System.out.println("query searchSer : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial loadtabledetail = new Serial(Handler.ifIsNull(rs.getString("SE06ORDER")), Handler.ifIsNull(rs.getString("SE06CPYNO")), Handler.ifIsNull(rs.getString("VOLFROM")), Handler.ifIsNull(rs.getString("VOLTO")), Handler.ifIsNull(rs.getString("INFORMATION")));
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

    public static List<Serial> getTableIssue(String orderno) {
        ArrayList<Serial> list = new ArrayList<Serial>();
        String query = " SELECT SE06CPYNO, SE06VOLLBL, SE06ISSLBL, SE06ISSNO, SE06VOLDESC, SE06ISSDESC, SE06RCVDATE FROM SEISSU WHERE SE06ISSTAT = 'R' AND SE06ORDER = '" + orderno + "' " + "ORDER BY SE06ISSNO";
        System.out.println("query getTableIssue : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial loadtabledetail = new Serial(Handler.ifIsNull(rs.getString("SE06CPYNO")), Handler.ifIsNull(rs.getString("SE06VOLLBL")), Handler.ifIsNull(rs.getString("SE06ISSLBL")), Handler.ifIsNull(rs.getString("SE06ISSNO")), Handler.ifIsNull(rs.getString("SE06VOLDESC")), Handler.ifIsNull(rs.getString("SE06ISSDESC")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE06RCVDATE"))));
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

    public static List<Serial> getPrevious(String ctrlno) {
        ArrayList<Serial> list = new ArrayList<Serial>();
        DBConnection dbtype = new DBConnection();
        String query = "";
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT TOP 1 BI02ISSN, BI02TYPE, BI02SPINE, BI02CTRLNO, BI02COVER, BI02LETTERING, BI02ORIGINAL, BI02ADVER, BI02TITLEPAGE, BI02SUPPLEMENT, BI02INDEX, BI02CONTENTS FROM BIINST WHERE BI02CTRLNO = '" + ctrlno + "'";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT BI02ISSN, BI02TYPE, BI02SPINE, BI02CTRLNO, BI02COVER, BI02LETTERING, BI02ORIGINAL, BI02ADVER, BI02TITLEPAGE, BI02SUPPLEMENT, BI02INDEX, BI02CONTENTS FROM BIINST WHERE BI02CTRLNO = '" + ctrlno + "' AND ROWNUM = 1 ";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("mysql here");
            query = "SELECT BI02ISSN, BI02TYPE, BI02SPINE, BI02CTRLNO, BI02COVER, BI02LETTERING, BI02ORIGINAL, BI02ADVER, BI02TITLEPAGE, BI02SUPPLEMENT, BI02INDEX, BI02CONTENTS FROM BIINST WHERE BI02CTRLNO = '" + ctrlno + "' LIMIT 1 ";
        }
        System.out.println("query getPrevious : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial loadtabledetail = new Serial(Handler.ifIsNull(rs.getString("BI02ISSN")), Handler.ifIsNull(rs.getString("BI02TYPE")), Handler.ifIsNull(rs.getString("BI02SPINE")), Handler.ifIsNull(rs.getString("BI02CTRLNO")), Handler.ifIsNull(rs.getString("BI02COVER")), Handler.ifIsNull(rs.getString("BI02LETTERING")), Handler.ifIsNull(rs.getString("BI02ORIGINAL")), Handler.ifIsNull(rs.getString("BI02ADVER")), Handler.ifIsNull(rs.getString("BI02TITLEPAGE")), Handler.ifIsNull(rs.getString("BI02SUPPLEMENT")), Handler.ifIsNull(rs.getString("BI02INDEX")), Handler.ifIsNull(rs.getString("BI02CONTENTS")));
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

    public static boolean InsertSer(String issn, String ctrlno, String officer, String binder, String currency, String erate, String year, String fcost, String lcost, String dsent, String dexpect, String remarks, String isbn, String typeBind, String status, String spineTitle, String callno, String cColor, String lColor, String daterec, String recby, String oriCover, String advert, String titlePage, String supp, String indexInc, String contentsPage, String volFrom, String volTo, String issueFrom, String issueTo, String pages, String issueselected, String copyno, String ordernoMast, String bindnoConvert, String bindRefConvert) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        Serial.inserterMast(bindnoConvert, ctrlno, volFrom, volTo, issueFrom, issueTo, copyno, binder, currency, erate, fcost, lcost, year, recby, daterec, bindRefConvert, officer, dsent, dexpect, remarks, status, pages, ordernoMast);
        Serial.insertSerInst(bindnoConvert, ctrlno, issn, spineTitle, callno, typeBind, lColor, oriCover, titlePage, indexInc, advert, supp, contentsPage, officer, cColor);
        System.out.println(String.valueOf(issueselected) + "issueselected");
        String[] newsissueselected = issueselected.split(",");
        int totalissue = newsissueselected.length;
        System.out.println(String.valueOf(totalissue) + "totalissue");
        int i = 0;
        while (i < totalissue) {
            System.out.println(String.valueOf(newsissueselected[i]) + "total is");
            Serial.updatingSeissue(ordernoMast, newsissueselected[i], bindnoConvert);
            ++i;
        }
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BII0002", bindnoConvert, recby);
        return bSuccessful;
    }

    public static boolean inserterMast(String bindnoConvert, String ctrlno, String volFrom, String volTo, String issueFrom, String issueTo, String copyno, String binder, String currency, String erate, String fcost, String lcost, String year, String recby, String daterec, String bindRefConvert, String officer, String dsent, String dexpect, String remarks, String status, String pages, String ordernoMast) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("BI01BINDNO", bindnoConvert);
        valueStr.put("BI01CTRLNO", ctrlno);
        valueStr.put("BI01VOLFROM", volFrom);
        valueStr.put("BI01VOLTO", volTo);
        valueStr.put("BI01ISSFROM", issueFrom);
        valueStr.put("BI01ISSTO", issueTo);
        valueStr.put("BI01COPY", copyno);
        valueStr.put("BI01BINDER", binder);
        valueStr.put("BI01FOREX", currency);
        valueStr.put("BI01RATE", erate);
        if (fcost.length() != 0) {
            valueStr.put("BI01FCOST", fcost);
        }
        valueStr.put("BI01LCOST", lcost);
        valueStr.put("BI01YEAR", year);
        valueStr.put("BI01TYPE", "S");
        valueStr.put("BI01OFFID", recby);
        valueStr.put("BI01CRDATE", daterec);
        valueStr.put("BI01REFERENCE", bindRefConvert);
        valueStr.put("BI01OFFICER", officer);
        valueStr.put("BI01DTSENT", dsent);
        valueStr.put("BI01DTEXPECTED", dexpect);
        valueStr.put("BI01REMARKS", remarks);
        valueStr.put("BI01STATUS", status);
        valueStr.put("BI01PAGES", pages);
        valueStr.put("BI01ORDERNO", ordernoMast);
        String query = QueryBuilder.createInsertQuery("BIMAST", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean insertSerInst(String bindnoConvert, String ctrlno, String isbn, String spineTitle, String callno, String typeBind, String lColor, String oriCover, String titlePage, String indexInc, String advert, String supp, String contentsPage, String officer, String cColor) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("BI02BINDNO", bindnoConvert);
        valueStr.put("BI02CTRLNO", ctrlno);
        valueStr.put("BI02ISSN", isbn);
        valueStr.put("BI02SPINE", spineTitle);
        valueStr.put("BI02CALLNO", callno);
        valueStr.put("BI02TYPE", typeBind);
        valueStr.put("BI02LETTERING", lColor);
        valueStr.put("BI02ORIGINAL", oriCover);
        valueStr.put("BI02TITLEPAGE", titlePage);
        valueStr.put("BI02INDEX", indexInc);
        valueStr.put("BI02ADVER", advert);
        valueStr.put("BI02SUPPLEMENT", supp);
        valueStr.put("BI02CONTENTS", contentsPage);
        valueStr.put("BI02OFFID", officer);
        valueStr.put("BI02COVER", cColor);
        String query = QueryBuilder.createInsertQuery("BIINST", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updatingSeissue(String ordernoMast, String issueselected, String bindnoConvert) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE06ORDER", ordernoMast);
        condition.put("SE06ISSNO", issueselected);
        valueStr.put("SE06ISSTAT", "Y");
        valueStr.put("SE06BINDNO", bindnoConvert);
        String query = QueryBuilder.createUpdateQuery("SEISSU", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean deleteSer(String refno, String id) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        String bindNo = Serial.getBindno(refno);
        System.out.println(String.valueOf(bindNo) + "bindNo");
        Serial.deleteSerMast(bindNo);
        Serial.updatingissueDelete(bindNo);
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BID0002", bindNo, id);
        return bSuccessful;
    }

    public static String getBindno(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT BI01BINDNO FROM BIMAST WHERE BI01REFERENCE = '" + value + "'";
        System.out.println("SQL getAccession" + sSQLStmt);
        String bindno = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    bindno = Handler.ifIsNull(rs.getString("BI01BINDNO"));
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
        return bindno;
    }

    public static String deleteSerMast(String bindno) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("BI01BINDNO", bindno);
        String query = QueryBuilder.createDeleteQuery("BIMAST", map);
        System.out.println("Query ::" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return query;
    }

    public static boolean updatingissueDelete(String bindNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE06BINDNO", bindNo);
        valueStr.put("SE06ISSTAT", "R");
        valueStr.put("SE06BINDNO", "");
        String query = QueryBuilder.createUpdateQuery("SEISSU", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static List<Serial> searchSer(String input_criteria, String search_type) {
        ArrayList<Serial> list = new ArrayList<Serial>();
        String query = "SELECT BI01REFERENCE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, BI01VOLFROM, BI01VOLTO, BI01ISSFROM, SE1.SE06ISSLBL AS LABELISSFROM, BI01ISSTO, SE2.SE06ISSLBL AS LABELISSTO, BI01DTSENT, BI01DTEXPECTED, BI01STATUS FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO LEFT JOIN SEISSU SE1 ON BI01ORDERNO = SE1.SE06ORDER  AND  BI01ISSFROM = SE1.SE06ISSNO LEFT JOIN SEISSU SE2 ON BI01ORDERNO = SE2.SE06ORDER  AND  BI01ISSTO = SE2.SE06ISSNO WHERE BI01TYPE = 'S' AND ";
        if (search_type.equals("ctrlNo")) {
            query = String.valueOf(query) + "UPPER(BI01CTRLNO) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("officcerCode")) {
            query = String.valueOf(query) + "UPPER(BI01OFFICER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("binderCode")) {
            query = String.valueOf(query) + "UPPER(BI01BINDER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("bindno")) {
            query = String.valueOf(query) + "UPPER(BI01BINDNO) = UPPER('" + input_criteria + "')";
        }
        System.out.println("query searchSer : " + query);
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
                    Serial loadtabledetail = new Serial(Handler.ifIsNull(rs.getString("BI01REFERENCE")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("BI01VOLFROM")), Handler.ifIsNull(rs.getString("BI01VOLTO")), Handler.ifIsNull(rs.getString("BI01ISSFROM")), Handler.ifIsNull(rs.getString("LABELISSFROM")), Handler.ifIsNull(rs.getString("BI01ISSTO")), Handler.ifIsNull(rs.getString("LABELISSTO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))), Handler.ifIsNull(rs.getString("BI01STATUS")));
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

    public static boolean UpdateSer(String bindingNo, String officer, String binder, String currency, String erate, String year, String fcost, String lcost, String dsent, String dexpect, String pages, String remarks, String typeBind, String status, String spineTitle, String cColor, String lColor, String oriCover, String advert, String titlePage, String supp, String indexInc, String contentsPage, String liferayLogin) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        Serial.updateSerMast(bindingNo, binder, currency, erate, fcost, lcost, year, officer, dsent, dexpect, pages, remarks, status);
        Serial.updateSerInst(bindingNo, spineTitle, typeBind, lColor, oriCover, titlePage, indexInc, advert, supp, contentsPage, cColor);
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BIU0002", bindingNo, liferayLogin);
        return bSuccessful;
    }

    public static boolean updateSerMast(String bindingNo, String binder, String currency, String erate, String fcost, String lcost, String year, String officer, String dsent, String dexpect, String pages, String remarks, String status) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("BI01BINDNO", bindingNo);
        valueStr.put("BI01BINDER", binder);
        valueStr.put("BI01FOREX", currency);
        valueStr.put("BI01RATE", erate);
        valueStr.put("BI01FCOST", fcost);
        valueStr.put("BI01LCOST", lcost);
        valueStr.put("BI01YEAR", year);
        valueStr.put("BI01OFFICER", officer);
        valueStr.put("BI01DTSENT", dsent);
        valueStr.put("BI01DTEXPECTED", dexpect);
        valueStr.put("BI01REMARKS", remarks);
        valueStr.put("BI01STATUS", status);
        valueStr.put("BI01PAGES", pages);
        String query = QueryBuilder.createUpdateQuery("BIMAST", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateSerInst(String bindingNo, String spineTitle, String typeBind, String lColor, String oriCover, String titlePage, String indexInc, String advert, String supp, String contentsPage, String cColor) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("BI02BINDNO", bindingNo);
        valueStr.put("BI02SPINE", spineTitle);
        valueStr.put("BI02TYPE", typeBind);
        valueStr.put("BI02LETTERING", lColor);
        valueStr.put("BI02ORIGINAL", oriCover);
        valueStr.put("BI02TITLEPAGE", titlePage);
        valueStr.put("BI02INDEX", indexInc);
        valueStr.put("BI02ADVER", advert);
        valueStr.put("BI02SUPPLEMENT", supp);
        valueStr.put("BI02CONTENTS", contentsPage);
        valueStr.put("BI02COVER", cColor);
        String query = QueryBuilder.createUpdateQuery("BIINST", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
