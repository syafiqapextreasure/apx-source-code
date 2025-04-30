/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.sourceforge.barbecue.Barcode
 *  net.sourceforge.barbecue.BarcodeFactory
 */
package com.kmlink.ilmu.parable.parable_beta;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;

public class BarcodeGenerator {
    public void generateBarcode(String value) throws Exception {
        Barcode barcode = BarcodeFactory.create3of9((String)value, (boolean)true);
        barcode.setBarHeight(80);
        barcode.setBarWidth(2);
        String relativeWebPath = "/WEB-INF/static/file1.ext";
    }
}
