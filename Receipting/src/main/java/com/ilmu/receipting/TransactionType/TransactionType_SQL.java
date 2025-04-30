/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.TransactionType;

public class TransactionType_SQL {
    public static String ListTransactionType() throws Exception {
        String sql = "SELECT GL38TXCD, GL38DESC, GL38TYPE, GL38MODE FROM GLTRXC ORDER BY GL38TXCD";
        return sql;
    }

    public static String ListTransaction() throws Exception {
        String sql = "SELECT GL38TXCD, GL38DESC, GL38TYPE, GL38MODE FROM GLTRXC WHERE GL38TYPE IN ('D', 'J') AND GL38MODE='D' ORDER BY GL38TXCD";
        return sql;
    }

    public static String GetTransactionType(String code) throws Exception {
        String sql = "SELECT GL38TXCD, GL38DESC, GL38TYPE, GL38MODE FROM GLTRXC WHERE GL38TXCD='" + code + "'";
        return sql;
    }
}
