/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global.serial;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FreqType {
    private String freqTime = null;
    private String freqType = null;

    public String getfreqTime() {
        return Handler.ifIsNull(this.freqTime);
    }

    public String getfreqType() {
        return Handler.ifIsNull(this.freqType);
    }

    public FreqType(String freqTime, String freqType) {
        this.freqTime = freqTime;
        this.freqType = freqType;
    }

    public static List<FreqType> freqtypentime(String freq) {
        ArrayList<FreqType> list = new ArrayList<FreqType>();
        String query = "  SELECT GL49TIME, GL49PTYPE FROM GLFREQ WHERE  GL49FREQ = '" + freq + "'";
        System.out.println("freqtypentime = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    FreqType cddblist = new FreqType(rs.getString("GL49TIME"), rs.getString("GL49PTYPE"));
                    list.add(cddblist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
