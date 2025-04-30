/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronDetails;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatronSearch {
    static Connection con;
    private String Id = null;
    private String Name = null;

    public PatronSearch(String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public PatronSearch(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public static List<PatronSearch> searchPatronById(String criteria, String cate_id) {
        ArrayList<PatronSearch> list = new ArrayList<PatronSearch>();
        Statement stmt = null;
        String query = "SELECT GL14PATR, GL14NAME FROM GLPATR WHERE GL14PATR LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronSearch newSearchPatronById = new PatronSearch(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronById);
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

    public static List<PatronSearch> searchPatronByName(String criteria, String cate_id) {
        ArrayList<PatronSearch> list = new ArrayList<PatronSearch>();
        Statement stmt = null;
        String query = "SELECT GLPATR.GL14PATR, GLPATR.GL14NAME FROM GLPATR INNER JOIN GLCATE ON GLPATR.GL14CATE=GLCATE.GL07CATE WHERE GLPATR.GL14NAME LIKE '%" + criteria + "%' AND GLCATE.GL07CATE='" + cate_id + "' ";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronSearch newSearchPatronByName = new PatronSearch(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByName);
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

    public static String getPatronNameById(String GL14PATR) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT GL14NAME FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL14NAME");
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

    public static List<PatronSearch> checkValidPatr(String patrid) {
        ArrayList<PatronSearch> patronID = new ArrayList<PatronSearch>();
        String query = "SELECT GL14PATR FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + patrid + "') ";
        System.out.println("checkpatrid = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronSearch newpatronID = new PatronSearch(rs.getString("GL14PATR"));
                    patronID.add(newpatronID);
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
        return patronID;
    }
}
