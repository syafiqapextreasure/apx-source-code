/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.mail.Address
 *  javax.mail.Authenticator
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.MessagingException
 *  javax.mail.PasswordAuthentication
 *  javax.mail.SendFailedException
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.AddressException
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.MimeMessage
 */
package com.kmlink.ilmu.overdueNotification;

import com.kmlink.ilmu.mailBrowser.email.MailBrowser;
import com.kmlink.ilmu.mailBrowser.email.SMTP;
import com.kmlink.ilmu.overdueNotification.ReminderByOfficerId;
import com.kmlink.ilmu.overdueNotification.ReminderByPatronId;
import com.kmlink.ilmu.overdueNotification.ReprintReminder;
import com.kmlink.ilmu.overdueNotification.UnprintedReminder;
import com.kmlink.ilmu.overdueNotification.UnprintedReminderBranchId;
import com.kmlink.ilmu.overdueNotification.UnprintedReminderPatronCate;
import com.kmlink.ilmu.overdueNotification.UnprintedReminderPatronCateBranchId;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OverdueNotification {
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

    public OverdueNotification() {
    }

    public OverdueNotification(String unprintedDate, String reprintDate, String inputPatronId, String patronCategoryId, String branchId, String reminder, String address, String officerId, String patronStatus) {
        this.unprintedDate = unprintedDate;
        this.reprintDate = reprintDate;
        this.inputPatronId = inputPatronId;
        this.patronCategoryId = patronCategoryId;
        this.branchId = branchId;
        this.reminder = reminder;
        this.address = address;
        this.officerId = officerId;
    }

    public OverdueNotification(String reprintDate) {
        this.reprintDate = reprintDate;
    }

    public OverdueNotification(String accessionNo, String title) {
        this.accessionNo = accessionNo;
        this.title = title;
    }

    public OverdueNotification(String accessionNo, String patron, String title, String callNo, String dueDate, String reminderType, String lateBy, String fines, String email, String branch, String location) {
        this.accessionNo = accessionNo;
        this.patron = patron;
        this.title = title;
        this.callNo = callNo;
        this.dueDate = dueDate;
        this.reminderType = reminderType;
        this.lateBy = lateBy;
        this.fines = fines;
        this.email = email;
        this.branch = branch;
        this.location = location;
    }

    public OverdueNotification(String accessionNo, String patron, String title, String callNo, String dueDate, String reminderType, int countLateBy, BigDecimal countFine, String email, String branch, String location) {
        this.accessionNo = accessionNo;
        this.patron = patron;
        this.title = title;
        this.callNo = callNo;
        this.dueDate = dueDate;
        this.reminderType = reminderType;
        this.countLateBy = countLateBy;
        this.countFine = countFine;
        this.email = email;
        this.branch = branch;
        this.location = location;
    }

    public OverdueNotification(String accessionNo, String patron, String title, String callNo, String dueDate, String reminderType, String lateBy, BigDecimal countFine, String email, String branch, String location) {
        this.accessionNo = accessionNo;
        this.patron = patron;
        this.title = title;
        this.callNo = callNo;
        this.dueDate = dueDate;
        this.reminderType = reminderType;
        this.lateBy = lateBy;
        this.countFine = countFine;
        this.email = email;
        this.branch = branch;
        this.location = location;
    }

    public static List<String> getFileterPatronCategoryId() {
        ArrayList<String> patron = new ArrayList<String>();
        String query = "SELECT GL07CATE FROM GLCATE ";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patron.add(rs.getString("GL07CATE"));
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
        return patron;
    }

    public static List<String> getFileterPatronCategoryDesc() {
        ArrayList<String> patron = new ArrayList<String>();
        String query = "SELECT GL07DESC FROM GLCATE ";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patron.add(rs.getString("GL07DESC"));
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
        return patron;
    }

    public static List<String> getFilterBranchId() {
        ArrayList<String> branch = new ArrayList<String>();
        String query = "SELECT GL71BRNC FROM GLBRNC ";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branch.add(rs.getString("GL71BRNC"));
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

    public static List<String> getFilterBranchDesc() {
        ArrayList<String> branch = new ArrayList<String>();
        String query = "SELECT GL71DESC FROM GLBRNC ";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branch.add(rs.getString("GL71DESC"));
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

    public static List<String> getPatronStatusId() {
        ArrayList<String> patronStatus = new ArrayList<String>();
        String query = "SELECT GL08STAT, GL08DESC FROM GLSTAT";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patronStatus.add(rs.getString("GL08STAT"));
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
        return patronStatus;
    }

    public static List<String> getPatronStatusDesc() {
        ArrayList<String> patronStatus = new ArrayList<String>();
        String query = "SELECT GL08DESC FROM GLSTAT";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patronStatus.add(rs.getString("GL08DESC"));
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
        return patronStatus;
    }

    public static List<OverdueNotification> getTableList(String retrieveType, String unprintedDate, String reprintDate, String inputPatronId, String patronCategoryId, String branchId, String reminder, String address, String officerId, String patronStatusId) throws ParseException {
        LocalDate localDate;
        List<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String convertedDate = dtf.format(localDate = LocalDate.now());
        if (unprintedDate.equals(convertedDate) && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && patronCategoryId.equals("") && branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = UnprintedReminder.getUnprintedReminder(unprintedDate, reminder);
        } else if (unprintedDate.equals(convertedDate) && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && !patronCategoryId.equals("") && branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = UnprintedReminderPatronCate.getUnprintedReminderPatronCate(unprintedDate, reminder, patronCategoryId);
        } else if (unprintedDate.equals(convertedDate) && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && patronCategoryId.equals("") && !branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = UnprintedReminderBranchId.getUnprintedReminderBranchId(unprintedDate, reminder, branchId);
        } else if (unprintedDate.equals(convertedDate) && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && !patronCategoryId.equals("") && !branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = UnprintedReminderPatronCateBranchId.getUnprintedReminderPatronCateBranchId(unprintedDate, reminder, patronCategoryId, branchId);
        } else if (retrieveType.equals("reprint") && unprintedDate.equals("") && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && patronCategoryId.equals("") && branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = ReprintReminder.getReprintReminder(reprintDate, reminder);
        } else if (retrieveType.equals("patron") && unprintedDate.equals("") && reprintDate.equals("") && !inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && patronCategoryId.equals("") && branchId.equals("") && officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = ReminderByPatronId.getReminderByPatronId(reminder, inputPatronId, convertedDate);
        } else if (!retrieveType.equals("") && !unprintedDate.equals("") && reprintDate.equals("") && inputPatronId.equals("") && !reminder.equals("") && address.equals("department") && patronCategoryId.equals("") && branchId.equals("") && !officerId.equals("") && patronStatusId.equals("")) {
            OverdueResult = ReminderByOfficerId.getReminderByOfficerId(officerId, reminder, convertedDate);
        }
        return OverdueResult;
    }

    public static List<OverdueNotification> getTableListWithOfficeId(String officerId) {
        ArrayList<OverdueNotification> overdueResult = new ArrayList<OverdueNotification>();
        String query = "SELECT * FROM GLMAIL WHERE GL34SENDER=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, officerId);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                }
                System.out.println("what is inside listOfSenderMailBrowser: " + overdueResult);
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
        return overdueResult;
    }

    public static List<OverdueNotification> getReprintDate() throws ParseException {
        ArrayList<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        String query = "SELECT DISTINCT CI10NOTDATE, CI02DOCNO\r\nFROM CINOTC T1\r\nLEFT JOIN CICIRC T2 ON (T1.CI10DOCNO = T2.CI02DOCNO AND T1.CI10PATRON=T2.CI02PATR)\r\nLEFT JOIN CTPONT T3 ON (T3.CT06MATNO = T2.CI02MATNO)\r\nLEFT JOIN CTTITL T4 ON (T4.CT05POINTER = T3.CT06POINTER) WHERE CI02FLAG='C'\r\nAND T1.CI10TYPE = 'O3' AND (T2.CI02SENT3 > '' OR T2.CI02SENT3 IS NOT NULL)\r\nAND CT05SRAW IS NOT NULL ORDER BY CI10NOTDATE";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                System.out.println(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String appendDate = rs.getString("CI10NOTDATE");
                    StringBuilder sb = new StringBuilder(appendDate);
                    sb.insert(6, "/");
                    sb.insert(4, "/");
                    System.out.println(sb);
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = i2 == 1 ? String.valueOf(row) + sb + ", " : String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    OverdueResult.add(new OverdueNotification(OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI10NOTDATE"))));
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

    public static List<String> getListReprintDate() throws ParseException {
        ArrayList<String> listReprintDate = new ArrayList<String>();
        String query = "SELECT DISTINCT CI10NOTDATE\r\nFROM CINOTC T1\r\nLEFT JOIN CICIRC T2 ON (T1.CI10DOCNO = T2.CI02DOCNO AND T1.CI10PATRON=T2.CI02PATR)\r\nLEFT JOIN CTPONT T3 ON (T3.CT06MATNO = T2.CI02MATNO)\r\nLEFT JOIN CTTITL T4 ON (T4.CT05POINTER = T3.CT06POINTER) WHERE CI02FLAG='C'\r\nAND T1.CI10TYPE = 'O3' AND (T2.CI02SENT3 > '' OR T2.CI02SENT3 IS NOT NULL)\r\nAND CT05SRAW IS NOT NULL ORDER BY CI10NOTDATE";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                System.out.println(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    listReprintDate.add(OverdueNotification.swapYearAndDayInDateFormat(rs.getString("CI10NOTDATE")));
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
        return listReprintDate;
    }

    public static List<OverdueNotification> getReprintDescription(String getSelectedDate) throws ParseException {
        String convertedSqlDateFormat = OverdueNotification.convertMssqlDateFormat(getSelectedDate);
        ArrayList<OverdueNotification> OverdueResult = new ArrayList<OverdueNotification>();
        String query = "SELECT DISTINCT CI10NOTDATE, CI10DOCNO, CI02DOCNO\r\n,REPLACE(REPLACE(REPLACE(REPLACE(T4.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS CT05SRAW\r\nFROM CINOTC T1\r\nLEFT JOIN CICIRC T2 ON (T1.CI10DOCNO = T2.CI02DOCNO AND T1.CI10PATRON=T2.CI02PATR)\r\nLEFT JOIN CTPONT T3 ON (T3.CT06MATNO = T2.CI02MATNO)\r\nLEFT JOIN CTTITL T4 ON (T4.CT05POINTER = T3.CT06POINTER)\r\nWHERE CI02FLAG='C'\r\nAND CI10NOTDATE=?\r\nAND T1.CI10TYPE = 'O3'\r\nAND (T2.CI02SENT3 > '' OR T2.CI02SENT3 IS NOT NULL)\r\nAND CT05SRAW IS NOT NULL\r\n";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setDate(1, new Date(new SimpleDateFormat("yyyyMMdd").parse(convertedSqlDateFormat).getTime()));
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                System.out.println();
                while (rs.next()) {
                    String row = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    OverdueResult.add(new OverdueNotification(rs.getString("CI10DOCNO"), rs.getString("CT05SRAW")));
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
            catch (ParseException e) {
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
        return OverdueResult;
    }

    public static void updateMailBrowser(String accessionNo, String patronId) throws ParseException {
        String filteredPatronId = patronId.substring(0, patronId.indexOf(44));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        String sqldate = dtf.format(localDate);
        String insertQuery = "INSERT INTO GLMAIL (GL34SENDER,GL34SDATE,GL34STIME,GL34MESSAGE,GL34RECEIVER,GL34RDATE,GL34RTIME,GL34CCID,GL34SUBJECT,GL34REPLY,GL34STATUS,GL34MAILNO,GL34EMAIL,GL34RTYPE,GL34LETTERID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String updateQuery = "UPDATE CICIRC SET CI02SENT3=? WHERE CI02DOCNO=? AND CI02PATR=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(updateQuery);
                stmt.setString(1, sqldate);
                stmt.setString(2, accessionNo);
                stmt.setString(3, filteredPatronId);
                int row = stmt.executeUpdate();
                PreparedStatement stmt1 = null;
                stmt1 = conn.prepareStatement(insertQuery);
                stmt1.setString(1, "");
                stmt1.setString(2, sqldate);
                stmt1.setString(3, "");
                stmt1.setString(4, "");
                stmt1.setString(5, filteredPatronId);
                stmt1.setString(6, "");
                stmt1.setString(7, "");
                stmt1.setString(8, "");
                stmt1.setString(9, "");
                stmt1.setString(10, "");
                stmt1.setString(11, "");
                stmt1.setString(12, "");
                stmt1.setString(13, "");
                stmt1.setString(14, "");
                stmt1.setString(15, "");
                int n = stmt1.executeUpdate();
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

    public static String filterVBnHTMLMessage(String msg) {
        String filteredMsg = null;
        filteredMsg = msg.contains("BEGIN VARIABLE") ? msg : msg.replaceAll("\\{.*?\\}", "");
        return filteredMsg;
    }

    public static String filterHTMLMessage(String msg) {
        String filtereHTMLdMsg = null;
        filtereHTMLdMsg = msg.replaceAll("\\{.*?\\}", "").replaceAll("\\<.*?\\>", "").replaceAll("\\/.*?\\.table", "");
        return filtereHTMLdMsg;
    }

    public static String SaveHTMLMessageToTxtFile(String msg) {
        String filteredMsg = null;
        filteredMsg = msg.contains("BEGIN VARIABLE") ? msg.replaceAll("\\{.*?\\}", "").replaceAll("\\<.*?\\>", "").replaceAll("\\/.*?\\.table", "") : msg.replaceAll("\\{.*?\\}", "");
        return filteredMsg;
    }

    public static String displayModalMessage(String msg) {
        String filteredMsg = null;
        if (msg.contains("BEGIN VARIABLE")) {
            filteredMsg = msg;
            filteredMsg = filteredMsg.replaceAll("<link(.*?)\">", "");
            filteredMsg = filteredMsg.replaceAll("<style>(.*?)<\\/style>", "");
            filteredMsg = String.valueOf(filteredMsg) + "<style>\r\n" + "\r\n" + "#htmlContent .ta-right {\r\n" + "\ttext-align: right;\r\n" + "\tfloat: right;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .ta-center {\r\n" + "\tmargin-left: auto;\r\n" + "\tmargin-right: auto;\r\n" + "\ttext-align: center;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .padd-right-5 {\r\n" + "\tpadding-right: 5px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .margin-top-15 {\r\n" + "\tmargin-top: 15px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .margin-btm-15 {\r\n" + "\tmargin-bottom: 15px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .ta-left {\r\n" + "\tfloat: left;\r\n" + "\tdisplay: inline-block;\r\n" + "\tposition: absolute;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .float-right {\r\n" + "\tfloat: right;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .clear {\r\n" + "\tclear: both;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .table {\r\n" + "\tdisplay: inline-block;\r\n" + "\tposition: absolute;\r\n" + "\ttop: auto;\r\n" + "\twhite-space: pre-wrap;\r\n" + "\toverflow: hidden;\r\n" + "\tfont-size: 10pt !important;\r\n" + "\tfont-family: \"Times New Roman\";\r\n" + "\tmargin-left: 0.30in;\r\n" + "}\r\n" + "\r\n" + "</style>";
        } else {
            filteredMsg = msg.replaceAll("\\{.*?\\}", "");
        }
        return filteredMsg;
    }

    public static String swapYearAndDayInDateFormat(String inputDate) throws ParseException {
        java.util.Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        String outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        return outputDate;
    }

    public static String convertMssqlDateFormat(String inputDate) throws ParseException {
        java.util.Date inputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(inputDate);
        String outputDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(inputDateFormat);
        return outputDate;
    }

    public static void sendEmail(int mailNo, String receiverEmailAddr) throws AddressException, MessagingException, IOException, URISyntaxException {
        block26: {
            String readSender = SMTP.readSender();
            String readHost = SMTP.readHostName();
            String readPort = SMTP.readPort();
            final String username = SMTP.username();
            final String password = SMTP.password();
            String startTLS = SMTP.startTLS();
            Properties props = System.getProperties();
            props.put("mail.smtp.host", readHost);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance((Properties)props, (Authenticator)new Authenticator(){

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom((Address)new InternetAddress(readSender, "NoReply"));
            message.setReplyTo((Address[])InternetAddress.parse((String)readSender, (boolean)false));
            Connection conn = null;
            MailBrowser mail = new MailBrowser();
            String query = "SELECT GL34MAILNO\r\n,GL34RECEIVER, GL14NAME, T2.GL14IPADD\r\n      ,GL34SUBJECT\r\n      ,GL34MESSAGE\r\nFROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nWHERE GL34MAILNO = ?";
            String sqlUpdateMailStatus = "UPDATE GLMAIL SET GL34STATUS='Y' WHERE GL34MAILNO=?";
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, mailNo);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    if (!rs.next()) break block26;
                    String row = "";
                    int i22 = 1;
                    while (i22 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i22) + ", ";
                        if (i22 != 1) {
                            if (i22 == 2) {
                                message.setSubject(rs.getString(i22));
                            } else if (i22 != 4) {
                                if (i22 == 5) {
                                    message.setSubject(rs.getString(i22));
                                } else if (i22 == 6) {
                                    if (rs.getString(i22).contains("BEGIN VARIABLE")) {
                                        message.setContent((Object)OverdueNotification.filterVBnHTMLMessage(rs.getString(i22)), "text/html; charset=UTF-8");
                                    } else {
                                        message.setText(OverdueNotification.filterVBnHTMLMessage(rs.getString(i22)));
                                    }
                                }
                            }
                        }
                        ++i22;
                    }
                    mail = new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34RECEIVER"), rs.getString("GL14NAME"), rs.getString("GL14IPADD"), rs.getString("GL34SUBJECT"), rs.getString("GL34RECEIVER"));
                    try {
                        message.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse((String)receiverEmailAddr, (boolean)false));
                        Transport.send((Message)message);
                    }
                    catch (SendFailedException i22) {
                    }
                    catch (MessagingException i22) {
                        // empty catch block
                    }
                    try {
                        PreparedStatement stmtUpdate = null;
                        stmtUpdate = conn.prepareStatement(sqlUpdateMailStatus);
                        stmtUpdate.setInt(1, mailNo);
                        int updateRow = stmtUpdate.executeUpdate();
                        System.out.println(updateRow);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
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
        }
    }

    public static void updateSentNotificationCICRC(String patronId, String accessionNo, String notifyType) {
        Connection conn = null;
        Connection conn1 = null;
        Connection conn2 = null;
        Statement pstmt = null;
        Statement pstmt1 = null;
        Statement stmt = null;
        ResultSet rs = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String currTime = localTime.format(formatter);
        String sqlQueryFirstNotify = "UPDATE CICIRC SET CI02SENT1='" + dtf.format(localDate) + "' WHERE CI02PATR='" + patronId + "' AND CI02DOCNO='" + accessionNo + "'";
        String sqlQuerySecondNotify = "UPDATE CICIRC SET CI02SENT2='" + dtf.format(localDate) + "' WHERE CI02PATR='" + patronId + "' AND CI02DOCNO='" + accessionNo + "'";
        String sqlQueryThirdNotify = "UPDATE CICIRC SET CI02SENT3='" + dtf.format(localDate) + "' WHERE CI02PATR='" + patronId + "' AND CI02DOCNO='" + accessionNo + "'";
        String sqlQueryGetCINOTCnum = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='CINOTC'";
        try {
            try {
                conn = DBConnection.getConnection();
                int maxCINOTC = 0;
                if (notifyType == null) {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sqlQueryGetCINOTCnum);
                    while (rs.next()) {
                        maxCINOTC = rs.getInt("GL98VALUE");
                    }
                    System.out.println(maxCINOTC);
                    int incrementNoticNo = maxCINOTC + 1;
                    String sqlQueryReminder = "INSERT INTO CINOTC (CI10COUNTER, CI10NOTDATE, CI10NOTTIME, CI10TYPE, CI10DOCNO, CI10PATRON, CI10OFFICER) VALUES(?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sqlQueryReminder);
                    pstmt.setInt(1, incrementNoticNo);
                    pstmt.setString(2, dtf.format(localDate));
                    pstmt.setString(3, currTime);
                    pstmt.setString(4, "V");
                    pstmt.setString(5, accessionNo);
                    pstmt.setString(6, patronId);
                    pstmt.setString(7, "");
                    System.out.println(sqlQueryReminder);
                    int row = pstmt.executeUpdate();
                    System.out.println(maxCINOTC);
                    String updateIncrementCINOTCno = "UPDATE GLNUMB SET GL98VALUE=" + incrementNoticNo + " WHERE GL98FUNC='CINOTC'";
                    System.out.println(updateIncrementCINOTCno);
                    pstmt1 = conn.prepareStatement(updateIncrementCINOTCno);
                    int updatedCount = pstmt1.executeUpdate();
                    System.out.println(updatedCount);
                } else if (notifyType.equals("first")) {
                    System.out.println(sqlQueryFirstNotify);
                    pstmt = conn.prepareStatement(sqlQueryFirstNotify);
                    pstmt.executeUpdate();
                } else if (notifyType.equals("second")) {
                    System.out.println(sqlQuerySecondNotify);
                    pstmt = conn.prepareStatement(sqlQuerySecondNotify);
                    pstmt.executeUpdate();
                } else if (notifyType.equals("third")) {
                    System.out.println(sqlQueryFirstNotify);
                    pstmt = conn.prepareStatement(sqlQueryThirdNotify);
                    pstmt.executeUpdate();
                }
            }
            catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (pstmt1 != null) {
                        pstmt1.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    if (conn2 != null) {
                        conn2.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (pstmt1 != null) {
                        pstmt1.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (conn1 != null) {
                        conn1.close();
                    }
                    if (conn2 != null) {
                        conn2.close();
                    }
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (conn1 != null) {
                    conn1.close();
                }
                if (conn2 != null) {
                    conn2.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendEmailtoPatronInNotication(int mailNo, String receiverEmailAddr, String letterSubject, String htmlMailContent) throws AddressException, MessagingException, IOException, URISyntaxException {
        String readSender = SMTP.readSender();
        String readHost = SMTP.readHostName();
        String readPort = SMTP.readPort();
        final String username = SMTP.username();
        final String password = SMTP.password();
        String startTLS = SMTP.startTLS();
        Properties props = System.getProperties();
        props.put("mail.smtp.host", readHost);
        props.put("mail.smtp.port", readPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", startTLS);
        Session session = Session.getInstance((Properties)props, (Authenticator)new Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom((Address)new InternetAddress(readSender, "NoReply"));
        message.setSubject(letterSubject);
        message.setReplyTo((Address[])InternetAddress.parse((String)readSender, (boolean)false));
        message.setContent((Object)htmlMailContent, "text/html; charset=UTF-8");
        try {
            message.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse((String)receiverEmailAddr, (boolean)false));
            Transport.send((Message)message);
        }
        catch (SendFailedException sendFailedException) {
        }
        catch (MessagingException messagingException) {
            // empty catch block
        }
    }
}
