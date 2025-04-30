/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.utilities.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QueryBuilder {
//    public static String createUpdateQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap, Map<String, String> conditionMap) {
//        Map.Entry<String, Object> pair;
//        Iterator<Map.Entry<String, Object>> it;
//        String query = "UPDATE " + table + " SET ";
//        if (map != null) {
//            it = map.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = '" + (String)pair.getValue() + "'";
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        if (intMap != null) {
//            if (map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            it = intMap.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = " + pair.getValue();
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        if (doubleMap != null) {
//            it = doubleMap.entrySet().iterator();
//            if (intMap != null || map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = " + pair.getValue();
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        query = String.valueOf(query) + " WHERE ";
//        it = conditionMap.entrySet().iterator();
//        while (it.hasNext()) {
//            pair = it.next();
//            query = String.valueOf(query) + pair.getKey() + " = '" + (String)pair.getValue() + "' ";
//            if (it.hasNext()) {
//                query = String.valueOf(query) + "AND";
//            }
//            query = String.valueOf(query) + " ";
//        }
//        return query;
//    }

	public static String createUpdateQuery(String table, Map<String, String> map, 
	        Map<String, Integer> intMap, Map<String, Double> doubleMap, 
	        Map<String, String> conditionMap) {
	    
	    if (table == null || table.trim().isEmpty()) {
	        throw new IllegalArgumentException("Table name cannot be null or empty");
	    }

	    StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");
	    
	    boolean needsComma = false;
	    
	    // Handle string fields
	    if (map != null) {
	        appendMapEntries(query, map, true);
	        needsComma = true;
	    }
	    
	    // Handle integer fields
	    if (intMap != null) {
	        if (needsComma) query.append(", ");
	        appendMapEntries(query, intMap, false);
	        needsComma = true;
	    }
	    
	    // Handle double fields
	    if (doubleMap != null) {
	        if (needsComma) query.append(", ");
	        appendMapEntries(query, doubleMap, false);
	    }
	    
	    // Add WHERE clause
	    query.append(" WHERE ");
	    appendConditions(query, conditionMap);
	    
	    return query.toString();
	}

//	private static void appendMapEntries(StringBuilder query, Map<?, ?> map, boolean isString) {
//	    if (map == null || map.isEmpty()) return;
//	    
//	    Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry<?, ?> entry = it.next();
//	        query.append(entry.getKey()).append(" = ");
//	        
//	        if (isString) {
//	            query.append("'").append(entry.getValue()).append("'");
//	        } else {
//	            query.append(entry.getValue());
//	        }
//	        
//	        if (it.hasNext()) {
//	            query.append(", ");
//	        }
//	    }
//	}
	
	private static <K, V> void appendMapEntries(StringBuilder query, Map<K, V> map, boolean isString) {
	    if (map == null || map.isEmpty()) {
	        return;
	    }

	    Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<K, V> entry = iterator.next();
	        K key = entry.getKey();
	        V value = entry.getValue();

	        if (key == null) {
	            continue;
	        }

	        query.append(key).append(" = ");
	        
	        if (isString) {
	            appendStringValue(query, value);
	        } else {
	            appendNumericValue(query, value);
	        }
	        
	        if (iterator.hasNext()) {
	            query.append(", ");
	        }
	    }
	}

	private static void appendStringValue(StringBuilder query, Object value) {
	    if (value == null) {
	        query.append("null");
	    } else {
	        query.append("'").append(value.toString().replace("'", "''")).append("'");
	    }
	}

	private static void appendNumericValue(StringBuilder query, Object value) {
	    if (value == null) {
	        query.append("null");
	    } else {
	        query.append(value);
	    }
	}

	private static void appendConditions(StringBuilder query, Map<String, String> conditionMap) {
	    if (conditionMap == null || conditionMap.isEmpty()) {
	        query.append("1=1");
	        return;
	    }

	    Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> entry = it.next();
	        query.append(entry.getKey())
	             .append(" = '")
	             .append(entry.getValue())
	             .append("'");
	        
	        if (it.hasNext()) {
	            query.append(" AND ");
	        }
	    }
	}
//    public static String createInsertQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap) {
//        Map.Entry<String, Object> pair;
//        Iterator<Map.Entry<String, Object>> it;
//        String query = "INSERT INTO " + table + "(";
//        String column = "";
//        String value = "";
//        String columnInt = "";
//        String valueInt = "";
//        String columnDouble = "";
//        String valueDouble = "";
//        if (table.equals("CTWORK") || table.equals("CTSUBJ") || table.equals("CTAUTH") || table.equals("CTCALL") || table.equals("CTINDX") || table.equals("CTPUBL") || table.equals("CTSERI") || table.equals("CTTITL") || table.equals("WEDOCM")) {
//            if (map != null) {
//                it = map.entrySet().iterator();
//                while (it.hasNext()) {
//                    pair = it.next();
//                    column = String.valueOf(column) + pair.getKey();
//                    value = String.valueOf(value) + "N'" + (String)pair.getValue() + "'";
//                    if (!it.hasNext()) continue;
//                    column = String.valueOf(column) + ", ";
//                    value = String.valueOf(value) + ", ";
//                }
//            }
//        } else if (map != null) {
//            it = map.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                column = String.valueOf(column) + pair.getKey();
//                value = pair.getValue() == null ? String.valueOf(value) + null : (((String)pair.getValue()).isEmpty() ? String.valueOf(value) + null : String.valueOf(value) + "'" + (String)pair.getValue() + "'");
//                if (!it.hasNext()) continue;
//                column = String.valueOf(column) + ", ";
//                value = String.valueOf(value) + ", ";
//            }
//        }
//        if (intMap != null) {
//            it = intMap.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                columnInt = String.valueOf(columnInt) + pair.getKey();
//                valueInt = String.valueOf(valueInt) + pair.getValue();
//                if (!it.hasNext()) continue;
//                columnInt = String.valueOf(columnInt) + ", ";
//                valueInt = String.valueOf(valueInt) + ", ";
//            }
//        }
//        if (doubleMap != null) {
//            it = doubleMap.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                columnDouble = String.valueOf(columnDouble) + pair.getKey();
//                valueDouble = String.valueOf(valueDouble) + pair.getValue();
//                if (!it.hasNext()) continue;
//                columnDouble = String.valueOf(columnDouble) + ", ";
//                valueDouble = String.valueOf(valueDouble) + ", ";
//            }
//        }
//        query = String.valueOf(query) + column;
//        if (intMap != null) {
//            if (map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            query = String.valueOf(query) + columnInt;
//        }
//        if (doubleMap != null) {
//            if (map != null || intMap != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            query = String.valueOf(query) + columnDouble;
//        }
//        query = String.valueOf(query) + ") VALUES(" + value;
//        if (intMap != null) {
//            if (map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            query = String.valueOf(query) + valueInt;
//        }
//        if (doubleMap != null) {
//            if (map != null || intMap != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            query = String.valueOf(query) + valueDouble;
//        }
//        query = String.valueOf(query) + ")";
//        System.out.println(query);
//        return query;
//    }
	
	public static String createInsertQuery(String table, Map<String, String> map, 
	        Map<String, Integer> intMap, Map<String, Double> doubleMap) {
	    
	    if (table == null || table.trim().isEmpty()) {
	        throw new IllegalArgumentException("Table name cannot be null or empty");
	    }

	    StringBuilder query = new StringBuilder("INSERT INTO ").append(table).append("(");
	    StringBuilder columns = new StringBuilder();
	    StringBuilder values = new StringBuilder();
	    
	    boolean isNCharTable = NCHAR_TABLES.contains(table);
	    boolean needsComma = false;
	    
	    // Handle string fields
	    if (map != null) {
	        appendStringMapEntries(columns, values, map, isNCharTable);
	        needsComma = true;
	    }
	    
	    // Handle integer fields
	    if (intMap != null) {
	        if (needsComma) {
	            columns.append(", ");
	            values.append(", ");
	        }
	        appendIntegerMapEntries(columns, values, intMap);
	        needsComma = true;
	    }
	    
	    // Handle double fields
	    if (doubleMap != null) {
	        if (needsComma) {
	            columns.append(", ");
	            values.append(", ");
	        }
	        appendDoubleMapEntries(columns, values, doubleMap);
	    }
	    
	    query.append(columns).append(") VALUES(").append(values).append(")");
	    
	    return query.toString();
	}

	private static void appendStringMapEntries(StringBuilder columns, StringBuilder values, 
	        Map<String, String> map, boolean isNChar) {
	    
	    if (map == null || map.isEmpty()) {
	        return;
	    }

	    Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, String> entry = iterator.next();
	        
	        if (columns.length() > 0) {
	            columns.append(", ");
	            values.append(", ");
	        }
	        
	        columns.append(entry.getKey());
	        
	        if (isNChar) {
	            values.append("N'").append(entry.getValue() != null ? entry.getValue().replace("'", "''") : "null").append("'");
	        } else {
	            if (entry.getValue() == null || entry.getValue().isEmpty()) {
	                values.append("null");
	            } else {
	                values.append("'").append(entry.getValue().replace("'", "''")).append("'");
	            }
	        }
	    }
	}

	private static void appendIntegerMapEntries(StringBuilder columns, StringBuilder values, 
	        Map<String, Integer> map) {
	    
	    if (map == null || map.isEmpty()) {
	        return;
	    }

	    Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, Integer> entry = iterator.next();
	        
	        if (columns.length() > 0) {
	            columns.append(", ");
	            values.append(", ");
	        }
	        
	        columns.append(entry.getKey());
	        values.append(entry.getValue() != null ? entry.getValue() : "null");
	    }
	}

	private static void appendDoubleMapEntries(StringBuilder columns, StringBuilder values, 
	        Map<String, Double> map) {
	    
	    if (map == null || map.isEmpty()) {
	        return;
	    }

	    Iterator<Map.Entry<String, Double>> iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry<String, Double> entry = iterator.next();
	        
	        if (columns.length() > 0) {
	            columns.append(", ");
	            values.append(", ");
	        }
	        
	        columns.append(entry.getKey());
	        values.append(entry.getValue() != null ? entry.getValue() : "null");
	    }
	}

	private static final Set<String> NCHAR_TABLES = new HashSet<>(Arrays.asList(
		    "CTWORK", "CTSUBJ", "CTAUTH", "CTCALL", "CTINDX", 
		    "CTPUBL", "CTSERI", "CTTITL", "WEDOCM"
		));

    public static String createDeleteQuery(String table, Map<String, String> conditionMap) {
        String query = "DELETE FROM " + table + " WHERE ";
        Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
            if (it.hasNext()) {
                query = String.valueOf(query) + "AND";
            }
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
                if (count != column.size()) {
                    query = String.valueOf(query) + ",";
                }
                query = String.valueOf(query) + " ";
                ++count;
            }
        } else {
            query = String.valueOf(query) + "* ";
        }
        query = String.valueOf(query) + "FROM " + table + " WHERE ";
        if (conditionMap != null) {
            Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> pair = it.next();
                query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
                if (it.hasNext()) {
                    query = String.valueOf(query) + "AND";
                }
                query = String.valueOf(query) + " ";
            }
        }
        return query;
    }

