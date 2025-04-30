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

public class Subfields {
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

    public Subfields(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<Subfields> getSubfield(String tagId) {
        String query = "SELECT GL23SUBF, GL23DESC FROM GLMSUBF WHERE GL23MARC='U' AND GL23TAG=?";
        ArrayList<Subfields> subfields = new ArrayList<Subfields>();
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
                    subfields.add(new Subfields(rs.getString("GL23SUBF"), rs.getString("GL23DESC")));
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
        return subfields;
    }
}
