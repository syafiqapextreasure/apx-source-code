/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.utilities.query.DBQuery;
import com.kmlink.ilmu.circulation.utilities.query.QueryBuilder;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ILL {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    public static String getILLFlag() {
        String result = "";
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='ILLPATRONCAT'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    result = rsObj.getString("GL99VALUE");
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
        return result;
    }

    public static boolean chkILLModExist() {
        boolean exist = false;
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='ILLMODULE'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    exist = rsObj.getString("GL99VALUE").equals("Y");
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
        return exist;
    }

    public static String getAccessionStat(String accession) {
        String status = "";
        String sql = "SELECT CT03STAT FROM CTDOCM WHERE CT03DOCNO='" + accession + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    status = rsObj.getString("CT03STAT");
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
        return status;
    }

    public static String getPatrID(String accession) {
        String patron = "";
        String sql = "SELECT CT03PATR FROM CTDOCM WHERE CT03DOCNO='" + accession + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    patron = rsObj.getString("CT03PATR");
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
        return patron;
    }

    public static boolean getchkILLAccession(String accession) {
        boolean exist = false;
        String sql = "SELECT Count(*) as Count FROM ciinco WHERE CI01ACCESSION='" + accession + "' AND CI01STAT='N'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    exist = Integer.parseInt(rsObj.getString("Count")) > 0;
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
        return exist;
    }

    public static boolean getchkILLDischargeAcc(String accession) {
        boolean exist = false;
        String sql = "SELECT Count(*) as Count FROM ciinco WHERE CI01ACCESSION='" + accession + "' AND CI01STAT='P'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    exist = Integer.parseInt(rsObj.getString("Count")) > 0;
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
        return exist;
    }

    public static boolean updateStatus(String accession) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI01ACCESSION", accession);
        valueStr.put("CI01STAT", "P");
        String query = QueryBuilder.createUpdateQuery("CIINCO", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updateDischargingStat(String accession) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI01ACCESSION", accession);
        valueStr.put("CI01STAT", "R");
        String query = QueryBuilder.createUpdateQuery("CIINCO", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
