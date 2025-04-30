/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessLvl {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    public static String getPatronAccessLvl(String username) throws SQLException, URISyntaxException, IOException {
        String sql = "Select GL02ACL from GLPATR, GLGRMA where GL02GRP=GL14GRID and UPPER(GL14PATR)= UPPER('" + username + "')";
        System.out.println(sql);
        String accesslvl = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    System.out.println("A" + rsObj.getString("GL02ACL"));
                    accesslvl = rsObj.getString("GL02ACL");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                conn.close();
            }
        }
        finally {
            conn.close();
        }
        return accesslvl;
    }

    public static String getMenuAccessLvl(String programId) throws SQLException, URISyntaxException, IOException {
        String sql = "Select GL22ACL from GLMENU where GL22PROG= '" + programId + "'";
        String accesslvl = null;
        System.out.println(sql);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                accesslvl = rsObj.getString("GL22ACL");
            }
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
        return accesslvl;
    }

    public static boolean executeAccessLvl(String username, String programId) {
        boolean bSuccessful = true;
        try {
            String patronAccLvl = AccessLvl.getPatronAccessLvl(username);
            int menuAccLvl = Integer.parseInt(AccessLvl.getMenuAccessLvl(programId));
            if (patronAccLvl == null) {
                System.out.println("no patron");
                bSuccessful = false;
            } else if (Integer.parseInt(patronAccLvl) <= menuAccLvl) {
                System.out.println("in");
                bSuccessful = true;
            } else {
                System.out.println("no");
                bSuccessful = false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return bSuccessful;
    }
}
