/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  oracle.sql.CLOB
 */
package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.pdf.Library;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import oracle.sql.CLOB;

public class saveMail {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private static final int CHUNK_BUFFER_SIZE = 1024;

    public static void updateCICIRC(String accessionNo, String patronId) {
        System.out.println("OverdueNotification update Mail Browser: " + accessionNo + "   " + patronId);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        String sqldate = dtf.format(localDate);
        System.out.println("mssql today date: " + sqldate);
        String updateQuery = "UPDATE CICIRC SET CI02SENT3=? WHERE CI02DOCNO=? AND CI02PATR=?";
        try {
            try {
                conn = DBConnection.getConnection();
                pstmt = conn.prepareStatement(updateQuery);
                pstmt.setString(1, sqldate);
                pstmt.setString(2, accessionNo);
                pstmt.setString(3, patronId);
                int row = pstmt.executeUpdate();
                System.out.println("Update row: " + row);
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

    public static saveMail SAVEMAIL(int newmailno, String liferayLogin, String receiverId, String text, String letterId) throws Exception {
        System.out.println("Andy new mail no: " + newmailno);
        System.out.println("Andy liferay Login: " + liferayLogin);
        System.out.println("Andy receiverId: " + receiverId);
        System.out.println("Andy text: " + text);
        System.out.println("Andy letter Id: " + letterId);
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            Library subject = Library.lettersubject(letterId);
            valueInt.put("GL34MAILNO", newmailno);
            valueStr.put("GL34SENDER", liferayLogin);
            valueStr.put("GL34SDATE", date.format(now));
            valueStr.put("GL34STIME", time.format(now));
            valueStr.put("GL34MESSAGE", text);
            valueStr.put("GL34RECEIVER", receiverId);
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

    public static void InsertGLMAIL(String json, int newMailNo, String liferayLogin, String receiverId, String text, String letterId) throws Exception {
        DBConnection db = new DBConnection();
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
            LocalDateTime now = LocalDateTime.now();
            Library subject = Library.lettersubject(letterId);
            valueInt.put("GL34MAILNO", newMailNo);
            valueStr.put("GL34SENDER", liferayLogin);
            valueStr.put("GL34SDATE", date.format(now));
            valueStr.put("GL34STIME", time.format(now));
            valueStr.put("GL34MESSAGE", text);
            valueStr.put("GL34RECEIVER", receiverId);
            valueStr.put("GL34SUBJECT", subject.getName());
            valueStr.put("GL34STATUS", "N");
            valueStr.put("GL34LETTERID", letterId);
            if (DBConnection.getDBType().equals("oracle")) {
                saveMail.handleLongMessage("GLMAIL", valueStr, valueInt, null);
            } else {
                String add = QueryBuilder.createInsertQuery("GLMAIL", valueStr, valueInt, null);
                boolean bl = DBQuery.runQuery(add);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void handleLongMessage(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap) throws SQLException {
        Map.Entry<String, Object> pair;
        Iterator<Map.Entry<String, Object>> it;
        String query = "INSERT INTO " + table + "(";
        String column = "";
        String value = "";
        String columnInt = "";
        String valueInt = "";
        String columnDouble = "";
        String valueDouble = "";
        String longMessage = "";
        if (table.equals("CTWORK") || table.equals("CTSUBJ") || table.equals("CTAUTH") || table.equals("CTCALL") || table.equals("CTINDX") || table.equals("CTPUBL") || table.equals("CTSERI") || table.equals("CTTITL") || table.equals("MPDATA")) {
            if (map != null) {
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    pair = it.next();
                    column = String.valueOf(column) + pair.getKey();
                    value = String.valueOf(value) + "N'" + (String)pair.getValue() + "'";
                    if (!it.hasNext()) continue;
                    column = String.valueOf(column) + ", ";
                    value = String.valueOf(value) + ", ";
                }
            }
        } else if (map != null) {
            it = map.entrySet().iterator();
            while (it.hasNext()) {
                pair = it.next();
                String replaceValue = ((String)pair.getValue()).replaceAll("'", "''");
                column = String.valueOf(column) + pair.getKey();
                if (((String)pair.getValue()).isEmpty()) {
                    value = String.valueOf(value) + null;
                } else if (pair.getKey().equals("GL34MESSAGE")) {
                    longMessage = (String)pair.getValue();
                    replaceValue = "?";
                    value = String.valueOf(value) + replaceValue;
                } else {
                    value = String.valueOf(value) + "'" + replaceValue + "'";
                }
                if (!it.hasNext()) continue;
                column = String.valueOf(column) + ", ";
                value = String.valueOf(value) + ", ";
            }
        }
        if (intMap != null) {
            it = intMap.entrySet().iterator();
            while (it.hasNext()) {
                pair = it.next();
                columnInt = String.valueOf(columnInt) + pair.getKey();
                valueInt = String.valueOf(valueInt) + pair.getValue();
                if (!it.hasNext()) continue;
                columnInt = String.valueOf(columnInt) + ", ";
                valueInt = String.valueOf(valueInt) + ", ";
            }
        }
        if (doubleMap != null) {
            it = doubleMap.entrySet().iterator();
            while (it.hasNext()) {
                pair = it.next();
                columnDouble = String.valueOf(columnDouble) + pair.getKey();
                valueDouble = String.valueOf(valueDouble) + pair.getValue();
                if (!it.hasNext()) continue;
                columnDouble = String.valueOf(columnDouble) + ", ";
                valueDouble = String.valueOf(valueDouble) + ", ";
            }
        }
        query = String.valueOf(query) + column;
        if (intMap != null) {
            if (map != null) {
                query = String.valueOf(query) + ", ";
            }
            query = String.valueOf(query) + columnInt;
        }
        if (doubleMap != null) {
            if (map != null || intMap != null) {
                query = String.valueOf(query) + ", ";
            }
            query = String.valueOf(query) + columnDouble;
        }
        query = String.valueOf(query) + ") VALUES(" + value;
        if (intMap != null) {
            if (map != null) {
                query = String.valueOf(query) + ", ";
            }
            query = String.valueOf(query) + valueInt;
        }
        if (doubleMap != null) {
            if (map != null || intMap != null) {
                query = String.valueOf(query) + ", ";
            }
            query = String.valueOf(query) + valueDouble;
        }
        query = String.valueOf(query) + ")";
        conn = DBConnection.getConnection();
        Throwable throwable = null;
        Object var14_15 = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query);){
            CLOB tempClob = CLOB.createTemporary((Connection)conn, (boolean)false, (int)10);
            tempClob.setString(1L, longMessage);
            pstmt.setClob(1, (Clob)tempClob);
            pstmt.execute();
            conn.setAutoCommit(false);
            boolean isTempCLOB = CLOB.isTemporary((CLOB)tempClob);
            saveMail.show("CLOB.isTemporary: " + isTempCLOB);
            CLOB.freeTemporary((CLOB)tempClob);
        }
        catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }

    private static void show(String msg) {
        System.out.println(msg);
    }

    private static void executeClobQuery(int id) throws Exception {
        Throwable throwable = null;
        Object var2_3 = null;
        try (PreparedStatement pstmt = conn.prepareStatement("select max(GL34MAILNO) from GLMAIL");){
            pstmt.setInt(1, id);
            Throwable throwable2 = null;
            Object var5_8 = null;
            try (ResultSet rset = pstmt.executeQuery();){
                saveMail.show("LOB_ID = " + id);
                while (rset.next()) {
                    Clob c = rset.getClob(1);
                    saveMail.getAndDisplayClobData("CLOB_DATA  = ", c);
                }
            }
            catch (Throwable throwable3) {
                if (throwable2 == null) {
                    throwable2 = throwable3;
                } else if (throwable2 != throwable3) {
                    throwable2.addSuppressed(throwable3);
                }
                throw throwable2;
            }
        }
        catch (Throwable throwable4) {
            if (throwable == null) {
                throwable = throwable4;
            } else if (throwable != throwable4) {
                throwable.addSuppressed(throwable4);
            }
            throw throwable;
        }
    }

    private static void getAndDisplayClobData(String message, Clob clob) throws Exception {
        Throwable throwable = null;
        Object var3_4 = null;
        try (Reader clobStream = clob.getCharacterStream();){
            char[] buffer = new char[1024];
            int length = 0;
            saveMail.showln(message);
            while ((length = clobStream.read(buffer)) != -1) {
                saveMail.showln(new String(buffer, 0, length));
            }
            saveMail.show("");
        }
        catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }

    private static void showln(String msg) {
        System.out.print(msg);
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

    public static boolean updatingmailno(int newmailno) throws SQLException {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", "MAILNO");
        valueInt.put("GL98VALUE", newmailno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static saveMail RecordTransaction(String liferayLogin, String vsRefNo) throws Exception {
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
