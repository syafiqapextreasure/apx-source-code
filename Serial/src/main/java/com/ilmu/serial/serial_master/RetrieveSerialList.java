/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.serial_master;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrieveSerialList {
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

    public RetrieveSerialList(String matno, String title, String callno, String year, String publication, String edition) {
        this.matno = matno;
        this.title = title;
        this.callno = callno;
        this.publication = publication;
        this.year = year;
        this.edition = edition;
    }

    public RetrieveSerialList(String title) {
        this.title = title;
    }

    public RetrieveSerialList(String bufferno, String title) {
        this.bufferno = bufferno;
        this.title = title;
    }

    public RetrieveSerialList(String rating, String patrid, String patrname) {
        this.rating = rating;
        this.patrid = patrid;
        this.patrname = patrname;
    }

    public RetrieveSerialList(String title, int pointer, int hits) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public String getTitle() {
        return Handler.removeSubfield(this.title);
    }

    public String getCallNo() {
        return RetrieveSerialList.rawToDisplayFormat(this.callno);
    }

    public String getYear() {
        return this.year;
    }

    public String getPublication() {
        return this.publication;
    }

    public String getEdition() {
        return RetrieveSerialList.rawToDisplayFormat(this.edition);
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

    public static String getDataByMarcTag(String marcTag, String controlNo) {
        String result;
        block12: {
            String query = "SELECT CT05SRAW FROM CTPONT, " + RetrieveSerialList.getMarkTagTableName(marcTag) + " " + "WHERE CT06TAG = '" + marcTag + "' " + "AND CT06MATNO = '" + controlNo + "' " + "AND CT06POINTER = CT05POINTER";
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
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block12;
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
        return RetrieveSerialList.rawToDisplayFormat(result);
    }

    public static List<RetrieveSerialList> CT_SearchByAccno(String controlNoInput) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        String query = "  Select CT05SRAW from CTTITL, CTPONT where CT06POINTER=CT05POINTER and CT06MATNO= '" + controlNoInput + "'";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                RetrieveSerialList CT_SearchByAccno = new RetrieveSerialList(rs.getString("CT05SRAW"));
                list.add(CT_SearchByAccno);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<RetrieveSerialList> creator(String startDate, String endDate) {
        return RetrieveSerialList.searchByPatr(startDate, endDate, "CT02CREBY", "CT02CRDATE");
    }

    public static List<RetrieveSerialList> modifier(String startDate, String endDate) {
        return RetrieveSerialList.searchByPatr(startDate, endDate, "CT02IDXBY", "CT02IDXDATE");
    }

    public static List<RetrieveSerialList> bufferCreator(String startDate, String endDate) {
        return RetrieveSerialList.searchByBuffPatr(startDate, endDate, "CT04CREBY", "CT02CREDATE");
    }

    public static List<RetrieveSerialList> bufferModifier(String startDate, String endDate) {
        return RetrieveSerialList.searchByBuffPatr(startDate, endDate, "CT04MODIBY", "CT04MODIDATE");
    }

    public static List<RetrieveSerialList> searchByPatr(String startDate, String endDate, String patron, String date) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        String query = "SELECT DISTINCT CT02RATING, GL14NAME, " + patron + " AS PATRON " + "FROM CTMATM, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                RetrieveSerialList searchByPatr = new RetrieveSerialList(rs.getString("CT02RATING"), rs.getString("PATRON"), rs.getString("GL14NAME"));
                list.add(searchByPatr);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<RetrieveSerialList> searchByBuffPatr(String startDate, String endDate, String patron, String date) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        String query = "SELECT DISTINCT CT04POST, GL14NAME, " + patron + " AS PATRON " + "FROM CTWORK, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        System.out.println(query);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                RetrieveSerialList searchByPatr = new RetrieveSerialList(rs.getString("CT04POST"), rs.getString("PATRON"), rs.getString("GL14NAME"));
                list.add(searchByPatr);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getMarkTagTableName(String marcTag) {
        String query = "SELECT GL17TABNAME FROM GLTAGP WHERE GL17TAG = '" + marcTag + "' AND GL17MARC = 'U'";
        String result = null;
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

    public static List<RetrieveSerialList> getAllBy(String marcTag, String tableName, String condition) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        boolean retval = condition.contains("%");
        String query = "";
        query = retval ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER INNER JOIN SESERM ON SE01MATNO = CT06MATNO " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + condition + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTERINNER JOIN SESERM ON SE01MATNO = CT06MATNO " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + condition + "%' ORDER BY CT05SRAW";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList newGetAllBy = new RetrieveSerialList(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
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

    public static List<RetrieveSerialList> getInTag(String tag, String criteria) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        String sTableName = null;
        sTableName = RetrieveSerialList.getMarkTagTableName(tag);
        String query = null;
        boolean retval = criteria.contains("%");
        query = retval ? (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + RetrieveSerialList.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE N'" + criteria + "' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM CTPONT, " + RetrieveSerialList.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE N'" + criteria + "' and CT05HITS > 0") : (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + RetrieveSerialList.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE '" + criteria + "%' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM CTPONT, " + RetrieveSerialList.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE '" + criteria + "%' and CT05HITS > 0");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList newGetAllBy = new RetrieveSerialList(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
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

    public static List<RetrieveSerialList> CTINDX(String marcTag, String tableName, String condition, String tag) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        boolean retval = condition.contains("%");
        String query = null;
        query = retval ? (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER INNER JOIN SESERM ON SE01MATNO = CT06MATNO " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + condition + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER INNER JOIN SESERM ON SE01MATNO = CT06MATNO" + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + condition + "' ORDER BY CT05SRAW") : (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER INNER JOIN SESERM ON SE01MATNO = CT06MATNO " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + condition + "%' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER INNER JOIN SESERM ON SE01MATNO = CT06MATNO " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + condition + "%' ORDER BY CT05SRAW");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList newGetAllBy = new RetrieveSerialList(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
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

    public static List<RetrieveSerialList> getAllRaw(String pointer) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        String query = "SELECT T1.CT06MATNO AS MATNO, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T9.CT05SRAW AS INDX FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG = '260') LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList newGetAllBy = new RetrieveSerialList(rs.getString("MATNO"), rs.getString("TITLE"), rs.getString("CALLNO"), Handler.getPublication(rs.getString("PUBL")), Handler.getYear(rs.getString("PUBL")), rs.getString("INDX"));
                    list.add(newGetAllBy);
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

    public static List<RetrieveSerialList> searchByTitle(String criteria) {
        return RetrieveSerialList.getAllBy("001", "CTTITL", criteria);
    }

    public static List<RetrieveSerialList> searchByName(String criteria) {
        return RetrieveSerialList.getAllBy("002", "CTAUTH", criteria);
    }

    public static List<RetrieveSerialList> searchBySubject(String criteria) {
        return RetrieveSerialList.getAllBy("003", "CTSUBJ", criteria);
    }

    public static List<RetrieveSerialList> searchByPubl(String criteria) {
        return RetrieveSerialList.getAllBy("004", "CTPUBL", criteria);
    }

    public static List<RetrieveSerialList> searchBySeries(String criteria) {
        return RetrieveSerialList.getAllBy("005", "CTSERI", criteria);
    }

    public static List<RetrieveSerialList> searchByCallNo(String criteria) {
        return RetrieveSerialList.getAllBy("006", "CTCALL", criteria);
    }

    public static List<RetrieveSerialList> searchByIndx(String criteria, String tag) {
        return RetrieveSerialList.CTINDX("007", "CTINDX", criteria, tag);
    }

    public static List<RetrieveSerialList> retrieveTitle(String pointer) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS TITLE FROM CTTITL INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("TITLE"));
                    list.add(result);
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

    public static List<RetrieveSerialList> retrieveCallNo(String pointer) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS CALLNO FROM CTCALL INNER JOIN CTPONT ON CTCALL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CALLNO"));
                    list.add(result);
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

    public static String getTitleNameByControlNo(String controlNo) {
        String result;
        block12: {
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
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block12;
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
        return RetrieveSerialList.rawToDisplayFormat(result);
    }

    public static List<RetrieveSerialList> getByBufferNo(String bufferno) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        Statement stmt = null;
        String query = "SELECT CT04RAW FROM CTWORK WHERE CT04MATNO = '" + bufferno + "' AND CT04STATUS='A' AND CT04TAG='245'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CT04RAW"));
                    list.add(result);
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

    public static List<RetrieveSerialList> getByBufferTag(String tag, String criteria) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        Statement stmt = null;
        boolean retval = criteria.contains("%");
        String query = "";
        query = retval ? "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04SCONV LIKE '" + criteria + "' AND CT04STATUS='A' AND CT04TAG='" + tag + "'" : "SELECT CT04MATNO, CT04RAW FROM CTWORK WHERE CT04SCONV LIKE '" + criteria + "%' AND CT04STATUS='A' AND CT04TAG='" + tag + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CT04MATNO"), rs.getString("CT04RAW"));
                    list.add(result);
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

    public static List<RetrieveSerialList> getByCtrlNo(String ctrlno) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE CT06POINTER=CT05POINTER AND CT06MATNO = '" + ctrlno + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CT05SRAW"));
                    list.add(result);
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

    public static List<RetrieveSerialList> getOfficerID_Buffer(String patrid, String type, String startDate, String stopDate) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
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
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CT04MATNO"), rs.getString("CT04RAW"));
                    list.add(result);
                    System.out.println(rs.getString("CT04RAW"));
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

    public static List<RetrieveSerialList> getOfficerID_Index(String patrid, String type, String startDate, String stopDate) {
        ArrayList<RetrieveSerialList> list = new ArrayList<RetrieveSerialList>();
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
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    RetrieveSerialList result = new RetrieveSerialList(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(result);
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
}
