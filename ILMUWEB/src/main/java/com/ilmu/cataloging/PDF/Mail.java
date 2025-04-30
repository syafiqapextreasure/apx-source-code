/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.cataloging.PDF.Document;
import com.ilmu.cataloging.PDF.Library;
import com.ilmu.cataloging.Release_Circulation.CatalogingDOC;
import com.ilmu.cataloging.template.Cataloging;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mail {
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

    public static int CreateNew(String sender, String receiver, String date, String time, String message, String subject, String status) {
        int mailNo = -1;
        Object rs = null;
        String sql_insert = "INSERT INTO GLMAIL (GL34SENDER, GL34SDATE, GL34STIME, GL34SUBJECT, GL34STATUS, GL34MAILNO, GL34BINARY) VALUES ('" + sender + "', '" + date + "', '" + time + "'," + "'" + subject + "',  '" + status + "',CAST('" + message + "' AS VARBINARY(MAX)),((SELECT CAST(GL98VALUE AS INT) FROM GLNUMB WHERE GL98FUNC = 'MAILNO') + 1))";
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

    public static int CT_CreateReleaseForCI(List<Cataloging> cataloging, CatalogingDOC catDoc, String accMatNo, String accTitl, String accCallNo, String sender) {
        String message = Document.createReleaseForCIDoc(cataloging, Library.getContactsOnly(), catDoc, Library.getPatrDetails(), accMatNo, accTitl, accCallNo);
        return Mail.CreateNew(sender, catDoc.getAccNo(), Mail.getCurrentDate(), Mail.getCurrentTime(), message, "RELEASE FOR CIRCULATION", "N");
    }
}
