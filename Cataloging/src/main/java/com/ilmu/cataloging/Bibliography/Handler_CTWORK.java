/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.CurrentUser;
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Handler_CTWORK {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    String CT04MATNO = null;
    String CT04POINTER = null;
    String CT06MATNO = null;
    String CT04MARC = null;
    String CT04TAG = null;
    String CT04INDI1 = null;
    String CT04INDI2 = null;
    String CT04RAW = null;
    String CT04STATUS = null;
    String SUBFDATA = null;
    String GL17DESC = null;
    String GL17TABNAME = null;
    String CT04CREBY = null;
    String CT04CREDATE = null;
    String CT04CRETIME = null;
    String CT04MODIBY = null;
    String CT04MODIDATE = null;
    String CT04MODITIME = null;
    String GL17MANDA = null;

    public Handler_CTWORK(String CT04MATNO, String CT04MARC, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, String CT04STATUS, String GL17DESC, String GL17TABNAME, String GL17MANDA) {
        this.CT04MATNO = CT04MATNO;
        this.CT04MARC = CT04MARC;
        this.CT04TAG = CT04TAG;
        this.CT04INDI1 = CT04INDI1;
        this.CT04INDI2 = CT04INDI2;
        this.CT04RAW = CT04RAW;
        this.CT04STATUS = CT04STATUS;
        this.GL17DESC = GL17DESC;
        this.GL17TABNAME = GL17TABNAME;
        this.GL17MANDA = GL17MANDA;
    }

    public Handler_CTWORK(String CT04MATNO, String CT04STATUS, String CT04CREBY, String CT04CREDATE, String CT04CRETIME, String CT04MODIBY, String CT04MODIDATE, String CT04MODITIME) {
        this.CT04MATNO = CT04MATNO;
        this.CT04STATUS = CT04STATUS;
        this.CT04CREBY = CT04CREBY;
        this.CT04CREDATE = CT04CREDATE;
        this.CT04CRETIME = CT04CRETIME;
        this.CT04MODIBY = CT04MODIBY;
        this.CT04MODIDATE = CT04MODIDATE;
        this.CT04MODITIME = CT04MODITIME;
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getCT04POINTER() {
        return this.CT04POINTER;
    }

    public String getCT04MATNO() {
        return this.CT04MATNO;
    }

    public String getCT04MARC() {
        return this.CT04MARC;
    }

    public String getCT04CREBY() {
        return this.CT04CREBY;
    }

    public String getCT04CREDATE() {
        return DateTime.ifDateNull(this.CT04CREDATE);
    }

    public String getCT04CRETIME() {
        return DateTime.ifTimeNull(this.CT04CRETIME);
    }

    public String getCT04MODIBY() {
        return this.CT04MODIBY;
    }

    public String getCT04MODIDATE() {
        return DateTime.ifDateNull(this.CT04MODIDATE);
    }

    public String getCT04MODITIME() {
        return DateTime.ifTimeNull(this.CT04MODITIME);
    }

    public String getCT04TAG() {
        return this.CT04TAG;
    }

    public String getCT04INDI1() {
        return this.CT04INDI1;
    }

    public String getCT04INDI2() {
        return this.CT04INDI2;
    }

    public String getSUBFDATA() {
        return this.SUBFDATA;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getCT04RAW() {
        return this.CT04RAW;
    }

    public String getCT04STATUS() {
        return this.CT04STATUS;
    }

    public String getGL17MANDA() {
        return this.GL17MANDA;
    }

    public static Handler_CTWORK DeleteInsertCTWORK(String CT04MATNO, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, int CT04COUNTER, String CT04SCONV, String CT02CREDATE, String CT02CRETIME, String CT02CREBY, String CT02MODIBY, String CT02MODIDATE, String CT02MODITIME) {
        String query = "SELECT COUNT(CT04MATNO) AS Count FROM CTWORK WHERE CT04MATNO = '" + CT04MATNO + "' AND CT04TAG='" + CT04TAG + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                c.setAutoCommit(false);
                if (rs.next()) {
                    System.out.println("delete");
                    String deleteQuery = "DELETE FROM CTWORK WHERE CT04MATNO = '" + CT04MATNO + "' AND CT04TAG='" + CT04TAG + "'";
                    PreparedStatement delete = c.prepareStatement(deleteQuery);
                    delete.execute();
                    c.commit();
                }
                HashMap<String, String> valueStr = new HashMap<String, String>();
                HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
                valueStr.put("CT04MATNO", CT04MATNO);
                valueStr.put("CT04MARC", "U");
                valueStr.put("CT04TAG", CT04TAG);
                valueStr.put("CT04INDI1", CT04INDI1);
                valueStr.put("CT04INDI2", CT04INDI2);
                if (CT04RAW.length() <= 2000) {
                    valueStr.put("CT04RAW", CT04RAW);
                }
                System.out.println(DateTime.timeToDBFormat(CT02CRETIME));
                CurrentUser user = new CurrentUser();
                valueStr.put("CT04CREDATE", DateTime.displayToDBFormat(CT02CREDATE));
                valueStr.put("CT04CRETIME", DateTime.timeToDBFormat(CT02CRETIME));
                valueStr.put("CT04CREBY", CT02CREBY);
                valueStr.put("CT04MODIDATE", DateTime.displayToDBFormat(CT02MODIDATE));
                valueStr.put("CT04MODITIME", DateTime.timeToDBFormat(CT02MODITIME));
                valueStr.put("CT04MODIBY", user.getCurrUser());
                valueStr.put("CT04STATUS", "A");
                valueStr.put("CT04SCONV", CT04SCONV);
                valueInt.put("CT04COUNTER", CT04COUNTER);
                c.commit();
                String insertQuery = QueryBuilder.createInsertQuery("CTWORK", valueStr, valueInt, null);
                boolean isSuccess = DBQuery.runQuery(insertQuery);
                System.out.println(isSuccess);
            }
            catch (SQLException e) {
                try {
                    c.rollback();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
                try {
                    c.close();
                    c.setAutoCommit(true);
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
                c.close();
                c.setAutoCommit(true);
                stmt.close();
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
