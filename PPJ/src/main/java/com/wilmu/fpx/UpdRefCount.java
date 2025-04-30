/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.global.database.DBConnection
 *  com.wilmu.utilities.Handler
 */
package com.wilmu.fpx;

import com.wilmu.global.database.DBConnection;
import com.wilmu.utilities.Handler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdRefCount {
    private String Value = null;

    public String getValue() {
        return Handler.chkIsNull((String)this.Value);
    }

    public UpdRefCount(String Value) {
        this.Value = Value;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String totalRetxn(String updref) {
        System.out.println("updref= " + updref);
        String query = "SELECT COUNT(*) AS TotalBill FROM RETRXN WHERE RE01UPDREF = ?";
        System.out.println("Query totalRetxn = " + query);
        String bill = null;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection connection = DBConnection.getConnection();
                try {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
                        preparedStatement.setString(1, updref);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            bill = Handler.chkIsNull((String)resultSet.getString("TotalBill"));
                        }
                    }
                    if (connection == null) return bill;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (connection == null) throw throwable;
                    connection.close();
                    throw throwable;
                }
                connection.close();
                return bill;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
}
