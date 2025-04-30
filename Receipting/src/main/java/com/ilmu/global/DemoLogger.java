/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Config;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

class DemoLogger {
    private static final Logger LOGGER = Logger.getLogger("global");
    private static final String Logging_File = Config.get("logger");

    DemoLogger() {
    }

    public void makeSomeLog() {
        try {
            File file = new File("filename.txt");
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "My first Log Message");
    }
}
