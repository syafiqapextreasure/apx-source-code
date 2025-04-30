/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.overdueNotification;

import com.kmlink.ilmu.overdueNotification.OverdueNotification;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OverdueReminder {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static ResultSet rs = null;
    private String unprintedDate = null;
    private String reprintDate = null;
    private String inputPatronId = null;
    private String patronCategoryId = null;
    private String branchId = null;
    private String reminder = null;
    private String address = null;
    private String officerId = null;
    private String patronStatusId = null;
    private String accessionNo = null;
    private String patron = null;
    private String title = null;
    private String callNo = null;
    private String dueDate = null;
    private String reminderType = null;
    private String lateBy = null;
    private String fines = null;
    private String email = null;
    private String branch = null;
    private String location = null;
    private int countLateBy;
    private BigDecimal countFine;

    public String getUnprintNotice() {
        return this.unprintedDate;
    }

    public void setUnprintNotice(String unprintedDate) {
        this.unprintedDate = unprintedDate;
    }

    public String getReprintNotice() {
        return this.reprintDate;
    }

    public void setReprintNotice(String reprintDate) {
        this.reprintDate = reprintDate;
    }

    public String getPatronId() {
        return this.inputPatronId;
    }

    public void setPatronId(String inputPatronId) {
        this.inputPatronId = inputPatronId;
    }

    public String getFilterByPatronCategory() {
        return this.patronCategoryId;
    }

    public void setFilterByPatronCategory(String patronCategoryId) {
        this.patronCategoryId = patronCategoryId;
    }

    public String getFilterByBranch() {
        return this.branchId;
    }

    public void setFilterByBranch(String branchId) {
        this.branchId = branchId;
    }

    public String getReminder() {
        return this.reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficerId() {
        return this.officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getPatronID() {
        return this.patronStatusId;
    }

    public void setPatronID(String patronStatusId) {
        this.patronStatusId = patronStatusId;
    }

    public String getAccessionNo() {
        return this.accessionNo;
    }

    public void setAccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    public String getPatron() {
        return this.patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallNo() {
        return this.callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReminderType() {
        return this.reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public String getLateBy() {
        return this.lateBy;
    }

    public void setLateBy(String lateBy) {
        this.lateBy = lateBy;
    }

    public String getFines() {
        return this.fines;
    }

    public void setFines(String fines) {
        this.fines = fines;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCountLateBy() {
        return this.countLateBy;
    }

    public void setCountLateBy(int countLateBy) {
        this.countLateBy = countLateBy;
    }

    public BigDecimal getCountFine() {
        return this.countFine;
    }

    public void setCountFine(BigDecimal countFine) {
        this.countFine = countFine;
    }

    public OverdueReminder() {
    }

    public OverdueReminder(String accessionNo, String patron, String title, String callNo, String dueDate, String reminderType, String email, String branch, String location) {
        this.accessionNo = accessionNo;
        this.patron = patron;
        this.title = title;
        this.callNo = callNo;
        this.dueDate = dueDate;
        this.reminderType = reminderType;
        this.email = email;
        this.branch = branch;
        this.location = location;
    }

    public static List<OverdueReminder> getTableList(String startDate, String endDate, String address, List<String> patronCateList, List<String> branchList, List<String> locationList) throws ParseException {
        String mssqlQuery = null;
        String oracleQuery = null;
        String mysqlQuery = null;
        String convertStartDate = String.valueOf(startDate.substring(6, 10)) + startDate.substring(3, 5) + startDate.substring(0, 2);
        String convertEndDate = String.valueOf(endDate.substring(6, 10)) + endDate.substring(3, 5) + endDate.substring(0, 2);
        ArrayList<OverdueReminder> OverdueResult = new ArrayList<OverdueReminder>();
        System.out.println(String.valueOf(convertStartDate) + convertEndDate);
        String oracleSqlStartDate = "select to_char (to_date('" + startDate + "','DD/MM/YYYY'), 'DD-Mon-YY'),  to_char (to_date('" + endDate + "','DD/MM/YYYY'), 'DD-Mon-YY') from dual";
        System.out.println("oracleSqlStartDate" + oracleSqlStartDate);
        DBConnection db = new DBConnection();
        String oracleStartDateFormat = null;
        String oracleEndDateFormat = null;
        if (DBConnection.getDBType().equals("oracle")) {
            conn = DBConnection.getConnection();
            try {
                stmt = conn.prepareStatement(oracleSqlStartDate);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    oracleStartDateFormat = rs.getString(1);
                    oracleEndDateFormat = rs.getString(2);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        String mssqlQueryBase = "SELECT T1.CI02DOCNO, T1.CI02FLAG, T1.CI02SENT3, T9.GL27GRACE3\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T1.CI02DDATE\r\n,DATEDIFF ( DAY , T1.CI02DDATE , '20210411' )\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND CI02DDATE BETWEEN '" + convertStartDate + "' AND '" + convertEndDate + "'\r\n" + "AND GL27CATE IN ('" + String.join((CharSequence)"','", patronCateList) + "')";
        String oracleQueryBase = "SELECT T1.CI02DOCNO, T1.CI02DDATE, T1.CI02FLAG, T1.CI02SENT3, T9.GL27GRACE3\r\n,T1.CI02PATR || ', ' ||T6.GL14NAME AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND CI02DDATE BETWEEN '" + convertStartDate + "' AND '" + convertEndDate + "'\r\n" + "AND GL27CATE IN ('" + String.join((CharSequence)"','", patronCateList) + "')";
        String mysqlQueryBase = "SELECT T1.CI02DOCNO, T1.CI02DDATE, T1.CI02FLAG, T1.CI02SENT3, T9.GL27GRACE3\r\n,CONCAT(T1.CI02PATR ,', ', T6.GL14NAME)AS CI02PATR\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\n,REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' ') AS CallNo\r\n,T6.GL14IPADD\r\n,T8.GL05BRNC AS Branch\r\n,T7.CT03LOCA AS Location\r\n,T9.GL27CATE, T9.GL27ICAT, T9.GL27SMD\r\nFROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02FLAG = 'C'\r\nAND CI02DDATE BETWEEN '" + convertStartDate + "' AND '" + convertEndDate + "'\r\n" + "AND GL27CATE IN ('" + String.join((CharSequence)"','", patronCateList) + "')";
        try {
            try {
                conn = DBConnection.getConnection();
                if (DBConnection.getDBType().equals("mssql")) {
                    if (!branchList.isEmpty() && !locationList.isEmpty()) {
                        mssqlQuery = String.valueOf(mssqlQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mssqlQuery);
                    } else if (!branchList.isEmpty() && locationList.isEmpty()) {
                        mssqlQuery = String.valueOf(mssqlQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mssqlQuery);
                    } else if (branchList.isEmpty() && !locationList.isEmpty()) {
                        mssqlQuery = String.valueOf(mssqlQueryBase) + " AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mssqlQuery);
                    }
                } else if (DBConnection.getDBType().equals("mysql")) {
                    if (!branchList.isEmpty() && !locationList.isEmpty()) {
                        mysqlQuery = String.valueOf(mysqlQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mysqlQuery);
                    } else if (!branchList.isEmpty() && locationList.isEmpty()) {
                        mysqlQuery = String.valueOf(mysqlQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mysqlQuery);
                    } else if (branchList.isEmpty() && !locationList.isEmpty()) {
                        mysqlQuery = String.valueOf(mysqlQueryBase) + " AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(mysqlQuery);
                    }
                } else if (DBConnection.getDBType().equals("oracle")) {
                    if (!branchList.isEmpty() && !locationList.isEmpty()) {
                        oracleQuery = String.valueOf(oracleQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(oracleQuery);
                    } else if (!branchList.isEmpty() && locationList.isEmpty()) {
                        oracleQuery = String.valueOf(oracleQueryBase) + " AND GL05BRNC IN ('" + String.join((CharSequence)"','", branchList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(oracleQuery);
                    } else if (branchList.isEmpty() && !locationList.isEmpty()) {
                        oracleQuery = String.valueOf(oracleQueryBase) + " AND T7.CT03LOCA IN ('" + String.join((CharSequence)"','", locationList) + "')\r\n" + "ORDER BY CI02PATR, CI02DDATE";
                        stmt = conn.prepareStatement(oracleQuery);
                    }
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
                    OverdueResult.add(new OverdueReminder(rs.getString("CI02DOCNO"), rs.getString("CI02PATR"), rs.getString("CT05SRAW"), rs.getString("CallNo"), OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI02DDATE")), "3rd Reminder", rs.getString("GL14IPADD"), rs.getString("Branch"), rs.getString("Location")));
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
        return OverdueResult;
    }
}
