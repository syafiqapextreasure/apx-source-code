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

public class UnprintedReminderPatronCateBranchId {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    private static OverdueNotification overdueNotify = null;

    public static List<OverdueNotification> getUnprintedReminderPatronCateBranchId(String unprintedDate, String reminder, String patronCategoryId, String branchId) throws ParseException {
        String sqlUnprintedDate = String.valueOf(unprintedDate.substring(6, 10)) + unprintedDate.substring(3, 5) + unprintedDate.substring(0, 2);
        DBConnection db = new DBConnection();
        ArrayList<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        String mysqlUnprintedDate = String.valueOf(unprintedDate.substring(6, 10)) + "-" + unprintedDate.substring(3, 5) + "-" + unprintedDate.substring(0, 2);
        System.out.println(sqlUnprintedDate);
        String mysqlQuery1 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) <='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery2 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY)  <='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery3 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY, INTERVAL GL27GRACE3 DAY) <='" + mysqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery1 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) <=7\r\n" + "ORDER BY CI02PATR\r\n";
        String mssqlQuery2 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) <=45\r\n" + "ORDER BY CI02DDATE\r\n";
        String mssqlQuery3 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02DDATE\r\n";
        String oracleQuery1 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) <=7\r\n" + "ORDER BY CI02PATR\r\n";
        String oracleQuery2 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) <=45\r\n" + "ORDER BY CI02DDATE\r\n";
        String oracleQuery3 = "SELECT T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '" + sqlUnprintedDate + "' ) - (SELECT COUNT(*) FROM GLHOLIDAY WHERE T8.GL05BRNC=GL30BRNC AND GL30DATE BETWEEN T1.CI02DDATE AND '" + sqlUnprintedDate + "') AS ACTUALDAY\r\n" + ",T6.GL14IPADD\n" + ",T8.GL05BRNC AS Branch\n" + ",T7.CT03LOCA AS Location\n" + ",T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\n" + "FROM CICIRC T1\r\n" + "LEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\n" + "LEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\n" + "LEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\n" + "LEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\n" + "LEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\n" + "LEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\n" + "LEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\n" + "LEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\n" + "WHERE CT03LOCA IN (SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC='" + branchId + "')\r\n" + "AND GL14CATE='" + patronCategoryId + "'\r\n" + "AND CI02FLAG = 'C'\r\n" + "AND CI02DDATE <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02DDATE\r\n";
        if (reminder.equals("first")) {
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
                    } else if (DBConnection.getDBType().equals("oracle")) {
                        stmt = conn.prepareStatement(oracleQuery1);
                        System.out.println(oracleQuery1);
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
                        String row = "";
                        int i2 = 1;
                        while (i2 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i2) + ", ";
                            ++i2;
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
                    } else if (DBConnection.getDBType().equals("oracle")) {
                        stmt = conn.prepareStatement(oracleQuery2);
                        System.out.println(oracleQuery2);
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
                        String row = "";
                        int i3 = 1;
                        while (i3 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i3) + ", ";
                            ++i3;
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
            CalculateFine calcFine = new CalculateFine();
            try {
                try {
                    conn = DBConnection.getConnection();
                    if (DBConnection.getDBType().equals("mssql")) {
                        stmt = conn.prepareStatement(mssqlQuery3);
                        System.out.println(mssqlQuery3);
                    } else if (DBConnection.getDBType().equals("mysql")) {
                        stmt = conn.prepareStatement(mysqlQuery3);
                        System.out.println(mysqlQuery3);
                    } else if (DBConnection.getDBType().equals("oracle")) {
                        stmt = conn.prepareStatement(oracleQuery3);
                        System.out.println(oracleQuery3);
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
                        String row = "";
                        int i4 = 1;
                        while (i4 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i4) + ", ";
                            ++i4;
                        }
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
