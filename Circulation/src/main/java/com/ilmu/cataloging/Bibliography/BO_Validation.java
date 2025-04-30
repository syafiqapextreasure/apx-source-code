/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BO_Validation {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL17TAG = null;
    private String GL23TAG = null;
    private String GL23SUBF = null;

    public BO_Validation(String GL17TAG) {
        this.GL17TAG = GL17TAG;
    }

    public BO_Validation(String GL23TAG, String GL23SUBF) {
        this.GL23TAG = GL23TAG;
        this.GL23SUBF = GL23SUBF;
    }

    public String getGL17TAG() {
        return this.GL17TAG;
    }

    public String getGL23TAG() {
        return this.GL23TAG;
    }

    public String getGL23SUBF() {
        return this.GL23SUBF;
    }

    public static List<BO_Validation> chkMandaTag(String GL17MARC) {
        String query = "Select GL17TAG from GLTAGP WHERE GL17MANDA='Y' AND GL17MARC='" + GL17MARC + "'";
        ArrayList<BO_Validation> list = new ArrayList<BO_Validation>();
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Validation getMandatoryTag = new BO_Validation(rs.getString("GL17TAG"));
                    list.add(getMandatoryTag);
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

    public static List<BO_Validation> mandatoryTag() {
        String query = "Select GL17TAG from GLTAGP WHERE GL17MANDA='Y' AND GL17MARC='U'";
        ArrayList<BO_Validation> list = new ArrayList<BO_Validation>();
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Validation getMandatoryTag = new BO_Validation(rs.getString("GL17TAG"));
                    list.add(getMandatoryTag);
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

    public static List<BO_Validation> validSubfields(String GL23TAG) {
        String query = "Select GL23TAG, GL23SUBF from GLMSUBF WHERE GL23TAG='" + GL23TAG + "' AND GL23MARC='U'";
        ArrayList<BO_Validation> list = new ArrayList<BO_Validation>();
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Validation getValidSubfields = new BO_Validation(rs.getString("GL23TAG"), rs.getString("GL23SUBF"));
                    list.add(getValidSubfields);
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

    public static List<BO_Validation> repeatTags() {
        String query = "Select GL17TAG from GLTAGP WHERE GL17REPEAT='N' AND GL17MARC='U'";
        ArrayList<BO_Validation> list = new ArrayList<BO_Validation>();
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Validation getMandatoryTag = new BO_Validation(rs.getString("GL17TAG"));
                    list.add(getMandatoryTag);
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
}
