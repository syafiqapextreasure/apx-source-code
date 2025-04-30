/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Acc_Maint;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CTRetrieve {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String CT05SRAW = null;
    private String newData = null;
    private String GL47DESC = null;
    private String GL05DESC = null;
    private String CT03COPYNO = null;
    private String GL71DESC = null;
    private String CT03STAT = null;
    private String CT03VOLUME = null;
    private String GL10DESC = null;
    private String CT03MATNO = null;
    private String CT06MATNO = null;
    private String CT03DOCNO = null;
    private String GL17TAG = null;
    private String GL17DESC = null;
    private String GL17TABNAME = null;
    private String CT05HITS = null;
    private String CT05POINTER = null;
    private String CT03COND = null;
    private String CT03LOCA = null;
    private String CT03ICAT = null;
    private String CT03FOREX = null;
    private String CT03LCOST = null;
    private String CT03FCOST = null;
    private String CT03HCHAR = null;
    private String CT03SCHAR = null;
    private String CT03CLMHITS = null;
    private String CT03SMD = null;
    private String CT03VEND = null;
    private String CT03CIRHITS = null;
    private String CT03LASTACT = null;
    private String CT03BDATE = null;
    private String CT03DDATE = null;
    private String CT03PATR = null;
    private String CT03LASTID = null;
    private String CT03ORDER = null;
    private String CT03INVOICE = null;
    private String CT03INVDATE = null;
    private String CT03CRDATE = null;
    private String CT03RATE = null;
    private String CT03ONTHEFLY = null;
    private String CT03ORG = null;
    private String GL24DESC = null;
    private String GL06DESC = null;
    private String GL39NAME = null;
    private String AC03REFNO = null;
    private String GL24PRATE = null;
    private int Total;
    private String TotalDocNo;
    private String TotalStat;

    public CTRetrieve(String CT05SRAW) {
        this.CT05SRAW = CT05SRAW;
    }

    public CTRetrieve(String GL17TAG, String GL17DESC, String GL17TABNAME) {
        this.GL17TAG = GL17TAG;
        this.GL17DESC = GL17DESC;
        this.GL17TABNAME = GL17TABNAME;
    }

    public CTRetrieve(String newData, String CT05HITS, String CT06MATNO, String CT05POINTER) {
        this.newData = newData;
        this.CT05HITS = CT05HITS;
        this.CT06MATNO = CT06MATNO;
        this.CT05POINTER = CT05POINTER;
    }

    public CTRetrieve(String CT05SRAW, String CT06MATNO) {
        this.CT05SRAW = CT05SRAW;
        this.CT06MATNO = CT06MATNO;
    }

    public CTRetrieve(String CT03DOCNO, String CT03MATNO, String GL47DESC, String GL05DESC, String CT03COPYNO, String GL71DESC, String CT03STAT, String CT03VOLUME, String GL10DESC) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03MATNO = CT03MATNO;
        this.GL47DESC = GL47DESC;
        this.GL05DESC = GL05DESC;
        this.CT03COPYNO = CT03COPYNO;
        this.GL71DESC = GL71DESC;
        this.CT03STAT = CT03STAT;
        this.CT03VOLUME = CT03VOLUME;
        this.GL10DESC = GL10DESC;
    }

    public CTRetrieve(String CT03DOCNO, String GL47DESC, String GL05DESC, String CT03COPYNO, String GL71DESC, String CT03STAT, String CT03VOLUME, String GL10DESC) {
        this.CT03DOCNO = CT03DOCNO;
        this.GL47DESC = GL47DESC;
        this.GL05DESC = GL05DESC;
        this.CT03COPYNO = CT03COPYNO;
        this.GL71DESC = GL71DESC;
        this.CT03STAT = CT03STAT;
        this.CT03VOLUME = CT03VOLUME;
        this.GL10DESC = GL10DESC;
    }

    public CTRetrieve(String CT03DOCNO, String CT03ORG, String CT03MATNO, String CT03LOCA, String CT03ICAT, String CT03COND, String CT03BDATE, String CT03DDATE, String CT03FOREX, String CT03LCOST, String CT03FCOST, String CT03HCHAR, String CT03SCHAR, String CT03STAT, String CT03PATR, String CT03CLMHITS, String CT03CIRHITS, String CT03LASTACT, String CT03LASTID, String CT03SMD, String CT03ORDER, String CT03VEND, String CT03INVOICE, String CT03INVDATE, String CT03VOLUME, String CT03CRDATE, String CT03RATE, String CT03COPYNO, String CT03ONTHEFLY, String GL05DESC, String GL10DESC, String GL24DESC, String GL24PRATE, String GL47DESC, String GL06DESC, String GL39NAME, String CT05SRAW, String AC03REFNO) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03ORG = CT03ORG;
        this.CT03MATNO = CT03MATNO;
        this.CT03LOCA = CT03LOCA;
        this.CT03ICAT = CT03ICAT;
        this.CT03COND = CT03COND;
        this.CT03BDATE = CT03BDATE;
        this.CT03DDATE = CT03DDATE;
        this.CT03FOREX = CT03FOREX;
        this.CT03LCOST = CT03LCOST;
        this.CT03FCOST = CT03FCOST;
        this.CT03HCHAR = CT03HCHAR;
        this.CT03SCHAR = CT03SCHAR;
        this.CT03STAT = CT03STAT;
        this.CT03PATR = CT03PATR;
        this.CT03CLMHITS = CT03CLMHITS;
        this.CT03CIRHITS = CT03CIRHITS;
        this.CT03LASTACT = CT03LASTACT;
        this.CT03LASTID = CT03LASTID;
        this.CT03SMD = CT03SMD;
        this.CT03ORDER = CT03ORDER;
        this.CT03VEND = CT03VEND;
        this.CT03INVOICE = CT03INVOICE;
        this.CT03INVDATE = CT03INVDATE;
        this.CT03VOLUME = CT03VOLUME;
        this.CT03CRDATE = CT03CRDATE;
        this.CT03RATE = CT03RATE;
        this.CT03COPYNO = CT03COPYNO;
        this.CT03ONTHEFLY = CT03ONTHEFLY;
        this.GL05DESC = GL05DESC;
        this.GL10DESC = GL10DESC;
        this.GL24DESC = GL24DESC;
        this.GL24PRATE = GL24PRATE;
        this.GL47DESC = GL47DESC;
        this.GL06DESC = GL06DESC;
        this.GL39NAME = GL39NAME;
        this.CT05SRAW = CT05SRAW;
        this.AC03REFNO = AC03REFNO;
    }

    public CTRetrieve(int Total, String TotalDocNo, String TotalStat) {
        this.Total = Total;
        this.TotalDocNo = TotalDocNo;
        this.TotalStat = TotalStat;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public String getnewData() {
        return Handler.ifIsNull(this.newData);
    }

    public String getCT03MATNO() {
        return Handler.ifIsNull(this.CT03MATNO);
    }

    public String getGL47DESC() {
        return Handler.ifIsNull(this.GL47DESC);
    }

    public String getGL05DESC() {
        return Handler.ifIsNull(this.GL05DESC);
    }

    public String getCT03COPYNO() {
        return Handler.ifIsNull(this.CT03COPYNO);
    }

    public String getGL71DESC() {
        return Handler.ifIsNull(this.GL71DESC);
    }

    public String getCT03STAT() {
        return Handler.ifIsNull(this.CT03STAT);
    }

    public String getCT03VOLUME() {
        return Handler.ifIsNull(this.CT03VOLUME);
    }

    public String getGL10DESC() {
        return Handler.ifIsNull(this.GL10DESC);
    }

    public String getCT03DOCNO() {
        return Handler.CT03DOCNO(this.CT03DOCNO);
    }

    public String getGL17TAG() {
        return Handler.ifIsNull(this.GL17TAG);
    }

    public String getGL17DESC() {
        return Handler.ifIsNull(this.GL17DESC);
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getCT05HITS() {
        return this.CT05HITS;
    }

    public String getCT05POINTER() {
        return this.CT05POINTER;
    }

    public String getCT06MATNO() {
        return Handler.ifIsNull(this.CT06MATNO);
    }

    public String getCT03LOCA() {
        return this.CT03LOCA;
    }

    public String getCT03ICAT() {
        return this.CT03ICAT;
    }

    public String getCT03FOREX() {
        return this.CT03FOREX;
    }

    public String getCT03LCOST() {
        return CTRetrieve.decimalConversion(this.CT03LCOST);
    }

    public String getCT03FCOST() {
        return CTRetrieve.decimalConversion(this.CT03FCOST);
    }

    public String getCT03HCHAR() {
        return CTRetrieve.decimalConversion(this.CT03HCHAR);
    }

    public String getCT03SCHAR() {
        return CTRetrieve.decimalConversion(this.CT03SCHAR);
    }

    public String getCT03CLMHITS() {
        return CTRetrieve.ifIsNull(this.CT03CLMHITS);
    }

    public String getCT03SMD() {
        return Handler.ifIsNull(this.CT03SMD);
    }

    public String getCT03VEND() {
        return CTRetrieve.ifIsNull(this.CT03VEND);
    }

    public String getCT03CIRHITS() {
        return CTRetrieve.ifIsNull(this.CT03CIRHITS);
    }

    public String getCT03LASTACT() {
        return CTRetrieve.dateConverter(this.CT03LASTACT);
    }

    public String getCT03COND() {
        return this.CT03COND;
    }

    public String getCT03BDATE() {
        return CTRetrieve.dateConverter(this.CT03BDATE);
    }

    public String getCT03DDATE() {
        return CTRetrieve.dateConverter(this.CT03DDATE);
    }

    public String getCT03PATR() {
        return CTRetrieve.ifIsNull(this.CT03PATR);
    }

    public String getCT03LASTID() {
        return CTRetrieve.ifIsNull(this.CT03LASTID);
    }

    public String getCT03ORDER() {
        return CTRetrieve.ifIsNull(this.CT03ORDER);
    }

    public String getCT03INVOICE() {
        return CTRetrieve.ifIsNull(this.CT03INVOICE);
    }

    public String getCT03INVDATE() {
        return CTRetrieve.dateConverter(this.CT03INVDATE);
    }

    public String getCT03CRDATE() {
        return CTRetrieve.dateConverter(this.CT03CRDATE);
    }

    public String getCT03RATE() {
        return CTRetrieve.ifIsNull(this.CT03RATE);
    }

    public String getCT03ONTHEFLY() {
        return CTRetrieve.checkingChk(this.CT03ONTHEFLY);
    }

    public String getCT03ORG() {
        return CTRetrieve.ifIsNull(this.CT03ORG);
    }

    public String getGL24DESC() {
        return CTRetrieve.ifIsNull(this.GL24DESC);
    }

    public String getGL24PRATE() {
        return CTRetrieve.ifIsNull(this.GL24PRATE);
    }

    public String getGL06DESC() {
        return CTRetrieve.ifIsNull(this.GL06DESC);
    }

    public String getGL39NAME() {
        return CTRetrieve.ifIsNull(this.GL39NAME);
    }

    public String getAC03REFNO() {
        return CTRetrieve.ifIsNull(this.AC03REFNO);
    }

    public int getTotal() {
        return this.Total;
    }

    public String getTotalDocNo() {
        return this.TotalDocNo;
    }

    public String getTotalStat() {
        return this.TotalStat;
    }

    public static String checkingChk(String nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append("N");
        } else {
            result.append(nullValue);
        }
        return result.toString();
    }

    public static String ifIsNull(String nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append(" ");
        } else {
            result.append(nullValue);
        }
        return result.toString();
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

    public static String decimalConversion(String cost) {
        StringBuilder result = new StringBuilder();
        if (cost == null) {
            result.append(" ");
        } else {
            double f = Double.parseDouble(cost);
            result.append(String.format("%.2f", f));
        }
        return result.toString();
    }

    public static Double ifDoubleIsNull(Double nullValue) {
        StringBuilder result = new StringBuilder();
        if (nullValue == null) {
            result.append(" ");
        }
        double newValue = Double.parseDouble(result.toString());
        return newValue;
    }

    public static String rawToDisplayFormat(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String[] rawArray = raw.split("\\|");
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }

    public static List<CTRetrieve> getTagTabName() {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = " SELECT GL17TAG, GL17DESC, GL17TABNAME FROM GLTAGP WHERE GL17MARC='U'";
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            try {
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve CT_getTagTabName = new CTRetrieve(rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17TABNAME"));
                    list.add(CT_getTagTabName);
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

    public static List<CTRetrieve> CT_SearchByMatno(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT03DOCNO, CT03MATNO, GL47DESC, GL05DESC, CT03COPYNO, GL71DESC, GL36DESC, CT03VOLUME , GL10DESC from CTDOCM LEFT JOIN GLSMD ON CT03SMD = GL47SMD LEFT JOIN GLDOCS ON GL36STAT=CT03STAT LEFT JOIN GLLOCA ON  CT03LOCA=GL05LOCA LEFT JOIN GLBRNC ON GL05BRNC=GL71BRNC LEFT JOIN GLICAT ON CT03ICAT=GL10ICAT WHERE CT03DOCNO= '" + controlNoInput + "'";
        System.out.println(query);
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            try {
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                System.out.print(controlNoInput);
                while (rs.next()) {
                    CTRetrieve CT_SearchByMatno = new CTRetrieve(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("GL47DESC"), rs.getString("GL05DESC"), rs.getString("CT03COPYNO"), rs.getString("GL71DESC"), String.valueOf(rs.getString("CT03VOLUME")) + " ", rs.getString("GL36DESC"), rs.getString("GL10DESC"));
                    list.add(CT_SearchByMatno);
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

    public static void CT_deleteAccMaint(String controlNo) {
        String query = "DELETE FROM CTDOCM WHERE CT03DOCNO = '" + controlNo + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }

    public static boolean CT_isDeletable(String accNo) {
        String query = "SELECT COUNT(*) AS Count FROM CTDOCM WHERE CT03DOCNO = '" + accNo + "' AND CT03STAT = 'F'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    deletable = true;
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
        return deletable;
    }

    public static List<CTRetrieve> CT_RetrieveDetails(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT03DOCNO, CT03MATNO, GL47DESC, GL05DESC, CT03COPYNO, GL71DESC, GL36DESC, CT03VOLUME , GL10DESC from CTDOCM LEFT JOIN GLDOCS ON GL36STAT=CT03STAT LEFT JOIN GLSMD ON CT03SMD = GL47SMD LEFT JOIN GLLOCA ON  CT03LOCA=GL05LOCA LEFT JOIN GLBRNC ON GL05BRNC=GL71BRNC LEFT JOIN GLICAT ON CT03ICAT=GL10ICAT WHERE CT03MATNO= '" + controlNoInput + "'";
        System.out.println(query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve CT_SearchByMatno = new CTRetrieve(rs.getString("CT03DOCNO"), rs.getString("CT03MATNO"), rs.getString("GL47DESC"), rs.getString("GL05DESC"), rs.getString("CT03COPYNO"), rs.getString("GL71DESC"), String.valueOf(rs.getString("CT03VOLUME")) + " ", rs.getString("GL36DESC"), rs.getString("GL10DESC"));
                    list.add(CT_SearchByMatno);
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

    public static List<CTRetrieve> CT_SearchByAccno(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT05SRAW from CTTITL, CTPONT where CT06POINTER=CT05POINTER and CT06MATNO= '" + controlNoInput + "' and ct06tag='245'";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve CT_SearchByAccno = new CTRetrieve(Handler.getSubfield(rs.getString("CT05SRAW"), isbd));
                    list.add(CT_SearchByAccno);
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

    public static List<CTRetrieve> CT_getTitle(String title) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT05SRAW from CTTITL, CTPONT, CTDOCM where CT06POINTER=CT05POINTER and CT03MATNO=CT06MATNO and CT03DOCNO='" + title + "' and ct06tag='245'";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve CT_SearchByMatno = new CTRetrieve(Handler.getSubfield(rs.getString("CT05SRAW"), isbd));
                    list.add(CT_SearchByMatno);
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

    public static CTRetrieve SE01_getDetailsByControlNo(String controlNo) {
        String query = "Select CT03DOCNO, GL47DESC, GL05DESC, CT03COPYNO, GL71DESC, CT03STAT, CT03VOLUME, GL10DESC from CTDOCM, GLSMD, GLLOCA, GLBRNC, GLICAT where CT03ICAT=GL10ICAT and GL05BRNC=GL71BRNC and CT03SMD = GL47SMD and CT03LOCA=GL05LOCA and CT03MATNO='" + controlNo + "' ";
        CTRetrieve newSE01_getDetailsByControlNo = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_getDetailsByControlNo = new CTRetrieve(rs.getString("CT03DOCNO"), rs.getString("GL47DESC"), rs.getString("GL05DESC"), rs.getString("CT03COPYNO"), rs.getString("GL71DESC"), Handler.ifIsNull(rs.getString("CT03STAT")), Handler.ifIsNull(rs.getString("CT03VOLUME")), Handler.ifIsNull(rs.getString("GL10DESC")));
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
        return newSE01_getDetailsByControlNo;
    }

    public static List<CTRetrieve> CT_GetListBy(String marcTag, String tableName, String condition) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "SELECT DISTINCT CT05SRAW, CT05HITS, CT06POINTER, (SELECT STUFF((SELECT ',' + CT06MATNO FROM " + tableName + " " + "LEFT JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER WHERE CT06STATUS = 'A' AND CT06TAG = '" + marcTag + "' AND CT05SCONV LIKE '" + condition + "%' " + "FOR XML PATH('')) ,1,1,'') AS CT06MATNO) AS CT06MATNO FROM " + tableName + " " + "LEFT JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + " " + "WHERE CT06STATUS = 'A' AND CT06TAG = '" + marcTag + "' AND CT05HITS!='1' and CT05SCONV LIKE '" + condition + "%' " + "UNION ALL select CT05SRAW, CT05HITS, CT06POINTER, CT06MATNO from " + tableName + " " + "LEFT JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "WHERE CT06STATUS = 'A' AND CT06TAG = '" + marcTag + "' AND  CT05HITS='1'  and CT05SCONV LIKE '" + condition + "%'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve newCT_GetListBy = new CTRetrieve(rs.getString("CT05SRAW"), rs.getString("CT05HITS"), rs.getString("CT06MATNO"), rs.getString("CT06POINTER"));
                    list.add(newCT_GetListBy);
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

    public static List<CTRetrieve> CT_GetTitle(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT05SRAW from CTTITL, CTPONT where CT06POINTER=CT05POINTER and CT06MATNO= '" + controlNoInput + "'";
        System.out.println(query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve CT_SearchByAccno = new CTRetrieve(rs.getString("CT05SRAW"));
                    list.add(CT_SearchByAccno);
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

    public static List<CTRetrieve> CT_GetCallNo(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT05SRAW ,CT06MATNO from CTCALL, CTPONT where CT06POINTER=CT05POINTER and CT06MATNO= '" + controlNoInput + "'";
        System.out.println(query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                System.out.print(controlNoInput);
                while (rs.next()) {
                    CTRetrieve CT_SearchByAccno = new CTRetrieve(rs.getString("CT05SRAW"), rs.getString("CT06MATNO"));
                    list.add(CT_SearchByAccno);
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

    public static List<CTRetrieve> CT_GetPubl(String controlNoInput) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "  Select CT05SRAW from CTPUBL, CTPONT where CT06POINTER=CT05POINTER and CT06MATNO= '" + controlNoInput + "'";
        System.out.println(query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                System.out.print(controlNoInput);
                while (rs.next()) {
                    CTRetrieve CT_SearchByAccno = new CTRetrieve(rs.getString("CT05SRAW"));
                    list.add(CT_SearchByAccno);
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

    public static List<CTRetrieve> SE01_GetListBy(String marcTag, String tableName, String condition) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "SELECT DISTINCT CT05SRAW, CT05HITS, CT06POINTER, CT06MATNO FROM " + tableName + " " + "LEFT JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + " " + "WHERE CT06STATUS = 'A' AND CT06TAG = '" + marcTag + "' AND CT05SCONV LIKE '" + condition + "%' ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve newSE01_GetListBy = new CTRetrieve(rs.getString("CT05SRAW"), rs.getString("CT05HITS"), rs.getString("CT06MATNO"), rs.getString("CT06POINTER"));
                    list.add(newSE01_GetListBy);
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

    public static List<CTRetrieve> searchByTitle(String criteria) {
        return CTRetrieve.CT_GetListBy("245", "CTTITL", criteria);
    }

    public static List<CTRetrieve> searchByName(String criteria) {
        return CTRetrieve.CT_GetListBy("100", "CTAUTH", criteria);
    }

    public static List<CTRetrieve> searchBySubject(String criteria) {
        return CTRetrieve.CT_GetListBy("650", "CTSUBJ", criteria);
    }

    public static List<CTRetrieve> searchByPubl(String criteria) {
        return CTRetrieve.CT_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<CTRetrieve> searchByPlaceOfPubl(String criteria) {
        return CTRetrieve.CT_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<CTRetrieve> searchByYearOfPubl(String criteria) {
        return CTRetrieve.CT_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<CTRetrieve> searchBySeries(String criteria) {
        return CTRetrieve.CT_GetListBy("440", "CTSERI", criteria);
    }

    public static List<CTRetrieve> searchByCallNo(String criteria) {
        return CTRetrieve.CT_GetListBy("090", "CTCALL", criteria);
    }

    public static List<CTRetrieve> searchByISBN(String criteria) {
        return CTRetrieve.CT_GetListBy("020", "CTINDX", criteria);
    }

    public static List<CTRetrieve> searchByISSN(String criteria) {
        return CTRetrieve.CT_GetListBy("022", "CTINDX", criteria);
    }

    public static List<CTRetrieve> searchByNoteArea(String criteria) {
        return CTRetrieve.CT_GetListBy("500", "CTINDX", criteria);
    }

    public static CTRetrieve CT_getDetailsBy(String matNo) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT CT03DOCNO,CT03ORG,CT03MATNO,CT03LOCA,CT03ICAT ,CT03COND,CT03BDATE,CT03BTIME,CT03DDATE,CT03DTIME,CT03FOREX ,CT03LCOST,CT03FCOST,CT03HCHAR,CT03SCHAR,CT03STAT,CT03PATR ,CT03CLMHITS,CT03CIRHITS,CT03LASTACT,CT03PTYPE,CT03SMD,CT03ORDER ,CT03VEND,CT03INVOICE,CT03INVDATE,CT03VOLUME,CT03CRDATE,CT03CREBY, CT03LASTID ,CT03RATE,CT03COPYNO,CT03ONTHEFLY, GL05DESC, GL10DESC, GL24DESC, GL24PRATE, GL47DESC, GL06DESC, GL39NAME, CT05SRAW, AC03REFNO FROM CTDOCM LEFT JOIN GLLOCA ON CT03LOCA=GL05LOCA LEFT JOIN GLICAT ON CT03ICAT=GL10ICAT LEFT JOIN GLFORX ON CT03FOREX=GL24FOREX LEFT JOIN GLSMD ON CT03SMD=GL47SMD LEFT JOIN GLCOND ON CT03COND=GL06COND LEFT JOIN GLVEND ON CT03VEND=GL39CODE LEFT JOIN CTPONT ON CT03MATNO=CT06MATNO LEFT JOIN CTTITL ON CT06POINTER=CT05POINTER LEFT JOIN ACORDD ON AC03ORDER=CT03ORDER WHERE CT03DOCNO = '" + matNo + "' and CT06TAG='245'");
        CTRetrieve updateAccMaint = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query.toString());
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    updateAccMaint = new CTRetrieve(rs.getString("CT03DOCNO"), rs.getString("CT03ORG"), rs.getString("CT03MATNO"), rs.getString("CT03LOCA"), rs.getString("CT03ICAT"), rs.getString("CT03COND"), rs.getString("CT03BDATE"), rs.getString("CT03DDATE"), rs.getString("CT03FOREX"), rs.getString("CT03LCOST"), rs.getString("CT03FCOST"), rs.getString("CT03HCHAR"), rs.getString("CT03SCHAR"), rs.getString("CT03STAT"), rs.getString("CT03PATR"), rs.getString("CT03CLMHITS"), rs.getString("CT03CIRHITS"), rs.getString("CT03LASTACT"), rs.getString("CT03LASTID"), rs.getString("CT03SMD"), rs.getString("CT03ORDER"), rs.getString("CT03VEND"), rs.getString("CT03INVOICE"), rs.getString("CT03INVDATE"), rs.getString("CT03VOLUME"), rs.getString("CT03CRDATE"), rs.getString("CT03RATE"), rs.getString("CT03COPYNO"), rs.getString("CT03ONTHEFLY"), rs.getString("GL05DESC"), rs.getString("GL10DESC"), rs.getString("GL24DESC"), rs.getString("GL24PRATE"), rs.getString("GL47DESC"), rs.getString("GL06DESC"), rs.getString("GL39NAME"), Handler.getSubfield(rs.getString("CT05SRAW"), isbd), rs.getString("AC03REFNO"));
                    System.out.println("Currency" + rs.getString("CT03FOREX"));
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
        return updateAccMaint;
    }

    public static List<CTRetrieve> DisplayAll(String matNo) {
        ArrayList<CTRetrieve> list = new ArrayList<CTRetrieve>();
        String query = "Select Count(CT03DOCNO) AS TotalNumber, CT03DOCNO, GL36DESC FROM CTDOCM, GLDOCS WHERE CT03STAT=GL36STAT AND CT03MATNO = '" + matNo + "' " + "GROUP BY CT03DOCNO, GL36DESC";
        System.out.println("Total" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CTRetrieve newCT_DisplayAll = new CTRetrieve(Integer.parseInt(rs.getString("TotalNumber")), rs.getString("CT03DOCNO"), "[" + rs.getString("GL36DESC") + "]\n");
                    list.add(newCT_DisplayAll);
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

    public static CTRetrieve CT_AccMaint(String CT03DOCNO, String CT03MATNO, String CT03LOCA, String CT03ICAT, String CT03VEND, String CT03COND, String CT03INVOICE, String CT03SMD, String CT03INVDATE, String currency, String CT03COPYNO, String CT03VOL, String CT03RATE, String copyType, String onthefly, String foreignCost, String localCost, String sCost, String hCost) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        valueStr.put("CT03DOCNO", CT03DOCNO);
        valueStr.put("CT03MATNO", CT03MATNO);
        valueStr.put("CT03LOCA", CT03LOCA);
        valueStr.put("CT03ICAT", CT03ICAT);
        valueStr.put("CT03VEND", CT03VEND);
        valueStr.put("CT03COND", CT03COND);
        valueStr.put("CT03INVOICE", CT03INVOICE);
        valueStr.put("CT03INVDATE", DateTime.displayToDBFormat(CT03INVDATE));
        valueStr.put("CT03COPYNO", CT03COPYNO);
        valueStr.put("CT03ONTHEFLY", onthefly);
        valueStr.put("CT03VOLUME", CT03VOL);
        valueStr.put("CT03ORG", copyType);
        valueStr.put("CT03STAT", "F");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        valueStr.put("CT03CRDATE", formatter.format(date).toString());
        valueDouble.put("CT03LCOST", Handler.parseDecimal(localCost));
        valueDouble.put("CT03FCOST", Handler.parseDecimal(foreignCost));
        valueDouble.put("CT03HCHAR", Handler.parseDecimal(hCost));
        valueDouble.put("CT03SCHAR", Handler.parseDecimal(sCost));
        valueDouble.put("CT03RATE", Handler.parseDecimal(CT03RATE));
        if (!CT03SMD.equals("")) {
            valueStr.put("CT03SMD", CT03SMD);
        }
        if (!currency.equals("")) {
            valueStr.put("CT03FOREX", currency);
        }
        String query = QueryBuilder.createInsertQuery("CTDOCM", valueStr, null, valueDouble);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static CTRetrieve CT_InsertBO(String CT04MATNO, String CT04MARC, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, String CT04STATUS, int CT04COUNTER) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("CT04MATNO", CT04MATNO);
        valueStr.put("CT04MARC", CT04MARC);
        valueStr.put("CT04TAG", CT04TAG);
        valueStr.put("CT04INDI1", CT04INDI1);
        valueStr.put("CT04INDI2", CT04INDI2);
        valueStr.put("CT04RAW", CT04RAW);
        valueStr.put("CT04STATUS", CT04STATUS);
        valueInt.put("CT04COUNTER", CT04COUNTER);
        String query = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CT_editAccMaint(String CT03DOCNO, String CT03MATNO, String CT03LOCA, String CT03ICAT, String CT03VEND, String CT03COND, String CT03INVOICE, String CT03SMD, String CT03INVDATE, String currency, String CT03COPYNO, String CT03VOL, String CT03RATE, String copyType, String onthefly, String foreignCost, String localCost, String sCost, String hCost) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", CT03DOCNO);
        valueStr.put("CT03MATNO", CT03MATNO);
        valueStr.put("CT03LOCA", CT03LOCA);
        valueStr.put("CT03ICAT", CT03ICAT);
        valueStr.put("CT03VEND", Handler.convUpperCase(CT03VEND.trim()));
        valueStr.put("CT03COND", CT03COND);
        valueStr.put("CT03INVOICE", CT03INVOICE);
        valueStr.put("CT03INVDATE", DateTime.displayToDBFormat(CT03INVDATE));
        System.out.println("dsss");
        valueStr.put("CT03COPYNO", CT03COPYNO);
        valueStr.put("CT03ONTHEFLY", onthefly);
        valueStr.put("CT03VOLUME", CT03VOL);
        valueStr.put("CT03ORG", copyType);
        valueDouble.put("CT03LCOST", Handler.parseDecimal(localCost));
        valueDouble.put("CT03FCOST", Handler.parseDecimal(foreignCost));
        valueDouble.put("CT03HCHAR", Handler.parseDecimal(hCost));
        valueDouble.put("CT03SCHAR", Handler.parseDecimal(sCost));
        valueDouble.put("CT03RATE", Handler.parseDecimal(CT03RATE));
        if (!CT03SMD.equals("")) {
            valueStr.put("CT03SMD", CT03SMD);
        }
        if (!currency.equals("")) {
            valueStr.put("CT03FOREX", currency);
        }
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, valueDouble, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean isExistControlNo(String controlNo) {
        String query = "SELECT COUNT(*) AS Count FROM SESERM WHERE SE01MATNO = '" + controlNo + "' ";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean accnoExist(String CT03DOCNO) {
        String query = "Select COUNT(CT03DOCNO) AS Count FROM CTDOCM WHERE CT03DOCNO='" + CT03DOCNO + "'";
        boolean exist = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean CICIRCExist(String CI03DOCNO) {
        String query = "Select COUNT(CI02DOCNO) AS Count FROM CICIRC WHERE CI02DOCNO='" + CI03DOCNO + "'";
        boolean exist = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }
}
