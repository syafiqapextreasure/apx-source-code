/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public Bibliography(String matno, String title, String callno, String year, String edition) {
        this.matno = matno;
        this.title = title;
        this.callno = callno;
        this.year = year;
        this.edition = edition;
    }

    public Bibliography(String title) {
        this.title = title;
    }

    public Bibliography(String title, int pointer, int hits) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
    }

    public String getTitle() {
        return Bibliography.rawToDisplayFormat(this.title);
    }

    public String getCallNo() {
        return Bibliography.rawToDisplayFormat(this.callno);
    }

    public String getYear() {
        return Bibliography.rawToDisplayFormat(this.year);
    }

    public String getEdition() {
        return Bibliography.rawToDisplayFormat(this.edition);
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

    public static List<Bibliography> CT_SearchByAccno(String controlNoInput) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
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
                Bibliography CT_SearchByAccno = new Bibliography(rs.getString("CT05SRAW"));
                list.add(CT_SearchByAccno);
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

    public static List<Bibliography> getAllBy(String marcTag, String tableName, String condition) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        String query = "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS FROM " + tableName + " INNER JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + "INNER JOIN GLTAGP ON CTPONT.CT06TAG = GLTAGP.GL17TAG AND CTPONT.CT06MARC=GLTAGP.GL17MARC " + "WHERE GL17GRID = '" + marcTag + "' AND CT05SCONV LIKE '" + condition + "' ORDER BY CT05SRAW";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography newGetAllBy = new Bibliography(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")));
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
        String query = "SELECT T1.CT06MATNO AS MATNO, T3.CT05SRAW AS TITLE, T5.CT05SRAW AS CALLNO, T7.CT05SRAW AS PUBL, T9.CT05SRAW AS INDX FROM CTPONT T1 LEFT JOIN  CTPONT T2 ON (T1.CT06MATNO = T2.CT06MATNO AND T2.CT06TAG = '245') LEFT JOIN  CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER) LEFT JOIN  CTPONT T4 ON (T1.CT06MATNO = T4.CT06MATNO AND T4.CT06TAG = '090') LEFT JOIN  CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER) LEFT JOIN  CTPONT T6 ON (T1.CT06MATNO = T6.CT06MATNO AND T6.CT06TAG = '260') LEFT JOIN  CTPUBL T7 ON (T6.CT06POINTER = T7.CT05POINTER) LEFT JOIN  CTPONT T8 ON (T1.CT06MATNO = T8.CT06MATNO AND T8.CT06TAG = '250') LEFT JOIN  CTINDX T9 ON (T8.CT06POINTER = T9.CT05POINTER) WHERE T1.CT06TAG = '245' AND T1.CT06MATNO IN (SELECT D1.CT06MATNO FROM CTPONT D1 WHERE D1.CT06POINTER='" + pointer + "')";
        System.out.println(query);
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography newGetAllBy = new Bibliography(rs.getString("MATNO"), rs.getString("TITLE"), rs.getString("CALLNO"), rs.getString("PUBL"), rs.getString("INDX"));
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

    public static List<Bibliography> searchByTitle(String criteria) {
        return Bibliography.getAllBy("001", "CTTITL", criteria);
    }

    public static List<Bibliography> searchByName(String criteria) {
        return Bibliography.getAllBy("002", "CTAUTH", criteria);
    }

    public static List<Bibliography> searchBySubject(String criteria) {
        return Bibliography.getAllBy("003", "CTSUBJ", criteria);
    }

    public static List<Bibliography> searchByPubl(String criteria) {
        return Bibliography.getAllBy("004", "CTPUBL", criteria);
    }

    public static List<Bibliography> searchBySeries(String criteria) {
        return Bibliography.getAllBy("005", "CTSERI", criteria);
    }

    public static List<Bibliography> searchByCallNo(String criteria) {
        return Bibliography.getAllBy("006", "CTCALL", criteria);
    }

    public static List<Bibliography> searchByIndx(String criteria) {
        return Bibliography.getAllBy("007", "CTINDX", criteria);
    }

    public static List<Bibliography> retrieveTitle(String pointer) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW AS TITLE FROM CTTITL INNER JOIN CTPONT ON CTTITL.CT05POINTER = CTPONT.CT06POINTER WHERE CT06POINTER = '" + pointer + "' ";
        System.out.println(query);
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
        System.out.println(query);
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

    public static List<Bibliography> getByBufferNo(String bufferno) {
        ArrayList<Bibliography> list = new ArrayList<Bibliography>();
        Statement stmt = null;
        String query = "SELECT CT04RAW FROM CTWORK WHERE CT04MATNO = '" + bufferno + "' AND CT04STATUS='A' AND CT04TAG='245'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Bibliography result = new Bibliography(rs.getString("CT04RAW"));
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
        System.out.println(query);
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
}
