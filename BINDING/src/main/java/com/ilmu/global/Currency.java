/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Currency {
    private String forexCode = null;
    private String forexDesc = null;
    private String pubRate = null;
    private String bankRate = null;
    private String rate = null;

    public String getforexCode() {
        return Handler.ifIsNull(this.forexCode);
    }

    public String getforexDesc() {
        return Handler.ifIsNull(this.forexDesc);
    }

    public String getpubRate() {
        return Handler.ifIsNull(this.pubRate);
    }

    public String getbankRate() {
        return Handler.ifIsNull(this.bankRate);
    }

    public String getrate() {
        return Handler.ifIsNull(this.rate);
    }

    public Currency(String forexCode, String forexDesc) {
        this.forexCode = forexCode;
        this.forexDesc = forexDesc;
    }

    public Currency(String rate) {
        this.rate = rate;
    }

    public Currency(String forexCode, String forexDesc, String pubRate, String bankRate) {
        this.forexCode = forexCode;
        this.forexDesc = forexDesc;
        this.pubRate = pubRate;
        this.bankRate = bankRate;
    }

    public static List<Currency> getCurrencyPDRATE(String currency) {
        ArrayList<Currency> valuePDRATE = new ArrayList<Currency>();
        String query = "SELECT GL24PRATE FROM GLFORX WHERE GL24FOREX = '" + currency + "' ";
        System.out.println("getCurrencyPDRATE = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Currency newvaluePDRATE = new Currency(Handler.ifIsNull(Handler.decimalConversion(rs.getString("GL24PRATE"))));
                    valuePDRATE.add(newvaluePDRATE);
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
        return valuePDRATE;
    }
}
