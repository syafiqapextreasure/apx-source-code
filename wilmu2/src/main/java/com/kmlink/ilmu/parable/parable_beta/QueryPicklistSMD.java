/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.object.smd;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistSMD {
    public List<smd> loadPicklistSMD() throws SQLException {
        Connection conn = DBConnection.getConnection();
        ArrayList<smd> listPickList = new ArrayList<smd>();
        try {
            try {
                Statement statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                ResultSet resultSet = null;
                resultSet = statement.executeQuery(sql_statement2.picklistSMD());
                while (resultSet.next()) {
                    smd smd2 = new smd();
                    smd2.setSMD(resultSet.getString("SMD"));
                    smd2.setDescription(resultSet.getString("Description"));
                    System.out.println("XXXXX" + resultSet.getString("Description"));
                    listPickList.add(smd2);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return listPickList;
    }
}
