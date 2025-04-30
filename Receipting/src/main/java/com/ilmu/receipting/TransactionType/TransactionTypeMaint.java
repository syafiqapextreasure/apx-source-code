/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.TransactionType;

import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.receipting.TransactionType.TransactionType_SQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeMaint {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL38TXCD = null;
    private String GL38DESC = null;
    private String GL38TYPE = null;
    private String GL38MODE = null;

    public TransactionTypeMaint(String GL38TXCD, String GL38DESC, String GL38TYPE, String GL38MODE) {
        this.GL38TXCD = GL38TXCD;
        this.GL38DESC = GL38DESC;
        this.GL38TYPE = GL38TYPE;
        this.GL38MODE = GL38MODE;
    }

    public String getGL38TXCD() {
        return this.GL38TXCD;
    }

    public String getGL38DESC() {
        return this.GL38DESC;
    }

    public String getGL38TYPE() {
        return this.GL38TYPE;
    }

    public String getGL38MODE() {
        return this.GL38MODE;
    }

    public static List<TransactionTypeMaint> ListTranscType() throws Exception {
        ArrayList<TransactionTypeMaint> list = new ArrayList<TransactionTypeMaint>();
        String sql = TransactionType_SQL.ListTransaction();
        System.out.println("SQL" + sql);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    TransactionTypeMaint newSE01_SearchByMatno = new TransactionTypeMaint(rs.getString("GL38TXCD"), rs.getString("GL38DESC"), rs.getString("GL38TYPE"), rs.getString("GL38MODE"));
                    list.add(newSE01_SearchByMatno);
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

    public static TransactionTypeMaint GetTranscType(String code) throws Exception {
        StringBuilder query = new StringBuilder();
        String sql = TransactionType_SQL.GetTransactionType(code);
        query.append(sql);
        TransactionTypeMaint updateAccMaint = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query.toString());
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    updateAccMaint = new TransactionTypeMaint(rs.getString("GL38TXCD"), rs.getString("GL38DESC"), rs.getString("GL38TYPE"), rs.getString("GL38MODE"));
                    System.out.println("Currency" + rs.getString("CT03FOREX"));
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
        return updateAccMaint;
    }
}
