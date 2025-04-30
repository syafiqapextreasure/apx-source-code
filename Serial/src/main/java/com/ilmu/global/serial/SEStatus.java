/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global.serial;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SEStatus {
    private static Connection c;

    public static String getDescByCode(String code) {
        String query = "SELECT * FROM SESTAT WHERE SE04STAT = '" + code + "'";
        try {
            c = DBConnection.getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String string = rs.getString("SE04DESC");
                return string;
            }
            return "-";
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "-";
    }
}
