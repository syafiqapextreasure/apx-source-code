/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect_backup {
    public static Connection getConnection() {
        Connection con;
        block13: {
            Properties prop = new Properties();
            InputStream input = null;
            con = null;
            try {
                try {
                    String filename = "config";
                    input = Connection.class.getClassLoader().getResourceAsStream(filename);
                    if (input == null) {
                        System.out.println("Sorry, unable to find " + filename);
                    }
                    prop.load(input);
                    String drivers = prop.getProperty("driverClassName");
                    String connectionURL = prop.getProperty("url");
                    String username = prop.getProperty("username");
                    String password = prop.getProperty("password");
                    System.out.println("Connection" + connectionURL);
                    Class.forName(drivers);
                    con = DriverManager.getConnection(connectionURL, username, password);
                    System.out.println("Connection Successful");
                }
                catch (IOException | ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    if (input == null) break block13;
                    try {
                        System.out.println("DBConnection Closed!");
                        input.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                if (input != null) {
                    try {
                        System.out.println("DBConnection Closed!");
                        input.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return con;
    }

    public String getDBType() {
        String dbtype;
        block13: {
            Properties prop = new Properties();
            InputStream input = null;
            dbtype = null;
            try {
                try {
                    String filename = "config";
                    input = Connection.class.getClassLoader().getResourceAsStream(filename);
                    if (input == null) {
                        System.out.println("Sorry, unable to find " + filename);
                    }
                    prop.load(input);
                    dbtype = prop.getProperty("dbtype");
                    System.out.println("Connection Successful");
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    if (input == null) break block13;
                    try {
                        input.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
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
        }
        return dbtype;
    }
}
