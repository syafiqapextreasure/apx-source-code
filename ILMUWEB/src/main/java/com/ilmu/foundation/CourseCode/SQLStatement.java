/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CourseCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllCourseCode() throws SQLException {
        ArrayList<Foundation> courselist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL12COURSE, GL12DESC, GL12TUTOR from GLCOUR";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL12COURSE(rs.getString("GL12COURSE"));
                    fnd.setGL12DESC(Handler.ifIsNull(rs.getString("GL12DESC")));
                    fnd.setGL12TUTOR(rs.getString("GL12TUTOR"));
                    courselist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return courselist;
    }

    public static SQLStatement AddCourse(String GL12COURSE, String GL12DESC) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL12COURSE", GL12COURSE);
        valueStr.put("GL12DESC", GL12DESC);
        String query = QueryBuilder.createInsertQuery("GLCOUR", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL12COURSE) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(RC01COURSE) AS Count FROM RCMASTER, GLCOUR WHERE RC01COURSE=GL12COURSE AND GL12COURSE='" + GL12COURSE + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) {
                        System.out.println(String.valueOf(rs.getString("Count")) + "ghgjg");
                        String query2 = "SELECT COUNT(GL14COURSE) AS Count FROM GLPATR, GLCOUR WHERE GL14COURSE=GL12COURSE AND GL12COURSE='" + GL12COURSE + "'";
                        System.out.println(query2);
                        stmt2 = con.createStatement();
                        rs2 = stmt2.executeQuery(query2);
                        while (rs2.next()) {
                            if (Integer.parseInt(rs2.getString("Count")) >= 1) {
                                System.out.println(String.valueOf(rs2.getString("Count")) + "gg5555");
                                deletable = false;
                                continue;
                            }
                            deletable = true;
                        }
                        continue;
                    }
                    deletable = false;
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
        return deletable;
    }

    public static void deleteCourse(String GL12COURSE) {
        Connection con = null;
        String query = "DELETE FROM GLCOUR WHERE GL12COURSE = '" + GL12COURSE + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement delete = con.prepareStatement(query);
                delete.execute();
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
    }
}
