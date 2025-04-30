/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

public class PayLoadModal {
    private String _token;
    private String orderNo;
    private String amount;
    private String txnId;
    private String txnTime;
    private String txnReference;
    private String status;
    private String bankName;
    private String mail;
    private String phone;
    private String add;
    private String pname;

    public String get_token() {
        return this._token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTxnTime() {
        return this.txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getTxnReference() {
        return this.txnReference;
    }

    public void setTxnReference(String txnReference) {
        this.txnReference = txnReference;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public PayLoadModal(String orderNo, String amount, String txnId, String txnTime, String txnReference, String status, String bankName, String mail, String phone, String add, String pname) {
        this.orderNo = orderNo;
        this.amount = amount;
        this.txnId = txnId;
        this.txnTime = txnTime;
        this.txnReference = txnReference;
        this.status = status;
        this.bankName = bankName;
        this.mail = mail;
        this.phone = phone;
        this.add = add;
        this.pname = pname;
    }

    public String toString() {
        return "PayLoadModal [_token=" + this._token + ", orderNo=" + this.orderNo + ", amount=" + this.amount + ", txnId=" + this.txnId + ", txnTime=" + this.txnTime + ", txnReference=" + this.txnReference + ", status=" + this.status + ", bankName=" + this.bankName + ", mail=" + this.mail + ", phone=" + this.phone + ", add=" + this.add + ", pname=" + this.pname + "]";
    }
}
