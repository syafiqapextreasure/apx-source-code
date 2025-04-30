/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.cirActivity;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class borrower {
    private String PatronID;
    private String Name;
    private String Department;
    private String DepartmentCode;
    private String Designation;
    private String DesignationCode;
    private String Borrowing;
    private String LostItem;

    public String getPatronID() {
        return Handler.ifIsNull(this.PatronID);
    }

    public String getName() {
        return Handler.ifIsNull(this.Name);
    }

    public String getDepartment() {
        return Handler.ifIsNull(this.Department);
    }

    public String getDepartmentCode() {
        return Handler.ifIsNull(this.DepartmentCode);
    }

    public String getDesignation() {
        return Handler.ifIsNull(this.Designation);
    }

    public String getDesignationCode() {
        return Handler.ifIsNull(this.DesignationCode);
    }

    public String getBorrowing() {
        return Handler.ifIsNull(this.Borrowing);
    }

    public String getLostItem() {
        return Handler.ifIsNull(this.LostItem);
    }

    public borrower(String PatronID, String Borrowing, String Name, String DepartmentCode, String Department, String DesignationCode, String Designation, String LostItem) {
        this.PatronID = PatronID;
        this.Borrowing = Borrowing;
        this.Name = Name;
        this.DepartmentCode = DepartmentCode;
        this.Department = Department;
        this.DesignationCode = DesignationCode;
        this.Designation = Designation;
        this.LostItem = LostItem;
    }

    public borrower(String LostItem) {
        this.LostItem = LostItem;
    }

    public static List<borrower> getborrowerList(String startSentDate, String endSentDate, String checkpatcat, String patcate, String checkbrnch, String brnch, String minBorrow) {
        ArrayList<borrower> list = new ArrayList<borrower>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        query = "SELECT CI02PATR, COUNT(CI02PATR) AS Borrow,  GL14NAME, GL11DEPT, GL11NAME, GL69DESG, GL69DESC FROM CICIRC LEFT JOIN GLPATR ON GL14PATR = CI02PATR LEFT JOIN GLDEPT ON GL11DEPT = GL14DEPT LEFT JOIN GLDESG ON GL69DESC = GL14DESC WHERE ";
        if (startSentDate != "" && endSentDate != "") {
            System.out.println("inputStartDate and inputEndDate");
            query = String.valueOf(query) + "CI02CDATE BETWEEN '" + startSentDate + "' AND '" + endSentDate + "' ";
        }
        if (startSentDate != "" && endSentDate == "") {
            System.out.println("inputStartDate");
            query = String.valueOf(query) + "CI02CDATE >= '" + startSentDate + "' ";
        }
        if (startSentDate == "" && endSentDate != "") {
            System.out.println("inputEndDate");
            query = String.valueOf(query) + "CI02CDATE <= '" + endSentDate + "' ";
        }
        if (checkpatcat.equals("Y")) {
            query = String.valueOf(query) + "AND GL14CATE = '" + patcate + "' ";
        }
        if (checkbrnch.equals("Y")) {
            query = String.valueOf(query) + "AND GL14BRNC = '" + brnch + "' ";
        }
        query = String.valueOf(query) + "GROUP BY CI02PATR, GL14NAME, GL11DEPT, GL11NAME, GL69DESG, GL69DESC " + "ORDER BY 2 DESC ";
        System.out.println("query getborrowerList : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int lMinRange;
                    String sPatronID = "";
                    String sPatronName = "";
                    String borrower2 = "";
                    String sDepartment = "";
                    String sDepartmentCode = "";
                    String sDesignation = "";
                    String sDesignationcode = "";
                    int iLostBook = 0;
                    int lTimesBorrowed = Integer.parseInt(Handler.ifIsNull(rs.getString("Borrow")));
                    if (lTimesBorrowed < (lMinRange = Integer.parseInt(minBorrow))) continue;
                    sPatronID = Handler.ifIsNull(rs.getString("CI02PATR"));
                    borrower2 = Handler.ifIsNull(rs.getString("Borrow"));
                    sPatronName = Handler.ifIsNull(rs.getString("GL14NAME"));
                    sDepartmentCode = Handler.ifIsNull(rs.getString("GL11DEPT"));
                    sDepartment = Handler.ifIsNull(rs.getString("GL11NAME"));
                    sDesignationcode = Handler.ifIsNull(rs.getString("GL69DESG"));
                    sDesignation = Handler.ifIsNull(rs.getString("GL69DESC"));
                    iLostBook = borrower.GetLostItemCount(sPatronID);
                    borrower loadtabledetail = new borrower(sPatronID, borrower2, sPatronName, sDepartmentCode, sDepartment, sDesignationcode, sDesignation, String.valueOf(iLostBook));
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

    public static int GetLostItemCount(String vsPatronID) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT COUNT(RE01PATR) AS lost FROM RETRXN WHERE RE01PATR = '" + vsPatronID + "' " + "AND RE01CODE = '104'";
        System.out.println("SQL GetLostItemCount" + sSQLStmt);
        int GetLostItemCount = 0;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    GetLostItemCount = rs.getInt("lost");
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
        return GetLostItemCount;
    }
}
