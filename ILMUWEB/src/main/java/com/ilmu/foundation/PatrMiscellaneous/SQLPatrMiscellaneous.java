/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatrMiscellaneous;

import com.ilmu.global.DateFormatter;
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

public class SQLPatrMiscellaneous {
    private String code;
    private String description;
    private String savedate;
    private String createuser;
    private String value;

    public String getcode() {
        return Handler.ifIsNull(this.code);
    }

    public String getdescription() {
        return Handler.ifIsNull(this.description);
    }

    public String getsavedate() {
        return Handler.ifIsNull(this.savedate);
    }

    public String getcreateuser() {
        return Handler.ifIsNull(this.createuser);
    }

    public String getvalue() {
        return Handler.ifIsNull(this.value);
    }

    public SQLPatrMiscellaneous(String code, String description, String createuser, String savedate) {
        this.code = code;
        this.description = description;
        this.createuser = createuser;
        this.savedate = savedate;
    }

    public SQLPatrMiscellaneous(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public SQLPatrMiscellaneous(String code, String description, String value) {
        this.code = code;
        this.description = description;
        this.value = value;
    }

    public static List<SQLPatrMiscellaneous> getAll() {
        ArrayList<SQLPatrMiscellaneous> list = new ArrayList<SQLPatrMiscellaneous>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = " SELECT GL66ATTRI, GL66DESC, GL66RECBY, GL66DATEREC FROM GLATTRI";
        System.out.println("SQL getAll :" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    list.add(new SQLPatrMiscellaneous(rs.getString("GL66ATTRI"), Handler.ifIsNull(rs.getString("GL66DESC")), Handler.ifIsNull(rs.getString("GL66RECBY")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL66DATEREC")))));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLPatrMiscellaneous> getAllBYCode(String code) {
        ArrayList<SQLPatrMiscellaneous> list = new ArrayList<SQLPatrMiscellaneous>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = " SELECT GL66ATTRI, GL66DESC, GL66RECBY, GL66DATEREC FROM GLATTRI WHERE GL66ATTRI = '" + code + "'";
        System.out.println("SQL get by code :" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    list.add(new SQLPatrMiscellaneous(rs.getString("GL66ATTRI"), Handler.ifIsNull(rs.getString("GL66DESC")), Handler.ifIsNull(rs.getString("GL66RECBY")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL66DATEREC")))));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean CreateMisscellaneous(String patrAttribute, String description, String daterec, String recby) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL66ATTRI", patrAttribute);
        valueStr.put("GL66DESC", description);
        valueStr.put("GL66DATEREC", daterec);
        valueStr.put("GL66RECBY", recby);
        String query = QueryBuilder.createInsertQuery("GLATTRI", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updatingMisscellaneous(String patrAttribute, String description) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL66ATTRI", patrAttribute);
        valueStr.put("GL66DESC", description);
        String query = QueryBuilder.createUpdateQuery("GLATTRI", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static void deleteMisscellaneous(String patrAttribute) {
        Connection con = null;
        String query = "DELETE FROM GLATTRI WHERE GL66ATTRI = '" + patrAttribute + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement delete = con.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<SQLPatrMiscellaneous> searchMisscellaneous(String search_type, String input_criteria) {
        ArrayList<SQLPatrMiscellaneous> list = new ArrayList<SQLPatrMiscellaneous>();
        System.out.println(String.valueOf(input_criteria) + " = what the input_criteria?");
        String newinput_criteria = input_criteria.replace("'", "''");
        String query = "SELECT GL66ATTRI, GL66DESC FROM GLATTRI WHERE ";
        if (search_type.equals("attribute")) {
            query = String.valueOf(query) + "UPPER(GL66ATTRI) like UPPER('%" + input_criteria + "%') ";
        } else if (search_type.equals("description")) {
            query = String.valueOf(query) + "UPPER(GL66DESC) like UPPER('%" + newinput_criteria + "%') ";
        }
        query = String.valueOf(query) + "ORDER BY GL66ATTRI";
        Connection conn = null;
        System.out.println("Query searchMisscellaneous " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLPatrMiscellaneous newGetAllBy = new SQLPatrMiscellaneous(rs.getString("GL66ATTRI"), Handler.ifIsNull(rs.getString("GL66DESC")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                    Thread.sleep(1000L);
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                try {
                    conn.close();
                    Thread.sleep(1000L);
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
                Thread.sleep(1000L);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<SQLPatrMiscellaneous> viewAtpatronDetail(String patronId) {
        ArrayList<SQLPatrMiscellaneous> list = new ArrayList<SQLPatrMiscellaneous>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String sql = "SELECT GL66ATTRI, GL66DESC, (SELECT GL65VALUE FROM GLPATREX WHERE GL65ATTRI = GL66ATTRI AND GL65PATR = '" + patronId + "') AS VALUE " + "FROM GLATTRI";
        System.out.println("SQL viewAtpatronDetail :" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    list.add(new SQLPatrMiscellaneous(rs.getString("GL66ATTRI"), Handler.ifIsNull(rs.getString("GL66DESC")), Handler.ifIsNull(rs.getString("VALUE"))));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    statement.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    connection.close();
                }
                catch (Exception exception) {}
            }
        }
        finally {
            try {
                rs.close();
            }
            catch (Exception exception) {}
            try {
                statement.close();
            }
            catch (Exception exception) {}
            try {
                connection.close();
            }
            catch (Exception exception) {}
        }
        return list;
    }
}
