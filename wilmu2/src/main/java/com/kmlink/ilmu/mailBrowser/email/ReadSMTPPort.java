/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.shared.global.Config;
import java.io.IOException;

public class ReadSMTPPort {
    private static final String Tpl_GetPort = Config.get("SMTPPort");

    public static String readPort() throws IOException {
        String port = Tpl_GetPort;
        return port;
    }
}
