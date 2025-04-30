/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.DemoLogger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Logging {
    public static void main(String[] args) {
        DemoLogger obj = new DemoLogger();
        obj.makeSomeLog();
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger("global");
        log.log(Level.INFO, "This is a log message");
    }
}
