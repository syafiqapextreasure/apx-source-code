/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.GeneralSubject;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjSearch {
    static Connection con;
    private String subjectStart = null;
    private String subjectEnd = null;
    private String join = null;
    private String desc = null;

    public SubjSearch(String subjectStart, String subjectEnd, String join, String desc) {
        this.subjectStart = subjectStart;
        this.subjectEnd = subjectEnd;
        this.join = join;
        this.desc = desc;
    }

    public String getsubjectStart() {
        return this.subjectStart;
    }

    public String getsubjectEnd() {
        return this.subjectEnd;
    }

    public String getjoin() {
        return this.join;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<SubjSearch> searchSubjByStart(String criteria, String cate_id) {
        ArrayList<SubjSearch> list = new ArrayList<SubjSearch>();
        Statement stmt = null;
        String query = "SELECT SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE GL54SUBJSTA LIKE '%" + criteria + "%' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SubjSearch newSearchPatronById = new SubjSearch(rs.getString("GL54SUBJSTA"), rs.getString("GL54SUBJEND"), rs.getString("GL54MARK"), rs.getString("GL54DESC"));
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

    public static List<SubjSearch> searchPatronByName(String criteria, String cate_id) {
        ArrayList<SubjSearch> list = new ArrayList<SubjSearch>();
        Statement stmt = null;
        String query1 = "SELECT SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE GL54SUBJEND LIKE '%" + criteria + "%'";
        System.out.println(query1);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    SubjSearch newSearchPatronByName = new SubjSearch(rs.getString("GL54SUBJSTA"), rs.getString("GL54SUBJEND"), rs.getString("GL54MARK"), rs.getString("GL54DESC"));
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

    public static String getPatronNameById(String GL54SUBJSTA) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE GL54SUBJEND LIKE '%" + GL54SUBJSTA + "%'";
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
