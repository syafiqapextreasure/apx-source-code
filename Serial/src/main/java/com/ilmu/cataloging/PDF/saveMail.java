/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class saveMail {
    private static Connection conn = null;
    private static Statement stmt = null;

    public static saveMail SAVEMAIL(int newmailno, String liferayLogin, String vendor, String text, String letterId) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("letterId" + letterId);
            Library subject = Library.lettersubject(letterId);
            System.out.println("newmailno" + newmailno);
            System.out.println("liferayLogin" + liferayLogin);
            System.out.println("text" + text);
            System.out.println("vendor" + vendor);
            System.out.println("GL34SUBJECT" + subject.getName());
            valueInt.put("GL34MAILNO", newmailno);
            valueStr.put("GL34SENDER", liferayLogin);
            valueStr.put("GL34SDATE", date.format(now));
            valueStr.put("GL34STIME", time.format(now));
            valueStr.put("GL34MESSAGE", text);
            valueStr.put("GL34RECEIVER", vendor);
            valueStr.put("GL34SUBJECT", subject.getName());
            valueStr.put("GL34STATUS", "N");
            valueStr.put("GL34LETTERID", letterId);
            String add = QueryBuilder.createInsertQuery("GLMAIL", valueStr, valueInt, null);
            boolean isSuccess = DBQuery.runQuery(add);
            System.out.println(isSuccess);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int Get98VALUE(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='" + value + "'";
        System.out.println("SQL MAILNO" + sSQLStmt);
        int getMAILNO = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getMAILNO = rs.getInt("GL98VALUE");
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
        return getMAILNO;
    }

    public static boolean updatingmailno(int newmailno) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", "MAILNO");
        valueInt.put("GL98VALUE", newmailno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static saveMail RecordTransaction(String liferayLogin, String vsRefNo) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String sModule = "AC";
            String vsCode = "ACI0007";
            valueStr.put(String.valueOf(sModule) + "68ACTCODE", vsCode);
            valueStr.put(String.valueOf(sModule) + "68PATRONID", liferayLogin);
            valueStr.put(String.valueOf(sModule) + "68DATE", date.format(now));
            valueStr.put(String.valueOf(sModule) + "68TIME", time.format(now));
            valueStr.put(String.valueOf(sModule) + "68REFER", vsRefNo);
            valueStr.put(String.valueOf(sModule) + "68TEMNID", Handler.getLocalIPAdd());
            String add = QueryBuilder.createInsertQuery(String.valueOf(sModule) + "AUDIT", valueStr, null, null);
            boolean isSuccess = DBQuery.runQuery(add);
            System.out.println(isSuccess);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
