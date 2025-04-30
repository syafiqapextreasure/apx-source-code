/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.cataloging.Global.SearchRecord;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Term_Search {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String CT05RAW = null;
    public String CT05HITS = null;
    public String CT05TITLE = null;
    public String GL17TABNAME = null;

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

    public static List<SearchRecord> getByTermSearch(String criteria, String tag) {
        ArrayList<SearchRecord> list = new ArrayList<SearchRecord>();
        Statement stmt = null;
        String query = "SELECT CT05SRAW, CT05HITS FROM " + Term_Search.getByTag(tag) + " WHERE CT05SCONV LIKE '" + criteria + "'";
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
}
