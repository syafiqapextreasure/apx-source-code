/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.cataloging.Template_Maint;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tag_Handler {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL17MARC = null;
    private String GL17TAG = null;
    private String GL17TABNAME = null;
    private String GL17DESC = null;
    private String GL17MANDA = null;
    private String GL17REPEAT = null;
    private String GL17GRIDID = null;
    private String GL17AUTH = null;
    private String GL17STOP = null;

    public Tag_Handler(String GL17MARC, String GL17TAG, String GL17DESC, String GL17MANDA, String GL17REPEAT, String GL17AUTH) {
        this.GL17MARC = GL17MARC;
        this.GL17TAG = GL17TAG;
        this.GL17DESC = GL17DESC;
        this.GL17MANDA = GL17MANDA;
        this.GL17REPEAT = GL17REPEAT;
        this.GL17AUTH = GL17AUTH;
    }

    public Tag_Handler(String GL17TAG, String GL17DESC, String GL17TABNAME, String GL17GRIDID) {
        this.GL17TAG = GL17TAG;
        this.GL17DESC = GL17DESC;
        this.GL17TABNAME = GL17TABNAME;
        this.GL17GRIDID = GL17GRIDID;
    }

    public Tag_Handler(String GL17STOP) {
        this.GL17STOP = GL17STOP;
    }

    public String getGL17MARC() {
        return this.GL17MARC;
    }

    public String getGL17TAG() {
        return this.GL17TAG;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getGL17MANDA() {
        return this.GL17MANDA;
    }

    public String getGL17REPEAT() {
        return this.GL17REPEAT;
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getGL17STOP() {
        return this.GL17STOP;
    }

    public String getGL17GRID() {
        return this.GL17GRIDID;
    }

    public String getGL17AUTH() {
        return this.GL17AUTH;
    }

    public static List<Tag_Handler> getBibTags() {
        ArrayList<Tag_Handler> list = new ArrayList<Tag_Handler>();
        try {
            String query = "SELECT GL17TAG, GL17DESC, GL17TABNAME, GL17GRID FROM GLTAGP WHERE GL17MARC='U'";
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag_Handler tagInfo = new Tag_Handler(rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17TABNAME"), rs.getString("GL17GRID"));
                list.add(tagInfo);
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public static Tag_Handler GL17STOP(String tag) {
        String query = "SELECT GL17STOP FROM GLTAGP WHERE GL17TAG='" + tag + "' " + "AND GL17MARC='U'";
        Tag_Handler getGL17STOP = null;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getGL17STOP = new Tag_Handler(rs.getString("GL17STOP"));
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
        return getGL17STOP;
    }

    public static boolean stopword(String GL99WORD) {
        String query = "Select COUNT(GL99WORD) AS Count FROM GLSTOPWORD WHERE GL99WORD='" + GL99WORD + "' ";
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static List<Tag_Handler> getTags() {
        ArrayList<Tag_Handler> list = new ArrayList<Tag_Handler>();
        try {
            String query = "SELECT GL17MARC, GL17TAG, GL17DESC, GL17MANDA, GL17REPEAT, GL17AUTFLAG FROM GLTAGP WHERE GL17MARC='U'";
            System.out.println(query);
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag_Handler tagInfo = new Tag_Handler(rs.getString("GL17MARC"), rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17MANDA"), rs.getString("GL17REPEAT"), rs.getString("GL17AUTFLAG"));
                list.add(tagInfo);
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public static boolean tagExists(String CTTPLTAG, String CT16SEQNO) {
        String query = "Select COUNT(CT16TAG) AS Count FROM CTTPL, GLTAGP WHERE GL17TAG=CT16TAG and CT16TAG='" + CTTPLTAG + "' " + "and GL17REPEAT='N' and " + "GL17MARC='U' and CT16MSTR='" + Integer.parseInt(CT16SEQNO) + "'";
        boolean exist = false;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}
