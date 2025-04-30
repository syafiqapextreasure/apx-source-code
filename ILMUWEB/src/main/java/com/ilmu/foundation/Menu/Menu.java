/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.Menu;

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

public class Menu {
    private String Module;
    private String Root;
    private String Index;
    private String Description;
    private String ProgramID;
    private String AccessLevel;
    private String recby;
    private String daterec;

    public String getModule() {
        return Handler.ifIsNull(this.Module);
    }

    public String getRoot() {
        return Handler.ifIsNull(this.Root);
    }

    public String getIndex() {
        return Handler.ifIsNull(this.Index);
    }

    public String getDescription() {
        return Handler.ifIsNull(this.Description);
    }

    public String getProgramID() {
        return Handler.ifIsNull(this.ProgramID);
    }

    public String getAccessLevel() {
        return Handler.ifIsNull(this.AccessLevel);
    }

    public String getrecby() {
        return Handler.ifIsNull(this.recby);
    }

    public String getdaterec() {
        return Handler.ifIsNull(this.daterec);
    }

    public Menu(String Index) {
        this.Index = Index;
    }

    public Menu(String Module, String Root, String Index, String Description, String ProgramID, String AccessLevel) {
        this.Module = Module;
        this.Root = Root;
        this.Index = Index;
        this.Description = Description;
        this.ProgramID = ProgramID;
        this.AccessLevel = AccessLevel;
    }

    public Menu(String Module, String Root, String Index, String Description, String ProgramID, String AccessLevel, String recby, String daterec) {
        this.Module = Module;
        this.Root = Root;
        this.Index = Index;
        this.Description = Description;
        this.ProgramID = ProgramID;
        this.AccessLevel = AccessLevel;
        this.recby = recby;
        this.daterec = daterec;
    }

    public static List<Menu> loadtable() {
        ArrayList<Menu> list = new ArrayList<Menu>();
        String query = "  SELECT GL22MODULE, GL22NAME, GL22IDX, GL22DESC, GL22PROG, GL22ACL FROM GLMENU";
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
                    Menu loadtabledetail = new Menu(Handler.ifIsNull(rs.getString("GL22MODULE")), Handler.ifIsNull(rs.getString("GL22NAME")), Handler.ifIsNull(rs.getString("GL22IDX")), Handler.ifIsNull(rs.getString("GL22DESC")), Handler.ifIsNull(rs.getString("GL22PROG")), Handler.ifIsNull(rs.getString("GL22ACL")));
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

    public static List<Menu> editView(String MODULE, String NAME, String IDX) {
        ArrayList<Menu> list = new ArrayList<Menu>();
        String query = "SELECT GL22MODULE, GL22NAME, GL22IDX, GL22DESC, GL22PROG, GL22ACL, GL22DATEREC, GL22RECBY FROM GLMENU WHERE GL22MODULE = '" + MODULE + "' AND GL22NAME = '" + NAME + "' AND GL22IDX = '" + IDX + "'";
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
                    Menu editViewdetail = new Menu(Handler.ifIsNull(rs.getString("GL22MODULE")), Handler.ifIsNull(rs.getString("GL22NAME")), Handler.ifIsNull(rs.getString("GL22IDX")), Handler.ifIsNull(rs.getString("GL22DESC")), Handler.ifIsNull(rs.getString("GL22PROG")), Handler.ifIsNull(rs.getString("GL22ACL")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL22DATEREC"))), Handler.ifIsNull(rs.getString("GL22RECBY")));
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

    public static List<Menu> getindex(String module, String name) {
        ArrayList<Menu> list = new ArrayList<Menu>();
        String query = "SELECT MAX(GL22IDX) AS getIndex FROM GLMENU WHERE UPPER(GL22MODULE) = UPPER('" + module + "') AND GL22NAME = '" + name + "' ";
        System.out.println("query getindex : " + query);
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
                    Menu loadtabledetail = new Menu(Handler.ifIsNull(rs.getString("getIndex")));
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

    public static List<Menu> CheckIfMenuAlreadyExist(String module, String name, String index) {
        ArrayList<Menu> list = new ArrayList<Menu>();
        String query = "SELECT COUNT(*) AS Count FROM GLMENU WHERE UPPER(GL22MODULE) = UPPER('" + module + "') AND GL22NAME = '" + name + "' " + "AND GL22IDX = '" + index + "' ";
        System.out.println("query CheckIfMenuAlreadyExist : " + query);
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
                    Menu loadtabledetail = new Menu(Handler.ifIsNull(rs.getString("Count")));
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

    public static List<Menu> searchatMenu(String input_criteria, String search_type) {
        ArrayList<Menu> list = new ArrayList<Menu>();
        String query = "SELECT GL22MODULE, GL22NAME, GL22IDX, GL22DESC, GL22PROG, GL22ACL FROM GLMENU WHERE  ";
        if (search_type.equals("ModuleName")) {
            query = String.valueOf(query) + "UPPER(GL22MODULE) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("RootName")) {
            query = String.valueOf(query) + "UPPER(GL22NAME) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("MenuIndex")) {
            query = String.valueOf(query) + "UPPER(GL22IDX) LIKE UPPER('%" + input_criteria + "%')";
        } else if (search_type.equals("ProgramID")) {
            query = String.valueOf(query) + "UPPER(GL22PROG) LIKE UPPER('%" + input_criteria + "%')";
        }
        System.out.println("query searchatMenu : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Menu loadsearchmenu = new Menu(Handler.ifIsNull(rs.getString("GL22MODULE")), Handler.ifIsNull(rs.getString("GL22NAME")), Handler.ifIsNull(rs.getString("GL22IDX")), Handler.ifIsNull(rs.getString("GL22DESC")), Handler.ifIsNull(rs.getString("GL22PROG")), Handler.ifIsNull(rs.getString("GL22ACL")));
                    list.add(loadsearchmenu);
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

    public static boolean CreateMenu(String ModuleName, String RootName, String MenuIndex, String MenuDescription, String ProgramID, String AccessLevel) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("GL22MODULE", ModuleName);
        valueStr.put("GL22NAME", RootName);
        valueStr.put("GL22IDX", MenuIndex);
        valueStr.put("GL22DESC", MenuDescription);
        valueStr.put("GL22PROG", ProgramID);
        valueStr.put("GL22ACL", AccessLevel);
        String query = QueryBuilder.createInsertQuery("GLMENU", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updating(String ModuleName, String RootName, String MenuIndex, String MenuDescription, String ProgramID, String AccessLevel, String hidemodule, String hiderootname, String hideindex) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        valueStr.put("GL22NAME", RootName);
        valueStr.put("GL22IDX", MenuIndex);
        valueStr.put("GL22DESC", MenuDescription);
        valueStr.put("GL22PROG", ProgramID);
        valueStr.put("GL22ACL", AccessLevel);
        condition.put("GL22MODULE", hidemodule);
        condition.put("GL22NAME", hiderootname);
        condition.put("GL22IDX", hideindex);
        String query = QueryBuilder.createUpdateQuery("GLMENU", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static void deleteMenu(String MODULE, String NAME, String IDX) {
        String query = "DELETE FROM GLMENU WHERE GL22MODULE = '" + MODULE + "' AND GL22NAME = '" + NAME + "' AND GL22IDX = '" + IDX + "'";
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
