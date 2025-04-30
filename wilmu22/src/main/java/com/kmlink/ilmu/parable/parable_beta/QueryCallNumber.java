/*
 * Decompiled with CFR 0.152.
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

public class QueryCallNumber {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadByCallNumber(String number1, String number2, int unprint, String column) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = number2.trim() == null || number2.trim().isEmpty() ? statement.executeQuery(sql_statement2.call_number(number1, unprint, column)) : statement.executeQuery(sql_statement2.call_number(number1, number2, unprint, column));
                System.out.println("testInner");
                result = GeneralUtil.extract_data(resultSet);
                System.out.println("testInner");
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

    public HashMap<String, Object> loadByCallNumber(String number1, String number2, int unprint, String column, String branch, String location) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = number2.trim() == null || number2.trim().isEmpty() ? statement.executeQuery(sql_statement2.call_number(number1, unprint, column)) : statement.executeQuery(sql_statement2.call_number(number1, number2, unprint, column, branch, location));
                System.out.println("testInner");
                result = GeneralUtil.extract_data(resultSet);
                System.out.println("testInner");
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
