/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.itextpdf.text.DocumentException
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
package com.kmlink.ilmu.mailBrowser.email;

import com.itextpdf.text.DocumentException;
import com.kmlink.ilmu.mailBrowser.email.SMTP;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class MailBrowser {
    private static int counter = 0;
    private String sender = null;
    private String sentDate = null;
    private String sentTime = null;
    private String message = null;
    private String receiverId = null;
    private String receivedDate = null;
    private String receivedTime = null;
    private String ccid = null;
    private String subject = null;
    private String reply = null;
    private String status = null;
    private int mailNo = 0;
    private String email = null;
    private String type = null;
    private String letterId = null;
    private String receiverName = null;
    private String sendToPatr = null;
    private String sendToVend = null;

    public String getSender() {
        return this.sender;
    }

    public String getSentDate() {
        return this.sentDate;
    }

    public String getSentTime() {
        return this.sentTime;
    }

    public String getMessage() {
        return this.message;
    }

    public String getReceiver() {
        return this.receiverId;
    }

    public String getReceivedDate() {
        return this.receivedDate;
    }

    public String getReceivedTime() {
        return this.receivedTime;
    }

    public String getCcid() {
        return this.ccid;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getReply() {
        return this.reply;
    }

    public String getStatus() {
        return this.status;
    }

    public int getMailNo() {
        return this.mailNo;
    }

    public String getEmail() {
        return this.email;
    }

    public String getType() {
        return this.type;
    }

    public String getLetterId() {
        return this.letterId;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSendToPatr() {
        return this.sendToPatr;
    }

    public void setSendToPatr(String sendToPatr) {
        this.sendToPatr = sendToPatr;
    }

    public String getSendToVend() {
        return this.sendToVend;
    }

    public void setSendToVend(String sendToVend) {
        this.sendToVend = sendToVend;
    }

    public MailBrowser(String sender, String sentDate, String sentTime, String message, String receiverId, String receivedDate, String receivedTime, String ccid, String subject, String reply, String status, int mailNo, String email, String type, String letterId) {
        this.sender = sender;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.message = message;
        this.receiverId = receiverId;
        this.receivedDate = receivedDate;
        this.receivedTime = receivedTime;
        this.ccid = ccid;
        this.subject = subject;
        this.reply = reply;
        this.status = status;
        this.mailNo = mailNo;
        this.email = email;
        this.type = type;
        this.letterId = letterId;
    }

    public MailBrowser(int mailNo, String sender, String sendToPatr, String receiverId, String email, String sentDate, String sentTime, String subject, String message) {
        this.mailNo = mailNo;
        this.sender = sender;
        this.sendToPatr = sendToPatr;
        this.receiverId = receiverId;
        this.email = email;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.subject = subject;
        this.message = message;
    }

    public MailBrowser(int mailNo, String receiverId, String receiverName, String email, String subject, String message) {
        this.mailNo = mailNo;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public MailBrowser(String subject) {
        this.subject = subject;
    }

    public MailBrowser() {
    }

    public static List<String> getSubject(String startDate, String endDate, String receiver, String sendMailStatus) throws ParseException {
        String convertedStartDate = MailBrowser.convertDBdateFormat(startDate);
        String convertedEndDate = MailBrowser.convertDBdateFormat(endDate);
        ArrayList<String> mail = new ArrayList<String>();
        if (receiver.equals("Any")) {
            String query = "SELECT DISTINCT GL34SUBJECT\r\nFROM GLMAIL\r\nWHERE GL34EMAIL IS NULL\r\nAND GL34SDATE >= '" + convertedStartDate + "'\r\n" + "AND GL34SDATE <= '" + convertedEndDate + "'\r\n" + "ORDER BY GL34SUBJECT\r\n";
            String query1 = "SELECT DISTINCT GL34SUBJECT\r\nFROM GLMAIL\r\nWHERE GL34EMAIL='Y'\r\nAND GL34SDATE >= '" + convertedStartDate + "'\r\n" + "AND GL34SDATE <= '" + convertedEndDate + "'\r\n" + "ORDER BY GL34SUBJECT\r\n";
            Connection conn = null;
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    if (sendMailStatus.equals("false")) {
                        stmt = conn.prepareStatement(query);
                        System.out.println(query);
                    } else if (sendMailStatus.equals("true")) {
                        stmt = conn.prepareStatement(query1);
                        System.out.println(query1);
                    }
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i2 = 1;
                        while (i2 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i2) + ", ";
                            ++i2;
                        }
                        mail.add(rs.getString("GL34SUBJECT"));
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
        } else if (receiver.equals("Patron")) {
            String query = "SELECT DISTINCT GL34SUBJECT\r\nFROM GLMAIL, GLPATR\r\nWHERE GL34EMAIL IS NULL\r\nAND GLPATR.GL14PATR= GLMAIL.GL34RECEIVER\r\nAND gl34sdate>= ?\r\nAND gl34sdate<=?\r\nORDER BY GL34SUBJECT\r\n";
            String query1 = "SELECT DISTINCT GL34SUBJECT\r\nFROM GLMAIL, GLPATR\r\nWHERE GL34EMAIL='Y'\r\nAND GLPATR.GL14PATR= GLMAIL.GL34RECEIVER\r\nAND gl34sdate>= ?\r\nAND gl34sdate<=?\r\nORDER BY GL34SUBJECT\r\n";
            Connection conn = null;
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    if (sendMailStatus.equals("false")) {
                        stmt = conn.prepareStatement(query);
                    } else if (sendMailStatus.equals("true")) {
                        stmt = conn.prepareStatement(query1);
                    }
                    stmt.setString(1, convertedStartDate);
                    stmt.setString(2, convertedEndDate);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i3 = 1;
                        while (i3 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i3) + ", ";
                            ++i3;
                        }
                        mail.add(rs.getString("GL34SUBJECT"));
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
        } else if (receiver.equals("Vendor")) {
            String query = "SELECT DISTINCT GL34SUBJECT FROM GLMAIL T1 LEFT JOIN GLVEND T2 ON(T2.GL39CODE= T1.GL34RECEIVER) WHERE GL34EMAIL IS NULL AND gl34sdate>= ? AND gl34sdate<= ? AND T2.GL39SUPPLIER ='Y' ORDER BY GL34SUBJECT";
            String query1 = "SELECT DISTINCT GL34SUBJECT FROM GLMAIL T1 LEFT JOIN GLVEND T2 ON(T2.GL39CODE= T1.GL34RECEIVER) WHERE GL34EMAIL='Y' AND gl34sdate>= ? AND gl34sdate<= ? AND T2.GL39SUPPLIER ='Y' ORDER BY GL34SUBJECT";
            Connection conn = null;
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    if (sendMailStatus.equals("false")) {
                        stmt = conn.prepareStatement(query);
                    } else if (sendMailStatus.equals("true")) {
                        stmt = conn.prepareStatement(query1);
                    }
                    stmt.setString(1, convertedStartDate);
                    stmt.setString(2, convertedEndDate);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i4 = 1;
                        while (i4 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i4) + ", ";
                            ++i4;
                        }
                        mail.add(rs.getString("GL34SUBJECT"));
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
        } else {
            String query = "SELECT DISTINCT GL34SUBJECT FROM GLMAIL T1 LEFT JOIN GLVEND T2 ON(T2.GL39CODE= T1.GL34RECEIVER) WHERE GL34RECEIVER='' OR GL34RECEIVER ='2000011982' AND GL34SDATE >=? AND GL34SDATE <=? ORDER BY GL34SUBJECT";
            String query1 = "SELECT DISTINCT GL34SUBJECT FROM GLMAIL T1 LEFT JOIN GLVEND T2 ON(T2.GL39CODE= T1.GL34RECEIVER) WHERE GL34RECEIVER='436234235' OR GL34RECEIVER ='200001198243243523' AND GL34SDATE >=? AND GL34SDATE <=? ORDER BY GL34SUBJECT";
            Connection conn = null;
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    if (sendMailStatus.equals("false")) {
                        stmt = conn.prepareStatement(query);
                    } else if (sendMailStatus.equals("true")) {
                        stmt = conn.prepareStatement(query1);
                    }
                    stmt.setString(1, convertedStartDate);
                    stmt.setString(2, convertedEndDate);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("what is metadata  [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i5 = 1;
                        while (i5 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i5) + ", ";
                            ++i5;
                        }
                        mail.add(rs.getString("GL34SUBJECT"));
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e5) {
                        e5.printStackTrace();
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
        return mail;
    }

    public static List<MailBrowser> getBySubject(String startDate, String endDate, String subject, String sendMailStatus) throws ParseException {
        String convertedStartDate = MailBrowser.convertDBdateFormat(startDate);
        String convertedEndDate = MailBrowser.convertDBdateFormat(endDate);
        String query = "SELECT GL34MAILNO, GL34SENDER\r\n      ,T2.GL14NAME, T3.GL39NAME, T2.GL14IPADD, T3.GL39EMAIL, T1.GL34RECEIVER \r\n ,GL34SDATE\r\n,GL34STIME\r\n,GL34SUBJECT\r\n,GL34MESSAGE\r\nFROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nLEFT JOIN GLVEND T3 ON (T3.GL39CODE = T1.GL34RECEIVER)\r\nWHERE GL34EMAIL IS NULL AND GL34STATUS='N'\r\nAND GL34SUBJECT = '" + subject + "'\r\n" + "AND GL34SDATE >= '" + convertedStartDate + "'\r\n" + "AND GL34SDATE <= '" + convertedEndDate + "'\r\n" + "ORDER BY GL34MAILNO\r\n";
        String query1 = "SELECT GL34MAILNO, GL34SENDER\r\n,T2.GL14NAME, T3.GL39NAME, T2.GL14IPADD, T3.GL39EMAIL, T1.GL34RECEIVER\r\n,GL34SDATE\r\n,GL34STIME\r\n,GL34SUBJECT\r\n,GL34MESSAGE\r\nFROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nLEFT JOIN GLVEND T3 ON (T3.GL39CODE = T1.GL34RECEIVER)\r\nWHERE GL34EMAIL='Y' AND GL34STATUS='N'\r\nAND GL34SUBJECT = '" + subject + "'\r\n" + "AND GL34SDATE >= '" + convertedStartDate + "'\r\n" + "AND GL34SDATE <= '" + convertedEndDate + "'\r\n" + "ORDER BY GL34MAILNO\r\n";
        ArrayList<MailBrowser> mail = new ArrayList<MailBrowser>();
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sendMailStatus.equals("false")) {
                    stmt = conn.prepareStatement(query);
                    System.out.println(query);
                } else if (sendMailStatus.equals("true")) {
                    stmt = conn.prepareStatement(query1);
                    System.out.println(query1);
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
                    String emailAdd = null;
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        emailAdd = rs.getString("GL14IPADD") != null ? rs.getString("GL14IPADD") : rs.getString("GL39EMAIL");
                        ++i2;
                    }
                    mail.add(new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34SENDER"), rs.getString("GL14NAME"), rs.getString("GL34RECEIVER"), emailAdd, MailBrowser.swapYearAndDayInDateFormat(rs.getString("GL34SDATE")), MailBrowser.convertTime(rs.getString("GL34STIME")), rs.getString("GL34SUBJECT"), rs.getString("GL34MESSAGE")));
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
        return mail;
    }

    public static MailBrowser get(int mailNo) {
        String query = "SELECT GL34MAILNO\r\n,GL34RECEIVER, GL14NAME, T2.GL14IPADD\r\n,GL34SUBJECT\r\n,GL34MESSAGE\r\nFROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nWHERE GL34MAILNO = ?";
        MailBrowser mail = new MailBrowser();
        Connection conn = null;
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
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                if (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    mail = new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34RECEIVER"), rs.getString("GL14NAME"), rs.getString("GL14IPADD"), rs.getString("GL34SUBJECT"), MailBrowser.SaveHTMLMessageToTxtFile(rs.getString("GL34MESSAGE")));
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
        return mail;
    }

    public static MailBrowser delete(String time, String mailNo) {
        String query = "DELETE FROMFROM GLMAIL\r\nWHERE [GL4STIME] =? [GL34MAILNO] = ?";
        MailBrowser mail = new MailBrowser();
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
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                if (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    mail = new MailBrowser(rs.getString("GL34SENDER"), rs.getString("GL34SDATE"), rs.getString("GL34STIME"), rs.getString("GL34MESSAGE"), rs.getString("GL34RECEIVER"), rs.getString("GL34RDATE"), rs.getString("GL34RTIME"), rs.getString("GL34CCID"), rs.getString("GL34SUBJECT"), rs.getString("GL34REPLY"), rs.getString("GL34STATUS"), rs.getInt("GL34MAILNO"), rs.getString("GL34EMAIL"), rs.getString("GL34RTYPE"), rs.getString("GL34LETTERID"));
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
        return mail;
    }

    public static boolean deleteEmail(String mailNo) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        try {
            MailBrowser.deleteEmailByMailID(mailNo);
            return true;
        }
        catch (Exception exception) {
            return bSuccessful;
        }
    }

    public static void deleteEmailByMailID(String mailNo) {
        String query = "DELETE FROM GLMAIL WHERE GL34MAILNO = '" + mailNo + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                int n = delete.executeUpdate();
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

    public static List<MailBrowser> printEmail(int mailNo) throws SQLException, FileNotFoundException, DocumentException {
        String query = "SELECT GL34MAILNO\r\n      ,GL34RECEIVER , T2.GL14NAME, T2.GL14IPADD\r\n      ,GL34SUBJECT\r\n      ,GL34MESSAGE\r\nFROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nWHERE GL34MAILNO = ?";
        ArrayList<MailBrowser> mail = new ArrayList<MailBrowser>();
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, mailNo);
            rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            int i = 1;
            while (i <= columnCount) {
                System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                ++i;
            }
            if (rs.next()) {
                String row = "";
                int i2 = 1;
                while (i2 <= columnCount) {
                    System.out.println(row);
                    row = String.valueOf(row) + rs.getString(i2) + ", ";
                    ++i2;
                }
                mail.add(new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34RECEIVER"), rs.getString("GL14NAME"), rs.getString("GL14IPADD"), rs.getString("GL34SUBJECT"), MailBrowser.filterPrintMessage(rs.getString("GL34MESSAGE"))));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public static List<MailBrowser> saveEmail(int mailNo) throws FileNotFoundException, DocumentException {
        String query = "SELECT GL34MAILNO ,GL34RECEIVER , T2.GL14NAME, T2.GL14IPADD ,GL34SUBJECT ,GL34MESSAGE FROM GLMAIL T1\r\nLEFT JOIN GLPATR T2 ON (T2.GL14PATR = T1.GL34RECEIVER)\r\nWHERE GL34MAILNO = ?";
        ArrayList<MailBrowser> mail = new ArrayList<MailBrowser>();
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, mailNo);
            rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            int i = 1;
            while (i <= columnCount) {
                System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                ++i;
            }
            if (rs.next()) {
                String row = "";
                int i2 = 1;
                while (i2 <= columnCount) {
                    row = String.valueOf(row) + rs.getString(i2) + ", ";
                    ++i2;
                }
                mail.add(new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34RECEIVER"), rs.getString("GL14NAME"), rs.getString("GL14IPADD"), rs.getString("GL34SUBJECT"), MailBrowser.SaveHTMLMessageToTxtFile(rs.getString("GL34MESSAGE"))));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return mail;
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
                                    if (rs.getString(i22).contains("html")) {
                                        message.setContent((Object)MailBrowser.filterVBnHTMLMessage(rs.getString(i22)), "text/html; charset=UTF-8");
                                    } else {
                                        message.setText(MailBrowser.filterVBnHTMLMessage(rs.getString(i22)));
                                    }
                                }
                            }
                        }
                        ++i22;
                    }
                    System.out.println(row);
                    mail = new MailBrowser(rs.getInt("GL34MAILNO"), rs.getString("GL34RECEIVER"), rs.getString("GL14NAME"), rs.getString("GL14IPADD"), rs.getString("GL34SUBJECT"), rs.getString("GL34RECEIVER"));
                    try {
                        message.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse((String)receiverEmailAddr, (boolean)false));
                        Transport.send((Message)message);
                    }
                    catch (SendFailedException i) {
                    }
                    catch (MessagingException i) {
                        // empty catch block
                    }
                    try {
                        PreparedStatement stmtUpdate = null;
                        stmtUpdate = conn.prepareStatement(sqlUpdateMailStatus);
                        stmtUpdate.setInt(1, mailNo);
                        int n = stmtUpdate.executeUpdate();
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

    public static String filterPrintMessage(String msg) {
        String filteredMsg = null;
        if (msg.contains("BEGIN VARIABLE")) {
            return msg;
        }
        filteredMsg = msg.replaceAll("\\{.*?\\}", "");
        return filteredMsg;
    }

    public static String filterVBnHTMLMessage(String msg) {
        String filteredMsg = null;
        filteredMsg = msg.contains("html") ? msg : msg.replaceAll("\\{.*?\\}", "");
        return filteredMsg;
    }

    public static String filterHTMLMessage(String msg) {
        String filtereHTMLdMsg = null;
        filtereHTMLdMsg = msg.replaceAll("\\{.*?\\}", "").replaceAll("\\<.*?\\>", "").replaceAll("\\/.*?\\.table", "");
        return filtereHTMLdMsg;
    }

    public static String SaveHTMLMessageToTxtFile(String msg) {
        String filteredMsg = null;
        String mapSubString = "  BEGIN ARRAY LATEITEMS     TITLE     ACCNO     CALLNO     DUEDATE     DUETIME     BORROWDATE     BORROWTIME     LATEBY     FINES     REMARK     LCOST     LOCATION     BRANCH     LOCATIONDESC     BRANCHDESC END ARRAY  BEGIN VARIABLE     LIBRARYNAME     LIBRARYADD1     LIBRARYADD2     LIBRARYADD3     LIBRARYTOWN     LIBRARYPOSTCODE     BRANCHNAME     BRANCHADD1     BRANCHADD2     BRANCHADD3     BRANCHTOWN     BRANCHPOSTCODE     LETTERDATE     LETTERTIME     LETTERSUBJECT     OFFICERNAME     DESIGNATION     PATRONNAME     PATRONADD1     PATRONADD2     PATRONADD3     PATRONID     HOMETEL     OFFTEL     DEPTCODE     DEPARTMENTDESC     POSTCODE     TOWN     COURSE END VARIABLE  BEGIN TEXT ";
        filteredMsg = msg.contains("box") ? msg.replaceAll("\\{.*?\\}", "").replaceAll("\\[.*?\\]", "\n").replace(mapSubString, "") : msg.replaceAll("\\<style>.*?\\</style>", "");
        return filteredMsg;
    }

    public static String displayModalMessage(String msg) {
        String filteredMsg = null;
        if (msg.contains("BEGIN VARIABLE")) {
            filteredMsg = msg;
            filteredMsg = filteredMsg.replaceAll("<link(.*?)\">", "");
            filteredMsg = filteredMsg.replaceAll("<style>(.*?)<\\/style>", "");
            filteredMsg = String.valueOf(filteredMsg) + "<style>\r\n" + "\r\n" + "#htmlContent .ta-right {\r\n" + "\ttext-align: right;\r\n" + "\tfloat: right;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .ta-center {\r\n" + "\tmargin-left: auto;\r\n" + "\tmargin-right: auto;\r\n" + "\ttext-align: center;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .padd-right-5 {\r\n" + "\tpadding-right: 5px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .margin-top-15 {\r\n" + "\tmargin-top: 15px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .margin-btm-15 {\r\n" + "\tmargin-bottom: 15px;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .ta-left {\r\n" + "\tfloat: left;\r\n" + "\tdisplay: inline-block;\r\n" + "\tposition: absolute;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .float-right {\r\n" + "\tfloat: right;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .clear {\r\n" + "\tclear: both;\r\n" + "}\r\n" + "\r\n" + "#htmlContent .table {\r\n" + "\tdisplay: inline-block;\r\n" + "\tposition: absolute;\r\n" + "\ttop: auto;\r\n" + "\twhite-space: pre-wrap;\r\n" + "\toverflow: hidden;\r\n" + "\tfont-size: 10pt !important;\r\n" + "\tfont-family: \"Times New Roman\";\r\n" + "\tmargin-left: 0.30in;\r\n" + "}\r\n" + "\r\n" + "</style>";
            filteredMsg = filteredMsg.replaceAll("\\{.*\\}", "").replaceAll("\\<style>.*?\\</style>", "");
        } else {
            filteredMsg = msg.replaceAll("\\{.*?\\}", "");
        }
        return msg;
    }

    public static String swapYearAndDayInDateFormat(String inputDate) throws ParseException {
        Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        String outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        return outputDate;
    }

    public static String convertDBdateFormat(String inputDate) throws ParseException {
        Date inputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(inputDate);
        String outputDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(inputDateFormat);
        return outputDate;
    }

    public static String convertTime(String input) {
        String output = "";
        if (input.equals("")) {
            return output;
        }
        output = String.valueOf(input.substring(0, 2)) + ":" + input.substring(2, 4) + ":" + input.substring(4, 6) + " " + MailBrowser.ampm(input.substring(0, 2));
        return output;
    }

    public static String ampm(String input) {
        String output = null;
        int parsedInput = Integer.parseInt(input);
        if (parsedInput >= 0 && parsedInput <= 11) {
            output = "AM";
        } else if (parsedInput >= 12 && parsedInput <= 23) {
            output = "PM";
        }
        return output;
    }
}
