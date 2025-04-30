/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private static Connection conn = null;
    private String vendorName;
    private String vendCode;

    public String getVendorName() {
        return this.vendorName;
    }

    public String getVendCode() {
        return this.vendCode;
    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public Vendor(String vendCode, String vendorName) {
        this.vendCode = vendCode;
        this.vendorName = vendorName;
    }

    public static List<Vendor> getVendorName(String code) {
        ArrayList<Vendor> vendorname = new ArrayList<Vendor>();
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND UPPER(GL39CODE) = UPPER('" + code + "')";
        System.out.println("getVendorName = " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Vendor newvendorname = new Vendor(rs.getString("GL39NAME"));
                    vendorname.add(newvendorname);
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
        return vendorname;
    }

    private static List<Vendor> searchList2(String sql) {
        ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
        Statement statement = null;
        ResultSet rs = null;
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    vendorList.add(new Vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    statement.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendorList;
    }

    public static List<Vendor> searchListByVendorCode(String vendorCode) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND UPPER(GL39CODE) LIKE UPPER('" + vendorCode + "%')";
        System.out.println(String.valueOf(vendorCode) + " vendorCode");
        return Vendor.searchList2(sql);
    }

    public static List<Vendor> searchListByVendorName(String vendorName) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39BINDER = 'Y' AND UPPER(GL39NAME) LIKE UPPER('%" + vendorName + "%')";
        System.out.println(String.valueOf(vendorName) + " vendorName");
        return Vendor.searchList2(sql);
    }
}
