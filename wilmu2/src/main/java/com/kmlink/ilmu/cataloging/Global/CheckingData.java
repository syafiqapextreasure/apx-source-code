/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckingData {
    private static final String SQL_QUERY = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = ?";

    public static String getFunc(String columnName) {
        if (columnName == null || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("Column name cannot be null or empty");
        }

        String data = "";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_QUERY)) {
            
            ps.setString(1, columnName);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("GL99VALUE");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving function value for column: " + columnName, e);
        }
        
        return data;
    }
}
