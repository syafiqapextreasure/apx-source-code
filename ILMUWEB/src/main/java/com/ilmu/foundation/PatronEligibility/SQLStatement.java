/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronEligibility;

import com.ilmu.foundation.PatronEligibility.PatronEligibility;
import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLStatement {
    public static Connection connection;

    public List<Foundation> getPatEligibility() throws SQLException {
        ArrayList<Foundation> pdlist = new ArrayList<Foundation>();
        String query = "SELECT GL27CATE, GL27ICAT, GL27SMD, GL27BRNC, GL27PLOAN from GLELIG ORDER BY GL27LASTDATE ASC";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL27CATE(rs.getString("GL27CATE"));
                    fnd.setGL27ICAT(rs.getString("GL27ICAT"));
                    fnd.setGL27SMD(rs.getString("GL27SMD"));
                    fnd.setGL27BRNC(rs.getString("GL27BRNC"));
                    fnd.setGL27PLOAN(rs.getString("GL27PLOAN"));
                    pdlist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pdlist;
    }

    public List<Foundation> getFines(String GL38CATE, String GL38ICAT, String GL38SMD, String GL38BRNC) throws SQLException {
        ArrayList<Foundation> pdlist = new ArrayList<Foundation>();
        String query = "SELECT GL38START, GL38STOP, GL38RATE, GL38FIRST from GLFINE WHERE GL38CATE='" + GL38CATE + "' AND GL38ICAT='" + GL38ICAT + "' AND GL38SMD='" + GL38SMD + "' AND GL38BRNC='" + GL38BRNC + "'";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                System.out.print(String.valueOf(query) + " getFines");
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL38START(rs.getString("GL38START"));
                    fnd.setGL38STOP(rs.getString("GL38STOP"));
                    fnd.setGL38RATE(rs.getString("GL38RATE"));
                    fnd.setGL38FIRST(rs.getString("GL38FIRST"));
                    pdlist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pdlist;
    }

    public int CountTotalRcrd() {
        int rowNum = 0;
        String query = "Select Count(*) as Count from Glelig ";
        try {
            try {
                connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    rowNum = Integer.parseInt(rs.getString("Count"));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowNum;
    }

    public List<PatronEligibility> getPatronEligibities(String value) throws SQLException {
        ArrayList<PatronEligibility> pe = new ArrayList<PatronEligibility>();
        System.out.println(String.valueOf(value) + "value156");
        DBConnection dbtype = new DBConnection();
        String sqlStatement = "";
        if (value != null) {
            if (value.equalsIgnoreCase("N")) {
                if (dbtype.getDBType().equalsIgnoreCase("mssql")) {
                    System.out.println("sql here");
                    sqlStatement = "SELECT TOP 100 el.GL27CATE AS A,el.GL27ICAT AS B,el.GL27SMD AS C,el.GL27BRNC AS D,el.GL27PLOAN AS E,el.GL27PTYPE AS F,ca.GL07DESC AS G,br.GL71DESC AS H,ic.GL10DESC AS I,smd.GL47DESC AS J FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD ";
                } else if (dbtype.getDBType().equalsIgnoreCase("oracle")) {
                    System.out.println("oracle here");
                    sqlStatement = "SELECT el.GL27CATE AS A,el.GL27ICAT AS B,el.GL27SMD AS C,el.GL27BRNC AS D,el.GL27PLOAN AS E,el.GL27PTYPE AS F,ca.GL07DESC AS G,br.GL71DESC AS H,ic.GL10DESC AS I,smd.GL47DESC AS J FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD WHERE rownum <= 100 ";
                } else if (dbtype.getDBType().equalsIgnoreCase("mysql")) {
                    System.out.println("mysql here");
                    sqlStatement = "SELECT el.GL27CATE AS A,el.GL27ICAT AS B,el.GL27SMD AS C,el.GL27BRNC AS D,el.GL27PLOAN AS E, el.GL27PTYPE AS F,ca.GL07DESC AS G,br.GL71DESC AS H,ic.GL10DESC AS I,smd.GL47DESC AS J FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD ";
                }
            } else if (value.equalsIgnoreCase("Y")) {
                sqlStatement = "SELECT el.GL27CATE AS A,el.GL27ICAT AS B,el.GL27SMD AS C,el.GL27BRNC AS D,el.GL27PLOAN AS E,el.GL27PTYPE AS F,ca.GL07DESC AS G,br.GL71DESC AS H,ic.GL10DESC AS I,smd.GL47DESC AS J FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD ";
            }
            sqlStatement = dbtype.getDBType().equalsIgnoreCase("mysql") ? String.valueOf(sqlStatement) + "ORDER BY A ASC, B ASC,  C ASC, D ASC LIMIT 100" : String.valueOf(sqlStatement) + "ORDER BY A ASC, B ASC,  C ASC, D ASC";
            System.out.println("SQL ELIG Y OR NO" + sqlStatement);
            try {
                try {
                    connection = DBConnection.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sqlStatement);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        PatronEligibility p = new PatronEligibility(rs.getString("A"), rs.getString("B"), rs.getString("C"), rs.getString("D"), rs.getInt("E"), rs.getString("F"), rs.getString("G"), rs.getString("H"), rs.getString("I"), rs.getString("J"));
                        pe.add(p);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        connection.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return pe;
    }

    public PatronEligibility getPatronEligibities(String patronCategory, String itemCategory, String smdCode, String branch) {
        PatronEligibility p = null;
        try {
            try {
                connection = DBConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT GL27CATE,GL27ICAT,GL27SMD,GL27BRNC FROM GLELIG WHERE GL27CATE='" + patronCategory + "' AND GL27ICAT='" + itemCategory + "' AND GL27SMD='" + smdCode + "' AND GL27BRNC='" + branch + "'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    p = new PatronEligibility(rs.getString("GL27CATE"), rs.getString("GL27ICAT"), rs.getString("GL27SMD"), rs.getString("GL27BRNC"), "*");
                }
                if (p == null) {
                    p = new PatronEligibility(patronCategory, itemCategory, smdCode, branch, "");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public static void addEligibility(String[] gL27CATE, String[] gL27ICAT, String[] gL27SMD, String[] gL27BRNC, String gL27PLOAN, String gL27ELIG, String gL27RENEW, String gL27GRACE1, String gL27GRACE2, String gL27GRACE3, String gL27TIMESCOST, String gL27PRCFEES, String gL27FGRACE1, String gL27FGRACE2, String gL27FGRACE3, String gL27ALLOWOVD, String gL27ALLOWRSV, String gL27PTYPE, String gL27INCFINE, String gL27MAXFINE, String[] gL38START, String[] gL38STOP, String[] gL38RATE, String[] gL38FIRST, String recby) {
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (gL27CATE.length >= 1) {
            int i = 0;
            while (i < gL27CATE.length) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("GL27CATE", gL27CATE[i]);
                map.put("GL27ICAT", gL27ICAT[i]);
                map.put("GL27SMD", gL27SMD[i]);
                map.put("GL27BRNC", gL27BRNC[i]);
                map.put("GL27PTYPE", gL27PTYPE);
                map.put("GL27MAXFINE", gL27MAXFINE != null ? Double.valueOf(Double.parseDouble(gL27MAXFINE)) : null);
                map.put("GL27PLOAN", gL27PLOAN != null ? Integer.valueOf(Integer.parseInt(gL27PLOAN)) : null);
                map.put("GL27ELIG", gL27ELIG != null ? Integer.valueOf(Integer.parseInt(gL27ELIG)) : null);
                map.put("GL27RENEW", gL27RENEW != null ? Integer.valueOf(Integer.parseInt(gL27RENEW)) : null);
                map.put("GL27GRACE1", gL27GRACE1 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE1)) : null);
                map.put("GL27GRACE2", gL27GRACE2 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE2)) : null);
                map.put("GL27GRACE3", gL27GRACE3 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE3)) : null);
                map.put("GL27TIMESCOST", gL27TIMESCOST != null ? Integer.valueOf(Integer.parseInt(gL27TIMESCOST)) : null);
                map.put("GL27FGRACE1", gL27FGRACE1 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE1)) : null);
                map.put("GL27FGRACE2", gL27FGRACE2 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE2)) : null);
                map.put("GL27FGRACE3", gL27FGRACE3 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE3)) : null);
                map.put("GL27PRCFEES", gL27PRCFEES != null ? Double.valueOf(Double.parseDouble(gL27PRCFEES)) : null);
                map.put("GL27INCFINE", gL27INCFINE);
                map.put("GL27PTYPE", gL27PTYPE);
                map.put("GL27ALLOWOVD", gL27ALLOWOVD);
                map.put("GL27ALLOWRSV", gL27ALLOWRSV);
                map.put("GL27DATEREC", dateFormat.format(new Date()));
                map.put("GL27RECBY", recby);
                mapList.add(map);
                ++i;
            }
        }
        List<String> queries = QueryBuilder.createInsertQuery("GLELIG", mapList);
        boolean isSuccess = DBQuery.runBatchQuery(queries);
        System.out.println("isSuccess360" + isSuccess);
        if (isSuccess) {
            SQLStatement.addFinesSchedule(gL27CATE, gL27ICAT, gL27BRNC, gL27SMD, gL38START, gL38STOP, gL38RATE, gL38FIRST);
        }
    }

    public static void editEligibilityWithoutFine(String[] gL27CATE, String[] gL27ICAT, String[] gL27SMD, String[] gL27BRNC, String gL27PLOAN, String gL27ELIG, String gL27RENEW, String gL27GRACE1, String gL27GRACE2, String gL27GRACE3, String gL27TIMESCOST, String gL27PRCFEES, String gL27FGRACE1, String gL27FGRACE2, String gL27FGRACE3, String gL27ALLOWOVD, String gL27ALLOWRSV, String gL27PTYPE, String gL27INCFINE, String gL27MAXFINE, String[] gL38START, String[] gL38STOP, String[] gL38RATE, String[] gL38FIRST, String recby) {
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (gL27CATE.length >= 1) {
            int i = 0;
            while (i < gL27CATE.length) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("GL27CATE", gL27CATE[i]);
                map.put("GL27ICAT", gL27ICAT[i]);
                map.put("GL27SMD", gL27SMD[i]);
                map.put("GL27BRNC", gL27BRNC[i]);
                map.put("GL27PTYPE", gL27PTYPE);
                map.put("GL27MAXFINE", gL27MAXFINE != null ? Double.valueOf(Double.parseDouble(gL27MAXFINE)) : null);
                map.put("GL27PLOAN", gL27PLOAN != null ? Integer.valueOf(Integer.parseInt(gL27PLOAN)) : null);
                map.put("GL27ELIG", gL27ELIG != null ? Integer.valueOf(Integer.parseInt(gL27ELIG)) : null);
                map.put("GL27RENEW", gL27RENEW != null ? Integer.valueOf(Integer.parseInt(gL27RENEW)) : null);
                map.put("GL27GRACE1", gL27GRACE1 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE1)) : null);
                map.put("GL27GRACE2", gL27GRACE2 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE2)) : null);
                map.put("GL27GRACE3", gL27GRACE3 != null ? Integer.valueOf(Integer.parseInt(gL27GRACE3)) : null);
                map.put("GL27TIMESCOST", gL27TIMESCOST != null ? Integer.valueOf(Integer.parseInt(gL27TIMESCOST)) : null);
                map.put("GL27FGRACE1", gL27FGRACE1 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE1)) : null);
                map.put("GL27FGRACE2", gL27FGRACE2 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE2)) : null);
                map.put("GL27FGRACE3", gL27FGRACE3 != null ? Integer.valueOf(Integer.parseInt(gL27FGRACE3)) : null);
                map.put("GL27PRCFEES", gL27PRCFEES != null ? Double.valueOf(Double.parseDouble(gL27PRCFEES)) : null);
                map.put("GL27INCFINE", gL27INCFINE);
                map.put("GL27PTYPE", gL27PTYPE);
                map.put("GL27ALLOWOVD", gL27ALLOWOVD);
                map.put("GL27ALLOWRSV", gL27ALLOWRSV);
                map.put("GL27DATEREC", dateFormat.format(new Date()));
                map.put("GL27RECBY", recby);
                mapList.add(map);
                ++i;
            }
        }
        List<String> queries = QueryBuilder.createInsertQuery("GLELIG", mapList);
        boolean isSuccess = DBQuery.runBatchQuery(queries);
        System.out.println("isSuccess" + isSuccess);
    }

    private static void addFinesSchedule(String[] gL27CATE, String[] gL27ICAT, String[] gL27BRNC, String[] gL27SMD, String[] gL38START, String[] gL38STOP, String[] gL38RATE, String[] gL38FIRST) {
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        System.out.println("gL27CATE.length " + gL27CATE.length);
        if (gL27CATE.length >= 1) {
            int i = 0;
            while (i < gL27CATE.length) {
                System.out.println("gL38RATE.length " + gL38RATE.length);
                int j = 0;
                while (j < gL38RATE.length) {
                    if (gL38RATE[j] != "") {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("GL38CATE", gL27CATE[i]);
                        map.put("GL38ICAT", gL27ICAT[i]);
                        map.put("GL38SMD", gL27SMD[i]);
                        map.put("GL38BRNC", gL27BRNC[i]);
                        map.put("GL38START", gL38START[j] != "" ? Integer.valueOf(Integer.parseInt(gL38START[j])) : null);
                        map.put("GL38STOP", gL38STOP[j] != "" ? Integer.valueOf(Integer.parseInt(gL38STOP[j])) : null);
                        map.put("GL38RATE", gL38RATE[j] != "" ? Double.valueOf(Double.parseDouble(gL38RATE[j])) : null);
                        map.put("GL38FIRST", gL38FIRST[j] != "" ? Double.valueOf(Double.parseDouble(gL38FIRST[j])) : null);
                        if (gL38RATE.length > 1) {
                            if (j == 0) {
                                System.out.println("j ==  0" + j);
                                map.put("GL38FIRST", Double.parseDouble("0.00"));
                            } else {
                                System.out.println("j >  1" + j + ": " + gL38FIRST[j - 1]);
                                map.put("GL38FIRST", gL38FIRST[j - 1] != "" ? Double.valueOf(Double.parseDouble(gL38FIRST[j - 1])) : null);
                            }
                        } else if (gL38RATE.length == 1) {
                            System.out.println("HERE == 1 : " + gL38FIRST[j]);
                            map.put("GL38FIRST", gL38FIRST[j] != "" ? Double.valueOf(Double.parseDouble("0.00")) : null);
                        }
                        mapList.add(map);
                    }
                    ++j;
                }
                ++i;
            }
        }
        List<String> queries = QueryBuilder.createInsertQuery("GLFINE", mapList);
        boolean isSuccess = DBQuery.runBatchQuery(queries);
        System.out.println("isSuccess2495 =" + isSuccess);
    }

    public static String checkEligibility(String gL27CATE, String gL27ICAT, String gL27SMD, String gL27BRNC) {
        try {
            connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CICIRC ci, CTDOCM ct, GLBRNC br, GLPATR p, GLLOCA l WHERE ci.CI02FLAG='C' AND ci.CI02DOCNO = CT03DOCNO AND ct.CT03SMD='" + gL27SMD + "' AND ct.CT03ICAT='" + gL27ICAT + "' AND l.GL05BRNC=br.GL71BRNC  AND br.GL71BRNC = '" + gL27BRNC + "' AND ci.CI02PATR = p.GL14PATR AND p.GL14CATE = '" + gL27CATE + "'");
            System.out.println("SELECT * FROM CICIRC ci, CTDOCM ct, GLBRNC br, GLPATR p, GLLOCA l WHERE ci.CI02FLAG='C' AND ci.CI02DOCNO = CT03DOCNO AND ct.CT03SMD='" + gL27SMD + "' AND ct.CT03ICAT='" + gL27ICAT + "' AND l.GL05BRNC=br.GL71BRNC  AND br.GL71BRNC = '" + gL27BRNC + "' AND ci.CI02PATR = p.GL14PATR AND p.GL14CATE = '" + gL27CATE + "'");
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return "continue";
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "error";
    }

    public static String deleteEligibility(String gL27CATE, String gL27ICAT, String gL27SMD, String gL27BRNC) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("GL27CATE", gL27CATE);
        map.put("GL27ICAT", gL27ICAT);
        map.put("GL27SMD", gL27SMD);
        map.put("GL27BRNC", gL27BRNC);
        String query = QueryBuilder.createDeleteQuery("GLELIG", map);
        System.out.println("Query ::" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println("Query ::" + query);
        System.out.println(isSuccess);
        if (isSuccess) {
            SQLStatement.deleteFinesSchedule(gL27CATE, gL27ICAT, gL27BRNC, gL27SMD);
        }
        return query;
    }

    public static String deleteFinesSchedule(String gL27CATE, String gL27ICAT, String gL27BRNC, String gL27SMD) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("GL38CATE", gL27CATE);
        map.put("GL38ICAT", gL27ICAT);
        map.put("GL38SMD", gL27SMD);
        map.put("GL38BRNC", gL27BRNC);
        String query = QueryBuilder.createDeleteQuery("GLFINE", map);
        System.out.println("Query ::" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return query;
    }
}
