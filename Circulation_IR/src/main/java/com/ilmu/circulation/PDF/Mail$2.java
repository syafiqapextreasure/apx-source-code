/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.activation.FileDataSource
 */
package com.ilmu.circulation.PDF;

import javax.activation.FileDataSource;

static class Mail$2
extends FileDataSource {
    Mail$2(String $anonymous0) {
        super($anonymous0);
    }

    public String getContentType() {
        return "application/octet-stream";
    }
}
