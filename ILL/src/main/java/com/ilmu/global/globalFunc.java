/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Encrypter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class globalFunc {
    private String Name = null;
    private String rsDocumentType = null;
    private String rsVolume = null;
    private String rsIssue = null;
    private String rsPageNumber = null;

    public String getName() {
        return Handler.ifIsNull(this.Name);
    }

    public String getrsDocumentType() {
        return Handler.ifIsNull(this.rsDocumentType);
    }

    public String getrsVolume() {
        return Handler.ifIsNull(this.rsVolume);
    }

    public String getrsIssue() {
        return Handler.ifIsNull(this.rsIssue);
    }

    public String getrsPageNumber() {
        return Handler.ifIsNull(this.rsPageNumber);
    }

    public globalFunc(String Name) {
        this.Name = Name;
    }

    public globalFunc(String rsDocumentType, String rsVolume, String rsIssue, String rsPageNumber) {
        this.rsDocumentType = rsDocumentType;
        this.rsVolume = rsVolume;
        this.rsIssue = rsIssue;
        this.rsPageNumber = rsPageNumber;
    }

    public static List<globalFunc> libname() {
        ArrayList<globalFunc> list = new ArrayList<globalFunc>();
        String query = "SELECT GL28NAME FROM GLLIBR";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sLibName = Handler.ifIsNull(rs.getString("GL28NAME"));
                    String getLibName = Encrypter.encrypt(sLibName);
                    globalFunc libname = new globalFunc(getLibName);
                    list.add(libname);
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

    public static globalFunc GetRequestDetails(String vsReferenceNo) {
        globalFunc reqDetail;
        block27: {
            reqDetail = null;
            String sql = "SELECT CI04DOTYPE,CI04VOLUME,CI04ISSUES, CI04PAGENO FROM CIOUTR WHERE CI04REQUEST = '" + vsReferenceNo + "'";
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            String dotyype = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) break block27;
                    switch (dotyype = Handler.ifIsNull(rs.getString("CI04DOTYPE"))) {
                        case "01": {
                            dotyype = "Book";
                            break;
                        }
                        case "02": {
                            dotyype = "Book Chapter";
                            break;
                        }
                        case "03": {
                            dotyype = "Journal";
                            break;
                        }
                        case "04": {
                            dotyype = "Journal Articles";
                        }
                    }
                    reqDetail = new globalFunc(dotyype, Handler.ifIsNull(rs.getString("CI04VOLUME")), Handler.ifIsNull(rs.getString("CI04ISSUES")), Handler.ifIsNull(rs.getString("CI04PAGENO")));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return reqDetail;
    }

    public static List<globalFunc> getAccessionNoEXSITORNot(String id) {
        ArrayList<globalFunc> count = new ArrayList<globalFunc>();
        String query = " SELECT COUNT(*) AS COUNT FROM CTDOCM WHERE CT03DOCNO = '" + id + "'";
        Connection conn = null;
        try {
            try {
                Statement stmt1 = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    globalFunc accnoExsit = new globalFunc(rs.getString("COUNT"));
                    count.add(accnoExsit);
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

    public static List<globalFunc> GetPatronName(String vsPatronId) {
        ArrayList<globalFunc> list = new ArrayList<globalFunc>();
        String query = "SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + vsPatronId + "')";
        System.out.println("query GetPatronName = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sLibName = Handler.ifIsNull(rs.getString("GL14NAME"));
                    globalFunc libname = new globalFunc(sLibName);
                    list.add(libname);
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

    public static int Get98VALUE(String value) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='" + value + "'";
        int getBINDNO = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getBINDNO = rs.getInt("GL98VALUE");
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
        return getBINDNO;
    }

    public static boolean updatingGlnumb(int newno) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", "OUTREQUESTNO");
        valueInt.put("GL98VALUE", newno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean updatingGlnumb2(int newno, String func) {
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("GL98FUNC", func);
        valueInt.put("GL98VALUE", newno);
        String query = QueryBuilder.createUpdateQuery("GLNUMB", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }
}
