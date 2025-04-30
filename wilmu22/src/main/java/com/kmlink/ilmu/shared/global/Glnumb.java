/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Glnumb {
    private static Connection conn = null;
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
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'BUFPOINT'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                ps = conn.prepareCall(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("GL98VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int GetBuffPoint(String columnName) {
        int result = 0;
        Object connection = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + columnName + "'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = Integer.parseInt(rs.getString("GL98VALUE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Glnumb getGL98VALUE(String columnName) {
        String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + columnName + "'";
        Glnumb result = null;
        System.out.println(query);
        try {
            try {
                System.out.println("reee1");
                conn = DBConnection.getConnection();
                System.out.println("reee");
                stmt = conn.createStatement();
                System.out.println("reee2");
                rs = stmt.executeQuery(query);
                System.out.println("reee3");
                while (rs.next()) {
                    System.out.println("reee");
                    result = new Glnumb(Integer.parseInt(rs.getString("GL98VALUE")));
                    System.out.println("ERER" + Integer.parseInt(rs.getString("GL98VALUE")));
                    query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + columnName + "'";
                    System.out.println("ccc" + query);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    System.out.println("ccc1");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public static void incHits(String GL98FUNC) {
        String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + GL98FUNC + "'";
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
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
    }

    public static void updateBufPoint(int pointer) {
        String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+'" + pointer + "' WHERE GL98FUNC = BUFPOINT";
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
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
                ps = conn.prepareCall(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getString("GL98VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    ps.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                ps.close();
                conn.close();
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
