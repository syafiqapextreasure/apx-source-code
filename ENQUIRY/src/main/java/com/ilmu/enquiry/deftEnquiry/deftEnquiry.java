/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.deftEnquiry;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class deftEnquiry {
    private String AccessionNo;
    private String PatronID;
    private String PatronName;
    private String NoticeType;
    private String CDATE;
    private String CTIME;

    public String getAccessionNo() {
        return Handler.ifIsNull(this.AccessionNo);
    }

    public String getPatronID() {
        return Handler.ifIsNull(this.PatronID);
    }

    public String getPatronName() {
        return Handler.ifIsNull(this.PatronName);
    }

    public String getNoticeType() {
        return Handler.ifIsNull(this.NoticeType);
    }

    public String getCDATE() {
        return Handler.ifIsNull(this.CDATE);
    }

    public String getCTIME() {
        return Handler.ifIsNull(this.CTIME);
    }

    public deftEnquiry(String AccessionNo, String PatronID, String PatronName, String NoticeType, String CDATE, String CTIME) {
        this.AccessionNo = AccessionNo;
        this.PatronID = PatronID;
        this.PatronName = PatronName;
        this.NoticeType = NoticeType;
        this.CDATE = CDATE;
        this.CTIME = CTIME;
    }

    public static List<deftEnquiry> getDefaultEnquiry(String startSentDate, String endSentDate) {
        ArrayList<deftEnquiry> list = new ArrayList<deftEnquiry>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "SELECT CI02DOCNO, CI02PATR, GL14NAME, CI02CDATE, CI02CTIME FROM CICIRC LEFT JOIN GLPATR ON GL14PATR = CI02PATR WHERE CI02FLAG='C' AND CI02SENT3 >= CI02DDATE AND ";
        if (startSentDate != "" && endSentDate != "") {
            System.out.println("inputStartDate and inputEndDate");
            query = String.valueOf(query) + "CI02SENT3 BETWEEN '" + startSentDate + "' AND '" + endSentDate + "' ";
        }
        if (startSentDate != "" && endSentDate == "") {
            System.out.println("inputStartDate");
            query = String.valueOf(query) + "CI02SENT3 >= '" + startSentDate + "' ";
        }
        if (startSentDate == "" && endSentDate != "") {
            System.out.println("inputEndDate");
            query = String.valueOf(query) + "CI02SENT3 <= '" + endSentDate + "' ";
        }
        System.out.println("query getDefaultEnquiry : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String sType = "";
                while (rs.next()) {
                    sType = "3rd Overdue Notification";
                    deftEnquiry loadtabledetail = new deftEnquiry(Handler.ifIsNull(rs.getString("CI02DOCNO")), Handler.ifIsNull(rs.getString("CI02PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), sType, Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI02CDATE"))), Handler.ifIsNull(rs.getString("CI02CTIME")));
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
