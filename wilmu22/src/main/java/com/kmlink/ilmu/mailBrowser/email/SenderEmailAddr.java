/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.shared.global.Config;
import java.io.IOException;

public class SenderEmailAddr {
    private static final String Tpl_GetSenderEmailAddr = Config.get("Sender");

    public static String readSender() throws IOException {
        String sender = Tpl_GetSenderEmailAddr;
        return sender;
    }
}
