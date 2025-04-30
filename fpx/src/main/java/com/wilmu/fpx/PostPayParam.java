/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

public class PostPayParam {
    private String Subsysid;
    private String Subpassword;
    private String Orderno;
    private String Description;
    private String txnTime;
    private String Amount;

    public String getSubsysid() {
        return this.Subsysid;
    }

    public void setSubsysid(String subsysid) {
        this.Subsysid = subsysid;
    }

    public String getSubpassword() {
        return this.Subpassword;
    }

    public void setSubpassword(String subpassword) {
        this.Subpassword = subpassword;
    }

    public String getOrderno() {
        return this.Orderno;
    }

    public void setOrderno(String orderno) {
        this.Orderno = orderno;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getTxnTime() {
        return this.txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getAmount() {
        return this.Amount;
    }

    public void setAmount(String amount) {
        this.Amount = amount;
    }
}
