/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String get(String key) {
        Properties properties = new Properties();
        try {
            String FILENAME = "config";
            InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(FILENAME);
            properties.load(inputStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
