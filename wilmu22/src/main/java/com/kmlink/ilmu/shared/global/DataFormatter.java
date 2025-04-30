/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.global;

import java.text.DecimalFormat;

public class DataFormatter {
    public static String replaceDashForNull(String input) {
        if (input == null) {
            return "-";
        }
        return input;
    }

    public static String replaceEmptyForNull(String input) {
        if (input == null) {
            return "";
        }
        return input;
    }

    public static String format2Decimal(double input) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(input);
    }
}
