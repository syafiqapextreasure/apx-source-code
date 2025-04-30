/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Weeding;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Weeding {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String WE03WDATE = null;
    public String WE03TITL = null;
    public String WE03DOCNO = null;
    private String CT02IDXDATE = null;
    private String CT02IDXBY = null;
    private String CT02IDXTIME = null;
    private String CT02TYPE = null;
    private String CT02MATNO = null;
    private String CT02CREDATE = null;
    private String CT02STATUS = null;
    private String CT02CRETIME = null;
    private String CT02CREBY = null;

    public Weeding(String WE03DOCNO, String WE03TITL, String WE03WDATE) {
        this.WE03DOCNO = WE03DOCNO;
        this.WE03TITL = WE03TITL;
        this.WE03WDATE = WE03WDATE;
    }

    public Weeding(String CT02MATNO, String CT02STATUS, String CT02CREDATE, String CT02CREBY, String CT02CRETIME, String CT02TYPE, String CT02IDXDATE, String CT02IDXBY, String CT02IDXTIME) {
        this.CT02MATNO = CT02MATNO;
        this.CT02STATUS = CT02STATUS;
        this.CT02CREDATE = CT02CREDATE;
        this.CT02CREBY = CT02CREBY;
        this.CT02CRETIME = CT02CRETIME;
        this.CT02TYPE = CT02TYPE;
        this.CT02IDXBY = CT02IDXBY;
        this.CT02IDXDATE = CT02IDXDATE;
        this.CT02IDXTIME = CT02IDXTIME;
    }

    public String getWE03WDATE() {
        return this.WE03WDATE;
    }

    public String getWE03TITL() {
        return this.WE03TITL;
    }

    public String getWE03DOCNO() {
        return this.WE03DOCNO;
    }

    public String getCT02MATNO() {
        return this.CT02MATNO;
    }

    public String getCT02STATUS() {
        return this.CT02STATUS;
    }

    public String getCT02TYPE() {
        return this.CT02TYPE;
    }

    public String getCT02IDXDATE() {
        return DateTime.formatDate(this.CT02IDXDATE);
    }

    public String getCT02IDXTIME() {
        return DateTime.formatTime(this.CT02IDXTIME);
    }

    public String getCT02IDXBY() {
        return this.CT02IDXBY;
    }

    public String getCT02CREDATE() {
        return DateTime.formatDate(this.CT02CREDATE);
    }

    public String getCT02CRTIME() {
        return DateTime.formatTime(this.CT02CRETIME);
    }

    public String getCT02CREBY() {
        return this.CT02CREBY;
    }

    public static Weeding getCTMATM(String controlNoInput) {
        String query = "SELECT CT02MATNO, CT02STATUS, CT02CRDATE, CT02CREBY, CT02CRETIME, CT02TYPE, CT02IDXBY, CT02IDXDATE,CT02IDXTIME FROM CTMATM WHERE CT02MATNO ='" + controlNoInput + "'";
        Weeding newSE01_SearchByMatno = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_SearchByMatno = new Weeding(rs.getString("CT02MATNO"), Handler.CatalogueIDXStatus(rs.getString("CT02STATUS")), rs.getString("CT02CRDATE"), rs.getString("CT02CREBY"), Handler.ifIsNull(rs.getString("CT02CRETIME")), Handler.CatalogueType(rs.getString("CT02TYPE")), rs.getString("CT02IDXDATE"), rs.getString("CT02IDXBY"), Handler.ifIsNull(rs.getString("CT02IDXTIME")));
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
        return newSE01_SearchByMatno;
    }

    public static boolean rcrdExist_CTWORK(String CT04MATNO) {
        String query = "SELECT CT04MATNO FROM CTWORK WHERE CT04MATNO='" + Handler.replaceWithA(CT04MATNO) + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    deletable = true;
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
        return deletable;
    }

    public static boolean rcrdExist_CTMATM(String CT03MATNO) {
        String query = "SELECT COUNT(*) AS CT03MATNO FROM CTDOCM WHERE CT03MATNO='" + CT03MATNO + "' and CT03STAT='A'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println("none");
                    deletable = Integer.parseInt(rs.getString("CT03MATNO")) <= 0;
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
        return deletable;
    }

    public static void updateCTMATM(String CT02MATNO) {
        String query = "UPDATE CTMATM SET CT02STATUS='D', CT02DLTDATE='" + DateTime.getTodaySystemDate() + "', " + "CT02DLTIME='" + DateTime.timeToDBFormat(DateTime.getTodayTime()) + "'WHERE CT02MATNO = '" + CT02MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }
}
