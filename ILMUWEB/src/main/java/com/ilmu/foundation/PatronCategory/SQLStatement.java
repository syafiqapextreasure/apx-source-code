/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronCategory;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllPatCate() throws SQLException {
        ArrayList<Foundation> catelist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL07CATE, GL07DESC from GLCATE";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL07CATE(rs.getString("GL07CATE"));
                    fnd.setGL07DESC(rs.getString("GL07DESC"));
                    catelist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return catelist;
    }

    public static SQLStatement AddPatCate(String GL07CATE, String GL07DESC, String GL07ELIG, String GL07MAXFINE, String GL07FINELIMIT, String GL07ILLOUT, String GL07MAXRESV, String GL07ALLOWOVD, String GL07ALLOWRSV, String GL07MAXACCT, String GL07POPDB, String GL07RATER, String GL07EMAIL, String GL07MODEM, String GL07SCHAR, String GL07BRFORC, String GL07ARTXN, String GL07DCFORC, String GL07RENEWFEE, String GL07RENEWGRC) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL07CATE", GL07CATE);
        valueStr.put("GL07DESC", GL07DESC);
        valueInt.put("GL07ELIG", Integer.parseInt(GL07ELIG));
        valueStr.put("GL07MAXFINE", GL07MAXFINE);
        valueStr.put("GL07FINELIMIT", GL07FINELIMIT);
        valueInt.put("GL07ILLOUT", Integer.parseInt(GL07ILLOUT));
        valueInt.put("GL07MAXRESV", Integer.parseInt(GL07MAXRESV));
        valueStr.put("GL07ALLOWOVD", GL07ALLOWOVD);
        valueStr.put("GL07ALLOWRSV", GL07ALLOWRSV);
        valueInt.put("GL07MAXACCT", Integer.parseInt(GL07MAXACCT));
        valueStr.put("GL07POPDB", GL07POPDB);
        valueStr.put("GL07RATER", GL07RATER);
        valueStr.put("GL07EMAIL", GL07EMAIL);
        valueStr.put("GL07MODEM", GL07MODEM);
        valueStr.put("GL07SCHAR", GL07SCHAR);
        valueStr.put("GL07BRFORC", GL07BRFORC);
        valueStr.put("GL07ARTXN", GL07ARTXN);
        valueStr.put("GL07DCFORC", GL07DCFORC);
        valueStr.put("GL07RENEWFEE", GL07RENEWFEE);
        valueStr.put("GL07RENEWGRC", GL07RENEWGRC);
        String query = QueryBuilder.createInsertQuery("GLCATE", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL07CATE) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Object stmt2 = null;
        Object rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(GL14CATE) AS Count FROM GLPATR, GLCATE WHERE GL14CATE=GL07CATE AND GL07CATE='" + GL07CATE + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    deletable = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
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
        return deletable;
    }

    public static void deletePatCat(String GL07CATE) {
        Connection con = null;
        String query = "DELETE FROM GLCATE WHERE GL07CATE = '" + GL07CATE + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement delete = con.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
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
    }
}
