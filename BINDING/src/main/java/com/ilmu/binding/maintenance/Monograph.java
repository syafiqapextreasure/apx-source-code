/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.maintenance;

import com.ilmu.binding.maintenance.Serial;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Monograph {
    private String refno;
    private String title;
    private String datesent;
    private String dateexpected;
    private String status;
    private String bindNo;
    private String accno;
    private String ctrlno;
    private String spine;
    private String officer;
    private String name;
    private String binder;
    private String bindName;
    private String currency;
    private String rate;
    private String year;
    private String fcost;
    private String lcost;
    private String remarks;
    private String issn;
    private String type;
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

    public String getrefno() {
        return Handler.ifIsNull(this.refno);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
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

    public String getbindNo() {
        return Handler.ifIsNull(this.bindNo);
    }

    public String getaccno() {
        return Handler.ifIsNull(this.accno);
    }

    public String getctrlno() {
        return Handler.ifIsNull(this.ctrlno);
    }

    public String getspine() {
        return Handler.ifIsNull(this.spine);
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

    public String getremarks() {
        return Handler.ifIsNull(this.remarks);
    }

    public String getissn() {
        return Handler.ifIsNull(this.issn);
    }

    public String gettype() {
        return Handler.ifIsNull(this.type);
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

    public Monograph(String refno, String title, String datesent, String dateexpected, String status) {
        this.refno = refno;
        this.title = title;
        this.datesent = datesent;
        this.dateexpected = dateexpected;
        this.status = status;
    }

    public Monograph(String bindNo, String refno, String status, String accno, String ctrlno, String spine, String officer, String name, String binder, String bindName, String currency, String rate, String year, String fcost, String lcost, String datesent, String dateexpected, String remarks, String issn, String type, String callno, String cover, String lettering, String original, String adver, String titlePage, String supplement, String index, String contents, String offid, String crdate, String title) {
        this.bindNo = bindNo;
        this.refno = refno;
        this.status = status;
        this.accno = accno;
        this.ctrlno = ctrlno;
        this.spine = spine;
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
        this.remarks = remarks;
        this.issn = issn;
        this.type = type;
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
        this.title = title;
    }

    public static List<Monograph> editView(String refno) {
        ArrayList<Monograph> list = new ArrayList<Monograph>();
        String query = "SELECT BI01BINDNO, BI01REFERENCE, BI01STATUS, BI01ACCESSION, BI01CTRLNO, BI02SPINE, BI01OFFICER, GL14NAME, BI01BINDER, GL39NAME, BI01FOREX, BI01RATE, BI01YEAR, BI01FCOST, BI01LCOST, BI01DTSENT, BI01DTEXPECTED, BI01REMARKS, BI02ISSN, BI02TYPE, BI02CALLNO, BI02COVER, BI02LETTERING, BI02ORIGINAL, BI02ADVER, BI02TITLEPAGE, BI02SUPPLEMENT, BI02INDEX, BI02CONTENTS,BI01OFFID,  BI01CRDATE,(SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE  FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO LEFT JOIN GLPATR ON BI01OFFICER = GL14PATR LEFT JOIN GLVEND ON BI01BINDER = GL39CODE WHERE BI01REFERENCE = '" + refno + "'";
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
                    Monograph loadtabledetail = new Monograph(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.ifIsNull(rs.getString("BI01REFERENCE")), Handler.ifIsNull(rs.getString("BI01STATUS")), Handler.ifIsNull(rs.getString("BI01ACCESSION")), Handler.ifIsNull(rs.getString("BI01CTRLNO")), Handler.ifIsNull(rs.getString("BI02SPINE")), Handler.ifIsNull(rs.getString("BI01OFFICER")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("BI01BINDER")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("BI01FOREX")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01RATE"))), Handler.ifIsNull(rs.getString("BI01YEAR")), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01FCOST"))), Handler.ifIsNull(Handler.decimalConversion(rs.getString("BI01LCOST"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))), Handler.ifIsNull(rs.getString("BI01REMARKS")), Handler.ifIsNull(rs.getString("BI02ISSN")), Handler.ifIsNull(rs.getString("BI02TYPE")), Handler.ifIsNull(rs.getString("BI02CALLNO")), Handler.ifIsNull(rs.getString("BI02COVER")), Handler.ifIsNull(rs.getString("BI02LETTERING")), Handler.ifIsNull(rs.getString("BI02ORIGINAL")), Handler.ifIsNull(rs.getString("BI02ADVER")), Handler.ifIsNull(rs.getString("BI02TITLEPAGE")), Handler.ifIsNull(rs.getString("BI02SUPPLEMENT")), Handler.ifIsNull(rs.getString("BI02INDEX")), Handler.ifIsNull(rs.getString("BI02CONTENTS")), Handler.ifIsNull(rs.getString("BI01OFFID")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01CRDATE"))), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd));
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

    public static List<Monograph> searchMono(String input_criteria, String search_type, String inputSentDate, String endinputSentDate) {
        ArrayList<Monograph> list = new ArrayList<Monograph>();
        String query = "SELECT BI01REFERENCE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, BI01DTSENT, BI01DTEXPECTED, BI01STATUS FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO WHERE BI01TYPE = 'M' AND ";
        if (search_type.equals("ctrlNo")) {
            query = String.valueOf(query) + "UPPER(BI01CTRLNO) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("officcerCode")) {
            query = String.valueOf(query) + "UPPER(BI01OFFICER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("binderCode")) {
            query = String.valueOf(query) + "UPPER(BI01BINDER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("sentDate")) {
            if (inputSentDate != "" && endinputSentDate != "") {
                System.out.println("inputStartDate and inputEndDate");
                query = String.valueOf(query) + "BI01DTSENT BETWEEN '" + inputSentDate + "' AND '" + endinputSentDate + "' ";
            }
            if (inputSentDate != "" && endinputSentDate == "") {
                System.out.println("inputStartDate");
                query = String.valueOf(query) + "BI01DTSENT >= '" + inputSentDate + "' ";
            }
            if (inputSentDate == "" && endinputSentDate != "") {
                System.out.println("inputEndDate");
                query = String.valueOf(query) + "BI01DTSENT <= '" + endinputSentDate + "' ";
            }
        } else if (search_type.equals("bindno")) {
            query = String.valueOf(query) + "UPPER(BI01BINDNO) = UPPER('" + input_criteria + "')";
        }
        System.out.println("query searchMono : " + query);
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
                    Monograph loadtabledetail = new Monograph(Handler.ifIsNull(rs.getString("BI01REFERENCE")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))), Handler.ifIsNull(rs.getString("BI01STATUS")));
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

    public static String CreateMonograph(String accno, String ctrlno, String officer, String binder, String currency, String erate, String year, String fcost, String lcost, String dsent, String dexpect, String remarks, String isbn, String typeBind, String status, String spineTitle, String callno, String cColor, String lColor, String daterec, String recby, String oriCover, String advert, String titlePage, String supp, String indexInc, String contentsPage, String bindnoConvert, String bindRefConvert) throws UnknownHostException {
        Monograph.saveMonoMast(bindnoConvert, ctrlno, accno, binder, currency, erate, fcost, lcost, year, recby, daterec, bindRefConvert, officer, dsent, dexpect, remarks, status);
        Monograph.saveMonoBiInst(bindnoConvert, ctrlno, isbn, spineTitle, callno, typeBind, lColor, oriCover, titlePage, indexInc, advert, supp, contentsPage, officer, cColor);
        Monograph.updatingAccessionStatus(accno, "B");
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BII0001", bindnoConvert, recby);
        return bindnoConvert;
    }

    public static boolean saveMonoMast(String bindnoConvert, String ctrlno, String accno, String binder, String currency, String erate, String fcost, String lcost, String year, String recby, String daterec, String bindRefConvert, String officer, String dsent, String dexpect, String remarks, String status) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("BI01BINDNO", bindnoConvert);
        valueStr.put("BI01CTRLNO", ctrlno);
        valueStr.put("BI01ACCESSION", accno);
        valueStr.put("BI01BINDER", binder);
        valueStr.put("BI01FOREX", currency);
        valueStr.put("BI01RATE", erate);
        if (fcost.length() != 0) {
            valueStr.put("BI01FCOST", fcost);
        }
        valueStr.put("BI01LCOST", lcost);
        valueStr.put("BI01YEAR", year);
        valueStr.put("BI01TYPE", "M");
        valueStr.put("BI01OFFID", recby);
        valueStr.put("BI01CRDATE", daterec);
        valueStr.put("BI01REFERENCE", bindRefConvert);
        valueStr.put("BI01OFFICER", officer);
        valueStr.put("BI01DTSENT", dsent);
        valueStr.put("BI01DTEXPECTED", dexpect);
        valueStr.put("BI01REMARKS", remarks);
        valueStr.put("BI01STATUS", status);
        String query = QueryBuilder.createInsertQuery("BIMAST", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean saveMonoBiInst(String bindnoConvert, String ctrlno, String isbn, String spineTitle, String callno, String typeBind, String lColor, String oriCover, String titlePage, String indexInc, String advert, String supp, String contentsPage, String officer, String cColor) {
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

    public static boolean updatingAccessionStatus(String accno, String status) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", accno);
        valueStr.put("CT03STAT", status);
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static int Get98VALUE(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='" + value + "'";
        System.out.println("SQL GetBINDNO" + sSQLStmt);
        int getBINDNO = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getBINDNO = rs.getInt("GL98VALUE");
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
        return getBINDNO;
    }

    public static boolean updatingbidno(int newbidno) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", "BINDNO");
        valueInt.put("GL98VALUE", newbidno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static int GetBIREFNO(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='" + value + "'";
        System.out.println("SQL GetBINDNO" + sSQLStmt);
        int getBINDNO = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getBINDNO = rs.getInt("GL98VALUE");
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
        return getBINDNO;
    }

    public static boolean updatingbindRef(int newbindRef) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", "BIREFNO");
        valueInt.put("GL98VALUE", newbindRef);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean UpdateMonoGraph(String bindingNo, String officer, String binder, String currency, String erate, String year, String fcost, String lcost, String dsent, String dexpect, String remarks, String typeBind, String status, String spineTitle, String cColor, String lColor, String oriCover, String advert, String titlePage, String supp, String indexInc, String contentsPage, String liferayLogin) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        Monograph.updateMonoMast(bindingNo, binder, currency, erate, fcost, lcost, year, officer, dsent, dexpect, remarks, status);
        Monograph.updateMonoInst(bindingNo, spineTitle, typeBind, lColor, oriCover, titlePage, indexInc, advert, supp, contentsPage, cColor);
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BIU0001", bindingNo, liferayLogin);
        return bSuccessful;
    }

    public static boolean updateMonoMast(String bindingNo, String binder, String currency, String erate, String fcost, String lcost, String year, String officer, String dsent, String dexpect, String remarks, String status) {
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
        String query = QueryBuilder.createUpdateQuery("BIMAST", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateMonoInst(String bindingNo, String spineTitle, String typeBind, String lColor, String oriCover, String titlePage, String indexInc, String advert, String supp, String contentsPage, String cColor) {
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

    public static boolean deletemonoGraph(String refno, String id) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        String accno = Monograph.getAccession(refno);
        System.out.println(String.valueOf(accno) + "accno");
        Monograph.updatingAccessionStatus(accno, "A");
        Monograph.deleteMonoMast(refno);
        String bindNo = Serial.getBindno(refno);
        System.out.println(String.valueOf(bindNo) + "bindNo");
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BID0001", bindNo, id);
        return bSuccessful;
    }

    public static void deleteMonoMast(String refno) {
        String query = "DELETE FROM BIMAST WHERE BI01REFERENCE = '" + refno + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Object stmt = null;
                Object rs = null;
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                    conn.commit();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
                conn.commit();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getAccession(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT BI01ACCESSION FROM BIMAST WHERE BI01REFERENCE = '" + value + "'";
        System.out.println("SQL getAccession" + sSQLStmt);
        String accession = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    accession = Handler.ifIsNull(rs.getString("BI01ACCESSION"));
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
        return accession;
    }
}
