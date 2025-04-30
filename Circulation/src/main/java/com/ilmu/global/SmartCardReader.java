/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 */
package com.ilmu.global;

import com.ilmu.global.Config;
import com.ilmu.global.connection.DBConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

public class SmartCardReader {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private static final String SmartCardPath = Config.get("SC_Path");

    private static Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {
        HashMap<String, String> result = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            System.out.println("Result1" + key);
            String value = request.getHeader(key);
            System.out.println("Result1" + value);
            result.put(key, value);
        }
        System.out.println("Result" + result);
        return result;
    }

    public static String getPatron_Id(String ip) throws SQLException, URISyntaxException {
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC= 'SMARTCARD'";
        Properties prop = new Properties();
        InputStream input = null;
        String patron_id = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL99VALUE").equals("Y")) continue;
                sql = "SELECT CI12PATRID FROM CISCARD WHERE CI12IPADD= '" + ip + "'";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj1 = stmt.executeQuery(sql);
                while (rsObj1.next()) {
                    patron_id = rsObj1.getString("CI12PATRID");
                    String query = "DELETE FROM CISCARD WHERE CI12IPADD = '" + ip + "' ";
                    System.out.println(query);
                    try {
                        try {
                            conn = DBConnection.getConnection();
                            PreparedStatement delete = conn.prepareStatement(query);
                            delete.execute();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                            try {
                                conn.close();
                            }
                            catch (SQLException e2) {
                                e2.printStackTrace();
                            }
                            continue;
                        }
                    }
                    catch (Throwable throwable) {
                        try {
                            conn.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                        throw throwable;
                    }
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return patron_id;
    }

    public static boolean deleteFile() throws SQLException {
        boolean deleted = false;
        File file = new File(SmartCardPath);
        System.out.println("Delete file");
        deleted = file.delete();
        return deleted;
    }
}
