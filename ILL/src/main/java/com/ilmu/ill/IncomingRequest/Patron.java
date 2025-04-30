/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.IncomingRequest;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Patron {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    String errmessage = "";

    public String getErrMessage() {
        return this.errmessage;
    }

    public boolean CheckPatronExist(String vsPatron) {
        boolean tempCheckPatronExist;
        block13: {
            tempCheckPatronExist = false;
            String sSQLStmt = null;
            String sPatronCat = null;
            String iPatronIsInILLBorrowerCategory = null;
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    sSQLStmt = "SELECT * FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + vsPatron + "')";
                    ResultSet rsObj = stmt.executeQuery(sSQLStmt);
                    System.out.println("CheckPatronExist" + sSQLStmt);
                    if (!rsObj.next()) {
                        this.errmessage = "Invalid Requestor ID";
                        tempCheckPatronExist = false;
                        break block13;
                    }
                    sPatronCat = rsObj.getString("GL14CATE");
                    iPatronIsInILLBorrowerCategory = Patron.LoadILLPatronCat();
                    if (iPatronIsInILLBorrowerCategory.equals(sPatronCat)) {
                        this.errmessage = "Valid";
                        tempCheckPatronExist = true;
                        break block13;
                    }
                    this.errmessage = "Invalid Group Category";
                    tempCheckPatronExist = false;
                }
                catch (Exception e) {
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
        }
        return tempCheckPatronExist;
    }

    public static String LoadILLPatronCat() throws SQLException {
        String sSQLStmt = "SELECT GL99VALUE FROM GLFLAG2 WHERE UPPER(GL99FUNC) = UPPER('ILLPATRONCAT')";
        System.out.println("SQL LoadILLPatronCat" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL99VALUE"));
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
        return value;
    }
}
