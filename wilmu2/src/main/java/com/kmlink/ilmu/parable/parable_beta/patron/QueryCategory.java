/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta.patron;

import com.kmlink.ilmu.parable.parable_beta.GeneralUtil_backup;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class QueryCategory {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadByPatronCategory(String category, int unprint) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.patronCategory(category, unprint));
                int size = resultSet.getRow();
                result = GeneralUtil.extract_data_patron(resultSet);
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
        return result;
    }
}
