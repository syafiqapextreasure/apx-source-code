/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta.patron;

import com.kmlink.ilmu.parable.parable_beta.object.course;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistCourse {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public List<course> loadPicklistCourse() throws SQLException {
        ArrayList<course> listPickList = new ArrayList<course>();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                resultSet = statement.executeQuery(sql_statement2.picklistPatronCourse());
                while (resultSet.next()) {
                    course course2 = new course();
                    course2.setDescription(resultSet.getString("Description").toString());
                    course2.setCourse(resultSet.getString("Course").toString());
                    listPickList.add(course2);
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
        return listPickList;
    }
}
