/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.membershipRenewal;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatronDetail {
    private static Connection conn = null;
    private String patronId;
    private String patronName;

    public String getPatronId() {
        return this.patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getPatronName() {
        return this.patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public PatronDetail(String patronId, String patronName) {
        this.patronId = patronId;
        this.patronName = patronName;
    }

    public static List<PatronDetail> searchByPatronId(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String sqlSortbyId = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nORDER BY GL14PATR\r\n";
        String sqlSortbyIdDesc = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nAND T2.GL07DESC=?\r\nORDER BY GL14PATR\r\n";
        String sqlSortbyName = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nORDER BY GL14NAME\r\n";
        String sqlSortbyNameDesc = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nAND T2.GL07DESC=?\r\nORDER BY GL14NAME\r\n";
        ArrayList<PatronDetail> patronDetail = new ArrayList<PatronDetail>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    if (filteredBy.equals("")) {
                        stmt = conn.prepareStatement(sqlSortbyId);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                    } else {
                        stmt = conn.prepareStatement(sqlSortbyIdDesc);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                        stmt.setString(2, filteredBy);
                    }
                } else if (sortedBy.equals("GL14NAME")) {
                    if (filteredBy.equals("")) {
                        stmt = conn.prepareStatement(sqlSortbyName);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                    } else {
                        stmt = conn.prepareStatement(sqlSortbyIdDesc);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                        stmt.setString(2, filteredBy);
                    }
                }
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    patronDetail.add(new PatronDetail(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return patronDetail;
    }

    public static List<PatronDetail> searchByName(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String query = "SELECT GL14PATR, GL14NAME, T2.GL07DESC\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14NAME LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME, T2.GL07DESC\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14NAME LIKE ?\r\nORDER BY GL14NAME\r\n";
        ArrayList<PatronDetail> patronDetail = new ArrayList<PatronDetail>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    patronDetail.add(new PatronDetail(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return patronDetail;
    }

    public static List<PatronDetail> searchByNewIc(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String query = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14NEWIC LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14NEWIC LIKE ?\r\nORDER BY GL14PATR";
        ArrayList<PatronDetail> patronDetail = new ArrayList<PatronDetail>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    patronDetail.add(new PatronDetail(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return patronDetail;
    }

    public static List<PatronDetail> searchByOldIc(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String query = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14OLDIC LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14OLDIC LIKE ?\r\nORDER BY GL14NAME";
        ArrayList<PatronDetail> patronDetail = new ArrayList<PatronDetail>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    patronDetail.add(new PatronDetail(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return patronDetail;
    }
}
