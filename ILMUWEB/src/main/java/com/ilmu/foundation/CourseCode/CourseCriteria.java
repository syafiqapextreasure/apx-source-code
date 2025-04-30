/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CourseCode;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseCriteria {
    static Connection con;
    private String course = null;
    private String desc = null;
    private String tutor = null;

    public CourseCriteria(String course, String desc, String tutor) {
        this.course = course;
        this.desc = desc;
        this.tutor = tutor;
    }

    public String getcourse() {
        return this.course;
    }

    public String getdesc() {
        return this.desc;
    }

    public String gettutor() {
        return this.tutor;
    }

    public static List<CourseCriteria> searchCourseByCode(String criteria) {
        ArrayList<CourseCriteria> list = new ArrayList<CourseCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL12COURSE, GL12DESC, GL12TUTOR FROM GLCOUR WHERE";
        sql = String.valueOf(sql) + " GL12COURSE LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CourseCriteria newSearchCourseByCode = new CourseCriteria(rs.getString("GL12COURSE"), Handler.ifIsNull(rs.getString("GL12DESC")), Handler.ifIsNull(rs.getString("GL12TUTOR")));
                    list.add(newSearchCourseByCode);
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

    public static List<CourseCriteria> searchCourseByDesc(String criteria) {
        ArrayList<CourseCriteria> list = new ArrayList<CourseCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL12COURSE, GL12DESC, GL12TUTOR FROM GLCOUR WHERE";
        sql = String.valueOf(sql) + " GL12DESC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CourseCriteria newSearchCourseByName = new CourseCriteria(rs.getString("GL12COURSE"), rs.getString("GL12DESC"), Handler.ifIsNull(rs.getString("GL12TUTOR")));
                    list.add(newSearchCourseByName);
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
