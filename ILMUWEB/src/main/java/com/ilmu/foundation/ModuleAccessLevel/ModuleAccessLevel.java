/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.ModuleAccessLevel;

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

public class ModuleAccessLevel {
    private String GroupID;
    private String ModuleCode;
    private String ModuleName;
    private String AcessLevel;

    public String getGroupID() {
        return Handler.ifIsNull(this.GroupID);
    }

    public String getModuleCode() {
        return Handler.ifIsNull(this.ModuleCode);
    }

    public String getModuleName() {
        return Handler.ifIsNull(this.ModuleName);
    }

    public String getAcessLevel() {
        return Handler.ifIsNull(this.AcessLevel);
    }

    public ModuleAccessLevel(String GroupID, String ModuleCode, String AcessLevel) {
        this.GroupID = GroupID;
        this.ModuleCode = ModuleCode;
        this.AcessLevel = AcessLevel;
    }

    public static List<ModuleAccessLevel> searchModuleAccessLevel(String input_criteria, String search_type) {
        String query = "";
        ArrayList<ModuleAccessLevel> list = new ArrayList<ModuleAccessLevel>();
        query = "SELECT GL58GRP, GL58MODULE, GL58ACCESS FROM GLGRAC WHERE ";
        if (search_type.equals("GroupID")) {
            query = String.valueOf(query) + "UPPER(GL58GRP) LIKE UPPER('%" + input_criteria + "%') ";
        } else if (search_type.equals("ModuleName")) {
            query = String.valueOf(query) + "UPPER(GL58MODULE) LIKE UPPER('%" + input_criteria + "%') ";
        }
        System.out.println("query searchModuleAccessLevel : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ModuleAccessLevel loadtabledetail = new ModuleAccessLevel(Handler.ifIsNull(rs.getString("GL58GRP")), Handler.ifIsNull(rs.getString("GL58MODULE")), Handler.ifIsNull(rs.getString("GL58ACCESS")));
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

    public static List<ModuleAccessLevel> editView(String code, String module) {
        ArrayList<ModuleAccessLevel> list = new ArrayList<ModuleAccessLevel>();
        String query = "  SELECT GL58GRP, GL58MODULE, GL58ACCESS, GL58RECBY, GL58DATEREC FROM GLGRAC WHERE GL58GRP ='" + code + "' AND GL58MODULE = '" + module + "'";
        System.out.println("query editView : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ModuleAccessLevel loadtabledetail = new ModuleAccessLevel(Handler.ifIsNull(rs.getString("GL58GRP")), Handler.ifIsNull(rs.getString("GL58MODULE")), Handler.ifIsNull(rs.getString("GL58ACCESS")));
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

    public static boolean updating(String GroupID, String ModuleName, int AccessLevel) {
        HashMap valueStr = new HashMap();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        valueInt.put("GL58ACCESS", AccessLevel);
        condition.put("GL58GRP", GroupID);
        condition.put("GL58MODULE", ModuleName);
        String query = QueryBuilder.createUpdateQuery("GLGRAC", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static void deleteAccessLevel(String groupId, String module) {
        String query = "DELETE FROM GLGRAC WHERE GL58GRP ='" + groupId + "' AND GL58MODULE = '" + module + "'";
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

    public static boolean CreateFundAccountChart(String GroupID, String ModuleName, int AccessLevel) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL58GRP", GroupID);
        valueStr.put("GL58MODULE", ModuleName);
        valueInt.put("GL58ACCESS", AccessLevel);
        String query = QueryBuilder.createInsertQuery("GLGRAC", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
