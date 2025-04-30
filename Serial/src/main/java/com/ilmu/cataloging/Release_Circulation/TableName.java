/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Release_Circulation;

import java.util.HashMap;
import java.util.Map;

public class TableName {
    private static Map<String, String> dbName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("invoice", "ACINVO");
            this.put("order", "ACORDD");
            this.put("vendor", "GLVEND");
            this.put("errorInSupply", "GLEISP");
            this.put("index", "CTINDX");
            this.put("pointer", "CTPONT");
            this.put("document", "CTDOCM");
            this.put("patrDetails", "GLLIBR");
        }
    };

    public static String get(String tableName) {
        return dbName.get(tableName);
    }
}
