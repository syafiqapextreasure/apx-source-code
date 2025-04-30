/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Glnumb {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private int GL98VALUE = 0;
    private String GL98FUNC = null;

    public Glnumb(int GL98VALUE) {
        this.GL98VALUE = GL98VALUE;
    }

    public int getGL98VALUE() {
        return this.GL98VALUE;
    }

    public String getGL98FUNC() {
        return this.GL98FUNC;
    }

    public static String GetCurrBuffPoint() {
        String result = "";
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'BUFPOINT'";
        System.out.println(sql);
        try {
            try {
                connection = DBConnection.getConnection();
                ps = connection.prepareCall(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("GL98VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int GetBuffPoint(String columnName) {
        int result = 0;
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + columnName + "'";
        System.out.println(sql);
        try {
            try {
                connection = DBConnection.getConnection();
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = Integer.parseInt(rs.getString("GL98VALUE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Glnumb getGL98VALUE(String columnName) {
        Glnumb.updateNumb(columnName);
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + columnName + "'";
        Glnumb result = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = new Glnumb(Integer.parseInt(rs.getString("GL98VALUE")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void updateNumb(String GL98FUNC) {
        String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + GL98FUNC + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void incHits(String GL98FUNC) {
        String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + GL98FUNC + "'";
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            PreparedStatement delete = c.prepareStatement(query);
            delete.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBufPoint(int pointer) {
        String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+'" + pointer + "' WHERE GL98FUNC = BUFPOINT";
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            PreparedStatement delete = c.prepareStatement(query);
            delete.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String GetCurrentAccessionNo() {
        String result = "";
        Connection connection = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'ACCNO'";
        System.out.println(sql);
        try {
            try {
                connection = DBConnection.getConnection();
                ps = connection.prepareCall(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("GL98VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean updatinglnumb(int value, String func) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", func);
        valueInt.put("GL98VALUE", value);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
