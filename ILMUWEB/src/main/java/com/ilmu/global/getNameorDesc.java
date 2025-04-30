/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getNameorDesc {
    private String Desc = null;

    public String getDesc() {
        return Handler.ifIsNull(this.Desc);
    }

    public getNameorDesc(String Desc) {
        this.Desc = Desc;
    }

    public static List<getNameorDesc> getName(String code, String table) {
        ArrayList<getNameorDesc> getdesc = new ArrayList<getNameorDesc>();
        String query = "";
        if (table.equals("patron")) {
            query = "SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + code + "') ";
        }
        System.out.println("query getName = " + query);
        Connection conn = null;
        String desc = "";
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ResultSetMetaData meta = rs.getMetaData();
                    getNameorDesc descp = new getNameorDesc(rs.getString(meta.getColumnName(1)));
                    getdesc.add(descp);
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
        return getdesc;
    }
}
