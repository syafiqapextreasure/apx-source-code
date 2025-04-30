/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.util.HashMap;
import java.util.Map;

public class TableName {
    private static Map<String, String> dbName = new HashMap(){
        private static final long serialVersionUID = 1L;
    };

    public static String get(String tableName) {
        return dbName.get(tableName);
    }
}
