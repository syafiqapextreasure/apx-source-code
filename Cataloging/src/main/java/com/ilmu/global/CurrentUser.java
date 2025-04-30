/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

public class CurrentUser {
    private String username = null;

    public void setCurrentUser(String value) {
        this.username = value;
    }

    public String getCurrUser() {
        System.out.println("User" + this.username);
        return this.username;
    }
}
