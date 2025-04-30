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

public class cmd {
    private String cmd;

    public String getcmd() {
        return Handler.ifIsNull(this.cmd);
    }

    public cmd(String cmd2) {
        this.cmd = cmd2;
    }

    public static String getCMDVal(String name) throws SQLException {
        String cmd22;
        block12: {
            Object cmd2 = null;
            String sql = "SELECT GL22CMD FROM GLMENU WHERE GL22PROG = 'AARM0150' AND UPPER(GL22DESC)  = UPPER('" + name + "')";
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            System.out.println("getCMDVal SQL : " + sql);
            cmd22 = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        cmd22 = "";
                        break block12;
                    }
                    cmd22 = Handler.ifIsNull(rs.getString("GL22CMD"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return cmd22;
    }
}
