/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.SerialsHolding;

public class ArraySerialsHolding {
    private String orderno;
    private String volume;
    private String issueno;
    private String issuelbl;
    private String copy;

    public String getorderno() {
        return this.orderno;
    }

    public String getvolume() {
        return this.volume;
    }

    public String getissueno() {
        return this.issueno;
    }

    public String getissuelbl() {
        return this.issuelbl;
    }

    public String getcopy() {
        return this.copy;
    }

    public void setorderno(String orderno) {
        this.orderno = orderno;
    }

    public void setvolume(String volume) {
        this.volume = volume;
    }

    public void setissueno(String issueno) {
        this.issueno = issueno;
    }

    public void setissuelbl(String issuelbl) {
        this.issuelbl = issuelbl;
    }

    public void setcopy(String copy) {
        this.copy = copy;
    }

    public ArraySerialsHolding(String orderno, String volume, String issueno, String issuelbl, String copy) {
        this.orderno = orderno;
        this.volume = volume;
        this.issueno = issueno;
        this.issuelbl = issuelbl;
        this.copy = copy;
    }

    public String toString() {
        return "ArraySerialsHolding [orderno = " + this.orderno + ", volume = " + this.volume + ", issueno= " + this.issueno + ", issuelbl= " + this.issuelbl + ", copy = " + this.copy + "]";
    }
}
