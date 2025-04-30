/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.cataloging.Global.ISBD;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bibliography {
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
    public String type = null;

    public Bibliography(String matno, String type, String title, String callno, String year, String publication, String edition) {
        this.matno = matno;
        this.type = type;
        this.title = title;
        this.callno = callno;
        this.publication = publication;
        this.year = year;
        this.edition = edition;
    }

    public Bibliography(String title) {
        this.title = title;
    }

    public Bibliography(String bufferno, String title) {
        this.bufferno = bufferno;
        this.title = title;
    }

    public Bibliography(String rating, String patrid, String patrname) {
        this.rating = rating;
        this.patrid = patrid;
        this.patrname = patrname;
    }

    public Bibliography(String tag, String title, int pointer, int hits) {
        this.tag = tag;
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public Bibliography(String title, int pointer, int hits) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public String getTitle() {
        return this.title;
    }

    public String getRating() {
        return this.rating;
    }

    public String getCallNo() {
        return Bibliography.rawToDisplayFormat(this.callno);
    }

    public String getYear() {
        return this.year;
    }

    public String getPublication() {
        return this.publication;
    }

    public String getEdition() {
        return Bibliography.rawToDisplayFormat(this.edition);
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

    public String getType() {
        return this.type;
    }

    public static String getDataByMarcTag(String marcTag, String controlNo) {
        String result;
        block12: {
            String query = "SELECT CT05SRAW FROM CTPONT, " + Bibliography.getMarkTagTableName(marcTag) + " " + "WHERE CT06TAG = '" + marcTag + "' " + "AND CT06MATNO = '" + controlNo + "' " + "AND CT06POINTER = CT05POINTER";
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
        return Bibliography.rawToDisplayFormat(result);
    }

    public static List<Bibliography> CT_SearchByAccno(String controlNoInput, String type) throws SQLException {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        List<ISBD> isbd = ISBD.getPunctuation("245");
        System.out.println("SS" + controlNoInput);
        boolean retval = controlNoInput.contains("%");
        String matno = "";
        matno = retval ? "CT06MATNO LIKE " : "CT06MATNO =";
        String query = "";
        query = type == "" ? "Select CT05SRAW, CT02TYPE, CT05RAW from CTTITL, CTPONT, CTMATM where CT02MATNO=CT06MATNO AND CT06POINTER=CT05POINTER and " + matno + "'" + controlNoInput + "' and ct06tag='245'" : "Select CT05SRAW, CT02TYPE,CT05RAW from CTTITL, CTPONT, CTMATM where CT02MATNO=CT06MATNO AND CT06POINTER=CT05POINTER and " + matno + "'" + controlNoInput + "' and CT02TYPE='" + type + "' AND ct06tag='245'";
        System.out.println("SS" + query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    String data = "";
                    data = rs.getString("CT05RAW") != null ? String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW") : rs.getString("CT05SRAW");
                    Bibliography CT_SearchByAccno = new Bibliography(ISBD.getSubfield(Handler.removeSubf(data), isbd), rs.getString("CT02TYPE"));
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

    public static List<Bibliography> CT_SearchByAccessionNo(String controlNoInput, String type) throws SQLException {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        List<ISBD> isbd = ISBD.getPunctuation("245");
        boolean retval = controlNoInput.contains("%");
        String query = "";
        if (type == "") {
            query = "  Select CT05SRAW, CT02TYPE, CT02MATNO, CT05POINTER, CT05RAW from CTTITL, CTPONT, CTMATM, CTDOCM where CT02MATNO=CT06MATNO AND CT06POINTER=CT05POINTER AND CT03MATNO = CT02MATNO and CT03DOCNO= '" + controlNoInput + "' and ct06tag='245'";
            query = "SELECT CT05SRAW, CT02TYPE, CT02MATNO, CT05POINTER, CT05RAW  from ctdocm inner join CTMATM on CT03MATNO = CT02MATNO left join CTPONT on CT03MATNO=CT06MATNO and CT06TAG = '245' left join CTTITL on CT06POINTER=CT05POINTER ";
            query = retval ? String.valueOf(query) + "where ct03docno like '" + controlNoInput + "'" : String.valueOf(query) + "where ct03docno = '" + controlNoInput + "'";
        } else {
            query = "  Select CT05SRAW, CT02TYPE, CT02MATNO, CT05RAW from CTTITL, CTPONT, CTMATM, CTDOCM where CT02MATNO=CT06MATNO AND CT06POINTER=CT05POINTER AND CT03MATNO = CT02MATNO and CT03DOCNO= '" + controlNoInput + "' and ct06tag='245' and ct02type = '" + type + "'";
            query = "SELECT CT05SRAW, CT02TYPE, CT02MATNO, CT05POINTER, CT05RAW  from ctdocm inner join CTMATM on CT03MATNO = CT02MATNO left join CTPONT on CT03MATNO=CT06MATNO and CT06TAG = '245' left join CTTITL on CT06POINTER=CT05POINTER ";
            query = retval ? String.valueOf(query) + "where ct03docno like '" + controlNoInput + "' and ct02type = '" + type + "'" : String.valueOf(query) + "where ct03docno = '" + controlNoInput + "' and ct02type = '" + type + "'";
        }
        System.out.println("Query" + query);
        try {
            try {
                Object conn = null;
                Statement stmt1 = null;
                ResultSet rs = null;
                c = DBConnection.getConnection();
                stmt1 = c.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    String data = "";
                    data = rs.getString("CT05SRAW") == null ? Handler.ifIsNull(String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW")) : ISBD.getSubfield(Handler.removeSubf(String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW")), isbd);
                    Bibliography CT_SearchByAccno = new Bibliography(data, rs.getString("CT02TYPE"), rs.getString("CT02MATNO"));
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

    public static List<Bibliography> creator(String startDate, String endDate, String criteria) {
        return Bibliography.searchByPatr(startDate, endDate, criteria, "CT02CREBY", "CT02CRDATE");
    }

    public static List<Bibliography> modifier(String startDate, String endDate, String criteria) {
        return Bibliography.searchByPatr(startDate, endDate, criteria, "CT02IDXBY", "CT02IDXDATE");
    }

    public static List<Bibliography> bufferCreator(String startDate, String endDate, String criteria) {
        return Bibliography.searchByBuffPatr(startDate, endDate, criteria, "CT04CREBY", "CT02CREDATE");
    }

    public static List<Bibliography> bufferModifier(String startDate, String endDate, String criteria) {
        return Bibliography.searchByBuffPatr(startDate, endDate, criteria, "CT04MODIBY", "CT04MODIDATE");
    }

    public static List<Bibliography> searchByPatr(String startDate, String endDate, String patrID, String patron, String date) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        String query = "SELECT DISTINCT CT02RATING, GL14NAME, " + patron + " AS PATRON " + "FROM CTMATM, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        if (patrID != null) {
            query = String.valueOf(query) + "AND " + patron + "='" + patrID + "'";
        } else if (startDate != null && endDate != null) {
            query = String.valueOf(query) + "AND " + date + " BETWEEN '" + startDate + "' AND '" + endDate + "'";
        }
        System.out.println("Date" + query);
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    Bibliography searchByPatr = new Bibliography(Handler.ifIsNull(rs.getString("CT02RATING")), rs.getString("PATRON"), rs.getString("GL14NAME"));
                    list.add(searchByPatr);
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

    public static List<Bibliography> searchByBuffPatr(String startDate, String endDate, String patrID, String patron, String date) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        String query = "SELECT DISTINCT  CT04POST, GL14NAME, " + patron + " AS PATRON " + "FROM CTWORK, GLPATR " + "WHERE " + patron + "=GL14PATR ";
        if (patrID != null) {
            query = String.valueOf(query) + "AND " + patron + "='" + patrID + "'";
        } else if (startDate != null && endDate != null) {
            query = String.valueOf(query) + "AND " + date + " BETWEEN '" + startDate + "' AND '" + endDate + "'";
        }
        System.out.println("SS" + query);
        Connection conn = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    Bibliography searchByPatr = new Bibliography(rs.getString("CT04POST"), rs.getString("PATRON"), rs.getString("GL14NAME"));
                    list.add(searchByPatr);
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

    public static boolean chkDuplicateRcrd(String tag) {
        String query = "SELECT GL17STOP FROM GLTAGP WHERE GL17TAG = '" + tag + "' AND GL17MARC = 'U'";
        boolean duplicate = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (rs.getString("GL17STOP") == null || !rs.getString("GL17STOP").equals("Y")) continue;
                    duplicate = true;
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
        return duplicate;
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

    public static List<Bibliography> getAllBy(String marcTag, String tableName, String condition, List<ISBD> isbd, String module, String type) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        boolean retval = condition.contains("%");
        String query = "";
        condition = Handler.removeSpecialChar(condition);
        String modules = Handler.tblName(module);
        DBConnection dbtype = new DBConnection();
        if (type == "") {
            if (retval) {
                if (DBConnection.getDBType().equals("mssql")) {
                    query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) ORDER BY CT05SRAW";
                } else if (DBConnection.getDBType().equals("oracle")) {
                    query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
                } else if (DBConnection.getDBType().equals("mysql")) {
                    query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
                }
            } else if (DBConnection.getDBType().equals("mssql")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) ORDER BY CT05SRAW";
            } else if (DBConnection.getDBType().equals("oracle")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
            } else if (DBConnection.getDBType().equals("mysql")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%' AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
            }
        } else if (retval) {
            if (DBConnection.getDBType().equals("mssql")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN CTMATM ON CTMATM.CT02MATNO=CTPONT.CT06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) ORDER BY CT05SRAW";
            } else if (DBConnection.getDBType().equals("oracle")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN CTMATM ON CTMATM.CT02MATNO=CTPONT.CT06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
            } else if (DBConnection.getDBType().equals("mysql")) {
                query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN CTMATM ON CTMATM.CT02MATNO=CTPONT.CT06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "' AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
            }
        } else if (DBConnection.getDBType().equals("mssql")) {
            query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN " + modules + "MATM ON " + modules + "MATM." + modules + "02MATNO=" + modules + "PONT." + modules + "06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%'AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CAST(CT05RAW AS varchar) ORDER BY CT05SRAW";
        } else if (DBConnection.getDBType().equals("oracle")) {
            query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN " + modules + "MATM ON " + modules + "MATM." + modules + "02MATNO=" + modules + "PONT." + modules + "06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%'AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
        } else if (DBConnection.getDBType().equals("mysql")) {
            query = "SELECT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN " + modules + "PONT ON " + tableName + ".CT05POINTER = " + modules + "PONT." + modules + "06POINTER " + "INNER JOIN GLTAGP ON " + modules + "PONT." + modules + "06TAG = GLTAGP.GL17TAG AND " + modules + "PONT." + modules + "06MARC=GLTAGP.GL17MARC " + "INNER JOIN " + modules + "MATM ON " + modules + "MATM." + modules + "02MATNO=" + modules + "PONT." + modules + "06MATNO " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE N'" + Handler.convUpperCase(condition) + "%'AND " + modules + "02TYPE='" + type + "' " + "AND CT05HITS IS NOT NULL " + "GROUP BY CT05POINTER, CT05SRAW, CT05HITS, CT05RAW ORDER BY CT05SRAW";
        }
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                stmt = c.createStatement(1005, 1007);
                rs = stmt.executeQuery(query);
                System.out.println(query);
                while (rs.next()) {
                    String data = "";
                    data = rs.getString("CT05RAW") != null ? String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW") : rs.getString("CT05SRAW");
                    Bibliography newGetAllBy = new Bibliography(ISBD.getSubfield(Handler.removeSubf(data), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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

    public static List<Bibliography> getInTag(String tag, String criteria, String module, String type) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        String sTableName = null;
        sTableName = Bibliography.getMarkTagTableName(tag);
        String modules = Handler.tblName(module);
        String query = null;
        boolean retval = criteria.contains("%");
        criteria = Handler.removeSpecialChar(criteria);
        query = type == "" ? (retval ? (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + modules + "PONT, " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05POINTER = " + modules + "06POINTER AND " + modules + "06TAG='" + tag + "' and" + " CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0") : (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + modules + "PONT, " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05POINTER = " + modules + "06POINTER AND " + modules + "06TAG='" + tag + "' and" + " CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0")) : (retval ? (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + modules + "PONT, " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05POINTER = " + modules + "06POINTER AND " + modules + "06TAG='" + tag + "' and" + " CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' and CT05HITS > 0") : (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + Bibliography.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + modules + "PONT, " + Bibliography.getMarkTagTableName(tag) + ", " + modules + "matm" + " WHERE CT05POINTER = " + modules + "06POINTER AND " + modules + "02matno=" + modules + "06matno and " + modules + "06TAG='" + tag + "' and" + " CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%' and CT05HITS > 0 and " + modules + "02type='" + type + "'"));
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                System.out.println(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    String data = "";
                    data = rs.getString("CT05RAW") != null ? String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW") : rs.getString("CT05SRAW");
                    Bibliography newGetAllBy = new Bibliography(ISBD.getSubfield(Handler.removeSubf(data), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
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

    public static List<Bibliography> CTINDX(String marcTag, String tableName, String condition, String tag, List<ISBD> isbd, String type) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        boolean retval = condition.contains("%");
        String query = null;
        query = type == "" ? (retval ? (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW") : (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' ORDER BY CT05SRAW")) : (retval ? (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "' ORDER BY CT05SRAW") : (!tag.equals("5%") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "INNER JOIN CTMATM ON CTMATM.CT02MATNO = CTPONT.CT06MATNO " + "WHERE GL17TAG = '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' AND CT02TYPE='" + type + "' ORDER BY CT05SRAW" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05RAW FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "INNER JOIN CTMATM ON CTMATM.CT02MATNO = CTPONT.CT06MATNO " + "WHERE GL17TAG LIKE '" + tag + "' AND CT05SCONV LIKE '" + Handler.convUpperCase(condition) + "%' AND CT02TYPE='" + type + "' ORDER BY CT05SRAW"));
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography newGetAllBy = new Bibliography(ISBD.getSubfield(String.valueOf(rs.getString("CT05SRAW")) + rs.getString("CT05RAW"), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
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

    public static List<Bibliography> getAllRaw(String pointer) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        String query = "SELECT Distinct T1.CT06MATNO AS MATNO, T10.CT02TYPE AS CTTYPE, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T9.CT05SRAW AS INDX FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG IN ('260','264') AND T6.CT06POINTER = (SELECT T11.CT05POINTER FROM CTPUBL T11 WHERE T6.CT06POINTER = T11.CT05POINTER AND T11.CT05SRAW LIKE '|a%')) LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) LEFT JOIN  CTMATM T10 ON (T2.CT06MATNO = T10.CT02MATNO) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> callisbd = ISBD.getPunctuation("090");
                List<ISBD> publisbd = ISBD.getPunctuation("260");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography newGetAllBy = new Bibliography(rs.getString("MATNO"), rs.getString("CTTYPE"), ISBD.getSubfield(Handler.removeSubf(rs.getString("TITLE")), isbd), rs.getString("CALLNO"), ISBD.getSubfield(rs.getString("PUBL"), publisbd), Handler.getYear(rs.getString("PUBL")), rs.getString("INDX"));
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

    public static List<Bibliography> searchByTitle(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("245");
        return Bibliography.getAllBy("001", "CTTITL", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchByName(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("100");
        return Bibliography.getAllBy("002", "CTAUTH", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchBySubject(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("650");
        return Bibliography.getAllBy("003", "CTSUBJ", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchByPubl(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("260");
        return Bibliography.getAllBy("004", "CTPUBL", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchBySeries(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("490");
        return Bibliography.getAllBy("005", "CTSERI", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchByCallNo(String criteria, String module, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("090");
        return Bibliography.getAllBy("006", "CTCALL", criteria, isbd, module, type);
    }

    public static List<Bibliography> searchByIndx(String criteria, String tag, String type) throws SQLException {
        List<ISBD> isbd = ISBD.getPunctuation("500");
        return Bibliography.CTINDX("007", "CTINDX", criteria, tag, isbd, type);
    }

    public static List<Bibliography> retrieveTitle(String pointer) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS TITLE FROM CTTITL INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("TITLE"));
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

    public static List<Bibliography> retrieveCallNo(String pointer) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS CALLNO FROM CTCALL INNER JOIN CTPONT ON CTCALL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("CALLNO"));
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
        return Bibliography.rawToDisplayFormat(result);
    }

    public static List<Bibliography> getByBufferNo(String bufferno, String type, String tableName) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "";
        query = type == "" ? "SELECT " + tableName + "04MATNO," + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK " + "WHERE " + tableName + "04MATNO LIKE '" + bufferno + "' AND " + tableName + "04STATUS='A' AND " + tableName + "04TAG='245' order by " + tableName + "04tag" : "  SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK, " + tableName + "MATM " + "where " + tableName + "02MATNO= REPLACE(" + tableName + "04MATNO,SUBSTRING(" + tableName + "04MATNO, 1, 1),'0') " + " and " + tableName + "04MATNO LIKE '" + bufferno + "' AND " + tableName + "02TYPE='" + type + "' AND " + tableName + "04STATUS='A' AND " + tableName + "04TAG='245' order by " + tableName + "04tag";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                System.out.println(query);
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString(String.valueOf(tableName) + "04MATNO"), ISBD.getSubfield(Handler.removeSubf(rs.getString(String.valueOf(tableName) + "04RAW")), isbd), rs.getString(String.valueOf(tableName) + "04MARC"));
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

    public static List<Bibliography> getByBufferAccNo(String bufferno, String type, String tableName) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "";
        query = type == "" ? "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK, CTDOCM " + "where CT03MATNO= REPLACE(" + tableName + "04MATNO,SUBSTRING(" + tableName + "04MATNO, 1, 1),'0') " + " and CT03DOCNO= '" + bufferno + "' AND " + tableName + "04STATUS='A' and " + tableName + "04tag='245' order by " + tableName + "04tag" : "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK, CTDOCM, " + tableName + "MATM " + "where CT03MATNO= REPLACE(" + tableName + "04MATNO,SUBSTRING(" + tableName + "04MATNO, 1, 1),'0') " + "AND " + tableName + "02MATNO= REPLACE(" + tableName + "04MATNO,SUBSTRING(" + tableName + "04MATNO, 1, 1),'0') " + " and CT03DOCNO= '" + bufferno + "' AND " + tableName + "02TYPE='" + type + "' AND " + tableName + "04STATUS='A' and " + tableName + "04tag='245' order by " + tableName + "04tag";
        System.out.println("Test" + query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString(String.valueOf(tableName) + "04MATNO"), ISBD.getSubfield(Handler.removeSubf(rs.getString(String.valueOf(tableName) + "04RAW")), isbd), rs.getString(String.valueOf(tableName) + "04MARC"));
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

    public static List<Bibliography> retrieve(String bufferno) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "  SELECT CT04MATNO, CT04RAW, CT04MARC FROM CTWORK, CTDOCM where CT03MATNO= REPLACE(CT04MATNO,SUBSTRING(CT04MATNO, 1, 1),'0')  and CT03DOCNO= '" + bufferno + "' AND CT04STATUS='A' and ct04tag='245' order by ct04tag";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("CT04MATNO"), ISBD.getSubfield(Handler.removeSubf(rs.getString("CT04RAW")), isbd), rs.getString("CT04MARC"));
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

    public static List<Bibliography> getByBufferTag(String tag, String criteria, String type, String tableName) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        criteria = Handler.removeSpecialChar(criteria);
        boolean retval = criteria.contains("%");
        String query = "";
        query = type == "" ? (retval ? "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK " + "WHERE " + tableName + "04SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' AND " + tableName + "04STATUS='A' AND " + tableName + "04TAG='" + tag + "'" : "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK " + "WHERE " + tableName + "04SCONV LIKE N'" + Handler.convUpperCase(criteria) + "%' AND " + tableName + "04STATUS='A' AND " + tableName + "04TAG='" + tag + "'") : (retval ? "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM CTWORK , " + tableName + "MATM " + "WHERE " + tableName + "02MATNO= REPLACE(" + tableName + "04MATNO,SUBSTRING(" + tableName + "04MATNO, 1, 1),'0') " + "\tand " + tableName + "04SCONV LIKE N'" + Handler.convUpperCase(criteria) + "' AND " + tableName + "04STATUS='A' " + "AND " + tableName + "04TAG='" + tag + "' AND " + tableName + "02TYPE='" + type + "'" : "SELECT " + tableName + "04MATNO, " + tableName + "04RAW, " + tableName + "04MARC FROM " + tableName + "WORK, CTMATM " + "WHERE " + tableName + "02MATNO= REPLACE(CT04MATNO,SUBSTRING(CT04MATNO, 1, 1),'0') " + "and " + tableName + "04SCONV LIKE N'" + Handler.convUpperCase(criteria) + "%' AND CT04STATUS='A' " + "AND " + tableName + "04TAG='" + tag + "' AND CT02TYPE='" + type + "'");
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString(String.valueOf(tableName) + "04MATNO"), ISBD.getSubfield(Handler.removeSubf(rs.getString(String.valueOf(tableName) + "04RAW")), isbd), rs.getString(String.valueOf(tableName) + "04MARC"));
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

    public static List<Bibliography> getByCtrlNo(String ctrlno) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE CT06POINTER=CT05POINTER AND CT06MATNO = '" + ctrlno + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("CT05SRAW"));
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

    public static List<Bibliography> getOfficerID_Buffer(String patrid, String type, String startDate, String stopDate, String cattype) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "";
        if (cattype == "") {
            query = "SELECT CT04MATNO, CT04RAW, CT04MARC FROM CTWORK WHERE CT04TAG='245' ";
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
        } else {
            query = "SELECT CT04MATNO, CT04RAW, CT04MARC FROM CTWORK, CTMATM WHERE CT04TAG='245' and CT02MATNO= REPLACE(CT04MATNO,SUBSTRING(CT04MATNO, 1, 1),'0') AND CT02TYPE='" + cattype + "' ";
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
        }
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("CT04MATNO"), ISBD.getSubfield(Handler.removeSubf(rs.getString("CT04RAW")), isbd), rs.getString("CT04MARC"));
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

    public static List<Bibliography> getOfficerID_Index(String patrid, String type, String startDate, String stopDate, String cattype) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "";
        query = cattype == "" ? "SELECT CT05POINTER, CT05SRAW, CT05HITS FROM CTMATM, CTPONT, CTTITL WHERE CT02MATNO = CT06MATNO AND CT06POINTER = CT05POINTER AND CT06TAG='245' " : "SELECT CT05POINTER, CT05SRAW, CT05HITS FROM CTMATM, CTPONT, CTTITL WHERE CT02MATNO = CT06MATNO AND CT06POINTER = CT05POINTER AND CT06TAG='245' and ct02type='" + cattype + "' ";
        if (startDate != "" && stopDate != "") {
            if (type.equals("creator")) {
                query = String.valueOf(query) + "AND CT02CREBY = '" + patrid + "' AND  " + "CT02CRDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
            } else if (type.equals("modifier")) {
                query = String.valueOf(query) + "AND CT02IDXBY = '" + patrid + "' AND " + "CT02IDXDATE BETWEEN '" + DateTime.displayToDBFormat(startDate) + "' AND '" + DateTime.displayToDBFormat(stopDate) + "'";
            }
        } else if (type.equals("creator")) {
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
                    Bibliography result = new Bibliography(ISBD.getSubfield(Handler.removeSubf(rs.getString("CT05SRAW")), isbd), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
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

    public static boolean updateStatus(String ctrlno, String status) throws SQLException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT02MATNO", ctrlno);
        valueStr.put("CT02PRIVATE", status);
        String query = QueryBuilder.createUpdateQuery("CTMATM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static String getPrivate(String ctrlno) {
        Statement stmt = null;
        String query = "SELECT CT02PRIVATE FROM CTMATM WHERE CT02MATNO = '" + ctrlno + "'";
        String status = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    status = rs.getString("CT02PRIVATE");
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
        return status;
    }
}
