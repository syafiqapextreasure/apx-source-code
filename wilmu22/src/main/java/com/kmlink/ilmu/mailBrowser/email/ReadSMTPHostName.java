/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.shared.global.Config;
import java.io.IOException;

public class ReadSMTPHostName {
    private static final String Tpl_GetHost = Config.get("SMTPHost");

    public static String readHostName() throws IOException {
        String hostName = Tpl_GetHost;
        return hostName;
    }
}
