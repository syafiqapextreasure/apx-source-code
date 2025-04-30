/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.enquiry.SerialsHolding;

import java.util.ArrayList;
import java.util.List;

public static class SerialsHolding$Entry {
    private String name;
    private List<SerialsHolding.Entry> children;

    public SerialsHolding$Entry(String name) {
        this.name = name;
    }

    public void add(SerialsHolding.Entry node) {
        if (this.children == null) {
            this.children = new ArrayList<SerialsHolding.Entry>();
        }
        this.children.add(node);
    }
}
