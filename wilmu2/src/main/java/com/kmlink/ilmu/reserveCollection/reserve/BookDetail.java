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

public class BookDetail {
    private static Connection conn;
    private String callNo;
    private String subject;
    private String title;
    private String author;
    private String publication;
    private String location;
    private String branch;

    public String getCallNo() {
        return this.callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return this.publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        BookDetail.conn = conn;
    }

    public BookDetail() {
    }

    public BookDetail(String callNo, String title, String author, String publication, String location, String branch) {
        this.callNo = callNo;
        this.title = title;
        this.author = author;
        this.publication = publication;
        this.location = location;
        this.branch = branch;
    }

    public BookDetail(String callNo, String subject, String title, String author, String publication, String location, String branch) {
        this.callNo = callNo;
        this.subject = subject;
        this.title = title;
        this.author = author;
        this.publication = publication;
        this.location = location;
        this.branch = branch;
    }

    public static List<BookDetail> getBookDetail(String controlNo) {
        System.out.println("in get book detail");
        String query = "SELECT REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' ') AS CallNo,\r\nREPLACE(REPLACE(REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS TITLE,\r\nREPLACE(REPLACE(T7.CT05SRAW, '|a',''),'|b', ' ') AS AUTHOR,\r\nREPLACE(REPLACE(T9.CT05SRAW, '|a',''),'|b', ' ') AS PUBLICATION,\r\nT11.GL05DESC, T12.GL71DESC\r\nFROM CTMATM T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CT02MATNO AND T2.CT06TAG = '090')\r\nLEFT JOIN CTCALL T3 ON (T3.CT05POINTER = T2.CT06POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CT02MATNO AND T4.CT06TAG = '245')\r\nLEFT JOIN CTTITL T5 ON (T5.CT05POINTER = T4.CT06POINTER )\r\nLEFT JOIN CTPONT T6 ON (T6.CT06MATNO = T1.CT02MATNO AND T6.CT06TAG = '100')\r\nLEFT JOIN CTAUTH T7 ON (T7.CT05POINTER = T6.CT06POINTER )\r\nLEFT JOIN CTPONT T8 ON (T8.CT06MATNO = T1.CT02MATNO AND T8.CT06TAG = '260')\r\nLEFT JOIN CTPUBL T9 ON (T9.CT05POINTER = T8.CT06POINTER )\r\nLEFT JOIN CTDOCM T10 ON (T10.CT03MATNO = T1.CT02MATNO)\r\nLEFT JOIN GLLOCA T11 ON (T11.GL05LOCA = T10.CT03LOCA)\r\nLEFT JOIN GLBRNC T12 ON (T12.GL71BRNC = T11.GL05BRNC)\r\nWHERE CT02MATNO=?";
        ArrayList<BookDetail> bookDetail = new ArrayList<BookDetail>();
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
                bookDetail.add(new BookDetail(rs.getString("CallNo"), rs.getString("TITLE"), rs.getString("AUTHOR"), rs.getString("Publication"), rs.getString("GL05DESC"), rs.getString("GL71DESC")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bookDetail;
    }
}
