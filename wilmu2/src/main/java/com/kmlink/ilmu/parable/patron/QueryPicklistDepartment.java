/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.patron;

import com.kmlink.ilmu.parable.parable_beta.object.department;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistDepartment {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public List<department> loadPicklistDepartment() throws SQLException {
        ArrayList<department> listPickList = new ArrayList<department>();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                resultSet = statement.executeQuery(sql_statement2.picklistPatronDepartment());
                while (resultSet.next()) {
                    department department2 = new department();
                    department2.setDepartment(resultSet.getString("Department").toString());
                    department2.setDescription(resultSet.getString("Description").toString());
                    listPickList.add(department2);
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
