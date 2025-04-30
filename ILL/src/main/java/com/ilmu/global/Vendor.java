/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
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
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39CODE) = UPPER('" + code + "')";
        Connection conn = null;
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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
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
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    statement.close();
                    connection.close();
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
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendorList;
    }

    public static List<Vendor> searchListByVendorCode(String vendorCode) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39CODE) LIKE UPPER('" + vendorCode + "%')";
        return Vendor.searchList2(sql);
    }

    public static List<Vendor> searchListByVendorName(String vendorName) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39NAME) LIKE UPPER('%" + vendorName + "%')";
        return Vendor.searchList2(sql);
    }
}
