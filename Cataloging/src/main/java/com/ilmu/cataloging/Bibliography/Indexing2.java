/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.cataloging.Bibliography.Bibliography;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Indexing2 {
    public String CT05POINTER;
    public String CT05SRAW;
    public String CT05SCONV;
    public String CT06HITS;
    public String CT06COUNT;

    public String getCT05POINTER() {
        return this.CT05POINTER;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public String getCT05SCONV() {
        return this.CT05SCONV;
    }

    public String getCT06HITS() {
        return this.CT06HITS;
    }

    public String getCT06COUNT() {
        return this.CT06COUNT;
    }

    public Indexing2(String CT05POINTER, String CT05SRAW, String CT05SCONV, String CT06HITS, String CT06COUNT) {
        this.CT05POINTER = CT05POINTER;
        this.CT05SRAW = CT05SRAW;
        this.CT05SCONV = CT05SCONV;
        this.CT06HITS = CT06HITS;
        this.CT06COUNT = CT06COUNT;
    }

    public static List<Indexing2> find5SRAW(String GL17TAG, String conv) {
        ArrayList<Indexing2> list = new ArrayList<Indexing2>();
        String sql = "SELECT * FROM  " + Bibliography.getMarkTagTableName(GL17TAG) + " WHERE CT05SCONV = '" + conv + "' ";
        System.out.println("find5SRAW" + sql);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String raws = Handler.ifIsNull(rs.getString("CT05RAW"));
                    System.out.println("line 67 : " + raws);
                    raws = raws != null ? String.valueOf(Handler.ifIsNull(rs.getString("CT05SRAW"))) + " " + Handler.ifIsNull(rs.getString("CT05RAW")) : Handler.ifIsNull(rs.getString("CT05SRAW"));
                    Indexing2 newGetAllBy = new Indexing2(Handler.ifIsNull(rs.getString("CT05POINTER")), raws, Handler.ifIsNull(rs.getString("CT05SCONV")), Handler.ifIsNull(rs.getString("CT05HITS")), Handler.ifIsNull(rs.getString("CT05COUNT")));
                    list.add(newGetAllBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                    stmt.close();
                    rs.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
                stmt.close();
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
