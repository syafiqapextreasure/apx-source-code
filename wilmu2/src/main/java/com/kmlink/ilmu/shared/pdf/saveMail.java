package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
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
      conn = DBConnection.getConnection();
      pstmt = conn.prepareStatement(updateQuery);
      pstmt.setString(1, sqldate);
      pstmt.setString(2, accessionNo);
      pstmt.setString(3, patronId);
      int row = pstmt.executeUpdate();
      System.out.println("Update row: " + row);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
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
    Map<String, String> valueStr = new HashMap<>();
    Map<String, Integer> valueInt = new HashMap<>();
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
      DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
      LocalDateTime now = LocalDateTime.now();
      Library subject = Library.lettersubject(letterId);
      valueInt.put("GL34MAILNO", Integer.valueOf(newmailno));
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
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public static void InsertGLMAIL(String json, int newMailNo, String liferayLogin, String receiverId, String text, String letterId) throws Exception {
    DBConnection db = new DBConnection();
    Map<String, String> valueStr = new HashMap<>();
    Map<String, Integer> valueInt = new HashMap<>();
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
      DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
      LocalDateTime now = LocalDateTime.now();
      Library subject = Library.lettersubject(letterId);
      valueInt.put("GL34MAILNO", Integer.valueOf(newMailNo));
      valueStr.put("GL34SENDER", liferayLogin);
      valueStr.put("GL34SDATE", date.format(now));
      valueStr.put("GL34STIME", time.format(now));
      valueStr.put("GL34MESSAGE", text);
      valueStr.put("GL34RECEIVER", receiverId);
      valueStr.put("GL34SUBJECT", subject.getName());
      valueStr.put("GL34STATUS", "N");
      valueStr.put("GL34LETTERID", letterId);
      if (DBConnection.getDBType().equals("oracle")) {
        handleLongMessage("GLMAIL", valueStr, valueInt, null);
      } else {
        String add = QueryBuilder.createInsertQuery("GLMAIL", valueStr, valueInt, null);
        boolean bool = DBQuery.runQuery(add);
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static void handleLongMessage(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap) throws SQLException {
    String query = "INSERT INTO " + table + "(";
    String column = "";
    String value = "";
    String columnInt = "";
    String valueInt = "";
    String columnDouble = "";
    String valueDouble = "";
    String longMessage = "";
    if (table.equals("CTWORK") || table.equals("CTSUBJ") || table.equals("CTAUTH") || table.equals("CTCALL") || 
      table.equals("CTINDX") || table.equals("CTPUBL") || table.equals("CTSERI") || table.equals("CTTITL") || 
      table.equals("MPDATA")) {
      if (map != null) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry<String, String> pair = it.next();
          column = String.valueOf(column) + (String)pair.getKey();
          value = String.valueOf(value) + "N'" + (String)pair.getValue() + "'";
          if (it.hasNext()) {
            column = String.valueOf(column) + ", ";
            value = String.valueOf(value) + ", ";
          } 
        } 
      } 
    } else if (map != null) {
      Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<String, String> pair = it.next();
        String replaceValue = ((String)pair.getValue()).replaceAll("'", "''");
        column = String.valueOf(column) + (String)pair.getKey();
        if (((String)pair.getValue()).isEmpty()) {
          value = String.valueOf(value) + null;
        } else if (((String)pair.getKey()).equals("GL34MESSAGE")) {
          longMessage = pair.getValue();
          replaceValue = "?";
          value = String.valueOf(value) + replaceValue;
        } else {
          value = String.valueOf(value) + "'" + replaceValue + "'";
        } 
        if (it.hasNext()) {
          column = String.valueOf(column) + ", ";
          value = String.valueOf(value) + ", ";
        } 
      } 
    } 
    if (intMap != null) {
      Iterator<Map.Entry<String, Integer>> it = intMap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<String, Integer> pair = it.next();
        columnInt = String.valueOf(columnInt) + (String)pair.getKey();
        valueInt = String.valueOf(valueInt) + pair.getValue();
        if (it.hasNext()) {
          columnInt = String.valueOf(columnInt) + ", ";
          valueInt = String.valueOf(valueInt) + ", ";
        } 
      } 
    } 
    if (doubleMap != null) {
      Iterator<Map.Entry<String, Double>> it = doubleMap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<String, Double> pair = it.next();
        columnDouble = String.valueOf(columnDouble) + (String)pair.getKey();
        valueDouble = String.valueOf(valueDouble) + pair.getValue();
        if (it.hasNext()) {
          columnDouble = String.valueOf(columnDouble) + ", ";
          valueDouble = String.valueOf(valueDouble) + ", ";
        } 
      } 
    } 
    query = String.valueOf(query) + column;
    if (intMap != null) {
      if (map != null)
        query = String.valueOf(query) + ", "; 
      query = String.valueOf(query) + columnInt;
    } 
    if (doubleMap != null) {
      if (map != null || intMap != null)
        query = String.valueOf(query) + ", "; 
      query = String.valueOf(query) + columnDouble;
    } 
    query = String.valueOf(query) + ") VALUES(" + value;
    if (intMap != null) {
      if (map != null)
        query = String.valueOf(query) + ", "; 
      query = String.valueOf(query) + valueInt;
    } 
    if (doubleMap != null) {
      if (map != null || intMap != null)
        query = String.valueOf(query) + ", "; 
      query = String.valueOf(query) + valueDouble;
    } 
    query = String.valueOf(query) + ")";
    conn = DBConnection.getConnection();
    Exception exception1 = null, exception2 = null;
  }
  
  private static void show(String msg) {
    System.out.println(msg);
  }
  
  private static void executeClobQuery(int id) throws Exception {
    Exception exception1 = null, exception2 = null;
  }
  
  private static void getAndDisplayClobData(String message, Clob clob) throws Exception {
    Exception exception1 = null, exception2 = null;
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
      Statement stmt = null;
      ResultSet rs = null;
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sSQLStmt);
      while (rs.next())
        getMAILNO = rs.getInt("GL98VALUE"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return getMAILNO;
  }
  
  public static boolean updatingmailno(int newmailno) throws SQLException {
    Map<String, Integer> valueInt = new HashMap<>();
    Map<String, String> condition = new HashMap<>();
    condition.put("GL98FUNC", "MAILNO");
    valueInt.put("GL98VALUE", Integer.valueOf(newmailno));
    String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
    boolean isSuccess = DBQuery.runQuery(query);
    System.out.println(isSuccess);
    return isSuccess;
  }
  
  public static saveMail RecordTransaction(String liferayLogin, String vsRefNo) throws Exception {
    Map<String, String> valueStr = new HashMap<>();
    Map<String, Integer> valueInt = new HashMap<>();
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
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return null;
  }
}
