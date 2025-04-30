/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.ordermaintenance;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditDebit {
    private String NoteNo = null;
    private String Vendor = null;
    private String NoteType = null;
    private String Status = null;
    private String ReceivedDate = null;
    private String InvoiceNumber = null;
    private String UsedDate = null;
    private String Currency = null;
    private String ForeignAmount = null;
    private String LocalAmount = null;
    private String VendorName = null;
    private String Remark = null;
    private String ExchangeRate = null;

    public String getNoteNo() {
        return Handler.ifIsNull(this.NoteNo);
    }

    public String getVendor() {
        return Handler.ifIsNull(this.Vendor);
    }

    public String getNoteType() {
        return Handler.ifIsNull(this.NoteType);
    }

    public String getStatus() {
        return Handler.ifIsNull(this.Status);
    }

    public String getReceivedDate() {
        return Handler.ifIsNull(this.ReceivedDate);
    }

    public String getInvoiceNumber() {
        return Handler.ifIsNull(this.InvoiceNumber);
    }

    public String getUsedDate() {
        return Handler.ifIsNull(this.UsedDate);
    }

    public String getCurrency() {
        return Handler.ifIsNull(this.Currency);
    }

    public String getForeignAmount() {
        return Handler.ifIsNull(this.ForeignAmount);
    }

    public String getLocalAmount() {
        return Handler.ifIsNull(this.LocalAmount);
    }

    public String getVendorName() {
        return Handler.ifIsNull(this.VendorName);
    }

    public String getRemark() {
        return Handler.ifIsNull(this.Remark);
    }

    public String getExchangeRate() {
        return Handler.ifIsNull(this.ExchangeRate);
    }

    public CreditDebit(String NoteNo, String Vendor, String NoteType, String Status, String ReceivedDate, String InvoiceNumber, String UsedDate, String Currency, String ForeignAmount, String LocalAmount) {
        this.NoteNo = NoteNo;
        this.Vendor = Vendor;
        this.NoteType = NoteType;
        this.Status = Status;
        this.ReceivedDate = ReceivedDate;
        this.InvoiceNumber = InvoiceNumber;
        this.UsedDate = UsedDate;
        this.Currency = Currency;
        this.ForeignAmount = ForeignAmount;
        this.LocalAmount = LocalAmount;
    }

    public CreditDebit(String NoteType, String Vendor, String NoteNo, String ReceivedDate, String Currency, String ForeignAmount, String LocalAmount, String Remark) {
        this.NoteType = NoteType;
        this.Vendor = Vendor;
        this.NoteNo = NoteNo;
        this.ReceivedDate = ReceivedDate;
        this.Currency = Currency;
        this.ForeignAmount = ForeignAmount;
        this.LocalAmount = LocalAmount;
        this.Remark = Remark;
    }

    public static List<CreditDebit> viewMainTable() throws SQLException {
        ArrayList<CreditDebit> list = new ArrayList<CreditDebit>();
        String query = "SELECT AC16NOTENO, AC16VEND, GL39NAME,  CASE WHEN AC16NOTETYPE = 'C' THEN 'Credit' WHEN AC16NOTETYPE = 'D' THEN 'Debit' END AS NOTETYPE, CASE WHEN AC16NOTESTATUS = 'R' THEN 'Ready To Use' WHEN AC16NOTESTATUS = 'U' THEN 'Used'END AS NOTESTATUS, AC16REVDATE, AC16INVNO, AC16USEDATE, AC16FOREX, GL24DESC, AC16FAMOUNT, AC16LAMOUNT FROM ACCDNOTE CDNOTE, GLVEND VEND, GLFORX FORX WHERE AC16VEND = GL39CODE AND AC16FOREX = GL24FOREX";
        System.out.println("query = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CreditDebit cddblist = new CreditDebit(rs.getString("AC16NOTENO"), rs.getString("AC16VEND"), rs.getString("NOTETYPE"), rs.getString("NOTESTATUS"), DateFormatter.DBToDisplayFormat(rs.getString("AC16REVDATE")), Handler.ifIsNull(rs.getString("AC16INVNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("AC16USEDATE"))), rs.getString("AC16FOREX"), rs.getString("AC16FAMOUNT"), rs.getString("AC16LAMOUNT"));
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

    public static List<CreditDebit> viewDetalByNoteNo(String noteNo) {
        ArrayList<CreditDebit> viewDetalByNoteNo = new ArrayList<CreditDebit>();
        String query = "SELECT SE16NOTETYPE, SE16VEND, SE16NOTENO, SE16REVDATE, SE16FOREX, SE16FAMOUNT, SE16LAMOUNT, SE16REMARK FROM SECDNOTE WHERE SE16NOTENO = '" + noteNo + "'";
        System.out.println("viewDetalByNoteNo = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CreditDebit newviewDetalByNoteNo = new CreditDebit(rs.getString("SE16NOTETYPE"), rs.getString("SE16VEND"), rs.getString("SE16NOTENO"), rs.getString("SE16REVDATE"), rs.getString("SE16FOREX"), rs.getString("SE16FAMOUNT"), rs.getString("SE16LAMOUNT"), rs.getString("SE16REMARK"));
                    viewDetalByNoteNo.add(newviewDetalByNoteNo);
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
        return viewDetalByNoteNo;
    }

    public static boolean deleteNote(String noteType) {
        String query = "DELETE FROM ACCDNOTE WHERE AC16NOTENO = '" + noteType + "'";
        System.out.println("deleteNoteNo = " + query);
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                return false;
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
        return true;
    }

    public static List<CreditDebit> mainSearch(String condition) {
        if (condition.isEmpty()) {
            return null;
        }
        ArrayList<CreditDebit> list = new ArrayList<CreditDebit>();
        String queryMainSearch = "SELECT SE16NOTENO, SE16VEND, GL39NAME, CASE WHEN SE16NOTETYPE = 'C' THEN 'Credit' WHEN SE16NOTETYPE = 'D' THEN 'Debit' END AS NOTETYPE, CASE WHEN SE16NOTESTATUS = 'R' THEN 'Ready To Use' WHEN SE16NOTESTATUS = 'U' THEN 'Used' END AS NOTESTATUS, SE16REVDATE, SE16INVNO, SE16USEDATE, SE16FOREX, GL24DESC, SE16FAMOUNT, SE16LAMOUNT FROM SECDNOTE LEFT JOIN GLVEND ON SE16VEND = GL39CODE LEFT JOIN GLFORX ON SE16FOREX = GL24FOREX WHERE " + condition;
        System.out.println("queryMainSearch = " + queryMainSearch);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(queryMainSearch);
                while (rs.next()) {
                    CreditDebit newMainSearch = new CreditDebit(rs.getString("SE16NOTENO"), rs.getString("SE16VEND"), rs.getString("NOTETYPE"), rs.getString("NOTESTATUS"), DateFormatter.DBToDisplayFormat(rs.getString("SE16REVDATE")), Handler.ifIsNull(rs.getString("SE16INVNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE16USEDATE"))), rs.getString("SE16FOREX"), rs.getString("SE16FAMOUNT"), rs.getString("SE16LAMOUNT"));
                    list.add(newMainSearch);
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

    public static List<CreditDebit> searchByVendorCode(String criteria) {
        return CreditDebit.mainSearch("UPPER(SE16VEND) LIKE UPPER('%" + criteria + "%') ");
    }

    public static List<CreditDebit> searchByNoteNo(String criteria) {
        return CreditDebit.mainSearch("SE16NOTENO LIKE '%" + criteria + "%' ");
    }

    public static List<CreditDebit> searchByReceivedDate(String criteria) {
        return CreditDebit.mainSearch("SE16REVDATE LIKE '%" + DateFormatter.displayToDBFormat(criteria) + "%' ");
    }

    public static List<CreditDebit> searchByUsedDate(String criteria) {
        return CreditDebit.mainSearch("SE16USEDATE LIKE '%" + DateFormatter.displayToDBFormat(criteria) + "%' ");
    }

    public static boolean addcreditdebit(String noteType, String note_no, String inputVendorCode, String received_date, String currency, String foreignAmount, String localAmount, String remark, String userid) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        if (remark == null) {
            valueStr.put("SE16NOTENO", note_no);
            valueStr.put("SE16VEND", inputVendorCode);
            valueStr.put("SE16NOTETYPE", noteType);
            valueStr.put("SE16REVDATE", received_date);
            valueStr.put("SE16FOREX", currency);
            valueStr.put("SE16LAMOUNT", localAmount);
            valueStr.put("SE16FAMOUNT", foreignAmount);
            valueStr.put("SE16NOTESTATUS", "R");
            valueStr.put("SE16RECBY", userid);
            valueStr.put("SE16RECDATE", received_date);
        } else {
            valueStr.put("SE16NOTENO", note_no);
            valueStr.put("SE16VEND", inputVendorCode);
            valueStr.put("SE16NOTETYPE", noteType);
            valueStr.put("SE16REVDATE", received_date);
            valueStr.put("SE16FOREX", currency);
            valueStr.put("SE16LAMOUNT", localAmount);
            valueStr.put("SE16FAMOUNT", foreignAmount);
            valueStr.put("SE16NOTESTATUS", "R");
            valueStr.put("SE16RECBY", userid);
            valueStr.put("SE16RECDATE", received_date);
            valueStr.put("SE16REMARK", remark);
        }
        String query = QueryBuilder.createInsertQuery("SECDNOTE", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static List<CreditDebit> dispalyBack(String note_no) {
        ArrayList<CreditDebit> listdisplay = new ArrayList<CreditDebit>();
        String query = "SELECT SE16NOTENO, SE16VEND, GL39NAME, CASE WHEN SE16NOTETYPE = 'C' THEN 'Credit' WHEN SE16NOTETYPE = 'D' THEN 'Debit' END AS NOTETYPE, CASE WHEN SE16NOTESTATUS = 'R' THEN 'Ready To Use' WHEN SE16NOTESTATUS = 'U' THEN 'Used' END AS NOTESTATUS, SE16REVDATE, SE16INVNO, SE16USEDATE, SE16FOREX, GL24DESC, SE16FAMOUNT, SE16LAMOUNT FROM SECDNOTE LEFT JOIN GLVEND ON SE16VEND = GL39CODE LEFT JOIN GLFORX ON SE16FOREX = GL24FOREX WHERE SE16NOTENO = '" + note_no + "'";
        System.out.println("querydispalyBack = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    CreditDebit partdispalyBack = new CreditDebit(rs.getString("SE16NOTENO"), rs.getString("SE16VEND"), rs.getString("NOTETYPE"), rs.getString("NOTESTATUS"), DateFormatter.DBToDisplayFormat(rs.getString("SE16REVDATE")), Handler.ifIsNull(rs.getString("SE16INVNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("SE16USEDATE"))), rs.getString("SE16FOREX"), rs.getString("SE16FAMOUNT"), rs.getString("SE16LAMOUNT"));
                    listdisplay.add(partdispalyBack);
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
        return listdisplay;
    }

    public static boolean updatingCreditDebit(String noteType, String note_no, String inputVendorCode, String received_date, String currency, String foreignAmount, String localAmount, String remark, String userid) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE16NOTENO", note_no);
        if (remark == null) {
            System.out.println("NULLLLLLLLL REMARKS");
            valueStr.put("SE16VEND", inputVendorCode);
            valueStr.put("SE16NOTETYPE", noteType);
            valueStr.put("SE16REVDATE", received_date);
            valueStr.put("SE16FOREX", currency);
            valueStr.put("SE16LAMOUNT", localAmount);
            valueStr.put("SE16FAMOUNT", foreignAmount);
            valueStr.put("SE16NOTESTATUS", "R");
            valueStr.put("SE16RECBY", userid);
            valueStr.put("SE16RECDATE", received_date);
        } else {
            System.out.println("NOOOOOOOOOOOTTT NULLLL REMARKS");
            valueStr.put("SE16VEND", inputVendorCode);
            valueStr.put("SE16NOTETYPE", noteType);
            valueStr.put("SE16REVDATE", received_date);
            valueStr.put("SE16FOREX", currency);
            valueStr.put("SE16LAMOUNT", localAmount);
            valueStr.put("SE16FAMOUNT", foreignAmount);
            valueStr.put("SE16NOTESTATUS", "R");
            valueStr.put("SE16RECBY", userid);
            valueStr.put("SE16RECDATE", received_date);
            valueStr.put("SE16REMARK", remark);
        }
        String query = QueryBuilder.createUpdateQuery("SECDNOTE", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
