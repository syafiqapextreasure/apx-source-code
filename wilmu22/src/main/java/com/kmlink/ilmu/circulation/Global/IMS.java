/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Global;

import com.kmlink.ilmu.circulation.utilities.query.DBQuery;
import com.kmlink.ilmu.circulation.utilities.query.QueryBuilder;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class IMS {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    public static boolean chkIfExistIMS() {
        String sql = "SELECT * FROM GLFLAG2 WHERE GL99FUNC='IMS'";
        boolean success = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (!rsObj.getString("GL99VALUE").equals("Y")) continue;
                    success = true;
                }
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
        return success;
    }

    public static IMS IMS_Fines(String IMS04ACCNO, String IMS04REFNO, String IMS04PATR, BigDecimal IMS04AMOUNT, String IMS04TRXCODE, String IMS04DATE, String IMS04TRXCATE, String IMS04FLAG) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        valueStr.put("IMS04ACCNO", IMS04ACCNO);
        valueStr.put("IMS04REFNO", IMS04REFNO);
        valueStr.put("IMS04PATR", IMS04PATR);
        valueDouble.put("IMS04AMOUNT", Double.parseDouble(IMS04AMOUNT.toString()));
        valueStr.put("IMS04TRXCODE", IMS04TRXCODE);
        valueStr.put("IMS04DATE", IMS04DATE);
        valueStr.put("IMS04TRXCATE", IMS04TRXCATE);
        valueStr.put("IMS04FLAG", IMS04FLAG);
        String query = QueryBuilder.createInsertQuery("IMS_FINES", valueStr, null, valueDouble);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }
}
