/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Paramips;

import com.ilmu.global.CurrentUser;
import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Paramips {
    private String PARA01TPLNAME = null;
    private String MP01ODATA = null;
    private String MP01OINDI1 = null;
    private String MP01OINDI2 = null;
    private String MP01OTAG = null;
    private String MP01BATCH = null;
    private String CT04BATCH = null;
    private String CT04MATNO = null;
    private String CT04RAW = null;
    private static Connection conn = null;

    public Paramips(String PARA01TPLNAME) {
        this.PARA01TPLNAME = PARA01TPLNAME;
    }

    public Paramips(String CT04BATCH, String CT04MATNO, String CT04RAW) {
        this.CT04BATCH = CT04BATCH;
        this.CT04MATNO = CT04MATNO;
        this.CT04RAW = CT04RAW;
    }

    public Paramips(String MP01BATCH, String MP01OTAG, String MP01OINDI1, String MP01OINDI2, String MP01ODATA) {
        this.MP01BATCH = MP01BATCH;
        this.MP01OTAG = MP01OTAG;
        this.MP01OINDI1 = MP01OINDI1;
        this.MP01OINDI2 = MP01OINDI2;
        this.MP01ODATA = MP01ODATA;
    }

    public String getPARA01TPLNAME() {
        return this.PARA01TPLNAME;
    }

    public String getMP01BATCH() {
        return this.MP01BATCH;
    }

    public String getMP01OTAG() {
        return this.MP01OTAG;
    }

    public String getMP01OINDI1() {
        return this.MP01OINDI1;
    }

    public String getM01OINDI2() {
        return this.MP01OINDI2;
    }

    public String getMP01ODATA() {
        return this.MP01ODATA;
    }

    public String getCT04BATCH() {
        return this.CT04BATCH;
    }

    public String getCT04MATNO() {
        return this.CT04MATNO;
    }

    public String getCT04RAW() {
        return this.CT04RAW;
    }

    public static List<Paramips> LoadTemplate() {
        ArrayList<Paramips> list = new ArrayList<Paramips>();
        String query = "  Select DISTINCT MP02TPLNAME from MPSCRPT";
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                Paramips Templates = new Paramips(rs.getString("MP02TPLNAME"));
                list.add(Templates);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Paramips> retrieveCTWORK() {
        ArrayList<Paramips> list = new ArrayList<Paramips>();
        String query = "  Select CT04BATCH, CT04MATNO, CT04RAW from CTWORK where CT04TAG='245' AND CT04BATCH IS NOT NULL";
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                Paramips Templates = new Paramips(rs.getString("CT04BATCH"), rs.getString("CT04MATNO"), rs.getString("CT04RAW"));
                list.add(Templates);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Paramips> RetrieveMPScript(String bufferno) {
        boolean isSuccess = true;
        StringBuilder batchNo = new StringBuilder();
        ArrayList<Paramips> list = new ArrayList<Paramips>();
        String query = "  Select MP01BATCH, MP01OTAG, MP01OINDI1, MP01OINDI2, MP01ODATA from MPDATA";
        try {
            Connection conn = null;
            Statement stmt1 = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(query);
            while (rs.next()) {
                Paramips Templates = new Paramips(rs.getString("MP01BATCH"));
                HashMap<String, String> valueStr = new HashMap<String, String>();
                HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
                String subfield = rs.getString("MP01ODATA").substring(0, 1);
                String CT04SCONV = "";
                if (subfield.equals("|")) {
                    System.out.println("got");
                    CT04SCONV = Handler.convUpperCase(Handler.rawToDisplayFormat(rs.getString("MP01ODATA")));
                } else {
                    System.out.println("no");
                    CT04SCONV = Handler.convUpperCase(rs.getString("MP01ODATA"));
                }
                Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
                int counter = CT04COUNTER.getGL98VALUE();
                valueStr.put("CT04MATNO", bufferno);
                valueStr.put("CT04MARC", "U");
                valueStr.put("CT04TAG", rs.getString("MP01OTAG"));
                valueStr.put("CT04INDI1", rs.getString("MP01OINDI1"));
                valueStr.put("CT04INDI2", rs.getString("MP01OINDI2"));
                String rawData = rs.getString("MP01ODATA");
                rawData = rawData.replace("'", "''");
                if (rs.getString("MP01ODATA").length() <= 2000) {
                    valueStr.put("CT04RAW", rawData);
                }
                valueStr.put("CT04CREDATE", DateTime.displayToDBFormat(DateTime.getTodayDate()));
                valueStr.put("CT04CRETIME", DateTime.timeToDBFormat(DateTime.getTodayTime()));
                valueStr.put("CT04CREBY", CurrentUser.getCurrUser());
                valueStr.put("CT04MODIDATE", DateTime.displayToDBFormat(DateTime.getTodayDate()));
                valueStr.put("CT04MODITIME", DateTime.timeToDBFormat(DateTime.getTodayTime()));
                valueStr.put("CT04MODIBY", CurrentUser.getCurrUser());
                valueStr.put("CT04STATUS", "A");
                valueStr.put("CT04SCONV", CT04SCONV);
                valueStr.put("CT04BATCH", rs.getString("MP01BATCH"));
                valueInt.put("CT04COUNTER", counter);
                conn.commit();
                String insertQuery = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
                isSuccess = DBQuery.runQuery(insertQuery);
                list.add(Templates);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void Delete_CTWORK(String CT04BATCH) {
        String query = "DELETE FROM MPDATA WHERE MP01BATCH = '" + CT04BATCH + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
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
    }
}
