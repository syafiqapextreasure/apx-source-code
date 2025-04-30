/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Template {
    private static Connection conn = null;
    private String templateName;
    private String value;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateNname(String templateName) {
        this.templateName = templateName;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Template(String templateName) {
        this.templateName = templateName;
    }

    public Template(String templateName, String value) {
        this.templateName = templateName;
        this.value = value;
    }

    public static List<Template> getTemplates(String branch) throws SQLException {
        String query = "SELECT LB01NOTES FROM LBMSTR WHERE LB01MTYPE='B' AND LB01PRINT='Y'";
        ArrayList<Template> templates = new ArrayList<Template>();
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    templates.add(new Template(rs.getString("LB01NOTES")));
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
        return templates;
    }
}
