/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Autlink {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String tablename = null;
    private int CT05POINTER = 0;
    private int AU06POINTER = 0;
    private String AU06MATNO = null;
    private String AU06STATUS = null;
    private String AU06TAG = null;
    private String CT05AUTLINK = null;
    private String CT05SRAW = null;
    private String GL17TRUC = null;

    public Autlink(String AU06MATNO) {
        this.AU06MATNO = AU06MATNO;
    }

    public Autlink(int CT05POINTER, String CT05SRAW, String CT05AUTLINK) {
        this.CT05POINTER = CT05POINTER;
        this.CT05SRAW = CT05SRAW;
        this.CT05AUTLINK = CT05AUTLINK;
    }

    public Autlink(int AU06POINTER, String GL17TRUC) {
        this.AU06POINTER = AU06POINTER;
        this.GL17TRUC = GL17TRUC;
    }

    public String getTablename() {
        return this.tablename;
    }

    public int getCT05POINTER() {
        return this.CT05POINTER;
    }

    public int getAU06POINTER() {
        return this.AU06POINTER;
    }

    public String getAU06STATUS() {
        return this.AU06STATUS;
    }

    public String getAU06TAG() {
        return this.AU06TAG;
    }

    public String getAU06MATNO() {
        return this.AU06MATNO;
    }

    public String getCT05AUTLINK() {
        return this.CT05AUTLINK;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public String getGL17TRUC() {
        return this.GL17TRUC;
    }

    public static String getTablename(String GL17TAG) {
        String query = "Select GL17TABNAME from GLTAGP WHERE GL17TAG='" + GL17TAG + "'";
        String GL17TABNAME = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    GL17TABNAME = rs.getString("GL17TABNAME");
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
        return GL17TABNAME;
    }

    public static int getCT05POINTER(String GL17TAG, String CT05SRAW) {
        int CT05POINTER;
        block12: {
            System.out.println("Data" + CT05SRAW + Handler.convUpperCase(CT05SRAW));
            String query = "SELECT CT05POINTER FROM " + Autlink.getTablename(GL17TAG) + " WHERE CT05AUTLINK='U' and CT05SCONV='" + Handler.convUpperCase(CT05SRAW) + "'";
            CT05POINTER = 0;
            System.out.println(query);
            try {
                try {
                    c = DBConnection.getConnection();
                    stmt = c.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        CT05POINTER = Integer.parseInt(rs.getString("CT05POINTER"));
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
        System.out.println("Data" + CT05POINTER);
        return CT05POINTER;
    }

    public static Autlink getAU06MATNO(String GL17TAG, String CT05SRAW) {
        CT05SRAW = CT05SRAW.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
        System.out.println(CT05SRAW);
        String query = "SELECT AU06MATNO FROM AUPONT WHERE AU06POINTER='" + Autlink.getCT05POINTER(GL17TAG, CT05SRAW) + "'";
        Autlink AU06MATNO = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    AU06MATNO = new Autlink(rs.getString("AU06MATNO"));
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
        return AU06MATNO;
    }

    public static List<Autlink> getAU06POINTER(String AU06MATNO) {
        ArrayList<Autlink> list = new ArrayList<Autlink>();
        String query = "SELECT AU06POINTER, GL17TRUC FROM AUPONT, GLTAGP WHERE GL17TAG=AU06TAG AND GL17MARC='Y' AND AU06MATNO='" + AU06MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Autlink AU06POINTER = new Autlink(Integer.parseInt(rs.getString("AU06POINTER")), rs.getString("GL17TRUC"));
                    list.add(AU06POINTER);
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

    public static List<Autlink> getAUPONT(String CT05TAG, String AU06POINTER) {
        ArrayList<Autlink> list = new ArrayList<Autlink>();
        System.out.println(String.valueOf(Autlink.getTablename(CT05TAG)) + AU06POINTER);
        String query = "SELECT * FROM " + Autlink.getTablename(CT05TAG) + " WHERE CT05POINTER IN (" + AU06POINTER + ")";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Autlink listAU06POINTER = new Autlink(Integer.parseInt(rs.getString("CT05POINTER")), rs.getString("CT05SRAW"), rs.getString("CT05AUTLINK"));
                    list.add(listAU06POINTER);
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
