/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.template;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
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
        String query = "Select GL23SUBF, GL23DESC, GL23REPEAT from GLMSUBF WHERE GL23TAG='" + GL23TAG + "'";
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

    public static List<String> getNonRepeatSubf(String GL23TAG, String marc) {
        ArrayList<String> list = new ArrayList<String>();
        String query = "Select GL23SUBF,GL23DESC, GL23REPEAT from GLMSUBF WHERE GL23TAG='" + GL23TAG + "' and GL23REPEAT = 'N' and GL17MARC='" + marc + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String newSubfList = rs.getString("GL23SUBF");
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
}
