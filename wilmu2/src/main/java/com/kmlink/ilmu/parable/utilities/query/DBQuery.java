package com.kmlink.ilmu.parable.utilities.query;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DBQuery {
  static Connection conn = null;
  
  static Statement stmt = null;
  
  static ResultSet rs = null;
  
  public static String getSingleData(String column, String table, String condition) {
    String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      if (rs.next())
        return rs.getString(column); 
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return null;
  }
  
  public static String getSingleDataAsData(String column, String table, String condition) {
    String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      if (rs.next())
        return rs.getString("Data"); 
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return null;
  }
  
  public static List<String> getListData(String column, String table, String condition) {
    List<String> list = new ArrayList<>();
    String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next())
        list.add(rs.getString("Data")); 
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return list;
  }
  
  public static boolean runQuery(String query) throws SQLException {
    System.out.println(query);
    conn = DBConnection.getConnection();
    try {
      PreparedStatement updateOrder = conn.prepareStatement(query, 
          1003, 
          1008);
      int success = updateOrder.executeUpdate();
      if (success > 0)
        return true; 
      return false;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return false;
  }
  
  public static boolean isExist(String table, Map<String, String> conditionMap) {
    String query = "SELECT COUNT(*) AS Count FROM " + table + " WHERE ";
    if (conditionMap != null) {
      Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<String, String> pair = it.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = '" + (String)pair.getValue() + "' ";
        if (it.hasNext())
          query = String.valueOf(query) + "AND"; 
        query = String.valueOf(query) + " ";
      } 
    } 
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    try {
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
    return false;
  }
  
  public static boolean runBatchQuery(List<String> list) {
    try {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      for (String query : list) {
        PreparedStatement queryStatement = conn.prepareStatement(query);
        queryStatement.execute();
        System.out.println(query);
      } 
      conn.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      } 
      return false;
    } 
  }
}
