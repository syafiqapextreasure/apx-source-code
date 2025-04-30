///*
// * Decompiled with CFR 0.152.
// */
//package com.ilmu.utilities.query;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class QueryBuilder {
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
//
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
//        if (table.equals("CTWORK") || table.equals("CTSUBJ") || table.equals("CTAUTH") || table.equals("CTCALL") || table.equals("CTINDX") || table.equals("CTPUBL") || table.equals("CTSERI") || table.equals("CTTITL") || table.equals("MPDATA")) {
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
//                value = ((String)pair.getValue()).isEmpty() ? String.valueOf(value) + null : String.valueOf(value) + "'" + (String)pair.getValue() + "'";
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
//        return query;
//    }
//
//    public static String createDeleteQuery(String table, Map<String, String> conditionMap) {
//        String query = "DELETE FROM " + table + " WHERE ";
//        Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> pair = it.next();
//            query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
//            if (it.hasNext()) {
//                query = String.valueOf(query) + "AND";
//            }
//            query = String.valueOf(query) + " ";
//        }
//        return query;
//    }
//
//    public static String createDeleteIntQuery(String table, Map<String, Integer> conditionMap) {
//        String query = "DELETE FROM " + table + " WHERE ";
//        Iterator<Map.Entry<String, Integer>> it = conditionMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Integer> pair = it.next();
//            query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
//            if (it.hasNext()) {
//                query = String.valueOf(query) + "AND";
//            }
//            query = String.valueOf(query) + " ";
//        }
//        return query;
//    }
//
//    public static String createSelectQuery(String table, List<String> column, Map<String, String> conditionMap) {
//        String query = "SELECT ";
//        if (column != null) {
//            int count = 1;
//            for (String s : column) {
//                query = String.valueOf(query) + s;
//                if (count != column.size()) {
//                    query = String.valueOf(query) + ",";
//                }
//                query = String.valueOf(query) + " ";
//                ++count;
//            }
//        } else {
//            query = String.valueOf(query) + "* ";
//        }
//        query = String.valueOf(query) + "FROM " + table + " WHERE ";
//        if (conditionMap != null) {
//            Iterator<Map.Entry<String, String>> it = conditionMap.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> pair = it.next();
//                query = String.valueOf(query) + pair.getKey() + " = '" + pair.getValue() + "' ";
//                if (it.hasNext()) {
//                    query = String.valueOf(query) + "AND";
//                }
//                query = String.valueOf(query) + " ";
//            }
//        }
//        return query;
//    }
//
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
//}
