/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentMode {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String code;
    private String description;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static List<PaymentMode> getPaymentCode() {
        ArrayList<PaymentMode> list = new ArrayList<PaymentMode>();
        String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE='P'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PaymentMode paymentMode = new PaymentMode(rs.getString("CODE"), rs.getString("DESCRIPTION"));
                    list.add(paymentMode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
