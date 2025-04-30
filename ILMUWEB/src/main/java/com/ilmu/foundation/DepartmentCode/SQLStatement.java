/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.DepartmentCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.Handler;
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
    public Connection connection;

    public List<Foundation> getAllDeptCode() throws SQLException {
        ArrayList<Foundation> deptlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT GL11DEPT, GL11NAME, GL11HEAD, GL11STAFF from GLDEPT";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL11DEPT(rs.getString("GL11DEPT"));
                    fnd.setGL11NAME(rs.getString("GL11NAME"));
                    fnd.setGL11HEAD(Handler.ifIsNull(rs.getString("GL11HEAD")));
                    fnd.setGL11STAFF(rs.getInt("GL11STAFF"));
                    deptlist.add(fnd);
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
        return deptlist;
    }

    public static SQLStatement AddDeptCode(String GL11DEPT, String GL11NAME, String GL11ADD1, String GL11ADD2, String GL11ADD3, String GL11POSCODE, String GL11TOWN, String GL11TEL, String GL11HEAD, String GL11STAFF, String GL11FAX) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        System.out.println("suitha");
        valueStr.put("GL11DEPT", GL11DEPT);
        valueStr.put("GL11NAME", GL11NAME);
        valueStr.put("GL11ADD1", GL11ADD1);
        valueStr.put("GL11ADD2", GL11ADD2);
        valueStr.put("GL11ADD3", GL11ADD3);
        valueStr.put("GL11POSCODE", GL11POSCODE);
        valueStr.put("GL11TOWN", GL11TOWN);
        valueStr.put("GL11TEL", GL11TEL);
        valueStr.put("GL11HEAD", GL11HEAD);
        System.out.println("suitha" + GL11STAFF);
        if (GL11STAFF == null || GL11STAFF == "") {
            valueInt.put("GL11STAFF", 0);
        } else {
            valueInt.put("GL11STAFF", Integer.parseInt(GL11STAFF));
        }
        valueStr.put("GL11FAX", GL11FAX);
        String query = QueryBuilder.createInsertQuery("GLDEPT", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL11DEPT) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean deletable = false;
        String query = "SELECT COUNT(GL14DEPT) AS Count FROM GLPATR, GLDEPT WHERE GL14DEPT=GL11DEPT AND GL11DEPT='" + GL11DEPT + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE01DEPT) AS Count FROM SESERM, GLDEPT WHERE SE01DEPT=GL11DEPT AND GL11DEPT='" + GL11DEPT + "'";
                    System.out.println(query2);
                    while (rs.next()) {
                        Integer.parseInt(rs.getString("Count"));
                    }
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

    public static void deleteDept(String GL11DEPT) {
        Connection con = null;
        String query = "DELETE FROM GLDEPT WHERE GL11DEPT = '" + GL11DEPT + "'";
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
