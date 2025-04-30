/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.Authority.AuthorityGlobal;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Global_Retrival {
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
    public String authlink = null;

    public Global_Retrival(String title, int pointer, int hits, String authlink) {
        this.title = title;
        this.pointer = pointer;
        this.hits = hits;
        this.authlink = authlink;
    }

    public int getHits() {
        return this.hits;
    }

    public int getPointer() {
        return this.pointer;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthLink() {
        return this.authlink;
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

    public static List<Global_Retrival> getInTag(String tag, String criteria) {
        ArrayList<Global_Retrival> list = new ArrayList<Global_Retrival>();
        String sTableName = null;
        sTableName = Global_Retrival.getMarkTagTableName(tag);
        String query = null;
        boolean retval = criteria.contains("%");
        System.out.println("2223etest");
        criteria = criteria.replaceAll("[^A-Za-z0-9% ][^\\p{N}\\p{P}\\p{Z}\\p{L}\\p{M}*]+", " ");
        criteria = criteria.replaceAll("[^a-zA-Z0-9% ]", " ");
        criteria = criteria.trim().replaceAll(" +", " ");
        query = retval ? (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS,CT05AUTLINK FROM " + Global_Retrival.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "'" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS,CT05AUTLINK FROM CTPONT, " + Global_Retrival.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE N'" + Handler.convUpperCase(criteria) + "'") : (!sTableName.equals("CTINDX") ? "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS, CT05AUTLINK FROM " + Global_Retrival.getMarkTagTableName(tag) + " WHERE CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%'" : "SELECT DISTINCT CT05POINTER, CT05SRAW, CT05HITS,CT05AUTLINK FROM CTPONT, " + Global_Retrival.getMarkTagTableName(tag) + " WHERE CT05POINTER = CT06POINTER AND CT06TAG='" + tag + "' and" + " CT05SCONV LIKE '" + Handler.convUpperCase(criteria) + "%'");
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Global_Retrival newGetAllBy = new Global_Retrival(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), Integer.parseInt(rs.getString("CT05HITS")), rs.getString("CT05AUTLINK"));
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
}
