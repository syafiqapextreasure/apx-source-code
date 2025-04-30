/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String patrName;

    public String getpatrName() {
        return this.patrName;
    }

    public Patron(String patrName) {
        this.patrName = patrName;
    }

    public static List<Patron> getPatrName(String code) {
        ArrayList<Patron> patrname = new ArrayList<Patron>();
        String query = " SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + code + "')";
        System.out.println("getPatrName = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Patron newpatrname = new Patron(rs.getString("GL14NAME"));
                    patrname.add(newpatrname);
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
        return patrname;
    }
}
