/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

public class CurrentUser {
    private String user = null;

    public CurrentUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public static String getCurrUser() {
        String user = "Sysadmin";
        return user;
    }
}
