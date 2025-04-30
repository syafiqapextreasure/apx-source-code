/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.object;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class branch {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String Branch = "";
    private String BranchDesc = "";
    private String BranchSubLoc = "";
    private String LocationDesc = "";

    public String getBranch() {
        return this.Branch;
    }

    public void setBranch(String branch2) {
        this.Branch = branch2;
    }

    public String getBranchDesc() {
        return this.BranchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.BranchDesc = branchDesc;
    }

    public String getBranchSubLoc() {
        return this.BranchSubLoc;
    }

    public void setBranchSubLoc(String branchSubLoc) {
        this.BranchSubLoc = branchSubLoc;
    }

    public String getLocationDesc() {
        return this.LocationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.LocationDesc = locationDesc;
    }

    public branch(String branch2) {
        this.Branch = branch2;
    }

    public static List<String> getBranches() {
        ArrayList<String> branches = new ArrayList<String>();
        String query = "SELECT DISTINCT(LB01BRNC) FROM LBMSTR";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branches.add(rs.getString("LB01BRNC"));
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
        return branches;
    }

    public static List<branch> getBrch() {
        ArrayList<branch> list = new ArrayList<branch>();
        String query = "SELECT DISTINCT(LB01BRNC) FROM LBMSTR";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    branch branches = new branch(rs.getString("LB01BRNC"));
                    list.add(branches);
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
        return list;
    }
}
