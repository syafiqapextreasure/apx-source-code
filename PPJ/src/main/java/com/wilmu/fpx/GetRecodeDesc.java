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

public class GetRecodeDesc {
    private String Value = null;

    public String getValue() {
        return Handler.chkIsNull((String)this.Value);
    }

    public GetRecodeDesc(String Value) {
        this.Value = Value;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String re01code(String recode) {
        System.out.println("orderno= " + recode);
        String query = "SELECT GL38DESC FROM GLTRXC WHERE GL38TXCD = ?";
        System.out.println("Query GetRecodeDesc = " + query);
        String remark = null;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection connection = DBConnection.getConnection();
                try {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
                        preparedStatement.setString(1, recode);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            remark = Handler.chkIsNull((String)resultSet.getString("GL38DESC"));
                        }
                    }
                    if (connection == null) return remark;
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
                return remark;
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
        return remark;
    }
}
