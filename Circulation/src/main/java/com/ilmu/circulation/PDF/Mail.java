/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.ilmu.cataloging.Release_Circulation.CatalogingDOC
 *  com.ilmu.cataloging.template.Cataloging
 *  javax.activation.DataHandler
 *  javax.activation.DataSource
 *  javax.activation.FileDataSource
 *  javax.mail.Address
 *  javax.mail.BodyPart
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.MessagingException
 *  javax.mail.Multipart
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.MimeBodyPart
 *  javax.mail.internet.MimeMessage
 *  javax.mail.internet.MimeMultipart
 */
package com.ilmu.circulation.PDF;

import com.ilmu.cataloging.Release_Circulation.CatalogingDOC;
import com.ilmu.cataloging.template.Cataloging;
import com.ilmu.circulation.PDF.Library;
import com.ilmu.global.Config;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
    int count = 0;
    private static Map<String, String> columnName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("sender", "GL34SENDER");
            this.put("date", "GL34SDATE");
            this.put("time", "GL34STIME");
            this.put("message", "GL34MESSAGE");
            this.put("subject", "GL34SUBJECT");
            this.put("status", "GL34STATUS");
            this.put("mailNo", "GL34MAILNO");
        }
    };

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    private static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
        return sdf.format(cal.getTime());
    }

    public static int CreateNew(Library sender, String receiver, String date, String time, String message, String subject, String status) {
        int mailNo = -1;
        Object rs = null;
        System.out.println(sender + date + time + subject + status + message);
        String sql_insert = "INSERT INTO GLMAIL (GL34SENDER, GL34SDATE, GL34STIME, GL34MESSAGE, GL34SUBJECT, GL34STATUS, GL34MAILNO) OUTPUT Inserted.GL34MAILNO VALUES ('" + sender.getPatrName() + "', '" + date + "', '" + time + "', '" + message + "', '" + subject + "', '" + status + "', " + " ((SELECT CAST(GL98VALUE AS INT)" + " FROM GLNUMB" + " WHERE GL98FUNC = 'MAILNO') + 1))";
        String sql_updateMailNo = "UPDATE GLNUMB SET GL98VALUE = GL98VALUE + 1 WHERE GL98FUNC = 'MAILNO'";
        Connection connection = null;
        Statement statement = null;
        PreparedStatement pstmt = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sql_insert);
            pstmt.execute();
            System.out.println("w");
            statement.executeUpdate(sql_updateMailNo);
            connection.commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mailNo;
    }

    public static String GetMessage(String mailNo) {
        String message = "";
        String sql = "SELECT GL34MESSAGE FROM GLMAIL WHERE GL34MAILNO ='" + mailNo + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    message = rs.getString("GL34MESSAGE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public static int CT_CreateReleaseForCI(List<Cataloging> cataloging, Library library, CatalogingDOC catDOC, Library receiver, Library sender, String accMatNo, String accTitl, String accCallNo) {
        String from = "siti@kmlink.com.my";
        String to = "siti@kmlink.com.my";
        String subject = "Release for Circulation";
        String bodyText = "Your item has been reserved";
        String PDF_Release_For_CI = Config.get("PDF_Release_For_CI");
        String pdf = String.valueOf(receiver.getPatrId()) + ".pdf";
        String filename = String.valueOf(PDF_Release_For_CI) + "\\Cataloging\\" + pdf;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "mail.kmlink.com.my");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance((Properties)properties, null);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom((Address)new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, (Address)new InternetAddress(to));
            message.setSubject(subject);
            message.setSentDate(new Date());
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(bodyText);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            FileDataSource fileDataSource = new FileDataSource(filename){

                public String getContentType() {
                    return "application/octet-stream";
                }
            };
            attachmentPart.setDataHandler(new DataHandler((DataSource)fileDataSource));
            attachmentPart.setFileName(pdf);
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart((BodyPart)messagePart);
            multipart.addBodyPart((BodyPart)attachmentPart);
            message.setContent((Multipart)multipart);
            Transport.send((Message)message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
