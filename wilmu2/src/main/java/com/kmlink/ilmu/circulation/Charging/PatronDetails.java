/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Global.DateFormatter;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatronDetails {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL14MEMDATE;
    private String GL14EXPDATE;
    private String GL14NAME;
    private String GL14PATR;
    private String GL14STAT;
    private String GL14IMG;
    private String GL14CATE;
    private String CT03DOCNO;
    private String CT05SCONV;
    private String CT03STAT;

    public String getGL14PATR() {
        return this.GL14PATR;
    }

    public void setGL14PATR(String GL14PATR) {
        this.GL14PATR = GL14PATR;
    }

    public String getGL14NAME() {
        return this.GL14NAME;
    }

    public void setGL14NAME(String GL14NAME) {
        this.GL14NAME = GL14NAME;
    }

    public String getGL14MEMDATE() {
        return this.GL14MEMDATE;
    }

    public void setGL14MEMDATE(String GL14MEMDATE) {
        this.GL14MEMDATE = GL14MEMDATE;
    }

    public String getGL14EXPDATE() {
        return this.GL14EXPDATE;
    }

    public void setGL14EXPDATE(String GL14EXPDATE) {
        this.GL14EXPDATE = GL14EXPDATE;
    }

    public String getGL14STAT() {
        return this.GL14STAT;
    }

    public void setGL14STAT(String GL14STAT) {
        this.GL14STAT = GL14STAT;
    }

    public String getCT03DOCNO() {
        return this.CT03DOCNO;
    }

    public void setCT03DOCNO(String CT03DOCNO) {
        this.CT03DOCNO = CT03DOCNO;
    }

    public String getCT03STAT() {
        return this.CT03STAT;
    }

    public void setCT03STAT(String CT03STAT) {
        this.CT03STAT = CT03STAT;
    }

    public String getCT05SCONV() {
        return this.CT05SCONV;
    }

    public void setCT05SCONV(String CT05SCONV) {
        this.CT05SCONV = CT05SCONV;
    }

    public String getGL14CATE() {
        return this.GL14CATE;
    }

    public void setGL14CATE(String GL14CATE) {
        this.GL14CATE = GL14CATE;
    }

    public String getGL14IMG() {
        return this.GL14IMG;
    }

    public void setGL14IMG(String GL14IMG) {
        this.GL14IMG = GL14IMG;
    }

    public PatronDetails(String CT03DOCNO, String CT03STAT, String CT05SCONV) {
        this.CT03DOCNO = CT03DOCNO;
        this.CT03STAT = CT03STAT;
        this.CT05SCONV = CT05SCONV;
    }

    public PatronDetails(String GL14PATR, String GL14NAME, String GL14MEMDATE, String GL14EXPDATE, String GL14STAT, String GL14CATE) {
        this.GL14PATR = GL14PATR;
        this.GL14NAME = GL14NAME;
        this.GL14MEMDATE = GL14MEMDATE;
        this.GL14EXPDATE = GL14EXPDATE;
        this.GL14STAT = GL14STAT;
        this.GL14CATE = GL14CATE;
    }

    public static List<PatronDetails> getPatDetail(String GL14PATR) throws SQLException, IOException {
        ArrayList<PatronDetails> details = new ArrayList<PatronDetails>();
        String query = "SELECT GL14PATR,GL14NAME,GL14MEMDATE,GL14EXPDATE,GL08DESC,GL07DESC FROM GLPATR, GLSTAT, GLCATE WHERE GL14STAT=GL08STAT and GL14CATE=GL07CATE and GL14PATR='" + GL14PATR + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                String imgLen = "";
                String images = "";
                while (rs.next()) {
                    PatronDetails patronDetails = new PatronDetails(rs.getString("GL14PATR"), rs.getString("GL14NAME"), DateFormatter.DBToDisplayFormat(rs.getString("GL14MEMDATE")), DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE")), rs.getString("GL08DESC"), rs.getString("GL07DESC"));
                    details.add(patronDetails);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return details;
    }

    public static List<PatronDetails> getCTDOCM(String CT03DOCNO) throws SQLException {
        ArrayList<PatronDetails> details = new ArrayList<PatronDetails>();
        String query = "SELECT [CT03DOCNO], CT05SCONV,[CT03STAT] FROM [CTDOCM], CTPONT, CTTITL where CT06MATNO=CT03MATNO and CT06POINTER=CT05POINTER and CT06TAG='245' and CT03DOCNO='" + CT03DOCNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatronDetails patronDetails = new PatronDetails(rs.getString("CT03DOCNO"), rs.getString("CT05SCONV"), rs.getString("CT03STAT"));
                    details.add(patronDetails);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return details;
    }
}
