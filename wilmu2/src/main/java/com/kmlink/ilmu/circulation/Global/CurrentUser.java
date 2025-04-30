/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Global;

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
