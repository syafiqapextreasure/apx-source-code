/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.StyleTable;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Style {
    private static Connection conn = null;
    private String name;
    private String value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Style(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static List<Style> getStyle(String template, String branch, String selectedStyle) {
        Connection conn = null;
        DBConnection dbtype = new DBConnection();
        String Mssql_query = "SELECT LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01PRINT) as LB01PRINT from LBMSTR\n\rWHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01PRINT)\n\r)AS LBMSTRUnpivot\n\rUNION\n\rSELECT T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?";
        String Mysql_query = "SELECT 'LB01PRINT' AS LB02PROPERTY, LB01PRINT AS LB02VALUE FROM LBMSTR\n\rWHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?\n\rUNION\n\rSELECT T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?";
        ArrayList<Style> style = new ArrayList<Style>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (DBConnection.getDBType().equals("mssql")) {
                    System.out.println("query: " + Mssql_query);
                    stmt = conn.prepareStatement(Mssql_query);
                    stmt.setString(1, selectedStyle);
                    stmt.setString(2, template);
                    stmt.setString(3, branch);
                    stmt.setString(4, selectedStyle);
                    stmt.setString(5, template);
                    stmt.setString(6, branch);
                    rs = stmt.executeQuery();
                } else if (DBConnection.getDBType().equals("mysql")) {
                    System.out.println("query: " + Mysql_query);
                    stmt = conn.prepareStatement(Mysql_query);
                    stmt.setString(1, selectedStyle);
                    stmt.setString(2, template);
                    stmt.setString(3, branch);
                    stmt.setString(4, selectedStyle);
                    stmt.setString(5, template);
                    stmt.setString(6, branch);
                    rs = stmt.executeQuery();
                }
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    style.add(new Style(rs.getString("LB02PROPERTY"), rs.getString("LB02VALUE")));
                }
                StyleTable styleTable = new StyleTable();
                styleTable.creatHTMLtable(style);
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
        return style;
    }
}
