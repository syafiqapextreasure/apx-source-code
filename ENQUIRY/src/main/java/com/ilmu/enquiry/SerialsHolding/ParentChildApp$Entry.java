/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.SerialsHolding;

import java.util.ArrayList;
import java.util.List;

public static class ParentChildApp$Entry {
    private String name;
    private List<ParentChildApp.Entry> children;

    public ParentChildApp$Entry(String name) {
        this.name = name;
    }

    public void add(ParentChildApp.Entry node) {
        if (this.children == null) {
            this.children = new ArrayList<ParentChildApp.Entry>();
        }
        this.children.add(node);
    }
}
