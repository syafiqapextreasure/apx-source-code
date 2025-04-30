/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchRecord {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String title = null;
    public String raw = null;
    public String matno = null;
    public int hits = 0;
    public int pointer = 0;
    public String callno = null;
    public String publisher = null;
    public String year = null;
    public String edition = null;

    public String getTitle() {
        return this.title;
    }

    public String getRaw() {
        return this.raw;
    }

    public String getCallNo() {
        return SearchRecord.rawToDisplayFormat(this.callno);
    }

    public String getPublisher() {
        return SearchRecord.getPublisher(this.publisher);
    }

    public String getYear() {
        return SearchRecord.getYear(this.year);
    }

    public String getEdition() {
        return SearchRecord.rawToDisplayFormat(this.edition);
    }

    public String getMatno() {
        return this.matno;
    }

    public int getHits() {
        return this.hits;
    }

    public int getPointer() {
        return this.pointer;
    }

    public SearchRecord(String title) {
        this.title = title;
    }

    public SearchRecord(String title, int pointer, int hits) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public SearchRecord(String title, String raw, int hits) {
        this.title = title;
        this.raw = raw;
        this.hits = hits;
    }

    public SearchRecord(String title, int hits) {
        this.title = title;
        this.hits = hits;
    }

    public SearchRecord(String matno, String title, String callno, String publisher, String year, String edition) {
        this.matno = matno;
        this.title = title;
        this.callno = callno;
        this.publisher = publisher;
        this.year = year;
        this.edition = edition;
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

    public static String getYear(String raw) {
        if (raw == null) {
            return "-";
        }
        String[] rawArray = raw.split("\\|c");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(0);
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

    public static String getPublisher(String raw) {
        if (raw == null) {
            return "-";
        }
        String[] rawArray = raw.split("\\|b");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String newData = rawArray[i].substring(0);
                String splitData = newData.substring(0, newData.indexOf("|c"));
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

    public static List<SearchRecord> getAllBy(String marcTag, String tableName, String condition) {
        ArrayList<SearchRecord> list;
        block12: {
            list = new ArrayList<SearchRecord>();
            String query = "SELECT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + ", CTPONT, GLTAGP " + "WHERE " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "AND CTPONT.CT06TAG = GLTAGP.GL17TAG " + "AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "AND GL17GRID = '" + marcTag + "' " + "AND CT05SCONV LIKE '" + condition + "' " + "ORDER BY CT05SRAW";
            System.out.println(query);
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        SearchRecord newGetAllBy = new SearchRecord(ISBD.convert(Integer.parseInt(rs.getString("CT05POINTER")), rs.getString("CT05SRAW")), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
                        list.add(newGetAllBy);
                        break block12;
                    }
                    StringBuilder htmlBuilder = new StringBuilder();
                    htmlBuilder.append("<html>");
                    htmlBuilder.append("<head><title>Hello World</title></head>");
                    htmlBuilder.append("<body><p>Look at my body!</p></body>");
                    htmlBuilder.append("</html>");
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
        return list;
    }

    public static List<SearchRecord> searchByTitle(String criteria) {
        return SearchRecord.getAllBy("001", "CTTITL", criteria);
    }

    public static List<SearchRecord> searchByName(String criteria) {
        return SearchRecord.getAllBy("002", "CTAUTH", criteria);
    }

    public static List<SearchRecord> searchBySubject(String criteria) {
        System.out.println(criteria);
        return SearchRecord.getAllBy("003", "CTSUBJ", criteria);
    }

    public static List<SearchRecord> searchByPubl(String criteria) {
        return SearchRecord.getAllBy("004", "CTPUBL", criteria);
    }

    public static List<SearchRecord> searchByPlaceOfPubl(String criteria) {
        return SearchRecord.getAllBy("004", "CTPUBL", criteria);
    }

    public static List<SearchRecord> searchByYearOfPubl(String criteria) {
        return SearchRecord.getAllBy("004", "CTPUBL", criteria);
    }

    public static List<SearchRecord> searchBySeries(String criteria) {
        return SearchRecord.getAllBy("005", "CTSERI", criteria);
    }

    public static List<SearchRecord> searchByCallNo(String criteria) {
        return SearchRecord.getAllBy("006", "CTCALL", criteria);
    }

    public static List<SearchRecord> searchByIndx(String criteria) {
        return SearchRecord.getAllBy("007", "CTINDX", criteria);
    }

    public static List<SearchRecord> getAllRaw(String pointer) {
        ArrayList<SearchRecord> list = new ArrayList<SearchRecord>();
        String query = "SELECT T1.CT06MATNO AS MATNO, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T7.CT05SRAW AS YEAR, T9.CT05SRAW AS INDX FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG = '260') LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SearchRecord newGetAllBy = new SearchRecord(rs.getString("MATNO"), rs.getString("TITLE"), rs.getString("CALLNO"), rs.getString("PUBL"), rs.getString("YEAR"), rs.getString("INDX"));
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

    public static List<SearchRecord> getByCtrlNo(String ctrlno) {
        ArrayList<SearchRecord> list = new ArrayList<SearchRecord>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE CT06POINTER=CT05POINTER AND CT06MATNO = '" + ctrlno + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SearchRecord result = new SearchRecord(rs.getString("CT05SRAW"));
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

    public static List<SearchRecord> getByBufferNo(String bufferno) {
        ArrayList<SearchRecord> list = new ArrayList<SearchRecord>();
        Statement stmt = null;
        String query = "SELECT CT04RAW FROM CTWORK WHERE CT04MATNO = '" + bufferno + "' AND CT04STATUS='A' AND CT04TAG='245'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SearchRecord result = new SearchRecord(rs.getString("CT04RAW"));
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

    public static List<SearchRecord> getByTermSearch(String criteria, String tag) {
        ArrayList<SearchRecord> list = new ArrayList<SearchRecord>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW, CT05HITS FROM " + SearchRecord.getByTag(tag) + " WHERE CT05SCONV LIKE '" + criteria + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SearchRecord result = new SearchRecord(Handler.rawToDisplayFormat1(rs.getString("CT05SRAW")), rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05HITS")));
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

    public static String getByTag(String tag) {
        String query = "Select GL17TABNAME from GLTAGP where GL17TAG='" + tag + "' and GL17MARC='U'";
        String results = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    results = rs.getString("GL17TABNAME");
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
        return results;
    }
}
