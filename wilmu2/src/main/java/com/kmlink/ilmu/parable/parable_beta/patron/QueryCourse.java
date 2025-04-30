/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta.patron;

import com.kmlink.ilmu.parable.parable_beta.GeneralUtil_backup;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class QueryCourse {
    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public HashMap<String, Object> loadyByCourse(String course, int unprint) throws SQLException {
        Connection con = DBConnection.getConnection();
        statement = conn.createStatement();
        sql_statement sql_statement2 = new sql_statement();
        Object columnCount = null;
        GeneralUtil_backup GeneralUtil = new GeneralUtil_backup();
        resultSet = statement.executeQuery(sql_statement2.patronCourse(course, unprint));
        int size = resultSet.getRow();
        HashMap<String, Object> result = GeneralUtil.extract_data_patron(resultSet);
        return result;
    }
}
