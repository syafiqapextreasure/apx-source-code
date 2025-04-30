/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.Flag;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flag {
    private String Function;
    private String Description;
    private String Value;

    public String getFunction() {
        return Handler.ifIsNull(this.Function);
    }

    public String getDescription() {
        return Handler.ifIsNull(this.Description);
    }

    public String getValue() {
        return Handler.ifIsNull(this.Value);
    }

    public Flag(String Function, String Description, String Value) {
        this.Function = Function;
        this.Description = Description;
        this.Value = Value;
    }

    public static List<Flag> loadtable() {
        ArrayList<Flag> list = new ArrayList<Flag>();
        String query = "SELECT GL99FUNC, GL99DESC, GL99VALUE FROM GLFLAG2";
        System.out.println("query loadtable : " + query);
        Connection conn = null;
        String orderno = "";
        String status = "";
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Flag loadtabledetail = new Flag(Handler.ifIsNull(rs.getString("GL99FUNC")), Handler.ifIsNull(rs.getString("GL99DESC")), Handler.ifIsNull(rs.getString("GL99VALUE")));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static List<Flag> viewEditFlag(String func) {
        ArrayList<Flag> list = new ArrayList<Flag>();
        String query = "SELECT GL99FUNC, GL99DESC, GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = '" + func + "'";
        System.out.println("query editViewFlag : " + query);
        Connection conn = null;
        String orderno = "";
        String status = "";
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Flag loadtabledetail = new Flag(Handler.ifIsNull(rs.getString("GL99FUNC")), Handler.ifIsNull(rs.getString("GL99DESC")), Handler.ifIsNull(rs.getString("GL99VALUE")));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static List<Flag> searchatFlag(String input_criteria, String search_type) {
        ArrayList<Flag> list = new ArrayList<Flag>();
        String query = "SELECT GL99FUNC, GL99DESC, GL99VALUE FROM GLFLAG2 ";
        if (search_type.equals("Function")) {
            query = String.valueOf(query) + "WHERE UPPER(GL99FUNC) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("Description")) {
            query = String.valueOf(query) + "WHERE UPPER(GL99DESC) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("Value")) {
            query = String.valueOf(query) + "WHERE UPPER(GL99VALUE) = UPPER('" + input_criteria + "')";
        }
        System.out.println("query searchatFlag : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Flag loadsearchflag = new Flag(Handler.ifIsNull(rs.getString("GL99FUNC")), Handler.ifIsNull(rs.getString("GL99DESC")), Handler.ifIsNull(rs.getString("GL99VALUE")));
                    list.add(loadsearchflag);
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
        return list;
    }

    public static boolean CreateFlag(String Function2, String Description, String Value) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("GL99FUNC", Function2);
        valueStr.put("GL99DESC", Description);
        valueStr.put("GL99VALUE", Value);
        String query = QueryBuilder.createInsertQuery("GLFLAG2", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updating(String Function2, String Description, String Value) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        valueStr.put("GL99DESC", Description);
        valueStr.put("GL99VALUE", Value);
        condition.put("GL99FUNC", Function2);
        String query = QueryBuilder.createUpdateQuery("GLFLAG2", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static void deleteFlag(String code) {
        String query = "DELETE FROM GLFLAG2 WHERE GL99FUNC = '" + code + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Object stmt = null;
                Object rs = null;
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
    }
}
