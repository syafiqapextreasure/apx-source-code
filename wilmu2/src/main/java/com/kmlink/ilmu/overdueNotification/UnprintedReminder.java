/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.kmlink.ilmu.overdueNotification;

import com.kmlink.ilmu.overdueNotification.CalculateFine;
import com.kmlink.ilmu.overdueNotification.OverdueNotification;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

public class UnprintedReminder {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    private String msPatronCategory;
    private String msRetDueDate;
    private String msRetSMD;
    private String msRetItemBranch;
    private String msRetItemCat;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<OverdueNotification> getUnprintedReminder(String unprintedDate, String reminder) throws ParseException {
        System.out.println(unprintedDate);
        DBConnection db = new DBConnection();
        String sqlUnprintedDate = String.valueOf(unprintedDate.substring(6, 10)) + unprintedDate.substring(3, 5) + unprintedDate.substring(0, 2);
        String mysqlUnprintedDate = String.valueOf(unprintedDate.substring(6, 10)) + "-" + unprintedDate.substring(3, 5) + "-" + unprintedDate.substring(0, 2);
        System.out.println(sqlUnprintedDate);
        String mysqlQuery1 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\nAND DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) <='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery2 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\nAND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY)  <='" + mysqlUnprintedDate + "'\r\n" + "AND DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY)  >='" + mysqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mysqlQuery3 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY) AS addDateG1\r\n,DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE2 DAY) AS G2plusG1\r\n,DATE_ADD((DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY), INTERVAL GL27GRACE3 DAY) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND DATE_ADD(DATE_ADD(CI02DDATE, INTERVAL GL27GRACE2 DAY)+ INTERVAL GL27GRACE1 DAY, INTERVAL GL27GRACE3 DAY) <='" + mysqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery1 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE\r\n,DATEADD(DAY, GL27GRACE1, CI02DDATE) AS addDateG1\r\n,DATEADD(DAY, GL27GRACE2, CI02DDATE) AS addDateG2\r\n,DATEADD(DAY, GL27GRACE3, CI02DDATE) AS addDateG3\r\n, T1.CI02DOCNO\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND DATEADD(DAY, GL27GRACE3, CI02DDATE) >='" + sqlUnprintedDate + "'\r\n" + "AND DATEADD(DAY, GL27GRACE2, CI02DDATE) >='" + sqlUnprintedDate + "'\r\n" + "AND DATEADD(DAY, GL27GRACE1, CI02DDATE) <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery2 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATEADD(DAY, GL27GRACE1, CI02DDATE) AS addDateG1\r\n,DATEADD(DAY, GL27GRACE2, CI02DDATE) AS addDateG2\r\n,DATEADD(DAY, GL27GRACE3, CI02DDATE) AS addDateG3\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND DATEADD(DAY, GL27GRACE3, DATEADD(DAY, GL27GRACE2, CI02DDATE)) >='" + sqlUnprintedDate + "'\r\n" + "AND DATEADD(DAY, GL27GRACE2, DATEADD(DAY, GL27GRACE1, CI02DDATE)) <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String mssqlQuery3 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n,DATEADD(DAY, GL27GRACE1, CI02DDATE) AS addDateG1\r\n,DATEADD(DAY, GL27GRACE2, DATEADD(DAY, GL27GRACE1, CI02DDATE)) AS G2plusG1\r\n,DATEADD(DAY, GL27GRACE3, DATEADD(DAY, GL27GRACE2, CI02DDATE)+GL27GRACE1) AS G3plusG2\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND DATEADD(DAY, GL27GRACE3, DATEADD(DAY, GL27GRACE2, CI02DDATE)+GL27GRACE1) <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String oracleQuery1 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1, 'YYYYMMDD') AS addDateG1\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') AS addDateG2\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE3+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') AS addDateG3\r\n,T1.CI02PATR || ', ' ||T6.GL14NAME AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C' AND CI02DDATE <= '" + sqlUnprintedDate + "'  AND (CI02SENT1 IS NULL OR CI02SENT1 = '')\r\n" + "AND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE3+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') >='" + sqlUnprintedDate + "'\r\n" + "AND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') >='" + sqlUnprintedDate + "'\r\n" + "AND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1, 'YYYYMMDD') <='" + sqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String oracleQuery2 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1, 'YYYYMMDD') AS addDateG1\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') AS addDateG2\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE3+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') AS addDateG3\r\n,T1.CI02PATR || ', ' ||T6.GL14NAME AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C' AND CI02DDATE <= '" + sqlUnprintedDate + "'  AND (CI02SENT2 IS NULL OR CI02SENT2 = '')\r\n" + "AND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE3+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') >='" + sqlUnprintedDate + "'\r\n" + "AND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE2+GL27GRACE1, 'YYYYMMDD') <='" + sqlUnprintedDate + "'\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        String oracleQuery3 = "SELECT T9.GL27GRACE1, T9.GL27GRACE2, T9.GL27GRACE3, T1.CI02DDATE, T1.CI02DOCNO\r\n, to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1, 'DD-Mon-YY') AS addDateG1\r\n,to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1+GL27GRACE2, 'DD-Mon-YY') AS addDateG2\r\n,to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1+GL27GRACE2+GL27GRACE3, 'DD-Mon-YY') AS addDateG3\r\n,T1.CI02PATR || ', ' ||T6.GL14NAME AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nINNER JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nINNER JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND to_char (to_date(CI02DDATE,'YYYYMMDD')+GL27GRACE1+GL27GRACE2+GL27GRACE3, 'YYYYMMDD')  <='" + sqlUnprintedDate + "'\r\n" + "AND (CI02SENT3 IS NULL OR CI02SENT3 = '')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
        ArrayList<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        System.out.println(unprintedDate);
        System.out.println(reminder);
        String CT03DOCNO = "";
        String GL14PATR = "";
        String loanType = "";
        if (reminder.equals("first")) {
            System.out.println("first reminder");
            CalculateFine calcFine = new CalculateFine();
            try {
                try {
                    conn = DBConnection.getConnection();
                    ResultSet rs = null;
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
                        OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "1st Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
                    }
                    return OverdueResult;
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn == null) return OverdueResult;
                        conn.close();
                        return OverdueResult;
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
                return OverdueResult;
            }
            finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (reminder.equals("second")) {
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
                        OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "2nd Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
                    }
                    return OverdueResult;
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn == null) return OverdueResult;
                        conn.close();
                        return OverdueResult;
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
                return OverdueResult;
            }
            finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!reminder.equals("third")) return OverdueResult;
        System.out.println("third reminder");
        CalculateFine calcFine = new CalculateFine();
        try {
            try {
                conn = DBConnection.getConnection();
                if (DBConnection.getDBType().equals("mssql")) {
                    System.out.println(mssqlQuery3);
                    stmt = conn.prepareStatement(mssqlQuery3);
                } else if (DBConnection.getDBType().equals("mysql")) {
                    System.out.println(mysqlQuery3);
                    stmt = conn.prepareStatement(mysqlQuery3);
                } else if (DBConnection.getDBType().equals("oracle")) {
                    System.out.println(oracleQuery3);
                    stmt = conn.prepareStatement(oracleQuery3);
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
                    OverdueResult.add(new OverdueNotification(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "3rd Reminder", calcFine.calculateDays(rs.getString("CI02DDATE"), rs.getString("Branch"), conn), calcFine.calculatefines2(rs.getString("CI02PATR"), rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("CI02DDATE"), rs.getString("Branch"), conn), rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
                }
                return OverdueResult;
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn == null) return OverdueResult;
                    conn.close();
                    return OverdueResult;
                }
                catch (SQLException e4) {
                    e4.printStackTrace();
                }
            }
            return OverdueResult;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean checkEgilibilty(String gracePeriod, String msDueDate, String msBranch) {
        boolean elig = false;
        try {
            String dueDate = DateTime.formatDatelocal(msDueDate);
            LocalDate localDueDate = new LocalDate((Object)dueDate);
            LocalDate localCurrDate = LocalDate.now();
            int newGracePeriod = Integer.parseInt(gracePeriod);
            System.out.println("grace" + gracePeriod);
            System.out.println("dueDate" + localDueDate.plusDays(newGracePeriod));
            elig = localDueDate.plusDays(newGracePeriod).isBefore((ReadablePartial)localCurrDate);
        }
        catch (NumberFormatException dueDate) {
            // empty catch block
        }
        String currDate = DateTime.getTodayDate();
        int holidaycount = 0;
        String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDueDate + "'AND '" + currDate + "' ";
        try {
            Statement stmt = null;
            ResultSet rsObj = null;
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return elig;
    }
}
