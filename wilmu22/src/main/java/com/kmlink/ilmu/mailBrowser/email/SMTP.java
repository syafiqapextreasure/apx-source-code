/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.shared.global.Config;
import java.io.IOException;

public class SMTP {
    private static final String Tpl_GetSenderEmailAddr = Config.get("Sender");
    private static final String Tpl_GetHost = Config.get("SMTPHost");
    private static final String Tpl_GetPsw = Config.get("SMTPpassword");
    private static final String Tpl_GetPort = Config.get("SMTPPort");
    private static final String Tpl_GetUsername = Config.get("SMTPUsername");
    private static final String Tpl_GetPassword = Config.get("SMTPPassword");
    private static final String Tpl_GetStartTLS = Config.get("SMTPStartTLS");

    public static String readSender() throws IOException {
        String sender = Tpl_GetSenderEmailAddr;
        return sender;
    }

    public static String readHostName() throws IOException {
        String hostName = Tpl_GetHost;
        return hostName;
    }

    public static String readSMPTPasswrd() throws IOException {
        String pswd = Tpl_GetPsw;
        return pswd;
    }

    public static String readPort() throws IOException {
        String port = Tpl_GetPort;
        return port;
    }

    public static String username() throws IOException {
        String username = Tpl_GetUsername;
        return username;
    }

    public static String password() throws IOException {
        String password = Tpl_GetPassword;
        return password;
    }

    public static String startTLS() throws IOException {
        String startTLS = Tpl_GetStartTLS;
        return startTLS;
    }
}
