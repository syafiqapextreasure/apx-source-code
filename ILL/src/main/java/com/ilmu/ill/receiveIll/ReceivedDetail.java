/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.receiveIll;

public class ReceivedDetail {
    private String Accession;
    private String SMD;
    private String ItemCatgory;
    private String Loca;
    private String Con;

    public String getAccession() {
        return this.Accession;
    }

    public void setAccession(String accession) {
        this.Accession = accession;
    }

    public String getSMD() {
        return this.SMD;
    }

    public void setSMD(String sMD) {
        this.SMD = sMD;
    }

    public String getItemCatgory() {
        return this.ItemCatgory;
    }

    public void setItemCatgory(String itemCatgory) {
        this.ItemCatgory = itemCatgory;
    }

    public String getLoca() {
        return this.Loca;
    }

    public void setLoca(String loca) {
        this.Loca = loca;
    }

    public String getCon() {
        return this.Con;
    }

    public void setCon(String con) {
        this.Con = con;
    }

    public ReceivedDetail(String Accession, String SMD, String ItemCatgory, String Loca, String Con) {
        this.Accession = Accession;
        this.SMD = SMD;
        this.ItemCatgory = ItemCatgory;
        this.Loca = Loca;
        this.Con = Con;
    }

    public String toString() {
        return "ReceivedDetail [Accession = " + this.Accession + ", SMD = " + this.SMD + ", ItemCatgory = " + this.ItemCatgory + ", Loca = " + this.Loca + ", Con = " + this.Con + "]";
    }
}
