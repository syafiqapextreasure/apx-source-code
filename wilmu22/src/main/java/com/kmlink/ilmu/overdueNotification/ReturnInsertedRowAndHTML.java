/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.overdueNotification;

import java.util.List;

public class ReturnInsertedRowAndHTML {
    private String html;
    private List<String> insertedRow;

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<String> getInsertedRow() {
        return this.insertedRow;
    }

    public void setInsertedRow(List<String> insertedRow) {
        this.insertedRow = insertedRow;
    }

    public ReturnInsertedRowAndHTML() {
    }

    public ReturnInsertedRowAndHTML(List<String> insertedRow) {
        this.insertedRow = insertedRow;
    }

    public ReturnInsertedRowAndHTML(String html, List<String> insertedRow) {
        this.html = html;
        this.insertedRow = insertedRow;
    }
}
