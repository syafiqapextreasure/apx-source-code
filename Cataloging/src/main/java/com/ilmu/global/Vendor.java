/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
    static Connection con;
    private String Code = null;
    private String Name = null;

    public Vendor(String Code, String Name) {
        this.Code = Code;
        this.Name = Name;
    }

    public String getCode() {
        return this.Code;
    }

    public String getName() {
        return this.Name;
    }

    public static List<Vendor> searchVendorByCode(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND GL39CODE LIKE '%" + Handler.convUpperCase(criteria.trim()) + "%' ";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchVendByCode = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchVendByCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Vendor> searchVendorByName(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND GL39NAME LIKE '%" + criteria + "%' ";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchVendByName = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchVendByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getVendorNameByVendorCode(String vendorCode) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND GL39CODE = '" + vendorCode + "' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL39NAME");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<Vendor> searchBinderByCode(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND GL39CODE LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchBinderByCode = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchBinderByCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Vendor> searchBinderByName(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND GL39NAME LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchBinderByName = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchBinderByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getBinderNameByBinderCode(String binderCode) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND GL39CODE = '" + binderCode + "' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL39NAME");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static List<Vendor> searchPublisherByCode(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39PUB = 'Y' AND GL39CODE LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchPublisherByCode = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchPublisherByCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Vendor> searchPublisherByName(String criteria) {
        ArrayList<Vendor> list = new ArrayList<Vendor>();
        Statement stmt = null;
        String query = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39PUB = 'Y' AND GL39NAME LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newSearchPublisherByName = new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME"));
                    list.add(newSearchPublisherByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static String getPublisherNameByPublisherCode(String publisherCode) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39PUB = 'Y' AND GL39CODE = '" + publisherCode + "' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL39NAME");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
