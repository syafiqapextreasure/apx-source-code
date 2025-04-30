/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.DocumentStatus;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class documnetStatus {
    private String Code = null;
    private String Description = null;
    private String ChgTo = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDescription() {
        return Handler.ifIsNull(this.Description);
    }

    public String getChgTo() {
        return Handler.ifIsNull(this.ChgTo);
    }

    public documnetStatus(String Code, String Description) {
        this.Code = Code;
        this.Description = Description;
    }

    public documnetStatus(String Code, String Description, String ChgTo) {
        this.Code = Code;
        this.Description = Description;
        this.ChgTo = ChgTo;
    }

    public static List<documnetStatus> viewDocStatus() {
        ArrayList<documnetStatus> view = new ArrayList<documnetStatus>();
        String query = "SELECT GL36STAT, GL36DESC FROM GLDOCS";
        System.out.println("queryviewDOCSTATUS = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    documnetStatus partview = new documnetStatus(rs.getString("GL36STAT"), rs.getString("GL36DESC"));
                    view.add(partview);
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
        return view;
    }

    public static List<documnetStatus> viewDocStatusNotIn(String Code) {
        ArrayList<documnetStatus> view = new ArrayList<documnetStatus>();
        String query = "SELECT GL36STAT, GL36DESC FROM GLDOCS WHERE GL36STAT NOT IN  ('" + Code + "')";
        System.out.println("queryviewDOCSTATUSNotin = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    documnetStatus partview = new documnetStatus(rs.getString("GL36STAT"), rs.getString("GL36DESC"));
                    view.add(partview);
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
        return view;
    }

    public static List<documnetStatus> getAll(String Code) {
        ArrayList<documnetStatus> view = new ArrayList<documnetStatus>();
        String query = "SELECT GL36STAT, GL36DESC, GL36CHGTO FROM GLDOCS WHERE GL36STAT = '" + Code + "'";
        System.out.println("queryviewDOCSTATUS = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    documnetStatus partview = new documnetStatus(rs.getString("GL36STAT"), rs.getString("GL36DESC"), Handler.ifIsNull(rs.getString("GL36CHGTO")));
                    view.add(partview);
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
        return view;
    }
}
