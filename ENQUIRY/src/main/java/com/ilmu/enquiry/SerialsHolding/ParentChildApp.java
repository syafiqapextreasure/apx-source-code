/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package com.ilmu.enquiry.SerialsHolding;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ParentChildApp {
    public static void main(String[] args) {
        Entry workNode = new Entry("Work");
        workNode.add(new Entry("Effort"));
        workNode.add(new Entry("Trust"));
        Entry salaryNode = new Entry("Salary");
        Entry cultureNode = new Entry("Culture");
        cultureNode.add(salaryNode);
        cultureNode.add(workNode);
        Gson g = new Gson();
        System.out.println(g.toJson((Object)cultureNode));
    }

    public static class Entry {
        private String name;
        private List<Entry> children;

        public Entry(String name) {
            this.name = name;
        }

        public void add(Entry node) {
            if (this.children == null) {
                this.children = new ArrayList<Entry>();
            }
            this.children.add(node);
        }
    }
}
