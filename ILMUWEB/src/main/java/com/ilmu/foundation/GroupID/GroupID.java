/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.GroupID;

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

public class GroupID {
    private String groupid;
    private String groupname;
    private String accesslevel;
    private String recby;
    private String daterec;

    public String getgroupid() {
        return Handler.ifIsNull(this.groupid);
    }

    public String getgroupname() {
        return Handler.ifIsNull(this.groupname);
    }

    public String getaccesslevel() {
        return Handler.ifIsNull(this.accesslevel);
    }

    public String getrecby() {
        return Handler.ifIsNull(this.recby);
    }

    public String getdaterec() {
        return Handler.ifIsNull(this.daterec);
    }

    public GroupID(String groupid, String groupname, String accesslevel) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.accesslevel = accesslevel;
    }

    public GroupID(String groupid, String groupname, String accesslevel, String recby, String daterec) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.accesslevel = accesslevel;
        this.recby = recby;
        this.daterec = daterec;
    }

    public static List<GroupID> loadtable() {
        ArrayList<GroupID> list = new ArrayList<GroupID>();
        String query = "SELECT GL02GRP, GL02NAME, GL02ACL FROM GLGRMA";
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
                    GroupID loadtabledetail = new GroupID(Handler.ifIsNull(rs.getString("GL02GRP")), Handler.ifIsNull(rs.getString("GL02NAME")), Handler.ifIsNull(rs.getString("GL02ACL")));
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

    public static List<GroupID> editView(String id) {
        ArrayList<GroupID> list = new ArrayList<GroupID>();
        String query = "SELECT GL02GRP, GL02NAME, GL02ACL, GL02DATEREC, GL02RECBY FROM GLGRMA WHERE GL02GRP = '" + id + "'";
        System.out.println("query editView : " + query);
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
                    GroupID editViewdetail = new GroupID(Handler.ifIsNull(rs.getString("GL02GRP")), Handler.ifIsNull(rs.getString("GL02NAME")), Handler.ifIsNull(rs.getString("GL02ACL")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL02DATEREC"))), Handler.ifIsNull(rs.getString("GL02RECBY")));
                    list.add(editViewdetail);
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

    public static boolean updating(String groupid, String groupname, int accesslevel) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        valueStr.put("GL02NAME", groupname);
        valueInt.put("GL02ACL", accesslevel);
        condition.put("GL02GRP", groupid);
        String query = QueryBuilder.createUpdateQuery("GLGRMA", valueStr, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean CreateGroupID(String groupid, String groupname, int accesslevel) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL02GRP", groupid);
        valueStr.put("GL02NAME", groupname);
        valueInt.put("GL02ACL", accesslevel);
        String query = QueryBuilder.createInsertQuery("GLGRMA", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean CheckIfExist(String code) {
        String query = "SELECT COUNT(*) AS Count FROM GLPATR WHERE  GL14GRID = '" + code + "'";
        boolean deletable = false;
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    deletable = true;
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
        return deletable;
    }

    public static void deleteGroupID(String code) {
        String query = "DELETE FROM GLGRMA WHERE GL02GRP = '" + code + "'";
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

    public static List<GroupID> searchatGroupID(String input_criteria, String search_type) {
        ArrayList<GroupID> list = new ArrayList<GroupID>();
        String query = "SELECT GL02GRP, GL02NAME, GL02ACL FROM GLGRMA WHERE ";
        if (search_type.equals("GroupId")) {
            query = String.valueOf(query) + "UPPER(GL02GRP) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("GroupName")) {
            query = String.valueOf(query) + "UPPER(GL02NAME) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("AccessLevel")) {
            query = String.valueOf(query) + "UPPER(GL02ACL) LIKE UPPER('%" + input_criteria + "%')";
        }
        System.out.println("query searchatGroupID : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    GroupID loadsearchgroupid = new GroupID(Handler.ifIsNull(rs.getString("GL02GRP")), Handler.ifIsNull(rs.getString("GL02NAME")), Handler.ifIsNull(rs.getString("GL02ACL")));
                    list.add(loadsearchgroupid);
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
}
