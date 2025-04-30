/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Utility {
    public static String getBookTitle(String msMatNo) {
        String msBookTitle = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + msMatNo + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBookTitle = rsObj.getString("CT05SRAW");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msBookTitle;
    }

    public static String getBookCallNo(String msMatNo) {
        String msBookCallNo = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "Select CT05POINTER, CT05SRAW From CTCALL, CTPONT Where CT06MATNO = '" + msMatNo + "' " + "And  CT06POINTER = CT05POINTER " + "And  CT06TAG = '090' " + "And  CT06STATUS = 'A'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBookCallNo = rsObj.getString("CT05SRAW");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msBookCallNo;
    }

    public static String getMsCircStatus(String msDocNo) {
        String msCircStatus = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt = conn.createStatement();
            String sql = "SELECT CT03DOCNO, CT03STAT,GL36STAT, GL36DESC  FROM CTDOCM,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }

    public static String getMsCircStatus(String msDocNo, String msCircMatNo, String msCircFlag) {
        String msCircStatus = null;
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            stmt = conn.createStatement();
            String sql = "SELECT CT03DOCNO, CT03STAT,GL36STAT, GL36DESC FROM CTDOCM ,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }
}
