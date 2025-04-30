/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Weeding;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Weeding {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String WE03WDATE = null;
    public String WE03TITL = null;
    public String WE03DOCNO = null;
    private String CT02IDXDATE = null;
    private String CT02IDXBY = null;
    private String CT02IDXTIME = null;
    private String CT02TYPE = null;
    private String CT02MATNO = null;
    private String CT02CREDATE = null;
    private String CT02STATUS = null;
    private String CT02CRETIME = null;
    private String CT02CREBY = null;

    public Weeding(String WE03DOCNO, String WE03TITL, String WE03WDATE) {
        this.WE03DOCNO = WE03DOCNO;
        this.WE03TITL = WE03TITL;
        this.WE03WDATE = WE03WDATE;
    }

    public Weeding(String CT02MATNO, String CT02STATUS, String CT02CREDATE, String CT02CREBY, String CT02CRETIME, String CT02TYPE, String CT02IDXDATE, String CT02IDXBY, String CT02IDXTIME) {
        this.CT02MATNO = CT02MATNO;
        this.CT02STATUS = CT02STATUS;
        this.CT02CREDATE = CT02CREDATE;
        this.CT02CREBY = CT02CREBY;
        this.CT02CRETIME = CT02CRETIME;
        this.CT02TYPE = CT02TYPE;
        this.CT02IDXBY = CT02IDXBY;
        this.CT02IDXDATE = CT02IDXDATE;
        this.CT02IDXTIME = CT02IDXTIME;
    }

    public String getWE03WDATE() {
        return this.WE03WDATE;
    }

    public String getWE03TITL() {
        return this.WE03TITL;
    }

    public String getWE03DOCNO() {
        return this.WE03DOCNO;
    }

    public String getCT02MATNO() {
        return this.CT02MATNO;
    }

    public String getCT02STATUS() {
        return this.CT02STATUS;
    }

    public String getCT02TYPE() {
        return this.CT02TYPE;
    }

    public String getCT02IDXDATE() {
        return GeneralUtility.StrToDate(this.CT02IDXDATE);
    }

    public String getCT02IDXTIME() {
        return GeneralUtility.StrToTime2(this.CT02IDXTIME);
    }

    public String getCT02IDXBY() {
        return this.CT02IDXBY;
    }

    public String getCT02CREDATE() {
        return GeneralUtility.StrToDate(this.CT02CREDATE);
    }

    public String getCT02CRTIME() {
        return GeneralUtility.StrToTime2(this.CT02CRETIME);
    }

    public String getCT02CREBY() {
        return this.CT02CREBY;
    }

    public static boolean rcrdExist_CTWORK(String CT04MATNO) {
        String query = "SELECT CT04MATNO FROM CTWORK WHERE CT04MATNO='" + Handler.replaceWithA(CT04MATNO) + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {
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

    public static void updateCTMATM(String CT02MATNO) {
        String query = "UPDATE CTMATM SET CT02STATUS='D', CT02DLTDATE='" + DateTime.getTodayDate() + "', " + "CT02DLTIME='" + DateTime.getTodayTime() + "'WHERE CT02MATNO = '" + CT02MATNO + "'";
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

    public static void deleteCTDOCMByMATNO(String matno) {
        String query = "DELETE FROM CTPONT WHERE CT06MATNO = '" + matno + "'";
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            PreparedStatement delete = c.prepareStatement(query);
            delete.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean rcrdExist_CTMATM(String CT03MATNO) {
        String query = "SELECT COUNT(*) AS CT03MATNO FROM CTDOCM WHERE CT03MATNO='" + CT03MATNO + "' and CT03STAT='A'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("none");
                    deletable = Integer.parseInt(rs.getString("CT03MATNO")) <= 0;
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

    public static boolean insertWEDOCM(String CT03DOCNO, String reason) {
        boolean exist = false;
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        ArrayList list = new ArrayList();
        try {
            if (dbtype.getDBType().equals("mssql")) {
                query = "SELECT docm.CT03DOCNO,docm.CT03STAT, docm.CT03MATNO, docm.CT03ORG, docm.CT03LOCA, docm.CT03ICAT, docm.CT03COND, docm.CT03BDATE, docm.CT03BTIME, docm.CT03DDATE, docm.CT03DTIME, docm.CT03FOREX, docm.CT03LCOST, docm.CT03FCOST, docm.CT03HCHAR, docm.CT03SCHAR, docm.CT03PATR, docm.CT03CLMHITS, docm.CT03CIRHITS, docm.CT03LASTACT, docm.CT03LASTID, docm.CT03PTYPE, docm.CT03SMD, docm.CT03ORDER, docm.CT03VEND, docm.CT03INVOICE, docm.CT03INVDATE, docm.CT03VOLUME, docm.CT03CRDATE, docm.CT03CREBY, docm.CT03DATEREC, docm.CT03RECBY, docm.CT03LASTDATE, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '250')) AS EDITION, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '300')) AS COLLATION, (SELECT CT05SRAW FROM CTAUTH WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '100')) AS AUTHOR, (SELECT CT05SRAW FROM CTPUBL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '260')) AS PUBLISHER, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS TITLE FROM CTDOCM docm INNER JOIN GLICAT icat ON docm.CT03ICAT = icat.GL10ICAT INNER JOIN GLSMD smd ON docm.CT03SMD = smd.GL47SMD INNER JOIN GLCOND cond ON docm.CT03COND = cond.GL06COND INNER JOIN GLDOCS docs ON CT03STAT = docs.GL36STAT INNER JOIN GLLOCA loca ON docm.CT03LOCA = loca.GL05LOCA INNER JOIN GLBRNC brnc ON loca.GL05BRNC = brnc.GL71BRNC WHERE CT03DOCNO = '" + CT03DOCNO + "'";
            } else if (dbtype.getDBType().equals("oracle")) {
                query = "SELECT docm.CT03DOCNO,docm.CT03STAT, docm.CT03MATNO, docm.CT03ORG, docm.CT03LOCA, docm.CT03ICAT, docm.CT03COND, docm.CT03BDATE, docm.CT03BTIME, docm.CT03DDATE, docm.CT03DTIME, docm.CT03FOREX, docm.CT03LCOST, docm.CT03FCOST, docm.CT03HCHAR, docm.CT03SCHAR, docm.CT03PATR, docm.CT03CLMHITS, docm.CT03CIRHITS, docm.CT03LASTACT, docm.CT03LASTID, docm.CT03PTYPE, docm.CT03SMD, docm.CT03ORDER, docm.CT03VEND, docm.CT03INVOICE, docm.CT03INVDATE, docm.CT03VOLUME, docm.CT03CRDATE, docm.CT03CREBY, docm.CT03DATEREC, docm.CT03RECBY, docm.CT03LASTDATE, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '250' AND rownum = 1)) AS EDITION, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '300' AND rownum = 1)) AS COLLATION, (SELECT CT05SRAW FROM CTAUTH WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '100' AND rownum = 1)) AS AUTHOR, (SELECT CT05SRAW FROM CTPUBL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '260' AND rownum = 1)) AS PUBLISHER, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' AND rownum = 1)) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' AND rownum = 1)) AS TITLE FROM CTDOCM docm INNER JOIN GLICAT icat ON docm.CT03ICAT = icat.GL10ICAT INNER JOIN GLSMD smd ON docm.CT03SMD = smd.GL47SMD INNER JOIN GLCOND cond ON docm.CT03COND = cond.GL06COND INNER JOIN GLDOCS docs ON CT03STAT = docs.GL36STAT INNER JOIN GLLOCA loca ON docm.CT03LOCA = loca.GL05LOCA INNER JOIN GLBRNC brnc ON loca.GL05BRNC = brnc.GL71BRNC WHERE CT03DOCNO = '" + CT03DOCNO + "'";
            } else if (dbtype.getDBType().equals("mysql")) {
                query = "SELECT docm.CT03DOCNO,docm.CT03STAT, docm.CT03MATNO, docm.CT03ORG, docm.CT03LOCA, docm.CT03ICAT, docm.CT03COND, docm.CT03BDATE, docm.CT03BTIME, docm.CT03DDATE, docm.CT03DTIME, docm.CT03FOREX, docm.CT03LCOST, docm.CT03FCOST, docm.CT03HCHAR, docm.CT03SCHAR, docm.CT03PATR, docm.CT03CLMHITS, docm.CT03CIRHITS, docm.CT03LASTACT, docm.CT03LASTID, docm.CT03PTYPE, docm.CT03SMD, docm.CT03ORDER, docm.CT03VEND, docm.CT03INVOICE, docm.CT03INVDATE, docm.CT03VOLUME, docm.CT03CRDATE, docm.CT03CREBY, docm.CT03DATEREC, docm.CT03RECBY, docm.CT03LASTDATE, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '250' LIMIT 1)) AS EDITION, (SELECT CT05SRAW FROM CTINDX WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '300' LIMIT 1)) AS COLLATION, (SELECT CT05SRAW FROM CTAUTH WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '100' LIMIT 1)) AS AUTHOR, (SELECT CT05SRAW FROM CTPUBL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '260' LIMIT 1)) AS PUBLISHER, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' LIMIT 1)) AS CALLNUMBER, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' LIMIT 1)) AS TITLE FROM CTDOCM docm INNER JOIN GLICAT icat ON docm.CT03ICAT = icat.GL10ICAT INNER JOIN GLSMD smd ON docm.CT03SMD = smd.GL47SMD INNER JOIN GLCOND cond ON docm.CT03COND = cond.GL06COND INNER JOIN GLDOCS docs ON CT03STAT = docs.GL36STAT INNER JOIN GLLOCA loca ON docm.CT03LOCA = loca.GL05LOCA INNER JOIN GLBRNC brnc ON loca.GL05BRNC = brnc.GL71BRNC WHERE CT03DOCNO = '" + CT03DOCNO + "'";
            }
            System.out.println(query);
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                String CT03CIRHITS;
                String CT03DATEREC;
                String CLMHITS;
                String WE03SCHAR;
                Glnumb wetrxno = Glnumb.getGL98VALUE("WEDOCM");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate localDate = LocalDate.now();
                String CT03HCHAR = Handler.ifIsNull(rs.getString("CT03HCHAR"));
                if (CT03HCHAR.equals(" ")) {
                    CT03HCHAR = "0.00";
                }
                if ((WE03SCHAR = Handler.ifIsNull(rs.getString("CT03SCHAR"))).equals(" ")) {
                    WE03SCHAR = "0.00";
                }
                if ((CLMHITS = Handler.ifIsNull(rs.getString("CT03CLMHITS"))).equals(" ")) {
                    CLMHITS = "0";
                }
                if ((CT03DATEREC = Handler.ifIsNull(rs.getString("CT03DATEREC"))).equals(" ")) {
                    CT03DATEREC = "";
                }
                if ((CT03CIRHITS = Handler.ifIsNull(rs.getString("CT03CIRHITS"))).equals(" ")) {
                    CT03CIRHITS = "0";
                    System.out.println("CT03CIRHITS:" + CT03CIRHITS + "sadad");
                }
                HashMap<String, String> valueStr = new HashMap<String, String>();
                HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
                String lastdate = rs.getString("CT03LASTDATE");
                valueInt.put("WE03TRXNO", wetrxno.getGL98VALUE());
                valueStr.put("WE03DOCNO", rs.getString("CT03DOCNO"));
                valueStr.put("WE03ORG", Handler.ifIsNull(rs.getString("CT03ORG")));
                valueStr.put("WE03MATNO", rs.getString("CT03MATNO"));
                valueStr.put("WE03LOCA", rs.getString("CT03LOCA"));
                valueStr.put("WE03ICAT", Handler.ifIsNull(rs.getString("CT03ICAT")));
                valueStr.put("WE03COND", Handler.ifIsNull(rs.getString("CT03COND")));
                valueStr.put("WE03BDATE", Handler.ifIsNull(rs.getString("CT03BDATE")));
                valueStr.put("WE03BTIME", Handler.ifIsNull(rs.getString("CT03BTIME")));
                valueStr.put("WE03DDATE", Handler.ifIsNull(rs.getString("CT03DDATE")));
                valueStr.put("WE03DTIME", Handler.ifIsNull(rs.getString("CT03DTIME")));
                valueStr.put("WE03FOREX", Handler.ifIsNull(rs.getString("CT03FOREX")));
                valueStr.put("WE03LCOST", Handler.ifIsNull(rs.getString("CT03LCOST")));
                valueStr.put("WE03FCOST", Handler.ifIsNull(rs.getString("CT03FCOST")));
                valueStr.put("WE03HCHAR", CT03HCHAR);
                valueStr.put("WE03SCHAR", WE03SCHAR);
                valueStr.put("WE03STAT", Handler.ifIsNull(rs.getString("CT03STAT")));
                valueStr.put("WE03PATR", Handler.ifIsNull(rs.getString("CT03PATR")));
                valueStr.put("WE03CLMHITS", CLMHITS);
                valueStr.put("WE03CIRHITS", CT03CIRHITS);
                valueStr.put("WE03LASTACT", Handler.ifIsNull(rs.getString("CT03LASTACT")));
                valueStr.put("WE03LASTID", Handler.ifIsNull(rs.getString("CT03LASTID")));
                valueStr.put("WE03PTYPE", Handler.ifIsNull(rs.getString("CT03PTYPE")));
                valueStr.put("WE03SMD", Handler.ifIsNull(rs.getString("CT03SMD")));
                valueStr.put("WE03ORDER", Handler.ifIsNull(rs.getString("CT03ORDER")));
                valueStr.put("WE03VEND", Handler.ifIsNull(rs.getString("CT03VEND")));
                valueStr.put("WE03INVOICE", Handler.ifIsNull(rs.getString("CT03INVOICE")));
                valueStr.put("WE03INVDATE", Handler.ifIsNull(rs.getString("CT03INVDATE")));
                valueStr.put("WE03VOLUME", Handler.ifIsNull(rs.getString("CT03VOLUME")));
                valueStr.put("WE03CRDATE", Handler.ifIsNull(rs.getString("CT03CRDATE")));
                valueStr.put("WE03CREBY", Handler.ifIsNull(rs.getString("CT03CREBY")));
                valueStr.put("WE03DATEREC", CT03DATEREC);
                valueStr.put("WE03RECBY", Handler.ifIsNull(rs.getString("CT03RECBY")));
                valueStr.put("WE03REASON", reason);
                valueStr.put("WE03WDATE", dtf.format(localDate).toString());
                valueStr.put("WE03TITLE", Handler.ifIsNull(rs.getString("TITLE")).replace("'", "''"));
                valueStr.put("WE03AUTHOR", Handler.ifIsNull(rs.getString("AUTHOR")).replace("'", "''"));
                valueStr.put("WE03CALLNO", Handler.ifIsNull(rs.getString("CALLNUMBER")));
                valueStr.put("WE03PUBLISHER", Handler.ifIsNull(rs.getString("PUBLISHER")));
                valueStr.put("WE03EDITION", Handler.ifIsNull(rs.getString("EDITION")));
                valueStr.put("WE03COLLATION", Handler.ifIsNull(rs.getString("COLLATION")));
                String add = QueryBuilder.createInsertQuery("WEDOCM", valueStr, valueInt, null);
                System.out.println("ADD" + add);
                boolean isSuccess = DBQuery.runQuery(add);
                System.out.println(isSuccess);
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void deleteCTDOCM(String CT03DOCNO, String username) {
        String query = "DELETE FROM CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "'";
        try {
            c = DBConnection.getConnection();
            PreparedStatement delete = c.prepareStatement(query);
            delete.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Weeding> retrieveWeedingList(String criteria, String searchType, String searchType1) throws SQLException {
        ArrayList<Weeding> weeding = new ArrayList<Weeding>();
        List<ISBD> isbd = ISBD.getPunctuation("245");
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            System.out.println(searchType);
            String sql = "Select WE03DOCNO, WE03TITLE, WE03WDATE from WEDOCM ";
            if (criteria.equals("accession")) {
                sql = String.valueOf(sql) + "where WE03DOCNO = '" + searchType + "'";
            } else if (criteria.equals("date")) {
                searchType = searchType.replaceAll("-", "");
                searchType1 = searchType1.replaceAll("-", "");
                sql = searchType.contains("%") ? String.valueOf(sql) + "where WE03WDATE LIKE '" + searchType + "' " : String.valueOf(sql) + "where WE03WDATE between '" + searchType + "' and '" + searchType1 + "'";
            } else if (criteria.equals("title")) {
                sql = searchType.contains("%") ? String.valueOf(sql) + "where WE03TITLE LIKE N'" + searchType + "' " : String.valueOf(sql) + "where WE03TITLE LIKE N'" + searchType + "%' ";
            }
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                Weeding list = new Weeding(rs.getString("WE03DOCNO"), Handler.getSubfield(rs.getString("WE03TITLE"), isbd), DateFormatter.DBToDisplayFormat(rs.getString("WE03WDATE")));
                weeding.add(list);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return weeding;
    }

    public static boolean totalCopy(String CT03DOCNO) throws SQLException {
        boolean validate = false;
        c = DBConnection.getConnection();
        stmt = c.createStatement();
        try {
            String sql = "SELECT Count(*) as Count FROM CTDOCM, WEDOCM WHERE WE03MATNO=CT03MATNO AND WE03DOCNO=CT03DOCNO AND WE03DOCNO = '" + CT03DOCNO + "' " + "AND CT03DOCNO <> '" + CT03DOCNO + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("Count")) != 0) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public static String WEmatno(String CT03DOCNO) {
        boolean validate = false;
        String CT03MATNO = null;
        try {
            stmt = c.createStatement();
            String sql = "SELECT WE03MATNO FROM WEDOCM WHERE WE03DOCNO='" + CT03DOCNO + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                CT03MATNO = rsObj.getString("WE03MATNO");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return CT03MATNO;
    }

    public static Weeding getCTMATM(String controlNoInput) {
        String query = "SELECT CT02MATNO, CT02STATUS, CT02CRDATE, CT02CREBY, CT02CRETIME, CT02TYPE, CT02IDXBY, CT02IDXDATE,CT02IDXTIME FROM CTMATM WHERE CT02MATNO ='" + controlNoInput + "'";
        Weeding newSE01_SearchByMatno = null;
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String cretime = Handler.ifIsNull(rs.getString("CT02CRETIME"));
                System.out.println("cretime" + cretime);
                if (cretime != " ") {
                    cretime = GeneralUtility.StrToTime2(cretime);
                }
                newSE01_SearchByMatno = new Weeding(rs.getString("CT02MATNO"), Handler.CatalogueIDXStatus(rs.getString("CT02STATUS")), rs.getString("CT02CRDATE"), rs.getString("CT02CREBY"), cretime, Handler.CatalogueType(rs.getString("CT02TYPE")), rs.getString("CT02IDXDATE"), rs.getString("CT02IDXBY"), rs.getString("CT02IDXTIME"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return newSE01_SearchByMatno;
    }

    public static boolean checkCTMATMD(String CT03MATNO) throws SQLException {
        boolean validate = false;
        c = DBConnection.getConnection();
        stmt = c.createStatement();
        try {
            String sql = "SELECT CT02STATUS FROM CTMATM WHERE CT02MATNO = '" + CT03MATNO + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (!rs.getString("CT02STATUS").equals("D")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public static boolean checkCTPONTzero(String CT06MATNO) {
        String query = "SELECT COUNT(*) AS CT06MATNO FROM CTPONT WHERE CT06MATNO='" + CT06MATNO + "'";
        boolean validYN = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("checkCTPONTzero");
                    validYN = Integer.parseInt(rs.getString("CT06MATNO")) <= 0;
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
        return validYN;
    }
}
