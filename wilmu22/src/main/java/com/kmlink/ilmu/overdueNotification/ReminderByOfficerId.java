/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.overdueNotification;

import com.kmlink.ilmu.overdueNotification.CalculateFine;
import com.kmlink.ilmu.overdueNotification.OverdueNotification;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReminderByOfficerId {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    private static DBConnection db = null;
    public static OverdueNotification overdueNotify = null;

    public static List<OverdueNotification> getReminderByOfficerId(String officerId, String reminder, String convertedDate) throws ParseException {
        ArrayList<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        String sqlCurrentDate = String.valueOf(convertedDate.substring(6, 10)) + convertedDate.substring(3, 5) + convertedDate.substring(0, 2);
        DBConnection db = new DBConnection();
        String mssqlQuery1 = "SELECT T1.CI02DOCNO, T1.CI02FLAG, T1.CI02SENT1, T9.GL27GRACE1\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )\r\n" + ",T6.GL14IPADD\r\n" + ",T8.GL05BRNC AS Branch\r\n" + ",T7.CT03LOCA AS Location\r\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C' AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "' AND T9.GL27GRACE1>0\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )>=T9.GL27GRACE1 AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )<=T9.GL27GRACE2\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery1 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( '" + sqlCurrentDate + "' , T1.CI02DDATE ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlCurrentDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C' AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "'\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' ) >=1 AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' ) <=7\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery2 = "SELECT T1.CI02DOCNO, T1.CI02FLAG, T1.CI02SENT2, T9.GL27GRACE2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )\r\n" + ",T6.GL14IPADD\r\n" + ",T8.GL05BRNC AS Branch\r\n" + ",T7.CT03LOCA AS Location\r\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C' AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "' AND T9.GL27GRACE2>0\r\n" + "AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )>=T9.GL27GRACE2 AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "'  )<=T9.GL27GRACE3\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery2 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( '" + sqlCurrentDate + "' , T1.CI02DDATE ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlCurrentDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C' AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "' AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )>=1 AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )<=45\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery3 = "SELECT T1.CI02DOCNO, T1.CI02FLAG, T1.CI02SENT3, T9.GL27GRACE3\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "' )\r\n" + ",T6.GL14IPADD\r\n" + ",T8.GL05BRNC AS Branch\r\n" + ",T7.CT03LOCA AS Location\r\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C'AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "' AND T9.GL27GRACE3>0\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlCurrentDate + "'  )>=T9.GL27GRACE3\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery3 = "SELECT T1.CI02DOCNO, T1.CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS patronIDName\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( '" + sqlCurrentDate + "' , T1.CI02DDATE ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlCurrentDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\r\n" + ",T8.GL05BRNC AS Branch\r\n" + ",T7.CT03LOCA AS Location\r\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CI02FLAG = 'C' AND CI02OFFI='" + officerId + "' AND CI02DDATE <='" + sqlCurrentDate + "'AND (CI02SENT3 IS NULL)\r\n" + "ORDER BY CI02PATR, CI02DOCNO\r\n";
        if (reminder.equals("first")) {
            System.out.println("first reminder");
            CalculateFine calcFine = new CalculateFine();
            try {
                try {
                    conn = DBConnection.getConnection();
                    if (DBConnection.getDBType().equals("mssql")) {
                        stmt = conn.prepareStatement(mssqlQuery1);
                        System.out.println(mssqlQuery1);
                    } else if (DBConnection.getDBType().equals("mysql")) {
                        stmt = conn.prepareStatement(mysqlQuery1);
                        System.out.println(mysqlQuery1);
                    }
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        i = 1;
                        while (i <= columnCount) {
                            ++i;
                        }
                        OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "1st Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
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
        } else if (reminder.equals("second")) {
            System.out.println("second reminder");
            CalculateFine calcFine = new CalculateFine();
            try {
                try {
                    conn = DBConnection.getConnection();
                    if (DBConnection.getDBType().equals("mssql")) {
                        stmt = conn.prepareStatement(mssqlQuery2);
                        System.out.println(mssqlQuery2);
                    } else if (DBConnection.getDBType().equals("mysql")) {
                        stmt = conn.prepareStatement(mysqlQuery2);
                        System.out.println(mysqlQuery2);
                    }
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        i = 1;
                        while (i <= columnCount) {
                            ++i;
                        }
                        OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "2nd Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
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
        } else if (reminder.equals("third")) {
            System.out.println("third reminder");
            CalculateFine calcFine = new CalculateFine();
            int count = 0;
            try {
                try {
                    conn = DBConnection.getConnection();
                    if (DBConnection.getDBType().equals("mssql")) {
                        System.out.println(mssqlQuery3);
                        stmt = conn.prepareStatement(mssqlQuery3);
                    } else if (DBConnection.getDBType().equals("mysql")) {
                        System.out.println(mysqlQuery3);
                        stmt = conn.prepareStatement(mysqlQuery3);
                    }
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        i = 1;
                        while (i <= columnCount) {
                            ++i;
                        }
                        System.out.println(++count);
                        OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "3rd Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
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
        return OverdueResult;
    }
}
