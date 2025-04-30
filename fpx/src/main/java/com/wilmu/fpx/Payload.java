/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.wilmu.fpx;

import com.fasterxml.jackson.annotation.JsonProperty;

class Payload {
    private String txnTime;
    private String amount;
    private String txnReference;
    private String txnId;
    private String bankName;
    @JsonProperty(value="orderNo")
    private String orderNo;
    @JsonProperty(value="status")
    private String status;

    Payload() {
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxnTime() {
        return this.txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTxnReference() {
        return this.txnReference;
    }

    public void setTxnReference(String txnReference) {
        this.txnReference = txnReference;
    }

    public String getTxnId() {
        return this.txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
