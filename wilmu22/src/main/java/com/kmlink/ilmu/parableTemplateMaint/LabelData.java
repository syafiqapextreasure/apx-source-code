/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.Label;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabelData {
    private static Connection conn = null;

    public static List<Label> getLabelValue(String template, String branch, String selectedAttr) throws SQLException {
        String query = "SELECT LB01WIDTH, LB01HEIGHT, LB01ROWS, LB01COLS FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        ArrayList<Label> data = new ArrayList<Label>();
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, selectedAttr);
                stmt.setString(2, template);
                stmt.setString(3, branch);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    Label label = new Label();
                    System.out.println(rs.getString("LB01WIDTH"));
                    System.out.println(rs.getString("LB01HEIGHT"));
                    System.out.println(rs.getString("LB01ROWS"));
                    System.out.println(rs.getString("LB01COLS"));
                    label.setValue(rs.getString("LB01WIDTH"));
                    label.setValue(rs.getString("LB01HEIGHT"));
                    label.setValue(rs.getString("LB01ROWS"));
                    label.setValue(rs.getString("LB01COLS"));
                    data.add(label);
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
        return data;
    }
}
