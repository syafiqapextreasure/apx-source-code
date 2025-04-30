/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Source {
    private String CODE = null;
    private String DESCRIPTION = null;

    public String getCODE() {
        return Handler.ifIsNull(this.CODE);
    }

    public String getDESCRIPTION() {
        return Handler.ifIsNull(this.DESCRIPTION);
    }

    public Source(String CODE, String DESCRIPTION) {
        this.CODE = CODE;
        this.DESCRIPTION = DESCRIPTION;
    }

    public static List<Source> getBibSource() {
        ArrayList<Source> list = new ArrayList<Source>();
        String query = "SELECT CODE, DESCRIPTION FROM FNDCODE WHERE FCODE = 'B'";
        System.out.println("querygetBibSource = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Source newSource = new Source(rs.getString("CODE"), rs.getString("DESCRIPTION"));
                    list.add(newSource);
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
