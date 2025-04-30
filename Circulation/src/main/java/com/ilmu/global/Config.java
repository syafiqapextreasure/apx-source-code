/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String get(String key) {
        System.out.println("key" + key);
        Properties properties = new Properties();
        try {
            String FILENAME = "Circulation_Config";
            System.out.println("FILENAME is input "+FILENAME);
            InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(FILENAME);
            System.out.println("Here is input "+inputStream);
            properties.load(inputStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("file" + properties.getProperty(key));
        return properties.getProperty(key);
    }
}
