/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.object.branch;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistBranch {
    public List<branch> loadPicklistBranch() throws SQLException {
        Connection conn = DBConnection.getConnection();
        ArrayList<branch> listPickList = new ArrayList<branch>();
        try {
            try {
                Statement statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                ResultSet resultSet = null;
                resultSet = statement.executeQuery(sql_statement2.picklistBranch());
                while (resultSet.next()) {
                    branch branch2 = new branch();
                    branch2.setBranchDesc(resultSet.getString("GL71DESC"));
                    listPickList.add(branch2);
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
