package com.ppk.topController.membership.renual.entity;

public class RenewFee {
    private String renewFee;
    private String renewGrc;

    // Constructor
    public RenewFee(String renewFee, String renewGrc) {
        this.renewFee = renewFee;
        this.renewGrc = renewGrc;
    }

    // Getters
    public String getRenewFee() {
        return renewFee;
    }

    public String getRenewGrc() {
        return renewGrc;
    }
}