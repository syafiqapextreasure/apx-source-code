package com.kmlink.ilmu.circulation.Global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Glnumb {
  private static Connection c = null;
  
  private static Statement stmt = null;
  
  private static ResultSet rs = null;
  
  private int GL98VALUE = 0;
  
  private String GL98FUNC = null;
  
  public Glnumb(int GL98VALUE) {
    this.GL98VALUE = GL98VALUE;
  }
  
  public int getGL98VALUE() {
    return this.GL98VALUE;
  }
  
  public String getGL98FUNC() {
    return this.GL98FUNC;
  }
  
  public static String GetCurrBuffPoint() {
    String result = "";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'BUFPOINT'";
    System.out.println(sql);
    try {
      connection = DBConnection.getConnection();
      ps = connection.prepareCall(sql);
      rs = ps.executeQuery();
      if (rs.next())
        result = rs.getString("GL98VALUE"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return result;
  }
  
  public static int GetBuffPoint(String columnName) {
    int result = 0;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + 
      columnName + "'";
    System.out.println(sql);
    try {
      connection = DBConnection.getConnection();
      ps = connection.prepareCall(sql);
      rs = ps.executeQuery();
      if (rs.next())
        result = Integer.parseInt(rs.getString("GL98VALUE")); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return result;
  }
  
  public static Glnumb getGL98VALUE(String columnName) {
    updateNumb(columnName);
    String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = '" + 
      columnName + "'";
    Glnumb result = null;
    try {
      c = DBConnection.getConnection();
      stmt = c.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next())
        result = new Glnumb(Integer.parseInt(rs.getString("GL98VALUE"))); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        c.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return result;
  }
  
  public static void updateNumb(String GL98FUNC) {
    String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + GL98FUNC + "'";
    System.out.println(query);
    try {
      c = DBConnection.getConnection();
      PreparedStatement delete = c.prepareStatement(query);
      delete.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        c.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public static void incHits(String GL98FUNC) {
    String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+1 WHERE GL98FUNC = '" + GL98FUNC + "'";
    System.out.println(query);
    try {
      c = DBConnection.getConnection();
      PreparedStatement delete = c.prepareStatement(query);
      delete.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static void updateBufPoint(int pointer) {
    String query = "UPDATE GLNUMB SET GL98VALUE=GL98VALUE+'" + pointer + "' WHERE GL98FUNC = BUFPOINT";
    System.out.println(query);
    try {
      c = DBConnection.getConnection();
      PreparedStatement delete = c.prepareStatement(query);
      delete.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  
  public static String GetCurrentAccessionNo() {
    String result = "";
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'ACCNO'";
    System.out.println(sql);
    try {
      connection = DBConnection.getConnection();
      ps = connection.prepareCall(sql);
      rs = ps.executeQuery();
      if (rs.next())
        result = rs.getString("GL98VALUE"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return result;
  }
}
