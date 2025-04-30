/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.PDF;

import javax.activation.FileDataSource;

class Mail$2
extends FileDataSource {
    Mail$2(String $anonymous0) {
        super($anonymous0);
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }
}
