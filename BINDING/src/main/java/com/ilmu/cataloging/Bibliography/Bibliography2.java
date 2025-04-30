/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

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

public class Bibliography2 {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String title = null;
    public String matno = null;
    public int hits = 0;
    public int pointer = 0;
    public String callno = null;
    public String year = null;
    public String edition = null;
    public String bufferno = null;
    public String patrname = null;
    public String patrid = null;
    public String rating = null;
    public String publication = null;
    public String tag = null;
    public String DOCNO = null;
    public String ICAT = null;
    public String ICATDESC = null;
    public String STATDESC = null;
    public String STAT = null;

    public Bibliography2(String matno, String title, String callno, String year, String publication, String edition) {
        this.matno = matno;
        this.title = title;
        this.callno = callno;
        this.publication = publication;
        this.year = year;
        this.edition = edition;
    }

    public Bibliography2(String matno, String title, String callno, String year, String publication, String edition, String DOCNO) {
        this.matno = matno;
        this.title = title;
        this.callno = callno;
        this.publication = publication;
        this.year = year;
        this.edition = edition;
        this.DOCNO = DOCNO;
    }

    public Bibliography2(String title) {
        this.title = title;
    }

    public Bibliography2(String bufferno, String title) {
        this.bufferno = bufferno;
        this.title = title;
    }

    public Bibliography2(String rating, String patrid, String patrname) {
        this.rating = rating;
        this.patrid = patrid;
        this.patrname = patrname;
    }

    public Bibliography2(String tag, String title, int pointer, int hits) {
        this.tag = tag;
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public Bibliography2(String title, int pointer, int hits) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCallNo() {
        return Bibliography2.rawToDisplayFormat(this.callno);
    }

    public String getYear() {
        return this.year;
    }

    public String getPublication() {
        return this.publication;
    }

    public String getEdition() {
        return Bibliography2.rawToDisplayFormat(this.edition);
    }

    public String getMatno() {
        return this.matno;
    }

    public String getBufferno() {
        return this.bufferno;
    }

    public String getPatrID() {
        return this.patrid;
    }

    public String getPatrName() {
        return this.patrname;
    }

    public int getHits() {
        return this.hits;
    }

    public int getPointer() {
        return this.pointer;
    }

    public String getDOCNO() {
        return this.DOCNO;
    }

    public String getICAT() {
        return this.ICAT;
    }

    public String getICATDESC() {
        return this.ICATDESC;
    }

    public String getSTAT() {
        return this.STAT;
    }

    public String getSTATDESC() {
        return this.STATDESC;
    }

    public static String getDataByMarcTag(String marcTag, String controlNo) {
        String result;
        block14: {
            String query = "SELECT CT05SRAW FROM CTPONT, " + Bibliography2.getMarkTagTableName(marcTag) + " " + "WHERE CT06TAG = '" + marcTag + "' " + "AND CT06MATNO = '" + controlNo + "' " + "AND CT06POINTER = CT05POINTER";
            result = null;
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        result = rs.getString("CT05SRAW");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        c.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block14;
                }
            }
            catch (Throwable throwable) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Bibliography2.rawToDisplayFormat(result);
    }

