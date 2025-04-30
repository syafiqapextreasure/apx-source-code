/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.ISBD;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetTitle {
    public static String GetTagValue(String vsControlNo, String vsTag) {
        String title = null;
        int getTotalBibStat = GetTitle.getBibStat(vsControlNo);
        if (getTotalBibStat == 0) {
            String sTable = GetTitle.getMarkTagTableName(vsTag);
            String[] tempArray = sTable.split("=");
            String sModule = tempArray[2].trim().substring(0, 2);
            sTable = tempArray[2].trim();
            title = GetTitle.GetTitles(vsControlNo, vsTag, sModule, sTable);
        } else {
            title = GetTitle.getSrowCTwork(vsControlNo, vsTag);
            title = "*" + title;
        }
        return title;
    }

    public static int getBibStat(String sControlNo) {
        String query = "SELECT COUNT(*) AS TOTAL FROM CTMATM WHERE CT02MATNO = '" + sControlNo + "' " + "AND CT02STATUS = 'T'";
        int total = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    total = Integer.parseInt(rs.getString("TOTAL"));
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
        return total;
    }

    public static String getSrowCTwork(String sControlNo, String sTag) {
        String repalceMatno = sControlNo.replaceFirst("0", "A");
        String query = "SELECT CT04RAW FROM CTWORK WHERE CT04TAG = '" + sTag + "' AND CT04MATNO = '" + repalceMatno + "'";
        String ctwork = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation(sTag);
                while (rs.next()) {
                    ctwork = Handler.getSubfield(Handler.ifIsNull(rs.getString("CT04RAW")), isbd);
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
        return ctwork;
    }

    public static String getMarkTagTableName(String marcTag) {
        String TABNAME;
        String CATTITLTAG;
        block12: {
            String query = "Select GL99CATTITLTAG, GL17TABNAME From GLFLAG, GLTAGP Where GL17MARC = 'U' And GL17TAG = '" + marcTag + "'";
            CATTITLTAG = null;
            TABNAME = null;
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        CATTITLTAG = Handler.ifIsNull(rs.getString("GL99CATTITLTAG"));
                        TABNAME = Handler.ifIsNull(rs.getString("GL17TABNAME"));
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
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "=" + CATTITLTAG + " =" + TABNAME;
    }

    public static String GetTitles(String vsControlNo, String vsTag, String sModule, String sTable) {
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        if (vsTag.equals("100")) {
            vsTag = "'100', '110', '111','130'";
        } else if (vsTag.equals("400")) {
            vsTag = "'400', '490'";
        } else if (vsTag.equals("020")) {
            vsTag = "'020', '022'";
        }
        if (!sTable.equals("")) {
            if (DBConnection.getDBType().equals("mssql")) {
                sSQLStmt = "SELECT TOP 1 " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN (" + vsTag + ") " + "AND  " + sModule + "06STATUS = 'A'";
            }
            if (DBConnection.getDBType().equals("oracle")) {
                sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN (" + vsTag + ") " + "AND  " + sModule + "06STATUS = 'A' AND ROWNUM=1";
            }
            if (DBConnection.getDBType().equals("mysql")) {
                sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN (" + vsTag + ") " + "AND  " + sModule + "06STATUS = 'A' LIMIT 1";
            }
        }
        String getpoiter = null;
        String getsrow = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                List<ISBD> isbd = ISBD.getPunctuation(vsTag);
                while (rs.next()) {
                    getpoiter = Handler.ifIsNull(rs.getString(String.valueOf(sModule) + "05POINTER"));
                    getsrow = Handler.getSubfield(Handler.ifIsNull(rs.getString(String.valueOf(sModule) + "05SRAW")), isbd);
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
        return getsrow;
    }
}
