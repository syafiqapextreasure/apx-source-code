/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.VendorCode;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendorCriteria {
    static Connection con;
    private String vendorCode = null;
    private String vendorName = null;
    private String binder = null;
    private String vendor = null;
    private String publisher = null;

    public VendorCriteria(String vendorCode, String vendorName, String binder, String vendor, String publisher) {
        this.vendorCode = vendorCode;
        this.vendorName = vendorName;
        this.binder = binder;
        this.vendor = vendor;
        this.publisher = publisher;
    }

    public String getvendorCode() {
        return this.vendorCode;
    }

    public String getvendorName() {
        return this.vendorName;
    }

    public String getbinder() {
        return this.binder;
    }

    public String getvendor() {
        return this.vendor;
    }

    public String getpublisher() {
        return this.publisher;
    }

    public static List<VendorCriteria> searchVendorByCode(String criteria) {
        ArrayList<VendorCriteria> list = new ArrayList<VendorCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL39CODE, GL39NAME, GL39BINDER, GL39SUPPLIER, GL39PUB FROM GLVEND WHERE";
        sql = String.valueOf(sql) + " GL39CODE LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    VendorCriteria newSearchVendorByCode = new VendorCriteria(rs.getString("GL39CODE"), rs.getString("GL39NAME"), rs.getString("GL39BINDER"), rs.getString("GL39SUPPLIER"), rs.getString("GL39PUB"));
                    list.add(newSearchVendorByCode);
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

    public static List<VendorCriteria> searchVendorByName(String criteria) {
        ArrayList<VendorCriteria> list = new ArrayList<VendorCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL39CODE, GL39NAME, GL39BINDER, GL39SUPPLIER, GL39PUB FROM GLVEND WHERE";
        sql = String.valueOf(sql) + " GL39NAME LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    VendorCriteria newSearchVendorByName = new VendorCriteria(rs.getString("GL39CODE"), rs.getString("GL39NAME"), rs.getString("GL39BINDER"), rs.getString("GL39SUPPLIER"), rs.getString("GL39PUB"));
                    list.add(newSearchVendorByName);
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

    public static List<VendorCriteria> searchVendorByPic(String criteria) {
        ArrayList<VendorCriteria> list = new ArrayList<VendorCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL39CODE, GL39NAME, GL39BINDER, GL39SUPPLIER, GL39PUB FROM GLVEND WHERE";
        sql = String.valueOf(sql) + " GL39PERINC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    VendorCriteria newSearchVendorByPic = new VendorCriteria(rs.getString("GL39CODE"), rs.getString("GL39NAME"), rs.getString("GL39BINDER"), rs.getString("GL39SUPPLIER"), rs.getString("GL39PUB"));
                    list.add(newSearchVendorByPic);
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

    public static List<VendorCriteria> searchVendorByPoscode(String criteria) {
        ArrayList<VendorCriteria> list = new ArrayList<VendorCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL39CODE, GL39NAME, GL39BINDER, GL39SUPPLIER, GL39PUB FROM GLVEND WHERE";
        sql = String.valueOf(sql) + " GL39PCODE LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    VendorCriteria newSearchVendorByPoscode = new VendorCriteria(rs.getString("GL39CODE"), rs.getString("GL39NAME"), rs.getString("GL39BINDER"), rs.getString("GL39SUPPLIER"), rs.getString("GL39PUB"));
                    list.add(newSearchVendorByPoscode);
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
