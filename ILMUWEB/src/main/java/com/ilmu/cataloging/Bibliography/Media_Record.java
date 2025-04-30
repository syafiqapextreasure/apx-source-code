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

public class Media_Record {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL26START = null;
    private String GL25START = null;
    private String GL26CODE = null;
    private String GL26STYLE = null;
    private String GL26DESC = null;
    private String GL26MSTRCODE = null;

    public Media_Record(String GL26START, String GL26CODE, String GL26DESC) {
        this.GL26START = GL26START;
        this.GL26CODE = GL26CODE;
        this.GL26DESC = GL26DESC;
    }

    public Media_Record(String GL26MSTRCODE, String GL25START, String GL26DESC, String GL26STYLE) {
        this.GL26MSTRCODE = GL26MSTRCODE;
        this.GL25START = GL25START;
        this.GL26DESC = GL26DESC;
        this.GL26STYLE = GL26STYLE;
    }

    public String getGL26START() {
        return this.GL26START;
    }

    public String getGL25START() {
        return this.GL25START;
    }

    public String getGL26CODE() {
        return this.GL26CODE;
    }

    public String getGL26DESC() {
        return this.GL26DESC;
    }

    public String getGL26STYLE() {
        return this.GL26STYLE;
    }

    public String getGL26MSTRCODE() {
        return this.GL26MSTRCODE;
    }

    public static List<Media_Record> getDistinct_MediNew(String controlTag) {
        ArrayList<Media_Record> list = new ArrayList<Media_Record>();
        String query = "Select DISTINCT GL25MSTRCODE, GL25START, GL25DESC, GL25STYLE FROM GLMEDINEW WHERE GL25MSTRCODE='" + controlTag + "'order by GL25START";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Media_Record newDistinct_MediNew = new Media_Record(rs.getString("GL25MSTRCODE"), rs.getString("GL25START"), rs.getString("GL25DESC"), rs.getString("GL25STYLE"));
                    list.add(newDistinct_MediNew);
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

    public static List<Media_Record> getMediNew(String controlTag) {
        ArrayList<Media_Record> list = new ArrayList<Media_Record>();
        String query = "Select GL26START, GL26CODE, GL26DESC FROM GLINFONEW WHERE GL26MSTRCODE='" + controlTag + "'order by GL26START";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Media_Record newMediNew = new Media_Record(rs.getString("GL26START"), rs.getString("GL26CODE"), rs.getString("GL26DESC"));
                    list.add(newMediNew);
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

    public static List<Media_Record> getInfoNew(String controlTag) {
        ArrayList<Media_Record> list = new ArrayList<Media_Record>();
        String query = "SELECT GL26START, min(GL26CODE) as CODE,GL26MSTRCODE FROM GLINFONEW WHERE GL26MSTRCODE='" + controlTag + "' GROUP BY GL26MSTRCODE, GL26START";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Media_Record newInfoNew = new Media_Record(rs.getString("GL26START"), rs.getString("CODE"), rs.getString("GL26MSTRCODE"));
                    list.add(newInfoNew);
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
