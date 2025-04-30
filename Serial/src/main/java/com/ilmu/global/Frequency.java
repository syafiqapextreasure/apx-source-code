/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Frequency {
    private String CODE = null;
    private String DESCRIPTION = null;

    public String getCODE() {
        return Handler.ifIsNull(this.CODE);
    }

    public String getDESCRIPTION() {
        return Handler.ifIsNull(this.DESCRIPTION);
    }

    public Frequency(String CODE, String DESCRIPTION) {
        this.CODE = CODE;
        this.DESCRIPTION = DESCRIPTION;
    }

    public static List<Frequency> getFrequency() {
        ArrayList<Frequency> list = new ArrayList<Frequency>();
        String query = "SELECT GL49FREQ, GL49DESC FROM GLFREQ";
        System.out.println("querygetFrequency = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Frequency newFrequency = new Frequency(rs.getString("GL49FREQ"), rs.getString("GL49DESC"));
                    list.add(newFrequency);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
