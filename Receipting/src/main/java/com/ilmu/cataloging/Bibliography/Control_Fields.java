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

public class Control_Fields {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL59MSTRCODE = null;
    private String GL59DESC = null;

    public Control_Fields(String GL59MSTRCODE, String GL59DESC) {
        this.GL59MSTRCODE = GL59MSTRCODE;
        this.GL59DESC = GL59DESC;
    }

    public String getGL59MSTRCODE() {
        return this.GL59MSTRCODE;
    }

    public String getGL59DESC() {
        return this.GL59DESC;
    }

    public static List<Control_Fields> getMasterCode(String controlTag) {
        ArrayList<Control_Fields> list = new ArrayList<Control_Fields>();
        String query = "Select GL59MSTRCODE, GL59DESC from GLMEDIMASTER WHERE GL59TAG='" + controlTag + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Control_Fields newMasterCode = new Control_Fields(rs.getString("GL59MSTRCODE"), rs.getString("GL59DESC"));
                    list.add(newMasterCode);
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
