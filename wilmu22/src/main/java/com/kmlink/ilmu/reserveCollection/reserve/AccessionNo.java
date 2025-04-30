/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.reserveCollection.reserve;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessionNo {
    private static Connection conn;
    private String accNo;
    private String location;
    private String branch;
    private String itemCat;
    private String smd;
    private String status;

    public AccessionNo(String accNo, String location, String branch, String itemCat, String smd, String status) {
        this.accNo = accNo;
        this.location = location;
        this.branch = branch;
        this.itemCat = itemCat;
        this.smd = smd;
        this.status = status;
    }

    public static List<AccessionNo> getAllAccessionNo(String controlNo) throws SQLException {
        String query = "SELECT CT03DOCNO, T2.GL05DESC, T3.GL71DESC, T4.GL10DESC, T5.GL47DESC , T6.GL36DESC FROM CTDOCM T1\r\nLEFT JOIN GLLOCA T2 ON (T2.GL05LOCA = T1.CT03LOCA)\r\nLEFT JOIN GLBRNC T3 ON (T3.GL71BRNC = T2.GL05BRNC)\r\nLEFT JOIN GLICAT T4 ON (T4.GL10ICAT = T1.CT03ICAT)\r\nLEFT JOIN GLSMD T5 ON (T5.GL47SMD = T1.CT03SMD)\r\nLEFT JOIN GLDOCS T6 ON (T6.GL36STAT = T1.CT03STAT)\r\nWHERE CT03MATNO=?";
        ArrayList<AccessionNo> AllAccesion = new ArrayList<AccessionNo>();
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, controlNo);
            rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            int i = 1;
            while (i <= columnCount) {
                System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                ++i;
            }
            while (rs.next()) {
                AllAccesion.add(new AccessionNo(rs.getString("CT03DOCNO"), rs.getString("GL05DESC"), rs.getString("GL71DESC"), rs.getString("GL10DESC"), rs.getString("GL47DESC"), rs.getString("GL36DESC")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return AllAccesion;
    }
}
