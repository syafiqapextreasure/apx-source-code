/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.mail.Authenticator
 *  javax.mail.PasswordAuthentication
 */
package com.kmlink.ilmu.overdueNotification;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

static class OverdueNotification$1
extends Authenticator {
    private final /* synthetic */ String val$username;
    private final /* synthetic */ String val$password;

    OverdueNotification$1(String string, String string2) {
        this.val$username = string;
        this.val$password = string2;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.val$username, this.val$password);
    }
}
