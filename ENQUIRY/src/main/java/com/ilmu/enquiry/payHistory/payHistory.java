/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.payHistory;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class payHistory {
    private String InvoiceNo;
    private String InvoiceDate;
    private String OrderNo;
    private String ChequeNo;
    private String ChequeDate;
    private String VoucherNo;
    private String VoucherDate;
    private String RefNo;
    private String RefDate;
    private String Amount;
    private String Vendor;

    public String getInvoiceNo() {
        return Handler.ifIsNull(this.InvoiceNo);
    }

    public String getInvoiceDate() {
        return Handler.ifIsNull(this.InvoiceDate);
    }

    public String getOrderNo() {
        return Handler.ifIsNull(this.OrderNo);
    }

    public String getChequeNo() {
        return Handler.ifIsNull(this.ChequeNo);
    }

    public String getChequeDate() {
        return Handler.ifIsNull(this.ChequeDate);
    }

    public String getVoucherNo() {
        return Handler.ifIsNull(this.VoucherNo);
    }

    public String getVoucherDate() {
        return Handler.ifIsNull(this.VoucherDate);
    }

    public String getRefNo() {
        return Handler.ifIsNull(this.RefNo);
    }

    public String getRefDate() {
        return Handler.ifIsNull(this.RefDate);
    }

    public String getAmount() {
        return Handler.ifIsNull(this.Amount);
    }

    public String getVendor() {
        return Handler.ifIsNull(this.Vendor);
    }

    public payHistory(String InvoiceNo, String InvoiceDate, String OrderNo, String ChequeNo, String ChequeDate, String VoucherNo, String VoucherDate, String RefNo, String RefDate, String Amount, String Vendor) {
        this.InvoiceNo = InvoiceNo;
        this.InvoiceDate = InvoiceDate;
        this.OrderNo = OrderNo;
        this.ChequeNo = ChequeNo;
        this.ChequeDate = ChequeDate;
        this.VoucherNo = VoucherNo;
        this.VoucherDate = VoucherDate;
        this.RefNo = RefNo;
        this.RefDate = RefDate;
        this.Amount = Amount;
        this.Vendor = Vendor;
    }

    public static List<payHistory> getpayHistoryTable(String module, String vendor, String radioOption1, String radioOption2, String radioOption3, String inputstartDoc, String inputendDoc, String startDate, String endDate) {
        ArrayList<payHistory> list = new ArrayList<payHistory>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        String msField = "";
        String sMinInput = "";
        String sMaxInput = "";
        if (module.equals("acq")) {
            if (radioOption1 != null) {
                if (radioOption1.equals("doctRange")) {
                    if (radioOption2.contentEquals("doctRangeinv")) {
                        msField = "AC05INVNO";
                    } else if (radioOption2.contentEquals("doctRangecheq")) {
                        msField = "AC05BDRAF";
                    } else if (radioOption2.contentEquals("doctRangevoc")) {
                        msField = "AC05VOUCHER";
                    } else if (radioOption2.contentEquals("doctRangeref")) {
                        msField = "AC05REFERNO";
                    }
                    sMinInput = inputstartDoc;
                    sMaxInput = inputendDoc;
                    if (sMinInput.compareTo(sMaxInput) > 0) {
                        sMinInput = inputendDoc;
                        sMaxInput = inputstartDoc;
                    }
                } else if (radioOption1.equals("dateRange")) {
                    if (radioOption3.contentEquals("dateRangeinvo")) {
                        msField = "AC05INVDATE";
                    } else if (radioOption3.contentEquals("dateRangeRef")) {
                        msField = "AC05RDATE";
                    } else if (radioOption3.contentEquals("dateRangevoc")) {
                        msField = "AC05VCHDATE";
                    } else if (radioOption3.contentEquals("dateRangeproc")) {
                        msField = "AC05PDATE";
                    } else if (radioOption3.contentEquals("dateRangecheq")) {
                        msField = "AC05BDATE";
                    }
                    sMinInput = startDate;
                    sMaxInput = endDate;
                    if (sMinInput.compareTo(sMaxInput) > 0) {
                        sMinInput = endDate;
                        sMaxInput = startDate;
                    }
                }
            }
            query = "SELECT AC05INVNO AS INVNO, AC05INVDATE AS INVDATE, AC05ORDER AS ORDERNO, AC05BDRAF AS BDRAF, AC05BDATE AS BDATE, AC05VOUCHER AS VOUCHER, AC05VCHDATE AS VCHDATE, AC05REFERNO AS REFERNO, AC05RDATE AS RDATE, AC05AMT AS AMT, GL39NAME AS NAME FROM ACINVO LEFT JOIN GLVEND ON GL39CODE = AC05SUPPLIER WHERE UPPER(AC05SUPPLIER) = ('" + vendor + "') ";
            if (radioOption1 != null) {
                query = String.valueOf(query) + "AND UPPER(" + msField + ") BETWEEN UPPER('" + sMinInput + "') AND UPPER('" + sMaxInput + "') ";
            }
            query = String.valueOf(query) + "AND AC05STAT = 'P'";
        } else if (module.equals("ser")) {
            if (radioOption1 != null) {
                if (radioOption1.equals("doctRange")) {
                    if (radioOption2.contentEquals("doctRangeinv")) {
                        msField = "SE07INVNO";
                    } else if (radioOption2.contentEquals("doctRangecheq")) {
                        msField = "SE07BDRAF";
                    } else if (radioOption2.contentEquals("doctRangevoc")) {
                        msField = "SE07VOUCHER";
                    } else if (radioOption2.contentEquals("doctRangeref")) {
                        msField = "SE07REFERNO";
                    }
                    sMinInput = inputstartDoc;
                    sMaxInput = inputendDoc;
                    if (sMinInput.compareTo(sMaxInput) > 0) {
                        sMinInput = inputendDoc;
                        sMaxInput = inputstartDoc;
                    }
                } else if (radioOption1.equals("dateRange")) {
                    if (radioOption3.contentEquals("dateRangeinvo")) {
                        msField = "SE07INVDATE";
                    } else if (radioOption3.contentEquals("dateRangeRef")) {
                        msField = "SE07RDATE";
                    } else if (radioOption3.contentEquals("dateRangevoc")) {
                        msField = "SE07VCHDATE";
                    } else if (radioOption3.contentEquals("dateRangeproc")) {
                        msField = "SE07PDATE";
                    } else if (radioOption3.contentEquals("dateRangecheq")) {
                        msField = "SE07BDATE";
                    }
                    sMinInput = startDate;
                    sMaxInput = endDate;
                    if (sMinInput.compareTo(sMaxInput) > 0) {
                        sMinInput = endDate;
                        sMaxInput = startDate;
                    }
                }
            }
            query = "SELECT SE07INVNO AS INVNO, SE07INVDATE AS INVDATE, SE07ORDER AS ORDERNO, SE07BDRAF AS BDRAF, SE07BDATE AS BDATE, SE07VOUCHER AS VOUCHER, SE07VCHDATE AS VCHDATE, SE07REFERNO AS REFERNO, SE07RDATE AS RDATE, SE07AMT AS AMT, GL39NAME AS NAME FROM SEINVO LEFT JOIN GLVEND ON GL39CODE = SE07SUPPLIER WHERE UPPER(SE07SUPPLIER) = ('" + vendor + "') ";
            if (radioOption1 != null) {
                query = String.valueOf(query) + "AND UPPER(" + msField + ") BETWEEN UPPER('" + sMinInput + "') AND UPPER('" + sMaxInput + "') ";
            }
            query = String.valueOf(query) + "AND SE07STAT  = 'P'";
        }
        System.out.println("query getpayHistoryTable : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    payHistory loadtabledetail = new payHistory(Handler.ifIsNull(rs.getString("INVNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("INVDATE"))), Handler.ifIsNull(rs.getString("ORDERNO")), Handler.ifIsNull(rs.getString("BDRAF")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BDATE"))), Handler.ifIsNull(rs.getString("VOUCHER")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("VCHDATE"))), Handler.ifIsNull(rs.getString("REFERNO")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("RDATE"))), Handler.ifIsNull(rs.getString("AMT")), Handler.ifIsNull(rs.getString("NAME")));
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
