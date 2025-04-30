/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Requestor {
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    private String Code = null;
    private String Name = null;
    private String Category = null;

    static {
        stmt = null;
        rs = null;
    }

    public Requestor(String Code, String Name, String Category) {
        this.Code = Code;
        this.Name = Name;
        this.Category = Category;
    }

    public String getCode() {
        return this.Code;
    }

    public String getName() {
        return this.Name;
    }

    public String getCategory() {
        return this.Category;
    }

    public static List<Requestor> getListBy(String condition) {
        if (condition.isEmpty()) {
            return null;
        }
        ArrayList<Requestor> list = new ArrayList<Requestor>();
        String query = "SELECT GL14PATR, GL14NAME, GL07DESC FROM GLPATR LEFT JOIN GLCATE ON GLPATR.GL14CATE = GLCATE.GL07CATE WHERE " + condition;
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Requestor newGetListBy = new Requestor(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL07DESC"));
                    list.add(newGetListBy);
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
        return list;
    }

    public static List<Requestor> searchByRequestorID(String criteria) {
        return Requestor.getListBy("GL14PATR LIKE '" + criteria + "%' ");
    }

    public static List<Requestor> searchByRequestorIDBlur(String criteria) {
        return Requestor.getListBy("GL14PATR = '" + criteria + "' ");
    }

    public static List<Requestor> searchByRequestorName(String criteria) {
        return Requestor.getListBy("GL14NAME LIKE '" + criteria + "%' ");
    }

    public static List<Requestor> searchByRequestorNewIC(String criteria) {
        return Requestor.getListBy("GL14NEWIC LIKE '" + criteria + "%' ");
    }

    public static List<Requestor> searchByRequestorOldIC(String criteria) {
        return Requestor.getListBy("GL14OLDIC LIKE '" + criteria + "%' ");
    }

    public static String getRequestorNameByRequestorCode(String requestorCode) {
        String result = null;
        Statement stmt = null;
        String query = "SELECT GL14NAME FROM GLPATR WHERE GL14PATR = '" + requestorCode + "' ";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL14NAME");
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
        return result;
    }
}
