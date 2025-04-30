/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
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
        String sql = "Select GL02ACL from GLPATR, GLGRMA where GL02GRP=GL14GRID and UPPER(GL14PATR) = UPPER('" + username + "')";
        System.out.println(sql);
        String accesslvl = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                System.out.println("A" + rsObj.getString("GL02ACL"));
                accesslvl = rsObj.getString("GL02ACL");
            }
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
        return accesslvl;
    }

    public static String getMenuAccessLvl(String programId) throws SQLException, URISyntaxException, IOException {
        String sql = "Select GL22ACL from GLMENU where GL22PROG= '" + programId + "'";
        System.out.println("getMenuAccessLvl" + sql);
        String accesslvl = null;
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

    public static int accesslevel(String patronId, String programId) throws SQLException, URISyntaxException, IOException {
        String sql = "\tSELECT Count(*) as count FROM GLPATR, GLGRAC, GLMENU WHERE GL14GRID = GL58GRP AND GL22MODULE = GL58MODULE AND GL22ACL >= GL58ACCESS AND UPPER(GL14PATR) = UPPER('" + patronId + "') " + "AND GL22PROG = '" + programId + "'";
        System.out.println("getMenuAccessLvl" + sql);
        int count = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                count = Integer.parseInt(rsObj.getString("count"));
            }
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
        return count;
    }

    public static boolean executeAccessLvl(String username, String programId) {
        boolean bSuccessful = true;
        try {
            int count = AccessLvl.accesslevel(username, programId);
            System.out.println("Name" + count);
            bSuccessful = count != 0;
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
