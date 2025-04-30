/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.CodeTable;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CodeCriteria {
    static Connection con;
    private String code = null;
    private String desc = null;

    public CodeCriteria(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getcode() {
        return this.code;
    }

    public String getdesc() {
        return this.desc;
    }

    public static List<CodeCriteria> searchCodeByCode(String criteria, String fcode) {
        ArrayList<CodeCriteria> list = new ArrayList<CodeCriteria>();
        Statement stmt = null;
        String sql = "SELECT CODE, DESCRIPTION, FCODE from FNDCODE WHERE";
        sql = String.valueOf(sql) + " CODE LIKE '%" + criteria + "%' AND FCODE = '" + fcode + "'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CodeCriteria newSearchItemByCode = new CodeCriteria(rs.getString("CODE"), rs.getString("DESCRIPTION"));
                    list.add(newSearchItemByCode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return list;
    }

    public static List<CodeCriteria> searchCodeByDesc(String criteria, String fcode) {
        ArrayList<CodeCriteria> list = new ArrayList<CodeCriteria>();
        Statement stmt = null;
        String sql = "SELECT CODE, DESCRIPTION, FCODE from FNDCODE WHERE";
        sql = String.valueOf(sql) + " DESCRIPTION LIKE '%" + criteria + "%' AND FCODE = '" + fcode + "'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    CodeCriteria newSearchItemByName = new CodeCriteria(rs.getString("CODE"), rs.getString("DESCRIPTION"));
                    list.add(newSearchItemByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return list;
    }
}
