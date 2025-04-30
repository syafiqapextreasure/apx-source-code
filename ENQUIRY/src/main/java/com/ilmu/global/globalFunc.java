/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Encrypter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class globalFunc {
    private String Name = null;

    public String getName() {
        return Handler.ifIsNull(this.Name);
    }

    public globalFunc(String Name) {
        this.Name = Name;
    }

    public static List<globalFunc> libname() {
        ArrayList<globalFunc> list = new ArrayList<globalFunc>();
        String query = "SELECT GL28NAME FROM GLLIBR";
        System.out.println("query libname = " + query);
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
}
