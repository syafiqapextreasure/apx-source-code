/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class AuditTrail {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    public static AuditTrail InsertAudit(String sModule, String vsCode, String vsRefNo, String user) throws UnknownHostException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
                LocalDateTime now = LocalDateTime.now();
                valueStr.put(String.valueOf(sModule) + "68ACTCODE", vsCode);
                valueStr.put(String.valueOf(sModule) + "68PATRONID", user);
                valueStr.put(String.valueOf(sModule) + "68DATE", date.format(now));
                valueStr.put(String.valueOf(sModule) + "68TIME", time.format(now));
                valueStr.put(String.valueOf(sModule) + "68REFER", vsRefNo);
                valueStr.put(String.valueOf(sModule) + "68TEMNID", Handler.getLocalIPAdd());
                System.out.println("SS" + sModule);
                String add = QueryBuilder.createInsertQuery(String.valueOf(sModule) + "AUDIT", valueStr, null, null);
                System.out.println("Audit" + add);
                boolean isSuccess = DBQuery.runQuery(add);
                System.out.println(isSuccess);
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
