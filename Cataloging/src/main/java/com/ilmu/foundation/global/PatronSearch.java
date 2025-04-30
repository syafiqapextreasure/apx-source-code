/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.global;

import com.ilmu.global.DateTime;
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
    private String CT05SRAW = null;
    private String CT05POINTER = null;
    private String CT02MATNO = null;

    public PatronSearch(String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public PatronSearch(String CT02MATNO, String CT05SRAW, String CT05POINTER) {
        this.CT02MATNO = CT02MATNO;
        this.CT05SRAW = CT05SRAW;
        this.CT05POINTER = CT05POINTER;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public String getCT02MATNO() {
        return this.CT02MATNO;
    }

    public String getCT05POINTER() {
        return this.CT05POINTER;
    }

    public String getCT05SRAW() {
        return PatronSearch.rawToDisplayFormat(this.CT05SRAW);
    }

    public static String rawToDisplayFormat(String raw) {
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            String[] rawArray = raw.split("\\|");
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
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

    public static List<PatronSearch> CT_getListByDate(String startDate, String stopDate, String patron) {
        return PatronSearch.CT_getListBy("CT02CREBY='" + patron + "' AND CT02CRDATE BETWEEN " + DateTime.displayToDBFormat(startDate) + " AND " + DateTime.displayToDBFormat(stopDate));
    }

    public static List<PatronSearch> CT_getListBy(String condition) {
        ArrayList<PatronSearch> list = new ArrayList<PatronSearch>();
        Statement stmt = null;
        String query = "Select DISTINCT CT02MATNO, CT05SRAW, CT06POINTER from CTMATM, CTPONT, CTTITL WHERE CT06MATNO=CT02MATNO AND CT06POINTER=CT05POINTER AND " + condition;
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronSearch newSearchPatronByName = new PatronSearch(rs.getString("CT02MATNO"), rs.getString("CT05SRAW"), rs.getString("CT06POINTER"));
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
}
