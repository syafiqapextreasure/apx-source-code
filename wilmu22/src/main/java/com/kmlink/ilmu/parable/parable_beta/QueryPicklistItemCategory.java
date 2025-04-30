/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.object.item_category;
import com.kmlink.ilmu.parable.parable_beta.object.sql_statement;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QueryPicklistItemCategory {
    public List<item_category> loadPicklistItemCategory() throws SQLException {
        Connection conn = DBConnection.getConnection();
        ArrayList<item_category> listPickList = new ArrayList<item_category>();
        try {
            try {
                Statement statement = conn.createStatement();
                sql_statement sql_statement2 = new sql_statement();
                ResultSet resultSet = null;
                resultSet = statement.executeQuery(sql_statement2.picklistItemCategory());
                while (resultSet.next()) {
                    item_category item_category2 = new item_category();
                    System.out.println(resultSet.getString("ItemCategory"));
                    item_category2.setItemCategory(resultSet.getString("ItemCategory").toString());
                    item_category2.setDescription(resultSet.getString("Description").toString());
                    listPickList.add(item_category2);
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
