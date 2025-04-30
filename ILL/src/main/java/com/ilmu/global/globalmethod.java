/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class globalmethod {
    private String Code = null;
    private String Desc = null;
    private String lblName = null;
    private String lblLibAdd1 = null;
    private String lblLibAdd2 = null;
    private String lblLibAdd3 = null;

    public String getCode() {
        return Handler.ifIsNull(this.Code);
    }

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public String getlblName() {
        return Handler.ifIsNull(this.lblName);
    }

    public String getlblLibAdd1() {
        return Handler.ifIsNull(this.lblLibAdd1);
    }

    public String getlblLibAdd2() {
        return Handler.ifIsNull(this.lblLibAdd2);
    }

    public String getlblLibAdd3() {
        return Handler.ifIsNull(this.lblLibAdd3);
    }

    public globalmethod(String Code, String Desc) {
        this.Code = Code;
        this.Desc = Desc;
    }

    public globalmethod(String lblName, String lblLibAdd1, String lblLibAdd2, String lblLibAdd3) {
        this.lblName = lblName;
        this.lblLibAdd1 = lblLibAdd1;
        this.lblLibAdd2 = lblLibAdd2;
        this.lblLibAdd3 = lblLibAdd3;
    }

    public static String LoadILLPatronCat() throws SQLException {
        String sSQLStmt = "SELECT GL99VALUE FROM GLFLAG2 WHERE UPPER(GL99FUNC) = UPPER('ILLPATRONCAT')";
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

    public static List<globalmethod> selectLLName() throws SQLException {
        ArrayList<globalmethod> list = new ArrayList<globalmethod>();
        String ILLPatronCat = globalmethod.LoadILLPatronCat();
        String query = "SELECT GL14PATR, GL14NAME FROM GLPATR WHERE UPPER(GL14CATE) = UPPER('" + ILLPatronCat + "') " + "ORDER BY GL14PATR ASC";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    globalmethod newbrnc = new globalmethod(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newbrnc);
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

    public static String GetTagValue(String vsControlNo, String vsTag, String vsSingleISBN) {
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        String sTable = globalmethod.getMarkTagTableName(vsTag);
        String[] tempArray = sTable.split("=");
        String sModule = tempArray[2].trim().substring(0, 2);
        sTable = tempArray[2].trim();
        if (!sTable.equals("")) {
            if (vsSingleISBN.equals("YES")) {
                if (dbtype.getDBType().equals("mssql")) {
                    sSQLStmt = "SELECT TOP 1 " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A'";
                }
                if (dbtype.getDBType().equals("oracle")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A' AND ROWNUM=1";
                }
                if (dbtype.getDBType().equals("mysql")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A' LIMIT 1";
                }
            } else if (vsTag.equals("260")) {
                if (dbtype.getDBType().equals("mssql")) {
                    sSQLStmt = "SELECT TOP 1 " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A'";
                }
                if (dbtype.getDBType().equals("oracle")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A' AND ROWNUM=1";
                }
                if (dbtype.getDBType().equals("mysql")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A' LIMIT 1";
                }
            } else {
                sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A'";
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

    public static List<globalmethod> DisplayLibraryDetails(String id) {
        ArrayList<globalmethod> list = new ArrayList<globalmethod>();
        String query = "SELECT GL14NAME,GL14OFFADD1,GL14OFFADD2,GL14OFFADD3 FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + id + "')";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    globalmethod patronAdd = new globalmethod(Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL14OFFADD1")), Handler.ifIsNull(rs.getString("GL14OFFADD2")), Handler.ifIsNull(rs.getString("GL14OFFADD3")));
                    list.add(patronAdd);
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
        return list;
    }

    public static List<globalmethod> LoadReferenceNo() throws SQLException {
        ArrayList<globalmethod> list = new ArrayList<globalmethod>();
        String query = "SELECT CI04REQUEST,CI04MATNO FROM CIOUTR WHERE CI04DTRECEIVED IS NULL AND CI04MATNO IS NOT NULL ";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    globalmethod newrefno = new globalmethod(rs.getString("CI04REQUEST"), rs.getString("CI04MATNO"));
                    list.add(newrefno);
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

    public static List<globalmethod> getCondition() {
        ArrayList<globalmethod> count = new ArrayList<globalmethod>();
        String query = "SELECT GL06COND AS CODE, GL06DESC AS DESCN FROM GLCOND ORDER BY GL06COND ASC";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    globalmethod countExsit = new globalmethod(rs.getString("CODE"), rs.getString("DESCN"));
                    count.add(countExsit);
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
        return count;
    }

    public static List<globalmethod> getItemCategory() {
        ArrayList<globalmethod> count = new ArrayList<globalmethod>();
        String query = "SELECT GL10ICAT AS CODE, GL10DESC AS DESCN FROM GLICAT ORDER BY GL10ICAT ASC";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    globalmethod countExsit = new globalmethod(rs.getString("CODE"), rs.getString("DESCN"));
                    count.add(countExsit);
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
        return count;
    }

    public static List<globalmethod> getLocation() {
        ArrayList<globalmethod> count = new ArrayList<globalmethod>();
        String query = "SELECT GL05LOCA AS CODE, GL05DESC AS DESCN FROM GLLOCA ORDER BY GL05LOCA ASC";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    globalmethod countExsit = new globalmethod(rs.getString("CODE"), rs.getString("DESCN"));
                    count.add(countExsit);
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
        return count;
    }

    public static List<globalmethod> getSMD() {
        ArrayList<globalmethod> count = new ArrayList<globalmethod>();
        String query = "  SELECT GL47SMD AS CODE, GL47DESC AS DESCN FROM GLSMD ORDER BY GL47SMD ASC";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    globalmethod countExsit = new globalmethod(rs.getString("CODE"), rs.getString("DESCN"));
                    count.add(countExsit);
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
        return count;
    }
}
