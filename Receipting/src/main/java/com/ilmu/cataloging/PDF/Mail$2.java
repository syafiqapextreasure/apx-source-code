/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import javax.activation.FileDataSource;

static class Mail$2
extends FileDataSource {
    Mail$2(String $anonymous0) {
        super($anonymous0);
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }
}
