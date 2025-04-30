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

public class orderMode {
    private String ACQMODE = null;
    private String DESC = null;

    public String getACQMODE() {
        return Handler.ifIsNull(this.ACQMODE);
    }

    public String getDESC() {
        return Handler.ifIsNull(this.DESC);
    }

    public orderMode(String ACQMODE, String DESC) {
        this.ACQMODE = ACQMODE;
        this.DESC = DESC;
    }

    public static List<orderMode> getOrderMode() {
        ArrayList<orderMode> list = new ArrayList<orderMode>();
        String query = "SELECT GL42ACQMODE, GL42DESC FROM GLACQM";
        System.out.println("querygetOrderModec = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    orderMode neworderMode = new orderMode(rs.getString("GL42ACQMODE"), rs.getString("GL42DESC"));
                    list.add(neworderMode);
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
