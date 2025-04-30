/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.checkin;

import com.ilmu.binding.maintenance.Monograph;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bindingCheckin {
    private String bindno;
    private String title;
    private String volfrom;
    private String volto;
    private String issfrom;
    private String issto;
    private String copy;
    private String bindname;
    private String officename;
    private String dsent;
    private String accno;
    private String type;
    private String ctrlno;

    public String getbindno() {
        return Handler.ifIsNull(this.bindno);
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

    public String getissto() {
        return Handler.ifIsNull(this.issto);
    }

    public String getcopy() {
        return Handler.ifIsNull(this.copy);
    }

    public String getbindname() {
        return Handler.ifIsNull(this.bindname);
    }

    public String getofficename() {
        return Handler.ifIsNull(this.officename);
    }

    public String getdsent() {
        return Handler.ifIsNull(this.dsent);
    }

    public String getaccno() {
        return Handler.ifIsNull(this.accno);
    }

    public String gettype() {
        return Handler.ifIsNull(this.type);
    }

    public String getctrlno() {
        return Handler.ifIsNull(this.ctrlno);
    }

    public bindingCheckin(String bindno, String title, String volfrom, String volto, String issfrom, String issto, String copy, String bindname, String officename, String dsent, String accno, String type, String ctrlno) {
        this.bindno = bindno;
        this.title = title;
        this.volfrom = volfrom;
        this.volto = volto;
        this.issfrom = issfrom;
        this.issto = issto;
        this.copy = copy;
        this.bindname = bindname;
        this.officename = officename;
        this.dsent = dsent;
        this.accno = accno;
        this.type = type;
        this.ctrlno = ctrlno;
    }

    public static List<bindingCheckin> tablecheckin(String input_criteria, String search_type, String inputStartDate, String inputEndDate) {
        ArrayList<bindingCheckin> list = new ArrayList<bindingCheckin>();
        String query = "SELECT BI01BINDNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, BI01VOLFROM, BI01VOLTO, BI01ISSFROM, BI01ISSTO, BI01COPY, GL39NAME, GL14NAME, BI01DTSENT, BI01ACCESSION, BI01TYPE, BI01CTRLNO FROM BIMAST LEFT JOIN GLVEND ON BI01BINDER = GL39CODE LEFT JOIN GLPATR ON GL14PATR = BI01OFFICER LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO WHERE BI01STATUS = 'S' AND ";
        if (search_type.equals("issn")) {
            query = String.valueOf(query) + "UPPER(BI02ISSN) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("binder")) {
            query = String.valueOf(query) + "UPPER(BI01BINDER) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("accno")) {
            query = String.valueOf(query) + "UPPER(BI01ACCESSION) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("refno")) {
            query = String.valueOf(query) + "UPPER(BI01REFERENCE) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("year")) {
            query = String.valueOf(query) + "BI01YEAR = '" + input_criteria + "'";
        } else if (search_type.equals("ctrlno")) {
            query = String.valueOf(query) + "UPPER(BI01CTRLNO) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("datesent")) {
            if (inputStartDate != "" && inputEndDate != "") {
                System.out.println("inputStartDate and inputEndDate");
                query = String.valueOf(query) + "BI01DTSENT BETWEEN '" + inputStartDate + "' AND '" + inputEndDate + "' ";
            }
            if (inputStartDate != "" && inputEndDate == "") {
                System.out.println("inputStartDate");
                query = String.valueOf(query) + "BI01DTSENT >= '" + inputStartDate + "' ";
            }
            if (inputStartDate == "" && inputEndDate != "") {
                System.out.println("inputEndDate");
                query = String.valueOf(query) + "BI01DTSENT <= '" + inputEndDate + "' ";
            }
        }
        System.out.println("query tablecheckin : " + query);
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
                    bindingCheckin loadtabledetail = new bindingCheckin(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("BI01VOLFROM")), Handler.ifIsNull(rs.getString("BI01VOLTO")), Handler.ifIsNull(rs.getString("BI01ISSFROM")), Handler.ifIsNull(rs.getString("BI01ISSTO")), Handler.ifIsNull(rs.getString("BI01COPY")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(rs.getString("BI01ACCESSION")), Handler.ifIsNull(rs.getString("BI01TYPE")), Handler.ifIsNull(rs.getString("BI01CTRLNO")));
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

    public static boolean checkinMonograph(String bindno, String accno, String liferayLogin) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        bindingCheckin.updateMast(bindno);
        bindingCheckin.updateCTDOM(accno);
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BIU0003", bindno, liferayLogin);
        return bSuccessful;
    }

    public static boolean updateMast(String bindno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        condition.put("BI01BINDNO", bindno);
        valueStr.put("BI01DTRETURN", date.format(now));
        valueStr.put("BI01STATUS", "R");
        String query = QueryBuilder.createUpdateQuery("BIMAST", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateCTDOM(String accno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", accno);
        valueStr.put("CT03STAT", "F");
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean checkinSerial(String bindno, String liferayLogin, String createAcc, String checkCtrlno, String checkacc, String checkSMD, String checkicat, String checkloca, String checkcon, String checkfprice, String checklprice, String checkvol, String checkcopy, String checkspine) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        if (createAcc.equals("NO")) {
            bindingCheckin.updateMast(bindno);
            bindingCheckin.updateSEISSUE(bindno);
        }
        if (createAcc.equals("YES")) {
            bindingCheckin.updateMast(bindno);
            bindingCheckin.updateSEISSUE(bindno);
            bindingCheckin.InsertAccession(checkCtrlno, checkacc, checkSMD, checkicat, checkloca, checkcon, checkfprice, checklprice, checkvol, checkcopy, checkspine, liferayLogin);
        }
        String gsModule = "BI";
        RecordTransaction.InsertAudit(gsModule, "BIU0003", bindno, liferayLogin);
        return bSuccessful;
    }

    public static boolean updateSEISSUE(String bindno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE06BINDNO", bindno);
        valueStr.put("SE06ISSTAT", "B");
        String query = QueryBuilder.createUpdateQuery("SEISSU", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean InsertAccession(String checkCtrlno, String checkacc, String checkSMD, String checkicat, String checkloca, String checkcon, String checkfprice, String checklprice, String checkvol, String checkcopy, String checkspine, String liferayLogin) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
        LocalDateTime now = LocalDateTime.now();
        valueStr.put("CT03DOCNO", checkacc);
        valueStr.put("CT03ORG", "Y");
        valueStr.put("CT03MATNO", checkCtrlno);
        valueStr.put("CT03LOCA", checkloca);
        valueStr.put("CT03ICAT", checkicat);
        valueStr.put("CT03COND", checkcon);
        valueStr.put("CT03LCOST", checklprice);
        valueStr.put("CT03FCOST", checkfprice);
        valueStr.put("CT03STAT", "F");
        valueStr.put("CT03SMD", checkSMD);
        valueStr.put("CT03VOLUME", checkvol);
        valueStr.put("CT03CRDATE", date.format(now));
        valueStr.put("CT03CREBY", liferayLogin);
        valueStr.put("CT03COPYNO", checkcopy);
        String query = QueryBuilder.createInsertQuery("CTDOCM", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static String getAccession() throws UnknownHostException {
        int getVal = Monograph.Get98VALUE("ACCNO");
        System.out.println(String.valueOf(getVal) + "getVal");
        int newgetVal = new Integer(getVal + 1);
        bindingCheckin.updatingGLNUMB(newgetVal, "ACCNO");
        String getValConvert = String.format("%010d", newgetVal);
        System.out.println("getValConvert" + getValConvert);
        return getValConvert;
    }

    public static boolean updatingGLNUMB(int val, String func) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", func);
        valueInt.put("GL98VALUE", val);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
