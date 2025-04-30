/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronEligibility;

import java.io.Serializable;
import java.util.Date;

public class PatronEligibility
implements Serializable {
    private static final long serialVersionUID = 1L;
    private String patronCategory;
    private String itemCategory;
    private String smdCode;
    private String branch;
    private String recordDate;
    private String recordBy;
    private String branchDesc;
    private String itemCategoryDesc;
    private String smdCodeDesc;
    private String patronCategoryDesc;
    private String loanPeriodType;
    private String reserveAllow;
    private String overdueAllow;
    private String includeLostFine;
    private String loanPeriodString;
    private int loanPeriod;
    private int eligibilityNum;
    private int renewalNum;
    private int gracePeriodOverdue1;
    private int gracePeriodOverdue2;
    private int gracePeriodOverdue3;
    private int finesGracePeriod1;
    private int finesGracePeriod2;
    private int finesGracePeriod3;
    private int timesCost;
    private double maxFines;
    private double lostProcessFee;
    private Date lastDate;
    private String indicator;

    public PatronEligibility() {
    }

    public PatronEligibility(String patronCategory, String itemCategory, String smdCode, String branch, int loanPeriod) {
        this.patronCategory = patronCategory;
        this.itemCategory = itemCategory;
        this.smdCode = smdCode;
        this.branch = branch;
        this.loanPeriod = loanPeriod;
    }

    public PatronEligibility(String patronCategory, String itemCategory, String smdCode, String branch, int loanPeriod, String loanPeriodType, String patronCategoryDesc, String branchDesc, String itemCategoryDesc, String smdCodeDesc) {
        this.patronCategory = patronCategory;
        this.itemCategory = itemCategory;
        this.smdCode = smdCode;
        this.branch = branch;
        this.loanPeriod = loanPeriod;
        this.loanPeriodType = loanPeriodType;
        this.branchDesc = branchDesc;
        this.itemCategoryDesc = itemCategoryDesc;
        this.smdCodeDesc = smdCodeDesc;
        this.patronCategoryDesc = patronCategoryDesc;
    }

    public PatronEligibility(String patronCategory, String itemCategory, String smdCode, String branch, String indicator) {
        this.patronCategory = patronCategory;
        this.itemCategory = itemCategory;
        this.smdCode = smdCode;
        this.branch = branch;
        this.indicator = indicator;
    }

    public PatronEligibility(String patronCategory, String patronCategoryDesc, String itemCategory, String itemCategoryDesc, String smdCode, String smdCodeDesc, String branch, String branchDesc, String loanPeriodString) {
        this.patronCategory = patronCategory;
        this.itemCategory = itemCategory;
        this.smdCode = smdCode;
        this.branch = branch;
        this.loanPeriodString = loanPeriodString;
        this.patronCategoryDesc = patronCategoryDesc;
        this.itemCategoryDesc = itemCategoryDesc;
        this.smdCodeDesc = smdCodeDesc;
        this.branchDesc = branchDesc;
    }

    public String getPatronCategory() {
        return this.patronCategory;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public String getSmdCode() {
        return this.smdCode;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getRecordDate() {
        return this.recordDate;
    }

    public String getRecordBy() {
        return this.recordBy;
    }

    public int getLoanPeriod() {
        return this.loanPeriod;
    }

    public int getEligibilityNum() {
        return this.eligibilityNum;
    }

    public int getRenewalNum() {
        return this.renewalNum;
    }

    public int getGracePeriodOverdue1() {
        return this.gracePeriodOverdue1;
    }

    public int getGracePeriodOverdue2() {
        return this.gracePeriodOverdue2;
    }

    public int getGracePeriodOverdue3() {
        return this.gracePeriodOverdue3;
    }

    public int getFinesGracePeriod1() {
        return this.finesGracePeriod1;
    }

    public int getFinesGracePeriod2() {
        return this.finesGracePeriod2;
    }

    public int getFinesGracePeriod3() {
        return this.finesGracePeriod3;
    }

    public int getTimesCost() {
        return this.timesCost;
    }

    public double getMaxFines() {
        return this.maxFines;
    }

    public double getLostProcessFee() {
        return this.lostProcessFee;
    }

    public Date getLastDate() {
        return this.lastDate;
    }

    public String getBranchDesc() {
        return this.branchDesc;
    }

    public String getItemCategoryDesc() {
        return this.itemCategoryDesc;
    }

    public String getSmdCodeDesc() {
        return this.smdCodeDesc;
    }

    public String getLoanPeriodType() {
        return this.loanPeriodType;
    }

    public String getReserveAllow() {
        return this.reserveAllow;
    }

    public String getOverdueAllow() {
        return this.overdueAllow;
    }

    public String getIncludeLostFine() {
        return this.includeLostFine;
    }

    public String getPatronCategoryDesc() {
        return this.patronCategoryDesc;
    }

    public String getIndicator() {
        return this.indicator;
    }

    public String getLoanPeriodString() {
        return this.loanPeriodString;
    }
}
