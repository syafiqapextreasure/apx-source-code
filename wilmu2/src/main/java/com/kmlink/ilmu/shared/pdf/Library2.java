/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Library2 {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String patrId;
    private String name;
    private String desgination;

    public String getPatrId() {
        return this.patrId;
    }

    public String getName() {
        return this.name;
    }

    public String getdesgination() {
        return this.desgination;
    }

    private Library2(String patrId, String name, String desgination) {
        this.patrId = patrId;
        this.name = name;
        this.desgination = desgination;
    }

    public static Library2 hodDetail() throws SQLException {
        Library2 library;
        block12: {
            library = null;
            String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN GLLIBR ON GL14PATR = GL28ACQHEAD LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=GL28ACQHEAD";
            System.out.println("hodDetail" + sql);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        String patr = "";
                        String name = "";
                        String desc = "";
                        library = new Library2(patr, name, desc);
                        break block12;
                    }
                    library = new Library2(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        stmt.close();
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return library;
    }

    public static Library2 loginDetail(String patrid) {
        Library2 library = null;
        String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE UPPER(GL14PATR)= UPPER('" + patrid + "')";
        System.out.println("loginDetail" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library2(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }
}
