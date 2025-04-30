/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.cataloging.Template_Maint;

public class Cataloging {
    private String TableName;
    private int Id;
    private String Name;
    private String Tags;
    private String[] status;
    private String status1;
    private String[] TempName1;
    private String[] Tags1;
    private String[] Subfields1;
    private String[] Indi1;
    private String[] Indi2;
    private String Ind1;
    private String Ind2;
    private String TagName;
    private String Marc;
    private String Subf;
    private String SubfDesc;
    public boolean valid;
    private String TempName;
    private int TempId;
    private String Rpt;
    private String Manda;
    private int[] TplId;
    private String IndiLvl;
    private String IndiDesc;
    private String CtrlCode;
    private String CtrlDesc;
    private String Hit;
    private String AccNo;
    private String DocNo;
    private String AccItemCat;
    private String AccStat;
    private String Title;
    private String CallNo;
    private String DelayDate;
    private int TotalDocNo;
    private String TotalStat;
    private String TotalDoc;

    public int getTempId() {
        return this.TempId;
    }

    public void setTempId(int TempId) {
        this.TempId = TempId;
    }

    public String getAccNo() {
        return this.AccNo;
    }

    public void setAccNo(String AccNo) {
        this.AccNo = AccNo;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCallNo() {
        return this.CallNo;
    }

    public void setCallNo(String CallNo) {
        this.CallNo = CallNo;
    }

    public String getDocNo() {
        return this.DocNo;
    }

    public void setDocNo(String DocNo) {
        this.DocNo = DocNo;
    }

    public String getDelayDate() {
        return this.DelayDate;
    }

    public void setDelayDate(String DelayDate) {
        this.DelayDate = DelayDate;
    }

    public String getAccItemCat() {
        return this.AccItemCat;
    }

    public void setAccItemCat(String AccItemCat) {
        this.AccItemCat = AccItemCat;
    }

    public String getAccStat() {
        return this.AccStat;
    }

    public void setAccStat(String AccStat) {
        this.AccStat = AccStat;
    }

    public String getTempName() {
        return this.TempName;
    }

    public void setTempName(String TempName) {
        this.TempName = TempName;
    }

    public String[] getTempName1() {
        return this.TempName1;
    }

    public void setTempName1(String[] TempName1) {
        this.TempName1 = TempName1;
    }

    public String getStatus1() {
        return this.status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String[] getStatus() {
        return this.status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public int[] getTplId() {
        return this.TplId;
    }

    public void setTplId(int[] TplId) {
        this.TplId = TplId;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getIndiLvl() {
        return this.IndiLvl;
    }

    public void setIndiLvl(String IndiLvl) {
        this.IndiLvl = IndiLvl;
    }

    public String getIndiDesc() {
        return this.IndiDesc;
    }

    public void setIndiDesc(String IndiDesc) {
        this.IndiDesc = IndiDesc;
    }

    public String getInd1() {
        return this.Ind1;
    }

    public void setInd1(String Ind1) {
        this.Ind1 = Ind1;
    }

    public String getInd2() {
        return this.Ind2;
    }

    public void setInd2(String Ind2) {
        this.Ind2 = Ind2;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTags() {
        return this.Tags;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }

    public String[] getTags1() {
        return this.Tags1;
    }

    public void setTags1(String[] Tags1) {
        this.Tags1 = Tags1;
    }

    public String[] getSubfields1() {
        return this.Subfields1;
    }

    public void setSubfields1(String[] Subfields1) {
        this.Subfields1 = Subfields1;
    }

    public String[] getIndi1() {
        return this.Indi1;
    }

    public void setIndi1(String[] Indi1) {
        this.Indi1 = Indi1;
    }

    public String[] getIndi2() {
        return this.Indi2;
    }

    public void setIndi2(String[] Indi2) {
        this.Indi2 = Indi2;
    }

    public String getTagName() {
        return this.TagName;
    }

    public void setTagName(String TagName) {
        this.TagName = TagName;
    }

    public String getMarc() {
        return this.Marc;
    }

    public void setMarc(String Marc) {
        this.Marc = Marc;
    }

    public String getSubfields() {
        return this.Subf;
    }

    public void setSubfields(String Subf) {
        this.Subf = Subf;
    }

    public String getSubfDesc() {
        return this.SubfDesc;
    }

    public void setSubfDesc(String SubfDesc) {
        this.SubfDesc = SubfDesc;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean newValid) {
        this.valid = newValid;
    }

    public String getRpt() {
        return this.Rpt;
    }

    public void setRpt(String Rpt) {
        this.Rpt = Rpt;
    }

    public String getCtrlCode() {
        return this.CtrlCode;
    }

    public void setCtrlCode(String CtrlCode) {
        this.CtrlCode = CtrlCode;
    }

    public String getCtrlDesc() {
        return this.CtrlDesc;
    }

    public void setCtrlDesc(String CtrlDesc) {
        this.CtrlDesc = CtrlDesc;
    }

    public String getManda() {
        return this.Manda;
    }

    public void setManda(String Manda) {
        this.Manda = Manda;
    }

    public int getTotalDocNo() {
        return this.TotalDocNo;
    }

    public void setTotalDocNo(int TotalDocNo) {
        this.TotalDocNo = TotalDocNo;
    }

    public String getTotalDoc() {
        return this.TotalDoc;
    }

    public void setTotalDoc(String TotalDoc) {
        this.TotalDoc = TotalDoc;
    }

    public String getTotalStat() {
        return this.TotalStat;
    }

    public void setTotalStat(String TotalStat) {
        this.TotalStat = TotalStat;
    }
}
