/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronEligibility;

import com.ilmu.foundation.PatronEligibility.PatronEligibility;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchElig {
    static Connection con;

    public static List<PatronEligibility> searchEligByCate(String[] criteria) {
        ArrayList<PatronEligibility> resultList = new ArrayList<PatronEligibility>();
        Statement stmt = null;
        String sql = "SELECT el.GL27CATE AS cate,el.GL27ICAT AS icate,el.GL27SMD AS smd,el.GL27BRNC AS branch,el.GL27PLOAN AS loan,el.GL27PTYPE AS type,ca.GL07DESC AS cateDesc,br.GL71DESC AS branchDesc,ic.GL10DESC AS icateDesc,smd.GL47DESC AS smdDesc FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD WHERE";
        int i = 0;
        while (i < criteria.length) {
            sql = i != criteria.length - 1 ? String.valueOf(sql) + " el.GL27CATE LIKE '%" + criteria[i] + "%' OR" : String.valueOf(sql) + " el.GL27CATE LIKE '%" + criteria[i] + "%'";
            ++i;
        }
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatronEligibility result = new PatronEligibility(rs.getString("cate"), rs.getString("cateDesc"), rs.getString("icate"), rs.getString("icateDesc"), rs.getString("smd"), rs.getString("smdDesc"), rs.getString("branch"), rs.getString("branchDesc"), String.valueOf(String.valueOf(rs.getInt("loan"))) + "&nbsp" + (rs.getString("type").toString().equalsIgnoreCase("D") ? "day(s)" : "hour(s)"));
                    resultList.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static List<PatronEligibility> searchEligByIcate(String[] criteria) {
        ArrayList<PatronEligibility> resultList = new ArrayList<PatronEligibility>();
        Statement stmt = null;
        String sql = "SELECT el.GL27CATE AS cate,el.GL27ICAT AS icate,el.GL27SMD AS smd,el.GL27BRNC AS branch,el.GL27PLOAN AS loan,el.GL27PTYPE AS type,ca.GL07DESC AS cateDesc,br.GL71DESC AS branchDesc,ic.GL10DESC AS icateDesc,smd.GL47DESC AS smdDesc FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD WHERE";
        int i = 0;
        while (i < criteria.length) {
            sql = i != criteria.length - 1 ? String.valueOf(sql) + " el.GL27ICAT LIKE '%" + criteria[i] + "%' OR" : String.valueOf(sql) + " el.GL27ICAT LIKE '%" + criteria[i] + "%'";
            ++i;
        }
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatronEligibility result = new PatronEligibility(rs.getString("cate"), rs.getString("cateDesc"), rs.getString("icate"), rs.getString("icateDesc"), rs.getString("smd"), rs.getString("smdDesc"), rs.getString("branch"), rs.getString("branchDesc"), String.valueOf(String.valueOf(rs.getInt("loan"))) + "&nbsp" + (rs.getString("type").toString().equalsIgnoreCase("D") ? "day(s)" : "hour(s)"));
                    resultList.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static List<PatronEligibility> searchEligBySmd(String[] criteria) {
        ArrayList<PatronEligibility> resultList = new ArrayList<PatronEligibility>();
        Statement stmt = null;
        String sql = "SELECT el.GL27CATE AS cate,el.GL27ICAT AS icate,el.GL27SMD AS smd,el.GL27BRNC AS branch,el.GL27PLOAN AS loan,el.GL27PTYPE AS type,ca.GL07DESC AS cateDesc,br.GL71DESC AS branchDesc,ic.GL10DESC AS icateDesc,smd.GL47DESC AS smdDesc FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD WHERE";
        int i = 0;
        while (i < criteria.length) {
            sql = i != criteria.length - 1 ? String.valueOf(sql) + " el.GL27SMD LIKE '%" + criteria[i] + "%' OR" : String.valueOf(sql) + " el.GL27SMD LIKE '%" + criteria[i] + "%'";
            ++i;
        }
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatronEligibility result = new PatronEligibility(rs.getString("cate"), rs.getString("cateDesc"), rs.getString("icate"), rs.getString("icateDesc"), rs.getString("smd"), rs.getString("smdDesc"), rs.getString("branch"), rs.getString("branchDesc"), String.valueOf(String.valueOf(rs.getInt("loan"))) + "&nbsp" + (rs.getString("type").toString().equalsIgnoreCase("D") ? "day(s)" : "hour(s)"));
                    resultList.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public static List<PatronEligibility> searchEligByBranch(String[] criteria) {
        ArrayList<PatronEligibility> resultList = new ArrayList<PatronEligibility>();
        Statement stmt = null;
        String sql = "SELECT el.GL27CATE AS cate,el.GL27ICAT AS icate,el.GL27SMD AS smd,el.GL27BRNC AS branch,el.GL27PLOAN AS loan,el.GL27PTYPE AS type,ca.GL07DESC AS cateDesc,br.GL71DESC AS branchDesc,ic.GL10DESC AS icateDesc,smd.GL47DESC AS smdDesc FROM GLELIG el JOIN GLCATE ca ON el.GL27CATE=ca.GL07CATE JOIN GLBRNC br ON el.GL27BRNC=br.GL71BRNC JOIN GLICAT ic ON el.GL27ICAT=ic.GL10ICAT JOIN GLSMD smd ON el.GL27SMD=smd.GL47SMD WHERE";
        int i = 0;
        while (i < criteria.length) {
            sql = i != criteria.length - 1 ? String.valueOf(sql) + " el.GL27BRNC LIKE '%" + criteria[i] + "%' OR" : String.valueOf(sql) + " el.GL27BRNC LIKE '%" + criteria[i] + "%'";
            ++i;
        }
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatronEligibility result = new PatronEligibility(rs.getString("cate"), rs.getString("cateDesc"), rs.getString("icate"), rs.getString("icateDesc"), rs.getString("smd"), rs.getString("smdDesc"), rs.getString("branch"), rs.getString("branchDesc"), String.valueOf(String.valueOf(rs.getInt("loan"))) + "&nbsp" + (rs.getString("type").toString().equalsIgnoreCase("D") ? "day(s)" : "hour(s)"));
                    resultList.add(result);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
