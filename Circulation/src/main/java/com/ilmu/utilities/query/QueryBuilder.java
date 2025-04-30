package com.ilmu.utilities.query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QueryBuilder {
    private static final String[] NCHAR_TABLES = {
        "CTWORK", "CTSUBJ", "CTAUTH", "CTCALL", "CTINDX", 
        "CTPUBL", "CTSERI", "CTTITL", "WEDOCM"
    };

    public static String createUpdateQuery(String table, Map<String, String> map, 
            Map<String, Integer> intMap, Map<String, Double> doubleMap, 
            Map<String, String> conditionMap) {
        StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");
        
        // Handle string values
        if (map != null) {
            appendUpdateValues(query, map, true);
        }
        
        // Handle integer values
        if (intMap != null) {
            if (map != null) {
                query.append(", ");
            }
            appendUpdateValues(query, intMap, false);
        }
        
        // Handle double values
        if (doubleMap != null) {
            if (intMap != null || map != null) {
                query.append(", ");
            }
            appendUpdateValues(query, doubleMap, false);
        }
        
        // Add WHERE clause
        query.append(" WHERE ");
        appendConditions(query, conditionMap);
        
        return query.toString();
    }

    public static String createInsertQuery(String table, Map<String, String> map, 
            Map<String, Integer> intMap, Map<String, Double> doubleMap) {
        StringBuilder query = new StringBuilder("INSERT INTO ").append(table).append("(");
        StringBuilder values = new StringBuilder(") VALUES(");
        
        boolean isNCharTable = isNCharTable(table);
        
        // Handle string values
        if (map != null) {
            appendInsertColumnsAndValues(query, values, map, isNCharTable);
        }
        
        // Handle integer values
        if (intMap != null) {
            if (map != null) {
                query.append(", ");
                values.append(", ");
            }
            appendInsertColumnsAndValues(query, values, intMap, false);
        }
        
        // Handle double values
        if (doubleMap != null) {
            if (map != null || intMap != null) {
                query.append(", ");
                values.append(", ");
            }
            appendInsertColumnsAndValues(query, values, doubleMap, false);
        }
        
        return query.append(values).append(")").toString();
    }

    public static String createDeleteQuery(String table, Map<String, String> conditionMap) {
        StringBuilder query = new StringBuilder("DELETE FROM ").append(table).append(" WHERE ");
        appendConditions(query, conditionMap);
        return query.toString();
    }

    public static String createSelectQuery(String table, List<String> column, 
            Map<String, String> conditionMap) {
        StringBuilder query = new StringBuilder("SELECT ");
        
        if (column != null && !column.isEmpty()) {
            query.append(String.join(", ", column));
        } else {
            query.append("*");
        }
        
        query.append(" FROM ").append(table).append(" WHERE ");
        appendConditions(query, conditionMap);
        
        return query.toString();
    }

    public static String createUpdateIntQuery(String table, Map<String, String> map, 
            Map<String, Integer> intMap, Map<String, Double> doubleMap, 
            Map<String, Integer> conditionMap) {
        StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");
        
        // Handle string values
        if (map != null) {
            appendUpdateValues(query, map, true);
        }
        
        // Handle integer values
        if (intMap != null) {
            if (map != null) {
                query.append(", ");
            }
            appendUpdateValues(query, intMap, false);
        }
        
        // Handle double values
        if (doubleMap != null) {
            if (intMap != null || map != null) {
                query.append(", ");
            }
            appendUpdateValues(query, doubleMap, false);
        }
        
        // Add WHERE clause with integer conditions
        query.append(" WHERE ");
        appendIntConditions(query, conditionMap);
        
        return query.toString();
    }

    private static boolean isNCharTable(String table) {
        for (String ncharTable : NCHAR_TABLES) {
            if (ncharTable.equals(table)) {
                return true;
            }
        }
        return false;
    }

    private static <T> void appendUpdateValues(StringBuilder query, Map<String, T> map, boolean isString) {
        Iterator<Map.Entry<String, T>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> pair = it.next();
            query.append(pair.getKey()).append(" = ");
            if (isString) {
                query.append("'").append(pair.getValue()).append("'");
            } else {
                query.append(pair.getValue());
            }
            if (it.hasNext()) {
                query.append(", ");
            }
        }
    }

    private static <T> void appendInsertColumnsAndValues(StringBuilder columns, 
            StringBuilder values, Map<String, T> map, boolean useNChar) {
        Iterator<Map.Entry<String, T>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> pair = it.next();
            columns.append(pair.getKey());
            
            T value = pair.getValue();
            if (value == null) {
                values.append("null");
            } else if (value instanceof String) {
                String strValue = (String) value;
                if (strValue.isEmpty()) {
                    values.append("null");
                } else {
                    values.append(useNChar ? "N'" : "'")
                          .append(strValue)
                          .append("'");
                }
            } else {
                values.append(value);
            }
            
            if (it.hasNext()) {
                columns.append(", ");
                values.append(", ");
            }
        }
    }

    private static void appendConditions(StringBuilder query, Map<String, String> conditionMap) {
        if (conditionMap != null) {
            Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> pair = it.next();
                query.append(pair.getKey())
                     .append(" = '")
                     .append(pair.getValue())
                     .append("'");
                if (it.hasNext()) {
                    query.append(" AND ");
                }
            }
        }
    }

    private static void appendIntConditions(StringBuilder query, Map<String, Integer> conditionMap) {
        if (conditionMap != null) {
            Iterator<Map.Entry<String, Integer>> it = conditionMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> pair = it.next();
                query.append(pair.getKey())
                     .append(" = ")
                     .append(pair.getValue());
                if (it.hasNext()) {
                    query.append(" AND ");
                }
            }
        }
    }
}