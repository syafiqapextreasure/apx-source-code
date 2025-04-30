/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.ordermaintenance;

import com.ilmu.global.connection.DBConnection;
import com.ilmu.serial.ordermaintenance.TableName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class vendor {
    private String vendorCode;
    private String vendorName;
    private String address1;
    private String address2;
    private String address3;
    private String telephoneNumber;
    private String faxNumber;
    private String personInCharge;
    private String designation;
    private String contactNo;
    private String customerReference;
    private String contractBeginDate;
    private String contractEndDate;
    private String dateAdded;
    private String discount;
    private String remark;
    private String bankAccountNo;
    private String postcode;
    private String IPAddress;
    private String binder;
    private String supplier;
    private String publisher;
    private String dateRecorded;
    private String recordedBy;
    private String lastModifyDate;
    private String status;
    private String username;
    private String password;
    private String emailAddress;
    private String defaultFundCode;
    private String bankName;
    private static Map<String, String> columnName = new HashMap(){
        private static final long serialVersionUID = 1L;
    };

    public vendor(String vendorCode, String vendorName, String address1, String address2, String address3, String telephoneNumber, String faxNumber, String personInCharge, String designation, String contactNo, String customerReference, String contractBeginDate, String contractEndDate, String dateAdded, String discount, String remark, String bankAccountNo, String postcode, String iPAddress, String binder, String supplier, String publisher, String dateRecorded, String recordedBy, String lastModifyDate, String status, String username, String password, String emailAddress, String defaultFundCode, String bankName) {
        this.vendorCode = vendorCode;
        this.vendorName = vendorName;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.personInCharge = personInCharge;
        this.designation = designation;
        this.contactNo = contactNo;
        this.customerReference = customerReference;
        this.contractBeginDate = contractBeginDate;
        this.contractEndDate = contractEndDate;
        this.dateAdded = dateAdded;
        this.discount = discount;
        this.remark = remark;
        this.bankAccountNo = bankAccountNo;
        this.postcode = postcode;
        this.IPAddress = iPAddress;
        this.binder = binder;
        this.supplier = supplier;
        this.publisher = publisher;
        this.dateRecorded = dateRecorded;
        this.recordedBy = recordedBy;
        this.lastModifyDate = lastModifyDate;
        this.status = status;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.defaultFundCode = defaultFundCode;
        this.bankName = bankName;
    }

    private vendor(String GL39CODE, String GL39NAME) {
        this.vendorCode = GL39CODE;
        this.vendorName = GL39NAME;
    }

    private vendor(String GL39NAME) {
        this.vendorName = GL39NAME;
    }

    public String getVendorCode() {
        return this.vendorCode;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public String getAddress1() {
        return this.address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public String getAddress3() {
        return this.address3;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public String getFaxNumber() {
        return this.faxNumber;
    }

    public String getPersonInCharge() {
        return this.personInCharge;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public String getCustomerReference() {
        return this.customerReference;
    }

    public String getContractBeginDate() {
        return this.contractBeginDate;
    }

    public String getContractEndDate() {
        return this.contractEndDate;
    }

    public String getDateAdded() {
        return this.dateAdded;
    }

    public String getDiscount() {
        return this.discount;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getBankAccountNo() {
        return this.bankAccountNo;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getIPAddress() {
        return this.IPAddress;
    }

    public String getBinder() {
        return this.binder;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getDateRecorded() {
        return this.dateRecorded;
    }

    public String getRecordedBy() {
        return this.recordedBy;
    }

    public String getLastModifyDate() {
        return this.lastModifyDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getDefaultFundCode() {
        return this.defaultFundCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public static String getColumnName(String name) {
        return columnName.get(name);
    }

    public String toString() {
        return "Vendor [vendorCode=" + this.vendorCode + ", vendorName=" + this.vendorName + ", address1=" + this.address1 + ", address2=" + this.address2 + ", address3=" + this.address3 + ", telephoneNumber=" + this.telephoneNumber + ", faxNumber=" + this.faxNumber + ", personInCharge=" + this.personInCharge + ", designation=" + this.designation + ", contactNo=" + this.contactNo + ", customerReference=" + this.customerReference + ", contractBeginDate=" + this.contractBeginDate + ", contractEndDate=" + this.contractEndDate + ", dateAdded=" + this.dateAdded + ", discount=" + this.discount + ", remark=" + this.remark + ", bankAccountNo=" + this.bankAccountNo + ", postcode=" + this.postcode + ", IPAddress=" + this.IPAddress + ", binder=" + this.binder + ", supplier=" + this.supplier + ", publisher=" + this.publisher + ", dateRecorded=" + this.dateRecorded + ", recordedBy=" + this.recordedBy + ", lastModifyDate=" + this.lastModifyDate + ", status=" + this.status + ", username=" + this.username + ", password=" + this.password + ", emailAddress=" + this.emailAddress + ", defaultFundCode=" + this.defaultFundCode + ", bankName=" + this.bankName + "]";
    }

    private static vendor search(String sql) {
        vendor vendor2 = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                System.out.println(sql);
                if (rs.next()) {
                    vendor2 = new vendor(rs.getString(vendor.getColumnName("vendorCode")), rs.getString(vendor.getColumnName("vendorName")), rs.getString(vendor.getColumnName("address1")), rs.getString(vendor.getColumnName("address2")), rs.getString(vendor.getColumnName("address3")), rs.getString(vendor.getColumnName("telephoneNumber")), rs.getString(vendor.getColumnName("faxNumber")), rs.getString(vendor.getColumnName("personInCharge")), rs.getString(vendor.getColumnName("designation")), rs.getString(vendor.getColumnName("contactNo")), rs.getString(vendor.getColumnName("customerReference")), rs.getString(vendor.getColumnName("contractBeginDate")), rs.getString(vendor.getColumnName("contractEndDate")), rs.getString(vendor.getColumnName("dateAdded")), rs.getString(vendor.getColumnName("discount")), rs.getString(vendor.getColumnName("remark")), rs.getString(vendor.getColumnName("bankAccountNo")), rs.getString(vendor.getColumnName("postcode")), rs.getString(vendor.getColumnName("IPAddress")), rs.getString(vendor.getColumnName("binder")), rs.getString(vendor.getColumnName("supplier")), rs.getString(vendor.getColumnName("publisher")), rs.getString(vendor.getColumnName("dateRecorded")), rs.getString(vendor.getColumnName("recordedBy")), rs.getString(vendor.getColumnName("lastModifyDate")), rs.getString(vendor.getColumnName("status")), rs.getString(vendor.getColumnName("username")), rs.getString(vendor.getColumnName("password")), rs.getString(vendor.getColumnName("emailAddress")), rs.getString(vendor.getColumnName("defaultFundCode")), rs.getString(vendor.getColumnName("bankName")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendor2;
    }

    private static String search_GetName(String sql) {
        String name = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                System.out.println(sql);
                if (rs.next()) {
                    name = rs.getString(vendor.getColumnName("vendorName"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    private static List<vendor> searchList(String sql) {
        ArrayList<vendor> vendorList = new ArrayList<vendor>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    vendorList.add(new vendor(rs.getString(vendor.getColumnName("vendorCode")), rs.getString(vendor.getColumnName("vendorName")), rs.getString(vendor.getColumnName("address1")), rs.getString(vendor.getColumnName("address2")), rs.getString(vendor.getColumnName("address3")), rs.getString(vendor.getColumnName("telephoneNumber")), rs.getString(vendor.getColumnName("faxNumber")), rs.getString(vendor.getColumnName("personInCharge")), rs.getString(vendor.getColumnName("designation")), rs.getString(vendor.getColumnName("contactNo")), rs.getString(vendor.getColumnName("customerReference")), rs.getString(vendor.getColumnName("contractBeginDate")), rs.getString(vendor.getColumnName("contractEndDate")), rs.getString(vendor.getColumnName("dateAdded")), rs.getString(vendor.getColumnName("discount")), rs.getString(vendor.getColumnName("remark")), rs.getString(vendor.getColumnName("bankAccountNo")), rs.getString(vendor.getColumnName("postcode")), rs.getString(vendor.getColumnName("IPAddress")), rs.getString(vendor.getColumnName("binder")), rs.getString(vendor.getColumnName("supplier")), rs.getString(vendor.getColumnName("publisher")), rs.getString(vendor.getColumnName("dateRecorded")), rs.getString(vendor.getColumnName("recordedBy")), rs.getString(vendor.getColumnName("lastModifyDate")), rs.getString(vendor.getColumnName("status")), rs.getString(vendor.getColumnName("username")), rs.getString(vendor.getColumnName("password")), rs.getString(vendor.getColumnName("emailAddress")), rs.getString(vendor.getColumnName("defaultFundCode")), rs.getString(vendor.getColumnName("bankName"))));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    ps.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    ps.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendorList;
    }

    public static vendor searchByVendorCode(String vendorCode) {
        String sql = "SELECT * FROM " + TableName.get("vendor") + " WHERE " + vendor.getColumnName("vendorCode") + " = '" + vendorCode + "'";
        return vendor.search(sql);
    }

    private static List<vendor> searchList2(String sql) {
        ArrayList<vendor> vendorList = new ArrayList<vendor>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println(sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    vendorList.add(new vendor(rs.getString("GL39CODE"), rs.getString("GL39NAME")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return vendorList;
    }

    public static List<vendor> searchListByVendorCode(String vendorCode) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39CODE) LIKE UPPER('" + vendorCode + "%')";
        System.out.println(String.valueOf(vendorCode) + " vendorCode");
        return vendor.searchList2(sql);
    }

    public static List<vendor> searchListByVendorName(String vendorName) {
        String sql = "SELECT GL39CODE, GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39NAME) LIKE UPPER('%" + vendorName + "%')";
        System.out.println(String.valueOf(vendorName) + " vendorName");
        return vendor.searchList2(sql);
    }

    public static String AC07_getVendorNameByVendorCode(String vendorCode) {
        String sql = "SELECT * FROM " + TableName.get("vendor") + " WHERE " + vendor.getColumnName("vendorCode") + " = '" + vendorCode + "'";
        return vendor.search_GetName(sql);
    }

    public static List<vendor> getVendorName(String code) {
        ArrayList<vendor> vendorname = new ArrayList<vendor>();
        String query = "SELECT GL39NAME FROM GLVEND WHERE GL39SUPPLIER = 'Y' AND UPPER(GL39CODE) = UPPER('" + code + "') ";
        System.out.println("getVendorName = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    vendor newvendorname = new vendor(rs.getString("GL39NAME"));
                    vendorname.add(newvendorname);
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
        return vendorname;
    }
}
