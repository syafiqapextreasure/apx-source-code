/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Release_Circulation;

import com.ilmu.global.DateTime;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ReleaseForCirculation {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String CT03DOCNO = null;
    private String CT05SRAW = null;
    private int CT05PONT = 0;
    private String CT03MATNO = null;

    public ReleaseForCirculation(String CT05SRAW, int CT05PONT, String CT03MATNO) {
        this.CT05SRAW = CT05SRAW;
        this.CT05PONT = CT05PONT;
        this.CT03MATNO = CT03MATNO;
    }

    public String getCT05SRAW() {
        return ISBD.convert(this.CT05PONT, this.CT05SRAW);
    }

    public void setCT05SRAW(String CT05SRAW) {
        System.out.println("erere34212" + CT05SRAW);
        this.CT05SRAW = CT05SRAW;
    }

    public String getCT03MATNO() {
        return this.CT03MATNO;
    }

    public void setCT03MATNO(String CT03MATNO) {
        this.CT03MATNO = CT03MATNO;
    }

    public static boolean CT03DOCNOExist(String CT03DOCNO) {
        String query = "Select COUNT(CT03DOCNO) AS Count FROM CTDOCM WHERE CT03DOCNO='" + CT03DOCNO + "'";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean TitleExist(String CT03DOCNO) {
        String query = "Select Count(CT05SRAW) As Count from CTTITL,CTPONT, CTDOCM where CT06POINTER=CT05POINTER and CT03MATNO=CT06MATNO and CT03DOCNO='" + CT03DOCNO + "' ";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean CallNoExist(String CT03DOCNO) {
        String query = "Select Count(CT05SRAW) as Count from CTCALL,CTPONT,CTDOCM where CT06POINTER=CT05POINTER and  CT03MATNO=CT06MATNO and CT03DOCNO='" + CT03DOCNO + "'";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean statAvailable(String CT03DOCNO) {
        String query = "Select COUNT(CT03DOCNO) AS Count FROM CTDOCM WHERE CT03STAT='A' and CT03DOCNO='" + CT03DOCNO + "'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static int getGLLIBR() {
        String query = "Select gl28resvtime FROM gllibr ";
        int days = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    days = Integer.parseInt(rs.getString("gl28resvtime"));
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
        return days;
    }

    public static List<ReleaseForCirculation> Title(String CT03DOCNO) throws SQLException {
        ArrayList<ReleaseForCirculation> tplList = new ArrayList<ReleaseForCirculation>();
        String query = " Select CT05SRAW, CT05POINTER, CT03MATNO from CTTITL,CTPONT, CTDOCM where CT06POINTER=CT05POINTER and CT03MATNO=CT06MATNO and CT03DOCNO='" + CT03DOCNO + "' ";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReleaseForCirculation new01_TemplateMaintenance = new ReleaseForCirculation(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), rs.getString("CT03MATNO"));
                    tplList.add(new01_TemplateMaintenance);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return tplList;
    }

    public static List<ReleaseForCirculation> CallNo(String CT03DOCNO) throws SQLException {
        ArrayList<ReleaseForCirculation> tplList = new ArrayList<ReleaseForCirculation>();
        String query = "Select CT05SRAW, CT05POINTER, CT03MATNO from CTCALL,CTPONT,CTDOCM where CT06POINTER=CT05POINTER and  CT03MATNO=CT06MATNO and CT03DOCNO='" + CT03DOCNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReleaseForCirculation new01_TemplateMaintenance = new ReleaseForCirculation(rs.getString("CT05SRAW"), Integer.parseInt(rs.getString("CT05POINTER")), rs.getString("CT03MATNO"));
                    tplList.add(new01_TemplateMaintenance);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return tplList;
    }

    public static List<ReleaseForCirculation> Matno(String CT03DOCNO) throws SQLException {
        ArrayList<ReleaseForCirculation> tplList = new ArrayList<ReleaseForCirculation>();
        String query = "Select CT03MATNO from CTDOCM where CT03DOCNO='" + CT03DOCNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ReleaseForCirculation new01_TemplateMaintenance = new ReleaseForCirculation("", 0, rs.getString("CT03MATNO"));
                    tplList.add(new01_TemplateMaintenance);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return tplList;
    }

    public static boolean updateRelease(String CT03DOCNO) {
        System.out.println(DateTime.displayToDBFormat(DateTime.getTodayDate()));
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", CT03DOCNO);
        valueStr.put("CT03RFCDATE", DateTime.displayToDBFormat(DateTime.getTodayDate()));
        valueStr.put("CT03STAT", "A");
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean itemOnHold(String CT03DOCNO) {
        System.out.println(DateTime.displayToDBFormat(DateTime.getTodayDate()));
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", CT03DOCNO);
        valueStr.put("CT03RFCDATE", DateTime.displayToDBFormat(DateTime.getTodayDate()));
        valueStr.put("CT03STAT", "H");
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateResv(String CT03DOCNO) {
        System.out.println(DateTime.displayToDBFormat(DateTime.getTodayDate()));
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.add(5, ReleaseForCirculation.getGLLIBR());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newDate = sdf.format(c.getTime());
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        condition.put("CI03PRIOR", 1);
        valueStr.put("CI03NDATE", DateTime.displayToDBFormat(DateTime.getTodayDate()));
        valueStr.put("CI03DDATE", newDate);
        valueStr.put("CI03NSTAT", "A");
        String query = QueryBuilder.createUpdateIntQuery("CIRESV", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
