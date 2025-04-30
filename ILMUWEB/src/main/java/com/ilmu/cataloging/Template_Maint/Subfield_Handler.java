/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Template_Maint;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Subfield_Handler {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL23SUBF = null;
    private String GL23DESC = null;
    private String GL23REPEAT = null;

    public Subfield_Handler(String GL23SUBF, String GL23DESC, String GL23REPEAT) {
        this.GL23SUBF = GL23SUBF;
        this.GL23DESC = GL23DESC;
        this.GL23REPEAT = GL23REPEAT;
    }

    public Subfield_Handler(String GL23DESC, String GL23REPEAT) {
        this.GL23DESC = GL23DESC;
        this.GL23REPEAT = GL23REPEAT;
    }

    public String getGL23REPEAT() {
        return this.GL23REPEAT;
    }

    public String getGL23DESC() {
        return this.GL23DESC;
    }

    public String getGL23SUBF() {
        return this.GL23SUBF;
    }

    public static List<Subfield_Handler> getListOfSubf(String GL23TAG) {
        ArrayList<Subfield_Handler> list = new ArrayList<Subfield_Handler>();
        String query = "SELECT GL23SUBF, GL23DESC, GL23REPEAT FROM GLMSUBF WHERE GL23TAG='" + GL23TAG + "' and GL23SUBF BETWEEN  'a' and 'z' and GL23MARC='U' " + "UNION " + "SELECT GL23SUBF, GL23DESC, GL23REPEAT " + "FROM GLMSUBF " + "WHERE GL23TAG='" + GL23TAG + "' and GL23SUBF BETWEEN  '0' and '9' and GL23MARC='U'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Subfield_Handler newSubfList = new Subfield_Handler(rs.getString("GL23SUBF"), rs.getString("GL23DESC"), rs.getString("GL23REPEAT"));
                    list.add(newSubfList);
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
        return list;
    }

    public static Subfield_Handler getSubfDetails(String subfields, String tag) {
        String query = "SELECT GL23DESC, GL23REPEAT FROM GLMSUBF WHERE GL23SUBF='" + subfields + "' " + "AND GL23TAG='" + tag + "' " + "AND GL23MARC='U'";
        Subfield_Handler getSubfields = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getSubfields = new Subfield_Handler(rs.getString("GL23DESC"), rs.getString("GL23REPEAT"));
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
        return getSubfields;
    }

    public static boolean Add_Subfield(String subfields) {
        String query = "SELECT COUNT(*) AS Count FROM GLMSUBF WHERE GL23SUBF = '" + subfields + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    deletable = false;
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
        return deletable;
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
