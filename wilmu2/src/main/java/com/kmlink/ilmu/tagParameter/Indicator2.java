/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.tagParameter;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Indicator2 {
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Indicator2(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<Indicator2> getIndicator2(String tagId) throws SQLException {
        String query = "SELECT GL18INDI, GL18DESC1 FROM GLMINDI WHERE GL18INDILVL='2' AND GL18MARC='U' AND GL18TAG=?";
        ArrayList<Indicator2> ind2 = new ArrayList<Indicator2>();
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, tagId);
                rs = pstmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    ind2.add(new Indicator2(rs.getString("GL18INDI"), rs.getString("GL18DESC1")));
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
        return ind2;
    }
}
