/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Template_Maint;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Indicator_Handler {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String GL18INDILVL = null;
    public String GL18INDI = null;
    private String GL18DESC = null;

    public Indicator_Handler(String GL18INDILVL, String GL18DESC, String GL18INDI) {
        this.GL18INDILVL = GL18INDILVL;
        this.GL18DESC = GL18DESC;
        this.GL18INDI = GL18INDI;
    }

    public String getGL18INDILVL() {
        return this.GL18INDILVL;
    }

    public String getGL18INDI() {
        return this.GL18INDI;
    }

    public String getGL18DESC() {
        return this.GL18DESC;
    }

    public static List<Indicator_Handler> getIndi1(String GL18TAG, String marc) {
        ArrayList<Indicator_Handler> list = new ArrayList<Indicator_Handler>();
        String query = "Select GL18INDILVL, GL18DESC1, GL18INDI FROM GLMINDI WHERE GL18TAG='" + GL18TAG + "' AND GL18INDILVL='1' and GL18MARC='" + marc + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Indicator_Handler indi1 = new Indicator_Handler(rs.getString("GL18INDILVL"), rs.getString("GL18DESC1"), rs.getString("GL18INDI"));
                    list.add(indi1);
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

    public static List<Indicator_Handler> getIndi2(String GL18TAG, String marc) {
        ArrayList<Indicator_Handler> list = new ArrayList<Indicator_Handler>();
        String query = "Select GL18INDILVL, GL18DESC1, GL18INDI FROM GLMINDI WHERE GL18TAG='" + GL18TAG + "' AND GL18INDILVL='2' and GL18MARC='" + marc + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Indicator_Handler indi2 = new Indicator_Handler(rs.getString("GL18INDILVL"), rs.getString("GL18DESC1"), rs.getString("GL18INDI"));
                    list.add(indi2);
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
