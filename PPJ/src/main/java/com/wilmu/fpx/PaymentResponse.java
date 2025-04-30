/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

public class PaymentResponse {
    private String status;
    private String message;

    public PaymentResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
