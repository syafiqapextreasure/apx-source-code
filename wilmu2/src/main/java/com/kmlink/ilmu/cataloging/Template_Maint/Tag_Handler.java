/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Template_Maint;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tag_Handler {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL17MARC = null;
    private String GL17TAG = null;
    private String GL17TABNAME = null;
    private String GL17DESC = null;
    private String GL17MANDA = null;
    private String GL17REPEAT = null;
    private String GL17GRIDID = null;
    private String GL17AUTH = null;
    private String GL17STOP = null;
    private String GL17DEFAULT = null;
    private String CODE = null;
    private String DESC = null;

    public Tag_Handler(String GL17MARC, String GL17TAG, String GL17DESC, String GL17MANDA, String GL17REPEAT, String GL17AUTH, String GL17DEFAULT) {
        this.GL17MARC = GL17MARC;
        this.GL17TAG = GL17TAG;
        this.GL17DESC = GL17DESC;
        this.GL17MANDA = GL17MANDA;
        this.GL17REPEAT = GL17REPEAT;
        this.GL17AUTH = GL17AUTH;
        this.GL17DEFAULT = GL17DEFAULT;
    }

    public Tag_Handler(String GL17TAG, String GL17DESC, String GL17TABNAME, String GL17GRIDID) {
        this.GL17TAG = GL17TAG;
        this.GL17DESC = GL17DESC;
        this.GL17TABNAME = GL17TABNAME;
        this.GL17GRIDID = GL17GRIDID;
    }

    public Tag_Handler(String CODE, String DESC) {
        this.CODE = CODE;
        this.DESC = DESC;
    }

    public Tag_Handler(String GL17STOP) {
        this.GL17STOP = GL17STOP;
    }

    public String getGL17MARC() {
        return this.GL17MARC;
    }

    public String getGL17TAG() {
        return this.GL17TAG;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getGL17MANDA() {
        return this.GL17MANDA;
    }

    public String getGL17REPEAT() {
        return this.GL17REPEAT;
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getGL17STOP() {
        return this.GL17STOP;
    }

    public String getGL17GRID() {
        return this.GL17GRIDID;
    }

    public String getGL17AUTH() {
        return this.GL17AUTH;
    }

    public String getGL17DEFAULT() {
        return this.GL17DEFAULT;
    }

    public String getCODE() {
        return this.CODE;
    }

    public String getDESC() {
        return this.DESC;
    }

    public static List<Tag_Handler> getBibTags(String marc) {
        ArrayList<Tag_Handler> list = new ArrayList<Tag_Handler>();
        try {
            String query = "SELECT GL17TAG, GL17DESC, GL17TABNAME, GL17GRID FROM GLTAGP WHERE GL17MARC='" + marc + "' ORDER BY GL17TAG";
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag_Handler tagInfo = new Tag_Handler(rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17TABNAME"), rs.getString("GL17GRID"));
                list.add(tagInfo);
            }
            c.close();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public static Tag_Handler GL17STOP(String tag, String marc) {
        String query = "SELECT GL17STOP FROM GLTAGP WHERE GL17TAG='" + tag + "' " + "AND GL17MARC='" + marc + "'";
        Tag_Handler getGL17STOP = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getGL17STOP = new Tag_Handler(rs.getString("GL17STOP"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getGL17STOP;
    }

    public static boolean stopword(String GL99WORD) {
        GL99WORD = GL99WORD.replaceAll("[^a-zA-Z0-9% ]", " ");
        GL99WORD = GL99WORD.trim().replaceAll(" +", " ");
        String query = "Select COUNT(GL99WORD) AS Count FROM GLSTOPWORD WHERE GL99WORD='" + GL99WORD + "' ";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static List<Tag_Handler> getTags(String marc) {
        ArrayList<Tag_Handler> list = new ArrayList<Tag_Handler>();
        try {
            String query = "SELECT GL17MARC, GL17TAG, GL17DESC, GL17MANDA, GL17REPEAT, GL17AUTFLAG, GL17DEFAULT FROM GLTAGP WHERE GL17MARC='" + marc + "' ORDER BY GL17TAG";
            System.out.println(query);
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag_Handler tagInfo = new Tag_Handler(rs.getString("GL17MARC"), rs.getString("GL17TAG"), rs.getString("GL17DESC"), rs.getString("GL17MANDA"), rs.getString("GL17REPEAT"), rs.getString("GL17AUTFLAG"), rs.getString("GL17DEFAULT"));
                list.add(tagInfo);
            }
            c.close();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public static List<Tag_Handler> getBibType(String module) {
        String code = Handler.bibType(module);
        ArrayList<Tag_Handler> list = new ArrayList<Tag_Handler>();
        try {
            String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = '" + code + "' ";
            System.out.println("getBibType " + query);
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag_Handler tagInfo = new Tag_Handler(rs.getString("CODE"), rs.getString("DESCRIPTION"));
                list.add(tagInfo);
            }
            c.close();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return list;
    }

    public static String getMsBibType(String CODE, String fcode) {
        String desc = "";
        try {
            String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = '" + fcode + "' AND CODE='" + CODE + "'";
            System.out.println("TestCode" + query);
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                desc = rs.getString("DESCRIPTION");
            }
            c.close();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return desc;
    }

    public static String getDefaultTag(String tag) {
        String defaults = "";
        try {
            String query = "SELECT GL17MARC, GL17TAG, GL17DESC, GL17MANDA, GL17REPEAT, GL17AUTFLAG, GL17DEFAULT FROM GLTAGP WHERE GL17MARC='U' AND GL17TAG='" + tag + "'";
            System.out.println(query);
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                defaults = rs.getString("GL17DEFAULT");
            }
            c.close();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return defaults;
    }

    public static boolean tagExists(String CTTPLTAG, String CTTPLNAME) {
        String query = "Select CTTPLTAG FROM CTTPL, GLTAGP WHERE GL17TAG=CTTPLTAG and CTTPLTAG='" + CTTPLTAG + "' " + "and GL17REPEAT='N' and " + "GL17MARC='U' and CTTPLN='" + CTTPLNAME + "'";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }
}