    public static List<Bibliography2> CT_SearchByAccno(String controlNoInput) throws SQLException {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        List<ISBD> isbd = ISBD.getPunctuation("245");
        String query = "SELECT CT03DOCNO, CT05SRAW FROM CTTITL LEFT OUTER JOIN CTPONT ON CT06POINTER=CT05POINTER LEFT OUTER JOIN CTDOCM ON CT03MATNO = CT06MATNO WHERE CT06MATNO= '" + controlNoInput + "' and ct06tag='245' ";
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
                    Bibliography2 CT_SearchByAccno = new Bibliography2(Handler.ifIsNull(rs.getString("CT03DOCNO")), Handler.getSubfield(rs.getString("CT05SRAW"), isbd));
                    list.add(CT_SearchByAccno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> creator(String startDate, String endDate) {
        return Bibliography2.searchByPatr(startDate, endDate, "CT02CREBY", "CT02CRDATE");
    }

    public static List<Bibliography2> modifier(String startDate, String endDate) {
        return Bibliography2.searchByPatr(startDate, endDate, "CT02IDXBY", "CT02IDXDATE");
    }

    public static List<Bibliography2> bufferCreator(String startDate, String endDate) {
        return Bibliography2.searchByBuffPatr(startDate, endDate, "CT04CREBY", "CT02CREDATE");
    }

    public static List<Bibliography2> bufferModifier(String startDate, String endDate) {
        return Bibliography2.searchByBuffPatr(startDate, endDate, "CT04MODIBY", "CT04MODIDATE");
    }

    public static List<Bibliography2> searchByPatr(String startDate, String endDate, String patron, String date) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        String query = "SELECT DISTINCT CT02RATING, GL14NAME, " + patron + " AS PATRON " + "FROM CTMATM, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        System.out.println(query);
        try {
            try {
                Connection conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 searchByPatr = new Bibliography2(rs.getString("CT02RATING"), rs.getString("PATRON"), rs.getString("GL14NAME"));
                    list.add(searchByPatr);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> searchByBuffPatr(String startDate, String endDate, String patron, String date) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        String query = "SELECT DISTINCT CT04POST, GL14NAME, " + patron + " AS PATRON " + "FROM CTWORK, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        System.out.println(query);
        try {
            try {
                Connection conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 searchByPatr = new Bibliography2(rs.getString("CT04POST"), rs.getString("PATRON"), rs.getString("GL14NAME"));
                    list.add(searchByPatr);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static String getMarkTagTableName(String marcTag) {
        String query = "SELECT GL17TABNAME FROM GLTAGP WHERE GL17TAG = '" + marcTag + "' AND GL17MARC = 'U'";
        String result = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL17TABNAME");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return result;
    }

    public static String rawToDisplayFormat(String raw) {
        if (raw == null) {
            return "-";
        }
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
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

    public static List<Bibliography2> getAllBy(String marcTag, String tableName, String condition, List<ISBD> isbd) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        boolean retval = condition.contains("%");
        String query = "";
        query = retval ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND CT05HITS IS NOT NULL ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%' AND CT05HITS IS NOT NULL ORDER BY CT05SRAW";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 newGetAllBy = new Bibliography2(Handler.getSubfield(rs.getString("CT05SRAW"), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                    Thread.sleep(1000L);
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    c.close();
                    Thread.sleep(1000L);
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
                Thread.sleep(1000L);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Bibliography2> getInTag(String tag, String criteria) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        String sTableName = null;
        sTableName = Bibliography2.getMarkTagTableName(tag);
        String query = null;
        boolean retval = criteria.contains("%");
        query = retval ? (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + Bibliography2.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM CTPONT, " + Bibliography2.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0") : (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + Bibliography2.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM CTPONT, " + Bibliography2.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 newGetAllBy = new Bibliography2(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> CTINDX(String marcTag, String tableName, String condition, String tag, List<ISBD> isbd) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        boolean retval = condition.contains("%");
        String query = null;
        query = retval ? (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW") : (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' ORDER BY CT05SRAW");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 newGetAllBy = new Bibliography2(Handler.getSubfield(rs.getString("CT05SRAW"), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getAllRaw2(String pointer) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        String query = "SELECT T1.CT06MATNO AS MATNO, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T9.CT05SRAW AS INDX, T10.CT03DOCNO AS DOCNO FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG = '260') LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTDOCM T10 ON (T1.CT06MATNO  = T10.CT03MATNO) LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> callisbd = ISBD.getPunctuation("090");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 newGetAllBy = new Bibliography2(rs.getString("MATNO"), Handler.getSubfield(rs.getString("TITLE"), isbd), rs.getString("CALLNO"), Handler.getPublication(rs.getString("PUBL")), Handler.getYear(rs.getString("PUBL")), rs.getString("INDX"), rs.getString("DOCNO"));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getAllRaw(String pointer) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        String query = "SELECT T1.CT06MATNO AS MATNO, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T9.CT05SRAW AS INDX FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG = '260') LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> callisbd = ISBD.getPunctuation("090");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 newGetAllBy = new Bibliography2(rs.getString("MATNO"), Handler.getSubfield(rs.getString("TITLE"), isbd), rs.getString("CALLNO"), Handler.getPublication(rs.getString("PUBL")), Handler.getYear(rs.getString("PUBL")), rs.getString("INDX"));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> searchByTitle(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("245");
        return Bibliography2.getAllBy("001", "CTTITL", criteria, isbd);
    }

    public static List<Bibliography2> searchByName(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("100");
        return Bibliography2.getAllBy("002", "CTAUTH", criteria, isbd);
    }

    public static List<Bibliography2> searchBySubject(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("650");
        return Bibliography2.getAllBy("003", "CTSUBJ", criteria, isbd);
    }

    public static List<Bibliography2> searchByPubl(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("260");
        return Bibliography2.getAllBy("004", "CTPUBL", criteria, isbd);
    }

    public static List<Bibliography2> searchBySeries(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("490");
        return Bibliography2.getAllBy("005", "CTSERI", criteria, isbd);
    }

    public static List<Bibliography2> searchByCallNo(String criteria) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("090");
        return Bibliography2.getAllBy("006", "CTCALL", criteria, isbd);
    }

    public static List<Bibliography2> searchByIndx(String criteria, String tag) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("500");
        return Bibliography2.CTINDX("007", "CTINDX", criteria, tag, isbd);
    }

    public static List<Bibliography2> retrieveTitle(String pointer) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS TITLE FROM CTTITL INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(rs.getString("TITLE"));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> retrieveCallNo(String pointer) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS CALLNO FROM CTCALL INNER JOIN CTPONT ON CTCALL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(rs.getString("CALLNO"));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static String getTitleNameByControlNo(String controlNo) {
        String result;
        block14: {
            result = null;
            Statement stmt = null;
            String query = "SELECT CT05SRAW FROM CTTITL INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06MATNO = '" + controlNo + "' ";
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        result = rs.getString("CT05SRAW");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        c.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block14;
                }
            }
            catch (Throwable throwable) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Bibliography2.rawToDisplayFormat(result);
    }

    public static List<Bibliography2> getByBufferNo(String bufferno) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04MATNO LIKE '" + bufferno + "' AND CT04STATUS='A' AND CT04TAG='245' order by ct04tag";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("Title" + rs.getString("CT04RAW"));
                    Bibliography2 result = new Bibliography2(rs.getString("CT04MATNO"), Handler.getSubfield(rs.getString("CT04RAW"), isbd));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getByBufferTag(String tag, String criteria) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        boolean retval = criteria.contains("%");
        String query = "";
        query = retval ? "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04SCONV LIKE '" + Handler.convUpperCase(criteria) + "' AND CT04STATUS='A' AND CT04TAG='" + tag + "'" : "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' AND CT04STATUS='A' AND CT04TAG='" + tag + "'";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(rs.getString("CT04MATNO"), Handler.getSubfield(rs.getString("CT04RAW"), isbd));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getByCtrlNo(String ctrlno) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE CT06POINTER=CT05POINTER AND CT06MATNO = '" + ctrlno + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(rs.getString("CT05SRAW"));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getOfficerID_Buffer(String patrid, String type, String startDate, String stopDate) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04TAG='245' ";
        System.out.println(String.valueOf(startDate) + stopDate);
        if (startDate == "" && stopDate == "") {
            if (type.equals("creator")) {
                query = String.valueOf(query) + "AND CT04CREBY = '" + patrid + "'";
            } else if (type.equals("modifier")) {
                query = String.valueOf(query) + "AND CT04MODIBY = '" + patrid + "'";
            }
        } else if (type.equals("creator")) {
            query = String.valueOf(query) + "AND CT04CREBY = '" + patrid + "' AND  " + "CT04CREDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
        } else if (type.equals("modifier")) {
            query = String.valueOf(query) + "AND CT04MODIBY = '" + patrid + "' AND " + "CT04MODIDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
        }
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(rs.getString("CT04MATNO"), Handler.getSubfield(rs.getString("CT04RAW"), isbd));
                    list.add(result);
                    System.out.println(rs.getString("CT04RAW"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static List<Bibliography2> getOfficerID_Index(String patrid, String type, String startDate, String stopDate) {
        ArrayList<Bibliography2> list = new ArrayList<Bibliography2>();
        Statement stmt = null;
        String query = "SELECT CT05POINTER, CT05SRAW, CT05HITS FROM CTMATM, CTPONT, CTTITL WHERE CT02MATNO = CT06MATNO AND CT06POINTER = CT05POINTER AND CT06TAG='245' ";
        if (startDate != "" && stopDate != "") {
            if (type.equals("creator")) {
                query = String.valueOf(query) + "AND CT02CREBY = '" + patrid + "' AND  " + "CT02CRDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
            } else if (type.equals("modifier")) {
                query = String.valueOf(query) + "AND CT02IDXBY = '" + patrid + "' AND " + "CT02IDXDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
            }
        } else if (type.equals("creator")) {
            System.out.println(type);
            query = String.valueOf(query) + "AND CT02CREBY = '" + patrid + "'";
        } else if (type.equals("modifier")) {
            query = String.valueOf(query) + "AND CT02IDXBY = '" + patrid + "'";
        }
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography2 result = new Bibliography2(Handler.getSubfield(rs.getString("CT05SRAW"), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
}
