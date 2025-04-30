/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.GeneralSubject;

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

    public List<Foundation> getAllSubjCode() throws SQLException {
        ArrayList<Foundation> subjlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC from GLSUBJ";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL54SUBJSTA(rs.getString("GL54SUBJSTA"));
                    fnd.setGL54SUBJEND(rs.getString("GL54SUBJEND"));
                    fnd.setGL54MARK(rs.getString("GL54MARK"));
                    fnd.setGL54DESC(rs.getString("GL54DESC"));
                    subjlist.add(fnd);
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
        return subjlist;
    }

    public static SQLStatement AddGenSubj(String GL54SUBJSTA, String GL54SUBJEND, String GL54MARK, String GL54DESC) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL54SUBJSTA", GL54SUBJSTA);
        valueStr.put("GL54SUBJEND", GL54SUBJEND);
        valueStr.put("GL54MARK", GL54MARK);
        valueStr.put("GL54DESC", GL54DESC);
        String query = QueryBuilder.createInsertQuery("GLSUBJ", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static void deleteSubj(String GL54SUBJSTA) {
        Connection con = null;
        String query = "DELETE FROM GLSUBJ WHERE GL54SUBJSTA = '" + GL54SUBJSTA + "'";
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
