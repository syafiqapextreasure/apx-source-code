/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.GeneralUtil_backup;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.json.JSONObject;

public class QueryIndexDate {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadByIndexDate(String date1, String date2, int unprint, String column) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                String dateStart = GeneralUtil.date_formattter(date1);
                String dateEnd = GeneralUtil.date_formattter(date2);
                resultSet = statement.executeQuery(sql_statement2.index_date(dateStart, dateEnd, unprint, column));
                int size = resultSet.getRow();
                result = GeneralUtil.extract_data(resultSet);
                System.out.println(new JSONObject(result).toString());
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

    public HashMap<String, Object> loadByIndexDate(String date1, String date2, int unprint, String column, String branch, String location) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                String dateStart = GeneralUtil.date_formattter(date1);
                String dateEnd = GeneralUtil.date_formattter(date2);
                resultSet = statement.executeQuery(sql_statement2.index_date(dateStart, dateEnd, unprint, column, branch, location));
                int size = resultSet.getRow();
                result = GeneralUtil.extract_data(resultSet);
                System.out.println(new JSONObject(result).toString());
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
