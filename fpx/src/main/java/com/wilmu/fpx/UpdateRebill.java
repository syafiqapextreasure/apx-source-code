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

public class UpdateRebill {
    public static boolean updateReBill(String orderNo, String txnId, String txnTime, String txnReference, String status, String bankName, String url) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("RE02ORDERNO", orderNo);
        if (!txnId.equals("")) {
            valueStr.put("RE02TXNID", txnId);
        }
        valueStr.put("RE02TXNTIME", txnTime);
        valueStr.put("RE02TXNREFERENCE", txnReference);
        valueStr.put("RE02STATUS", status);
        valueStr.put("RE02BANKNAME", bankName);
        valueStr.put("RE02URL", url);
        String query = UpdateQueryBuilder.createUpdateQuery((String)"REBILL", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runSQLQuery((String)query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
