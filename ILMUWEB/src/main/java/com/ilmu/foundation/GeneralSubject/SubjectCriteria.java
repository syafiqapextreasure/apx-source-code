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

public class SubjectCriteria {
    static Connection con;
    private String start = null;
    private String end = null;
    private String join = null;
    private String desc = null;

    public SubjectCriteria(String start, String end, String join, String desc) {
        this.start = start;
        this.end = end;
        this.join = join;
        this.desc = desc;
    }

    public String getstart() {
        return this.start;
    }

    public String getend() {
        return this.end;
    }

    public String getjoin() {
        return this.join;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<SubjectCriteria> searchSubjByStart(String criteria) {
        ArrayList<SubjectCriteria> list = new ArrayList<SubjectCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE";
        sql = String.valueOf(sql) + " GL54SUBJSTA LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SubjectCriteria newSearchSubjByStart = new SubjectCriteria(rs.getString("GL54SUBJSTA"), rs.getString("GL54SUBJEND"), rs.getString("GL54MARK"), rs.getString("GL54DESC"));
                    list.add(newSearchSubjByStart);
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

    public static List<SubjectCriteria> searchSubjByEnd(String criteria) {
        ArrayList<SubjectCriteria> list = new ArrayList<SubjectCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE";
        sql = String.valueOf(sql) + " GL54SUBJEND LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SubjectCriteria newSearchSubjByEnd = new SubjectCriteria(rs.getString("GL54SUBJSTA"), rs.getString("GL54SUBJEND"), rs.getString("GL54MARK"), rs.getString("GL54DESC"));
                    list.add(newSearchSubjByEnd);
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

    public static List<SubjectCriteria> searchSubjByDesc(String criteria) {
        ArrayList<SubjectCriteria> list = new ArrayList<SubjectCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC FROM GLSUBJ WHERE";
        sql = String.valueOf(sql) + " GL54DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    SubjectCriteria newSearchSubjByDesc = new SubjectCriteria(rs.getString("GL54SUBJSTA"), rs.getString("GL54SUBJEND"), rs.getString("GL54MARK"), rs.getString("GL54DESC"));
                    list.add(newSearchSubjByDesc);
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
