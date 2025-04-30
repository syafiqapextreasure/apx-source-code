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

public class bookingRoomDesc {
    private String Value = null;

    public String getValue() {
        return Handler.chkIsNull((String)this.Value);
    }

    public bookingRoomDesc(String Value) {
        this.Value = Value;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String roomdesc(String bilno) {
        System.out.println("orderno= " + bilno);
        String query = "SELECT T2.RE01BILLNO, T2.RE01BOOKING, T2.RE01REFER, T3.ROOM_ID AS ROOMID, SUBSTRING(T4.content,1,100)  AS ROOMDESC FROM REBILL T1, RETRXN T2, MRBS_BOOKINGS T3, MRBS_MULTI_LANG T4 WHERE T2.RE01BILLNO = ? AND T2.RE01BILLNO = T1.RE02BILLNO AND T2.RE01BOOKING = T3.id AND T4.MODEL = 'pjRoom' AND T4.FOREIGN_ID = T3.ROOM_ID AND T4.FIELD = 'title'";
        System.out.println("Query bookingRoomDesc = " + query);
        String remark = null;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection connection = DBConnection.getConnection();
                try {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
                        preparedStatement.setString(1, bilno);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            remark = "Booking No: " + Handler.chkIsNull((String)(String.valueOf(resultSet.getString("ROOMID")) + " - " + Handler.chkIsNull((String)resultSet.getString("ROOMDESC"))));
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
