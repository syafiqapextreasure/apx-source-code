/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.LocationCode;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationCriteria {
    static Connection con;
    private String locacode = null;
    private String desc = null;
    private String branch = null;
    private String special = null;
    private String collection = null;
    private String GL71BRNC = null;
    private String GL71DESC = null;

    public LocationCriteria(String locacode, String desc, String branch, String special, String collection) {
        this.locacode = locacode;
        this.desc = desc;
        this.branch = branch;
        this.special = special;
        this.collection = collection;
    }

    public LocationCriteria(String GL71BRNC, String GL71DESC) {
        this.GL71BRNC = GL71BRNC;
        this.GL71DESC = GL71DESC;
    }

    public String getGL71BRNC() {
        return this.GL71BRNC;
    }

    public String getGL71DESC() {
        return this.GL71DESC;
    }

    public String getlocacode() {
        return this.locacode;
    }

    public String getdesc() {
        return this.desc;
    }

    public String getbranch() {
        return this.branch;
    }

    public String getspecial() {
        return this.special;
    }

    public String getcollection() {
        return this.collection;
    }

    public static List<LocationCriteria> searchLocaByCode(String criteria) {
        ArrayList<LocationCriteria> list = new ArrayList<LocationCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL05LOCA, GL05DESC, GL05BRNC, GL05SUBJECT, GL05MATCAP FROM GLLOCA WHERE";
        sql = String.valueOf(sql) + " GL05LOCA LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    LocationCriteria newSearchLocaByCode = new LocationCriteria(rs.getString("GL05LOCA"), rs.getString("GL05DESC"), rs.getString("GL05BRNC"), rs.getString("GL05SUBJECT"), rs.getString("GL05MATCAP"));
                    list.add(newSearchLocaByCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<LocationCriteria> searchLocaByBranch(String criteria) {
        ArrayList<LocationCriteria> list = new ArrayList<LocationCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL05LOCA, GL05DESC, GL05BRNC, GL05SUBJECT, GL05MATCAP FROM GLLOCA WHERE";
        sql = String.valueOf(sql) + " GL05BRNC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    LocationCriteria newSearchLocaByBranch = new LocationCriteria(rs.getString("GL05LOCA"), rs.getString("GL05DESC"), rs.getString("GL05BRNC"), rs.getString("GL05SUBJECT"), rs.getString("GL05MATCAP"));
                    list.add(newSearchLocaByBranch);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<LocationCriteria> searchLocaByDesc(String criteria) {
        ArrayList<LocationCriteria> list = new ArrayList<LocationCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL05LOCA, GL05DESC, GL05BRNC, GL05SUBJECT, GL05MATCAP FROM GLLOCA WHERE";
        sql = String.valueOf(sql) + " GL05DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    LocationCriteria newSearchLocaByDesc = new LocationCriteria(rs.getString("GL05LOCA"), rs.getString("GL05DESC"), rs.getString("GL05BRNC"), rs.getString("GL05SUBJECT"), rs.getString("GL05MATCAP"));
                    list.add(newSearchLocaByDesc);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<LocationCriteria> getLocation() {
        ArrayList<LocationCriteria> list = new ArrayList<LocationCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL71BRNC, GL71DESC FROM GLBRNC";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    LocationCriteria newSearchLocaByDesc = new LocationCriteria(rs.getString("GL71BRNC"), rs.getString("GL71DESC"));
                    list.add(newSearchLocaByDesc);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
