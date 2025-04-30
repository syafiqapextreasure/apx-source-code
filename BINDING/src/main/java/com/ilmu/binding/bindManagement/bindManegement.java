/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.bindManagement;

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

public class bindManegement {
    private String bindno;
    private String binder;
    private String title;
    private String callno;
    private String accession;
    private String status;
    private String sdate;
    private String expdate;
    private String orderno;

    public String getbindno() {
        return Handler.ifIsNull(this.bindno);
    }

    public String getbinder() {
        return Handler.ifIsNull(this.binder);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getcallno() {
        return Handler.ifIsNull(this.callno);
    }

    public String getaccession() {
        return Handler.ifIsNull(this.accession);
    }

    public String getbindNo() {
        return Handler.ifIsNull(this.status);
    }

    public String getsdate() {
        return Handler.ifIsNull(this.sdate);
    }

    public String getexpdate() {
        return Handler.ifIsNull(this.expdate);
    }

    public String getorderno() {
        return Handler.ifIsNull(this.orderno);
    }

    public bindManegement(String bindno, String binder, String title, String callno, String accession, String status, String sdate, String expdate, String orderno) {
        this.bindno = bindno;
        this.binder = binder;
        this.title = title;
        this.callno = callno;
        this.accession = accession;
        this.status = status;
        this.sdate = sdate;
        this.expdate = expdate;
        this.orderno = orderno;
    }

    public static List<bindManegement> getBindManagementTable(String type, String binder, String ctrlno, String startSentDate, String endSentDate, String checkbinder, String checkcontrolNo, String checkbindDate) {
        ArrayList<bindManegement> list = new ArrayList<bindManegement>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT BI01BINDNO, BI01BINDER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '090') AS CALLNUMBER, BI01ACCESSION, BI01STATUS, BI01DTSENT, BI01DTEXPECTED, BI01ORDERNO FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO WHERE BI01STATUS NOT IN ('R', 'C') AND ";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT BI01BINDNO, BI01BINDER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, BI01ACCESSION, BI01STATUS, BI01DTSENT, BI01DTEXPECTED, BI01ORDERNO FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO WHERE BI01STATUS NOT IN ('R', 'C') AND ";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT BI01BINDNO, BI01BINDER, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' LIMIT 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, BI01ACCESSION, BI01STATUS, BI01DTSENT, BI01DTEXPECTED, BI01ORDERNO FROM BIMAST LEFT JOIN BIINST ON BI01BINDNO = BI02BINDNO WHERE BI01STATUS NOT IN ('R', 'C') AND ";
        }
        if (type.equals("M")) {
            query = String.valueOf(query) + "BI01TYPE = 'M' ";
        } else if (type.equals("S")) {
            query = String.valueOf(query) + "BI01TYPE = 'S' ";
        }
        if (checkbinder.equals("Y")) {
            query = String.valueOf(query) + "AND UPPER(BI01BINDER) = UPPER('" + binder + "') ";
        }
        if (checkcontrolNo.equals("Y")) {
            query = String.valueOf(query) + "AND UPPER(BI01CTRLNO) = UPPER('" + ctrlno + "') ";
        }
        if (checkbindDate.equals("Y")) {
            if (startSentDate != "" && endSentDate != "") {
                System.out.println("inputStartDate and inputEndDate");
                query = String.valueOf(query) + "AND BI01DTSENT BETWEEN '" + startSentDate + "' AND '" + endSentDate + "' ";
            }
            if (startSentDate != "" && endSentDate == "") {
                System.out.println("inputStartDate");
                query = String.valueOf(query) + "AND BI01DTSENT >= '" + startSentDate + "' ";
            }
            if (startSentDate == "" && endSentDate != "") {
                System.out.println("inputEndDate");
                query = String.valueOf(query) + "AND BI01DTSENT <= '" + endSentDate + "' ";
            }
        }
        System.out.println("query getBindManagementTable : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> call = ISBD.getPunctuation("090");
                while (rs.next()) {
                    bindManegement loadtabledetail = new bindManegement(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.ifIsNull(rs.getString("BI01BINDER")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.ifIsNull(rs.getString("BI01ACCESSION")), Handler.ifIsNull(rs.getString("BI01STATUS")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))), Handler.ifIsNull(rs.getString("BI01ORDERNO")));
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

    public static boolean managementBind(String liferayLogin, String bindno, String accno, String type, int total, String order) throws SQLException, UnknownHostException {
        String gsModule;
        int i;
        boolean bSuccessful = false;
        String[] newsbindno = bindno.split(",");
        if (type.equals("M")) {
            String[] newsaccno = accno.split(",");
            i = 0;
            while (i < total) {
                System.out.println("value is :" + newsbindno[i] + "," + newsaccno[i]);
                bindManegement.updateMastStatus(newsbindno[i]);
                bindManegement.updateAccStatus(newsaccno[i]);
                gsModule = "BI";
                RecordTransaction.InsertAudit(gsModule, "BIU0004", newsbindno[i], liferayLogin);
                ++i;
            }
        }
        if (type.equals("S")) {
            String[] newsorder = order.split(",");
            i = 0;
            while (i < total) {
                System.out.println("value is :" + newsbindno[i] + "," + newsorder[i]);
                bindManegement.updateMastStatus(newsbindno[i]);
                bindManegement.updateIssueStatus(newsbindno[i]);
                gsModule = "BI";
                RecordTransaction.InsertAudit(gsModule, "BIU0004", newsbindno[i], liferayLogin);
                ++i;
            }
        }
        return bSuccessful;
    }

    public static boolean updateMastStatus(String bindno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("BI01BINDNO", bindno);
        valueStr.put("BI01STATUS", "C");
        String query = QueryBuilder.createUpdateQuery("BIMAST", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateAccStatus(String accno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", accno);
        valueStr.put("CT03STAT", "A");
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateIssueStatus(String bindno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE06BINDNO", bindno);
        valueStr.put("SE06ISSTAT", "R");
        String query = QueryBuilder.createUpdateQuery("SEISSU", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
