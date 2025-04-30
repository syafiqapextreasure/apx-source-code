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

public class QuerySMD {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadBySMD(String smd, String catalogDate1, String catalogDate2, String indexDate1, String indexDate2, int unprint, String column) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                if (!catalogDate1.isEmpty() && !catalogDate2.isEmpty()) {
                    System.out.println("convert date 1");
                    catalogDate1 = GeneralUtil.date_formattter(catalogDate1);
                    catalogDate2 = GeneralUtil.date_formattter(catalogDate2);
                }
                if (!indexDate1.isEmpty() && !indexDate2.isEmpty()) {
                    System.out.println("convert date 2");
                    indexDate1 = GeneralUtil.date_formattter(indexDate1);
                    indexDate2 = GeneralUtil.date_formattter(indexDate2);
                }
                resultSet = !catalogDate1.isEmpty() && !catalogDate2.isEmpty() || !indexDate1.isEmpty() && !indexDate2.isEmpty() ? statement.executeQuery(sql_statement2.smd(smd, catalogDate1, catalogDate2, indexDate1, indexDate2, unprint, column)) : statement.executeQuery(sql_statement2.smd(smd, unprint, column));
                int size = resultSet.getRow();
                result = GeneralUtil.extract_data(resultSet);
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

    public HashMap<String, Object> loadBySMD(String smd, String catalogDate1, String catalogDate2, String indexDate1, String indexDate2, int unprint, String column, String branch, String location) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                if (!catalogDate1.isEmpty() && !catalogDate2.isEmpty()) {
                    System.out.println("convert date 1");
                    catalogDate1 = GeneralUtil.date_formattter(catalogDate1);
                    catalogDate2 = GeneralUtil.date_formattter(catalogDate2);
                }
                if (!indexDate1.isEmpty() && !indexDate2.isEmpty()) {
                    System.out.println("convert date 2");
                    indexDate1 = GeneralUtil.date_formattter(indexDate1);
                    indexDate2 = GeneralUtil.date_formattter(indexDate2);
                }
                resultSet = !catalogDate1.isEmpty() && !catalogDate2.isEmpty() || !indexDate1.isEmpty() && !indexDate2.isEmpty() ? statement.executeQuery(sql_statement2.smd(smd, catalogDate1, catalogDate2, indexDate1, indexDate2, unprint, column, branch, location)) : statement.executeQuery(sql_statement2.smd(smd, unprint, column));
                int size = resultSet.getRow();
                result = GeneralUtil.extract_data(resultSet);
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
