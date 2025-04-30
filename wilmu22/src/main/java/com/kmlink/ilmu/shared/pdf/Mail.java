/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Mail {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
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

    public static String GetMessage(String mailNo) {
        String message = "";
        String sql = "SELECT GL34MESSAGE FROM GLMAIL WHERE GL34MAILNO ='" + mailNo + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    message = rs.getString("GL34MESSAGE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
