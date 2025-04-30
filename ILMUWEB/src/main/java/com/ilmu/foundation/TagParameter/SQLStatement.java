/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.TagParameter;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllTagParam() throws SQLException {
        ArrayList<Foundation> taglist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL17MARC, GL17TAG, GL17DESC from GLTAGP";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL17MARC(rs.getString("GL17MARC"));
                    fnd.setGL17TAG(rs.getString("GL17TAG"));
                    fnd.setGL17DESC(rs.getString("GL17DESC"));
                    taglist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return taglist;
    }

    public static SQLStatement AddTagP(String GL17MARC, String GL17TAG, String GL17DESC, String GL17TRUC, String GL17TABNAME, String GL17ACRO, String GL17GRID, String GL17LEN, String GL17IDXLANG, String GL17DEFAULT, String GL17REMARK, String GL17REPEAT, String GL17AUTFLAG, String GL17MANDA, String GL17CPFLAG, String GL17IDXFLAG, String GL17PARAMIPS, String GL17KEYWORD, String GL17MEDIA, String GL17STOP) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL17MARC", GL17MARC);
        valueStr.put("GL17TAG", GL17TAG);
        if (GL17DESC != null) {
            valueStr.put("GL17DESC", GL17DESC);
        } else {
            valueStr.put("GL17DESC", null);
        }
        if (GL17TRUC != null) {
            valueStr.put("GL17TRUC", GL17TRUC);
        } else {
            valueStr.put("GL17TRUC", null);
        }
        if (GL17TABNAME != null) {
            valueStr.put("GL17TABNAME", GL17TABNAME);
        } else {
            valueStr.put("GL17TABNAME", null);
        }
        if (GL17ACRO != null) {
            valueStr.put("GL17ACRO ", GL17ACRO);
        } else {
            valueStr.put("GL17ACRO", null);
        }
        if (GL17GRID != null) {
            valueStr.put("GL17GRID ", GL17GRID);
        } else {
            valueStr.put("GL17GRID", null);
        }
        if (GL17LEN != null) {
            valueInt.put("GL17LEN", Integer.parseInt(GL17LEN));
        } else {
            valueInt.put("GL17LEN", null);
        }
        if (GL17IDXLANG != null) {
            valueStr.put("GL17IDXLANG ", GL17IDXLANG);
        } else {
            valueStr.put("GL17IDXLANG", null);
        }
        if (GL17DEFAULT != null) {
            valueStr.put("GL17DEFAULT ", GL17DEFAULT);
        } else {
            valueStr.put("GL17DEFAULT", null);
        }
        if (GL17REMARK != null) {
            valueStr.put("GL17REMARK ", GL17REMARK);
        } else {
            valueStr.put("GL17REMARK", null);
        }
        valueStr.put("GL17REPEAT", GL17REPEAT);
        valueStr.put("GL17AUTFLAG", GL17AUTFLAG);
        valueStr.put("GL17MANDA", GL17MANDA);
        valueStr.put("GL17CPFLAG", GL17CPFLAG);
        valueStr.put("GL17IDXFLAG", GL17IDXFLAG);
        valueStr.put("GL17PARAMIPS", GL17PARAMIPS);
        valueStr.put("GL17KEYWORD", GL17KEYWORD);
        valueStr.put("GL17MEDIA", GL17MEDIA);
        valueStr.put("GL17STOP", GL17STOP);
        String query = QueryBuilder.createInsertQuery("GLTAGP", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }
}
