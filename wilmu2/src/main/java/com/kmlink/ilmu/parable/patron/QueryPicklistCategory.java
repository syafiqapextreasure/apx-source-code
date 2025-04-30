/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.patron;

import com.kmlink.ilmu.parable.parable_beta.object.patron_category;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistCategory {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public List<patron_category> loadPicklistCategory() throws SQLException {
        ArrayList<patron_category> listPickList = new ArrayList<patron_category>();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                resultSet = statement.executeQuery(sql_statement2.picklistPatronCategory());
                while (resultSet.next()) {
                    patron_category patron_category2 = new patron_category();
                    patron_category2.setPatronCategory(resultSet.getString("Patron_Category").toString());
                    patron_category2.setDescription(resultSet.getString("Description").toString());
                    listPickList.add(patron_category2);
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
