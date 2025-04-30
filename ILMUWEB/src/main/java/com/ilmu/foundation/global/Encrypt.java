/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.global;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String crypt(String GL14PASSWORD) {
        if (GL14PASSWORD == null || GL14PASSWORD.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        digester.update(GL14PASSWORD.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        int i = 0;
        while (i < hash.length) {
            if ((0xFF & hash[i]) < 16) {
                hexString.append("0" + Integer.toHexString(0xFF & hash[i]));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            ++i;
        }
        System.out.println(hexString.toString());
        return hexString.toString();
    }
}
