/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class viewNote {
    private String id;
    private String date;
    private String time;
    private String title;
    private String author;
    private String viewpermission;
    private String editpermission;
    private String note;
    private String refkey;
    private String printpms;

    public String getid() {
        return Handler.ifIsNull(this.id);
    }

    public String getdate() {
        return Handler.ifIsNull(this.date);
    }

    public String gettime() {
        return Handler.ifIsNull(this.time);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getauthor() {
        return Handler.ifIsNull(this.author);
    }

    public String getviewpermission() {
        return Handler.ifIsNull(this.viewpermission);
    }

    public String geteditpermissionn() {
        return Handler.ifIsNull(this.editpermission);
    }

    public String getnote() {
        return Handler.ifIsNull(this.note);
    }

    public String getrefkey() {
        return Handler.ifIsNull(this.refkey);
    }

    public String getprintpms() {
        return Handler.ifIsNull(this.printpms);
    }

    public viewNote(String id, String date, String time, String title, String author, String viewpermission, String editpermission) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.author = author;
        this.viewpermission = viewpermission;
        this.editpermission = editpermission;
    }

    public viewNote(String id, String refkey, String title, String note, String viewpermission, String editpermission, String date, String time, String printpms) {
        this.id = id;
        this.refkey = refkey;
        this.title = title;
        this.note = note;
        this.viewpermission = viewpermission;
        this.editpermission = editpermission;
        this.date = date;
        this.time = time;
        this.printpms = printpms;
    }

    public static List<viewNote> getViewNoteTable(String acat, String ref) {
        ArrayList<viewNote> list = new ArrayList<viewNote>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        query = "  SELECT AN01TRXNO, AN01MESGDATE, AN01MESGTIME, AN01TITLE, AN01AUTHOR, AN01VIEWPMS, AN01EDITPMS FROM ANMESG WHERE AN01ACAT = '" + acat + "' " + "AND AN01REFKEY = '" + ref + "'";
        System.out.println("query getViewNoteTable : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String time = rs.getString("AN01MESGTIME");
                    time = time == null ? "" : DateTime.Time(rs.getString("AN01MESGTIME"));
                    viewNote loadtabledetail = new viewNote(Handler.ifIsNull(rs.getString("AN01TRXNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AN01MESGDATE"))), time, Handler.ifIsNull(rs.getString("AN01TITLE")), Handler.ifIsNull(rs.getString("AN01AUTHOR")), Handler.ifIsNull(rs.getString("AN01VIEWPMS")), Handler.ifIsNull(rs.getString("AN01EDITPMS")));
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

    public static List<viewNote> viewedit(String id) {
        ArrayList<viewNote> list = new ArrayList<viewNote>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        query = "SELECT AN01TRXNO, AN01REFKEY, AN01TITLE, AN01NOTE, AN01VIEWPMS, AN01EDITPMS, AN01MESGDATE, AN01MESGTIME, AN01PRNTPMS FROM ANMESG WHERE AN01TRXNO = '" + id + "'";
        System.out.println("query viewedit : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String time = rs.getString("AN01MESGTIME");
                    time = time == null ? "" : DateTime.Time(rs.getString("AN01MESGTIME"));
                    viewNote loadtabledetail = new viewNote(Handler.ifIsNull(rs.getString("AN01TRXNO")), Handler.ifIsNull(rs.getString("AN01REFKEY")), Handler.ifIsNull(rs.getString("AN01TITLE")), Handler.ifIsNull(rs.getString("AN01NOTE")), Handler.ifIsNull(rs.getString("AN01VIEWPMS")), Handler.ifIsNull(rs.getString("AN01EDITPMS")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AN01MESGDATE"))), time, Handler.ifIsNull(rs.getString("AN01PRNTPMS")));
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

    public static boolean saveNote(String acat, String refkey, String title, String note, String viewpms, String editpms, String author, String vendor) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        int annote = Glnumb.GetBuffPoint("ANNOTE");
        System.out.println(String.valueOf(annote) + "annote");
        int newannote = new Integer(annote + 1);
        Glnumb.updatinglnumb(newannote, "ANNOTE");
        valueInt.put("AN01TRXNO", newannote);
        valueStr.put("AN01ACAT", acat);
        valueStr.put("AN01REFKEY", refkey);
        valueStr.put("AN01TITLE", title);
        valueStr.put("AN01NOTE", note);
        valueStr.put("AN01VIEWPMS", viewpms);
        valueStr.put("AN01EDITPMS", editpms);
        valueStr.put("AN01AUTHOR", author);
        valueStr.put("AN01PRNTPMS", vendor);
        valueStr.put("AN01MESGDATE", DateTime.getTodaySystemDate());
        valueStr.put("AN01MESGTIME", DateTime.getTodayTime());
        String query = QueryBuilder.createInsertQuery("ANMESG", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updatingnote(String idnote, String notetitle, String note, String viewprm, String editprm, String vendor) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("AN01TRXNO", idnote);
        valueStr.put("AN01TITLE", notetitle);
        valueStr.put("AN01NOTE", note);
        valueStr.put("AN01VIEWPMS", viewprm);
        valueStr.put("AN01EDITPMS", editprm);
        valueStr.put("AN01PRNTPMS", vendor);
        String query = QueryBuilder.createUpdateQuery("ANMESG", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static String GetGroup(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL14GRID FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + value + "')";
        System.out.println("SQL GetGroup" + sSQLStmt);
        String getGroupid = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getGroupid = rs.getString("GL14GRID");
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
        return getGroupid;
    }

    public static void deleteNote(String id) {
        String query = "DELETE FROM ANMESG WHERE AN01TRXNO = '" + id + "'";
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
    }

    public static int CheckIfExist(String id) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(*) AS COUNT FROM ANMESG WHERE UPPER(AN01REFKEY) = UPPER('" + id + "')";
        System.out.println("SQL CheckIfExist" + sSQLStmt);
        int getTolNote = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getTolNote = rs.getInt("COUNT");
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
        return getTolNote;
    }
}
