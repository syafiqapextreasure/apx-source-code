/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.counterService.charging.model;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;

public class Charging {
    private Patron patr;
    String accessionNo;
    String title;
    String chargeDate;
    String chargeTime;
    String dueDate;
    String dueTime;
    String status;
    String chargingErrorMsg;

    public Patron getPatr() {
        return this.patr;
    }

    public void setPatr(Patron patr) {
        this.patr = patr;
    }

    public String getAccessionNo() {
        return this.accessionNo;
    }

    public void setAccessionNo(String accessionNo) {
        this.accessionNo = accessionNo;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChargeDate() {
        return this.chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getChargeTime() {
        return this.chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return this.dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargingErrorMsg() {
        return this.chargingErrorMsg;
    }

    public void setChargingErrorMsg(String chargingErrorMsg) {
        this.chargingErrorMsg = chargingErrorMsg;
    }
}
