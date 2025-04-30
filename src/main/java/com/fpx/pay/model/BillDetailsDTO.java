package com.fpx.pay.model;

public class BillDetailsDTO {
    private String billNo;
    private String billPatr;
    private String billStatus;
    private String billEmail;
    private String billPhone;
    private String expDate;

    // Constructor
    public BillDetailsDTO(String billNo, String billPatr, String billStatus, String billEmail, String billPhone, String expDate) {
        this.billNo = billNo;
        this.billPatr = billPatr;
        this.billStatus = billStatus;
        this.billEmail = billEmail;
        this.billPhone = billPhone;
        this.expDate = expDate;
    }

    // Getters and Setters
    public String getBillNo() { return billNo; }
    public void setBillNo(String billNo) { this.billNo = billNo; }

    public String getBillPatr() { return billPatr; }
    public void setBillPatr(String billPatr) { this.billPatr = billPatr; }

    public String getBillStatus() { return billStatus; }
    public void setBillStatus(String billStatus) { this.billStatus = billStatus; }

    public String getBillEmail() { return billEmail; }
    public void setBillEmail(String billEmail) { this.billEmail = billEmail; }

    public String getBillPhone() { return billPhone; }
    public void setBillPhone(String billPhone) { this.billPhone = billPhone; }

    public String getExpDate() { return expDate; }
    public void setExpDate(String expDate) { this.expDate = expDate; }
}
