/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.VendorFeedback;

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

public class VendorFeedback {
    private String Code;
    private String Description;
    private String NotifyRequestor;
    private String OPACDisplay;
    private String recby;
    private String daterec;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDescription() {
        return Handler.ifIsNull(this.Description);
    }

    public String getNotifyRequestor() {
        return Handler.ifIsNull(this.NotifyRequestor);
    }

    public String getOPACDisplay() {
        return Handler.ifIsNull(this.OPACDisplay);
    }

    public String getrecby() {
        return Handler.ifIsNull(this.recby);
    }

    public String getdaterec() {
        return Handler.ifIsNull(this.daterec);
    }

    public VendorFeedback(String Code, String Description, String NotifyRequestor, String OPACDisplay) {
        this.Code = Code;
        this.Description = Description;
        this.NotifyRequestor = NotifyRequestor;
        this.OPACDisplay = OPACDisplay;
    }

    public VendorFeedback(String Code, String Description, String NotifyRequestor, String OPACDisplay, String recby, String daterec) {
        this.Code = Code;
        this.Description = Description;
        this.NotifyRequestor = NotifyRequestor;
        this.OPACDisplay = OPACDisplay;
        this.recby = recby;
        this.daterec = daterec;
    }

    public static List<VendorFeedback> loadtable() {
        ArrayList<VendorFeedback> list = new ArrayList<VendorFeedback>();
        String query = "SELECT GL40CODE, GL40DESC, GL40NFLAG, GL40DISPLAY FROM GLFEED";
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
                    VendorFeedback loadtabledetail = new VendorFeedback(Handler.ifIsNull(rs.getString("GL40CODE")), Handler.ifIsNull(rs.getString("GL40DESC")), Handler.ifIsNull(rs.getString("GL40NFLAG")), Handler.ifIsNull(rs.getString("GL40DISPLAY")));
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

    public static List<VendorFeedback> editView(String code) {
        ArrayList<VendorFeedback> list = new ArrayList<VendorFeedback>();
        String query = "SELECT GL40CODE, GL40DESC, GL40NFLAG, GL40DISPLAY, GL40RECBY, GL40DATEREC FROM GLFEED WHERE GL40CODE = '" + code + "'";
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
                    VendorFeedback editViewdetail = new VendorFeedback(Handler.ifIsNull(rs.getString("GL40CODE")), Handler.ifIsNull(rs.getString("GL40DESC")), Handler.ifIsNull(rs.getString("GL40NFLAG")), Handler.ifIsNull(rs.getString("GL40DISPLAY")), Handler.ifIsNull(rs.getString("GL40RECBY")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL40DATEREC"))));
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

    public static boolean CreateVendorFeedback(String codeVendor, String Description, String Notify, String Display) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        valueStr.put("GL40CODE", codeVendor);
        valueStr.put("GL40DESC", Description);
        valueStr.put("GL40NFLAG", Notify);
        valueStr.put("GL40DISPLAY", Display);
        String query = QueryBuilder.createInsertQuery("GLFEED", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updating(String codeVendor, String Description, String Notify, String Display) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        valueStr.put("GL40DESC", Description);
        valueStr.put("GL40NFLAG", Notify);
        valueStr.put("GL40DISPLAY", Display);
        condition.put("GL40CODE", codeVendor);
        String query = QueryBuilder.createUpdateQuery("GLFEED", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean CheckIfExistACFEED(String code) {
        String query = "SELECT COUNT(*) AS Count FROM ACFEED WHERE  AC04FCODE = '" + code + "'";
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

    public static boolean CheckIfExistSEFEED(String code) {
        String query = "SELECT COUNT(*) AS Count FROM SEFEED WHERE  SE12FCODE = '" + code + "'";
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

    public static void deleteVendorFeedback(String code) {
        String query = "DELETE GLFEED WHERE GL40CODE = '" + code + "'";
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

    public static List<VendorFeedback> searchatVendFeedback(String input_criteria, String search_type) {
        ArrayList<VendorFeedback> list = new ArrayList<VendorFeedback>();
        String query = "SELECT GL40CODE, GL40DESC, GL40NFLAG, GL40DISPLAY FROM GLFEED WHERE ";
        if (search_type.equals("Code")) {
            query = String.valueOf(query) + "UPPER(GL40CODE) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("Description")) {
            query = String.valueOf(query) + "UPPER(GL40DESC) LIKE UPPER('%" + input_criteria + "%')";
        }
        System.out.println("query searchatVendFeedback : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    VendorFeedback loadsearchVendorFeedback = new VendorFeedback(Handler.ifIsNull(rs.getString("GL40CODE")), Handler.ifIsNull(rs.getString("GL40DESC")), Handler.ifIsNull(rs.getString("GL40NFLAG")), Handler.ifIsNull(rs.getString("GL40DISPLAY")));
                    list.add(loadsearchVendorFeedback);
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
