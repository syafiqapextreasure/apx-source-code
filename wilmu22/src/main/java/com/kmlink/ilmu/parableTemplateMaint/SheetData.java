/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.Sheet;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SheetData {
    private static Connection conn = null;

    public static List<Sheet> getSheetValue(String template, String branch, String selectedAttr) throws SQLException {
        String query = "SELECT LB01MTYPE, LB01PAPERTYPE, LB01SIZE, LB01WIDTH, LB01HEIGHT, LB01TOP, LB02LEFT, LB01ROWS, LB01COLS, LB01PRINT, LB01NOTES, LB01COLN, LB01TPLGRP, LB01TBLN, LB01DECIMAL FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        ArrayList<Sheet> sheet = new ArrayList<Sheet>();
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
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    sheet.add(new Sheet(rs.getString("LB02PROPERTY"), rs.getString("LB02VALUE")));
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
        return sheet;
    }
}
