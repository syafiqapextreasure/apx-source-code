/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.global.database.DBConnection
 */
package com.wilmu.fpx;

import com.wilmu.global.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertReBillAudit {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void addrebillaudit(String subsysId, String bankId, String bankName, String txnId, String txnTime, String sellerTxnTime, String orderNo, String exOrderNo, String status, String success, String amount, String ppjTransNo, String sapDocNo) throws IOException {
        try {
            c = DBConnection.getConnection();
            String sql = "INSERT INTO REBILLAUDIT (RE68subsysId, RE68bankId, RE68bankName, RE68txnId, RE68txnTime, RE68sellerTxnTime, RE68orderNo,RE68exOrderNo, RE68status, RE68sucess, RE68amount, RE68ppjTransNo, RE68sapDocNo) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, subsysId);
            if (bankId.equals("null")) {
                statement.setNull(2, 0);
            } else {
                statement.setString(2, bankId);
            }
            statement.setString(3, bankName);
            if (txnId.equals("null")) {
                statement.setNull(4, 0);
            } else {
                statement.setString(4, txnId);
            }
            if (txnTime.equals("null")) {
                statement.setNull(5, 0);
            } else {
                statement.setString(5, txnTime);
            }
            statement.setString(6, sellerTxnTime);
            if (orderNo.equals("null")) {
                statement.setNull(7, 0);
            } else {
                statement.setString(7, orderNo);
            }
            if (exOrderNo.equals("null")) {
                statement.setNull(8, 0);
            } else {
                statement.setString(8, exOrderNo);
            }
            if (status.equals("null")) {
                statement.setNull(9, 0);
            } else {
                statement.setString(9, status);
            }
            statement.setString(10, success);
            statement.setString(11, amount);
            statement.setString(12, ppjTransNo);
            if (sapDocNo.equals("null")) {
                statement.setNull(13, 0);
            } else {
                statement.setString(13, sapDocNo);
            }
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A contact was inserted with photo image.");
            }
            statement.close();
            c.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String toUrlPath(String path) {
        return path.indexOf(92) < 0 ? path : path.replace('\\', '/');
    }
}
