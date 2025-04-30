/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadPatronDetails {
    private String Name;
    private String MemberDate;
    private String Expiry;
    private String Category;
    private String Status;

    public String getName() {
        return Handler.ifIsNull(this.Name);
    }

    public String getMemberDate() {
        return Handler.ifIsNull(this.MemberDate);
    }

    public String getExpiry() {
        return Handler.ifIsNull(this.Expiry);
    }

    public String getCategory() {
        return Handler.ifIsNull(this.Category);
    }

    public String getStatus() {
        return Handler.ifIsNull(this.Status);
    }

    public LoadPatronDetails(String Name, String MemberDate, String Expiry, String Category, String Status) {
        this.Name = Name;
        this.MemberDate = MemberDate;
        this.Expiry = Expiry;
        this.Category = Category;
        this.Status = Status;
    }

    public static List<LoadPatronDetails> loadPatr(String id) {
        ArrayList<LoadPatronDetails> list = new ArrayList<LoadPatronDetails>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        DBConnection dbtype = new DBConnection();
        String query = "SELECT GL14PATR, GL14NAME, GL14MEMDATE, GL14EXPDATE , GL07DESC ,GL08DESC FROM GLPATR  LEFT JOIN GLCATE ON GL14CATE = GL07CATE LEFT JOIN GLSTAT ON GL14STAT = GL08STAT WHERE GL14PATR = '" + id + "'  " + "ORDER BY GL14PATR";
        System.out.println("query loadPatr : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    LoadPatronDetails loadtabledetail = new LoadPatronDetails(Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14MEMDATE"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("GL14EXPDATE"))), Handler.ifIsNull(rs.getString("GL07DESC")), Handler.ifIsNull(rs.getString("GL08DESC")));
                    list.add(loadtabledetail);
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
        return list;
    }
}
