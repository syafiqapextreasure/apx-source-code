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

public class SelectStatement {
    private String Code = null;
    private String Desc = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public SelectStatement(String Code, String Desc) {
        this.Code = Code;
        this.Desc = Desc;
    }

    public static List<SelectStatement> selectFundAccount() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT FA01FUND, FA01DESC FROM FAMAST  ORDER BY FA01SEQC ";
        System.out.println("query selectFundAccount = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("FA01FUND"), rs.getString("FA01DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }

    public static List<SelectStatement> selectGSubj() {
        ArrayList<SelectStatement> list = new ArrayList<SelectStatement>();
        String query = "SELECT GL54SUBJSTA, GL54DESC FROM GLSUBJ ORDER BY GL54SUBJSTA";
        System.out.println("query selectFundAccount = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SelectStatement newFundAccount = new SelectStatement(rs.getString("GL54SUBJSTA"), rs.getString("GL54DESC"));
                    list.add(newFundAccount);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return list;
    }
}