//    public static String createUpdateIntQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap, Map<String, Integer> conditionMap) {
//        Map.Entry<String, Object> pair;
//        Iterator<Map.Entry<String, Object>> it;
//        System.out.println("New");
//        String query = "UPDATE " + table + " SET ";
//        System.out.println(query);
//        if (map != null) {
//            it = map.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = '" + (String)pair.getValue() + "'";
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        if (intMap != null) {
//            if (map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            it = intMap.entrySet().iterator();
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = " + pair.getValue();
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        if (doubleMap != null) {
//            it = doubleMap.entrySet().iterator();
//            if (intMap != null || map != null) {
//                query = String.valueOf(query) + ", ";
//            }
//            while (it.hasNext()) {
//                pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = " + pair.getValue();
//                if (!it.hasNext()) continue;
//                query = String.valueOf(query) + ", ";
//            }
//        }
//        query = String.valueOf(query) + " WHERE ";
//        it = conditionMap.entrySet().iterator();
//        while (it.hasNext()) {
//            pair = it.next();
//            query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
//            if (it.hasNext()) {
//                query = String.valueOf(query) + "AND";
//            }
//            query = String.valueOf(query) + " ";
//        }
//        System.out.println(query);
//        return query;
//    }
    
    public static String createUpdateIntQuery(String table, Map<String, String> map, 
            Map<String, Integer> intMap, Map<String, Double> doubleMap, 
            Map<String, Integer> conditionMap) {
        
        if (table == null || table.trim().isEmpty()) {
            throw new IllegalArgumentException("Table name cannot be null or empty");
        }

        StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");
        
        boolean needsComma = false;
        
        // Handle string fields
        if (map != null) {
            appendStringMapEntries(query, map);
            needsComma = true;
        }
        
        // Handle integer fields
        if (intMap != null) {
            if (needsComma) query.append(", ");
            appendIntegerMapEntries(query, intMap);
            needsComma = true;
        }
        
        // Handle double fields
        if (doubleMap != null) {
            if (needsComma) query.append(", ");
            appendDoubleMapEntries(query, doubleMap);
        }
        
        // Add WHERE clause with integer conditions
        query.append(" WHERE ");
        appendIntegerConditions(query, conditionMap);
        
        return query.toString();
    }

    private static void appendStringMapEntries(StringBuilder query, Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            query.append(entry.getKey())
                 .append(" = '")
                 .append(entry.getValue() != null ? entry.getValue().replace("'", "''") : "null")
                 .append("'");
            
            if (iterator.hasNext()) {
                query.append(", ");
            }
        }
    }

    private static void appendIntegerMapEntries(StringBuilder query, Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return;
        }

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            query.append(entry.getKey())
                 .append(" = ")
                 .append(entry.getValue() != null ? entry.getValue() : "null");
            
            if (iterator.hasNext()) {
                query.append(", ");
            }
        }
    }

    private static void appendDoubleMapEntries(StringBuilder query, Map<String, Double> map) {
        if (map == null || map.isEmpty()) {
            return;
        }

        Iterator<Map.Entry<String, Double>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> entry = iterator.next();
            query.append(entry.getKey())
                 .append(" = ")
                 .append(entry.getValue() != null ? entry.getValue() : "null");
            
            if (iterator.hasNext()) {
                query.append(", ");
            }
        }
    }

    private static void appendIntegerConditions(StringBuilder query, Map<String, Integer> conditionMap) {
        if (conditionMap == null || conditionMap.isEmpty()) {
            query.append("1=1");
            return;
        }

        Iterator<Map.Entry<String, Integer>> iterator = conditionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            query.append(entry.getKey())
                 .append(" = ")
                 .append(entry.getValue());
            
            if (iterator.hasNext()) {
                query.append(" AND ");
            }
        }
    }
}
