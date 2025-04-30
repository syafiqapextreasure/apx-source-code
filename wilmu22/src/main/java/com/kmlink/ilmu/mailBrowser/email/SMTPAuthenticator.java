/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.mail.PasswordAuthentication
 */
package com.kmlink.ilmu.mailBrowser.email;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator {
    private static final String SMTP_AUTH_USER = "neucanvas@kmlink.com.my";
    private static final String SMTP_AUTH_PASSWORD = "Kl$b!@#$";

    public PasswordAuthentication getPasswordAuthentication() {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PASSWORD;
        return new PasswordAuthentication(username, password);
    }
}
