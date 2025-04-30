/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.global.database.DBQuery
 *  com.wilmu.global.database.UpdateQueryBuilder
 */
package com.wilmu.fpx;

import com.wilmu.global.database.DBQuery;
import com.wilmu.global.database.UpdateQueryBuilder;
import java.util.HashMap;

public class UpdatePayStatus {
    public static boolean updatePayStatus(String orderNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("RE02ORDERNO", orderNo);
        valueStr.put("RE02PAYSTAT", "1");
        String query = UpdateQueryBuilder.createUpdateQuery((String)"REBILL", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runSQLQuery((String)query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
