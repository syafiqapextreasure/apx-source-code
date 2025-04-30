/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronDetails;

public class BlobToString {
    public static String convert(byte[] blob) {
        String str = "";
        if (blob != null && blob.length > 0) {
            byte[] byArray = blob;
            int n = blob.length;
            int n2 = 0;
            while (n2 < n) {
                byte b = byArray[n2];
                str = String.valueOf(str) + (char)b;
                ++n2;
            }
        }
        return str;
    }
}
