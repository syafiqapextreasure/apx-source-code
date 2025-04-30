/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta.object;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class labelBranch {
    public static List<String> getLabelBranch() {
        ArrayList<String> branches = new ArrayList<String>();
        String query = "SELECT DISTINCT(T1.GL05BRNC), T2.GL71DESC FROM GLLOCA T1 LEFT JOIN GLBRNC T2 ON (T1.GL05BRNC = T2.GL71BRNC)";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branches.add(rs.getString("GL71DESC"));
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
        return branches;
    }
}
