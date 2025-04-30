/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.foundation.glflag2.data;

import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetGlflag2 {
    private String Value = null;

    public String getValue() {
        return Handler.ifIsNull(this.Value);
    }

    public GetGlflag2(String Value) {
        this.Value = Value;
    }

    public static String getGlflag2Value(String id) {
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = '" + id + "'";
        System.out.println("query GetGlflag2 = " + query);
        String glflagvalue = null;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (id.equals("CURRENCYFORMAT")) {
                        String[] splitter = Handler.ifIsNull(rs.getString("GL99VALUE")).split("\\.");
                        splitter[0].length();
                        splitter[1].length();
                        glflagvalue = Integer.toString(splitter[1].length());
                        continue;
                    }
                    glflagvalue = Handler.ifIsNull(rs.getString("GL99VALUE"));
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
        return glflagvalue;
    }
}
