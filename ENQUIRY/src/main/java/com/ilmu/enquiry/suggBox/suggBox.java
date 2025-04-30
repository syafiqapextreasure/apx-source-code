/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.suggBox;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
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

public class suggBox {
    private String patr;
    private String patrName;
    private String date;
    private String time;
    private String suggestion;
    private String replyStatus;

    public String getpatr() {
        return Handler.ifIsNull(this.patr);
    }

    public String getpatrName() {
        return Handler.ifIsNull(this.patrName);
    }

    public String getdate() {
        return Handler.ifIsNull(this.date);
    }

    public String gettime() {
        return Handler.ifIsNull(this.time);
    }

    public String getsuggestion() {
        return Handler.ifIsNull(this.suggestion);
    }

    public String getreplyStatus() {
        return Handler.ifIsNull(this.replyStatus);
    }

    public suggBox(String patr, String patrName, String date, String time, String suggestion, String replyStatus) {
        this.patr = patr;
        this.patrName = patrName;
        this.date = date;
        this.time = time;
        this.suggestion = suggestion;
        this.replyStatus = replyStatus;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<suggBox> getSuggectionBox(String startSentDate, String endSentDate) {
        ArrayList<suggBox> list = new ArrayList<suggBox>();
        String query = "SELECT OP02PATR, GL14NAME, OP02DATE, OP02TIME, OP02SUGGEST, OP02REMARKS FROM OPSBOX LEFT JOIN GLPATR ON OP02PATR = GL14PATR WHERE ";
        if (!startSentDate.isEmpty() && !endSentDate.isEmpty()) {
            System.out.println("inputStartDate and inputEndDate");
            query = String.valueOf(query) + "OP02DATE BETWEEN '" + startSentDate + "' AND '" + endSentDate + "' ";
        }
        if (!startSentDate.isEmpty() && endSentDate.isEmpty()) {
            System.out.println("inputStartDate");
            query = String.valueOf(query) + "OP02DATE >= '" + startSentDate + "' ";
        }
        if (startSentDate.isEmpty() && !endSentDate.isEmpty()) {
            System.out.println("inputEndDate");
            query = String.valueOf(query) + "OP02DATE <= '" + endSentDate + "' ";
        }
        query = String.valueOf(query) + "ORDER BY OP02DATE DESC";
        System.out.println("query getSuggectionBox : " + query);
        try {
            Throwable throwable = null;
            Object var5_7 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    block24: {
                        Statement stmt = conn.createStatement();
                        try {
                            try (ResultSet rs = stmt.executeQuery(query);){
                                while (rs.next()) {
                                    suggBox loadtabledetail = new suggBox(Handler.ifIsNull(rs.getString("OP02PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("OP02DATE"))), Handler.ifIsNull(rs.getString("OP02TIME")), Handler.ifIsNull(rs.getString("OP02SUGGEST")), Handler.ifIsNull(rs.getString("OP02REMARKS")));
                                    list.add(loadtabledetail);
                                }
                            }
                            if (stmt == null) break block24;
                        }
                        catch (Throwable throwable2) {
                            if (throwable == null) {
                                throwable = throwable2;
                            } else if (throwable != throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            if (stmt == null) throw throwable;
                            stmt.close();
                            throw throwable;
                        }
                        stmt.close();
                    }
                    if (conn == null) return list;
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                    } else if (throwable != throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return list;
            }
            catch (Throwable throwable4) {
                if (throwable == null) {
                    throwable = throwable4;
                    throw throwable;
                }
                if (throwable == throwable4) throw throwable;
                throwable.addSuppressed(throwable4);
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<suggBox> editView(String patr, String date, String time) {
        ArrayList<suggBox> list = new ArrayList<suggBox>();
        String query = "SELECT OP02PATR, GL14NAME, OP02DATE, OP02TIME, OP02SUGGEST, OP02REMARKS FROM OPSBOX LEFT JOIN GLPATR ON GL14PATR = OP02PATR WHERE OP02PATR = '" + patr + "' AND OP02DATE = '" + date + "' " + "AND OP02TIME = '" + time + "'";
        System.out.println("query editView : " + query);
        try {
            Throwable throwable = null;
            Object var6_8 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    block21: {
                        Statement stmt = conn.createStatement();
                        try {
                            try (ResultSet rs = stmt.executeQuery(query);){
                                while (rs.next()) {
                                    suggBox loadtabledetail = new suggBox(Handler.ifIsNull(rs.getString("OP02PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("OP02DATE"))), Handler.ifIsNull(rs.getString("OP02TIME")), Handler.ifIsNull(rs.getString("OP02SUGGEST")), Handler.ifIsNull(rs.getString("OP02REMARKS")));
                                    list.add(loadtabledetail);
                                }
                            }
                            if (stmt == null) break block21;
                        }
                        catch (Throwable throwable2) {
                            if (throwable == null) {
                                throwable = throwable2;
                            } else if (throwable != throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            if (stmt == null) throw throwable;
                            stmt.close();
                            throw throwable;
                        }
                        stmt.close();
                    }
                    if (conn == null) return list;
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                    } else if (throwable != throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return list;
            }
            catch (Throwable throwable4) {
                if (throwable == null) {
                    throwable = throwable4;
                    throw throwable;
                }
                if (throwable == throwable4) throw throwable;
                throwable.addSuppressed(throwable4);
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updatingSugg(String patr, String date, String time, String reply) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("OP02PATR", patr);
        condition.put("OP02DATE", date);
        condition.put("OP02TIME", time);
        valueStr.put("OP02REMARKS", reply);
        valueStr.put("OP02STATUS", "R");
        valueStr.put("OP02RDATE", DateTime.getTodaySystemDate());
        valueStr.put("OP02RTIME", DateTime.getTodayTime());
        String query = QueryBuilder.createUpdateQuery("OPSBOX", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static String deleteSuggBox(String patr, String date, String time) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("OP02PATR", patr);
        map.put("OP02DATE", date);
        map.put("OP02TIME", time);
        String query = QueryBuilder.createDeleteQuery("OPSBOX", map);
        System.out.println("Query ::" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return query;
    }
}
