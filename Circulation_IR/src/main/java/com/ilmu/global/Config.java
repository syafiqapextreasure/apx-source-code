/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String get(String key) {
        System.out.println("key:" + key);
        Properties properties = new Properties();
        try {
            String FILENAME = "config";
            InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(FILENAME);
            System.out.println(Config.class.getClassLoader().getResourceAsStream(FILENAME) + "=123");
            System.out.println(inputStream + "inputStream");
            properties.load(inputStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("file" + properties.getProperty(key));
        return properties.getProperty(key);
    }
}
