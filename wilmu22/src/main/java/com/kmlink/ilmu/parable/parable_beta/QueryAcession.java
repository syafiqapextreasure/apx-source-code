/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.GeneralUtil_backup;
import com.kmlink.ilmu.parable.parable_beta.object.book_spine;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryAcession {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadbyAcession(String number1, String number2, int CT03BLESTATUS, String column) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = number2.trim() == null || number2.trim().isEmpty() ? statement.executeQuery(sql_statement2.accession(number1, CT03BLESTATUS, column)) : statement.executeQuery(sql_statement2.accession(number1, number2, CT03BLESTATUS, column));
                result = GeneralUtil.extract_data(resultSet);
                System.out.println("ds" + result);
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

    public HashMap<String, Object> loadbyAcession(String number1, String number2, int CT03BLESTATUS, String column, String selectedBranch, String selectedLocation) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            conn = DBConnection.getConnection();
            statement = conn.createStatement();
            sql_statement sql_statement2 = new sql_statement();
            GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
            resultSet = number2.trim() == null || number2.trim().isEmpty() ? statement.executeQuery(sql_statement2.accession(number1, CT03BLESTATUS, column, selectedBranch, selectedLocation)) : statement.executeQuery(sql_statement2.accession(number1, number2, CT03BLESTATUS, column, selectedBranch, selectedLocation));
            result = GeneralUtil.extract_data(resultSet);
            System.out.println("ds" + result);
        }
        finally {
            conn.close();
        }
        return result;
    }

    public HashMap<String, Object> loadbyAcessionRange(String number1, int CT03BLESTATUS, String column) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.accession(number1, CT03BLESTATUS, column));
                result = GeneralUtil.extract_data(resultSet);
                System.out.println("ds" + result);
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

    public HashMap<String, Object> loadbyAcessionRange(String number1, int CT03BLESTATUS, String column, String selectedBranch, String selectedLocation) throws SQLException {
        HashMap<String, Object> result = null;
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.accession(number1, CT03BLESTATUS, column, selectedBranch, selectedLocation));
                result = GeneralUtil.extract_data(resultSet);
                System.out.println("ds" + result);
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

    public book_spine loadbyAcession(String number1, int CT03BLESTATUS, String column) throws SQLException {
        book_spine book_spine2 = new book_spine();
        try {
            try {
                conn = DBConnection.getConnection();
                statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.accession(number1, CT03BLESTATUS, column));
                book_spine2 = GeneralUtil.extract_book_spine(resultSet);
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
        return book_spine2;
    }

    public book_spine loadAcession(String number1) throws SQLException {
        book_spine book_spine2 = new book_spine();
        try {
            try {
                conn = DBConnection.getConnection();
                Statement statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
                resultSet = statement.executeQuery(sql_statement2.accessions(number1));
                book_spine2 = GeneralUtil.extract_book_spine(resultSet);
            }
            catch (SQLException sQLException) {
                conn.close();
            }
        }
        finally {
            conn.close();
        }
        return book_spine2;
    }

    public static boolean updateCT03BLESTATUS(String CT03DOCNO) throws SQLException {
        conn = DBConnection.getConnection();
        String query = "Select CT03BLESTATUS from CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "'";
        boolean label = false;
        try {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    label = QueryAcession.updateStatus(rs.getString("CT03BLESTATUS"), CT03DOCNO);
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
        return label;
    }

    public static boolean updateStatus(String labelStatus, String docno) throws SQLException {
        String query = "";
        conn = DBConnection.getConnection();
        query = labelStatus == null ? "UPDATE CTDOCM SET CT03BLESTATUS = 1  WHERE CT03DOCNO = '" + docno + "'" : "UPDATE CTDOCM SET CT03BLESTATUS = CT03BLESTATUS + 1  WHERE CT03DOCNO = '" + docno + "'";
        System.out.println(query);
        try {
            try {
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
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
        return true;
    }

    public static boolean updateCT03SPINESTATUS(String CT03DOCNO) throws SQLException {
        conn = DBConnection.getConnection();
        String query = "Select CT03SPINESTATUS from CTDOCM WHERE CT03DOCNO = '" + CT03DOCNO + "'";
        boolean label = false;
        try {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    label = QueryAcession.updateStatus1(rs.getString("CT03SPINESTATUS"), CT03DOCNO);
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
        return label;
    }

    public static boolean updateStatus1(String labelStatus, String docno) throws SQLException {
        String query = "";
        query = labelStatus == null ? "UPDATE CTDOCM SET CT03SPINESTATUS = 1  WHERE CT03DOCNO = '" + docno + "'" : "UPDATE CTDOCM SET CT03SPINESTATUS = CT03SPINESTATUS + 1  WHERE CT03DOCNO = '" + docno + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
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
        return true;
    }

    public List<String> LLCSplit(String call_nox) {
        ArrayList<String> LLC = new ArrayList<String>();
        int i = 0;
        while (i < call_nox.length()) {
            String testx = String.valueOf(call_nox.charAt(i));
            LLC.add(testx);
            ++i;
        }
        return LLC;
    }
}
