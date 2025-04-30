package com.kmlink.ilmu.shared.utilities.query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QueryBuilder {
  public static String createUpdateQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap, Map<String, String> conditionMap) {
    String query = "UPDATE " + table + " SET ";
    if (map != null) {
      Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, String> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = '" + (String)pair.getValue() + "'";
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    if (intMap != null) {
      if (map != null)
        query = String.valueOf(query) + ", "; 
      Iterator<Map.Entry<String, Integer>> iterator = intMap.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, Integer> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = " + pair.getValue();
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    if (doubleMap != null) {
      Iterator<Map.Entry<String, Double>> iterator = doubleMap.entrySet().iterator();
      if (intMap != null || map != null)
        query = String.valueOf(query) + ", "; 
      while (iterator.hasNext()) {
        Map.Entry<String, Double> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = " + pair.getValue();
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    query = String.valueOf(query) + " WHERE ";
    Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> pair = it.next();
      query = String.valueOf(query) + (String)pair.getKey() + " = '" + (String)pair.getValue() + "' ";
      if (it.hasNext())
        query = String.valueOf(query) + "AND"; 
      query = String.valueOf(query) + " ";
    } 
    return query;
  }
  
  public static String createInsertQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap) {
    String query = "INSERT INTO " + table + "(";
    String column = "";
    String value = "";
    String columnInt = "";
    String valueInt = "";
    String columnDouble = "";
    String valueDouble = "";
    if (table.equals("CTWORK") || table.equals("CTSUBJ") || table.equals("CTAUTH") || 
      table.equals("CTCALL") || table.equals("CTINDX") || table.equals("CTPUBL") || 
      table.equals("CTSERI") || table.equals("CTTITL") || table.equals("MPDATA")) {
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
    return query;
  }
  
  public static String createDeleteQuery(String table, Map<String, String> conditionMap) {
    String query = "DELETE FROM " + table + " WHERE ";
    Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> pair = it.next();
      query = String.valueOf(query) + (String)pair.getKey() + " = '" + (String)pair.getValue() + "' ";
      if (it.hasNext())
        query = String.valueOf(query) + "AND"; 
      query = String.valueOf(query) + " ";
    } 
    return query;
  }
  
  public static String createSelectQuery(String table, List<String> column, Map<String, String> conditionMap) {
    String query = "SELECT ";
    if (column != null) {
      int count = 1;
      for (String s : column) {
        query = String.valueOf(query) + s;
        if (count != column.size())
          query = String.valueOf(query) + ","; 
        query = String.valueOf(query) + " ";
        count++;
      } 
    } else {
      query = String.valueOf(query) + "* ";
    } 
    query = String.valueOf(query) + "FROM " + table + " WHERE ";
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
    return query;
  }
  
  public static String createUpdateIntQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap, Map<String, Integer> conditionMap) {
    System.out.println("New");
    String query = "UPDATE " + table + " SET ";
    System.out.println(query);
    if (map != null) {
      Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, String> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = '" + (String)pair.getValue() + "'";
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    if (intMap != null) {
      if (map != null)
        query = String.valueOf(query) + ", "; 
      Iterator<Map.Entry<String, Integer>> iterator = intMap.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, Integer> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = " + pair.getValue();
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    if (doubleMap != null) {
      Iterator<Map.Entry<String, Double>> iterator = doubleMap.entrySet().iterator();
      if (intMap != null || map != null)
        query = String.valueOf(query) + ", "; 
      while (iterator.hasNext()) {
        Map.Entry<String, Double> pair = iterator.next();
        query = String.valueOf(query) + (String)pair.getKey() + " = " + pair.getValue();
        if (iterator.hasNext())
          query = String.valueOf(query) + ", "; 
      } 
    } 
    query = String.valueOf(query) + " WHERE ";
    Iterator<Map.Entry<String, Integer>> it = conditionMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, Integer> pair = it.next();
      query = String.valueOf(query) + (String)pair.getKey() + " = '" + pair.getValue() + "' ";
      if (it.hasNext())
        query = String.valueOf(query) + "AND"; 
      query = String.valueOf(query) + " ";
    } 
    System.out.println(query);
    return query;
  }
  
  public static String createString(Map<String, String> conditionMap) {
    Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
    String marc_record = "";
    while (it.hasNext()) {
      Map.Entry<String, String> pair = it.next();
      if (((String)pair.getKey()).equals("Tag"))
        marc_record = String.valueOf(marc_record) + "<td>" + (String)pair.getValue() + "</td>"; 
      if (((String)pair.getKey()).equals("Indi1"))
        marc_record = String.valueOf(marc_record) + "<td>" + (String)pair.getValue() + "</td>"; 
      if (((String)pair.getKey()).equals("Indi2"))
        marc_record = String.valueOf(marc_record) + "<td>" + (String)pair.getValue() + "</td>"; 
      if (((String)pair.getKey()).equals("Data"))
        marc_record = String.valueOf(marc_record) + "<td>" + (String)pair.getValue() + "</td>"; 
    } 
    return marc_record;
  }
}
