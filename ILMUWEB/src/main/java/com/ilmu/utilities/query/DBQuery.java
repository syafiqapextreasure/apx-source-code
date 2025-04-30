/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.utilities.query;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBQuery {
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    public static String getSingleData(String column, String table, String condition) {
        String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String string = rs.getString(column);
                return string;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getSingleDataAsData(String column, String table, String condition) {
        String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String string = rs.getString("Data");
                return string;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<String> getListData(String column, String table, String condition) {
        ArrayList<String> list = new ArrayList<String>();
        String query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    list.add(rs.getString("Data"));
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
                return null;
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

    public static boolean runQuery(String query) {
        System.out.println(String.valueOf(query) + " runQuery");
        c = DBConnection.getConnection();
        try {
            PreparedStatement updateOrder = c.prepareStatement(query);
            int success = updateOrder.executeUpdate();
            return success > 0;
            {
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * Exception decompiling
     */
    public static boolean isExist(String table, Map<String, String> conditionMap) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [15[DOLOOP]], but top level block is 3[TRYBLOCK]
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static boolean runBatchQuery(List<String> list) {
        System.out.println("********START: runBatchQuery***********");
        try {
            c = DBConnection.getConnection();
            for (String query : list) {
                PreparedStatement queryStatement = c.prepareStatement(query);
                queryStatement.execute();
                System.out.println(String.valueOf(query) + " testQuery");
            }
            System.out.println("Commiting data here....");
            System.out.println("********STOP: runBatchQuery - SUCCESS***********");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("********STOP: runBatchQuery - FAILED***********");
            return false;
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException se) {}
            try {
                if (c != null) {
                    c.close();
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
