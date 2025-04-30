package com.ppk.topEntity.formsEntity.dto;

public class BorangCadangan {
    private String NoReq;
    private String TarikhPermintaan;
    private String Judul;
    private String JenisBahan;
    private String BilSalinan;
    private String Status;
    private String StatusDesc;

    // Constructor
    public BorangCadangan(String NoReq, String TarikhPermintaan, String Judul, String JenisBahan, 
                          String BilSalinan, String Status, String StatusDesc) {
        this.NoReq = NoReq;
        this.TarikhPermintaan = TarikhPermintaan;
        this.Judul = Judul;
        this.JenisBahan = JenisBahan;
        this.BilSalinan = BilSalinan;
        this.Status = Status;
        this.StatusDesc = StatusDesc;
    }

    // Getters
    public String getNoReq() {
        return Handler.chkIsNull(this.NoReq);
    }

    public String getTarikhPermintaan() {
        return Handler.chkIsNull(this.TarikhPermintaan);
    }

    public String getJudul() {
        return Handler.chkIsNull(this.Judul);
    }

    public String getJenisBahan() {
        return Handler.chkIsNull(this.JenisBahan);
    }

    public String getBilSalinan() {
        return Handler.chkIsNull(this.BilSalinan);
    }

    public String getStatus() {
        return Handler.chkIsNull(this.Status);
    }

    public String getStatusDesc() {
        return Handler.chkIsNull(this.StatusDesc);
    }
}

 class Handler {

    public static String chkIsNull(String value) {
        return (value == null || value.isEmpty()) ? "N/A" : value;
    }
}


