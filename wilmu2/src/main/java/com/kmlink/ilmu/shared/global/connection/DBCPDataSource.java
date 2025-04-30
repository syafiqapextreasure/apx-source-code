/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.dbcp.BasicDataSource
 */
package com.kmlink.ilmu.shared.global.connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSource {
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:mariadb://localhost:3306/mysql");
        ds.setUsername("ilmuweb");
        ds.setPassword("ilmuweb");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource() {
    }
}
