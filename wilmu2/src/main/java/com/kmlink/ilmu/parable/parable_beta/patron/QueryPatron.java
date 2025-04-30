/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta.patron;

import com.kmlink.ilmu.parable.parable_beta.GeneralUtil_backup;
import com.kmlink.ilmu.parable.parable_beta.object.patron;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.parable.utilities.query.DBQuery;
import com.kmlink.ilmu.parable.utilities.query.QueryBuilder;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class QueryPatron {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadByPatron(String number1, String number2, int unprint, String selectedBranch) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = number2.trim() == null || number2.trim().isEmpty() ? statement.executeQuery(sql_statement2.loadByPatronID(number1, unprint, selectedBranch)) : statement.executeQuery(sql_statement2.loadByPatronID(number1, number2, unprint, selectedBranch));
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

    public patron loadByPatronSingle(String number, int unprint) throws SQLException {
        patron patron2 = new patron();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement(1004, 1007);
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.patronID(number, unprint));
                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                patron2 = GeneralUtil.extract_data_patron_single(resultSet);
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
        return patron2;
    }

    public patron loadByPatron(String number) throws SQLException {
        patron patron2 = new patron();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement(1004, 1007);
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.ptrID(number));
                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                patron2 = GeneralUtil.extract_data_patron_single(resultSet);
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
        return patron2;
    }

    public static boolean updatGL14BSTATUS(String GL14PATRID) throws SQLException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL14PATR", GL14PATRID);
        valueStr.put("GL14BPRINT", "Y");
        String query = QueryBuilder.createUpdateQuery("GLPATR", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
