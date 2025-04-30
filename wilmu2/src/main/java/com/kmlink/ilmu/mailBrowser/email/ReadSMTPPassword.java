/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.shared.global.Config;
import java.io.IOException;

public class ReadSMTPPassword {
    private static final String Tpl_GetPsw = Config.get("SMTPpassword");

    public static String readSMPTPasswrd() throws IOException {
        String pswd = Tpl_GetPsw;
        return pswd;
    }
}
