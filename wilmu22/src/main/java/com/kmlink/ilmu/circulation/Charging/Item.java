/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.sql.ItemSQL;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
    private String vsItemCategory;
    private String vsItemCategoryDesc;
    private String vsItemUnit;
    private String vsItemLastDate;
    private String vsItemDateRec;
    private String vsItemRecBy;
    private String vsItemDisplay;
    private String vsITemReservec;
    private int vsItemElig;
    private String vsItemCode;
    private String msItemTitle;
    private String msItemCallNo;
    private String msItemCircStatus;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    public static String getItemBrnc(String msdocno) {
        String sql = ItemSQL.getMsItemBrnc(msdocno);
        String branch = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    branch = String.valueOf(rsObj.getString("GL05BRNC"));
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
        return branch;
    }

    public String GetCircStatus(String vsItemID) {
        String itemStatus = null;
        String sql = ItemSQL.getMsCircStatus(vsItemID);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    itemStatus = rsObj.getString("CT03STAT");
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
        return itemStatus;
    }

    public static String getReservedPatron(String msAccession) {
        String patronName = null;
        String sql = ItemSQL.getMsReservedPatron(msAccession);
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    patronName = String.valueOf(rsObj.getString("GL14NAME")) + ", " + rsObj.getString("GL14PATR");
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
        return patronName;
    }

    public static boolean getCircSattelite() {
        boolean exist = false;
        String sql = ItemSQL.getMsCircSattelite();
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (!rsObj.getString("GL99VALUE").equals("Y")) continue;
                    exist = true;
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
        return exist;
    }

    public static String getPatrSatelitteBrnc(String msPatronID) {
        String branch = "";
        String sql = ItemSQL.getMsPatrSatelitteBrnc(msPatronID);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    branch = String.valueOf(rsObj.getString("GL05BRNC"));
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
        return branch;
    }

    public static boolean checkReservationStatus(String msRetMatNo) {
        boolean success = false;
        boolean count = false;
        int reservedno = 0;
        String sql = ItemSQL.getMsChkResvStat(msRetMatNo);
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    reservedno = Integer.parseInt(rsObj.getString("ReservedNO"));
                    success = reservedno != 0;
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
        return success;
    }
}
